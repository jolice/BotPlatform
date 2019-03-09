package me.nextgeneric.bot.engine.message;

import me.nextgeneric.bot.core.Bot;
import me.nextgeneric.bot.core.MessageContext;
import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.MessageHandler;
import me.nextgeneric.bot.core.annotation.Warning;
import me.nextgeneric.bot.core.application.ApplicationMessageHandler;
import me.nextgeneric.bot.core.command.CommandHandler;
import me.nextgeneric.bot.core.message.IncomingMessage;
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
