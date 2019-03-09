package me.nextgeneric.bot.vk.keyboard;

import com.google.gson.Gson;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;

public enum VKKeyboardSerializer {

    INSTANCE;

    private static final Gson SHARED_GSON = new Gson();

    public String serialize(BotKeyboard botKeyboard) {
        return SHARED_GSON.toJson(botKeyboard);
    }

}
