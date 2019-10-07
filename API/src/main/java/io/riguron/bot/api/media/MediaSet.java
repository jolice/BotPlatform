package io.riguron.bot.api.media;

import java.util.List;

public interface MediaSet {

    List<PhotoMedia> getPhotos();

    List<VideoMedia> getVideos();

    List<AudioMedia> getAudios();

    List<DocumentMedia> getDocuments();

}
