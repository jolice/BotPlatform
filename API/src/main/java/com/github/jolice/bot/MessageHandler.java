package com.github.jolice.bot;

import com.github.jolice.bot.message.IncomingMessage;

public interface MessageHandler {

    void messageReceived(IncomingMessage incomingMessage);
}
