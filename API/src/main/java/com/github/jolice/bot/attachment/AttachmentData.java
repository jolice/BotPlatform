package com.github.jolice.bot.attachment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.nio.file.Path;

@Data
@Setter(AccessLevel.NONE)
@AllArgsConstructor
public class AttachmentData {

    private Path path;
    private long chatId;

}
