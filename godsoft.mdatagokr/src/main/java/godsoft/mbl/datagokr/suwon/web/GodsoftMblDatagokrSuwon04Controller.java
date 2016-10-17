package godsoft.mbl.datagokr.suwon.web;

import godsoft.mbl.datagokr.suwon.service.GodsoftMblDatagokrSuwon04Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrSuwon04Controller {

	@Autowired
	private GodsoftMblDatagokrSuwon04Service godsoftMblDatagokrSuwon04Service;

	/**
	 * 수원시 불법주정차 CCTV
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cCTV.mdo")
	public String cCTV(@RequestParam Map<String, String> vo, ModelMap model)
			throws Exception {
		godsoftMblDatagokrSuwon04Service.cCTV(vo, model);
		return "godsoft/mbl/datagokr/suwon/cCTV";
	}

}
