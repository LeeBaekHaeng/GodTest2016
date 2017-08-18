package egovframework.com.uss.olh.qna.web;

import java.util.List;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olh.qna.service.EgovQnaManageService;
import egovframework.com.uss.olh.qna.service.QnaManageDefaultVO;
import egovframework.com.uss.olh.qna.service.QnaManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 *
 * Q&A를 처리하는 Controller 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     	수정자           			수정내용
 *  ------------   --------    ---------------------------------------------
 *   2009.04.01  	박정규          최초 생성
 *   2011.08.26		정진오			IncludedInfo annotation 추가
 *   2011.10.21		이기하			삭제시 비밀번호 확인 추가(최종감리 반영)
 *
 * </pre>
 */

@Controller
public class EgovQnaManageController {

	@Resource(name = "QnaManageService")
	private EgovQnaManageService qnaManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	// Validation 관련
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 개별 배포시 메인메뉴를 조회한다.
	 * @param model
	 * @return	"/uss/olh/qna/"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olh/qna/EgovMain.do")
	public String egovMain(ModelMap model) throws Exception {
		return "egovframework/com/uss/olh/qna/EgovMain";
	}

	/**
	 * 메뉴를 조회한다.
	 * @param model
	 * @return	"/uss/olh/qna/EgovLeft"
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/olh/qna/EgovLeft.do")
	public String egovLeft(ModelMap model) throws Exception {
		return "egovframework/com/uss/olh/qna/EgovLeft";
	}

	/**
	 * Q&A정보 목록을 조회한다. (pageing)
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaListInqire"
	 * @throws Exception
	 */
	@IncludedInfo(name = "Q&A관리", order = 550, gid = 50)
	@RequestMapping(value = "/uss/olh/qna/QnaListInqire.do")
	public String selectQnaList(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
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

		List<?> QnaList = qnaManageService.selectQnaList(searchVO);
		model.addAttribute("resultList", QnaList);

		// 인증여부 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			model.addAttribute("certificationAt", "N");
		} else {
			model.addAttribute("certificationAt", "Y");
		}

