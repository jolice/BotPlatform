package me.nextgeneric.bot.vk.attachment;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import me.nextgeneric.bot.core.attachment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VKAttachmentFactory implements AttachmentFactory {

    private GroupActor groupActor;
    private VkApiClient apiClient;

    @Autowired
    public VKAttachmentFactory(GroupActor groupActor, VkApiClient apiClient) {
        this.groupActor = groupActor;
        this.apiClient = apiClient;
    }

    @Override
    public PhotoAttachment photo(AttachmentData attachmentData) {
        return new VKPhotoAttachment(attachmentData.getPath(), this.groupActor, this.apiClient, attachmentData.getChatId());
    }

    @Override
    public AudioAttachment audio(AttachmentData attachmentData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentAttachment document(AttachmentData attachmentData) {
        throw new UnsupportedOperationException();
    }


    @Override
    public VideoAttachment video(AttachmentData attachmentData) {
        throw new UnsupportedOperationException();
    }
}
