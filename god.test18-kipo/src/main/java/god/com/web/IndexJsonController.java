package god.com.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import god.com.service.IndexVO;

@Controller
public class IndexJsonController {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/god/index/json.do")
	public View json(IndexVO vo, Model model) {

		egovLogger.debug("json");

		egovLogger.debug("indexVO=" + vo);
		egovLogger.debug("vo=" + vo.getParam1());
		egovLogger.debug("getParam1s=" + vo.getParam1s());
		egovLogger.debug("getParam2=" + vo.getParam2());
		egovLogger.debug("getParam2s=" + vo.getParam2s());

		model.addAttribute("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return new MappingJackson2JsonView();
	}

	@RequestMapping("/god/index/json2.do")
	public String json2(IndexVO vo, Model model) {

		egovLogger.debug("json2");

		egovLogger.debug("vo=" + vo);
		egovLogger.debug("getParam1=" + vo.getParam1());
		egovLogger.debug("getParam1s=" + vo.getParam1s());
		egovLogger.debug("getParam2=" + vo.getParam2());
		egovLogger.debug("getParam2s=" + vo.getParam2s());

		model.addAttribute("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return "godJsonView";
	}

	@RequestMapping("/god/index/json3.do")
	@ResponseBody
	public Map<String, Object> json3(IndexVO vo) {

		egovLogger.debug("json3");

		egovLogger.debug("vo=" + vo);
		egovLogger.debug("getParam1=" + vo.getParam1());
		egovLogger.debug("getParam1s=" + vo.getParam1s());
		egovLogger.debug("getParam2=" + vo.getParam2());
		egovLogger.debug("getParam2s=" + vo.getParam2s());

		Map<String, Object> model = new HashMap<>();
		model.put("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return model;
	}

	@RequestMapping("/god/index/json4.do")
	@ResponseBody
	public Map<String, Object> json4(@RequestBody Map<String, Object> vo) {

		egovLogger.debug("json3");

		egovLogger.debug("vo=" + vo);
		egovLogger.debug("getParam1=" + vo.get("param1"));
		egovLogger.debug("getParam1s=" + vo.get("param1s"));
		egovLogger.debug("getParam2=" + vo.get("param2"));
		egovLogger.debug("getParam2s=" + vo.get("param2s"));

		Map<String, Object> model = new HashMap<>();
		model.put("attributeName1", "속성명1");

		egovLogger.debug("model=" + model);

		return model;
	}

}
