package io.riguron.bot.api.command.stateful;

import io.riguron.bot.api.command.execution.CommandExecution;

public interface CommandState {

    boolean execute(CommandExecution execution);

    void prompt(CommandExecution commandExecution);

    CommandState nextState();

}
