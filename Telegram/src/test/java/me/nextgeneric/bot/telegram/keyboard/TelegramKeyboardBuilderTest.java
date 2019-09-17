package me.nextgeneric.bot.telegram.keyboard;

import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelegramKeyboardBuilderTest {

    @Test
    void whenBuildKeyboardAndCompareToNativeThenSame() {

        String[][] matrix = new String[][]{
                new String[]{"1", "2", "3"},
                new String[]{"4", "5", "6"},
                new String[]{"7", "8", "9"}
        };

        ReplyKeyboardMarkup expectedKeyboard = new ReplyKeyboardMarkup(matrix);
        expectedKeyboard.selective(true).resizeKeyboard(true).oneTimeKeyboard(true);
        TelegramKeyboardBuilder telegramKeyboardBuilder = new TelegramKeyboardBuilder();

        telegramKeyboardBuilder.oneTime(true).resize(true).selective(true).
                addButton("1").addButton("2").addButton("3")
                .newLine()
                .addButton("4").addButton("5").addButton("6")
                .newLine()
                .addButton("7").addButton("8").addButton("9").
                build();

        BotKeyboard botKeyboard = telegramKeyboardBuilder.build();
        assertEquals(botKeyboard.getClass(), TelegramKeyboard.class);
        TelegramKeyboard telegramKeyboard = (TelegramKeyboard) botKeyboard;

        // comparing json representations
        assertEquals(telegramKeyboard.getKeyboard().toString(), expectedKeyboard.toString());
    }

    @Test
    void whenEmptyKeyboardThenExceptionFired() {
        assertThrows(IllegalStateException.class, () -> new TelegramKeyboardBuilder().build());
    }

}