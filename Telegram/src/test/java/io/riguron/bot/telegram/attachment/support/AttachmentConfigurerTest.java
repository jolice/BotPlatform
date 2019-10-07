package io.riguron.bot.telegram.attachment.support;

import com.pengrad.telegrambot.model.request.InputMedia;
import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendPhoto;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.telegram.keyboard.TelegramKeyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AttachmentConfigurerTest {

    private AttachmentConfigurer attachmentConfigurer = new AttachmentConfigurer(1L);

    @Test
    void setOrAdd() {
        InputMedia<?> media = new InputMediaPhoto(new byte[0]);
        AbstractSendRequest<?> sendRequest = mock(AbstractSendRequest.class);
        attachmentConfigurer.setOrAdd(media, aLong -> sendRequest);

        // Only one set, then setting send request, not media group
        assertEquals(attachmentConfigurer.getSendRequest(), sendRequest);
        assertEquals(attachmentConfigurer.getFinalQuery(null).getClass(), sendRequest.getClass());

        InputMedia<?> secondMedia = new InputMediaPhoto(new byte[0]);
        AbstractSendRequest<?> secondRequest = mock(AbstractSendRequest.class);

        // Adding second
        attachmentConfigurer.setOrAdd(secondMedia, aLong -> secondRequest);

        // Send request is set to null, using media collection instead
        assertNull(attachmentConfigurer.getSendRequest());

        // Final query is media group because two attachments added
        assertEquals(attachmentConfigurer.getFinalQuery(null).getClass(), SendMediaGroup.class);
       assertEquals(attachmentConfigurer.getMediaGroup().getParameters().keySet().stream().filter(x -> x.startsWith("attach")).count(), 2);

    }

    @Test
    void whenPlainSetThenAdded() {
        AbstractSendRequest<?> sendRequest = mock(AbstractSendRequest.class);
        attachmentConfigurer.set(sendRequest);
        assertEquals(attachmentConfigurer.getSendRequest(), sendRequest);
        assertEquals(attachmentConfigurer.getFinalQuery(null), sendRequest);
    }

    @Test
    void whenSetKeyboardThenApplied() {
        AbstractSendRequest<?> sendRequest = new SendPhoto(1L, new byte[0]);
        attachmentConfigurer.set(sendRequest);
        BotKeyboard botKeyboard = new TelegramKeyboard(mock(Keyboard.class));
        assertEquals(attachmentConfigurer.getFinalQuery(botKeyboard), sendRequest);
        assertTrue(sendRequest.getParameters().containsKey("reply_markup"));
    }

    @Test
    void whenGetEmptyMediaGroupThenExceptionFired() {
        assertThrows(IllegalStateException.class, () -> new AttachmentConfigurer(1L).getMediaGroup());
    }

}