package me.nextgeneric.bot.vk.keyboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VKKeyboardAction {

    private String type;
    private String label;
}
