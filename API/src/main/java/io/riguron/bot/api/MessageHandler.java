package io.riguron.bot.api;

import io.riguron.bot.api.message.IncomingMessage;

public interface MessageHandler {

    void messageReceived(IncomingMessage incomingMessage);
}
