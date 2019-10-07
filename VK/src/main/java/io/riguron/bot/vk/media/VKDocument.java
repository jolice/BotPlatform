package io.riguron.bot.vk.media;

import com.vk.api.sdk.objects.docs.Doc;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.DocumentMedia;

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
