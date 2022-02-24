package test.zlatamigas.xmltask.validator;

import epam.zlatamigas.xmltask.exception.GemException;
import epam.zlatamigas.xmltask.validator.GemsXMLValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GemsXMLValidatorTest {

    private static final String XML_FILE = "data/gem_2.xml";
    private static final String XML_FILE_WRONG = "data/gem_wrong.xml";
    private GemsXMLValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new GemsXMLValidator();
    }

    @Test
    public void testIsValidXML() {

        boolean actual = false;
        try {
            actual = validator.isValidXML(XML_FILE);
        } catch (GemException e) {
            System.out.println("hello");
            fail(e.getMessage(),e);
        }
        assertTrue(actual);
    }

    @Test
    public void testIsNotValid() {

        boolean actual = false;
        try {
            actual = validator.isValidXML(XML_FILE_WRONG);
        } catch (GemException e) {
            fail(e.getMessage(),e);
        }
        assertFalse(actual);
    }
}