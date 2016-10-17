package godsoft.mbl.datagokr.mafra.web;

import godsoft.mbl.datagokr.mafra.service.GodsoftDatagokrMafraService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftDatagokrMafraController {

	@Autowired
	private GodsoftDatagokrMafraService godsoftDatagokrMafraService;

	/**
	 * 낙농체험 목장 정보(TI_MAFRA_EXPRN_FARM_INFO)
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/Grid_20150407000000000218_1.mdo")
	public String Grid_20150407000000000218_1(
			@RequestParam Map<String, String> data, ModelMap model)
			throws Exception {
		godsoftDatagokrMafraService.Grid_20150407000000000218_1(data, model);
		return "godsoft/mbl/datagokr/mafra/Grid_20150407000000000218_1";
	}

}
