package me.nextgeneric.bot.telegram;

import me.nextgeneric.bot.core.KeyboardContext;
import me.nextgeneric.bot.core.MessageContext;
import me.nextgeneric.bot.core.attachment.AttachmentFactory;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import me.nextgeneric.bot.core.message.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramMessageContext implements MessageContext {

    private AttachmentFactory attachmentFactory;
    private MessageFactory messageFactory;
    private KeyboardContext keyboardContext;

    @Autowired
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
