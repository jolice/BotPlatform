package me.nextgeneric.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendDocument;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.attachment.AttachmentData;
import me.nextgeneric.bot.core.attachment.DocumentAttachment;
import me.nextgeneric.bot.telegram.attachment.support.AttachmentConfigurer;

@EqualsAndHashCode(callSuper = false)
@ToString
public class TelegramDocumentAttachment extends TelegramAttachment implements DocumentAttachment {

    private SendDocument sendDocument;

    public TelegramDocumentAttachment(AttachmentData attachmentData) {
        super(attachmentData);
        this.sendDocument = new SendDocument(attachmentData.getChatId(), attachmentData.getPath().toFile());
    }

    @Override
    public void caption(String text) {
        this.sendDocument.caption(text);
    }

    @Override
    public void apply(AttachmentConfigurer dynamicAttachment) {
        dynamicAttachment.set(sendDocument);

    }
}
