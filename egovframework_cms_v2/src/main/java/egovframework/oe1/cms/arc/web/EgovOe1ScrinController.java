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

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.arc.service.EgovOe1ScrinService;
import egovframework.oe1.cms.arc.service.EgovOe1ScrinVO;
import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.com.service.EgovOe1LoginVO;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 화면정보 관리 컨트롤러 클래스
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
public class EgovOe1ScrinController {

	protected Log log = LogFactory.getLog(this.getClass());

	/** EgovOe1ScrinService */
	@Resource(name = "egovOe1ScrinService")
	private EgovOe1ScrinService egovOe1ScrinService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	/**
	 * 엑셀다운관련
	 */
	/***/
	@Resource(name = "excelService")
	private EgovExcelService excelService;

	/**
	 * 파일관련
	 */
	/** EgovFileMngService */
	@Resource(name = "EgovFileMngService")
	private EgovOe1FileMngService fileMngService;
	/**
	 * 파일관련
	 */
	/** EgovFileMngUtil */
	@Resource(name = "EgovFileMngUtil")
	private EgovOe1FileMngUtil fileUtil;

	/**
	 * IdGeneration
	 */
	@Resource(name = "egovScrinMngIdGnrService")
	private EgovIdGnrService idGnrService;

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	public EgovOe1CmmUseService egovCmmUseService;

	/**
	 * 화면정보를 삭제한다.
	 * @param 검색조건이 담긴 scrinVO 
	 * @return "forward:/cms/arc/selectScrin.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/removeScrin.do")
	public String deleteScrin(
			@ModelAttribute("scrinId") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {

		// Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		/* 첨부파일삭제 후 첨부파일 ID 사용여부 N으로 처리 */
		if (!scrinVO.getAtchFileId().equals("")) {
			EgovOe1FileVO fileVo = new EgovOe1FileVO();
			fileVo.setAtchFileId(scrinVO.getAtchFileId());// 첨부파일 ID
			fileMngService.deleteAllFileInf(fileVo);
		}

