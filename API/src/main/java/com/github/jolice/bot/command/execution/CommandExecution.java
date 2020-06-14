package com.github.jolice.bot.command.execution;

import com.github.jolice.bot.MessageEvent;
import com.github.jolice.bot.command.arguments.Arguments;

public interface CommandExecution {

    Arguments getArguments();

    MessageEvent getMessageEvent();

    String getBody();

    long getChatId();
}
