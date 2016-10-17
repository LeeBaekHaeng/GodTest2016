package godsoft.mbl.datagokr.daejeon.service.impl;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class GodsoftMblDatagokrDaejeonServiceImplTest {

	@Test
	public void test() throws Exception {
		// fail("Not yet implemented");

		// String addr =
		// "http://data.daejeon.go.kr/openapi-data/service/rest/kidSafe/kidSafeDaejeonService/kidSafeAreaDaejeon";
		//
		// String parameter = "";
		//
		// // 인증키(서비스키) url인코딩
		// // String serviceKey = URLEncoder
		// //
		// .encode("tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D",
		// // "UTF-8");
		// String serviceKey =
		// "?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";
		//
		// // parameter = "ServiceKey" + serviceKey;
		//
		// /*
		// * parameter setting parameter = parameter + "&" + "PARAM1=AAA";
		// * parameter = parameter + "&" + "PARAM2=BBB"; parameter = parameter +
		// * "&" + "PARAM3=CCC";
		// */
		//
		// addr = addr + serviceKey + parameter;
		//
		// System.out.println(addr);
		//
		// URL url = new URL(addr);
		// InputStream in = url.openStream();
		// // CachedOutputStream bos = new CachedOutputStream();
		// // IOUtils.copy(in, bos);
		// in.close();
		// // bos.close();
		// // return bos.getOut().toString();

		String url = "http://data.daejeon.go.kr/openapi-data/service/rest/kidSafe/kidSafeDaejeonService/kidSafeAreaDaejeon?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";
		Document document = Jsoup
				.parse(new URL(url).openStream(), "utf-8", url);
		System.out.println(document);
	}

}
