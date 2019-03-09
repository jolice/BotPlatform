package me.nextgeneric.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendDocument;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TelegramDocumentAttachmentTest {

    @Test
    void apply() {
        AttachmentConfigurer attachmentConfigurer = spy(new AttachmentConfigurer(1L));
        TelegramDocumentAttachment telegramDocumentAttachment = new TelegramDocumentAttachment(new AttachmentData(Paths.get("path"), 1L));
        telegramDocumentAttachment.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer).set(any(SendDocument.class));

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendDocument.class);
        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("document").toString(), "path");

        TelegramDocumentAttachment anotherDocument = new TelegramDocumentAttachment(new AttachmentData(Paths.get("path2"), 2L));

        anotherDocument.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer, times(2)).set(any(SendDocument.class));

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendDocument.class);
        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("document").toString(), "path2");

    }
}