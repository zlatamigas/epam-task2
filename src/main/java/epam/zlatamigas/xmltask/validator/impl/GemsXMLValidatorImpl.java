package epam.zlatamigas.xmltask.validator.impl;

import epam.zlatamigas.xmltask.builder.GemErrorHandler;
import epam.zlatamigas.xmltask.exception.GemException;
import epam.zlatamigas.xmltask.validator.GemsXmlValidator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GemsXMLValidatorImpl implements GemsXmlValidator {

    private static final String SCHEMA_NAME = "schema/gem.xsd";

    private static GemsXMLValidatorImpl instance;

    private GemsXMLValidatorImpl() {
    }

    public static GemsXMLValidatorImpl getInstance() {
        if (instance == null) {
            instance = new GemsXMLValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean validateXML(String xmlFile) throws GemException {

        String xmlPath = "";

        try {
            ClassLoader loader = this.getClass().getClassLoader();

            URL resource = loader.getResource(SCHEMA_NAME);
            File schemaFile = new File(resource.getFile());
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();

            URL resourceXML = loader.getResource(xmlFile);
            xmlPath = new File(resourceXML.getFile()).getPath();
            Source source = new StreamSource(xmlPath);

            validator.setErrorHandler(new GemErrorHandler());
            validator.validate(source);

        } catch (NullPointerException e){
            throw new GemException(e.getMessage(), e);
        } catch (IOException e) {
            throw new GemException("Cannot open file: " + xmlPath, e);
        } catch (SAXException e) {
            return false;
        }

        return true;
    }
}
