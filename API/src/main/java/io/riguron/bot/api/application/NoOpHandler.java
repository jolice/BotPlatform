package io.riguron.bot.api.application;

import io.riguron.bot.api.MessageEvent;

public enum NoOpHandler implements ApplicationMessageHandler {

    INSTANCE;

    @Override
    public void handleMessage(MessageEvent messageEvent) {
    }
}
