package me.nextgeneric.bot.vk;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import me.nextgeneric.bot.core.BotLauncher;
import me.nextgeneric.bot.core.MessageHandler;
import me.nextgeneric.bot.vk.message.VKIncomingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VKBotLauncher extends CallbackApiLongPoll implements BotLauncher {

    private MessageHandler messageHandler;

    @Autowired
    public VKBotLauncher(VkApiClient client, GroupActor actor, MessageHandler messageHandler) {
        super(client, actor);
        this.messageHandler = messageHandler;
    }

    @Override
    public void launch() {
        try {
            this.run();
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageNew(Integer groupId, Message message) {
        messageHandler.messageReceived(new VKIncomingMessage(message));
    }
}
