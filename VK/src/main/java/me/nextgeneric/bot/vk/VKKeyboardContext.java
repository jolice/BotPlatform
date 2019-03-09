package me.nextgeneric.bot.vk;

import me.nextgeneric.bot.core.KeyboardContext;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import me.nextgeneric.bot.core.keyboard.KeyboardBuilder;
import me.nextgeneric.bot.vk.keyboard.VKKeyboard;
import me.nextgeneric.bot.vk.keyboard.VKKeyboardBuilder;
import org.springframework.stereotype.Component;

@Component
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
