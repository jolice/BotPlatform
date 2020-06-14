package com.github.jolice.bot.telegram.attachment;

import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.request.SendPhoto;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.attachment.AttachmentData;
import com.github.jolice.bot.attachment.PhotoAttachment;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TelegramPhotoAttachment extends TelegramAttachment implements PhotoAttachment {

    private InputMediaPhoto inputMediaPhoto;

    public TelegramPhotoAttachment(AttachmentData attachmentData) {
        super(attachmentData);
        this.inputMediaPhoto = new InputMediaPhoto(attachmentData.getPath().toFile());
    }

    @Override
    public void caption(String text) {
        this.inputMediaPhoto.caption(text);
    }

    @Override
    public void apply(AttachmentConfigurer dynamicAttachment) {
        dynamicAttachment.setOrAdd(this.inputMediaPhoto, aLong -> new SendPhoto(aLong, getPath().toFile()));
    }
}
