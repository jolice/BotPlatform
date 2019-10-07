package io.riguron.bot.engine.message;

import io.riguron.bot.api.Bot;
import io.riguron.bot.api.MessageContext;
import io.riguron.bot.api.MessageEvent;
import io.riguron.bot.api.MessageHandler;
import io.riguron.bot.api.annotation.Warning;
import io.riguron.bot.api.application.ApplicationMessageHandler;
import io.riguron.bot.api.command.CommandHandler;
import io.riguron.bot.api.message.IncomingMessage;
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
        MessageEvent messageEvent = new MessageEvent(incomingMessage, messageContext, bot);
        String body = incomingMessage.getText();
        if (body.startsWith("/")) {
            commandHandler.handle(messageEvent);
        } else {
            delegate.handleMessage(messageEvent);
        }
    }
}
