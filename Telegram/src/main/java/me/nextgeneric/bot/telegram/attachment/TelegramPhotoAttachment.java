package me.nextgeneric.bot.telegram.attachment;

import com.pengrad.telegrambot.model.request.InputMediaPhoto;
import com.pengrad.telegrambot.request.SendPhoto;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.core.attachment.PhotoAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;

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
