package io.riguron.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import io.riguron.bot.api.KeyboardContext;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.keyboard.KeyboardBuilder;
import org.springframework.stereotype.Component;

public class TelegramKeyboardContext implements KeyboardContext {

    @Override
    public KeyboardBuilder builder() {
        return new TelegramKeyboardBuilder();
    }

    @Override
    public BotKeyboard remove() {
        return new TelegramKeyboard(new ReplyKeyboardRemove());
    }
}
