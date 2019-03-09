package me.nextgeneric.bot.core.command;

import me.nextgeneric.bot.core.MessageEvent;

public interface CommandHandler {

    void handle(MessageEvent messageEvent);
}
