package io.riguron.bot.api.command.repository;

import io.riguron.bot.api.command.Command;

public interface CommandRepository {

    void registerCommand(Command command);

    Command getCommand(String input);

    CommandRepository EMPTY = new EmptyRepository();
}
