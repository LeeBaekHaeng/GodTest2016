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
package egovframework.oe1.cms.srm.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.*;

import javax.annotation.Resource;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.oe1.cms.com.service.EgovOe1CmmUseService;
import egovframework.oe1.cms.com.service.EgovOe1ComDefaultCodeVO;
import egovframework.oe1.cms.com.service.EgovOe1FileMngService;
import egovframework.oe1.cms.com.service.EgovOe1FileMngUtil;
import egovframework.oe1.cms.com.service.EgovOe1FileVO;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupService;
import egovframework.oe1.cms.sys.service.EgovOe1AuthorGroupVO;

import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqProcService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;
import egovframework.oe1.cms.stn.service.EgovOe1RiskManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 운영개선요청 분배 컨트롤러 클래스
 * 
 * @author 운영환경1팀 박수림
 * @since 2010.08.10
 * @version 1.0
 * @see
 *
 * <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2010.08.10  박수림          최초 생성
 *
 * </pre>
 */
@Controller
public class EgovOe1OperImprovReqProcController {

    /** Validator */
    @Resource(name = "beanValidator")
    protected DefaultBeanValidator beanValidator;

    /** EgovOe1OperImprovReqService */
    @Resource(name = "operImprovReqService")
    private EgovOe1OperImprovReqService operImprovReqService;
    
    /** EgovOe1OperImprovReqService */
    @Resource(name = "operImprovReqProcService")
    private EgovOe1OperImprovReqProcService operImprovReqProcService;
    
    /** EgovCmmUseService */
    @Resource(name = "EgovCmmUseService")
    public EgovOe1CmmUseService egovCmmUseService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    EgovPropertyService propertiesService;

    /** EgovFileMngService */
    @Resource(name="EgovFileMngService")
    private EgovOe1FileMngService fileMngService;

    /** egovAuthorGroupService */
    @Resource(name = "egovOe1AuthorGroupService")
    private EgovOe1AuthorGroupService egovAuthorGroupService;
    
    /** EgovFileMngUtil */
    @Resource(name="EgovFileMngUtil")
    private EgovOe1FileMngUtil fileUtil;	

    /** 파일구분자 */
    static final char FILE_SEPARATOR = File.separatorChar;
    public EgovOe1OperImprovReqProcController() {
    }

    /**
	 * 운영개선요청 분배목록을 조회한다. (pageing)
	 * @param 조회할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqDstbList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/srm/admin/selectOperImprovReqDstbList.do")
	public String selectOperImprovReqDstbList(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model
			) throws Exception {
		//검색조건
        model.addAttribute("searchVO", vo);
		
		//상태
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1023");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetailForAll(vo1);
        model.addAttribute("srequstSttusCode", srTrgetCode_result1);
		
    	//업무구분
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1020");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
        model.addAttribute("soperJobSeCode", srTrgetCode_result2);	
		
    	/** EgovPropertyService.sample */
		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		
        List list = operImprovReqService.selectOperImprovReqList(vo);
        model.addAttribute("resultList", list);
        
