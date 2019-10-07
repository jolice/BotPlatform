package io.riguron.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import io.riguron.bot.telegram.attachment.TelegramAttachment;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import io.riguron.bot.api.Sticker;
import io.riguron.bot.api.attachment.MessageAttachment;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.message.OutgoingMessage;
import io.riguron.bot.telegram.attachment.TelegramAttachment;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;

public class OutgoingTelegramMessage implements OutgoingMessage {


    private AttachmentConfigurer dynamicAttachment;
    private TelegramBot telegramBot;
    private BotKeyboard keyboard;
    private long chatId;

    public OutgoingTelegramMessage(AttachmentConfigurer dynamicAttachment, TelegramBot telegramBot, long chatId) {
        this.dynamicAttachment = dynamicAttachment;
        this.telegramBot = telegramBot;
        this.chatId = chatId;
    }

    @Override
    public OutgoingMessage text(String text) {
        this.dynamicAttachment.set(new SendMessage(this.chatId, text));
        return this;
    }

    @Override
    public OutgoingMessage sticker(Sticker sticker) {
        this.dynamicAttachment.set(new SendSticker(this.chatId, String.valueOf(sticker.getId())));
        return this;
    }

    @Override
    public OutgoingMessage attachment(MessageAttachment messageAttachment) {
        ((TelegramAttachment) messageAttachment).apply(this.dynamicAttachment);
        return this;
    }

    @Override
    public OutgoingMessage keyboard(BotKeyboard keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    @Override
    public void send() {
        this.telegramBot.execute(this.dynamicAttachment.getFinalQuery(this.keyboard));
    }

}
