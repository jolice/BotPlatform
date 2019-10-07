package io.riguron.bot.telegram.media;

import com.pengrad.telegrambot.model.PhotoSize;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.PhotoMedia;

@EqualsAndHashCode
@ToString
public class TelegramPhoto implements PhotoMedia {

    private PhotoSize photo;

    public TelegramPhoto(PhotoSize photo) {
        this.photo = photo;
    }

    @Override
    public int getWidth() {
        return photo.width();
    }

    @Override
    public int getHeight() {
        return photo.height();
    }

    @Override
    public String getPath() {
        return photo.fileId();
    }


}
