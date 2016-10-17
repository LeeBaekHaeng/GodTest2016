package godsoft.mbl.datagokr.suwon.web;

import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon03Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrSuwon03Controller {

	@Autowired
	private GodsoftMblDatagokrSuwon03Service godsoftMblDatagokrSuwon03Service;

	/**
	 * 경기도 수원시 교육강좌 목록
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/educationalCoursesList.mdo")
	public String educationalCoursesList(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon03Service.educationalCoursesList(vo, model);
		return "godsoft/mbl/datagokr/suwon/educationalCoursesList";
	}

	/**
	 * 경기도 수원시 문화관광 상세
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/educationalCourses.mdo")
	public String educationalCourses(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrSuwon03Service.educationalCourses(vo, model);
		return "godsoft/mbl/datagokr/suwon/educationalCourses";
	}

}
