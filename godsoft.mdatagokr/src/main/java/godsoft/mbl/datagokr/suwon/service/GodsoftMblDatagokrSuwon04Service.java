package godsoft.mbl.datagokr.suwon.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrSuwon04Service {

	/**
	 * 수원시 불법주정차 CCTV
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void cCTV(Map<String, String> vo, ModelMap model) throws Exception;

}
