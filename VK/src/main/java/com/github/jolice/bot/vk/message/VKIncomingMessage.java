package com.github.jolice.bot.vk.message;

import com.github.jolice.bot.vk.media.VKMediaSet;
import com.vk.api.sdk.objects.messages.Message;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.media.MediaSet;
import com.github.jolice.bot.message.IncomingMessage;

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
