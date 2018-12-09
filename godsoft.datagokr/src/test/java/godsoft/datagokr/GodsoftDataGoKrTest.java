package godsoft.datagokr;

import java.net.URLEncoder;

import org.apache.commons.net.util.Base64;
import org.junit.Test;

public class GodsoftDataGoKrTest {

	@Test
	public void test() throws Exception {
		// fail("Not yet implemented");

		// %EA%B0%80%EB%A6%AC%EC%82%B0

		String string = "가리산";

		byte[] binaryData = string.getBytes();

		System.out.println(Base64.encodeBase64String(binaryData));
		System.out.println(Base64.encodeBase64URLSafeString(binaryData));

		System.out.println(URLEncoder.encode(string, "UTF-8"));
	}

}
