package me.nextgeneric.bot.vk.message;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class VKMessageFactoryTest {

    @Test
    void newOutgoingMessage() {
        assertEquals(new VKMessageFactory(mock(GroupActor.class), mock(VkApiClient.class)).newOutgoingMessage(1L).getClass(), VKOutgoingMessage.class);
    }
}