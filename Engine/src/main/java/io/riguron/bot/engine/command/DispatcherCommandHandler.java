package io.riguron.bot.engine.command;

import io.riguron.bot.api.MessageEvent;
import io.riguron.bot.api.command.*;
import io.riguron.bot.api.command.arguments.Arguments;
import io.riguron.bot.api.command.arguments.OffsetArguments;
import io.riguron.bot.api.command.execution.StaticCommandExecution;
import io.riguron.bot.api.command.repository.CommandRepository;
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
