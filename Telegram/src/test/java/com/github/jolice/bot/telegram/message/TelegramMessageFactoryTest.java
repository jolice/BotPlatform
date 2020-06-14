package com.github.jolice.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TelegramMessageFactoryTest {

    private TelegramMessageFactory telegramMessageFactory = new TelegramMessageFactory(mock(TelegramBot.class));

    @Test
    void newOutgoingMessage() {
        assertEquals(telegramMessageFactory.newOutgoingMessage(1L).getClass(), OutgoingTelegramMessage.class);
    }
}