package me.nextgeneric.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.Keyboard;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;

@EqualsAndHashCode
@ToString
public class TelegramKeyboard implements BotKeyboard {

    private Keyboard keyboard;

    public TelegramKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Keyboard getKeyboard(){
        return keyboard;
    }

}
