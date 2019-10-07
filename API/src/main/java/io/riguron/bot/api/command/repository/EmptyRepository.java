package io.riguron.bot.api.command.repository;

import io.riguron.bot.api.command.Command;
import io.riguron.bot.api.command.NullCommand;

public class EmptyRepository implements CommandRepository {
    @Override
    public void registerCommand(Command command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command getCommand(String input) {
        return NullCommand.INSTANCE;
    }
}
