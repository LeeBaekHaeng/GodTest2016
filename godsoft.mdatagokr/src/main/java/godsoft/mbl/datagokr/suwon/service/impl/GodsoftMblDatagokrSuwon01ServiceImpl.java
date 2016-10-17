package godsoft.mbl.datagokr.suwon.service.impl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon01Service;

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
public class GodsoftMblDatagokrSuwon01ServiceImpl implements
		GodsoftMblDatagokrSuwon01Service {

	@Override
	public void festivalEventsList(Map<String, String> vo, ModelMap model)
			throws Exception {

		// String url =
		// "http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEventsList";
		String url = "http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEventsList?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		model.addAttribute("htmlTitle", "경기도 수원시 행사축제 목록");

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

			item.put("CAST_MEMBERS", element.select("CAST_MEMBERS").text());
			item.put("CTR_INTRODUCTION", element.select("CTR_INTRODUCTION")
					.text());
			item.put("CTR_LOCATION", element.select("CTR_LOCATION").text());
			item.put("CTR_LOCATION_X", element.select("CTR_LOCATION_X").text());
			item.put("CTR_LOCATION_Y", element.select("CTR_LOCATION_Y").text());
			item.put("CTR_ORG", element.select("CTR_ORG").text());
			item.put("CTR_SEQ_NO", element.select("CTR_SEQ_NO").text());
			item.put("CTR_SUMMARY", element.select("CTR_SUMMARY").text());
			item.put("CTR_SUPERVISION", element.select("CTR_SUPERVISION")
					.text());
			item.put("CTR_TARGET", element.select("CTR_TARGET").text());
			item.put("CTR_WEEK", element.select("CTR_WEEK").text());
			item.put("CULTURE_NM", element.select("CULTURE_NM").text());
			item.put("DISCOUNT_INFO", element.select("DISCOUNT_INFO").text());
			item.put("DONG_CD", element.select("DONG_CD").text());
			item.put("END_DT", element.select("END_DT").text());
			item.put("END_TIME", element.select("END_TIME").text());
			item.put("FAX_NO", element.select("FAX_NO").text());
			item.put("GROUP_CD", element.select("GROUP_CD").text());
			item.put("GU_CD", element.select("GU_CD").text());
			item.put("HOMEPAGE_URL", element.select("HOMEPAGE_URL").text());
			item.put("OPEN_YN", element.select("OPEN_YN").text());
			item.put("ORG_SEQ_NO", element.select("ORG_SEQ_NO").text());
			item.put("RESERVE_URL", element.select("RESERVE_URL").text());
			item.put("SCALE_CD", element.select("SCALE_CD").text());
			item.put("START_DT", element.select("START_DT").text());
			item.put("START_TIME", element.select("START_TIME").text());
			item.put("TEL_NO", element.select("TEL_NO").text());
			item.put("THEME_CD", element.select("THEME_CD").text());
			item.put("THUMB_IMAGE", element.select("THUMB_IMAGE").text());
			item.put("THUMB_IMAGE_DESC", element.select("THUMB_IMAGE_DESC")
					.text());
			item.put("TICKET_PRICE", element.select("TICKET_PRICE").text());
			item.put("VIEW_AGE", element.select("VIEW_AGE").text());
			item.put("VIEW_URL", element.select("VIEW_URL").text());
			item.put("WEB_TOPON_USE_YN", element.select("WEB_TOPON_USE_YN")
					.text());

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
	public void festivalEvents(Map<String, String> vo, ModelMap model)
			throws Exception {
		new FestivalEvents(vo, model);
	}

	private class FestivalEvents {

		private FestivalEvents(Map<String, String> vo, ModelMap model)
				throws Exception {
			// http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEvents?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D&ctrSeqNo=1

			String url = "http://27.101.101.31/openapi-data/service/FestivalEvents/festivalEvents?ServiceKey=tI0v5%2FT30d3u4%2BCtrSld9G6gvMmUkDWxX1pQ09hdEY1EC6vQWlXjTGxmBabd%2B0k8upYXOoWqLN1nBI0Y158dVA%3D%3D";

			Connection connection = Jsoup.connect(url);

			// connection.timeout(300000);

			data(vo, model, connection);

			document(vo, model, connection);

			model.addAttribute("htmlTitle", "경기도 수원시 행사축제 상세");

			model.addAttribute("vo", vo);
		}

		private void data(Map<String, String> vo, ModelMap model,
				Connection connection) throws Exception {
			String ctrSeqNo = MapUtils.getString(vo, "ctrSeqNo");
			if (StringUtils.isEmpty(ctrSeqNo)) {
				vo.put("ctrSeqNo", "1");
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

			Elements elements = document.select("response > body > item");

			item.put("CAST_MEMBERS", elements.select("CAST_MEMBERS").text());
			item.put("CTR_INTRODUCTION", elements.select("CTR_INTRODUCTION")
					.text());
			item.put("CTR_LOCATION", elements.select("CTR_LOCATION").text());
			item.put("CTR_LOCATION_X", elements.select("CTR_LOCATION_X").text());
			item.put("CTR_LOCATION_Y", elements.select("CTR_LOCATION_Y").text());
			item.put("CTR_ORG", elements.select("CTR_ORG").text());
			item.put("CTR_SEQ_NO", elements.select("CTR_SEQ_NO").text());
			item.put("CTR_SUMMARY", elements.select("CTR_SUMMARY").text());
			item.put("CTR_SUPERVISION", elements.select("CTR_SUPERVISION")
					.text());
			item.put("CTR_TARGET", elements.select("CTR_TARGET").text());
			item.put("CTR_WEEK", elements.select("CTR_WEEK").text());
			item.put("CULTURE_NM", elements.select("CULTURE_NM").text());
			item.put("DISCOUNT_INFO", elements.select("DISCOUNT_INFO").text());
			item.put("DONG_CD", elements.select("DONG_CD").text());
			item.put("END_DT", elements.select("END_DT").text());
			item.put("END_TIME", elements.select("END_TIME").text());
			item.put("FAX_NO", elements.select("FAX_NO").text());
			item.put("GROUP_CD", elements.select("GROUP_CD").text());
			item.put("GU_CD", elements.select("GU_CD").text());
			item.put("HOMEPAGE_URL", elements.select("HOMEPAGE_URL").text());
			item.put("OPEN_YN", elements.select("OPEN_YN").text());
			item.put("ORG_SEQ_NO", elements.select("ORG_SEQ_NO").text());
			item.put("RESERVE_URL", elements.select("RESERVE_URL").text());
			item.put("SCALE_CD", elements.select("SCALE_CD").text());
			item.put("START_DT", elements.select("START_DT").text());
			item.put("START_TIME", elements.select("START_TIME").text());
			item.put("TEL_NO", elements.select("TEL_NO").text());
			item.put("THEME_CD", elements.select("THEME_CD").text());
			item.put("THUMB_IMAGE", elements.select("THUMB_IMAGE").text());
			item.put("THUMB_IMAGE_DESC", elements.select("THUMB_IMAGE_DESC")
					.text());
			item.put("TICKET_PRICE", elements.select("TICKET_PRICE").text());
			item.put("VIEW_AGE", elements.select("VIEW_AGE").text());
			item.put("VIEW_URL", elements.select("VIEW_URL").text());
			item.put("WEB_TOPON_USE_YN", elements.select("WEB_TOPON_USE_YN")
					.text());

			model.addAttribute("item", item);
		}

	}

}
