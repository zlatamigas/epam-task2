package epam.zlatamigas.xmltask.creator;

import epam.zlatamigas.xmltask.entity.GemOrigin;

public enum GemType {
    PRECIOUS("precious"),
    SEMI_PRECIOUS("semi-precious");

    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    private final String value;

    GemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GemType typeValueOf(String value){
        String result = value.toUpperCase();
        result = result.replace(DASH, UNDERSCORE);
        return GemType.valueOf(result);
    }
}
