package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0101Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForest0101Controller {

	@Autowired
	private GodsoftMblDatagokrForest0101Service godsoftDataGoKrForest0101Service;

	/**
	 * 산림자원 > 경제림육성단지구역도
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ecoFrstyOpenAPI.mdo")
	public String ecoFrstyOpenAPI(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrForest0101Service.ecoFrstyOpenAPI(vo, model);
		return "godsoft/mbl/datagokr/forest/ecoFrstyOpenAPI";
	}

}
