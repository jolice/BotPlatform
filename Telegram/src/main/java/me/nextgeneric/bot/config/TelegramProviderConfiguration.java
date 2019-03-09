package me.nextgeneric.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import me.nextgeneric.bot.core.annotation.Telegram;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Telegram
@ComponentScan(basePackages = "me.nextgeneric.bot.telegram")
@PropertySource("file:telegram.properties")
public class TelegramProviderConfiguration {

    @Bean
    public TelegramBot telegramBot(@Value("${bot.token}") String botToken) {
        return new TelegramBot(botToken);
    }

}
