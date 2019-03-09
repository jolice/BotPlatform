package me.nextgeneric.bot.telegram.attachment;

import me.nextgeneric.bot.core.attachment.*;
import org.springframework.stereotype.Component;

@Component
public class TelegramAttachmentFactory implements AttachmentFactory {

    @Override
    public AudioAttachment audio(AttachmentData attachmentData) {
        return new TelegramAudioAttachment(attachmentData);
    }

    @Override
    public DocumentAttachment document(AttachmentData attachmentData) {
        return new TelegramDocumentAttachment(attachmentData);
    }

    @Override
    public PhotoAttachment photo(AttachmentData attachmentData) {
        return new TelegramPhotoAttachment(attachmentData);
    }

    @Override
    public VideoAttachment video(AttachmentData attachmentData) {
        return new TelegramVideoAttachment(attachmentData);
    }

}
