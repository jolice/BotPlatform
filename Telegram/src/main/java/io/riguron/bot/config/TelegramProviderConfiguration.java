package io.riguron.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import io.riguron.bot.api.annotation.Telegram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Telegram
@ComponentScan(basePackages = "io.riguron.bot.telegram")
@PropertySource("file:telegram.properties")
public class TelegramProviderConfiguration {

    @Bean
    public TelegramBot telegramBot(@Value("${bot.token}") String botToken) {
        return new TelegramBot(botToken);
    }

}
