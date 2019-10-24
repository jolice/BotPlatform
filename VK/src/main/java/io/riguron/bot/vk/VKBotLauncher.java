package io.riguron.bot.vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import io.riguron.bot.api.BotLauncher;
import io.riguron.bot.api.MessageHandler;
import io.riguron.bot.vk.message.VKIncomingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
public class VKBotLauncher extends CallbackApiLongPoll implements BotLauncher {

    private MessageHandler messageHandler;

    public VKBotLauncher(VkApiClient client, GroupActor actor, MessageHandler messageHandler) {
        super(client, actor);
        this.messageHandler = messageHandler;
    }

    @Override
    public void launch() {
        try {
            this.run();
        } catch (ClientException | ApiException e) {
            log.error("Can't start a bot", e);
        }
    }

    @Override
    public void messageNew(Integer groupId, Message message) {
        messageHandler.messageReceived(new VKIncomingMessage(message));
    }
}
