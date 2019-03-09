package me.nextgeneric.bot.telegram.media;

import com.pengrad.telegrambot.model.Video;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TelegramVideoTest {
    private static final int WIDTH = 1;

    private static final int HEIGHT = 1;

    private static final int DURATION = 1;

    private static final String PATH = "PATH";


    private TelegramVideo telegramVideo;

    public TelegramVideoTest() {
        Video video = mock(Video.class);
        when(video.width()).thenReturn(WIDTH);
        when(video.height()).thenReturn(HEIGHT);
        when(video.duration()).thenReturn(DURATION);
        when(video.fileId()).thenReturn(PATH);
        telegramVideo = new TelegramVideo(video);
    }

    @Test
    void getWidth() {
        when(telegramVideo.getWidth()).thenReturn(WIDTH);
    }

    @Test
    void getHeight() {
        when(telegramVideo.getHeight()).thenReturn(HEIGHT);
    }

    @Test
    void getDuration() {
        when(telegramVideo.getDuration()).thenReturn(DURATION);
    }

    @Test
    void getPath() {
        when(telegramVideo.getPath()).thenReturn(PATH);
    }
}