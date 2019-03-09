package me.nextgeneric.bot.core.command.stateful;

import me.nextgeneric.bot.core.command.Command;
import me.nextgeneric.bot.core.command.execution.CommandExecution;

import java.util.HashMap;
import java.util.Map;

public abstract class StatefulCommand implements Command {

    private Map<Long, CommandState> userStates = new HashMap<>();
    private CommandState initialState;

    public StatefulCommand(CommandState initialState) {
        this.initialState = initialState;
    }

    @Override
    public void execute(CommandExecution commandExecution) {
        userStates.compute(commandExecution.getChatId(), (aLong, commandState) -> {
            CommandState resultingState;
            if (commandExecution.getArguments().isEmpty() || commandState == null) {
                resultingState = initialState;
            } else {
                if (commandState.execute(commandExecution)) {
                    resultingState = commandState.nextState();
                    if (resultingState == CommandState.END) {
                        return null;
                    }
                } else {
                    resultingState = commandState;
                }
            }
            resultingState.prompt(commandExecution);
            return resultingState;
        });

    }

}
