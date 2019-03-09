package me.nextgeneric.bot.telegram.message;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import me.nextgeneric.bot.core.message.IncomingMessage;
import me.nextgeneric.bot.telegram.media.TelegramMediaSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IncomingTelegramMessageTest {

    private static final int VALUE = 1;

    private static final String TEXT = "Text";

    private static final long LONG_VALUE = 1L;

    private IncomingMessage incomingTelegramMessage;

    IncomingTelegramMessageTest() {
        Message message = mock(Message.class);
        when(message.messageId()).thenReturn(VALUE);
        when(message.date()).thenReturn(VALUE);
        when(message.chat()).thenReturn(mock(Chat.class));
        when(message.chat().title()).thenReturn(TEXT);
        when(message.text()).thenReturn(TEXT);
        when(message.chat().id()).thenReturn(LONG_VALUE);
        incomingTelegramMessage = new IncomingTelegramMessage(message);
    }

    @Test
    void getId() {
        assertEquals(incomingTelegramMessage.getId(), VALUE);
    }

    @Test
    void getDate() {
        assertEquals(incomingTelegramMessage.getDate(), VALUE);
    }

    @Test
    void getTitle() {
        assertEquals(incomingTelegramMessage.getTitle(), TEXT);
    }

    @Test
    void getText() {
        assertEquals(incomingTelegramMessage.getText(), TEXT);
    }

    @Test
    void getMedia() {
        assertEquals(incomingTelegramMessage.getMedia(), new TelegramMediaSet(mock(Message.class)));
    }

    @Test
    void getChatId() {
        assertEquals(incomingTelegramMessage.getChatId(), LONG_VALUE);
    }
}