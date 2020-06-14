package com.github.jolice.bot.keyboard;

public interface KeyboardBuilder {

    KeyboardBuilder oneTime(boolean oneTime);

    KeyboardBuilder resize(boolean resize);

    KeyboardBuilder selective(boolean selective);

    KeyboardBuilder addButton(String text);

    KeyboardBuilder newLine();

    BotKeyboard build();


}
