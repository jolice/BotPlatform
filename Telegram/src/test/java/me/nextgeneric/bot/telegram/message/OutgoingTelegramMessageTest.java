package me.nextgeneric.bot.telegram.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendSticker;
import com.pengrad.telegrambot.request.SendVideo;
import me.nextgeneric.bot.core.Sticker;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import me.nextgeneric.bot.telegram.attachment.TelegramAttachment;
import me.nextgeneric.bot.telegram.attachment.TelegramPhotoAttachment;
import me.nextgeneric.bot.telegram.attachment.TelegramVideoAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;
import me.nextgeneric.bot.telegram.keyboard.TelegramKeyboard;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutgoingTelegramMessageTest {

    private static final String KEYBOARD_KEY = "reply_markup";

    private static final String CHAT_ID_PARAM = "chat_id";

    private static final String TEXT = "TEXT";

    private static final int STICKER_ID = 2;

    private static final long CHAT_ID = 1L;

    private OutgoingTelegramMessage outgoingTelegramMessage;
    private AttachmentConfigurer sharedAttachment = new AttachmentConfigurer(CHAT_ID);

    OutgoingTelegramMessageTest() {
        TelegramBot telegramBot = mock(TelegramBot.class);
        outgoingTelegramMessage = new OutgoingTelegramMessage(sharedAttachment, telegramBot, CHAT_ID);
    }


    @Test
    void whenAddTextThenBodyAddedToParams() {
        OutgoingMessage outgoingMsg = outgoingTelegramMessage.text(TEXT);
        assertEquals(outgoingMsg, this.outgoingTelegramMessage);
        assertEquals(sharedAttachment.getSendRequest().getClass(), SendMessage.class);
        Map<String, Object> params = sharedAttachment.getSendRequest().getParameters();
        assertEquals(params.size(), 2);
        assertEquals(params.get("text"), TEXT);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }

    @Test
    void whenStickerAddedThenAddedToRequestParam() {
        Sticker sticker = mock(Sticker.class);
        when(sticker.getId()).thenReturn(STICKER_ID);
        OutgoingMessage outgoingMsg = outgoingTelegramMessage.sticker(sticker);
        assertEquals(outgoingMsg, this.outgoingTelegramMessage);
        assertEquals(sharedAttachment.getSendRequest().getClass(), SendSticker.class);
        Map<String, Object> params = sharedAttachment.getSendRequest().getParameters();
        assertEquals(params.size(), 2);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertEquals(Integer.valueOf(params.get("sticker").toString()), STICKER_ID);
        assertNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }

    @Test
    void whenSinglePhotoAttachmentThenBaseRequest() {
        TelegramAttachment photo = new TelegramPhotoAttachment(new AttachmentData(Paths.get(""), CHAT_ID));
        OutgoingMessage msg = outgoingTelegramMessage.attachment(photo);
        assertEquals(msg, this.outgoingTelegramMessage);
        // Not a media group since sending just one attachment
        assertEquals(sharedAttachment.getSendRequest().getClass(), SendPhoto.class);
        Map<String, Object> params = sharedAttachment.getMediaGroup().getParameters();
        assertEquals(params.size(), 3);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertNotNull(params.get("media"));
        assertNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }

    @Test
    void whenSingleVideoAttachmentThenBaseRequest() {
        TelegramAttachment photo = new TelegramVideoAttachment(new AttachmentData(Paths.get(""), CHAT_ID));
        OutgoingMessage msg = outgoingTelegramMessage.attachment(photo);
        assertEquals(msg, this.outgoingTelegramMessage);
        // Not a media group since sending just one attachment
        assertEquals(sharedAttachment.getSendRequest().getClass(), SendVideo.class);
        Map<String, Object> params = sharedAttachment.getMediaGroup().getParameters();
        assertEquals(params.size(), 3);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertNotNull(params.get("media"));
        assertNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }

    @Test
    void whenMultiplePhotoAttachmentThenMediaGroupUsed() {
        this.whenSinglePhotoAttachmentThenBaseRequest();
        // Moving from single attachment to media group
        outgoingTelegramMessage.attachment(new TelegramPhotoAttachment(new AttachmentData(Paths.get(""), CHAT_ID)));
        assertNull(sharedAttachment.getSendRequest());
        assertNotNull(sharedAttachment.getMediaGroup());
        assertEquals(sharedAttachment.getMediaGroup().getParameters().keySet().stream().filter(x -> x.startsWith("attach")).count(), 2);
        outgoingTelegramMessage.send();
    }

    @Test
    void whenMultipleVideoAttachmentThenMediaGroupUsed() {
        this.whenSingleVideoAttachmentThenBaseRequest();
        // Moving from single attachment to media group
        outgoingTelegramMessage.attachment(new TelegramVideoAttachment(new AttachmentData(Paths.get(""), CHAT_ID)));
        assertNull(sharedAttachment.getSendRequest());
        assertNotNull(sharedAttachment.getMediaGroup());
        assertEquals(sharedAttachment.getMediaGroup().getParameters().keySet().stream().filter(x -> x.startsWith("attach")).count(), 2);
        outgoingTelegramMessage.send();
    }

    @Test
    void testSendTextWithKeyboard() {
        TelegramKeyboard telegramKeyboard = mock(TelegramKeyboard.class);
        ReplyKeyboardMarkup replyKeyboardMarkup = mock(ReplyKeyboardMarkup.class);
        when(telegramKeyboard.getKeyboard()).thenReturn(replyKeyboardMarkup);
        outgoingTelegramMessage.keyboard(telegramKeyboard);
        outgoingTelegramMessage.text(TEXT);
        outgoingTelegramMessage.send();
        Map<String, Object> params = sharedAttachment.getSendRequest().getParameters();
        assertEquals(params.get("text"), TEXT);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertNotNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }

    @Test
    void testSendAttachmentWithKeyboard() {
        TelegramKeyboard telegramKeyboard = mock(TelegramKeyboard.class);
        ReplyKeyboardMarkup replyKeyboardMarkup = mock(ReplyKeyboardMarkup.class);
        when(telegramKeyboard.getKeyboard()).thenReturn(replyKeyboardMarkup);
        outgoingTelegramMessage.keyboard(telegramKeyboard);
        outgoingTelegramMessage.attachment(new TelegramPhotoAttachment(new AttachmentData(Paths.get(""), CHAT_ID)));
        outgoingTelegramMessage.send();
        assertNotNull(sharedAttachment.getSendRequest());
        Map<String, Object> params = sharedAttachment.getSendRequest().getParameters();
        assertNull(params.get("text"), TEXT);
        assertEquals(params.get(CHAT_ID_PARAM), CHAT_ID);
        assertNotNull(params.get(KEYBOARD_KEY));
        outgoingTelegramMessage.send();
    }




}