package epam.zlatamigas.xmltask.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class GemsSaxBuilder extends AbstractGemsBuilder {

    private static final Logger logger = LogManager.getLogger();

    private XMLReader reader;
    private GemHandler handler;

    public GemsSaxBuilder() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();

            reader.setErrorHandler(new GemErrorHandler());
            handler = new GemHandler();
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Failed configure GermsSaxBuilder", e);
        }

    }

    @Override
    public void buildSetGems(String filename) {
        try {
            gems.clear();
            reader.parse(filename);
            gems = handler.getGemsSet();
        } catch (IOException | SAXException e) {
            gems.clear();
            logger.error("Failed parse file: " + filename, e);
        }
    }
}
