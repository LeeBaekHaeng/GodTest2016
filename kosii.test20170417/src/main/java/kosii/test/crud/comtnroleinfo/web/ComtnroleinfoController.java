package kosii.test.crud.comtnroleinfo.web;

import java.util.List;

import javax.annotation.Resource;

import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoDefaultVO;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoService;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : ComtnroleinfoController.java
 * @Description : Comtnroleinfo Controller class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-17
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@SessionAttributes(types = ComtnroleinfoVO.class)
public class ComtnroleinfoController {

	@Resource(name = "comtnroleinfoService")
	private ComtnroleinfoService comtnroleinfoService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * COMTNROLEINFO 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 ComtnroleinfoDefaultVO
	 * @return "kosii/test/crud/comtnroleinfo/ComtnroleinfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/comtnroleinfo/ComtnroleinfoList.do")
	public String selectComtnroleinfoList(
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> comtnroleinfoList = comtnroleinfoService
				.selectComtnroleinfoList(searchVO);
		model.addAttribute("resultList", comtnroleinfoList);

		int totCnt = comtnroleinfoService
				.selectComtnroleinfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "kosii/test/crud/comtnroleinfo/ComtnroleinfoList";
	}

	@RequestMapping("/comtnroleinfo/addComtnroleinfoView.do")
	public String addComtnroleinfoView(
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			Model model) throws Exception {
		model.addAttribute("comtnroleinfoVO", new ComtnroleinfoVO());
		return "kosii/test/crud/comtnroleinfo/ComtnroleinfoRegister";
	}

	@RequestMapping("/comtnroleinfo/addComtnroleinfo.do")
	public String addComtnroleinfo(ComtnroleinfoVO comtnroleinfoVO,
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnroleinfoService.insertComtnroleinfo(comtnroleinfoVO);
		status.setComplete();
		return "forward:/comtnroleinfo/ComtnroleinfoList.do";
	}

	@RequestMapping("/comtnroleinfo/updateComtnroleinfoView.do")
	public String updateComtnroleinfoView(
			@RequestParam("roleCode") java.lang.String roleCode,
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			Model model) throws Exception {
		ComtnroleinfoVO comtnroleinfoVO = new ComtnroleinfoVO();
		comtnroleinfoVO.setRoleCode(roleCode);
		// 변수명은 CoC 에 따라 comtnroleinfoVO
		model.addAttribute(selectComtnroleinfo(comtnroleinfoVO, searchVO));
		return "kosii/test/crud/comtnroleinfo/ComtnroleinfoRegister";
	}

	@RequestMapping("/comtnroleinfo/selectComtnroleinfo.do")
	public @ModelAttribute("comtnroleinfoVO") ComtnroleinfoVO selectComtnroleinfo(
			ComtnroleinfoVO comtnroleinfoVO,
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO)
			throws Exception {
		return comtnroleinfoService.selectComtnroleinfo(comtnroleinfoVO);
	}

	@RequestMapping("/comtnroleinfo/updateComtnroleinfo.do")
	public String updateComtnroleinfo(ComtnroleinfoVO comtnroleinfoVO,
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnroleinfoService.updateComtnroleinfo(comtnroleinfoVO);
		status.setComplete();
		return "forward:/comtnroleinfo/ComtnroleinfoList.do";
	}

	@RequestMapping("/comtnroleinfo/deleteComtnroleinfo.do")
	public String deleteComtnroleinfo(ComtnroleinfoVO comtnroleinfoVO,
			@ModelAttribute("searchVO") ComtnroleinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnroleinfoService.deleteComtnroleinfo(comtnroleinfoVO);
		status.setComplete();
		return "forward:/comtnroleinfo/ComtnroleinfoList.do";
	}

}
