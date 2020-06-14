package com.github.jolice.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.github.jolice.bot.keyboard.BotKeyboard;
import com.github.jolice.bot.keyboard.KeyboardBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
public class TelegramKeyboardBuilder implements KeyboardBuilder {

    private List<List<String>> lines = Collections.emptyList();
    private boolean oneTime;
    private boolean resize;
    private boolean selective;

    @Override
    public KeyboardBuilder oneTime(boolean oneTime) {
        this.oneTime = oneTime;
        return this;
    }

    @Override
    public KeyboardBuilder resize(boolean resize) {
        this.resize = resize;
        return this;
    }

    @Override
    public KeyboardBuilder selective(boolean selective) {
        this.selective = selective;
        return this;
    }

    @Override
    public KeyboardBuilder addButton(String text) {
        if (lines.isEmpty()) {
            this.lines = new ArrayList<>();
            this.newLine();
        }
        this.lines.get(lines.size() - 1).add(text);
        return this;
    }

    @Override
    public KeyboardBuilder newLine() {
        this.lines.add(new ArrayList<>());
        return this;
    }

    @Override
    public BotKeyboard build() {
        if (lines.isEmpty()) {
            throw new IllegalStateException("Keyboard is empty!");
        }
        String[][] resultingArray = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            resultingArray[i] = lines.get(i).toArray(new String[0]);
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(resultingArray);
        replyKeyboardMarkup.oneTimeKeyboard(this.oneTime);
        replyKeyboardMarkup.resizeKeyboard(this.resize);
        replyKeyboardMarkup.selective(this.selective);
        return new TelegramKeyboard(replyKeyboardMarkup);
    }

}
