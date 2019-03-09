package me.nextgeneric.bot.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import me.nextgeneric.bot.core.message.IncomingMessage;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class MessageEvent {

    private IncomingMessage incomingMessage;
    private MessageContext messageContext;
    private Bot bot;

}
