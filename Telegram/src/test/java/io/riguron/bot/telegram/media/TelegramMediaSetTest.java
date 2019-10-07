package io.riguron.bot.telegram.media;

import com.pengrad.telegrambot.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelegramMediaSetTest {

    @Test
    void fillUp() {
        Message message = mock(Message.class);

        // Set photos
        PhotoSize photo = mock(PhotoSize.class);
        PhotoSize[] sizes = new PhotoSize[]{photo};
        when(message.photo()).thenReturn(sizes);
        TelegramPhoto telegramPhoto = new TelegramPhoto(photo);

        // Set videos
        Video video = mock(Video.class);
        TelegramVideo telegramVideo = new TelegramVideo(video);
        when(message.video()).thenReturn(video);

        // Set audios
        Audio audio = mock(Audio.class);
        TelegramAudio telegramAudio = new TelegramAudio(audio);
        when(message.audio()).thenReturn(audio);

        // Set documents
        Document document = mock(Document.class);
        TelegramDocument telegramDocument = new TelegramDocument(document);
        when(message.document()).thenReturn(document);

        TelegramMediaSet telegramMediaSet = new TelegramMediaSet(message);

        // Assert photos
        assertEquals(telegramMediaSet.getPhotos().size(), 1);
        assertEquals(telegramMediaSet.getPhotos().get(0), telegramPhoto);

        // Assert videos

        assertEquals(telegramMediaSet.getVideos().size(), 1);
        assertEquals(telegramMediaSet.getVideos().get(0), telegramVideo);

        // Assert audios

        assertEquals(telegramMediaSet.getAudios().size(), 1);
        assertEquals(telegramMediaSet.getAudios().get(0), telegramAudio);

        // Assert documents

        assertEquals(telegramMediaSet.getDocuments().size(), 1);
        assertEquals(telegramMediaSet.getDocuments().get(0), telegramDocument);

    }

}