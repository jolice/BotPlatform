package io.riguron.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendDocument;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.attachment.AttachmentData;
import io.riguron.bot.api.attachment.DocumentAttachment;
import io.riguron.bot.telegram.attachment.support.AttachmentConfigurer;

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
