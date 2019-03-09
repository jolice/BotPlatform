package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.video.Video;
import com.vk.api.sdk.objects.video.VideoFiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VKVideoTest {


    private VKVideo vkVideo;

    public VKVideoTest() {
        Video video = mock(Video.class);
        when(video.getDuration()).thenReturn(100);
        when(video.getFiles()).thenReturn(mock(VideoFiles.class));
        when(video.getFiles().getMp1080()).thenReturn("video.mp4");
        vkVideo = new VKVideo(video);
    }

    @Test
    void getWidth() {
        assertEquals(vkVideo.getWidth(), 0);
    }

    @Test
    void getHeight() {
        assertEquals(vkVideo.getHeight(), 0);
    }

    @Test
    void getDuration() {
        assertEquals(vkVideo.getDuration(), 100);
    }

    @Test
    void getPath() {
        assertEquals(vkVideo.getPath(), "video.mp4");
    }
}