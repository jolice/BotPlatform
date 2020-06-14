package com.github.jolice.bot.engine.message;

import com.github.jolice.bot.MessageEvent;
import com.github.jolice.bot.application.ApplicationMessageHandler;

import java.util.List;

public class MultipleMessageHandler implements ApplicationMessageHandler {

    private List<ApplicationMessageHandler> handlers;

    public MultipleMessageHandler(List<ApplicationMessageHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void handleMessage(MessageEvent messageEvent) {
        handlers.forEach(applicationMessageHandler -> applicationMessageHandler.handleMessage(messageEvent));
    }
}
