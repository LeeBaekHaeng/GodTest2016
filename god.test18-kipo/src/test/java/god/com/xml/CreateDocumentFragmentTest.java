package god.com.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CreateDocumentFragmentTest {

	// @Test
	public void test() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);

		Document doc = factory.newDocumentBuilder().parse(new File("infilename.xml"));

		String fragment = "<fragment><b>aaa</b></fragment>";

		factory = DocumentBuilderFactory.newInstance();
		Document d = factory.newDocumentBuilder().parse(new InputSource(new StringReader(fragment)));

		Node node = doc.importNode(d.getDocumentElement(), true);

		DocumentFragment docfrag = doc.createDocumentFragment();

		while (node.hasChildNodes()) {
			docfrag.appendChild(node.removeChild(node.getFirstChild()));
		}

		Element element = doc.getDocumentElement();
		element.appendChild(docfrag);

		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		DOMSource domSource = new DOMSource(doc);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(domSource, sr);
		System.out.println(sw.toString());
	}

	// @Test
	public void test2() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document document = db.newDocument();

		Element book = document.createElement("book");
		book.setAttribute("id", "javanut4");
		document.appendChild(book);
		for (int i = 1; i <= 3; i++) {
			Element chapter = document.createElement("chapter");
			Element title = document.createElement("title");
			title.appendChild(document.createTextNode("Chapter " + i));
			chapter.appendChild(title);
			chapter.appendChild(document.createElement("para"));
			book.appendChild(chapter);
		}

		Element html = document.createElement("html");
		book.appendChild(html);
		html.setTextContent("<b>god</b><b>god</b>");

		Element html2 = document.createElement("html2");
		book.appendChild(html2);
		// String fragment = "<b>god</b>";
		// Document d =
		// DocumentBuilderFactory.newInstance().newDocumentBuilder()
		// .parse(new InputSource(new StringReader(fragment)));
		// Node node = document.importNode(d.getDocumentElement(), true);
		// DocumentFragment df = document.createDocumentFragment();
		// df.appendChild(node);
		// html2.appendChild(df);
		// html2.appendChild(createDocumentFragment(document,
		// "<b>god</b><b>god</b>"));
		String dirtext = ">>>\n" + "<<<\n" + "&&&\n" + "<><><>.";
		dirtext = "<b>god</b><b>god</b>";
		html2.appendChild(document.createCDATASection(dirtext));
		// html2.appendChild(document.createTextNode(dirtext));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		StringWriter sw = new StringWriter();
		StreamResult sr = new StreamResult(sw);
		transformer.transform(source, sr);
		// System.out.println(sw.toString());
		// System.out.println(stripCDATA(sw.toString()));
		System.out.println(removeCDATA(sw.toString()));

		FileUtils.writeStringToFile(new File(SystemUtils.USER_HOME + "/Desktop/test.xml"), removeCDATA(sw.toString()),
				"utf-8");
	}

	// @Test
	public void test3() throws Exception {
		String xmlRecords = "<data></data>";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlRecords));

		Document doc = factory.newDocumentBuilder().parse(is);

		CDATASection cdataNode = doc.createCDATASection("");

		CharacterData cdata = cdataNode;

		cdata.setData("from java2s.com");
		System.out.println(cdataNode);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(System.out);
		transformer.transform(xmlSource, outputTarget);
	}

	@Test
	public void test4() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document document = db.newDocument();

		Element book = document.createElement("book");
		book.setAttribute("id", "javanut4");
		document.appendChild(book);
		for (int i = 1; i <= 3; i++) {
			Element chapter = document.createElement("chapter");
			Element title = document.createElement("title");
			title.appendChild(document.createTextNode("Chapter " + i));
			chapter.appendChild(title);
			chapter.appendChild(document.createElement("para"));
			book.appendChild(chapter);
		}

		Element html = document.createElement("html");
		book.appendChild(html);
		html.setTextContent("<b>god</b><b>god</b>");

		Element html2 = document.createElement("html2");
		book.appendChild(html2);
		// String fragment = "<b>god</b>";
		// Document d =
		// DocumentBuilderFactory.newInstance().newDocumentBuilder()
		// .parse(new InputSource(new StringReader(fragment)));
		// Node node = document.importNode(d.getDocumentElement(), true);
		// DocumentFragment df = document.createDocumentFragment();
		// df.appendChild(node);
		// html2.appendChild(df);
		// html2.appendChild(createDocumentFragment(document,
		// "<b>god</b><b>god</b>"));
		String dirtext = ">>>\n" + "<<<\n" + "&&&\n" + "<><><>.";
		dirtext = "<b>god</b><b>갓</b>";
		html2.appendChild(document.createCDATASection(dirtext));
		// html2.appendChild(document.createTextNode(dirtext));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		Source xmlSource = new DOMSource(document);
		Result outputTarget = new StreamResult(System.out);
		transformer.transform(xmlSource, outputTarget);

		StringWriter sw = new StringWriter();
		outputTarget = new StreamResult(sw);
		transformer.transform(xmlSource, outputTarget);

		String pathname = SystemUtils.USER_HOME + "/Desktop/test.xml";
		File file = new File(pathname);
		// OutputStream outputStream = new FileOutputStream(file);
		OutputStream out = new FileOutputStream(file);
		// OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
		Writer out2 = new OutputStreamWriter(out, StandardCharsets.UTF_8);
		Writer writer = new BufferedWriter(out2);
		outputTarget = new StreamResult(writer);
		// outputTarget = new StreamResult(osw);
		transformer.transform(xmlSource, outputTarget);

		String readFileToString = FileUtils.readFileToString(file);

		// System.out.println("readFileToString=" + readFileToString);

		// String data = readFileToString.replaceAll("<html2><!\\[CDATA\\[",
		// "<html2>").replaceAll("]]></html2>",
		// "</html2>");
		String data = readFileToString;
		data = data.replaceAll("<html2><!\\[CDATA\\[", "<html2>");
		data = data.replaceAll("]]></html2>", "</html2>");

		System.out.println("data=" + data);

		FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);

	}

	public DocumentFragment createDocumentFragment(Document document, String s) {
		Document d = null;
		try {
			d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(s)));
		} catch (SAXException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (ParserConfigurationException e) {
			e.getMessage();
		}

		Node node = document.importNode(d.getDocumentElement(), true);

		DocumentFragment documentFragment = document.createDocumentFragment();

		documentFragment.appendChild(node);

		return documentFragment;
	}

	public static String stripCDATA(String s) {
		s = s.trim();
		if (s.startsWith("<![CDATA[")) {
			s = s.substring(9);
			int i = s.indexOf("]]&gt;");
			if (i == -1) {
				throw new IllegalStateException("argument starts with <![CDATA[ but cannot find pairing ]]&gt;");
			}
			s = s.substring(0, i);
		}
		return s;
	}

	public String removeCDATA(String text) {
		// String resultString = "";
		// Pattern regex =
		// Pattern.compile("(?<!(<!\\[CDATA\\[))|((.*)\\w+\\W)");
		// Matcher regexMatcher = regex.matcher(text);
		// while (regexMatcher.find()) {
		// resultString += regexMatcher.group();
		// }
		// return resultString;

		// return text.replaceAll("<![CDATA[", "").replaceAll("]]>", "");
		// return text.replaceAll("<![CDATA[", "");
		// return text.replaceAll("(?<!CDATA)\\[\\w+\\]", "");
		// return text.replaceAll("<!\\[CDATA\\[",
		// "XML_CDATA_START").replaceAll("]]>", "XML_CDATA_END");
		return text.replaceAll("<!\\[CDATA\\[", "").replaceAll("]]>", "");
	}

}
