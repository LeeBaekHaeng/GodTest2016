package godsoft.mbl.datagokr.suwon.web;

import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon02Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrSuwon02Controller {

	@Autowired
	private GodsoftMblDatagokrSuwon02Service godsoftMblDatagokrSuwon02Service;

	/**
	 * 경기도 수원시 문화관광 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cultureTourismList.mdo")
	public String cultureTourismList(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon02Service.cultureTourismList(vo, model);
		return "godsoft/mbl/datagokr/suwon/cultureTourismList";
	}

	/**
	 * 경기도 수원시 문화관광 상세
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cultureTourism.mdo")
	public String cultureTourism(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon02Service.cultureTourism(vo, model);
		return "godsoft/mbl/datagokr/suwon/cultureTourism";
	}

}
