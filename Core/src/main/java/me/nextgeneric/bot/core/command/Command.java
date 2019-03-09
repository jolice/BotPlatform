package me.nextgeneric.bot.core.command;

import me.nextgeneric.bot.core.command.execution.CommandExecution;

import java.util.List;

public interface Command {

    void execute(CommandExecution commandExecution);

    List<String> aliases();

    Command NULL = new NullCommand();
}
