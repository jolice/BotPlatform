package io.riguron.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendAudio;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import io.riguron.bot.api.attachment.AttachmentData;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TelegramAudioAttachmentTest {

    @Test
    void apply() {
        AttachmentConfigurer attachmentConfigurer = spy(new AttachmentConfigurer(1L));
        TelegramAudioAttachment telegramDocumentAttachment = new TelegramAudioAttachment(new AttachmentData(Paths.get("path"), 1L));
        telegramDocumentAttachment.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer).set(any(SendAudio.class));

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendAudio.class);
        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("audio").toString(), "path");

        TelegramAudioAttachment anotherDocument = new TelegramAudioAttachment(new AttachmentData(Paths.get("path2"), 2L));

        anotherDocument.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer, times(2)).set(any(SendAudio.class));

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendAudio.class);
        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("audio").toString(), "path2");
    }
}