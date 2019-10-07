package io.riguron.bot.vk.keyboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.riguron.bot.api.keyboard.BotKeyboard;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VKKeyboard implements BotKeyboard {

    public static final VKKeyboard EMPTY = new VKKeyboard();

    private VKKeyboardButton[][] buttons;
    private boolean oneTime;


}
