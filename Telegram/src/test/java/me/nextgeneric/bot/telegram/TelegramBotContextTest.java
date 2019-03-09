package me.nextgeneric.bot.telegram;

import me.nextgeneric.bot.core.type.BotType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelegramBotContextTest {

    @Test
    void whenGetBotTypeThenTG() {
        assertEquals(new TelegramBotContext().getType(), BotType.TELEGRAM);
    }
}