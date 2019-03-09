package me.nextgeneric.bot.vk;

import me.nextgeneric.bot.core.KeyboardContext;
import me.nextgeneric.bot.core.MessageContext;
import me.nextgeneric.bot.core.attachment.AttachmentFactory;
import me.nextgeneric.bot.core.message.MessageFactory;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VKMessageContext implements MessageContext {

    private AttachmentFactory attachmentFactory;
    private MessageFactory messageFactory;
    private KeyboardContext keyboardContext;

    @Autowired
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
