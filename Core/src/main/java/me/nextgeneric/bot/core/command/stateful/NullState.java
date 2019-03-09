package me.nextgeneric.bot.core.command.stateful;

import me.nextgeneric.bot.core.command.execution.CommandExecution;

public class NullState implements CommandState {
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
