package com.github.jolice.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.github.jolice.bot.KeyboardContext;
import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.keyboard.KeyboardBuilder;

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
