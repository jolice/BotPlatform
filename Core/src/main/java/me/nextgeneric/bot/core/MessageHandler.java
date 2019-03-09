package me.nextgeneric.bot.core;

import me.nextgeneric.bot.core.message.IncomingMessage;

public interface MessageHandler {

    void messageReceived(IncomingMessage incomingMessage);
}
