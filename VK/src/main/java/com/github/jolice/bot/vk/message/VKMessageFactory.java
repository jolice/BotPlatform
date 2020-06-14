package com.github.jolice.bot.vk.message;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.github.jolice.bot.message.MessageFactory;
import com.github.jolice.bot.message.OutgoingMessage;

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
