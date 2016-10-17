package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0108Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForest0108Controller {

	@Autowired
	private GodsoftMblDatagokrForest0108Service godsoftDataGoKrForest0108Service;

	/**
	 * 산림자원 > 해외산림투자정보서비스
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tree.mdo")
	public String ecoFrstyOpenAPI(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrForest0108Service.tree(vo, model);
		return "godsoft/mbl/datagokr/forest/tree";
	}

}
