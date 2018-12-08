package god.com.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

public class CreateElement {

	@Test
	public void test() throws Exception {
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

		// Add a PI at the beginning of the document
		Element element = document.getDocumentElement();
		ProcessingInstruction pi = document.createProcessingInstruction("target", "instruction");
		element.getParentNode().insertBefore(pi, element);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

}
