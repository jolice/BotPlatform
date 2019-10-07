package io.riguron.bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import io.riguron.bot.telegram.message.IncomingTelegramMessage;
import io.riguron.bot.api.BotLauncher;
import io.riguron.bot.api.MessageHandler;
import io.riguron.bot.telegram.message.IncomingTelegramMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TelegramBotLauncher implements BotLauncher {

    private TelegramBot telegramBot;
    private MessageHandler messageHandler;

    public TelegramBotLauncher(TelegramBot telegramBot, MessageHandler messageHandler) {
        this.telegramBot = telegramBot;
        this.messageHandler = messageHandler;
    }

    @Override
    public void launch() {
        telegramBot.setUpdatesListener(list -> {
            try {
                list.forEach(update -> messageHandler.messageReceived(new IncomingTelegramMessage(update.message())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
