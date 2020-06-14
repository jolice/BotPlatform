package com.github.jolice.bot.command;

import com.github.jolice.bot.command.execution.CommandExecution;

import java.util.List;

public interface Command {

    void execute(CommandExecution commandExecution);

    List<String> aliases();

}
