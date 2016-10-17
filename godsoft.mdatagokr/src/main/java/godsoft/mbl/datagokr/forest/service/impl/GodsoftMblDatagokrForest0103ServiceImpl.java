package godsoft.mbl.datagokr.forest.service.impl;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0103Service;

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
public class GodsoftMblDatagokrForest0103ServiceImpl implements
		GodsoftMblDatagokrForest0103Service {

	@Override
	public void bkmntBdOpenAPI(Map<String, String> vo, ModelMap model)
			throws Exception {

		model.addAttribute("cmmnDetailCodes", getCmmnDetailCodes());

		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/bkmntBdOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		data(vo, model, connection);

		document(vo, model, connection);

		// model.addAttribute("htmlTitle", "백두대간 보호구역도");
		model.addAttribute("htmlTitle", "산림자원 > 백두대간보호구역도");

		model.addAttribute("vo", vo);
	}

	private void data(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {
		String pageIndex = MapUtils.getString(vo, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			vo.put("pageIndex", "1");
		}

		vo.put("key", "eea8c48391784796ad3f30655835d5ff");

		connection.data(vo);
	}

	private void document(Map<String, String> vo, ModelMap model,
			Connection connection) throws Exception {

		Document document = connection.post();

		Map<String, Object> result = new HashMap<String, Object>();
		result(vo, model, document, result);
		items(vo, model, document, result);
		model.addAttribute("result", result);

		paginationInfo(vo, model, document, result);
	}

	private void result(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> result) throws Exception {

		Elements resultElements = document.select("result");

		result.put("key", resultElements.select("key").text());
		result.put("totalCnt", resultElements.select("totalCnt").text());
		result.put("pageUnit", resultElements.select("pageUnit").text());
		result.put("pageIndex", resultElements.select("pageIndex").text());

		String bdType = resultElements.select("bdType").text();
		result.put("bdType", bdType);

		@SuppressWarnings("unchecked")
		List<CmmnDetailCode> cmmnDetailCodes = (List<CmmnDetailCode>) model
				.get("cmmnDetailCodes");
		for (CmmnDetailCode item : cmmnDetailCodes) {
			if (item.getCode().equals(bdType)) {
				result.put("bdTypeNm", item.getCodeNm());
				break;
			}
		}

		result.put("searchSdNm", resultElements.select("searchSdNm").text());
	}

	private void items(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> result) throws Exception {

		String itemsTagName = "ecoFrstyInfo";

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("result > " + itemsTagName);

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			// 보호구역 결과 파라미터 ( Response Parameter )
			item.put("bkmntZone", element.select("bkmntZone").text());
			item.put("snNm", element.select("snNm").text());
			item.put("mapNm", element.select("mapNm").text());

			// 마루금 결과 파라미터 ( Response Parameter )
			item.put("bkmntSect", element.select("bkmntSect").text());

			items.add(item);
		}

		result.put(itemsTagName, items);
	}

	private void paginationInfo(Map<String, String> vo, ModelMap model,
			Document document, Map<String, Object> result) throws Exception {

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(MapUtils.getIntValue(vo, "pageIndex"));
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(result,
				"totalCnt"));

		model.addAttribute("paginationInfo", paginationInfo);
	}

	private List<CmmnDetailCode> getCmmnDetailCodes() {
		List<CmmnDetailCode> items = new ArrayList<CmmnDetailCode>();

		CmmnDetailCode item = new CmmnDetailCode();
		item.setCode("100");
		item.setCodeNm("보호구역");
		items.add(item);

		item = new CmmnDetailCode();
		item.setCode("200");
		item.setCodeNm("마루금");
		items.add(item);

		return items;
	}

}
