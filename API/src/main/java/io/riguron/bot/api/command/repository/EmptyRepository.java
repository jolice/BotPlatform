package io.riguron.bot.api.command.repository;

import io.riguron.bot.api.command.Command;

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
