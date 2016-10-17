package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0103Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForest0103Controller {

	@Autowired
	private GodsoftMblDatagokrForest0103Service godsoftDataGoKrForest0103Service;

	/**
	 * 산림자원 > 백두대간보호구역도
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bkmntBdOpenAPI.mdo")
	public String bkmntBdOpenAPI(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrForest0103Service.bkmntBdOpenAPI(vo, model);
		return "godsoft/mbl/datagokr/forest/bkmntBdOpenAPI";
	}

}
