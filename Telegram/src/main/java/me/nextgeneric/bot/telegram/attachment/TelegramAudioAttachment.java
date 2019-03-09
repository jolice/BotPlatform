package me.nextgeneric.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendAudio;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.core.attachment.AudioAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;

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
