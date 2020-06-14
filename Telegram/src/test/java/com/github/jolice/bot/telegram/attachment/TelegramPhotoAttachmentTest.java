package com.github.jolice.bot.telegram.attachment;

import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendPhoto;
import com.github.jolice.bot.attachment.AttachmentData;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TelegramPhotoAttachmentTest {

   
    @Test
    void apply() {
        AttachmentConfigurer attachmentConfigurer = spy(new AttachmentConfigurer(1L));
        TelegramPhotoAttachment telegramPhotoAttachment = new TelegramPhotoAttachment(new AttachmentData(Paths.get("path"), 1L));
        telegramPhotoAttachment.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer).setOrAdd(any(InputMediaPhoto.class), any());

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendPhoto.class);
        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("photo").toString(), "path");
        telegramPhotoAttachment.apply(attachmentConfigurer);

        assertNull(attachmentConfigurer.getSendRequest());
        SendMediaGroup sendMediaGroup = attachmentConfigurer.getMediaGroup();
        assertNotNull(sendMediaGroup.getParameters().get("media"));
        assertEquals(StringUtils.countOccurrencesOf(sendMediaGroup.getParameters().get("media").toString(), "type"), 2);
    }
}