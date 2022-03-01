package epam.zlatamigas.xmltask.builder;

import epam.zlatamigas.xmltask.creator.GemFactory;
import epam.zlatamigas.xmltask.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();

    private final Set<Gem> gemsSet;
    private final EnumSet<GemsXmlTag> textXmlTag;

    private Gem currentGem;
    private GemsXmlTag currentXmlTag;

    public GemHandler() {
        gemsSet = new HashSet<>();
        textXmlTag = EnumSet.range(GemsXmlTag.ORIGIN, GemsXmlTag.IMPLEMENTATION);
    }

    public Set<Gem> getGemsSet() {
        return gemsSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        GemsXmlTag tagType = GemsXmlTag.typeValueOf(qName);
        if (tagType == GemsXmlTag.PRECIOUS || tagType == GemsXmlTag.SEMI_PRECIOUS) {
            currentGem = GemFactory.createGem(qName);

            currentGem.setId(attributes.getValue(GemsXmlTag.ID.getValue()));
            currentGem.setName(attributes.getValue(GemsXmlTag.NAME.getValue()));
        } else if (textXmlTag.contains(tagType)) {
            currentXmlTag = tagType;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        GemsXmlTag tagType = GemsXmlTag.typeValueOf(qName);
        if (tagType == GemsXmlTag.PRECIOUS || tagType == GemsXmlTag.SEMI_PRECIOUS) {
            gemsSet.add(currentGem);
            currentGem = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (currentXmlTag == null) {
            return;
        }

        switch (currentXmlTag) {
            case ORIGIN -> {
                currentGem.setOrigin(GemOrigin.typeValueOf(data));
            }
            case EXTRACTION_TIME -> {
                YearMonth extractionTime = YearMonth.parse(data);
                currentGem.setExtractionTime(extractionTime);
            }
            case COLOR -> {
                currentGem.setColor(data);
            }
            case TRANSPARENCY -> {
                currentGem.setTransparency(Integer.parseInt(data));
            }
            case FACES -> {
                currentGem.setFaces(Integer.parseInt(data));
            }
            case MASS -> {
                currentGem.setMass(Double.parseDouble(data));
            }
            case TYPE -> {
                PreciousType type = PreciousType.typeValueOf(data);
                ((PreciousGem) currentGem).setType(type);
            }
            case IMPLEMENTATION -> {
                String implementation = (data == null ? "" : data);
                ((SemiPreciousGem) currentGem).setImplementation(implementation);
            }
            default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
        }

        currentXmlTag = null;
    }
}
