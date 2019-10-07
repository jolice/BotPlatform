package io.riguron.bot.vk.message;

import com.vk.api.sdk.objects.messages.Message;
import io.riguron.bot.vk.media.VKMediaSet;
import io.riguron.bot.api.message.IncomingMessage;
import io.riguron.bot.vk.media.VKMediaSet;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VKIncomingMessageTest {

    private static final int VALUE = 1;

    private static final String TEXT = "Text";

    private static final long LONG_VALUE = 1L;

    private IncomingMessage incomingTelegramMessage;

    VKIncomingMessageTest() {
        Message message = mock(Message.class);
        when(message.getId()).thenReturn(VALUE);
        when(message.getDate()).thenReturn(VALUE);
        when(message.getTitle()).thenReturn(TEXT);
        when(message.getBody()).thenReturn(TEXT);
        when(message.getChatId()).thenReturn((int) LONG_VALUE);
        incomingTelegramMessage = new VKIncomingMessage(message);
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
        assertEquals(incomingTelegramMessage.getMedia(), new VKMediaSet(Collections.emptyList()));
    }

    @Test
    void getChatId() {
        assertEquals(incomingTelegramMessage.getChatId(), LONG_VALUE);
    }
}