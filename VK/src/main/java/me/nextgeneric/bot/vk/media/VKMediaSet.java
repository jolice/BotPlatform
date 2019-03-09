package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.messages.MessageAttachment;
import me.nextgeneric.bot.core.media.AbstractMediaSet;

import java.util.ArrayList;
import java.util.List;


public class VKMediaSet extends AbstractMediaSet {

    public VKMediaSet(List<MessageAttachment> messageAttachmentList) {
        for (MessageAttachment messageAttachment : messageAttachmentList) {
            super.lazyAdd(this::getOriginalPhotos, () -> setPhotos(new ArrayList<>()), messageAttachment.getPhoto(), VKPhoto::new);
            super.lazyAdd(this::getOriginalVideos, () -> setVideos(new ArrayList<>()), messageAttachment.getVideo(), VKVideo::new);
            super.lazyAdd(this::getOriginalAudios, () -> setAudios(new ArrayList<>()), messageAttachment.getAudio(), VKAudio::new);
            super.lazyAdd(this::getOriginalDocuments, () -> setDocuments(new ArrayList<>()), messageAttachment.getDoc(), VKDocument::new);
        }
    }
}
