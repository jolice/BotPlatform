package io.riguron.bot.api.message;

import io.riguron.bot.api.media.MediaSet;
import io.riguron.bot.api.media.MediaSet;

public interface IncomingMessage {

    int getId();

    long getDate();

    String getTitle();

    String getText();

    MediaSet getMedia();

    long getChatId();


}