		int totCnt = qnaManageService.selectQnaListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olh/qna/EgovQnaListInqire";
	}

	/**
	 * Q&A정보 목록에 대한 상세정보를 조회한다.
	 * @param passwordConfirmAt
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaDetailInqire"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaDetailInqire.do")
	public String selectQnaListDetail(@RequestParam("passwordConfirmAt") String passwordConfirmAt, QnaManageVO qnaManageVO,
			@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);

		vo.setPasswordConfirmAt(passwordConfirmAt); // 작성비밀번호 확인여부

		// 작성 비밀번호를 얻는다.
		String writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
		vo.setWritngPassword(EgovFileScrty.decode(writngPassword));

		model.addAttribute("result", vo);

		return "egovframework/com/uss/olh/qna/EgovQnaDetailInqire";
	}

	/**
	 * Q&A 조회수를  수정처리한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qna/QnaDetailInqire.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/QnaInqireCoUpdt.do")
	public String updateQnaInqireCo(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO) throws Exception {

		qnaManageService.updateQnaInqireCo(qnaManageVO);

		return "forward:/uss/olh/qna/QnaDetailInqire.do";

	}

	/**
	 * 로그인/실명확인 처리
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	/uss/olh/qna/EgovLoginRealnmChoice
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/LoginRealnmChoice.do")
	public String selectLoginRealnmChoice(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, Model model) throws Exception {

		model.addAttribute("QnaManageVO", new QnaManageVO());

		return "egovframework/com/uss/olh/qna/EgovQnaLoginRealnmChoice";
	}

	/**
	 * Q&A정보를 등록하기 위한 전 처리(인증체크)
	 * @param searchVO
	 * @param qnaManageVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaCnRegist"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/QnaCnRegistView.do")
	public String insertQnaCnView(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, QnaManageVO qnaManageVO, Model model) throws Exception {

		// 인증여부 체크
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (!isAuthenticated) {
			model.addAttribute("result", qnaManageVO);
			return "egovframework/com/uss/olh/qna/EgovQnaCnRegist";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String wrterNm = loginVO.getName(); // 사용자명
		String emailAdres = loginVO.getEmail(); // email 주소

		qnaManageVO.setWrterNm(wrterNm); // 작성자명
		qnaManageVO.setEmailAdres(emailAdres); // email 주소

		model.addAttribute("result", qnaManageVO);
		model.addAttribute("qnaManageVO", qnaManageVO);

		return "egovframework/com/uss/olh/qna/EgovQnaCnRegist";

	}

	/**
	 * Q&A정보를 등록한다.
	 * @param searchVO
	 * @param qnaManageVO
	 * @param bindingResult
	 * @return	"forward:/uss/olh/qna/QnaListInqire.do"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaCnRegist.do")
	public String insertQnaCn(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, @ModelAttribute("qnaManageVO") QnaManageVO qnaManageVO, BindingResult bindingResult,
			ModelMap model) throws Exception {

		beanValidator.validate(qnaManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/qna/EgovQnaCnRegist";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String frstRegisterId = loginVO.getUniqId();

		qnaManageVO.setFrstRegisterId(frstRegisterId); // 최초등록자ID
		qnaManageVO.setLastUpdusrId(frstRegisterId); // 최종수정자ID

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		qnaManageService.insertQnaCn(qnaManageVO);

		return "forward:/uss/olh/qna/QnaListInqire.do";
	}

	/**
	 * 작성 비밀번호를 확인하기 위한 전 처리
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaPasswordConfirm"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/QnaPasswordConfirmView.do")
	public String selectPasswordConfirmView(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, Model model) throws Exception {

		model.addAttribute("QnaManageVO", new QnaManageVO());

		return "egovframework/com/uss/olh/qna/EgovQnaPasswordConfirm";
	}

	/**
	 * 수정을 위해 작성 비밀번호를 확인한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qna/QnaDetailInqire.do"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaPasswordConfirm.do")
	public String selectPasswordConfirm(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, Model model) throws Exception {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);

		if (searchCnt > 0) { // 작성 비밀번호가 일치하는 경우

			// Q&A를 수정할 수 있는 화면으로 이동.
			return "forward:/uss/olh/qna/QnaCnUpdtView.do";

		} else { // 작성비밀번호가 틀린경우
			String passwordConfirmAt = "N";

			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/QnaDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;

		}

	}

	/**
	 * Q&A정보를 수정하기 위한 전 처리(비밀번호 암호화)
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaCnUpdt
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaCnUpdtView.do")
	public String updateQnaCnView(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);

		// 작성 비밀번호를 얻는다.
		String writngPassword = vo.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 복호화한다.
		vo.setWritngPassword(EgovFileScrty.decode(writngPassword));

		// 복호화된 패스워드를 넘긴다..
		model.addAttribute("qnaManageVO", vo);

		// result에도 세팅(jstl 사용을 위해)
		model.addAttribute(selectQnaListDetail("Y", qnaManageVO, searchVO, model));

		return "egovframework/com/uss/olh/qna/EgovQnaCnUpdt";
	}

	/**
	 * Q&A정보를 수정처리한다.
	 * @param searchVO
	 * @param qnaManageVO
	 * @param bindingResult
	 * @return	"forward:/uss/olh/qna/QnaListInqire.do"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaCnUpdt.do")
	public String updateQnaCn(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, @ModelAttribute("qnaManageVO") QnaManageVO qnaManageVO, BindingResult bindingResult)
			throws Exception {

		// Validation
		beanValidator.validate(qnaManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/olh/qna/EgovQnaCnUpdt";
		}

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String lastUpdusrId = loginVO.getUniqId();

		qnaManageVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		qnaManageService.updateQnaCn(qnaManageVO);

		return "forward:/uss/olh/qna/QnaListInqire.do";

	}

	/**
	 * 삭제을 위해 작성 비밀번호를 확인한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qna/QnaDetailInqire.do"
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/uss/olh/qna/QnaPasswordConfirmDel.do")
	public String selectPasswordConfirmDel(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, Model model) throws Exception {

		// 작성비밀번호를 암호화 하기 위해서 Get
		String writngPassword = qnaManageVO.getWritngPassword();

		// EgovFileScrty Util에 있는 암호화 모듈을 적용해서 암호화 한다.
		qnaManageVO.setWritngPassword(EgovFileScrty.encode(writngPassword));

		int searchCnt = qnaManageService.selectQnaPasswordConfirmCnt(qnaManageVO);

		if (searchCnt > 0) { // 작성 비밀번호가 일치하는 경우

			// Q&A를 삭제
			return "forward:/uss/olh/qna/QnaCnDelete.do";

		} else { // 작성비밀번호가 틀린경우
			String passwordConfirmAt = "N";
			// Q&A 상세조회 화면으로 이동.
			return "forward:/uss/olh/qna/QnaDetailInqire.do?passwordConfirmAt=" + passwordConfirmAt;

		}

	}

	/**
	 * Q&A정보를 삭제처리한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qna/QnaListInqire.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qna/QnaCnDelete.do")
	public String deleteQnaCn(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO) throws Exception {

		qnaManageService.deleteQnaCn(qnaManageVO);

		return "forward:/uss/olh/qna/QnaListInqire.do";
	}

	/**
	 * Q&A답변정보 목록을 조회한다. (pageing)
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaAnswerListInqire"
	 * @throws Exception
	 */
	@IncludedInfo(name = "Q&A답변관리", order = 551, gid = 50)
	@RequestMapping(value = "/uss/olh/qnm/QnaAnswerListInqire.do")
	public String selectQnaAnswerList(@ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.SiteList */
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

		List<?> QnaAnswerList = qnaManageService.selectQnaAnswerList(searchVO);
		model.addAttribute("resultList", QnaAnswerList);

		int totCnt = qnaManageService.selectQnaAnswerListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return "egovframework/com/uss/olh/qna/EgovQnaAnswerListInqire";
	}

	/**
	 * Q&A답변정보 목록에 대한 상세정보를 조회한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaAnswerDetailInqire"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qnm/QnaAnswerDetailInqire.do")
	public String selectQnaAnswerListDetail(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		QnaManageVO vo = qnaManageService.selectQnaListDetail(qnaManageVO);

		model.addAttribute("result", vo);

		return "egovframework/com/uss/olh/qna/EgovQnaAnswerDetailInqire";
	}

	/**
	 * Q&A답변정보를 수정하기 위한 전 처리(공통코드 처리)
	 * @param qnaManageVO
	 * @param searchVO
	 * @param model
	 * @return	"/uss/olh/qna/EgovQnaCnAnswerUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qnm/QnaCnAnswerUpdtView.do")
	public String updateQnaCnAnswerView(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO, ModelMap model) throws Exception {

		// 공통코드를 가져오기 위한 Vo
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM028");

		List<?> _result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("resultList", _result);

		// 변수명은 CoC 에 따라
		model.addAttribute(selectQnaAnswerListDetail(qnaManageVO, searchVO, model));

		return "egovframework/com/uss/olh/qna/EgovQnaCnAnswerUpdt";
	}

	/**
	 * Q&A답변정보를 수정처리한다.
	 * @param qnaManageVO
	 * @param searchVO
	 * @return	"forward:/uss/olh/qnm/QnaAnswerListInqire.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/olh/qnm/QnaCnAnswerUpdt.do")
	public String updateQnaCnAnswer(QnaManageVO qnaManageVO, @ModelAttribute("searchVO") QnaManageDefaultVO searchVO) throws Exception {

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String lastUpdusrId = loginVO.getUniqId();

		qnaManageVO.setLastUpdusrId(lastUpdusrId); // 최종수정자ID

		qnaManageService.updateQnaCnAnswer(qnaManageVO);

		return "forward:/uss/olh/qnm/QnaAnswerListInqire.do";

	}

}
