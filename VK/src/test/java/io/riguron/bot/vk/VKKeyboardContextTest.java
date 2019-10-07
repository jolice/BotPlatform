package io.riguron.bot.vk;

import io.riguron.bot.vk.keyboard.VKKeyboard;
import io.riguron.bot.vk.keyboard.VKKeyboardBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VKKeyboardContextTest {

    private final VKKeyboardContext telegramKeyboardContext = new VKKeyboardContext();

    @Test
    void builder() {
        assertEquals(telegramKeyboardContext.builder().getClass(), VKKeyboardBuilder.class);
    }

    @Test
    void remove() {
        assertEquals(telegramKeyboardContext.remove().getClass(), VKKeyboard.class);
    }
}