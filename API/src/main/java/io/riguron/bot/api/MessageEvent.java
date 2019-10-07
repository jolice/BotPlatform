package io.riguron.bot.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import io.riguron.bot.api.message.IncomingMessage;
import lombok.experimental.Delegate;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class MessageEvent {

    @Delegate
    private IncomingMessage incomingMessage;

    @Delegate
    private MessageContext messageContext;

    @Delegate
    private Bot bot;


}
