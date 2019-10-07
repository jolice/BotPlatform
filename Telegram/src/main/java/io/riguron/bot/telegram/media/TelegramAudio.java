package io.riguron.bot.telegram.media;

import com.pengrad.telegrambot.model.Audio;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.AudioMedia;

@EqualsAndHashCode
@ToString
public class TelegramAudio implements AudioMedia {

    private Audio audio;

    public TelegramAudio(Audio audio) {
        this.audio = audio;
    }

    @Override
    public int getDuration() {
        return audio.duration();
    }

    @Override
    public String getTitle() {
        return audio.title();
    }

    @Override
    public String getPath() {
        return audio.fileId();
    }
}
