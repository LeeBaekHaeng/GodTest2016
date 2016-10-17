package godsoft.mbl.datagokr.suwon.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrSuwon03Service {

	/**
	 * 경기도 수원시 교육강좌 목록
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void educationalCoursesList(Map<String, String> vo, ModelMap model)
			throws Exception;

	/**
	 * 경기도 수원시 문화관광 상세
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void educationalCourses(Map<String, String> vo, ModelMap model)
			throws Exception;

}
