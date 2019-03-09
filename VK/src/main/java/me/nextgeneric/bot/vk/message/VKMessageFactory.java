package me.nextgeneric.bot.vk.message;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import me.nextgeneric.bot.core.message.MessageFactory;
import me.nextgeneric.bot.core.message.OutgoingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VKMessageFactory implements MessageFactory {

    private GroupActor groupActor;
    private VkApiClient vkApiClient;

    @Autowired
    public VKMessageFactory(GroupActor groupActor, VkApiClient vkApiClient) {
        this.groupActor = groupActor;
        this.vkApiClient = vkApiClient;
    }


    @Override
    public OutgoingMessage newOutgoingMessage(long chatId) {
        return new VKOutgoingMessage(vkApiClient.messages().send(groupActor).chatId((int) chatId));
    }
}
