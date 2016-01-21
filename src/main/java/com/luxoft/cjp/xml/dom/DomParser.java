package com.luxoft.cjp.xml.dom;

import com.luxoft.cjp.xml.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Sample DOM parser
 *
 * @author Yuriy Privezentsev
 * @since 1/21/2016
 */
public class DomParser {

    private static final Logger LOG = LoggerFactory.getLogger(DomParser.class);

    private final File xmlFile;

    public DomParser() {
        this(Staff.DEFAULT_FILE_PATH);
    }

    @SuppressWarnings("ConstantConditions")
    public DomParser(String pathname) {
        String filePath = getClass().getClassLoader().getResource(pathname).getPath();
        this.xmlFile = new File(filePath);
    }

    /**
     * Traverse over XML document and print out tags' values.
     */
    public void parse() {
        Document document = getDocument();

        LOG.info("Root element :" + document.getDocumentElement().getNodeName());

        NodeList nList = document.getElementsByTagName(Staff.STAFF_ELEMENT);

        LOG.info("----------------------------");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            parseNode(nNode);
        }

    }

    private void parseNode(Node nNode) {
        LOG.info("\nCurrent Element :" + nNode.getNodeName());

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

            Element eElement = (Element) nNode;

            printSubElements(eElement);
        }
    }

    private void printSubElements(Element eElement) {
        LOG.info("Staff id : " + eElement.getAttribute(Staff.STAFF_ID));
        LOG.info("First Name : " + eElement.getElementsByTagName(Staff.FIRSTNAME).item(0).getTextContent());
        LOG.info("Last Name : " + eElement.getElementsByTagName(Staff.LASTNAME).item(0).getTextContent());
        LOG.info("Nick Name : " + eElement.getElementsByTagName(Staff.NICKNAME).item(0).getTextContent());
        LOG.info("Salary : " + eElement.getElementsByTagName(Staff.SALARY).item(0).getTextContent());
    }

    private Document getDocument() {
        try {
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document document = dBuilder.parse(getXmlFile());

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();
            return document;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("Something went wrong",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Create the new first level element
     */
    public void addElement() {
        final Document document = getDocument();
        final Element newStaffMember = document.createElement(Staff.STAFF_ELEMENT);
        newStaffMember.setAttribute(Staff.STAFF_ID, "3001");

        fillElementData(newStaffMember);

        NodeList list = document.getElementsByTagName("company");
        Element section = (Element) list.item(0);
        section.appendChild(newStaffMember);

        writeDocument(document);

    }

    private void fillElementData(Element newStaffMember) {
        appendTextElement(newStaffMember, Staff.FIRSTNAME, "Nicola");
        appendTextElement(newStaffMember, Staff.LASTNAME, "Tesla");
        appendTextElement(newStaffMember, Staff.NICKNAME, "scientist");
        appendTextElement(newStaffMember, Staff.SALARY, "15000");
    }

    private void writeDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(document), new StreamResult(getXmlFile()));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private void appendTextElement(Element parentElement, String childElementName, String childElementValue) {
        final Document document = parentElement.getOwnerDocument();
        final Element name = document.createElement(childElementName);
        name.appendChild(document.createTextNode(childElementValue));
        parentElement.appendChild(name);
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public static void main(String[] args) {
        final DomParser test = new DomParser();
        test.parse();
        test.addElement();
    }
}
