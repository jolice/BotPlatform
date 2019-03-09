package me.nextgeneric.bot.vk;

import me.nextgeneric.bot.core.type.BotType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VKBotContextTest {

    @Test
    void getType() {
        assertEquals(new VKBotContext().getType(), BotType.VK);
    }
}