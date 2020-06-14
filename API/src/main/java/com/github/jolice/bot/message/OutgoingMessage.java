package com.github.jolice.bot.message;

import com.github.jolice.bot.attachment.MessageAttachment;
import com.github.jolice.bot.Sticker;
import com.github.jolice.bot.keyboard.BotKeyboard;

public interface OutgoingMessage {

    OutgoingMessage text(String text);

    OutgoingMessage sticker(Sticker sticker);

    OutgoingMessage attachment(MessageAttachment messageAttachment);

    OutgoingMessage keyboard(BotKeyboard keyboard);

    void send();

}
