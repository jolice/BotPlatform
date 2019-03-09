package me.nextgeneric.bot.core;

import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import me.nextgeneric.bot.core.keyboard.KeyboardBuilder;

public interface KeyboardContext {

    KeyboardBuilder builder();

    BotKeyboard remove();

}
