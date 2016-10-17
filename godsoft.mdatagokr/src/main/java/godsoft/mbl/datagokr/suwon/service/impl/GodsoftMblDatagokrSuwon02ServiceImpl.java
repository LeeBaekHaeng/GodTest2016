package godsoft.mbl.datagokr.suwon.service.impl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon02Service;

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
public class GodsoftMblDatagokrSuwon02ServiceImpl implements
		GodsoftMblDatagokrSuwon02Service {

	@Override
	public void cultureTourismList(Map<String, String> vo, ModelMap model)
			throws Exception {

		// http://27.101.101.31/openapi-data/service/CultureTourism/cultureTourismList?pageNo=1&numOfRows=10&ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D
		String url = "http://27.101.101.31/openapi-data/service/CultureTourism/cultureTourismList?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		model.addAttribute("htmlTitle", "경기도 수원시 문화관광 목록");

		model.addAttribute("vo", vo);
	}

	private void data(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {
		String pageNo = MapUtils.getString(vo, "pageNo");
		if (StringUtils.isEmpty(pageNo)) {
			vo.put("pageNo", "1");
		}

		connection.data(vo);
	}

	private void document(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		Document document = connection.get();
		// Document document = connection.post();

		Map<String, Object> response = new HashMap<String, Object>();
		header(vo, model, document, response);
		body(vo, model, document, response);
		items(vo, model, document, response);
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

		Elements headerElements = document.select("response > body");

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("numOfRows", headerElements.select("numOfRows").text());
		body.put("pageNo", headerElements.select("pageNo").text());
		body.put("totalCount", headerElements.select("totalCount").text());

		response.put("body", body);
	}

	private void items(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> response) throws Exception {

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("response > body > items > item");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("ADDR1", element.select("ADDR1").text());
			item.put("ADDR2", element.select("ADDR2").text());
			item.put("ADDR_CODE", element.select("ADDR_CODE").text());
			item.put("AGREE_PERSON", element.select("AGREE_PERSON").text());
			item.put("ARTISAN", element.select("ARTISAN").text());
			item.put("BUS_COND", element.select("BUS_COND").text());
			item.put("BUS_TIME", element.select("BUS_TIME").text());
			item.put("CLOSE_DAY_INFO", element.select("CLOSE_DAY_INFO").text());
			item.put("CONTENT", element.select("CONTENT").text());
			item.put("EPOCH", element.select("EPOCH").text());
			item.put("FACEBOOK_URL", element.select("FACEBOOK_URL").text());
			item.put("FAX_NO", element.select("FAX_NO").text());
			item.put("FILE_SEQ", element.select("FILE_SEQ").text());
			item.put("HOMEPAGE_URL", element.select("HOMEPAGE_URL").text());
			item.put("LOCATION_X", element.select("LOCATION_X").text());
			item.put("LOCATION_Y", element.select("LOCATION_Y").text());
			item.put("ME2DAY_URL", element.select("ME2DAY_URL").text());
			item.put("MOD_DT", element.select("MOD_DT").text());
			item.put("MST_SEQ_NO", element.select("MST_SEQ_NO").text());
			item.put("PARKING_INFO", element.select("PARKING_INFO").text());
			item.put("POINT_DT", element.select("POINT_DT").text());
			item.put("POINT_NUM", element.select("POINT_NUM").text());
			item.put("PRODUCTS_NM", element.select("PRODUCTS_NM").text());
			item.put("REG_DT", element.select("REG_DT").text());
			item.put("SEAT", element.select("SEAT").text());
			item.put("SUMMARY", element.select("SUMMARY").text());
			item.put("TITLE", element.select("TITLE").text());
			item.put("TWITTER_URL", element.select("TWITTER_URL").text());
			item.put("USE_YN", element.select("USE_YN").text());
			item.put("VIEW_URL", element.select("VIEW_URL").text());
			item.put("YOZM_URL", element.select("YOZM_URL").text());
			item.put("ZIP_CODE", element.select("ZIP_CODE").text());

			items.add(item);
		}

		response.put("items", items);
	}

	private void paginationInfo(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> response) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(MapUtils.getIntValue(vo, "pageNo"));
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(1);

		@SuppressWarnings("unchecked")
		Map<String, Object> body = (Map<String, Object>) response.get("body");

		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(body,
				"totalCount"));

		model.addAttribute("paginationInfo", paginationInfo);
	}

	@Override
	public void cultureTourism(Map<String, String> vo, ModelMap model)
			throws Exception {
		new CultureTourism(vo, model);
	}

	private class CultureTourism {

		private CultureTourism(Map<String, String> vo, ModelMap model)
				throws Exception {
			// http://27.101.101.31/openapi-data/service/CultureTourism/cultureTourism?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D&mstSeqNo=1

			String url = "http://27.101.101.31/openapi-data/service/CultureTourism/cultureTourism?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

			Connection connection = Jsoup.connect(url);

			// connection.timeout(300000);

			data(vo, model, connection);

			document(vo, model, connection);

			model.addAttribute("htmlTitle", "경기도 수원시 문화관광 상세");

			model.addAttribute("vo", vo);
		}

		private void data(Map<String, String> vo, ModelMap model,
				Connection connection) throws Exception {
			String mstSeqNo = MapUtils.getString(vo, "mstSeqNo");
			if (StringUtils.isEmpty(mstSeqNo)) {
				vo.put("mstSeqNo", "1");
			}

			connection.data(vo);
		}

		private void document(Map<String, String> vo, ModelMap model,
				Connection connection) throws Exception {

			Document document = connection.get();
			// Document document = connection.post();

			item(vo, model, document);
		}

		private void item(Map<String, String> vo, ModelMap model,
				Document document) throws Exception {

			Map<String, Object> item = new HashMap<String, Object>();

			Elements element = document.select("response > body > item");

			item.put("ADDR1", element.select("ADDR1").text());
			item.put("ADDR2", element.select("ADDR2").text());
			item.put("ADDR_CODE", element.select("ADDR_CODE").text());
			item.put("AGREE_PERSON", element.select("AGREE_PERSON").text());
			item.put("ARTISAN", element.select("ARTISAN").text());
			item.put("BUS_COND", element.select("BUS_COND").text());
			item.put("BUS_TIME", element.select("BUS_TIME").text());
			item.put("CLOSE_DAY_INFO", element.select("CLOSE_DAY_INFO").text());
			item.put("CONTENT", element.select("CONTENT").text());
			item.put("EPOCH", element.select("EPOCH").text());
			item.put("FACEBOOK_URL", element.select("FACEBOOK_URL").text());
			item.put("FAX_NO", element.select("FAX_NO").text());
			item.put("FILE_SEQ", element.select("FILE_SEQ").text());
			item.put("HOMEPAGE_URL", element.select("HOMEPAGE_URL").text());
			item.put("LOCATION_X", element.select("LOCATION_X").text());
			item.put("LOCATION_Y", element.select("LOCATION_Y").text());
			item.put("ME2DAY_URL", element.select("ME2DAY_URL").text());
			item.put("MOD_DT", element.select("MOD_DT").text());
			item.put("MST_SEQ_NO", element.select("MST_SEQ_NO").text());
			item.put("PARKING_INFO", element.select("PARKING_INFO").text());
			item.put("POINT_DT", element.select("POINT_DT").text());
			item.put("POINT_NUM", element.select("POINT_NUM").text());
			item.put("PRODUCTS_NM", element.select("PRODUCTS_NM").text());
			item.put("REG_DT", element.select("REG_DT").text());
			item.put("SEAT", element.select("SEAT").text());
			item.put("SUMMARY", element.select("SUMMARY").text());
			item.put("TITLE", element.select("TITLE").text());
			item.put("TWITTER_URL", element.select("TWITTER_URL").text());
			item.put("USE_YN", element.select("USE_YN").text());
			item.put("VIEW_URL", element.select("VIEW_URL").text());
			item.put("YOZM_URL", element.select("YOZM_URL").text());
			item.put("ZIP_CODE", element.select("ZIP_CODE").text());

			model.addAttribute("item", item);
		}

	}

}
