package com.github.jolice.bot.telegram.attachment;

import com.pengrad.telegrambot.model.request.InputMediaVideo;
import com.pengrad.telegrambot.request.SendVideo;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.attachment.AttachmentData;
import com.github.jolice.bot.attachment.VideoAttachment;

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
