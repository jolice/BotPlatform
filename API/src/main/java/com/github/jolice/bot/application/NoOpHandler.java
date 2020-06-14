package com.github.jolice.bot.application;

import com.github.jolice.bot.MessageEvent;

public enum NoOpHandler implements ApplicationMessageHandler {

    INSTANCE;

    @Override
    public void handleMessage(MessageEvent messageEvent) {
    }
}
