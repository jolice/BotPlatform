package me.nextgeneric.bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import me.nextgeneric.bot.core.BotLauncher;
import me.nextgeneric.bot.core.MessageHandler;
import me.nextgeneric.bot.telegram.message.IncomingTelegramMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotLauncher implements BotLauncher {

    private TelegramBot telegramBot;
    private MessageHandler messageHandler;

    @Autowired
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
