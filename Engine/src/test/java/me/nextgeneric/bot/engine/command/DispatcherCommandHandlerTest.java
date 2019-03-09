package me.nextgeneric.bot.engine.command;

import me.nextgeneric.bot.core.MessageContext;
import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.command.Command;
import me.nextgeneric.bot.core.command.CommandHandler;
import me.nextgeneric.bot.core.command.arguments.OffsetArguments;
import me.nextgeneric.bot.core.command.execution.CommandExecution;
import me.nextgeneric.bot.core.command.execution.StaticCommandExecution;
import me.nextgeneric.bot.core.command.repository.CommandRepository;
import me.nextgeneric.bot.core.command.repository.VirtualCommandRepository;
import me.nextgeneric.bot.core.message.IncomingMessage;
import me.nextgeneric.bot.core.message.OutgoingMessage;
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
        when(commandRepository.getCommand(any())).thenReturn(Command.NULL);
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

        if (command != Command.NULL) {
            String[] argumentData = text.split(" ");
            Arguments offsetArguments = new OffsetArguments(Arrays.asList(argumentData));
            command.execute(new StaticCommandExecution(argumentData[0], messageEvent, offsetArguments));
        } else {
            messageEvent.getMessageContext().newMessage(messageEvent.getIncomingMessage().getChatId()).text(UNKNOWN_COMMAND).send();
        }
    }

     */
}