package com.github.jolice.bot.application;

import com.github.jolice.bot.MessageEvent;

public interface ApplicationMessageHandler {

    void handleMessage(MessageEvent messageEvent);

}