		egovOe1ScrinService.deleteScrin(scrinVO);
		return "forward:/cms/arc/selectScrin.do";
	}

	/**
	 * 화면정보 등록화면으로 이동한다.
	 * @param 검색조건이 담긴 scrinVO 
	 * @return "/cms/arc/EgovScrinRegist";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/addScrinView.do")
	public String insertScrinView(
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {

		// 시스템구분코드를 호출한다.
		EgovOe1ComDefaultCodeVO sysVo = new EgovOe1ComDefaultCodeVO();
		sysVo.setCodeId("OE1031");
		List CompnSysCodeList = egovCmmUseService.selectCmmCodeDetail(sysVo);
		model.addAttribute("compnSysCodeList", CompnSysCodeList);

		// 업무코드를 호출한다.
		EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
		vo.setCodeId("OE1030");
		List CompnCodeList = egovCmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("compnCodeList", CompnCodeList);

		return "/cms/arc/EgovScrinRegist";
	}

	/**
	 * 화면정보를 등록한다.
	 * @param 검색조건이 담긴 scrinVO , multiRequest
	 * @return "forward:/cms/arc/selectScrin.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/addScrin.do")
	public String insertScrin(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model,
			SessionStatus status) throws Exception {

		// Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper
				.getAuthenticatedUser();
		scrinVO.setFrstRegisterId(user.getMberId());

		/** 파일 처리 */
		List<EgovOe1FileVO> _result = null;
		String _atchFileId = "";
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			_result = fileUtil.parseFileInf(files, "", 0, "", "");
			_atchFileId = fileMngService.insertFileInfs(_result); // 파일이 생성되고나면
																	// 생성된 첨부파일
																	// ID를 리턴한다.
		}
		scrinVO.setAtchFileId(_atchFileId);

		// 화면IDd
		scrinVO.setScrinId(idGnrService.getNextStringId());
		// 화면 내용 등록
		egovOe1ScrinService.insertScrin(scrinVO);

		return "forward:/cms/arc/selectScrin.do";
	}

	/**
	 * 화면정보를 상세조회한다.
	 * @param 검색조건이 담긴 scrinVO
	 * @return "/cms/arc/EgovScrinDetail";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/getScrin.do")
	public String selectScrin(
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {

		// 시스템구분코드를 호출한다.
		EgovOe1ComDefaultCodeVO sysVo = new EgovOe1ComDefaultCodeVO();
		sysVo.setCodeId("OE1031");
		List CompnSysCodeList = egovCmmUseService.selectCmmCodeDetail(sysVo);
		model.addAttribute("compnSysCodeList", CompnSysCodeList);

		// 업무코드를 호출한다.
		EgovOe1ComDefaultCodeVO vo = new EgovOe1ComDefaultCodeVO();
		vo.setCodeId("OE1030");
		List CompnCodeList = egovCmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("compnCodeList", CompnCodeList);

		EgovOe1ScrinVO resultVO = new EgovOe1ScrinVO();

		resultVO = egovOe1ScrinService.selectScrin(scrinVO);

		EgovOe1FileVO fileVO = new EgovOe1FileVO();

		String _atchFileId = resultVO.getAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의
														// 파일 ID를 불러온다.
		fileVO.setAtchFileId(_atchFileId);
		List<EgovOe1FileVO> result = fileMngService.selectFileInfs(fileVO);

		// 실제 파일이 없으면 _atchFileId은 널로 세팅
		if (result.isEmpty()) {
			resultVO.setAtchFileId("");
		}

		model.addAttribute("scrinVO", resultVO);

		// 화면에 매핑된 기능 및 관련 메소드 정보 조회
		List serviceList = egovOe1ScrinService.selectFunctionList(scrinVO);
		model.addAttribute("resultlist", serviceList);

		return "/cms/arc/EgovScrinDetail";
	}

	/**
	 * 화면정보 목록을 조회한다.
	 * @param 검색조건이 담긴 scrinVO
	 * @return "/cms/arc/EgovScrinList";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/selectScrin.do")
	public String selectScrinList(
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {

		/** EgovPropertyService.sample */
		scrinVO.setPageUnit(propertiesService.getInt("pageUnit"));
		scrinVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(scrinVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(scrinVO.getPageUnit());
		paginationInfo.setPageSize(scrinVO.getPageSize());

		scrinVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		scrinVO.setLastIndex(paginationInfo.getLastRecordIndex());
		scrinVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List serviceList = egovOe1ScrinService.selectScrinList(scrinVO);

		model.addAttribute("resultList", serviceList);

		int totCnt = egovOe1ScrinService.selectScrinListTot(scrinVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "/cms/arc/EgovScrinList";
	}

	/**
	 * 화면정보를 수정한다.
	 * @param 검색조건이 담긴 scrinVO, multiRequest
	 * @return "forward:/cms/arc/getScrin.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/updtScrin.do")
	public String updateScrin(final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {

		try {

			String _atchFileId = scrinVO.getAtchFileId();// 해당 업무기능에 따라서 수정되는
															// 기능의 파일 ID를 불러온다.
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				if ("".equals(_atchFileId) || _atchFileId == null) {
					List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files,
							"", 0, _atchFileId, "");
					_atchFileId = fileMngService.insertFileInfs(_result); // 기존에
																			// 첨부파일
																			// ID가
																			// 없다.
					scrinVO.setAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된
														// 첨부파일 ID 정보를 세팅한다.
				} else {
					EgovOe1FileVO fvo = new EgovOe1FileVO();
					fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재
													// 첨부파일 ID를 세팅한다.
					int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에
																	// 속하는 최종 파일
																	// 순번을 획득한다.
					List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files,
							"", _cnt, _atchFileId, "");
					fileMngService.updateFileInfs(_result);
				}
			}

			// Spring Security
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
			}

			// 세션정보
			EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper
					.getAuthenticatedUser();
			scrinVO.setFrstRegisterId(user.getMberId());

			egovOe1ScrinService.updateScrin(scrinVO);

			model.addAttribute("resultMsg", "수정되었습니다.");

			// status.setComplete();
			return "forward:/cms/arc/getScrin.do";
		} catch (Exception ex) {
			// ex.printStackTrace();
			throw ex;
		}

	}

	/**
	 * 화면정보 등록화면으로 이동한다.
	 * @param 검색조건이 담긴 scrinVO
	 * @return "/cms/arc/EgovExcelScrinRegist";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/addExcelScrinView.do")
	public String insertExcelScrinView(
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO, ModelMap model)
			throws Exception {
		return "/cms/arc/EgovExcelScrinRegist";
	}

	/**
	 * 엑셀파일을 업로드하여 우편번호를 등록한다.
	 * @param 검색조건이 담긴 scrinVO, request, model
	 * @return forward:/cms/arc/selectScrin.do";
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/arc/excelScrinRegist.do")
	public String insertExcelScrin(
			@ModelAttribute("scrinVO") EgovOe1ScrinVO scrinVO,
			final HttpServletRequest request, Map commandMap, Model model)
			throws Exception {

		// Spring Security
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "/cms/com/EgovLoginUsr"; // 임시로그온페이지 이동
		}

		// 세션정보
		EgovOe1LoginVO user = (EgovOe1LoginVO) EgovUserDetailsHelper
				.getAuthenticatedUser();
		scrinVO.setFrstRegisterId(user.getMberId());

		String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap
				.get("cmd");
		if (sCmd.equals("")) {
			return "/cms/arc/EgovExcelScrinRegist";
		}
		final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				// 이전 데이터를 전부 삭제 후 처리함.
				egovOe1ScrinService.deleteExcelScrin();

				InputStream is = null;
				try {
				    is = file.getInputStream();
				    excelService.uploadExcel("egovOe1ScrinDAO.inserExceltScrin", is, 4);
				} catch (Exception e) {
				    throw e;
				} finally {
				    try {
				        if(is != null) {is.close();}
				    } catch (Exception e){
				    	// log none
				    	log.info(e.getMessage());
				    }
				}
			}
		}

		return "forward:/cms/arc/selectScrin.do";
	}

}