package me.nextgeneric.bot.core.command.stateful;

import me.nextgeneric.bot.core.command.execution.CommandExecution;

public interface CommandState {

    boolean execute(CommandExecution execution);

    void prompt(CommandExecution commandExecution);

    CommandState nextState();

    CommandState END = new NullState();

    CommandState NULL = new NullState();
}
