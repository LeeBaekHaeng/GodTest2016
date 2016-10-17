package godsoft.rest.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GodRestController {

	@RequestMapping("/rest/selectList.do")
	public String selectList(ModelMap model) {
		model.addAttribute("items", "items");
		return "jsonView";
	}

}
