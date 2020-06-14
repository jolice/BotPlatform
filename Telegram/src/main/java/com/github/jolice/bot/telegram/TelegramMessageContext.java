package com.github.jolice.bot.telegram;

import com.github.jolice.bot.KeyboardContext;
import com.github.jolice.bot.MessageContext;
import com.github.jolice.bot.attachment.AttachmentFactory;
import com.github.jolice.bot.message.OutgoingMessage;
import com.github.jolice.bot.message.MessageFactory;

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
