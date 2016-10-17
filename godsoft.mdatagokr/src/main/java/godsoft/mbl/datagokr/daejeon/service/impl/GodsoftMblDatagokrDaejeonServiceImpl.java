package godsoft.mbl.datagokr.daejeon.service.impl;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import godsoft.mbl.datagokr.daejeon.service.GodsoftMblDatagokrDaejeonService;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftMblDatagokrDaejeonServiceImpl implements
		GodsoftMblDatagokrDaejeonService {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void getKidSafeDaejeonList(Map<String, Object> vo, ModelMap model)
			throws Exception {

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("vo=" + vo);
			egovLogger.debug("model=" + model);
		}

		// connect
		String url = "http://data.daejeon.go.kr/openapi-data/service/rest/kidSafe/kidSafeDaejeonService/kidSafeAreaDaejeon";

		url += "?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

		// Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data(vo, model, connection);

		// 페이지당레코드 수
		String numOfRows = (String) vo.get("numOfRows");
		if (StringUtils.isEmpty(numOfRows) == false) {
			url += "&numOfRows=" + numOfRows;
		}

		// 검색분류코드
		String searchCondition = (String) vo.get("searchCondition");
		if (StringUtils.isEmpty(searchCondition) == false) {
			url += "&searchCondition=" + searchCondition;
		}

		// 검색키워드
		String searchKeyword = (String) vo.get("searchKeyword");
		if (StringUtils.isEmpty(searchKeyword) == false) {
			url += "&searchKeyword="
					+ URLEncoder.encode(searchKeyword, "utf-8");
		}

		Document document = Jsoup
				.parse(new URL(url).openStream(), "utf-8", url);

		// Document document = connection.get();

		items(vo, model, document);

		model.addAttribute("vo", vo);

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("url=" + url);
			egovLogger.debug("document=" + document);
			egovLogger.debug("vo=" + vo);
			egovLogger.debug("model=" + model);
		}
	}

	// private void data(Map<String, Object> vo, ModelMap model,
	// Connection connection) throws Exception {
	//
	// connection
	// .data("ServiceKey",
	// "tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D");
	//
	// // // 페이지 번호
	// // String pageNo = (String) vo.get("pageNo");
	// //
	// // if (StringUtils.isEmpty(pageNo) == false) {
	// // connection.data("pageNo", pageNo);
	// // }
	// //
	// // // 페이지당레코드 수
	// // String numOfRows = (String) vo.get("numOfRows");
	// //
	// // if (StringUtils.isEmpty(numOfRows) == false) {
	// // connection.data("numOfRows", numOfRows);
	// // }
	// //
	// // // 검색분류코드
	// // String searchCondition = (String) vo.get("searchCondition");
	// //
	// // if (StringUtils.isEmpty(searchCondition) == false) {
	// // connection.data("searchCondition", searchCondition);
	// // }
	// //
	// // // 검색키워드
	// // String searchKeyword = (String) vo.get("searchKeyword");
	// //
	// // if (StringUtils.isEmpty(searchKeyword) == false) {
	// // connection.data("searchKeyword", searchKeyword);
	// // }
	// }

	private void items(Map<String, Object> vo, ModelMap model, Document document)
			throws Exception {

		Elements itemElements = document.select("item");

		List<EgovMap> items = new ArrayList<EgovMap>();

		for (Element itemElement : itemElements) {
			EgovMap item = new EgovMap();

			// 도로명주소
			item.put("address", itemElement.select("address").text());

			// CCTV설치여부
			item.put("cctv", itemElement.select("cctv").text());

			// 위도
			item.put("latitude", itemElement.select("latitude").text());

			// 경도
			item.put("longitude", itemElement.select("longitude").text());

			// 관할경찰서명
			item.put("managecop", itemElement.select("managecop").text());

			// 관리기관명
			item.put("manageinstitution",
					itemElement.select("manageinstitution").text());

			// 보호구역 일련번호
			item.put("ntatcseq", itemElement.select("ntatcseq").text());

			// 데이터기준일
			item.put("regdttm", itemElement.select("regdttm").text());

			// 시설종류
			item.put("section", itemElement.select("section").text());

			// 관리기관연락처
			item.put("tel", itemElement.select("tel").text());

			// 시설명
			item.put("title", itemElement.select("title").text());

			items.add(item);
		}

		model.addAttribute("items", items);
	}

	@Override
	public void getKidSafeDaejeon(Map<String, Object> vo, ModelMap model)
			throws Exception {

		String url = "http://data.daejeon.go.kr/openapi-data/service/ rest/kidSafe/kidSafeDaejeonService/kidSafeAreaDaejeonView";

		url += "?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

		// 시설일련번호
		url += "&ntatcSeq=" + vo.get("ntatcSeq");

		Document document = Jsoup
				.parse(new URL(url).openStream(), "utf-8", url);

		items(vo, model, document);

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("vo=" + vo);
			egovLogger.debug("model=" + model);
			egovLogger.debug("url=" + url);
			egovLogger.debug("document=" + document);
		}
	}

}
