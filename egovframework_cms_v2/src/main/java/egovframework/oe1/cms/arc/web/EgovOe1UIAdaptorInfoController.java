/*
 * Copyright 2010 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.oe1.cms.arc.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1UIAdaptorInfoService;
import egovframework.oe1.cms.arc.service.EgovOe1UIAdaptorInfoVO;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * UI아답터 관리 및 변경요청접수관리 컨트롤러 클래스
 * 
 * @author 운영환경1팀 김연수
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  김연수          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1UIAdaptorInfoController {

	@Resource(name = "egovOe1UIAdaptorInfoService")
	private EgovOe1UIAdaptorInfoService egovOe1UIAdaptorInfoService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * UI아답터 정보 등록화면으로 이동
	 * @param 검색조건이 담긴 searchVo 
	 * @return "/cms/arc/EgovUIAdaptorInfoRegist";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/insertUIAdaptorInfoView.do")
	public String insertUIAdaptorInfoView(
			@ModelAttribute("vo") EgovOe1ComDefaultVO searchVo, ModelMap model)
			throws Exception {
		model.addAttribute("searchVo", searchVo);
		model.addAttribute("uiAdaptorInfo", new EgovOe1UIAdaptorInfoVO());

		return "/cms/arc/EgovUIAdaptorInfoRegist";
	}
	
	/**
	 * UI아답터 정보 등록
	 * @param 검색조건이 담긴 EgovOe1UIAdaptorInfoVO 
	 * @return "redirect:/cms/arc/selectUIAdaptorInfoList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/insertUIAdaptorInfo.do")
	public String insertUIAdaptorInfo(
			@ModelAttribute("vo") EgovOe1UIAdaptorInfoVO vo, ModelMap model)
			throws Exception {

		// Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper
				.getAuthenticatedUser();
		vo.setFrstRegisterId(user.getMberId());
		egovOe1UIAdaptorInfoService.insertUIAdaptorInfo(vo);

		return "redirect:/cms/arc/selectUIAdaptorInfoList.do";
	}
	
	/**
	 * UI아답터 정보 조회
	 * @param 검색조건이 담긴 id 
	 * @return "/cms/arc/EgovUIAdaptorInfoDetail"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectUIAdaptorInfo.do")
	public String selectUIAdaptorInfo(@RequestParam("id") String id,
			ModelMap model) throws Exception {
		getAndAddUIAdaptorInfo(id, model);

		return "/cms/arc/EgovUIAdaptorInfoDetail";
	}
	
	/**
	 * UI아답터 수정화면으로 이동
	 * @param 검색조건이 담긴 id 
	 * @return "/cms/arc/EgovUIAdaptorInfoUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/updateUIAdaptorInfoView.do")
	public String updateUIAdaptorInfoView(@RequestParam("id") String id,
			ModelMap model) throws Exception {
		getAndAddUIAdaptorInfo(id, model);

		return "/cms/arc/EgovUIAdaptorInfoUpdt";
	}
	
	/**
	 * UI아답터 수정
	 * @param 검색조건이 담긴 id, EgovOe1UIAdaptorInfoVO
	 * @return "/cms/arc/EgovUIAdaptorInfoUpdt";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/updateUIAdaptorInfo.do")
	public String updateUIAdaptorInfo(
			@ModelAttribute("vo") EgovOe1UIAdaptorInfoVO vo, ModelMap model)
			throws Exception {

		// Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper
				.getAuthenticatedUser();
		vo.setFrstRegisterId(user.getMberId());
		egovOe1UIAdaptorInfoService.updateUIAdaptorInfo(vo);

		model.addAttribute("resultMsg", "수정되었습니다.");

		return "redirect:/cms/arc/selectUIAdaptorInfo.do?id="
				+ vo.getUiAdaptId();
	}
	
	/**
	 * UI아답터 삭제
	 * @param 검색조건이 담긴 id
	 * @return "redirect:/cms/arc/selectUIAdaptorInfoList.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/deleteUIAdaptorInfo.do")
	public String deleteUIAdaptorInfo(@RequestParam("uiAdaptId") String id,
			ModelMap model) throws Exception {
		egovOe1UIAdaptorInfoService
				.deleteUIAdaptorInfo(new EgovOe1UIAdaptorInfoVO(id));

		return "redirect:/cms/arc/selectUIAdaptorInfoList.do";
	}
	
	/**
	 * UI아답터 목록
	 * @param 검색조건이 담긴 vo
	 * @return "/cms/arc/EgovUIAdaptorInfoList";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectUIAdaptorInfoList.do")
	public String selectUIAdaptorInfoList(
			@ModelAttribute("searchVo") EgovOe1ComDefaultVO vo, ModelMap model)
			throws Exception {

		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EgovOe1UIAdaptorInfoVO> list = egovOe1UIAdaptorInfoService
				.selectUIAdaptorInfoList(vo);
		model.addAttribute("list", list);

		int count = egovOe1UIAdaptorInfoService.countUIAdaptorInfoList(vo);
		paginationInfo.setTotalRecordCount(count);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/cms/arc/EgovUIAdaptorInfoList";
	}
	
	/**
	 * UI아답터 정보 조회
	 * @param 검색조건이 담긴 id
	 * 
	 * @exception Exception
	 */
	private void getAndAddUIAdaptorInfo(String id, ModelMap model)
			throws Exception {
		EgovOe1UIAdaptorInfoVO uvo = egovOe1UIAdaptorInfoService
				.selectUIAdaptorInfo(new EgovOe1UIAdaptorInfoVO(id));
		model.addAttribute("uiAdaptorInfo", uvo);
	}

}