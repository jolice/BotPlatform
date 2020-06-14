package com.github.jolice.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import com.github.jolice.bot.telegram.attachment.TelegramAttachment;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import com.github.jolice.bot.Sticker;
import com.github.jolice.bot.attachment.MessageAttachment;
import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.message.OutgoingMessage;

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
