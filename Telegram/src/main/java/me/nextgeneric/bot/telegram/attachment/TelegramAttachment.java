package me.nextgeneric.bot.telegram.attachment;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.core.attachment.MessageAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;

import java.nio.file.Path;

@Getter(AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public abstract class TelegramAttachment implements MessageAttachment {

    private Path path;
    private long chatId;

    public TelegramAttachment(AttachmentData attachmentData) {
        this.path = attachmentData.getPath();
        this.chatId = attachmentData.getChatId();
    }

    public abstract void apply(AttachmentConfigurer dynamicAttachment);

}
