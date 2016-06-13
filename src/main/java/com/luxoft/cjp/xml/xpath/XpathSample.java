package com.luxoft.cjp.xml.xpath;

import com.luxoft.cjp.xml.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * Generic sample demonstrating XPath possibilities.
 */
public class XpathSample {
    private static final Logger LOG = LoggerFactory.getLogger(XpathSample.class);

    private final Document xml;
    private final XPath xpath;

    public XpathSample() throws ParserConfigurationException, SAXException, IOException {
        this(Staff.DEFAULT_FILE_PATH);
    }

    public XpathSample(String pathname) throws IOException, SAXException, ParserConfigurationException {
        xml = getDocument(pathname);

        XPathFactory xPathfactory = XPathFactory.newInstance();
        xpath = xPathfactory.newXPath();
    }

    private Document getDocument(String pathname) throws ParserConfigurationException, SAXException, IOException {
        Document xml;
        String filePath = getClass().getClassLoader().getResource(pathname).getPath();
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        xml = dBuilder.parse(xmlFile);
        return xml;
    }

    public String getById(String id) throws XPathExpressionException {
        XPathExpression xPathExpression = xpath.compile("/company/staff[@id=\"" + id + "\"]");
        return xPathExpression.evaluate(xml);
    }


    public String getNameByNick (String nickname) throws XPathExpressionException {
        XPathExpression xPathExpression = xpath.compile("/company/staff/nickname[text() = \"" + nickname + "\"]/preceding-sibling::firstname[1]");
        return xPathExpression.evaluate(xml);
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        XpathSample sample = new XpathSample();
        LOG.info(sample.getNameByNick("craftsman"));

    }

}
