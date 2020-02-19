package god.epeople;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ApiTest6 {

	@Test
	public void test() throws Exception {
		// use our HostnameVerifier for all future connections
		HttpsURLConnection.setDefaultHostnameVerifier(WhitelistHostnameVerifier.INSTANCE);

		String spec = "https://27.101.212.7/shareApi/getIdeaList.do";
		System.out.println(spec);
		URL url = new URL(spec);
		String s = IOUtils.toString(url.openStream());
		System.out.println(s);
	}

}
