package io.riguron.bot.vk.media;

import com.vk.api.sdk.objects.video.Video;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.VideoMedia;

@EqualsAndHashCode
@ToString
public class VKVideo implements VideoMedia {

    private Video videoFull;

    public VKVideo(Video videoFull) {
        this.videoFull = videoFull;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getDuration() {
        return videoFull.getDuration();
    }

    @Override
    public String getPath() {
        return videoFull.getFiles().getMp1080();
    }
}
