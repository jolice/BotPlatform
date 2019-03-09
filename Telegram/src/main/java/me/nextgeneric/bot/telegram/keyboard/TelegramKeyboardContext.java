package me.nextgeneric.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import me.nextgeneric.bot.core.KeyboardContext;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import me.nextgeneric.bot.core.keyboard.KeyboardBuilder;
import org.springframework.stereotype.Component;

@Component
public class TelegramKeyboardContext implements KeyboardContext {

    @Override
    public KeyboardBuilder builder() {
        return new TelegramKeyboardBuilder();
    }

    @Override
    public BotKeyboard remove() {
        return new TelegramKeyboard(new ReplyKeyboardRemove());
    }
}
