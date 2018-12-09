package godsoft.datagokr.mafra.service.impl;

import godsoft.datagokr.mafra.service.GodsoftDatagokrMafraService;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftDatagokrMafraServiceImpl implements
		GodsoftDatagokrMafraService {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void Grid_20150407000000000218_1(Map<String, String> data,
			ModelMap model) throws Exception {
		model.addAttribute("data", data);

		String url = url(data, model);

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("url=" + url);
		}

		Connection connection = Jsoup.connect(url);

		connection.timeout(300000);

		Document document = connection.get();

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("document=" + document);
		}

		root(data, model, document);
		result(data, model, document);
		row(data, model, document);
	}

	public String url(Map<String, String> data, ModelMap model)
			throws Exception {
		StringBuffer sb = new StringBuffer();

		sb.append("http://data.mafra.go.kr:7080/openapi");

		// 발급받은 API_KEY
		sb.append("/d9f07145d07af2c99d85b6026de35c241b50e646a6269cf7e26c81a572725dde");

		// 요청파일 타입 xml, json
		sb.append("/xml");

		// OpenAPI 서비스 URL
		sb.append("/Grid_20150407000000000218_1");

		// 요청시작위치
		sb.append("/1");

		// 요청종료위치
		sb.append("/1000");

		// 지역
		// sb.append("/?AREA=");
		// sb.append(URLEncoder.encode(MapUtils.getString(data, "AREA", "충청"),
		// "utf-8"));

		String AREA = MapUtils.getString(data, "AREA");

		sb.append("/?AREA=");

		if (AREA == null) {
			// if (StringUtils.isEmpty(AREA) == true) {
			AREA = "충청";
			data.put("AREA", AREA);
		}

		sb.append(URLEncoder.encode(AREA, "utf-8"));

		// 목장명
		String FARM_NM = MapUtils.getString(data, "FARM_NM");

		// if (FARM_NM != null) {
		if (StringUtils.isEmpty(FARM_NM) == false) {
			sb.append("&FARM_NM=");
			sb.append(URLEncoder.encode(FARM_NM, "utf-8"));
		}

		return sb.toString();
	}

	private void root(Map<String, String> data, ModelMap model,
			Document document) throws Exception {
		model.addAttribute("totalCnt", document.select("totalCnt").text());
		model.addAttribute("startRow", document.select("startRow").text());
		model.addAttribute("endRow", document.select("endRow").text());

	}

	private void result(Map<String, String> data, ModelMap model,
			Document document) throws Exception {
		// EgovMap result = new EgovMap();
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("message", document.select("result > message").text());
		result.put("code", document.select("result > code").text());

		model.addAttribute("result", result);

	}

	private void row(Map<String, String> data, ModelMap model, Document document)
			throws Exception {
		Elements rowElements = document.select("row");

		// List<EgovMap> items = new ArrayList<EgovMap>();
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		for (Element gdTrailInfoElement : rowElements) {
			// EgovMap item = new EgovMap();
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("FARM_NM", gdTrailInfoElement.select("FARM_NM").text());
			item.put("FOND_DE", gdTrailInfoElement.select("FOND_DE").text());
			item.put("AREA", gdTrailInfoElement.select("AREA").text());
			item.put("HMPG", gdTrailInfoElement.select("HMPG").text());
			item.put("ROW_NUM", gdTrailInfoElement.select("ROW_NUM").text());
			item.put("FRAM_AR", gdTrailInfoElement.select("FRAM_AR").text());
			item.put("PRDCTN_QY", gdTrailInfoElement.select("PRDCTN_QY").text());
			item.put("RPRSNTV", gdTrailInfoElement.select("RPRSNTV").text());
			item.put("ADDR", gdTrailInfoElement.select("ADDR").text());
			item.put("BRD_LVSTCK_CO", gdTrailInfoElement
					.select("BRD_LVSTCK_CO").text());
			item.put("TLPHON_NO", gdTrailInfoElement.select("TLPHON_NO").text());
			item.put("FARM_INTRCN", gdTrailInfoElement.select("FARM_INTRCN")
					.text());

			items.add(item);
		}

		model.addAttribute("items", items);
	}

}
