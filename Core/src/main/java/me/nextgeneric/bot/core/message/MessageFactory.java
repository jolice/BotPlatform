package me.nextgeneric.bot.core.message;

public interface MessageFactory {

    OutgoingMessage newOutgoingMessage(long chatId);
}
