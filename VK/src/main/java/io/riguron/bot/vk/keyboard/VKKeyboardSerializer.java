package io.riguron.bot.vk.keyboard;

import com.google.gson.Gson;
import io.riguron.bot.api.keyboard.BotKeyboard;

public enum VKKeyboardSerializer {

    INSTANCE;

    private static final Gson SHARED_GSON = new Gson();

    public String serialize(BotKeyboard botKeyboard) {
        return SHARED_GSON.toJson(botKeyboard);
    }

}
