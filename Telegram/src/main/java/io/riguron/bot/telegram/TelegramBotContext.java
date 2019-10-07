package io.riguron.bot.telegram;

import io.riguron.bot.api.Bot;
import io.riguron.bot.api.type.BotType;
import org.springframework.stereotype.Component;

public class TelegramBotContext implements Bot {


    @Override
    public BotType getType() {
        return BotType.TELEGRAM;
    }
}
