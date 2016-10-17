package godsoft.mbl.datagokr.gwangju.web;

import godsoft.mbl.datagokr.gwangju.service.GodsoftDatagokrGwangjuService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftDatagokrGwangjuController {

	@Autowired
	private GodsoftDatagokrGwangjuService godsoftDatagokrGwangjuService;

	/**
	 * 광주광역시 의료기관 현황 > 의료기관 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGwangju001List.mdo")
	public String selectGwangju001List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftDatagokrGwangjuService.selectGwangju001List(vo, model);
		return "godsoft/mbl/datagokr/gwangju/selectGwangju001List";
	}

	/**
	 * 광주광역시 의료기관 현황 > 정신보건시설 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGwangju002List.mdo")
	public String selectGwangju002List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftDatagokrGwangjuService.selectGwangju002List(vo, model);
		return "godsoft/mbl/datagokr/gwangju/selectGwangju002List";
	}

	/**
	 * 광주광역시 의료기관 현황 > 의약단체 현황(2015년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGwangju003List.mdo")
	public String selectGwangju003List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftDatagokrGwangjuService.selectGwangju003List(vo, model);
		return "godsoft/mbl/datagokr/gwangju/selectGwangju003List";
	}

	/**
	 * 광주광역시 의료기관 현황 > 산후조리원 현황(2014년) CSV
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectGwangju004List.mdo")
	public String selectGwangju004List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftDatagokrGwangjuService.selectGwangju004List(vo, model);
		return "godsoft/mbl/datagokr/gwangju/selectGwangju004List";
	}

}
