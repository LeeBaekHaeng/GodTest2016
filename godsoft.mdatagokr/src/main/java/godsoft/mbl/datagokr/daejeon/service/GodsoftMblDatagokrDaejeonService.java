package godsoft.mbl.datagokr.daejeon.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrDaejeonService {

	/**
	 * 대전광역시 어린이보호구역 목록조회
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void getKidSafeDaejeonList(Map<String, Object> vo, ModelMap model)
			throws Exception;

	/**
	 * 대전광역시 어린이보호구역 상세조회
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void getKidSafeDaejeon(Map<String, Object> vo, ModelMap model)
			throws Exception;

}
