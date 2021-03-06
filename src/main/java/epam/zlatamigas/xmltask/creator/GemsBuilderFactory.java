package epam.zlatamigas.xmltask.creator;

import epam.zlatamigas.xmltask.builder.AbstractGemsBuilder;
import epam.zlatamigas.xmltask.builder.GemsDomBuilder;
import epam.zlatamigas.xmltask.builder.GemsSaxBuilder;
import epam.zlatamigas.xmltask.builder.GemsStaxBuilder;
import epam.zlatamigas.xmltask.exception.GemException;

public class GemsBuilderFactory {
    private GemsBuilderFactory(){};

    public static AbstractGemsBuilder createGemsBuilder(ParserType type) throws GemException {
        switch (type){

            case SAX -> {
                return new GemsSaxBuilder();
            }
            case STAX -> {
                return new GemsStaxBuilder();
            }
            case DOM -> {
                return new GemsDomBuilder();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
