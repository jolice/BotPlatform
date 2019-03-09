package me.nextgeneric.bot.telegram.attachment;

import com.pengrad.telegrambot.model.request.InputMediaVideo;
import com.pengrad.telegrambot.request.SendMediaGroup;
import com.pengrad.telegrambot.request.SendVideo;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class TelegramVideoAttachmentTest {

    @Test
    void whenApplyVideoAttachmentThenAdded() {
        AttachmentConfigurer attachmentConfigurer = spy(new AttachmentConfigurer(1L));
        TelegramVideoAttachment telegramVideoAttachment = new TelegramVideoAttachment(new AttachmentData(Paths.get("path"), 1L));
        telegramVideoAttachment.apply(attachmentConfigurer);
        // Make sure that one attachment was added
        verify(attachmentConfigurer).setOrAdd(any(InputMediaVideo.class), any());

        assertEquals(attachmentConfigurer.getSendRequest().getClass(), SendVideo.class);

        assertEquals(attachmentConfigurer.getSendRequest().getParameters().get("video").toString(), "path");
        telegramVideoAttachment.apply(attachmentConfigurer);

        assertNull(attachmentConfigurer.getSendRequest());
        SendMediaGroup sendMediaGroup = attachmentConfigurer.getMediaGroup();
        assertNotNull(sendMediaGroup.getParameters().get("media"));
        assertEquals(StringUtils.countOccurrencesOf(sendMediaGroup.getParameters().get("media").toString(), "type"), 2);

    }

}