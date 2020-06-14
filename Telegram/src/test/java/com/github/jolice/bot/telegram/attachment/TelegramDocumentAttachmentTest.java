package com.github.jolice.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendDocument;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import com.github.jolice.bot.attachment.AttachmentData;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TelegramDocumentAttachmentTest {

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