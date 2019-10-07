package io.riguron.bot.api.command;

import io.riguron.bot.api.command.execution.CommandExecution;

import java.util.List;

public interface Command {

    void execute(CommandExecution commandExecution);

    List<String> aliases();

    Command NULL = new NullCommand();
}
