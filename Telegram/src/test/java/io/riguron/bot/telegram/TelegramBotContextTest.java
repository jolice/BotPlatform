package io.riguron.bot.telegram;

import io.riguron.bot.api.type.BotType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelegramBotContextTest {

    @Test
    void whenGetBotTypeThenTG() {
        assertEquals(new TelegramBotContext().getType(), BotType.TELEGRAM);
    }
}