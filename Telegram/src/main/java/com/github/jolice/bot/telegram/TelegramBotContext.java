package com.github.jolice.bot.telegram;

import com.github.jolice.bot.Bot;
import com.github.jolice.bot.type.BotType;

public class TelegramBotContext implements Bot {


    @Override
    public BotType getType() {
        return BotType.TELEGRAM;
    }
}
