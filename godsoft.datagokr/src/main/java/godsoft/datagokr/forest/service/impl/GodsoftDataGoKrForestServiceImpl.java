package godsoft.datagokr.forest.service.impl;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import godsoft.datagokr.forest.service.GodsoftDataGoKrForestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftDataGoKrForestServiceImpl implements
		GodsoftDataGoKrForestService {

	@Override
	public void gdTrailInfoOpenAPISelectList(Map<String, Object> vo,
			ModelMap model) throws Exception {

		String pageUnit = (String) vo.get("pageUnit");
		if (StringUtils.isEmpty(pageUnit) == true) {
			vo.put("pageUnit", "10");
		}

		String searchArNm = (String) vo.get("searchArNm");
		if (StringUtils.isEmpty(searchArNm) == true) {
			vo.put("searchArNm", "서울");
		}

		gdTrailInfoOpenAPI(vo, model);
	}

	@Override
	public void gdTrailInfoOpenAPISelect(Map<String, Object> vo, ModelMap model)
			throws Exception {

		gdTrailInfoOpenAPI(vo, model);
	}

	private void gdTrailInfoOpenAPI(Map<String, Object> vo, ModelMap model)
			throws Exception {

		System.out.println("vo=" + vo);
		System.out.println("model=" + model);

		model.addAttribute("vo", vo);

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/gdTrailInfoOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		connection.timeout(300000);

		data(vo, model, connection);

		Document document = connection.post();

		items(vo, model, document);

		System.out.println("vo=" + vo);
		System.out.println("model=" + model);
	}

	private void data(Map<String, Object> vo, ModelMap model,
			Connection connection) throws Exception {

		// String pageUnit = (String) vo.get("pageUnit");
		// String pageIndex = (String) vo.get("pageIndex");
		String pageUnit = MapUtils.getString(vo, "pageUnit", "10");
		String pageIndex = MapUtils.getString(vo, "pageIndex", "1");
		String searchArNm = (String) vo.get("searchArNm");
		String searchMtNm = (String) vo.get("searchMtNm");

		// OPEN API 인증키
		connection.data("key", "37bb6a43b85d40368f303e2b26c97924");

		// 페이지당 결과값 갯수 ( 기본값 : 10 )
		if (StringUtils.isEmpty(pageUnit) == false) {
			connection.data("pageUnit", pageUnit);
			System.out.println("pageUnit=" + pageUnit);
		}

		// 페이지번호 ( 기본값 : 1 )
		if (StringUtils.isEmpty(pageIndex) == false) {
			connection.data("pageIndex", pageIndex);
			System.out.println("pageIndex=" + pageIndex);
		}

		// 단어검색값 (지역명), 인코딩 :UTF-8
		if (StringUtils.isEmpty(searchArNm) == false) {
			connection.data("searchArNm", searchArNm);
			System.out.println("searchArNm=" + searchArNm);
		}

		// 단어검색값 (산명), 인코딩 :UTF-8
		if (StringUtils.isEmpty(searchMtNm) == false) {
			connection.data("searchMtNm", searchMtNm);
			System.out.println("searchMtNm=" + searchMtNm);
		}
	}

	private void items(Map<String, Object> vo, ModelMap model, Document document)
			throws Exception {

		// result
		// key
		// totalCnt
		// pageUnit
		// pageIndex
		// searchCnm
		// searchNm

		EgovMap result = new EgovMap();
		result.put("key", document.select("key").text());
		result.put("totalCnt", document.select("totalCnt").text());
		result.put("pageUnit", document.select("pageUnit").text());
		result.put("pageIndex", document.select("pageIndex").text());
		result.put("searchCnm", document.select("searchCnm").text());
		result.put("searchNm", document.select("searchNm").text());
		model.addAttribute("result", result);

		// gdTrailInfo
		Elements gdTrailInfoElements = document.select("gdTrailInfo");

		List<EgovMap> items = new ArrayList<EgovMap>();

		for (Element gdTrailInfoElement : gdTrailInfoElements) {
			EgovMap item = new EgovMap();

			item.put("mntnCd", gdTrailInfoElement.select("mntnCd").text());
			item.put("mntNm", gdTrailInfoElement.select("mntNm").text());
			item.put("subNm", gdTrailInfoElement.select("subNm").text());
			item.put("areaNm", gdTrailInfoElement.select("areaNm").text());
			item.put("mntHeight", gdTrailInfoElement.select("mntHeight").text());
			item.put("aeatReason", gdTrailInfoElement.select("aeatReason")
					.text());
			item.put("overView", gdTrailInfoElement.select("overView").text());
			item.put("details", gdTrailInfoElement.select("details").text());

			trailPlus(gdTrailInfoElement.select("trailPlus"), item);

			item.put("transport", gdTrailInfoElement.select("transport").text());
			item.put("tourismInf", gdTrailInfoElement.select("tourismInf")
					.text());
			item.put("etcCourse", gdTrailInfoElement.select("etcCourse").text());
			item.put("flashUrl", gdTrailInfoElement.select("flashUrl").text());
			item.put("videoUrl", gdTrailInfoElement.select("videoUrl").text());

			items.add(item);
		}

		model.addAttribute("items", items);
	}

	private void trailPlus(Elements trailPlusElements, EgovMap item)
			throws Exception {
		// trailPlus
		// tpNo
		// tpTitl
		// tpContent

		List<EgovMap> items = new ArrayList<EgovMap>();

		for (Element trailPlusElement : trailPlusElements) {
			EgovMap item2 = new EgovMap();

			item2.put("tpNo", trailPlusElement.select("tpNo").text());
			item2.put("tpTitl", trailPlusElement.select("tpTitl").text());
			item2.put("tpContent", trailPlusElement.select("tpContent").text());

			items.add(item2);
		}

		item.put("trailPlus", items);
	}

}
