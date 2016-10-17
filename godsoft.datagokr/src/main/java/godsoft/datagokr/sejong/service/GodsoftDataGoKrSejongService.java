package godsoft.datagokr.sejong.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftDataGoKrSejongService {

	/**
	 * 세종시 모범음식점 목록
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void selectSejong01List(Map<String, Object> vo, ModelMap model)
			throws Exception;

}
