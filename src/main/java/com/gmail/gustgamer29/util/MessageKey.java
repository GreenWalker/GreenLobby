package com.gmail.gustgamer29.util;

public enum MessageKey {

    NOT_HAVE_SPACE_IN_INVENTORY("not_have_space_in_inventory"),
    BOW_NAME("bow_name"),
    ARROW_NAME("arrow_name"),
    BOW_LORE("bow_lore"),
    ARROW_LORE("arrow_lore"),
    FIRST_SLOT_IS_BUSY("first_slot_is_busy"),
    NETWORK_THRESHOLD("");

    private String message;

    MessageKey(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
