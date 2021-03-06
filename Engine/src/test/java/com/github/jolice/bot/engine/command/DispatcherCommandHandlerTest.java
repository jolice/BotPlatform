package com.github.jolice.bot.engine.command;

import com.github.jolice.bot.MessageContext;
import com.github.jolice.bot.MessageEvent;
import com.github.jolice.bot.command.Command;
import com.github.jolice.bot.command.CommandHandler;
import com.github.jolice.bot.command.NullCommand;
import com.github.jolice.bot.command.arguments.OffsetArguments;
import com.github.jolice.bot.command.execution.CommandExecution;
import com.github.jolice.bot.command.execution.StaticCommandExecution;
import com.github.jolice.bot.command.repository.CommandRepository;
import com.github.jolice.bot.command.repository.VirtualCommandRepository;
import com.github.jolice.bot.message.IncomingMessage;
import com.github.jolice.bot.message.OutgoingMessage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class DispatcherCommandHandlerTest {

    @Test
    void whenCommandNotNullThenExecuted() {
        CommandRepository commandRepository = new VirtualCommandRepository();
        CommandHandler dispatcherHandler = new DispatcherCommandHandler(commandRepository);
        Command command = spy(new Command() {
            @Override
            public void execute(CommandExecution commandExecution) {
            }

            @Override
            public List<String> aliases() {
                return Collections.singletonList("command");
            }
        });
        commandRepository.registerCommand(command);

        MessageEvent messageEvent = mock(MessageEvent.class);

        IncomingMessage incomingMessage = mock(IncomingMessage.class);
        when(incomingMessage.getText()).thenReturn("/command arg1 arg2");
        when(messageEvent.getIncomingMessage()).thenReturn(incomingMessage);

        dispatcherHandler.handle(messageEvent);

        StaticCommandExecution staticCommandExecution = new StaticCommandExecution("command", messageEvent, new OffsetArguments(Arrays.asList("command", "arg1", "arg2")));

        verify(command).execute(staticCommandExecution);

    }


    @Test
    void whenCommandIsNullThenAppropriateMessageReturned() {
        CommandRepository commandRepository = mock(CommandRepository.class);
        when(commandRepository.getCommand(any())).thenReturn(NullCommand.INSTANCE);
        CommandHandler commandHandler = new DispatcherCommandHandler(commandRepository);
        MessageEvent messageEvent = mock(MessageEvent.class);

        IncomingMessage incomingMessage = mock(IncomingMessage.class);
        when(incomingMessage.getText()).thenReturn("/some unknown command");
        when(messageEvent.getIncomingMessage()).thenReturn(incomingMessage);
        when(messageEvent.getIncomingMessage().getChatId()).thenReturn(1L);

        MessageContext messageContext = mock(MessageContext.class);
        when(messageEvent.getMessageContext()).thenReturn(messageContext);
        OutgoingMessage errorMessage = mock(OutgoingMessage.class);
        when(messageContext.newMessage(eq(1L))).thenReturn(errorMessage);

        when(errorMessage.text(any())).thenReturn(errorMessage);

        commandHandler.handle(messageEvent);
        verify(errorMessage).text(any());


    }

    /*

    @Override
    public void handle(MessageEvent messageEvent) {
        String text = messageEvent.getIncomingMessage().getText().replaceFirst("/", "");

        Command command = commandRepository.getCommand(text);

        if (command != NullCommand.INSTANCE) {
            String[] argumentData = text.split(" ");
            Arguments offsetArguments = new OffsetArguments(Arrays.asList(argumentData));
            command.execute(new StaticCommandExecution(argumentData[0], messageEvent, offsetArguments));
        } else {
            messageEvent.getMessageContext().newMessage(messageEvent.getIncomingMessage().getChatId()).text(UNKNOWN_COMMAND).send();
        }
    }

     */
}