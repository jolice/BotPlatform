package io.riguron.bot.api.type;

public enum BotType {

    TELEGRAM(BotTypeMeta.TELEGRAM), VK(BotTypeMeta.VK);

    private String description;

    BotType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
