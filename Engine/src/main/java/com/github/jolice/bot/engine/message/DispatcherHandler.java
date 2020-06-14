package com.github.jolice.bot.engine.message;

import com.github.jolice.bot.Bot;
import com.github.jolice.bot.MessageContext;
import com.github.jolice.bot.MessageEvent;
import com.github.jolice.bot.MessageHandler;
import com.github.jolice.bot.annotation.Warning;
import com.github.jolice.bot.application.ApplicationMessageHandler;
import com.github.jolice.bot.command.CommandHandler;
import com.github.jolice.bot.message.IncomingMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings(Warning.AUTOWIRING)
public class DispatcherHandler implements MessageHandler {

    private CommandHandler commandHandler;
    private ApplicationMessageHandler delegate;
    private Bot bot;
    private MessageContext messageContext;

    public DispatcherHandler(@Qualifier("DelegateHandler") ApplicationMessageHandler delegate, CommandHandler commandHandler, Bot bot, MessageContext messageContext) {
        this.commandHandler = commandHandler;
        this.delegate = delegate;
        this.bot = bot;
        this.messageContext = messageContext;
    }

    @Override
    public void messageReceived(IncomingMessage incomingMessage) {
        if (incomingMessage != null) {
            MessageEvent messageEvent = new MessageEvent(incomingMessage, messageContext, bot);
            String body = incomingMessage.getText();
            if (body.startsWith("/")) {
                commandHandler.handle(messageEvent);
            } else {
                delegate.handleMessage(messageEvent);
            }
        }

    }
}
