package godsoft.datagokr.kofpi.service.impl;

import java.net.URI;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class KofpiTest {

	@Test
	public void test() {
		System.out.println("테스트");

		RestTemplate restTemplate = new RestTemplate();

		// https://www.kofpi.or.kr/public/selMClass.do
		URI uri = URI.create("https://www.kofpi.or.kr/public/selMClass.do");
		String responseString = restTemplate.getForObject(uri, String.class);

		System.out.println(responseString);
	}

}
