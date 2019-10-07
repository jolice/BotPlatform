package io.riguron.bot.telegram.message;

import com.pengrad.telegrambot.model.Message;
import io.riguron.bot.telegram.media.TelegramMediaSet;
import io.riguron.bot.api.media.MediaSet;
import io.riguron.bot.telegram.media.TelegramMediaSet;
import io.riguron.bot.api.message.IncomingMessage;

public class IncomingTelegramMessage implements IncomingMessage {

    private Message message;

    public IncomingTelegramMessage(Message message) {
        this.message = message;
    }

    @Override
    public int getId() {
        return message.messageId();
    }

    @Override
    public long getDate() {
        return Long.valueOf(message.date());
    }

    @Override
    public String getTitle() {
        return message.chat().title();
    }

    @Override
    public String getText() {
        return message.text();
    }

    @Override
    public MediaSet getMedia() {
        return new TelegramMediaSet(message);
    }

    @Override
    public long getChatId() {
        return message.chat().id();
    }
}
