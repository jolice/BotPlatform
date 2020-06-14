package com.github.jolice.bot.vk.media;

import com.vk.api.sdk.objects.docs.Doc;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.media.DocumentMedia;

@EqualsAndHashCode
@ToString
public class VKDocument implements DocumentMedia {

    private Doc doc;

    public VKDocument(Doc doc) {
        this.doc = doc;
    }

    @Override
    public int getSize() {
        return doc.getSize();
    }

    @Override
    public String getPath() {
        return doc.getUrl();
    }
}
