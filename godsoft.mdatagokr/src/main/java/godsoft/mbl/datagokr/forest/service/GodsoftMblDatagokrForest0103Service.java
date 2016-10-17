package godsoft.mbl.datagokr.forest.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrForest0103Service {

	/**
	 * 산림자원 > 백두대간보호구역도
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void bkmntBdOpenAPI(Map<String, String> vo, ModelMap model)
			throws Exception;

}
