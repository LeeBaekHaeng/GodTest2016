package godsoft.mbl.datagokr.gwangju.service;

import java.util.Map;

import org.springframework.ui.ModelMap;

public interface GodsoftDatagokrGwangjuService {

	/**
	 * 광주광역시 의료기관 현황 > 의료기관 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void selectGwangju001List(Map<String, Object> vo, ModelMap model)
			throws Exception;

	/**
	 * 광주광역시 의료기관 현황 > 정신보건시설 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void selectGwangju002List(Map<String, Object> vo, ModelMap model)
			throws Exception;

	/**
	 * 광주광역시 의료기관 현황 > 의약단체 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void selectGwangju003List(Map<String, Object> vo, ModelMap model)
			throws Exception;

	/**
	 * 광주광역시 의료기관 현황 > 산후조리원 현황(2014년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	void selectGwangju004List(Map<String, Object> vo, ModelMap model)
			throws Exception;

}
