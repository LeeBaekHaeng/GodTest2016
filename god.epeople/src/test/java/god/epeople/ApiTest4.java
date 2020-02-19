package god.epeople;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ApiTest4 {

	@Test
	public void test() throws Exception {
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

}
