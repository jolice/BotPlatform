package com.github.jolice.bot.command;

import com.github.jolice.bot.command.execution.CommandExecution;

import java.util.Collections;
import java.util.List;

public enum NullCommand implements Command {

    INSTANCE;

    @Override
    public void execute(CommandExecution commandExecution) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> aliases() {
        return Collections.emptyList();
    }
}
