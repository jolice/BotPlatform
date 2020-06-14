package com.github.jolice.bot.message;

import com.github.jolice.bot.media.MediaSet;

public interface IncomingMessage {

    int getId();

    long getDate();

    String getTitle();

    String getText();

    MediaSet getMedia();

    long getChatId();


}
