package com.github.jolice.bot.engine.command;

import com.github.jolice.bot.MessageEvent;
import com.github.jolice.bot.command.Command;
import com.github.jolice.bot.command.CommandHandler;
import com.github.jolice.bot.command.NullCommand;
import com.github.jolice.bot.command.arguments.Arguments;
import com.github.jolice.bot.command.arguments.OffsetArguments;
import com.github.jolice.bot.command.execution.StaticCommandExecution;
import com.github.jolice.bot.command.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DispatcherCommandHandler implements CommandHandler {

    @Value("${command.unknown:Unknown command}")
    private String unknownCommand;

    private CommandRepository commandRepository;

    @Autowired
    public DispatcherCommandHandler(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public void handle(MessageEvent messageEvent) {
        String text = messageEvent.getIncomingMessage().getText().replaceFirst("/", "");

        String[] parts = text.split(" ");
        Command command = commandRepository.getCommand(parts[0]);
        if (command != NullCommand.INSTANCE) {
            Arguments offsetArguments = new OffsetArguments(Arrays.asList(parts));
            command.execute(new StaticCommandExecution(parts[0], messageEvent, offsetArguments));
        } else {
            messageEvent.getMessageContext().newMessage(messageEvent.getIncomingMessage().getChatId()).text(unknownCommand).send();
        }
    }
}
