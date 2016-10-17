package godsoft.mbl.datagokr.forest.service.impl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0201Service;

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
public class GodsoftMblDatagokrForest0201ServiceImpl implements
		GodsoftMblDatagokrForest0201Service {

	@Override
	public void iSpecimenOpenAPI(Map<String, String> vo, ModelMap model)
			throws Exception {

		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/iSpecimenOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		// model.addAttribute("htmlTitle", "곤충표본");
		model.addAttribute("htmlTitle", "국가생물종 지식정보 > 곤충표본");

		model.addAttribute("vo", vo);
	}

	private void data(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		vo.put("key", "3690b5d3fe7b4fa79728b8dd2f6c6e34");

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
		result.put("searchWrd", resultElements.select("searchWrd").text());

		pSpecimen(vo, model, resultElements, result);

		model.addAttribute("result", result);

		paginationInfo(vo, model, result);
	}

	private void pSpecimen(Map<String, String> vo, ModelMap model,
			Elements resultElements, Map<String, Object> result)
			throws Exception {

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = resultElements.select("pSpecimen");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("name", element.select("name").text());
			item.put("scientificName", element.select("scientificName").text());
			item.put("spcmNo", element.select("spcmNo").text());
			item.put("ilstrNo", element.select("ilstrNo").text());
			item.put("lblClznNm", element.select("lblClznNm").text());
			item.put("clctLocation", element.select("clctLocation").text());
			item.put("clctDy", element.select("clctDy").text());
			item.put("clctName", element.select("clctName").text());
			item.put("keepLctn", element.select("keepLctn").text());
			item.put("locationNm", element.select("locationNm").text());
			item.put("spcmKind", element.select("spcmKind").text());
			item.put("prsrSte", element.select("prsrSte").text());
			item.put("sxulDiv", element.select("sxulDiv").text());
			item.put("bdyLngt", element.select("bdyLngt").text());
			item.put("wingLngt", element.select("wingLngt").text());
			item.put("clznHosl", element.select("clznHosl").text());

			items.add(item);
		}

		result.put("pSpecimen", items);
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
