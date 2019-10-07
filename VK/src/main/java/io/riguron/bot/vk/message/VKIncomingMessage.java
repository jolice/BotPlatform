package io.riguron.bot.vk.message;

import com.vk.api.sdk.objects.messages.Message;
import io.riguron.bot.vk.media.VKMediaSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.media.MediaSet;
import io.riguron.bot.api.message.IncomingMessage;
import io.riguron.bot.vk.media.VKMediaSet;

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
