package com.github.jolice.bot.attachment;

public interface AttachmentFactory {

    AudioAttachment audio(AttachmentData attachmentData);

    DocumentAttachment document(AttachmentData attachmentData);

    PhotoAttachment photo(AttachmentData attachmentData);

    VideoAttachment video(AttachmentData attachmentData);

}
