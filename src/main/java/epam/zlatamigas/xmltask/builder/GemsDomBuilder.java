package epam.zlatamigas.xmltask.builder;

import epam.zlatamigas.xmltask.entity.*;
import epam.zlatamigas.xmltask.exception.GemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;


public class GemsDomBuilder extends AbstractGemsBuilder {

    private static final Logger logger = LogManager.getLogger();

    private DocumentBuilder docBuilder;

    public GemsDomBuilder() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Failed configure GermsDomBuilder", e);
        }
        logger.debug("GemDomBuilder configured");
    }

    @Override
    public void buildSetGems(String filename) throws GemException {
        Document doc = null;
        try {
            doc = docBuilder.parse(filename);
        } catch (SAXException | IOException e) {
            gems.clear();
            throw new GemException("Failed parsing file: " + filename, e);
        }

        gems.clear();
        Element root = doc.getDocumentElement();
        buildPreciousGems(root);
        buildSemiPreciousGems(root);
    }

    private void buildPreciousGems(Element root) {
        NodeList gemsList = root.getElementsByTagName(GemsXmlTag.PRECIOUS.getValue());

        Element gemElement;
        PreciousGem gem;
        PreciousType type;

        for (int i = 0; i < gemsList.getLength(); i++) {
            gemElement = (Element) gemsList.item(i);
            gem = new PreciousGem();

            buildGem(gemElement, gem);
            type = PreciousType.typeValueOf(getElementTextContent(gemElement, GemsXmlTag.TYPE.getValue()));
            gem.setType(type);

            gems.add(gem);
        }
    }

    private void buildSemiPreciousGems(Element root) {
        NodeList gemsList = root.getElementsByTagName(GemsXmlTag.SEMI_PRECIOUS.getValue());

        Element gemElement;
        SemiPreciousGem gem;
        String implementation;

        NodeList nodeList;
        Node node;

        for (int i = 0; i < gemsList.getLength(); i++) {
            gemElement = (Element) gemsList.item(i);
            gem = new SemiPreciousGem();

            buildGem(gemElement, gem);

            nodeList = gemElement.getElementsByTagName(GemsXmlTag.IMPLEMENTATION.getValue());
            if (nodeList.getLength() != 0) {
                node = nodeList.item(0);
                implementation = node.getTextContent();
            } else {
                implementation = "";
            }
            gem.setImplementation(implementation);

            gems.add(gem);
        }
    }

    private void buildGem(Element gemElement, Gem gem) {
        String id = gemElement.getAttribute(GemsXmlTag.ID.getValue());

        String name = null;
        Attr at = gemElement.getAttributeNode(GemsXmlTag.NAME.getValue());
        if (at != null) {
            name = at.getValue();
        }

        GemOrigin origin = GemOrigin.typeValueOf(getElementTextContent(gemElement, GemsXmlTag.ORIGIN.getValue()));
        YearMonth extractionTime = YearMonth.parse(
                getElementTextContent(gemElement, GemsXmlTag.EXTRACTION_TIME.getValue()));
        String color = getElementTextContent(gemElement, GemsXmlTag.COLOR.getValue());
        int transparency = Integer.parseInt(getElementTextContent(gemElement, GemsXmlTag.TRANSPARENCY.getValue()));
        int faces = Integer.parseInt(getElementTextContent(gemElement, GemsXmlTag.FACES.getValue()));
        double mass = Double.parseDouble(getElementTextContent(gemElement, GemsXmlTag.MASS.getValue()));

        gem.setId(id);
        gem.setName(name);
        gem.setOrigin(origin);
        gem.setExtractionTime(extractionTime);
        gem.setColor(color);
        gem.setTransparency(transparency);
        gem.setFaces(faces);
        gem.setMass(mass);
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();

        return text;
    }
}
