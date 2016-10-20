package godsoft.mbl.datagokr.kofpi.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public class KofpiTest {

	@Test
	public void test() {
		// https://www.kofpi.or.kr/public/dataOpen_03.do
		// 목재제품 DB백과 api

		// selMClass();
		selDinfo();
	}

	public void selMClass() {
		RestTemplate restTemplate = new RestTemplate();

		// 품목
		String url = "https://www.kofpi.or.kr/public/selMClass.do";

		String json = restTemplate.getForObject(url, String.class);

		System.out.println(json);

		Gson gson = new Gson();

		EgovMap map = gson.fromJson(json, EgovMap.class);

		System.out.println(map);

		System.out.println(map.getClass());

		System.out.println(map.get("mClass").getClass());

		@SuppressWarnings("unchecked")
		List<LinkedTreeMap<String, Object>> mClass = (List<LinkedTreeMap<String, Object>>) map
				.get("mClass");

		for (LinkedTreeMap<String, Object> item : mClass) {
			System.out.println(item);
			System.out.println(item.getClass());

			// 품목 코드
			System.out.println(item.get("MC_CD"));

			// 품목명
			System.out.println(item.get("MC_NM"));

			// 종류코드
			System.out.println(item.get("BC_CD"));
		}
	}

	public void selDinfo() {
		RestTemplate restTemplate = new RestTemplate();

		// 기본정보
		String url = "https://www.kofpi.or.kr/public/selDinfo.do?mc_cd={mc_cd}";

		String json = restTemplate.getForObject(url, String.class, "13");

		System.out.println(json);

		Gson gson = new Gson();

		EgovMap map = gson.fromJson(json, EgovMap.class);

		System.out.println(map);

		System.out.println(map.getClass());

		System.out.println(map.get("dinfo").getClass());

		@SuppressWarnings("unchecked")
		LinkedTreeMap<String, Object> dinfo = (LinkedTreeMap<String, Object>) map
				.get("dinfo");

		System.out.println(dinfo);
		System.out.println(dinfo.getClass());
		System.out.println(dinfo.get("ENG_NM"));
		System.out.println("https://www.kofpi.or.kr" + dinfo.get("IMG_PATH"));
		System.out.println(dinfo.get("JPN_NM"));
		System.out.println(dinfo.get("MC_NM"));
		System.out.println(dinfo.get("CHI_NM"));
		System.out.println(dinfo.get("MC_CD"));
		System.out.println(dinfo.get("SAENGYAK_NM"));
		System.out.println(dinfo.get("HAK_NM"));
		System.out.println(dinfo.get("DPYO_NM"));
		System.out.println(dinfo.get("GWA_NM"));
	}

}
