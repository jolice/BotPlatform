package me.nextgeneric.bot.telegram.media;

import com.pengrad.telegrambot.model.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TelegramDocumentTest {

    private static final Integer FILE_SIZE = Integer.MAX_VALUE;
    private static final String FILE_NAME = "FILE_NAME";

    private TelegramDocument telegramDocument;

    public TelegramDocumentTest() {
        Document document = mock(Document.class);
        when(document.fileSize()).thenReturn(FILE_SIZE);
        when(document.fileName()).thenReturn(FILE_NAME);
        this.telegramDocument = new TelegramDocument(document);
    }

    @Test
    void getSize() {
        assertEquals(telegramDocument.getSize(), FILE_SIZE);
    }

    @Test
    void getPath() {
        assertEquals(telegramDocument.getPath(), FILE_NAME);
    }


}