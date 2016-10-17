package godsoft.mbl.datagokr.daejeon.donggu.web;

import godsoft.mbl.datagokr.daejeon.donggu.service.impl.Donggu001CSV;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Donggu001Controller {

	@Autowired
	private Donggu001CSV donggu001csv;

	/**
	 * 대전광역시 동구 주차장정보
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectDonggu001List.mdo")
	public String selectDonggu001List(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		model.addAttribute("items", donggu001csv.selectDonggu001List(vo));

		return "godsoft/mbl/datagokr/daejeon/donggu/selectDonggu001List";
	}

}
