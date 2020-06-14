package com.github.jolice.bot.telegram;

import com.github.jolice.bot.type.BotType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelegramBotContextTest {

    @Test
    void whenGetBotTypeThenTG() {
        assertEquals(new TelegramBotContext().getType(), BotType.TELEGRAM);
    }
}