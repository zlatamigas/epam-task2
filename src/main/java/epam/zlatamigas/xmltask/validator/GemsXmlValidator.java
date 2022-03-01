package epam.zlatamigas.xmltask.validator;

import epam.zlatamigas.xmltask.exception.GemException;

public interface GemsXmlValidator {
    boolean validateXML(String xmlFile) throws GemException;
}
