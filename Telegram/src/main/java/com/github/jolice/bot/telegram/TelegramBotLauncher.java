package com.github.jolice.bot.telegram;

import com.github.jolice.bot.telegram.message.IncomingTelegramMessage;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.github.jolice.bot.BotLauncher;
import com.github.jolice.bot.MessageHandler;
import lombok.extern.slf4j.Slf4j;

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
