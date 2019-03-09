package me.nextgeneric.bot.engine.message;

import me.nextgeneric.bot.core.Bot;
import me.nextgeneric.bot.core.MessageContext;
import me.nextgeneric.bot.core.MessageHandler;
import me.nextgeneric.bot.core.application.ApplicationMessageHandler;
import me.nextgeneric.bot.core.command.CommandHandler;
import me.nextgeneric.bot.core.command.repository.CommandRepository;
import me.nextgeneric.bot.core.message.IncomingMessage;
import me.nextgeneric.bot.engine.command.DispatcherCommandHandler;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

class DispatcherHandlerTest {

    @Test
    void messageReceived() {
        IncomingMessage incomingMessage = mock(IncomingMessage.class);

        CommandHandler commandHandler = spy(new DispatcherCommandHandler(mock(CommandRepository.class)));
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