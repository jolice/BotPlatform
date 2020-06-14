package com.github.jolice.bot.config;

import com.github.jolice.bot.*;
import com.github.jolice.bot.vk.VKBotContext;
import com.github.jolice.bot.vk.VKBotLauncher;
import com.github.jolice.bot.vk.VKKeyboardContext;
import com.github.jolice.bot.vk.VKMessageContext;
import com.github.jolice.bot.vk.attachment.VKAttachmentFactory;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.github.jolice.bot.annotation.VK;
import com.github.jolice.bot.attachment.AttachmentFactory;
import com.github.jolice.bot.message.MessageFactory;
import com.github.jolice.bot.vk.message.VKMessageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@VK
@PropertySource("file:/vk.properties")
public class VKProviderConfiguration {

    @Bean
    public VkApiClient vkApiClient() {
        return new VkApiClient(HttpTransportClient.getInstance());
    }

    @Bean
    public GroupActor groupActor(@Value("${bot.group.id}") Integer groupId, @Value("${bot.group.token}") String botToken) {
        return new GroupActor(groupId, botToken);
    }

    @Bean
    public Bot bot() {
        return new VKBotContext();
    }

    @Bean
    public BotLauncher botLauncher(VkApiClient vkApiClient, GroupActor groupActor, MessageHandler messageHandler) {
        return new VKBotLauncher(vkApiClient, groupActor, messageHandler);
    }

    @Bean
    public KeyboardContext keyboardContext() {
        return new VKKeyboardContext();
    }

    @Bean
    public MessageContext messageContext(AttachmentFactory attachmentFactory, MessageFactory messageFactory, KeyboardContext keyboardContext) {
        return new VKMessageContext(attachmentFactory, messageFactory, keyboardContext);
    }

    @Bean
    public MessageFactory messageFactory(GroupActor groupActor, VkApiClient vkApiClient) {
        return new VKMessageFactory(groupActor, vkApiClient);
    }

    @Bean
    public AttachmentFactory attachmentFactory(GroupActor groupActor, VkApiClient apiClient) {
        return new VKAttachmentFactory(groupActor, apiClient);
    }


}
