package godsoft.mbl.datagokr.suwon.web;

import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon01Service;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrSuwon01Controller {

	@Autowired
	private GodsoftMblDatagokrSuwon01Service godsoftMblDatagokrSuwon01Service;

	/**
	 * 경기도 수원시 행사축제 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/festivalEventsList.mdo")
	public String festivalEventsList(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon01Service.festivalEventsList(vo, model);
		return "godsoft/mbl/datagokr/suwon/festivalEventsList";
	}

	/**
	 * 경기도 수원시 행사축제 상세
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/festivalEvents.mdo")
	public String festivalEvents(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon01Service.festivalEvents(vo, model);
		return "godsoft/mbl/datagokr/suwon/festivalEvents";
	}

	@RequestMapping("/url.mdo")
	public void naver(@RequestParam String spec, HttpServletResponse response)
			throws Exception {
		// String spec =
		// "http://openapi.map.naver.com/api/geocode?key=acadc19d58314c61c3ce90f95eca41fc&encoding=utf-8&coord=latlng&output=json&query=%EA%B2%BD%EA%B8%B0%EB%8F%84%20%EC%84%B1%EB%82%A8%EC%8B%9C%20%EB%B6%84%EB%8B%B9%EA%B5%AC%20%EC%A0%95%EC%9E%90%EB%8F%99%20178-1";

		URL url = new URL(spec);

		// System.out.println(IOUtils.toString(url.openStream()));

		IOUtils.copy(url.openStream(), response.getOutputStream());
	}

}
