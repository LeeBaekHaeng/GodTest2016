package godsoft.mbl.datagokr.suwon.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrSuwon01Service {

	/**
	 * 경기도 수원시 행사축제 목록
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void festivalEventsList(Map<String, String> vo, ModelMap model)
			throws Exception;

	/**
	 * 경기도 수원시 행사축제 상세
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void festivalEvents(Map<String, String> vo, ModelMap model)
			throws Exception;

}
