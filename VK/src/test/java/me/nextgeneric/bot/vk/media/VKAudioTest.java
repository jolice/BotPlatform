package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.audio.AudioFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VKAudioTest {

    private VKAudio vkAudio;

    VKAudioTest() {
        AudioFull audioFull = mock(AudioFull.class);
        when(audioFull.getDuration()).thenReturn(1);
        when(audioFull.getTitle()).thenReturn("title");
        when(audioFull.getUrl()).thenReturn("URL");
        this.vkAudio = new VKAudio(audioFull);
    }

    @Test
    void getDuration() {
        assertEquals(vkAudio.getDuration(), 1);
    }

    @Test
    void getTitle() {
        assertEquals(vkAudio.getTitle(), "title");
    }

    @Test
    void getPath() {
        assertEquals(vkAudio.getPath(), "URL");
    }
}