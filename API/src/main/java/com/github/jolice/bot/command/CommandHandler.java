package com.github.jolice.bot.command;

import com.github.jolice.bot.MessageEvent;

public interface CommandHandler {

    void handle(MessageEvent messageEvent);
}
