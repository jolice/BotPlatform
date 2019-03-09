package me.nextgeneric.bot.vk.keyboard;

import me.nextgeneric.bot.core.keyboard.BotKeyboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VKKeyboardBuilderTest {

    @Test
    void oneTime() {

        VKKeyboard expectedKeyboard = new VKKeyboard(new VKKeyboardButton[][]{

                new VKKeyboardButton[]{
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "1")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "2")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "3"))
                },

                new VKKeyboardButton[]{
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "4")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "5")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "6"))
                },

                new VKKeyboardButton[]{
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "7")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "8")),
                        new VKKeyboardButton("default", new VKKeyboardAction("text", "9"))
                },

        }, true);


        VKKeyboardBuilder telegramKeyboardBuilder = new VKKeyboardBuilder();

        telegramKeyboardBuilder.oneTime(true).resize(true).selective(true).
                addButton("1").addButton("2").addButton("3")
                .newLine()
                .addButton("4").addButton("5").addButton("6")
                .newLine()
                .addButton("7").addButton("8").addButton("9").
                build();

        BotKeyboard botKeyboard = telegramKeyboardBuilder.build();
        assertEquals(botKeyboard.getClass(), VKKeyboard.class);
        assertEquals(botKeyboard, expectedKeyboard);

    }

    @Test
    void addButton() {
    }

    @Test
    void newLine() {
    }

    @Test
    void build() {
    }

    @Test
    void resize() {
    }

    @Test
    void selective() {
    }
}