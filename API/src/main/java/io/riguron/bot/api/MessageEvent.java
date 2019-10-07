package io.riguron.bot.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import io.riguron.bot.api.message.IncomingMessage;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class MessageEvent {

    private IncomingMessage incomingMessage;
    private MessageContext messageContext;
    private Bot bot;

}
