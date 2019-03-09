package me.nextgeneric.bot.core.message;

import me.nextgeneric.bot.core.Sticker;
import me.nextgeneric.bot.core.attachment.MessageAttachment;
import me.nextgeneric.bot.core.keyboard.BotKeyboard;

public interface OutgoingMessage {

    OutgoingMessage text(String text);

    OutgoingMessage sticker(Sticker sticker);

    OutgoingMessage attachment(MessageAttachment messageAttachment);

    OutgoingMessage keyboard(BotKeyboard keyboard);

    void send();

}
