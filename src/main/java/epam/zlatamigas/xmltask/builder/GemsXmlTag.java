package epam.zlatamigas.xmltask.builder;

public enum GemsXmlTag {

    GEMS("gems"),

    PRECIOUS("precious"),
    SEMI_PRECIOUS("semi-precious"),

    ID("id"),
    NAME("name"),

    ORIGIN("origin"),
    EXTRACTION_TIME("extraction-time"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    FACES("faces"),
    MASS("mass"),
    TYPE("type"),
    IMPLEMENTATION("implementation");

    private static final String UNDERSCORE = "_";
    private static final String DASH = "-";

    private final String value;

    GemsXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GemsXmlTag typeValueOf(String value) {
        String result = value.toUpperCase();
        result = result.replace(DASH, UNDERSCORE);
        return GemsXmlTag.valueOf(result);
    }
}
