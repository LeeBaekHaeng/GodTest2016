package kosii.test.crud.comtnauthorinfo.web;

import java.util.List;

import javax.annotation.Resource;

import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoDefaultVO;
import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoService;
import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoVO;

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
 * @Class Name : ComtnauthorinfoController.java
 * @Description : Comtnauthorinfo Controller class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-10
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@SessionAttributes(types = ComtnauthorinfoVO.class)
public class ComtnauthorinfoController {

	@Resource(name = "comtnauthorinfoService")
	private ComtnauthorinfoService comtnauthorinfoService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * COMTNAUTHORINFO 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 ComtnauthorinfoDefaultVO
	 * @return "kosii/test/crud/comtnauthorinfo/ComtnauthorinfoList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/comtnauthorinfo/ComtnauthorinfoList.do")
	public String selectComtnauthorinfoList(
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
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

		List<?> comtnauthorinfoList = comtnauthorinfoService
				.selectComtnauthorinfoList(searchVO);
		model.addAttribute("resultList", comtnauthorinfoList);

		int totCnt = comtnauthorinfoService
				.selectComtnauthorinfoListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "kosii/test/crud/comtnauthorinfo/ComtnauthorinfoList";
	}

	@RequestMapping("/comtnauthorinfo/addComtnauthorinfoView.do")
	public String addComtnauthorinfoView(
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
			Model model) throws Exception {
		model.addAttribute("comtnauthorinfoVO", new ComtnauthorinfoVO());
		return "kosii/test/crud/comtnauthorinfo/ComtnauthorinfoRegister";
	}

	@RequestMapping("/comtnauthorinfo/addComtnauthorinfo.do")
	public String addComtnauthorinfo(ComtnauthorinfoVO comtnauthorinfoVO,
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnauthorinfoService.insertComtnauthorinfo(comtnauthorinfoVO);
		status.setComplete();
		return "forward:/comtnauthorinfo/ComtnauthorinfoList.do";
	}

	@RequestMapping("/comtnauthorinfo/updateComtnauthorinfoView.do")
	public String updateComtnauthorinfoView(
			@RequestParam("authorCode") java.lang.String authorCode,
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
			Model model) throws Exception {
		ComtnauthorinfoVO comtnauthorinfoVO = new ComtnauthorinfoVO();
		comtnauthorinfoVO.setAuthorCode(authorCode);
		// 변수명은 CoC 에 따라 comtnauthorinfoVO
		model.addAttribute(selectComtnauthorinfo(comtnauthorinfoVO, searchVO));
		return "kosii/test/crud/comtnauthorinfo/ComtnauthorinfoRegister";
	}

	@RequestMapping("/comtnauthorinfo/selectComtnauthorinfo.do")
	public @ModelAttribute("comtnauthorinfoVO") ComtnauthorinfoVO selectComtnauthorinfo(
			ComtnauthorinfoVO comtnauthorinfoVO,
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO)
			throws Exception {
		return comtnauthorinfoService.selectComtnauthorinfo(comtnauthorinfoVO);
	}

	@RequestMapping("/comtnauthorinfo/updateComtnauthorinfo.do")
	public String updateComtnauthorinfo(ComtnauthorinfoVO comtnauthorinfoVO,
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnauthorinfoService.updateComtnauthorinfo(comtnauthorinfoVO);
		status.setComplete();
		return "forward:/comtnauthorinfo/ComtnauthorinfoList.do";
	}

	@RequestMapping("/comtnauthorinfo/deleteComtnauthorinfo.do")
	public String deleteComtnauthorinfo(ComtnauthorinfoVO comtnauthorinfoVO,
			@ModelAttribute("searchVO") ComtnauthorinfoDefaultVO searchVO,
			SessionStatus status) throws Exception {
		comtnauthorinfoService.deleteComtnauthorinfo(comtnauthorinfoVO);
		status.setComplete();
		return "forward:/comtnauthorinfo/ComtnauthorinfoList.do";
	}

}
