package godsoft.mbl.datagokr.forest.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrForestService {

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

	/**
	 * 산림재해 산불발생위치도
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void frstFireOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception;

	/**
	 * 산악기상정보
	 * 
	 * @param data
	 * @param model
	 * @throws Exception
	 */
	void mountListSearch(Map<String, String> data, ModelMap model)
			throws Exception;

	/**
	 * 휴양문화 > 산림교육프로그램안내
	 * 
	 * @param data
	 * @param model
	 * @throws Exception
	 */
	void frstEduInfoOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception;

	/**
	 * 휴양문화 > 산정보
	 * 
	 * @param data
	 * @param model
	 * @throws Exception
	 */
	void mntInfoOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception;

	/**
	 * 휴양문화 > 숲에사는 식물정보
	 * 
	 * @param data
	 * @param model
	 * @throws Exception
	 */
	void fStoryOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception;

	/**
	 * 휴양문화 > 전통마을숲위치도
	 * 
	 * @param data
	 * @param model
	 * @throws Exception
	 */
	void traVllgFrstOpenAPI(Map<String, String> data, ModelMap model)
			throws Exception;

}
