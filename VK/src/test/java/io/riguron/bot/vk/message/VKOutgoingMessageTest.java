package io.riguron.bot.vk.message;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import io.riguron.bot.vk.attachment.VKAttachment;
import io.riguron.bot.vk.keyboard.VKKeyboard;
import io.riguron.bot.vk.keyboard.VKKeyboardButton;
import io.riguron.bot.vk.keyboard.VKKeyboardSerializer;
import io.riguron.bot.api.Sticker;
import io.riguron.bot.vk.attachment.VKAttachment;
import io.riguron.bot.vk.keyboard.VKKeyboard;
import io.riguron.bot.vk.keyboard.VKKeyboardButton;
import io.riguron.bot.vk.keyboard.VKKeyboardSerializer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class VKOutgoingMessageTest {

    @Test
    void text() {
        MessagesSendQuery messagesSendQuery = spy(new MessagesSendQuery(mock(VkApiClient.class), mock(GroupActor.class)));
        VKOutgoingMessage vkOutgoingMessage = new VKOutgoingMessage(messagesSendQuery);
        vkOutgoingMessage.text("TEXT");
        verify(messagesSendQuery).message("TEXT");
    }

    @Test
    void sticker() {
        MessagesSendQuery messagesSendQuery = spy(new MessagesSendQuery(mock(VkApiClient.class), mock(GroupActor.class)));
        VKOutgoingMessage vkOutgoingMessage = new VKOutgoingMessage(messagesSendQuery);
        Sticker sticker = mock(Sticker.class);
        when(sticker.getId()).thenReturn(1);
        vkOutgoingMessage.sticker(sticker);
        verify(messagesSendQuery).stickerId(sticker.getId());
    }

    @Test
    void attachment() throws ClientException, ApiException {
        MessagesSendQuery messagesSendQuery = spy(new MessagesSendQuery(mock(VkApiClient.class), mock(GroupActor.class)));
        VKOutgoingMessage vkOutgoingMessage = new VKOutgoingMessage(messagesSendQuery);

        VKAttachment vkPhotoAttachment = spy(new VKAttachment() {
            @Override
            public String attachmentString() {
                return "MOCK";
            }
        });
        vkOutgoingMessage.attachment(vkPhotoAttachment);
        verify(vkPhotoAttachment).setQuery(messagesSendQuery);

        doReturn(1).when(messagesSendQuery).execute();

        vkOutgoingMessage.send();
        verify(vkPhotoAttachment).attachmentString();
    }

    @Test
    void keyboard() {
        MessagesSendQuery messagesSendQuery = spy(new MessagesSendQuery(mock(VkApiClient.class), mock(GroupActor.class)));
        VKOutgoingMessage vkOutgoingMessage = new VKOutgoingMessage(messagesSendQuery);
        VKKeyboard vkKeyboard = new VKKeyboard(new VKKeyboardButton[][]{}, true);
        vkOutgoingMessage.keyboard(vkKeyboard);
        verify(messagesSendQuery).unsafeParam("keyboard", VKKeyboardSerializer.INSTANCE.serialize(vkKeyboard));
    }

}