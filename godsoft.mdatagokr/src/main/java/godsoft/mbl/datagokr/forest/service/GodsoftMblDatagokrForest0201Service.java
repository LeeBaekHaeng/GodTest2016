package godsoft.mbl.datagokr.forest.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftMblDatagokrForest0201Service {

	/**
	 * 국가생물종 지식정보 > 곤충표본
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void iSpecimenOpenAPI(Map<String, String> vo, ModelMap model)
			throws Exception;

}
