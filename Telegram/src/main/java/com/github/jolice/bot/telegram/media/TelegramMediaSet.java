package com.github.jolice.bot.telegram.media;

import com.pengrad.telegrambot.model.Message;
import com.github.jolice.bot.media.AbstractMediaSet;

import java.util.ArrayList;

public class TelegramMediaSet extends AbstractMediaSet {

    public TelegramMediaSet(Message message) {
        if (message.photo() != null && message.photo().length > 0) {
            super.lazyAdd(this::getOriginalPhotos, () -> setPhotos(new ArrayList<>()), message.photo()[message.photo().length - 1], TelegramPhoto::new);
        }
        super.lazyAdd(this::getOriginalVideos, () -> setVideos(new ArrayList<>()), message.video(), TelegramVideo::new);
        super.lazyAdd(this::getOriginalAudios, () -> setAudios(new ArrayList<>()), message.audio(), TelegramAudio::new);
        super.lazyAdd(this::getOriginalDocuments, () -> setDocuments(new ArrayList<>()), message.document(), TelegramDocument::new);
    }
}

