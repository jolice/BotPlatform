package io.riguron.bot.vk.keyboard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VKKeyboardButton {

    private String color;
    private VKKeyboardAction action;


}
