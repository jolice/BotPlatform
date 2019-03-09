package me.nextgeneric.bot.core.command.repository;

import me.nextgeneric.bot.core.command.Command;

public interface CommandRepository {

    void registerCommand(Command command);

    Command getCommand(String input);

    CommandRepository EMPTY = new EmptyRepository();
}
