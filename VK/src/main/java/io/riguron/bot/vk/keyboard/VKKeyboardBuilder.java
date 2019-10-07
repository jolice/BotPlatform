package io.riguron.bot.vk.keyboard;

import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.keyboard.KeyboardBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VKKeyboardBuilder implements KeyboardBuilder {

    private static final String BUTTON_COLOR = "default";

    private static final String DEFAULT_TYPE = "text";

    private List<List<VKKeyboardButton>> buttons = Collections.emptyList();
    private VKKeyboard vkKeyboard = new VKKeyboard();

    @Override
    public KeyboardBuilder oneTime(boolean oneTime) {
        vkKeyboard.setOneTime(oneTime);
        return this;
    }

    @Override
    public KeyboardBuilder addButton(String text) {
        if (buttons.isEmpty()) {
            buttons = new ArrayList<>();
            this.newLine();
        }
        buttons.get(buttons.size() - 1).add(new VKKeyboardButton(BUTTON_COLOR, new VKKeyboardAction(DEFAULT_TYPE, text)));
        return this;
    }

    @Override
    public KeyboardBuilder newLine() {
        buttons.add(new ArrayList<>());
        return this;
    }

    @Override
    public BotKeyboard build() {
        VKKeyboardButton[][] matrix = new VKKeyboardButton[buttons.size()][];
        for (int i = 0; i < this.buttons.size(); i++) {
            matrix[i] = this.buttons.get(i).toArray(new VKKeyboardButton[0]);
        }
        this.vkKeyboard.setButtons(matrix);

        return vkKeyboard;
    }

    @Override
    public KeyboardBuilder resize(boolean resize) {
        return this;
    }

    @Override
    public KeyboardBuilder selective(boolean selective) {
        return this;
    }


}
