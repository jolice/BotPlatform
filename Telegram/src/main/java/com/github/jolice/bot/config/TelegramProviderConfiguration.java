package com.github.jolice.bot.config;

import com.github.jolice.bot.*;
import com.pengrad.telegrambot.TelegramBot;
import com.github.jolice.bot.annotation.Telegram;
import com.github.jolice.bot.attachment.AttachmentFactory;
import com.github.jolice.bot.message.MessageFactory;
import com.github.jolice.bot.telegram.TelegramBotContext;
import com.github.jolice.bot.telegram.TelegramBotLauncher;
import com.github.jolice.bot.telegram.TelegramMessageContext;
import com.github.jolice.bot.telegram.attachment.TelegramAttachmentFactory;
import com.github.jolice.bot.telegram.keyboard.TelegramKeyboardContext;
import com.github.jolice.bot.telegram.message.TelegramMessageFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Telegram
@PropertySource("file:telegram.properties")
public class TelegramProviderConfiguration {

    @Bean
    public TelegramBot telegramBot(@Value("${bot.token}") String botToken) {
        return new TelegramBot(botToken);
    }

    @Bean
    public Bot bot() {
        return new TelegramBotContext();
    }

    @Bean
    public BotLauncher telegramBotLauncher(TelegramBot telegramBot, MessageHandler messageHandler) {
        return new TelegramBotLauncher(telegramBot, messageHandler);
    }

    @Bean
    public MessageContext messageContext(AttachmentFactory attachmentFactory, MessageFactory messageFactory, KeyboardContext keyboardContext) {
        return new TelegramMessageContext(attachmentFactory, messageFactory, keyboardContext);
    }

    @Bean
    public AttachmentFactory attachmentFactory() {
        return new TelegramAttachmentFactory();
    }

    @Bean
    public MessageFactory messageFactory(TelegramBot telegramBot) {
        return new TelegramMessageFactory(telegramBot);
    }

    @Bean
    public KeyboardContext telegramKeyboardContext() {
        return new TelegramKeyboardContext();
    }
}
