package io.riguron.bot.api;

import io.riguron.bot.api.attachment.AttachmentFactory;
import io.riguron.bot.api.attachment.AttachmentFactory;
import io.riguron.bot.api.message.OutgoingMessage;

public interface MessageContext {

    AttachmentFactory attachment();

    OutgoingMessage newMessage(long chatId);

    KeyboardContext keyboard();

}
