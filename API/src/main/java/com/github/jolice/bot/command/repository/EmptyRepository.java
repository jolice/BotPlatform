package com.github.jolice.bot.command.repository;

import com.github.jolice.bot.command.Command;
import com.github.jolice.bot.command.NullCommand;

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
