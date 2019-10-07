package io.riguron.bot.telegram.media;

import com.pengrad.telegrambot.model.Video;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.VideoMedia;

@EqualsAndHashCode
@ToString
public class TelegramVideo implements VideoMedia {

    private Video video;

    public TelegramVideo(Video video) {
        this.video = video;
    }

    @Override
    public int getWidth() {
        return video.width();
    }

    @Override
    public int getHeight() {
        return video.height();
    }

    @Override
    public int getDuration() {
        return video.duration();
    }

    @Override
    public String getPath() {
        return video.fileId();
    }
}
