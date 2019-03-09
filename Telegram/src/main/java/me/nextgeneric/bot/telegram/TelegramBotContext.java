package me.nextgeneric.bot.telegram;

import me.nextgeneric.bot.core.Bot;
import me.nextgeneric.bot.core.type.BotType;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotContext implements Bot {


    @Override
    public BotType getType() {
        return BotType.TELEGRAM;
    }
}
