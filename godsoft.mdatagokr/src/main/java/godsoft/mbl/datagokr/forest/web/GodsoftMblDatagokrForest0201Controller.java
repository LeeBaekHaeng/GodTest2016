package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0201Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForest0201Controller {

	@Autowired
	private GodsoftMblDatagokrForest0201Service godsoftDataGoKrForest0201Service;

	/**
	 * 국가생물종 지식정보 > 곤충표본
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/iSpecimenOpenAPI.mdo")
	public String iSpecimenOpenAPI(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrForest0201Service.iSpecimenOpenAPI(vo, model);
		return "godsoft/mbl/datagokr/forest/iSpecimenOpenAPI";
	}

}
