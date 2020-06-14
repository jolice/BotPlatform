package com.github.jolice.bot;

import com.github.jolice.bot.attachment.AttachmentFactory;
import com.github.jolice.bot.message.OutgoingMessage;

public interface MessageContext {

    AttachmentFactory attachment();

    OutgoingMessage newMessage(long chatId);

    KeyboardContext keyboard();

}
