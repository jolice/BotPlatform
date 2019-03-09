package me.nextgeneric.bot.core;

import me.nextgeneric.bot.core.attachment.AttachmentFactory;
import me.nextgeneric.bot.core.message.OutgoingMessage;

public interface MessageContext {

    AttachmentFactory attachment();

    OutgoingMessage newMessage(long chatId);

    KeyboardContext keyboard();

}
