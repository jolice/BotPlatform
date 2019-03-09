package me.nextgeneric.bot.telegram.media;

import com.pengrad.telegrambot.model.Audio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TelegramAudioTest {

    private static final int DURATION = 1;

    private static final String TITLE = "TITLE";

    private static final String PATH = "PATH";

    private TelegramAudio telegramAudio;

    TelegramAudioTest() {
        Audio originalAudio = mock(Audio.class);
        when(originalAudio.duration()).thenReturn(DURATION);
        when(originalAudio.title()).thenReturn(TITLE);
        when(originalAudio.fileId()).thenReturn(PATH);
        telegramAudio = new TelegramAudio(originalAudio);
    }

    @Test
    void getDuration() {
        assertEquals(telegramAudio.getDuration(), DURATION);
    }

    @Test
    void getTitle() {
        assertEquals(telegramAudio.getTitle(), TITLE);
    }

    @Test
    void getPath() {
        assertEquals(telegramAudio.getPath(), PATH);
    }
}