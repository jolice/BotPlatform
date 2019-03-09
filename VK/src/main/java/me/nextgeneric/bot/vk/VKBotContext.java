package me.nextgeneric.bot.vk;

import me.nextgeneric.bot.core.Bot;
import me.nextgeneric.bot.core.type.BotType;
import org.springframework.stereotype.Component;

@Component
public class VKBotContext implements Bot {

    @Override
    public BotType getType() {
        return BotType.VK;
    }
}
