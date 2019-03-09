package me.nextgeneric.bot.core.command.repository;

import me.nextgeneric.bot.core.command.Command;

import java.util.HashMap;
import java.util.Map;

public class VirtualCommandRepository implements CommandRepository {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void registerCommand(Command command) {
        command.aliases().forEach(s -> {
            if (commands.put(s.toLowerCase(), command) != null) {
                throw new IllegalStateException("Duplicate alias: " + s);
            }
        });
    }

    @Override
    public Command getCommand(String input) {
        return commands.getOrDefault(input.toLowerCase(), Command.NULL);
    }
}
