package io.riguron.bot.telegram.attachment;

import com.pengrad.telegrambot.model.request.InputMediaVideo;
import com.pengrad.telegrambot.request.SendVideo;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.attachment.AttachmentData;
import io.riguron.bot.api.attachment.VideoAttachment;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TelegramVideoAttachment extends TelegramAttachment implements VideoAttachment {

    private InputMediaVideo inputMediaVideo;

    public TelegramVideoAttachment(AttachmentData attachmentData) {
        super(attachmentData);
        this.inputMediaVideo = new InputMediaVideo(attachmentData.getPath().toFile());
    }

    @Override
    public void caption(String text) {
        this.inputMediaVideo.caption(text);
    }

    @Override
    public void apply(AttachmentConfigurer dynamicAttachment) {
        dynamicAttachment.setOrAdd(this.inputMediaVideo, aLong -> new SendVideo(aLong, getPath().toFile()));
    }
}
