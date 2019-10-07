package io.riguron.bot.config;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import io.riguron.bot.api.annotation.VK;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@VK
@ComponentScan(basePackages = "io.riguron.bot.vk")
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

}
