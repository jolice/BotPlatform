package me.nextgeneric.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;
import me.nextgeneric.bot.core.message.MessageFactory;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramMessageFactory implements MessageFactory {

    private TelegramBot telegramBot;

    @Autowired
    public TelegramMessageFactory(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public OutgoingMessage newOutgoingMessage(long chatId) {
        return new OutgoingTelegramMessage(new AttachmentConfigurer(chatId), telegramBot, chatId);
    }
}
