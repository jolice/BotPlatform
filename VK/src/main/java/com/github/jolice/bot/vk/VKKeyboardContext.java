package com.github.jolice.bot.vk;

import com.github.jolice.bot.KeyboardContext;
import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.keyboard.KeyboardBuilder;
import com.github.jolice.bot.vk.keyboard.VKKeyboard;
import com.github.jolice.bot.vk.keyboard.VKKeyboardBuilder;


public class VKKeyboardContext implements KeyboardContext {

    @Override
    public KeyboardBuilder builder() {
        return new VKKeyboardBuilder();
    }

    @Override
    public BotKeyboard remove() {
        return VKKeyboard.EMPTY;
    }
}
