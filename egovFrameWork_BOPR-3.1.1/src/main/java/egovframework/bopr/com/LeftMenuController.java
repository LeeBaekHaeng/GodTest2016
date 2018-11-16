package egovframework.bopr.com;

import egovframework.com.cmm.util.EgovUserDetailsHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeftMenuController {

	@Resource(name="egovUserDetailsHelper")
	private EgovUserDetailsHelper userDetailsHelper;
    /**
	 * LeftMenu를 설정한다.
	 * @param leftMenu String
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/bopr/LeftMenu.do")
    public String selectUserList(@RequestParam("url") String url,
    							 @RequestParam("leftMenu") String leftMenu,
    							 @RequestParam("openMenu") String openMenu,
    							 HttpServletRequest request)throws Exception {
    	//model.addAttribute("leftMenu", leftMenu);

    	//관리자여부확인
		if(userDetailsHelper.getAuthorities().contains("ROLE_ADMIN")){
			request.getSession().setAttribute("adminYn","Y");
		}else{
			request.getSession().setAttribute("adminYn","N");
		}


    	if(leftMenu != null && !leftMenu.equals("")){
    		request.getSession().setAttribute("leftMenu", leftMenu);
    	}
    	if(openMenu != null && !openMenu.equals("")){
    		request.getSession().setAttribute("openMenu", openMenu);
    	}

    	if(url.contains("http:") || url.contains("https:")){
    		return "egovframework/com/main/accessDenied";
    	}else{
    		return "redirect:"+url;
    	}

    }

}
