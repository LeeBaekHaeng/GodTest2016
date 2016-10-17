package godsoft.mbl.datagokr.suwon.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrSuwon02Service {

	/**
	 * 경기도 수원시 문화관광 목록
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void cultureTourismList(Map<String, String> vo, ModelMap model)
			throws Exception;

	/**
	 * 경기도 수원시 문화관광 상세
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void cultureTourism(Map<String, String> vo, ModelMap model)
			throws Exception;

}
