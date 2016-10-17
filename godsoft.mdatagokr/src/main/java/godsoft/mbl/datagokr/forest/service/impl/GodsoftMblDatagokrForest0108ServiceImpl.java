package godsoft.mbl.datagokr.forest.service.impl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0108Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftMblDatagokrForest0108ServiceImpl implements
		GodsoftMblDatagokrForest0108Service {

	@Override
	public void tree(Map<String, String> vo, ModelMap model) throws Exception {

		// String url = "http://www.ofiis.or.kr/openapi-data/service/rest/tree";
		String url = "http://www.ofiis.or.kr/openapi-data/service/rest/tree?serviceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		// model.addAttribute("htmlTitle", "해외 산림투자 정보서비스");
		model.addAttribute("htmlTitle", "산림자원 > 해외산림투자정보서비스");

		model.addAttribute("vo", vo);
	}

	private void data(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {
		String pageNo = MapUtils.getString(vo, "pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			vo.put("pageNo", "1");
		}

		String numOfRows = MapUtils.getString(vo, "numOfRows");
		if (StringUtils.isEmpty(numOfRows)) {
			vo.put("numOfRows", "10");
		}

		// data
		// vo.put("serviceKey",
		// "tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D");

		connection.data(vo);
	}

	private void document(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		Document document = connection.get();

		Map<String, Object> response = new HashMap<String, Object>();
		header(vo, model, document, response);
		body(vo, model, document, response);
		model.addAttribute("response", response);

		paginationInfo(vo, model, document, response);
	}

	private void header(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> response) throws Exception {

		Elements headerElements = document.select("response > header");

		Map<String, Object> header = new HashMap<String, Object>();

		header.put("resultCode", headerElements.select("resultCode").text());
		header.put("resultMsg", headerElements.select("resultMsg").text());

		response.put("header", header);
	}

	private void body(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> response) throws Exception {

		Elements bodyElements = document.select("response > body");

		Map<String, Object> body = new HashMap<String, Object>();

		bodyItems(vo, model, bodyElements, body);

		body.put("numOfRows", bodyElements.select("numOfRows").text());
		body.put("pageNo", bodyElements.select("pageNo").text());
		body.put("totalCount", bodyElements.select("totalCount").text());

		response.put("body", body);
	}

	private void bodyItems(Map<String, String> vo, ModelMap model,
			Elements bodyElements, Map<String, Object> body) throws Exception {

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = bodyElements.select("items > item");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("detail", element.select("detail").text());
			item.put("engNm", element.select("engNm").text());
			item.put("gnrNm", element.select("gnrNm").text());
			item.put("korNm", element.select("korNm").text());
			item.put("origin", element.select("origin").text());
			item.put("scfNm", element.select("scfNm").text());
			item.put("treeNo", element.select("treeNo").text());

			items.add(item);
		}

		body.put("items", items);
	}

	private void paginationInfo(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> response) throws Exception {

		@SuppressWarnings("unchecked")
		Map<String, Object> body = (Map<String, Object>) response.get("body");

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(MapUtils.getIntValue(vo, "pageNo"));
		paginationInfo.setRecordCountPerPage(MapUtils.getIntValue(vo,
				"numOfRows"));
		// paginationInfo.setRecordCountPerPage(MapUtils.getIntValue(vo,
		// "pageUnit"));
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(body,
				"totalCount"));

		model.addAttribute("paginationInfo", paginationInfo);
	}
}
