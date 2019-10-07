package io.riguron.bot.engine.message;

import io.riguron.bot.api.MessageEvent;
import io.riguron.bot.api.application.ApplicationMessageHandler;

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
