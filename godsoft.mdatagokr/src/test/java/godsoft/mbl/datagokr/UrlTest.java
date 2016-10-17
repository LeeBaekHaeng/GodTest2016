package godsoft.mbl.datagokr;

import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class UrlTest {

	@Test
	public void test() throws Exception {
		String spec = "http://openapi.map.naver.com/api/geocode?key=acadc19d58314c61c3ce90f95eca41fc&encoding=utf-8&coord=latlng&output=json&query=%EA%B2%BD%EA%B8%B0%EB%8F%84%20%EC%84%B1%EB%82%A8%EC%8B%9C%20%EB%B6%84%EB%8B%B9%EA%B5%AC%20%EC%A0%95%EC%9E%90%EB%8F%99%20178-1";

		URL url = new URL(spec);

		System.out.println("url=" + url);

		System.out.println("url=" + url.openStream());

		System.out.println(IOUtils.toString(url.openStream()));
	}

}
