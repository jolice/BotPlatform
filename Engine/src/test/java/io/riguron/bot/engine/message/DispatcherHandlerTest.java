package io.riguron.bot.engine.message;

import io.riguron.bot.engine.command.DispatcherCommandHandler;
import io.riguron.bot.api.Bot;
import io.riguron.bot.api.MessageContext;
import io.riguron.bot.api.MessageHandler;
import io.riguron.bot.api.application.ApplicationMessageHandler;
import io.riguron.bot.api.command.CommandHandler;
import io.riguron.bot.api.command.repository.CommandRepository;
import io.riguron.bot.api.message.IncomingMessage;
import io.riguron.bot.engine.command.DispatcherCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.*;

class DispatcherHandlerTest {

    @Test
    void messageReceived() {
        IncomingMessage incomingMessage = mock(IncomingMessage.class);

        CommandHandler commandHandler = Mockito.spy(new DispatcherCommandHandler(mock(CommandRepository.class)));
        ApplicationMessageHandler delegateHandler = spy(new MultipleMessageHandler(Collections.emptyList()));
        doNothing().when(commandHandler).handle(any());

        MessageHandler dispatcherHandler = new DispatcherHandler(delegateHandler, commandHandler, mock(Bot.class), mock(MessageContext.class));

        when(incomingMessage.getText()).thenReturn("text");
        dispatcherHandler.messageReceived(incomingMessage);
        verify(delegateHandler).handleMessage(any());

        when(incomingMessage.getText()).thenReturn("/text");
        dispatcherHandler.messageReceived(incomingMessage);
        verify(commandHandler).handle(any());

    }

}