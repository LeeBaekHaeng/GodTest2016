package godsoft.mbl.datagokr.forest.service.impl;

import egovframework.rte.fdl.string.EgovDateUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForestService;

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
public class GodsoftMblDatagokrForestServiceImpl implements
		GodsoftMblDatagokrForestService {

	@Override
	public void gdTrailInfoOpenAPISelectList(Map<String, Object> vo,
			ModelMap model) throws Exception {

		// String pageUnit = (String) vo.get("pageUnit");
		// if (StringUtils.isEmpty(pageUnit) == true) {
		// vo.put("pageUnit", "1000");
		// }

		String searchArNm = (String) vo.get("searchArNm");
		// if (StringUtils.isEmpty(searchArNm) == true) {
		if (searchArNm == null) {
			vo.put("searchArNm", "서울");
			// vo.put("searchArNm", "충북");
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

	@Override
	public void frstFireOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception {
		data(data, model);

		// http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/frstFireOpenAPI.do?key=bfbec0a11ce346c990f680565e75ad0c

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/frstFireOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("key", "bfbec0a11ce346c990f680565e75ad0c");

		connection.data(data);

		// document
		Document document = connection.post();

		// XStream xstream = new XStream();
		//
		// @SuppressWarnings("unchecked")
		// Map<String, Object> fromXML = (Map<String, Object>) xstream
		// .fromXML(document.toString());
		//
		// model.addAttribute("fromXML", fromXML);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("key", document.select("result > key").text());
		result.put("totalCnt", document.select("result > totalCnt").text());
		result.put("pageUnit", document.select("result > pageUnit").text());
		result.put("pageIndex", document.select("result > pageIndex").text());
		result.put("searchStDt", document.select("result > searchStDt").text());
		result.put("searchEdDt", document.select("result > searchEdDt").text());

		frstFireInfo(data, model, document, result);

		model.addAttribute("result", result);

		model.addAttribute("data", data);
	}

	private void data(Map<String, String> data, ModelMap model)
			throws Exception {
		// pageIndex
		String pageIndex = MapUtils.getString(data, "pageIndex");

		if (StringUtils.isEmpty(pageIndex)) {
			data.put("pageIndex", "1");
		}

		// searchStDt
		String searchStDt = MapUtils.getString(data, "searchStDt");

		if (StringUtils.isEmpty(searchStDt)) {
			data.put("searchStDt", EgovDateUtil.getCurrentYearAsInt() - 1
					+ "0101");
		}

		// searchEdDt
		String searchEdDt = MapUtils.getString(data, "searchEdDt");

		if (StringUtils.isEmpty(searchEdDt)) {
			data.put("searchEdDt", EgovDateUtil.getCurrentYearAsString()
					+ "1231");
		}
	}

	private void frstFireInfo(Map<String, String> data, ModelMap model,
			Document document, Map<String, Object> result) throws Exception {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("frstFireInfo");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("ocurDt", element.select("ocurDt").text());
			item.put("ocurYoil", element.select("ocurYoil").text());
			item.put("extingDt", element.select("extingDt").text());
			item.put("exintgTm", element.select("exintgTm").text());
			item.put("ocurGm", element.select("ocurGm").text());
			item.put("ocurDo", element.select("ocurDo").text());
			item.put("ocurSgg", element.select("ocurSgg").text());
			item.put("ocurEmd", element.select("ocurEmd").text());
			item.put("ocurRi", element.select("ocurRi").text());
			item.put("ocurJibun", element.select("ocurJibun").text());
			item.put("ownerSec", element.select("ownerSec").text());
			item.put("ocurCause", element.select("ocurCause").text());
			item.put("dmgArea", element.select("dmgArea").text());
			item.put("dmgMoney", element.select("dmgMoney").text());
			item.put("riskAvg", element.select("riskAvg").text());
			item.put("riskMax", element.select("riskMax").text());
			item.put("tempAvg", element.select("tempAvg").text());
			item.put("humidCurr", element.select("humidCurr").text());
			item.put("humidRel", element.select("humidRel").text());
			item.put("humidMin", element.select("humidMin").text());
			item.put("windMax", element.select("windMax").text());
			item.put("windAvg", element.select("windAvg").text());
			item.put("dirMax", element.select("dirMax").text());
			item.put("dirAvg", element.select("dirAvg").text());
			item.put("rainDays", element.select("rainDays").text());
			item.put("rainAmount", element.select("rainAmount").text());

			items.add(item);
		}

		result.put("frstFireInfo", items);
	}

	@Override
	public void mountListSearch(Map<String, String> data, ModelMap model)
			throws Exception {
		// connect
		String url = "http://know.kfri.go.kr/openapi/mtweather/mountListSearch.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("keyValue", "4511172577272987124348632365456364565829");

		connection.data(data);

		// document
		Document document = connection.get();

		Map<String, Object> metadata = new HashMap<String, Object>();

		Map<String, Object> resultSummary = new HashMap<String, Object>();
		resultSummary.put("totalCnt",
				document.select("metadata > resultSummary > totalCnt").text());
		metadata.put("resultSummary", resultSummary);

		Map<String, Object> inputData = new HashMap<String, Object>();
		inputData.put("keyValue",
				document.select("metadata > inputData > keyValue").text());
		inputData.put("localarea",
				document.select("metadata > inputData > localarea").text());
		inputData.put("obsid", document.select("metadata > inputData > obsid")
				.text());
		inputData
				.put("tm", document.select("metadata > inputData > tm").text());
		metadata.put("inputData", inputData);

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("metadata > outputData > items");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("obsid", element.attr("obsid"));
			item.put("obsname", element.select("obsname").text());

			String localarea = element.select("localarea").text();
			item.put("localarea", localarea);
			item.put("localareaNm", localareaNm(localarea));

			item.put("tm10m", element.select("tm10m").text());
			item.put("hm10m", element.select("hm10m").text());
			item.put("wd10m", element.select("wd10m").text());
			item.put("wd10mstr", element.select("wd10mstr").text());
			item.put("ws10m", element.select("ws10m").text());
			item.put("rn", element.select("rn").text());
			item.put("cprn", element.select("cprn").text());
			item.put("pa", element.select("pa").text());
			item.put("ts", element.select("ts").text());
			item.put("tm2m", element.select("tm2m").text());
			item.put("hm2m", element.select("hm2m").text());
			item.put("wd2m", element.select("wd2m").text());
			item.put("wd2mstr", element.select("wd2mstr").text());
			item.put("ws2m", element.select("ws2m").text());

			items.add(item);
		}

		Map<String, Object> outputData = new HashMap<String, Object>();
		outputData.put("items", items);
		metadata.put("outputData", outputData);

		model.addAttribute("metadata", metadata);

		model.addAttribute("data", data);
	}

	private String localareaNm(Object key) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "서울특별시");
		map.put("2", "세종특별자치시");
		map.put("3", "부산광역시");
		map.put("4", "대구광역시");
		map.put("5", "광주광역시");
		map.put("6", "인천광역시");
		map.put("7", "대전광역시");
		map.put("8", "울산광역시");
		map.put("9", "경기도");
		map.put("10", "강원도");
		map.put("11", "충청남도");
		map.put("12", "충청북도");
		map.put("13", "전라남도");
		map.put("14", "전라북도");
		map.put("15", "경상남도");
		map.put("16", "경상북도");
		map.put("17", "제주도");

		return map.get(key);
	}

	@Override
	public void frstEduInfoOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception {
		data.put("pageUnit", "1000");

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/frstEduInfoOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("key", "d0dc317ada5347c8adda46b898f2a3fe");

		connection.data(data);

		// document
		Document document = connection.get();

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("key", document.select("result > key").text());
		result.put("totalCnt", document.select("result > totalCnt").text());
		result.put("pageUnit", document.select("result > pageUnit").text());
		result.put("pageIndex", document.select("result > pageIndex").text());
		result.put("eduType", document.select("result > eduType").text());
		result.put("searchTitl", document.select("result > searchTitl").text());
		result.put("searchCont", document.select("result > searchCont").text());
		result.put("searchStDt", document.select("result > searchStDt").text());
		result.put("searchEdDt", document.select("result > searchEdDt").text());

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("result > ecoFrstyInfo");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("title", element.select("title").text());
			item.put("cont", element.select("cont").text());
			item.put("post", element.select("post").text());
			item.put("rgter", element.select("rgter").text());
			item.put("rgDt", element.select("rgDt").text());

			items.add(item);
		}

		result.put("ecoFrstyInfo", items);

		model.addAttribute("result", result);

		model.addAttribute("data", data);
	}

	@Override
	public void mntInfoOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception {
		String pageIndex = MapUtils.getString(data, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			data.put("pageIndex", "1");
		}

		// &searchWrd=%EA%B3%84%EB%A3%A1%EC%82%B0

		// String searchWrd = MapUtils.getString(data, "searchWrd");
		// if (StringUtils.isEmpty(searchWrd) == false) {
		// data.put("searchWrd", URLEncoder.encode(searchWrd, "UTF-8"));
		// }

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/mntInfoOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("key", "7cab6e8904ec429193ec4310ceb84395");

		connection.data(data);

		// document
		Document document = connection.post();

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("key", document.select("result > key").text());
		result.put("totalCnt", document.select("result > totalCnt").text());
		result.put("pageUnit", document.select("result > pageUnit").text());
		result.put("pageIndex", document.select("result > pageIndex").text());
		result.put("searchWrd", document.select("result > searchWrd").text());

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("result > mntInfo");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("mntiListNo", element.select("mntiListNo").text());
			item.put("mntiTop", element.select("mntiTop").text());
			item.put("mntiName", element.select("mntiName").text());
			item.put("mntiSname", element.select("mntiSname").text());
			item.put("mntiAdd", element.select("mntiAdd").text());
			item.put("mntiHigh", element.select("mntiHigh").text());
			item.put("mntiAdmin", element.select("mntiAdmin").text());
			item.put("mntiAdminNum", element.select("mntiAdminNum").text());
			item.put("mntiSummary", element.select("mntiSummary").text());
			item.put("mntiDetails", element.select("mntiDetails").text());

			Elements imageElements = element.select("image");
			List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
			for (Element imageElement : imageElements) {
				Map<String, Object> image = new HashMap<String, Object>();
				image.put("fileNo", imageElement.select("fileNo").text());
				image.put("fileName", imageElement.select("fileName").text());
				image.put("filePath", imageElement.select("filePath").text());
				images.add(image);
			}
			item.put("image", images);

			items.add(item);
		}

		result.put("mntInfo", items);

		model.addAttribute("result", result);

		model.addAttribute("data", data);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo
				.setCurrentPageNo(MapUtils.getIntValue(data, "pageIndex"));
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(result,
				"totalCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
	}

	@Override
	public void fStoryOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception {
		String pageIndex = MapUtils.getString(data, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			data.put("pageIndex", "1");
		}

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/fStoryOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("key", "e20098ba693f4054952a64383799009f");

		connection.data(data);

		// document
		Document document = connection.post();

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("key", document.select("result > key").text());
		result.put("totalCnt", document.select("result > totalCnt").text());
		result.put("pageUnit", document.select("result > pageUnit").text());
		result.put("pageIndex", document.select("result > pageIndex").text());
		result.put("searchWrd", document.select("result > searchWrd").text());

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("result > FStory");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("fsListNo", element.select("fsListNo").text());
			item.put("fsGbn", element.select("fsGbn").text());
			item.put("fsKName", element.select("fsKName").text());
			item.put("fsEName", element.select("fsEName").text());
			item.put("fsGuide", element.select("fsGuide").text());
			item.put("scientificName", element.select("scientificName").text());
			item.put("fsClassName", element.select("fsClassName").text());
			item.put("fsInhabit", element.select("fsInhabit").text());
			item.put("fsLifeTime", element.select("fsLifeTime").text());
			item.put("fsStory", element.select("fsStory").text());
			item.put("fsOffer", element.select("fsOffer").text());
			item.put("fsRegister", element.select("fsRegister").text());

			Elements imageElements = element.select("image");
			List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
			for (Element imageElement : imageElements) {
				Map<String, Object> image = new HashMap<String, Object>();
				image.put("fileNo", imageElement.select("fileNo").text());
				image.put("fileName", imageElement.select("fileName").text());
				image.put("filePath", imageElement.select("filePath").text());
				images.add(image);
			}
			item.put("image", images);

			items.add(item);
		}

		result.put("FStory", items);

		model.addAttribute("result", result);

		model.addAttribute("data", data);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo
				.setCurrentPageNo(MapUtils.getIntValue(data, "pageIndex"));
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(result,
				"totalCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		// model.addAttribute("htmlTitle", "숲에사는 식물정보");
		model.addAttribute("htmlTitle", "휴양문화 > 숲에사는 식물정보");
	}

	@Override
	public void traVllgFrstOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception {
		String pageIndex = MapUtils.getString(data, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			data.put("pageIndex", "1");
		}

		// connect
		String url = "http://www.forest.go.kr/newkfsweb/kfi/kfs/openapi/traVllgFrstOpenAPI.do";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// data
		data.put("key", "cabbbfe72f0e45299e37eaebd19f1cbf");

		connection.data(data);

		// document
		Document document = connection.post();

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("key", document.select("result > key").text());
		result.put("totalCnt", document.select("result > totalCnt").text());
		result.put("pageUnit", document.select("result > pageUnit").text());
		result.put("pageIndex", document.select("result > pageIndex").text());
		result.put("searchVllgNm", document.select("result > searchVllgNm")
				.text());
		result.put("searchPlcNm", document.select("result > searchPlcNm")
				.text());

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

		Elements elements = document.select("result > traVllgFrstInfo");

		for (Element element : elements) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("traVllgFrstNm", element.select("traVllgFrstNm").text());
			item.put("matrlNmPlc", element.select("matrlNmPlc").text());
			item.put("mainFoftrNm", element.select("mainFoftrNm").text());
			item.put("mainFrtpNm", element.select("mainFrtpNm").text());
			item.put("histrExmnnCont", element.select("histrExmnnCont").text());
			item.put("clturExmnnCont", element.select("clturExmnnCont").text());
			item.put("zoneArea", element.select("zoneArea").text());

			items.add(item);
		}

		result.put("traVllgFrstInfo", items);

		model.addAttribute("result", result);

		model.addAttribute("data", data);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo
				.setCurrentPageNo(MapUtils.getIntValue(data, "pageIndex"));
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(1);
		paginationInfo.setTotalRecordCount(MapUtils.getIntValue(result,
				"totalCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		// model.addAttribute("htmlTitle", "전통마을숲위치도");
		model.addAttribute("htmlTitle", "휴양문화 > 전통마을숲위치도");
	}

}
