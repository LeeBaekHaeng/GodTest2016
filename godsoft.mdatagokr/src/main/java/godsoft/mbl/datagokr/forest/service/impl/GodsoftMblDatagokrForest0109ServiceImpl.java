package godsoft.mbl.datagokr.forest.service.impl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0109Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftMblDatagokrForest0109ServiceImpl implements
		GodsoftMblDatagokrForest0109Service {

	@Override
	public void rcrfrInfoOpenAPI(Map<String, String> vo, ModelMap model)
			throws Exception {

		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/rcrfrInfoOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		// model.addAttribute("htmlTitle", "휴양림고시구역도");
		model.addAttribute("htmlTitle", "산림자원 > 휴양림고시구역도");

		model.addAttribute("vo", vo);
	}

	private void data(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		vo.put("key", "bcb3303eeaac4ea9b66f9a6d74f83530");

		vo.put("pageUnit", MapUtils.getString(vo, "pageUnit", "10"));

		vo.put("pageIndex", MapUtils.getString(vo, "pageIndex", "1"));

		connection.data(vo);
	}

	private void document(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		Document document = connection.get();

		Map<String, Object> result = new HashMap<String, Object>();

		Elements resultElements = document.select("result");

		result.put("key", resultElements.select("key").text());
		result.put("totalCnt", resultElements.select("totalCnt").text());
		result.put("pageUnit", resultElements.select("pageUnit").text());
		result.put("pageIndex", resultElements.select("pageIndex").text());
		result.put("searchRcrfrNm", resultElements.select("searchRcrfrNm")
				.text());

		rcrfrInfo(vo, model, resultElements, result);

		model.addAttribute("result", result);

		paginationInfo(vo, model, result);
	}

	private void rcrfrInfo(Map<String, String> vo, ModelMap model,
			Elements resultElements, Map<String, Object> result)
			throws Exception {

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = resultElements.select("rcrfrInfo");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("rcrfrNm", element.select("rcrfrNm").text());
			item.put("ctprvNm", element.select("ctprvNm").text());
			item.put("sgngNm", element.select("sgngNm").text());
			item.put("emndnNm", element.select("emndnNm").text());
			item.put("liNm", element.select("liNm").text());
			item.put("ltnoNm", element.select("ltnoNm").text());
			item.put("ownerNm", element.select("ownerNm").text());

			items.add(item);
		}

		result.put("rcrfrInfo", items);
	}

	private void paginationInfo(Map<String, String> vo, ModelMap model,
			Map<String, Object> result) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(MapUtils.getIntValue(vo, "pageIndex"));
		paginationInfo.setRecordCountPerPage(MapUtils.getIntValue(vo,
				"pageUnit"));
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(result,
				"totalCnt"));

		model.addAttribute("paginationInfo", paginationInfo);
	}

}
