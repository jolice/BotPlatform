package io.riguron.bot.api;

import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.keyboard.KeyboardBuilder;

public interface KeyboardContext {

    KeyboardBuilder builder();

    BotKeyboard remove();

}
