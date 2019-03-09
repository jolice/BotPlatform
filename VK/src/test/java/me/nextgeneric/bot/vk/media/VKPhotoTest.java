package me.nextgeneric.bot.vk.media;

import com.vk.api.sdk.objects.photos.Photo;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VKPhotoTest {

    private VKPhoto photo;

    VKPhotoTest() {
        Photo nativePhoto = mock(Photo.class);
        when(nativePhoto.getWidth()).thenReturn(10);
        when(nativePhoto.getHeight()).thenReturn(10);
        when(nativePhoto.getPhoto2560()).thenReturn("photo2560");
        photo = new VKPhoto(nativePhoto);
    }


    @Test
    void getWidth() {
        when(photo.getWidth()).thenReturn(10);
    }

    @Test
    void getHeight() {
        when(photo.getHeight()).thenReturn(10);
    }

    @Test
    void getPath() {
        when(photo.getPath()).thenReturn("photo2560");
    }
}