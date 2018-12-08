package god.com.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import god.com.service.IndexVO;

@Controller
public class IndexController {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/god/index.do")
	public String index(IndexVO indexVO, Model model) {

		egovLogger.debug("index");

		egovLogger.debug("indexVO=" + indexVO);
		egovLogger.debug("getParam1=" + indexVO.getParam1());
		egovLogger.debug("getParam1s=" + indexVO.getParam1s());

		model.addAttribute("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return "god/com/index";
	}

}
