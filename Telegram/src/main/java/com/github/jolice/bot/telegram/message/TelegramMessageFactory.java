package com.github.jolice.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import com.github.jolice.bot.message.MessageFactory;
import com.github.jolice.bot.message.OutgoingMessage;

public class TelegramMessageFactory implements MessageFactory {

    private TelegramBot telegramBot;

    public TelegramMessageFactory(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public OutgoingMessage newOutgoingMessage(long chatId) {
        return new OutgoingTelegramMessage(new AttachmentConfigurer(chatId), telegramBot, chatId);
    }
}
