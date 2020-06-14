package com.github.jolice.bot.media;

import java.util.List;

public interface MediaSet {

    List<PhotoMedia> getPhotos();

    List<VideoMedia> getVideos();

    List<AudioMedia> getAudios();

    List<DocumentMedia> getDocuments();

}
