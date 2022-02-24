package epam.zlatamigas.xmltask.creator;

import epam.zlatamigas.xmltask.entity.Gem;
import epam.zlatamigas.xmltask.entity.PreciousGem;
import epam.zlatamigas.xmltask.entity.SemiPreciousGem;

public class GemFactory {

    private GemFactory(){}

    public static Gem createGem(GemType type){
        switch (type){

            case PRECIOUS -> {
                return new PreciousGem();
            }
            case SEMI_PRECIOUS -> {
                return new SemiPreciousGem();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }

    public static Gem createGem(String typeName){
        GemType type = GemType.typeValueOf(typeName);
        switch (type){

            case PRECIOUS -> {
                return new PreciousGem();
            }
            case SEMI_PRECIOUS -> {
                return new SemiPreciousGem();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
