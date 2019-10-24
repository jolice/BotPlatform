package io.riguron.bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import io.riguron.bot.telegram.message.IncomingTelegramMessage;
import io.riguron.bot.api.BotLauncher;
import io.riguron.bot.api.MessageHandler;
import io.riguron.bot.telegram.message.IncomingTelegramMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
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
                log.error("Can't start a bot", e);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
