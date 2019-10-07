package io.riguron.bot.api.application;

import io.riguron.bot.api.MessageEvent;

public interface ApplicationMessageHandler {

    void handleMessage(MessageEvent messageEvent);

}
