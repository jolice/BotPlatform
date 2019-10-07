package io.riguron.bot.vk;

import io.riguron.bot.api.KeyboardContext;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.keyboard.KeyboardBuilder;
import io.riguron.bot.vk.keyboard.VKKeyboard;
import io.riguron.bot.vk.keyboard.VKKeyboardBuilder;
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
