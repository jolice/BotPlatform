package com.github.jolice.bot.telegram.media;

import com.pengrad.telegrambot.model.PhotoSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelegramPhotoTest {

    private static final int WIDTH = 1;

    private static final int HEIGHT = 1;

    private static final String PATH = "PATH";

    private TelegramPhoto telegramPhoto;

    TelegramPhotoTest() {
        PhotoSize photo = mock(PhotoSize.class);
        when(photo.width()).thenReturn(WIDTH);
        when(photo.height()).thenReturn(HEIGHT);
        when(photo.fileId()).thenReturn(PATH);
        telegramPhoto = new TelegramPhoto(photo);
    }

    @Test
    void getWidth() {
       assertEquals(WIDTH, telegramPhoto.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(HEIGHT, telegramPhoto.getHeight());
    }

    @Test
    void getPath() {
        assertEquals(PATH, telegramPhoto.getPath());
    }
}