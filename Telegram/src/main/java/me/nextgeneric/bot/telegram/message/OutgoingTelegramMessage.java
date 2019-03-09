package me.nextgeneric.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import me.nextgeneric.bot.core.Sticker;
import me.nextgeneric.bot.core.attachment.MessageAttachment;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import me.nextgeneric.bot.telegram.attachment.TelegramAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;

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
