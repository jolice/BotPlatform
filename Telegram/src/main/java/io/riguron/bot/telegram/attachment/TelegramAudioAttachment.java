package io.riguron.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendAudio;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.attachment.AttachmentData;
import io.riguron.bot.api.attachment.AudioAttachment;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;

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
