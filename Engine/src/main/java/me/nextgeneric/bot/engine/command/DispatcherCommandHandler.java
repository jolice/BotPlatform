package me.nextgeneric.bot.engine.command;

import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.command.*;
import me.nextgeneric.bot.core.command.arguments.Arguments;
import me.nextgeneric.bot.core.command.arguments.OffsetArguments;
import me.nextgeneric.bot.core.command.execution.StaticCommandExecution;
import me.nextgeneric.bot.core.command.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DispatcherCommandHandler implements CommandHandler {

    private static final String UNKNOWN_COMMAND = "Неизвестная команда!";

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
        if (command != Command.NULL) {
            Arguments offsetArguments = new OffsetArguments(Arrays.asList(parts));
            command.execute(new StaticCommandExecution(parts[0], messageEvent, offsetArguments));
        } else {
            messageEvent.getMessageContext().newMessage(messageEvent.getIncomingMessage().getChatId()).text(UNKNOWN_COMMAND).send();
        }
    }
}
