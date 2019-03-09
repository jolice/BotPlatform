package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.audio.AudioFull;
import com.vk.api.sdk.objects.docs.Doc;
import com.vk.api.sdk.objects.messages.MessageAttachment;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.video.Video;
import me.nextgeneric.bot.core.media.MediaSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VKMediaSetTest {

    @Test
    void doTest() {

        List<MessageAttachment> attachments = new ArrayList<>();

        MessageAttachment withPhoto = mock(MessageAttachment.class);
        Photo photo = mock(Photo.class);
        when(withPhoto.getPhoto()).thenReturn(photo);
        VKPhoto vkPhoto = new VKPhoto(photo);

        MessageAttachment withAudio = mock(MessageAttachment.class);
        AudioFull audio = mock(AudioFull.class);
        when(withAudio.getAudio()).thenReturn(audio);
        VKAudio vkAudio = new VKAudio(audio);

        MessageAttachment withVideo = mock(MessageAttachment.class);
        Video video = mock(Video.class);
        when(withVideo.getVideo()).thenReturn(video);
        VKVideo vkVideo = new VKVideo(video);

        MessageAttachment withDocument = mock(MessageAttachment.class);
        Doc doc = mock(Doc.class);
        when(withDocument.getDoc()).thenReturn(doc);
        VKDocument vkDocument = new VKDocument(doc);

        attachments.add(withPhoto);
        attachments.add(withAudio);
        attachments.add(withVideo);
        attachments.add(withDocument);


        MediaSet telegramMediaSet = new VKMediaSet(attachments);

        // Assert photos
        assertEquals(telegramMediaSet.getPhotos().size(), 1);
        assertEquals(telegramMediaSet.getPhotos().get(0), vkPhoto);

        // Assert videos

        assertEquals(telegramMediaSet.getVideos().size(), 1);
        assertEquals(telegramMediaSet.getVideos().get(0), vkVideo);

        // Assert audios

        assertEquals(telegramMediaSet.getAudios().size(), 1);
        assertEquals(telegramMediaSet.getAudios().get(0), vkAudio);

        // Assert documents

        assertEquals(telegramMediaSet.getDocuments().size(), 1);
        assertEquals(telegramMediaSet.getDocuments().get(0), vkDocument);
    }

}