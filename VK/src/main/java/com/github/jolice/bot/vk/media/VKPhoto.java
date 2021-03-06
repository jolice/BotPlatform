package com.github.jolice.bot.vk.media;

import com.vk.api.sdk.objects.photos.Photo;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.media.PhotoMedia;

@EqualsAndHashCode
@ToString
public class VKPhoto implements PhotoMedia {

    private Photo photoFull;

    public VKPhoto(Photo photoFull) {
        this.photoFull = photoFull;
    }

    @Override
    public int getWidth() {
        return photoFull.getWidth();
    }

    @Override
    public int getHeight() {
        return photoFull.getHeight();
    }

    @Override
    public String getPath() {
        return photoFull.getPhoto2560();
    }
}
