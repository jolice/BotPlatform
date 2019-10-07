package io.riguron.bot.telegram.keyboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramKeyboardContextTest {

    private final TelegramKeyboardContext telegramKeyboardContext = new TelegramKeyboardContext();

    @Test
    void builder() {
        assertEquals(telegramKeyboardContext.builder().getClass(), TelegramKeyboardBuilder.class);
    }

    @Test
    void remove() {
       assertEquals(telegramKeyboardContext.remove().getClass(), TelegramKeyboard.class);
    }
}