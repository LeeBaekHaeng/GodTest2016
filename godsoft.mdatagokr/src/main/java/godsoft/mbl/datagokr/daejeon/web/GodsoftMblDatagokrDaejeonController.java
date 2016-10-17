package godsoft.mbl.datagokr.daejeon.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import godsoft.mbl.datagokr.daejeon.service.GodsoftMblDatagokrDaejeonService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GodsoftMblDatagokrDaejeonController {

	@Autowired
	private GodsoftMblDatagokrDaejeonService godsoftMblDatagokrDaejeonService;

	/**
	 * 대전광역시 어린이보호구역 목록조회
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@IncludedInfo(name = "대전광역시 어린이보호구역 정보", order = 15007288, gid = 6300000)
	@RequestMapping("/getKidSafeDaejeonList.mdo")
	public String getKidSafeDaejeonList(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrDaejeonService.getKidSafeDaejeonList(vo, model);
		return "godsoft/mbl/datagokr/daejeon/getKidSafeDaejeonList";
	}

	/**
	 * 대전광역시 어린이보호구역 상세조회
	 * 
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getKidSafeDaejeon.mdo")
	public String getKidSafeDaejeon(@RequestParam Map<String, Object> vo,
			ModelMap model) throws Exception {
		godsoftMblDatagokrDaejeonService.getKidSafeDaejeon(vo, model);
		return "godsoft/mbl/datagokr/daejeon/getKidSafeDaejeon";
	}

}
