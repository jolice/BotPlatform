package com.github.jolice.bot.command.stateful;

import com.github.jolice.bot.command.execution.CommandExecution;

public interface CommandState {

    boolean execute(CommandExecution execution);

    void prompt(CommandExecution commandExecution);

    CommandState nextState();

}
