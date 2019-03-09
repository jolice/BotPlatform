package me.nextgeneric.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.Keyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class TelegramKeyboardTest {

    @Test
    void getKeyboard() {
        Keyboard keyboard = mock(Keyboard.class);
        TelegramKeyboard telegramKeyboard = new TelegramKeyboard(keyboard);
        assertEquals(keyboard, telegramKeyboard.getKeyboard());
    }
}