package me.nextgeneric.bot.engine.message;

import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.application.ApplicationMessageHandler;

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
