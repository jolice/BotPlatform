package com.github.jolice.bot.vk;

import com.github.jolice.bot.Bot;
import com.github.jolice.bot.type.BotType;

public class VKBotContext implements Bot {

    @Override
    public BotType getType() {
        return BotType.VK;
    }
}
