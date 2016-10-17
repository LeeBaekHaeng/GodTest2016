package godsoft.mbl.datagokr.sejong.web;

import godsoft.mbl.datagokr.sejong.service.GodsoftDataGoKrSejongService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftDataGoKrSejongController {

	@Autowired
	private GodsoftDataGoKrSejongService godsoftDataGoKrSejongService;

	/**
	 * 세종시 모범음식점 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sejong/selectSejong01List.mdo")
	public String selectSejong01List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrSejongService.selectSejong01List(vo, model);
		return "godsoft/mbl/datagokr/sejong/selectSejong01List";
	}

}
