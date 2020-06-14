package com.github.jolice.bot;

import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.keyboard.KeyboardBuilder;

public interface KeyboardContext {

    KeyboardBuilder builder();

    BotKeyboard remove();

}
