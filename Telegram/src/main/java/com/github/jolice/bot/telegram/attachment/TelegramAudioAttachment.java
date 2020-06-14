package com.github.jolice.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendAudio;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.attachment.AttachmentData;
import com.github.jolice.bot.attachment.AudioAttachment;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TelegramAudioAttachment extends TelegramAttachment implements AudioAttachment {

    private SendAudio sendAudio;

    public TelegramAudioAttachment(AttachmentData attachmentData) {
        super(attachmentData);
        this.sendAudio = new SendAudio(getChatId(), getPath().toFile());
    }

    @Override
    public void caption(String text) {
        this.sendAudio.caption(text);
    }

    @Override
    public void apply(AttachmentConfigurer dynamicAttachment) {
        dynamicAttachment.set(this.sendAudio);
    }
}
