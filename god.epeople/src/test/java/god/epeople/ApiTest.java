package god.epeople;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ApiTest {

	// @Test
	public void test() throws Exception {
		String spec = "http://www.epeople.go.kr/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);
		String s = IOUtils.toString(url.openStream());
		System.out.println(s);
	}

	// @Test
	public void test2() throws Exception {
		String spec = "https://www.epeople.go.kr/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);
		String s = IOUtils.toString(url.openStream());
		System.out.println(s);
	}

	// @Test
	public void test3() throws Exception {
		String spec = "http://27.101.212.7/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);
		String s = IOUtils.toString(url.openStream());
		System.out.println(s);
	}

	@Test
	public void test3A() throws Exception {
		String spec = "http://27.101.212.7/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);

		URLConnection connection = url.openConnection();
		String redirect = connection.getHeaderField("Location");
		System.out.println("redirect: " + redirect);
		if (redirect != null) {
			connection = new URL(redirect).openConnection();
		}

		String s = IOUtils.toString(connection.getInputStream());
		System.out.println(s);
	}

	// @Test
	public void test4() throws Exception {
		String spec = "https://27.101.212.7/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);
		String s = IOUtils.toString(url.openStream());
		System.out.println(s);
	}

}
