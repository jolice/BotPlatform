package com.github.jolice.bot.engine.message;

import com.github.jolice.bot.engine.command.DispatcherCommandHandler;
import com.github.jolice.bot.Bot;
import com.github.jolice.bot.MessageContext;
import com.github.jolice.bot.MessageHandler;
import com.github.jolice.bot.application.ApplicationMessageHandler;
import com.github.jolice.bot.command.CommandHandler;
import com.github.jolice.bot.command.repository.CommandRepository;
import com.github.jolice.bot.message.IncomingMessage;
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