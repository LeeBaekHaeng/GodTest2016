package epeople.admin.cmm.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

@Controller
public class AdminIndexController {

	@RequestMapping("/admin/index.do")
	public String index(ModelMap model) {
		LoginVO authenticatedUser = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("authenticatedUser", authenticatedUser);
		return "epeople/admin/cmm/EgovUnitMain";
	}

}
