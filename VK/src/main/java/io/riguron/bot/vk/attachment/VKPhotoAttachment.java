package io.riguron.bot.vk.attachment;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.photos.responses.MessageUploadResponse;
import com.vk.api.sdk.queries.upload.UploadPhotoMessageQuery;
import io.riguron.bot.api.attachment.PhotoAttachment;

import java.nio.file.Path;
import java.util.List;

public class VKPhotoAttachment extends VKAttachment implements PhotoAttachment {

    private GroupActor groupActor;
    private VkApiClient apiClient;
    private long chatId;
    private Path path;

    public VKPhotoAttachment(Path path, GroupActor groupActor, VkApiClient apiClient, long chatId) {
        this.path = path;
        this.groupActor = groupActor;
        this.apiClient = apiClient;
        this.chatId = chatId;
    }

    @Override
    public String attachmentString() {
        try {
            String uploadUrl = apiClient.photos().getMessagesUploadServer(groupActor).peerId((int) chatId).execute().getUploadUrl();
            UploadPhotoMessageQuery uploadPhotoMessageQuery = new UploadPhotoMessageQuery(apiClient, uploadUrl, path.toFile());
            MessageUploadResponse uploadResponse = uploadPhotoMessageQuery.execute();
            List<Photo> photos = apiClient.photos().saveMessagesPhoto(groupActor, uploadResponse.getPhoto()).hash(uploadResponse.getHash()).server(uploadResponse.getServer()).execute();
            Photo photo = photos.get(0);
            return photo.getOwnerId() + "_" + photo.getOwnerId();
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
    }


}
