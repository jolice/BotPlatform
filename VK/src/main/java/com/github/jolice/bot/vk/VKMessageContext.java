package com.github.jolice.bot.vk;

import com.github.jolice.bot.KeyboardContext;
import com.github.jolice.bot.MessageContext;
import com.github.jolice.bot.attachment.AttachmentFactory;
import com.github.jolice.bot.message.MessageFactory;
import com.github.jolice.bot.message.OutgoingMessage;


public class VKMessageContext implements MessageContext {

    private AttachmentFactory attachmentFactory;
    private MessageFactory messageFactory;
    private KeyboardContext keyboardContext;

    public VKMessageContext(AttachmentFactory attachmentFactory, MessageFactory messageFactory, KeyboardContext keyboardContext) {
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
        return messageFactory.newOutgoingMessage(chatId);
    }

    @Override
    public KeyboardContext keyboard() {
        return keyboardContext;
    }
}
