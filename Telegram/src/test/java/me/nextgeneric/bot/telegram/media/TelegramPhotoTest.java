package me.nextgeneric.bot.telegram.media;

import com.pengrad.telegrambot.model.PhotoSize;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TelegramPhotoTest {

    private static final int WIDTH = 1;

    private static final int HEIGHT = 1;

    private static final String PATH = "PATH";

    private TelegramPhoto telegramPhoto;

    public TelegramPhotoTest() {
        PhotoSize photo = mock(PhotoSize.class);
        when(photo.width()).thenReturn(WIDTH);
        when(photo.height()).thenReturn(HEIGHT);
        when(photo.fileId()).thenReturn(PATH);
        telegramPhoto = new TelegramPhoto(photo);
    }

    @Test
    void getWidth() {
        when(telegramPhoto.getWidth()).thenReturn(WIDTH);
    }

    @Test
    void getHeight() {
        when(telegramPhoto.getHeight()).thenReturn(HEIGHT);
    }

    @Test
    void getPath() {
        when(telegramPhoto.getPath()).thenReturn(PATH);
    }
}