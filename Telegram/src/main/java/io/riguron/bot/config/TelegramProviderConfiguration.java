package io.riguron.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import io.riguron.bot.api.*;
import io.riguron.bot.api.annotation.Telegram;
import io.riguron.bot.api.attachment.AttachmentFactory;
import io.riguron.bot.api.message.MessageFactory;
import io.riguron.bot.telegram.TelegramBotContext;
import io.riguron.bot.telegram.TelegramBotLauncher;
import io.riguron.bot.telegram.TelegramMessageContext;
import io.riguron.bot.telegram.attachment.TelegramAttachmentFactory;
import io.riguron.bot.telegram.keyboard.TelegramKeyboardContext;
import io.riguron.bot.telegram.message.TelegramMessageFactory;
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
