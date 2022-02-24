package epam.zlatamigas.xmltask.entity;

public enum GemOrigin {
    SOUTH_AMERICA("South America"),
    NORTH_AMERICA("North America"),
    EUROPE("Europe"),
    ASIA("Asia"),
    AUSTRALIA("Australia"),
    OCEANIA("Oceania");

    private static final String UNDERSCORE = "_";
    private static final String WHITE_SPACE = " ";

    private final String value;

    GemOrigin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GemOrigin typeValueOf(String value){
        String result = value.toUpperCase();
        result = result.replace(WHITE_SPACE, UNDERSCORE);
        return GemOrigin.valueOf(result);
    }
}
