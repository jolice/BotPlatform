package com.github.jolice.bot.media;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Setter(AccessLevel.PROTECTED)
@Data
public class AbstractMediaSet implements MediaSet {

    private List<PhotoMedia> photos = Collections.emptyList();
    private List<VideoMedia> videos = Collections.emptyList();
    private List<AudioMedia> audios = Collections.emptyList();
    private List<DocumentMedia> documents = Collections.emptyList();

    protected <T, R extends Media> void lazyAdd(Supplier<Collection<? super R>> data, Runnable initializer, T element, Function<T, ? extends R> mapper) {

        if (data.get().isEmpty()) {
            initializer.run();
        }

        if (element != null) {

            R mappedMedia = mapper.apply(element);
            data.get().add(mappedMedia);
        }
    }

    @Override
    public List<PhotoMedia> getPhotos() {
        return immutable(getOriginalPhotos());
    }

    @Override
    public List<VideoMedia> getVideos() {
        return immutable(getOriginalVideos());
    }

    @Override
    public List<AudioMedia> getAudios() {
        return immutable(getOriginalAudios());
    }

    @Override
    public List<DocumentMedia> getDocuments() {
        return immutable(getOriginalDocuments());
    }

    private <T> List<T> immutable(List<? extends T> list) {
        return list.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    protected List<PhotoMedia> getOriginalPhotos() {
        return this.photos;
    }

    protected List<VideoMedia> getOriginalVideos() {
        return this.videos;
    }

    protected List<AudioMedia> getOriginalAudios() {
        return this.audios;
    }

    protected List<DocumentMedia> getOriginalDocuments() {
        return documents;
    }
}
