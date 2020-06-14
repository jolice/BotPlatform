package com.github.jolice.bot.message;

public interface MessageFactory {

    OutgoingMessage newOutgoingMessage(long chatId);
}
