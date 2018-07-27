package god;

import org.junit.Test;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class GodTest {

	// @Test
	public void test() {
		// fail("Not yet implemented");

		XmlMapper mapper = new XmlMapper();
		System.out.println("mapper=" + mapper);
		System.out.println(mapper);
	}

	@Test
	public void test2() {
		JacksonXmlModule module = new JacksonXmlModule();
		// and then configure, for example:
		module.setDefaultUseWrapper(false);
		XmlMapper xmlMapper = new XmlMapper(module);
		// and you can also configure AnnotationIntrospectors etc here:

		System.out.println("module=" + module);
		System.out.println("xmlMapper=" + xmlMapper);
	}

}
