package io.riguron.bot.telegram;

import io.riguron.bot.api.KeyboardContext;
import io.riguron.bot.api.MessageContext;
import io.riguron.bot.api.attachment.AttachmentFactory;
import io.riguron.bot.api.message.OutgoingMessage;
import io.riguron.bot.api.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TelegramMessageContext implements MessageContext {

    private AttachmentFactory attachmentFactory;
    private MessageFactory messageFactory;
    private KeyboardContext keyboardContext;

    public TelegramMessageContext(AttachmentFactory attachmentFactory, MessageFactory messageFactory, KeyboardContext keyboardContext) {
        this.attachmentFactory = attachmentFactory;
        this.messageFactory = messageFactory;
        this.keyboardContext = keyboardContext;
    }

    @Override
    public AttachmentFactory attachment() {
        return attachmentFactory;
    }

    @Override
    public OutgoingMessage newMessage(long chatId) {
        return this.messageFactory.newOutgoingMessage(chatId);
    }

    @Override
    public KeyboardContext keyboard() {
        return this.keyboardContext;
    }
}
