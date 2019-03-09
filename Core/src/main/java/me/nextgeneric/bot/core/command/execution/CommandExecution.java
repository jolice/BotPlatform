package me.nextgeneric.bot.core.command.execution;

import me.nextgeneric.bot.core.MessageEvent;
import me.nextgeneric.bot.core.command.arguments.Arguments;

public interface CommandExecution {

    Arguments getArguments();

    MessageEvent getMessage();

    String getBody();

    long getChatId();
}
