package epam.zlatamigas.xmltask.builder;

import epam.zlatamigas.xmltask.creator.GemFactory;
import epam.zlatamigas.xmltask.entity.*;
import epam.zlatamigas.xmltask.exception.GemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;

public class GemsStaxBuilder extends AbstractGemsBuilder {

    private static final Logger logger = LogManager.getLogger();

    private final XMLInputFactory inputFactory;

    public GemsStaxBuilder() {
        inputFactory = XMLInputFactory.newFactory();
    }

    @Override
    public void buildSetGems(String filename) throws GemException {

        XMLStreamReader reader;
        String name;
        int type;

        Gem gem;

        try (FileInputStream inputStream = new FileInputStream(filename)) {
            reader = inputFactory.createXMLStreamReader(inputStream);

            gems.clear();
            while (reader.hasNext()) {
                type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(GemsXmlTag.PRECIOUS.getValue()) || name.equals(GemsXmlTag.SEMI_PRECIOUS.getValue())) {
                        gem = buildGem(reader);
                        gems.add(gem);
                    }
                }
            }

        } catch (IOException | XMLStreamException e) {
            gems.clear();
            throw new GemException("Failed parsing file: " + filename, e);
        }
    }

    private Gem buildGem(XMLStreamReader reader) throws XMLStreamException {

        Gem currentGem = GemFactory.createGem(reader.getLocalName());

        String id = reader.getAttributeValue(null, GemsXmlTag.ID.getValue());
        currentGem.setId(id);
        String name = reader.getAttributeValue(null, GemsXmlTag.NAME.getValue());
        currentGem.setName(name);

        GemsXmlTag tag;
        int type;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {

                    tag = GemsXmlTag.typeValueOf(reader.getLocalName());
                    buildGemProperties(reader, tag, currentGem);
                }
                case XMLStreamConstants.END_ELEMENT -> {

                    tag = GemsXmlTag.typeValueOf(reader.getLocalName());
                    if (tag == GemsXmlTag.PRECIOUS || tag == GemsXmlTag.SEMI_PRECIOUS) {
                        return currentGem;
                    }
                }
            }
        }

        return currentGem;
    }

    private void buildGemProperties(XMLStreamReader reader, GemsXmlTag xmlTag, Gem currentGem) throws XMLStreamException {
        String data = getXMLText(reader);
        switch (xmlTag) {
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
            default -> throw new EnumConstantNotPresentException(xmlTag.getDeclaringClass(), xmlTag.name());
        }
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
