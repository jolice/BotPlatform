package io.riguron.bot.api.message;

import io.riguron.bot.api.Sticker;
import io.riguron.bot.api.attachment.MessageAttachment;
import io.riguron.bot.api.keyboard.BotKeyboard;
import io.riguron.bot.api.Sticker;
import io.riguron.bot.api.attachment.MessageAttachment;
import io.riguron.bot.api.keyboard.BotKeyboard;

public interface OutgoingMessage {

    OutgoingMessage text(String text);

    OutgoingMessage sticker(Sticker sticker);

    OutgoingMessage attachment(MessageAttachment messageAttachment);

    OutgoingMessage keyboard(BotKeyboard keyboard);

    void send();

}
