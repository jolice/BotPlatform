package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.docs.Doc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VKDocumentTest {

    private VKDocument vkDocument;

    VKDocumentTest() {
        Doc doc = mock(Doc.class);
        when(doc.getSize()).thenReturn(100);
        when(doc.getUrl()).thenReturn("URL");
        this.vkDocument = new VKDocument(doc);
    }

    @Test
    void getSize() {
        assertEquals(vkDocument.getSize(), 100);
    }

    @Test
    void getPath() {
        assertEquals(vkDocument.getPath(), "URL");
    }
}