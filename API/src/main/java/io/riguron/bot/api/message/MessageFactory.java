package io.riguron.bot.api.message;

public interface MessageFactory {

    OutgoingMessage newOutgoingMessage(long chatId);
}
