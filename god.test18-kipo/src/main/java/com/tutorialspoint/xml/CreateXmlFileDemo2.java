package com.tutorialspoint.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXmlFileDemo2 {

	// https://www.tutorialspoint.com/java_xml/java_dom_create_document.htm

	public static void main(String argv[]) throws Exception {

		CreateXmlFileDemo2 createXmlFileDemo2 = new CreateXmlFileDemo2();

		createXmlFileDemo2.main();

	}

	public void main() throws Exception {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();

		cars(doc);
		supercars(doc);
		company(doc);
		carname(doc);
		carname1(doc);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		// transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

		DOMSource source = new DOMSource(doc);
		// StreamResult result = new StreamResult(new File("C:\\cars.xml"));
		StreamResult result = new StreamResult(new File("target/cars.xml"));
		transformer.transform(source, result);

		// Output to console for testing
		StreamResult consoleResult = new StreamResult(System.out);
		transformer.transform(source, consoleResult);

	}

	private void cars(Document doc) {

		// root element
		Element rootElement = doc.createElement("cars");
		doc.appendChild(rootElement);

	}

	private void supercars(Document doc) throws XPathExpressionException {

		// Element rootElement = doc.getDocumentElement();

		XPath xPath = XPathFactory.newInstance().newXPath();

		Element rootElement = (Element) xPath.compile("/cars").evaluate(doc, XPathConstants.NODE);

		// supercars element
		Element supercar = doc.createElement("supercars");
		rootElement.appendChild(supercar);

	}

	private void company(Document doc) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		Element supercar = (Element) xPath.compile("/cars/supercars").evaluate(doc, XPathConstants.NODE);

		// setting attribute to element
		Attr attr = doc.createAttribute("company");
		attr.setValue("Ferrari");
		supercar.setAttributeNode(attr);

	}

	private void carname(Document doc) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		Element supercar = (Element) xPath.compile("/cars/supercars").evaluate(doc, XPathConstants.NODE);

		// carname element
		Element carname = doc.createElement("carname");
		Attr attrType = doc.createAttribute("type");
		attrType.setValue("formula one");
		carname.setAttributeNode(attrType);
		carname.appendChild(doc.createTextNode("Ferrari 101"));
		supercar.appendChild(carname);

	}

	private void carname1(Document doc) throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		Element supercar = (Element) xPath.compile("/cars/supercars").evaluate(doc, XPathConstants.NODE);

		Element carname1 = doc.createElement("carname");
		Attr attrType1 = doc.createAttribute("type");
		attrType1.setValue("sports");
		carname1.setAttributeNode(attrType1);
		carname1.appendChild(doc.createTextNode("Ferrari 202"));
		supercar.appendChild(carname1);

	}

}