package me.nextgeneric.bot.vk.message;

import com.vk.api.sdk.objects.messages.Message;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.media.MediaSet;
import me.nextgeneric.bot.core.message.IncomingMessage;
import me.nextgeneric.bot.vk.media.VKMediaSet;

@EqualsAndHashCode
@ToString
public class VKIncomingMessage implements IncomingMessage {

    private Message message;

    public VKIncomingMessage(Message message) {
        this.message = message;
    }

    @Override
    public int getId() {
        return message.getId();
    }

    @Override
    public long getDate() {
        return Long.valueOf(message.getDate());
    }

    @Override
    public String getTitle() {
        return message.getTitle();
    }

    @Override
    public String getText() {
        return message.getBody();
    }

    @Override
    public MediaSet getMedia() {
        return new VKMediaSet(message.getAttachments());
    }

    @Override
    public long getChatId() {
        return message.getChatId();
    }
}
