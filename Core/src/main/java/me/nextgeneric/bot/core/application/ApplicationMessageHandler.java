package me.nextgeneric.bot.core.application;

import me.nextgeneric.bot.core.MessageEvent;

public interface ApplicationMessageHandler {

    void handleMessage(MessageEvent messageEvent);

    ApplicationMessageHandler NO_OP = messageRequest -> {
        throw new UnsupportedOperationException();
    };

}
