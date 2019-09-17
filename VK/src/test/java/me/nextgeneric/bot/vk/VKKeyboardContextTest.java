package me.nextgeneric.bot.vk;

import me.nextgeneric.bot.vk.keyboard.VKKeyboard;
import me.nextgeneric.bot.vk.keyboard.VKKeyboardBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VKKeyboardContextTest {

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