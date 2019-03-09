package me.nextgeneric.bot.telegram.media;

import com.pengrad.telegrambot.model.Document;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.media.DocumentMedia;

@EqualsAndHashCode
@ToString
public class TelegramDocument implements DocumentMedia {

    private Document file;

    public TelegramDocument(Document file) {
        this.file = file;
    }

    @Override
    public int getSize() {
        return file.fileSize();
    }

    @Override
    public String getPath() {
        return file.fileName();
    }
}
