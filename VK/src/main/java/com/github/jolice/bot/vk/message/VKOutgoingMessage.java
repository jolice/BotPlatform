package com.github.jolice.bot.vk.message;

import com.github.jolice.bot.vk.attachment.VKAttachment;
import com.github.jolice.bot.vk.keyboard.VKKeyboardSerializer;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.Sticker;
import com.github.jolice.bot.attachment.MessageAttachment;
import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.message.OutgoingMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@EqualsAndHashCode
@ToString
@Slf4j
public class VKOutgoingMessage implements OutgoingMessage {

    private MessagesSendQuery messagesSendQuery;
    private List<Supplier<String>> delayedAttachments = Collections.emptyList();

    VKOutgoingMessage(MessagesSendQuery messagesSendQuery) {
        this.messagesSendQuery = messagesSendQuery;
    }

    @Override
    public OutgoingMessage text(String text) {
        messagesSendQuery.message(text);
        return this;
    }

    @Override
    public OutgoingMessage sticker(Sticker sticker) {
        messagesSendQuery.stickerId(sticker.getId());
        return this;
    }

    @Override
    public OutgoingMessage attachment(MessageAttachment messageAttachment) {
        VKAttachment vkAttachment = (VKAttachment) messageAttachment;
        vkAttachment.setQuery(this.messagesSendQuery);
        if (this.delayedAttachments.isEmpty()) {
            this.delayedAttachments = new ArrayList<>();
        }
        this.delayedAttachments.add(vkAttachment::attachmentString);
        return this;
    }

    @Override
    public OutgoingMessage keyboard(BotKeyboard keyboard) {
        this.messagesSendQuery.unsafeParam("keyboard", VKKeyboardSerializer.INSTANCE.serialize(keyboard));
        return this;
    }

    @Override
    public void send() {
        this.delayedAttachments.forEach(stringSupplier -> this.messagesSendQuery.attachment(stringSupplier.get()));
        try {
            this.messagesSendQuery.execute();
        } catch (ApiException | ClientException e) {
            log.error("Failed to send a message", e);
        }
    }
}
