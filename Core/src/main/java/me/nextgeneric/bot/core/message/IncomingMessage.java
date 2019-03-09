package me.nextgeneric.bot.core.message;

import me.nextgeneric.bot.core.media.MediaSet;

public interface IncomingMessage {

    int getId();

    long getDate();

    String getTitle();

    String getText();

    MediaSet getMedia();

    long getChatId();


}
