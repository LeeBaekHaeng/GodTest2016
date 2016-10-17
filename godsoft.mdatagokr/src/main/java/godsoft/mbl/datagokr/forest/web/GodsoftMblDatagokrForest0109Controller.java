package godsoft.mbl.datagokr.forest.web;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForest0109Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrForest0109Controller {

	@Autowired
	private GodsoftMblDatagokrForest0109Service godsoftDataGoKrForest0109Service;

	/**
	 * 산림자원 > 휴양림고시구역도
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rcrfrInfoOpenAPI.mdo")
	public String rcrfrInfoOpenAPI(@RequestParam Map<String, String> vo,
			ModelMap model) throws Exception {
		godsoftDataGoKrForest0109Service.rcrfrInfoOpenAPI(vo, model);
		return "godsoft/mbl/datagokr/forest/rcrfrInfoOpenAPI";
	}

}
