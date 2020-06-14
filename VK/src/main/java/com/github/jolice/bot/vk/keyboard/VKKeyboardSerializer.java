package com.github.jolice.bot.vk.keyboard;

import com.google.gson.Gson;
import com.github.jolice.bot.keyboard.BotKeyboard;

public enum VKKeyboardSerializer {

    INSTANCE;

    private static final Gson SHARED_GSON = new Gson();

    public String serialize(BotKeyboard botKeyboard) {
        return SHARED_GSON.toJson(botKeyboard);
    }

}