        int totCnt = operImprovReqService.selectOperImprovReqListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
		return "cms/srm/EgovOperImprovReqDstbList";
	}


    /**
	 * 운영개선요청 처리목록을 조회한다. (pageing)
	 * @param 조회할 정보가 담긴 EgovOe1OperImprovReqVo
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqProcList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/srm/chrg/selectOperImprovReqProcList.do")
	public String selectOperImprovReqProcList(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, HttpSession session, ModelMap model
			) throws Exception {
		//검색조건
        model.addAttribute("searchVO", vo);
      
		//상태
        EgovOe1ComDefaultCodeVO vo1 = new EgovOe1ComDefaultCodeVO();
        vo1.setCodeId("OE1023");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetailForAll(vo1);
        model.addAttribute("srequstSttusCode", srTrgetCode_result1);
		
    	//업무구분
        EgovOe1ComDefaultCodeVO vo2 = new EgovOe1ComDefaultCodeVO();
        vo2.setCodeId("OE1020");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetailForAll(vo2);
        model.addAttribute("soperJobSeCode", srTrgetCode_result2);	
     
        
    	/** EgovPropertyService.sample */
		vo.setPageUnit(propertiesService.getInt("pageUnit"));
		vo.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** paging setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        vo.setSchargerId((String)session.getAttribute("s_mberId"));    //담당자id 
        
        List list = operImprovReqService.selectOperImprovReqList(vo);
        model.addAttribute("resultList", list);
        
        int totCnt = operImprovReqService.selectOperImprovReqListTotCnt(vo);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        		
		return "cms/srm/EgovOperImprovReqProcList";
	}
	
	
	/**
	 * 운영개선요청 등록 화면을 조회한다.
	 * 
	 * @param EgovOe1OperImprovReqProcVO
	 * @param model
	 * @return "operImprovReqRegiste"
	 * @exception Exception
	 
	@RequestMapping(value = "/cms/srm/addOperImprovReqProcView.do")
	public String insertOperImprovReqProcView(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model) throws Exception {
		//model.addAttribute("operImprovReqVO", new EgovOe1OperImprovReqVO());        
		
		return "cms/srm/EgovOperImprovReqProcRegist";
	}*/
	
	/**
	 * 운영개선요청 분배정보를 상세조회한다.
	 * @param EgovOe1OperImprovReqVo
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqDstb"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/admin/selectOperImprovReqDstb.do")
	public String selectOperImprovReqDstb(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

    	    //검색조건
            model.addAttribute("searchVO", vo);
            
            //요청구분코드
            EgovOe1ComDefaultCodeVO codeVo1 = new EgovOe1ComDefaultCodeVO();
            codeVo1.setCodeId("OE1012");
            List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(codeVo1);
            model.addAttribute("requstTyCode", srTrgetCode_result1);
    		
            //긴급처리여부
            EgovOe1ComDefaultCodeVO codeVo2 = new EgovOe1ComDefaultCodeVO();
            codeVo2.setCodeId("OE1005");
            List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(codeVo2);
            model.addAttribute("emrgncyProcessAt", srTrgetCode_result2);			
            
            //담당자
            EgovOe1AuthorGroupVO authorGroupVo = new EgovOe1AuthorGroupVO();
            authorGroupVo.setAuthorCode("ROLE_OPER_CHARGER"); //운영개선담당자
            authorGroupVo.setFirstIndex(0);
            authorGroupVo.setRecordCountPerPage(100);
            List authorUser = egovAuthorGroupService.selectAuthorUserList(authorGroupVo);
            model.addAttribute("authorUser", authorUser);
            
            EgovOe1OperImprovReqVO vo1 = new EgovOe1OperImprovReqVO();
            EgovOe1OperImprovReqVO vo2= new EgovOe1OperImprovReqVO();
            vo1.setOperImprvmRequstId(selectedId);
    	
            vo2 = operImprovReqService.selectOperImprovReq(vo1);
            model.addAttribute("vo",vo2);
    		
            return "cms/srm/EgovOperImprovReqDstb";
	}

	/**
	 * 운영개선요청 처리정보를 상세조회한다.
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "/cms/srm/EgovOperImprovReqProc"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/chrg/selectOperImprovReqProc.do")
	public String selectOperImprovReqProc(@RequestParam("selectedId")String selectedId, @ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {

		//검색조건
        model.addAttribute("searchVO", vo);
        
		//요청구분코드
        EgovOe1ComDefaultCodeVO codeVo1 = new EgovOe1ComDefaultCodeVO();
        codeVo1.setCodeId("OE1012");
        List srTrgetCode_result1 = egovCmmUseService.selectCmmCodeDetail(codeVo1);
        model.addAttribute("requstTyCode", srTrgetCode_result1);
		
    	//긴급처리여부
        EgovOe1ComDefaultCodeVO codeVo2 = new EgovOe1ComDefaultCodeVO();
        codeVo2.setCodeId("OE1005");
        List srTrgetCode_result2 = egovCmmUseService.selectCmmCodeDetail(codeVo2);
        model.addAttribute("emrgncyProcessAt", srTrgetCode_result2);			
		
		EgovOe1OperImprovReqVO vo1 = new EgovOe1OperImprovReqVO();
		EgovOe1OperImprovReqVO vo2= new EgovOe1OperImprovReqVO();
		vo1.setOperImprvmRequstId(selectedId);
		
		vo2 = operImprovReqService.selectOperImprovReq(vo1);
		model.addAttribute("vo",vo2);
		
        return "cms/srm/EgovOperImprovReqProc";
	}	
	
	/**
	 * 운영개선요청 작업분배를 한다.
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/admin/selectOperImprovReqDstbList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/admin/updateOperImprovReqDstb.do")
	public String updateOperImprovReqDstb(@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {
		operImprovReqProcService.updateOperImprovReqDstb(vo);
		return "forward:/cms/srm/admin/selectOperImprovReqDstbList.do";
	}

	/**
	 * 운영개선요청 변경이관을 위한 접수정보를 저장한다.
	 * @param EgovOe1OperImprovReqVo
	 * @param model
	 * @return "forward:/cms/srm/admin/selectOperImprovReqDstb.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/admin/updateOperImprovReqDstbTrans.do")
	public String updateOperImprovReqDstbTrans(@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {
		operImprovReqProcService.updateOperImprovReqDstb(vo);
		//이관시 상태정보 update test
		//operImprovReqService.updateOperImprovReqTransChange("02", vo.getLastUpdusrId(), vo.getOperImprovReqId());
		return "forward:/cms/srm/admin/selectOperImprovReqDstb.do";
	}

	/**
	 * 운영개선요청 처리정보를 저장한다.
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/chrg/selectOperImprovReqProcList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/chrg/updateOperImprovReqProc.do")
	public String updateOperImprovReqProc(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, 
			SessionStatus status,
			HttpServletRequest request)throws Exception  {
    	//세션확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
		}
		
	    String _atchFileId = vo.getProcessAtchFileId();// 해당 업무기능에 따라서 수정되는 기능의 파일 ID를 불러온다.
   
	    final Map<String, MultipartFile> files = multiRequest.getFileMap();
	    if(!files.isEmpty()){
            if("".equals(_atchFileId) || _atchFileId == null){
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", 0, _atchFileId, "");        
                _atchFileId = fileMngService.insertFileInfs(_result); // 기존에 첨부파일 ID가 없다.
                vo.setProcessAtchFileId(_atchFileId); // 관련 비즈니스 규칙에 따라서 생성된 첨부파일 ID 정보를 세팅한다.
            }else{
            	EgovOe1FileVO fvo = new EgovOe1FileVO();
                fvo.setAtchFileId(_atchFileId); // 최종 파일 순번을 획득하기 위하여 VO에 현재 첨부파일 ID를 세팅한다.
                int _cnt = fileMngService.getMaxFileSN(fvo); // 해당 첨부파일 ID에 속하는 최종 파일 순번을 획득한다.
                List<EgovOe1FileVO> _result = fileUtil.parseFileInf(files, "", _cnt, _atchFileId, "");     
                fileMngService.updateFileInfs(_result);
            }
	    }  			
	 		
		operImprovReqProcService.updateOperImprovReqProc(vo);
		if(vo.getRequstSttusCode().endsWith("06")){ //처리상태가  완료인 경우
			this.sendOperImprovReqResultMail(vo, request);
		}
		
		return "forward:/cms/srm/chrg/selectOperImprovReqProcList.do";
	}	

	
	/**
	 * 운영개선요청 처리만족도를 저장한다.
	 * @param EgovOe1OperImprovReqVO
	 * @param model
	 * @return "forward:/cms/srm/gnrl/selectOperImprovReqList.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/srm/gnrl/updateOperImprovReqEnd.do")
	public String updateOperImprovReqEnd(
			@ModelAttribute("vo") EgovOe1OperImprovReqVO vo, ModelMap model)throws Exception  {			
    	//세션확인
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated){
		     return "forward:/cms/com/EgovOe1LoginUsr.do";  //임시로그온페이지 이동
		}
		
		operImprovReqService.updateOperImprovReqEnd(vo);
		return "forward:/cms/srm/gnrl/selectOperImprovReqList.do";
	}	
	

	/**
	 * 운영개선요청 처리완료 메일을 발송한다.
	 * @param EgovOe1OperImprovReqVO
	 * @param HttpServletRequest
	 * @return "/"
	 * @exception Exception
	 */
	
	@Resource(name="oe1MailSender")
    private JavaMailSender oe1MailSender; 

	@RequestMapping(value="/cms/srm/gnrl/sendOperImprovReqResultMail.do")
	public void sendOperImprovReqResultMail(EgovOe1OperImprovReqVO vo, HttpServletRequest request) throws Exception  {			
		EgovOe1OperImprovReqVO vo2 = new EgovOe1OperImprovReqVO();
		vo2 = operImprovReqProcService.selectOperImprovReqResultMailInfo(vo);

		if(vo2.getRequstSttusCode().endsWith("05") || vo2.getRequstSttusCode().endsWith("06")){ //처리상태가  변경완료 || 완료인 경우
			String linkUrl = null;
			String subject = null;
			String text = null;
			linkUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"") + request.getContextPath();
			subject = "등록하신 운영개선요청건이 완료처리되었습니다.";
			text = vo2.getFrstRegisterNm();
			text += "님께서 ";
			text += vo2.getFrstRegisterPnttm();
			text += " 에 요청하신 ";
			text += "<br><br>[ "+ vo2.getOperImprvmRequstSj() +" ]";
			text += " 건에 대한 처리가 <br><br>";
			text += vo2.getProcessComptDe();
			text += " 에 완료 되었습니다.";
			text += "<br><br>아래 안내된 사이트에서 결과를 확인하신 후 처리에 대한 만족도 입력을 부탁드립니다.";
			text += "<br><br><a href='"+ linkUrl +"' target='_blank'><font color='blue'>< eGovframe 커뮤니케이션 > 으로 이동하기</font></a>";
				    	 
	    	MimeMessage msg = oe1MailSender.createMimeMessage();
			try{
				// 발신자
	    		msg.setFrom(new InternetAddress("test.egov.test@gmail.com","SYSTEM"));
	    		// 수신자
		        msg.addRecipient(RecipientType.TO, new InternetAddress(vo2.getTempValue()));	        
		        // 메일제목
		        msg.setSubject(MimeUtility.encodeText(subject,"EUC-KR","B"));
		        // 메일내용
		        msg.setText(text, "euc-kr", "html");

	    		this.oe1MailSender.send(msg);
			}catch(Exception ex){
				//ex.printStackTrace();
				//System.out.println("################################MailException#####################################");
				//System.out.println(ex);
				//System.out.println("##################################################################################");
			}
		}
		//return "forward:/cms/srm/admin/selectOperImprovReqDstbList.do";
	}	

    /** 
	 * 운영개선요청통계
	 * @param EgovOe1OperImprovReqVO
	 * @return "/cms/srm/EgovOperImprovReqStatus"
	 * @exception Exception
	 */
    @RequestMapping(value = "/cms/srm/admin/selectOperImprovReqStatus.do")
	public String selectRiskStatus(
			@ModelAttribute("searchVO") EgovOe1OperImprovReqVO searchVO,
			SessionStatus status, ModelMap model) throws Exception {

        // select  List.
        List<EgovOe1OperImprovReqVO> planList = operImprovReqProcService.selectOperImprovReqStatus(searchVO);
        model.addAttribute("resultList", planList);
          
        // select data
        EgovOe1OperImprovReqVO vo = new EgovOe1OperImprovReqVO();
        vo = operImprovReqProcService.selectOperImprovReqStatusTot(searchVO);
        model.addAttribute("vo", vo);
        
		return "cms/srm/EgovOperImprovReqStatus";
		
	}  
	
}