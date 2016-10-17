package godsoft.datagokr.forest.web;

import godsoft.datagokr.forest.service.GodsoftDataGoKrForestService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftDataGoKrForestController {

	@Autowired
	private GodsoftDataGoKrForestService godsoftDataGoKrForestService;

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gdTrailInfoOpenAPISelectList.do")
	public String gdTrailInfoOpenAPISelectList(
			@RequestParam Map<String, Object> vo, ModelMap model)
			throws Exception {
		godsoftDataGoKrForestService.gdTrailInfoOpenAPISelectList(vo, model);
		return "godsoft/datagokr/forest/gdTrailInfoOpenAPISelectList";
	}

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 상세
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gdTrailInfoOpenAPISelect.do")
	public String gdTrailInfoOpenAPISelect(
			@RequestParam Map<String, Object> vo, ModelMap model)
			throws Exception {
		godsoftDataGoKrForestService.gdTrailInfoOpenAPISelect(vo, model);
		return "godsoft/datagokr/forest/gdTrailInfoOpenAPISelect";
	}

}
