package io.riguron.bot.api.command.execution;

import io.riguron.bot.api.MessageEvent;
import io.riguron.bot.api.command.arguments.Arguments;

public interface CommandExecution {

    Arguments getArguments();

    MessageEvent getMessageEvent();

    String getBody();

    long getChatId();
}
