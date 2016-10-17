package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForestService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForestController {

	@Autowired
	private GodsoftMblDatagokrForestService godsoftDataGoKrForestService;

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gdTrailInfoOpenAPISelectList.mdo")
	public String gdTrailInfoOpenAPISelectList(
			@RequestParam Map<String, Object> vo, ModelMap model)
			throws Exception {
		godsoftDataGoKrForestService.gdTrailInfoOpenAPISelectList(vo, model);
		return "godsoft/mbl/datagokr/forest/gdTrailInfoOpenAPISelectList";
	}

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 상세
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gdTrailInfoOpenAPISelect.mdo")
	public String gdTrailInfoOpenAPISelect(
			@RequestParam Map<String, Object> vo, ModelMap model)
			throws Exception {
		godsoftDataGoKrForestService.gdTrailInfoOpenAPISelect(vo, model);
		return "godsoft/mbl/datagokr/forest/gdTrailInfoOpenAPISelect";
	}

	/**
	 * 산림재해 산불발생위치도
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forest/frstFireOpenAPI.mdo")
	public String frstFireOpenAPI(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.frstFireOpenAPI(data, model);
		return "godsoft/mbl/datagokr/forest/frstFireOpenAPI";
	}

	/**
	 * 산악기상정보
	 * 
	 * @param data
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forest/mountListSearch.mdo")
	public String mountListSearch(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.mountListSearch(data, model);
		return "godsoft/mbl/datagokr/forest/mountListSearch";
	}

	/**
	 * 휴양문화 > 산림교육프로그램안내
	 * 
	 * @param data
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/frstEduInfoOpenAPI.mdo")
	public String frstEduInfoOpenAPI(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.frstEduInfoOpenAPI(data, model);
		return "godsoft/mbl/datagokr/forest/frstEduInfoOpenAPI";
	}

	/**
	 * 휴양문화 > 산정보
	 * 
	 * @param data
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mntInfoOpenAPI.mdo")
	public String mntInfoOpenAPI(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.mntInfoOpenAPI(data, model);
		return "godsoft/mbl/datagokr/forest/mntInfoOpenAPI";
	}

	/**
	 * 휴양문화 > 숲에사는 식물정보
	 * 
	 * @param data
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fStoryOpenAPI.mdo")
	public String fStoryOpenAPI(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.fStoryOpenAPI(data, model);
		return "godsoft/mbl/datagokr/forest/fStoryOpenAPI";
	}

	/**
	 * 휴양문화 > 전통마을숲위치도
	 * 
	 * @param data
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/traVllgFrstOpenAPI.mdo")
	public String traVllgFrstOpenAPI(@RequestParam Map<String, String> data,
			ModelMap model) throws Exception {
		godsoftDataGoKrForestService.traVllgFrstOpenAPI(data, model);
		return "godsoft/mbl/datagokr/forest/traVllgFrstOpenAPI";
	}

}
