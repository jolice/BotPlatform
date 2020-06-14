package com.github.jolice.bot.telegram.attachment;

import com.pengrad.telegrambot.request.SendDocument;
import com.github.jolice.bot.telegram.attachment.support.AttachmentConfigurer;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.attachment.AttachmentData;
import com.github.jolice.bot.attachment.DocumentAttachment;

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
