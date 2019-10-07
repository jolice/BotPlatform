package io.riguron.bot.telegram.attachment.support;

import com.pengrad.telegrambot.model.request.InputMedia;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMediaGroup;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.telegram.keyboard.TelegramKeyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@EqualsAndHashCode
@ToString
public class AttachmentConfigurer {

    private AbstractSendRequest<?> sendRequest;
    private List<InputMedia<?>> groupMedia = Collections.emptyList();
    private long chatId;

    public AttachmentConfigurer(long chatId) {
        this.chatId = chatId;
    }

    public void setOrAdd(InputMedia<? extends InputMedia> inputMedia, Function<Long, AbstractSendRequest<?>> supplier) {
        if (this.groupMedia.isEmpty()) {
            this.groupMedia = new ArrayList<>();
            set(supplier.apply(this.chatId));
        } else {
            set(null);
        }
        this.groupMedia.add(inputMedia);
    }

    public void set(AbstractSendRequest<?> sendRequest) {
        this.sendRequest = sendRequest;
    }

    public BaseRequest<?, ?> getFinalQuery(BotKeyboard keyboard) {
        if (sendRequest != null) {
            if (keyboard != null) {
                TelegramKeyboard telegramKeyboard = (TelegramKeyboard) keyboard;
                sendRequest.replyMarkup(telegramKeyboard.getKeyboard());
            }
            return sendRequest;
        } else {
            return getMediaGroup();
        }
    }

    public AbstractSendRequest<?> getSendRequest() {
        return sendRequest;
    }

    public SendMediaGroup getMediaGroup() {
        if (this.groupMedia.isEmpty()) {
            throw new IllegalStateException("No group media present!");
        }

        return new SendMediaGroup(this.chatId, this.groupMedia.toArray(new InputMedia[this.groupMedia.size()]));
    }
}
