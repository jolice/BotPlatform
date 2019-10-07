package io.riguron.bot.vk;

import io.riguron.bot.api.Bot;
import io.riguron.bot.api.type.BotType;
import org.springframework.stereotype.Component;

public class VKBotContext implements Bot {

    @Override
    public BotType getType() {
        return BotType.VK;
    }
}
