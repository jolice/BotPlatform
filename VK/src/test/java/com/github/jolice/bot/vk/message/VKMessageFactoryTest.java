package com.github.jolice.bot.vk.message;

import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VKMessageFactoryTest {

    @Test
    void newOutgoingMessage() {

        VkApiClient vkApiClient = mock(VkApiClient.class);
        when(vkApiClient.messages()).thenAnswer((invocationOnMock -> {
            Messages messages = mock(Messages.class);
            when(messages.send(any(GroupActor.class))).thenReturn(mock(MessagesSendQuery.class));
            return messages;
        }));
        assertEquals(new VKMessageFactory(mock(GroupActor.class),vkApiClient).newOutgoingMessage(1L).getClass(), VKOutgoingMessage.class);
    }
}