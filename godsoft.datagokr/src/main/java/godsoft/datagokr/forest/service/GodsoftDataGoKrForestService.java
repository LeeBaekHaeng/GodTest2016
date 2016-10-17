package godsoft.datagokr.forest.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftDataGoKrForestService {

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 목록
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void gdTrailInfoOpenAPISelectList(Map<String, Object> vo, ModelMap model)
			throws Exception;

	/**
	 * 산림청 명산등산로(산림공간정보 1:25000) 상세
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void gdTrailInfoOpenAPISelect(Map<String, Object> vo, ModelMap model)
			throws Exception;

}
