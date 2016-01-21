package com.luxoft.cjp.xml.sax;

import com.luxoft.cjp.xml.Staff;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * SAX parser example
 *
 * @author Yuriy Privezentsev
 * @since 1/21/2016
 */
public class SaxParser extends DefaultHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SaxParser.class);
    private final File xmlFile;

    public SaxParser() {
        this(Staff.DEFAULT_FILE_PATH);
    }

    @SuppressWarnings("ConstantConditions")
    public SaxParser(String pathname) {
        String filePath = getClass().getClassLoader().getResource(pathname).getPath();
        this.xmlFile = new File(filePath);
    }

    @Override
    public void startDocument() throws SAXException {
        LOG.info("Start document");
    }

    @Override
    public void endDocument() throws SAXException {
        LOG.info("End document");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        LOG.info("Start the element: {}", qName);
        if (LOG.isInfoEnabled()) {
            printAttributes(attributes);
        }
    }

    private void printAttributes(Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            LOG.info("Attribute: {} of type: {} with value: {}", attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        LOG.info("End of the element: {}", qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        LOG.info("Element value: {}", new String(ch, start, length));
    }

    public void parse() throws ParserConfigurationException, org.xml.sax.SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(xmlFile, this);
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        SaxParser parser = new SaxParser();
        parser.parse();
    }
}
