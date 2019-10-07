package io.riguron.bot.vk.message;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import io.riguron.bot.api.message.MessageFactory;
import io.riguron.bot.api.message.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class VKMessageFactory implements MessageFactory {

    private GroupActor groupActor;
    private VkApiClient vkApiClient;

    public VKMessageFactory(GroupActor groupActor, VkApiClient vkApiClient) {
        this.groupActor = groupActor;
        this.vkApiClient = vkApiClient;
    }


    @Override
    public OutgoingMessage newOutgoingMessage(long chatId) {
        return new VKOutgoingMessage(vkApiClient.messages().send(groupActor).chatId((int) chatId));
    }
}
