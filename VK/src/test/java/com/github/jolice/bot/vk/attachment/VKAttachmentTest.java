package com.github.jolice.bot.vk.attachment;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class VKAttachmentTest {

    @Test
    void caption() {

        MessagesSendQuery messagesSendQuery = spy(new MessagesSendQuery(mock(VkApiClient.class), mock(GroupActor.class)));
        VKAttachment vkAttachment = new VKAttachment() {
            @Override
            public String attachmentString() {
              throw new UnsupportedOperationException();
            }
        };
        vkAttachment.setQuery(messagesSendQuery);
        vkAttachment.caption("caption");
        verify(messagesSendQuery).message("caption");
    }

    @Test
    void attachmentString() {
    }
}