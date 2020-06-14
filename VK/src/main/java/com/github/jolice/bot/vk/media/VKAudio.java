package com.github.jolice.bot.vk.media;

import com.vk.api.sdk.objects.audio.AudioFull;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.media.AudioMedia;

@EqualsAndHashCode
@ToString
public class VKAudio implements AudioMedia {

    private AudioFull audio;

    public VKAudio(AudioFull audio) {
        this.audio = audio;
    }

    @Override
    public int getDuration() {
        return audio.getDuration();
    }

    @Override
    public String getTitle() {
        return audio.getTitle();
    }

    @Override
    public String getPath() {
        return audio.getUrl();
    }
}
