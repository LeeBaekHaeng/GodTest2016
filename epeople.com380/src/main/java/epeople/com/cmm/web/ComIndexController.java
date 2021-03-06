package epeople.com.cmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class ComIndexController {

	@RequestMapping("/com/index.do")
	public String index(ModelMap model) {
		LoginVO authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("authenticatedUser", authenticatedUser);
		return "epeople/com/cmm/EgovUnitMain";
	}

}
