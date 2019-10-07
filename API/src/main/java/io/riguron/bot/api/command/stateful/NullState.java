package io.riguron.bot.api.command.stateful;

import io.riguron.bot.api.command.execution.CommandExecution;

public enum  NullState implements CommandState {

    INSTANCE;

    @Override
    public boolean execute(CommandExecution execution) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void prompt(CommandExecution commandExecution) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CommandState nextState() {
        throw new UnsupportedOperationException();
    }
}
