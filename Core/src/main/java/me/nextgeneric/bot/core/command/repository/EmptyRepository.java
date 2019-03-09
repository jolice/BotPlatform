package me.nextgeneric.bot.core.command.repository;

import me.nextgeneric.bot.core.command.Command;

public class EmptyRepository implements CommandRepository {
    @Override
    public void registerCommand(Command command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command getCommand(String input) {
        throw new UnsupportedOperationException();
    }
}
