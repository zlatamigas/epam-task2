package epam.zlatamigas.xmltask.entity;

public enum PreciousType {
    DIAMOND("diamond"),
    SAPPHIRE("sapphire"),
    RUBY("ruby"),
    EMERALD("emerald");

    private final String value;

    PreciousType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PreciousType typeValueOf(String value){
        String result = value.toUpperCase();
        return PreciousType.valueOf(result);
    }
}
