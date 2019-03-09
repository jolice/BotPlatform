package me.nextgeneric.bot.vk.message;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.Sticker;
import me.nextgeneric.bot.core.attachment.MessageAttachment;
import me.nextgeneric.bot.vk.attachment.VKAttachment;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import me.nextgeneric.bot.vk.keyboard.VKKeyboardSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@EqualsAndHashCode
@ToString
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
            e.printStackTrace();
        }
    }
}
