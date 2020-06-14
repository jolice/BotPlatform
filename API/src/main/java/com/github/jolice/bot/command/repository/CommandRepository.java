package com.github.jolice.bot.command.repository;

import com.github.jolice.bot.command.Command;

public interface CommandRepository {

    void registerCommand(Command command);

    Command getCommand(String input);

    CommandRepository EMPTY = new EmptyRepository();
}
