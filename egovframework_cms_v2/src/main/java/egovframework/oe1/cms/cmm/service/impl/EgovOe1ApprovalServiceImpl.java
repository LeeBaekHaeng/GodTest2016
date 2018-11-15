package egovframework.oe1.cms.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.oe1.cms.cmm.service.DataDic;
import egovframework.oe1.cms.cmm.service.DataDicDetailRequestInfo;
import egovframework.oe1.cms.cmm.service.DataDicRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainClassInfo;
import egovframework.oe1.cms.cmm.service.DomainClassRequestInfo;
import egovframework.oe1.cms.cmm.service.DomainInfo;
import egovframework.oe1.cms.cmm.service.DomainRequestInfo;
import egovframework.oe1.cms.cmm.service.EgovOe1ApprovalService;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1DataDicService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainClService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1DomainService;
import egovframework.oe1.cms.cmm.service.EgovOe1SynonymRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1SynonymService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicRequestService;
import egovframework.oe1.cms.cmm.service.EgovOe1WordDicService;
import egovframework.oe1.cms.cmm.service.MetaDataRequestInfo;
import egovframework.oe1.cms.cmm.service.MetaDataSearchVO;
import egovframework.oe1.cms.cmm.service.SynonymInfo;
import egovframework.oe1.cms.cmm.service.SynonymRequestInfo;
import egovframework.oe1.cms.cmm.service.WordDic;
import egovframework.oe1.cms.cmm.service.WordDicRequestInfo;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 신청심의승인 정보에 대한 비즈니스 구현 클래스
 * 
 * @author 실행환경개발팀 이중호
 * @since 2011.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  이중호          최초 생성
 * 
 * </pre>
 */
@Service("egovOe1ApprovalService")
public class EgovOe1ApprovalServiceImpl extends AbstractServiceImpl implements
		EgovOe1ApprovalService {
	/**
	 * approvalDAO
	 */
	@Resource(name = "approvalDAO")
	private ApprovalDAO approvalDAO;

	/** egovOe1DomainClRequestService */
	@Resource(name = "egovOe1DomainClRequestService")
	private EgovOe1DomainClRequestService egovOe1DomainClRequestService;
	
	/** EgovOe1DomainRequestService */
	@Resource(name = "egovOe1DomainRequestService")
	private EgovOe1DomainRequestService egovOe1DomainRequestService;
	
	/** egovOe1DomainClService */
	@Resource(name = "egovOe1DomainClService")
	private EgovOe1DomainClService egovOe1DomainClService;
	
	/** egovOe1DomainService */
	@Resource(name = "egovOe1DomainService")
	private EgovOe1DomainService egovOe1DomainService;
	
	/** egovOe1WordDicRequestService */
	@Resource(name = "egovOe1WordDicRequestService")
	private EgovOe1WordDicRequestService egovOe1WordDicRequestService;

	/** egovOe1WordDicService */
	@Resource(name = "egovOe1WordDicService")
	private EgovOe1WordDicService egovOe1WordDicService;

	/** egovOe1SynonymRequestService */
	@Resource(name = "egovOe1SynonymRequestService")
	private EgovOe1SynonymRequestService egovOe1SynonymRequestService;

	/** egovOe1SynonymService */
	@Resource(name = "egovOe1SynonymService")
	private EgovOe1SynonymService egovOe1SynonymService;

	/** egovOe1DataDicRequestService */
	@Resource(name = "egovOe1DataDicRequestService")
	private EgovOe1DataDicRequestService egovOe1DataDicRequestService;

	/** egovOe1DataDicService */
	@Resource(name = "egovOe1DataDicService")
	private EgovOe1DataDicService egovOe1DataDicService;

	
	
	/**
	 * 신청 심의승인한다.
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public String insertApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception {

		String rtnMessage = "";
		
		/** 승인('22') OE1104 */
		String processSttusCode = "22";
		
		/** 이력상태코드 OE1102 */
		String sttusCode = "21";
		metaDataRequestInfo.setProcessSttusCode(processSttusCode);

		/** 상세조회 */
		MetaDataRequestInfo retVo = selectApproval(metaDataRequestInfo);
		
		String jobSeCode      = retVo.getJobSeCode();
		String processPrvonsh = retVo.getReqstPrvonsh();
		String reqstProcessId = retVo.getReqstProcessId();
		long   reqstProcessSn = retVo.getReqstProcessSn();
		
		if ("DOMAINCL".equals(jobSeCode)) {
			/** 도메인분류신청상세조회 */
			DomainClassRequestInfo domainClassRequestInfo = new DomainClassRequestInfo();
			DomainClassInfo domainClassInfo = new DomainClassInfo();

			domainClassRequestInfo.setReqstProcessId(reqstProcessId);
			domainClassRequestInfo.setReqstProcessSn(reqstProcessSn);			
			domainClassRequestInfo = egovOe1DomainClRequestService.selectDomainClRequestInfo(domainClassRequestInfo);
			
			/** mapping */
			domainClassInfo.setDomnClNm(domainClassRequestInfo.getDomnClNm());
			
			domainClassInfo.setFrstRegisterId(domainClassRequestInfo.getApplcntId());
			domainClassInfo.setUseAt("Y");
			domainClassInfo.setProcessPrvonsh(processPrvonsh);
			domainClassInfo.setSttusCode(sttusCode);
			
			/** 도메인분류명 중복확인 */
			DomainClassInfo resultDomainClassInfo = egovOe1DomainClService.selectDomainClNmCnt(domainClassInfo);
			int totCnt = 0;
			if (resultDomainClassInfo != null) {
				if (!"".equals(resultDomainClassInfo.getDomnClNm())) {
					totCnt = 1;
					/** 중복 */
					rtnMessage = "등록된 도메인분류명이 있습니다.";
					return rtnMessage;
				}
			}
			
			/** 도메인분류 등록처리 */
			egovOe1DomainClService.insertDomainClInfo(domainClassInfo);

			
		} else if ("DOMAIN".equals(jobSeCode)) {
			/** 도메인신청상세조회 */
			DomainRequestInfo domainRequestInfo = new DomainRequestInfo();
			DomainInfo domainInfo = new DomainInfo();

			domainRequestInfo.setReqstProcessId(reqstProcessId);
			domainRequestInfo.setReqstProcessSn(reqstProcessSn);			
			domainRequestInfo = egovOe1DomainRequestService.selectDomainRequestInfo(domainRequestInfo);
			
			/** mapping */
			domainInfo.setDomnNm(domainRequestInfo.getDomnNm());
			domainInfo.setDomnClId(domainRequestInfo.getDomnClId());
			domainInfo.setDataTy(domainRequestInfo.getDataTy());
			domainInfo.setDataLt(domainRequestInfo.getDataLt());
			domainInfo.setDomnDc(domainRequestInfo.getDomnDc());
			
			domainInfo.setFrstRegisterId(domainRequestInfo.getApplcntId());
			domainInfo.setUseAt("Y");
			domainInfo.setProcessPrvonsh(processPrvonsh);
			domainInfo.setSttusCode(sttusCode);
			
			/** 도메인명 중복확인 */
			DomainInfo resultDomainInfo = egovOe1DomainService.selectDomainNmCnt(domainInfo);
			int totCnt = 0;
			if (resultDomainInfo != null) {
				if (!"".equals(resultDomainInfo.getDomnNm())) {
					totCnt = 1;
					/** 중복 */
					rtnMessage = "등록된 도메인명이 있습니다.";
					return rtnMessage;
				}
			}
			
			/** 도메인 등록처리 */
			egovOe1DomainService.insertDomainInfo(domainInfo);
			
		} else if ("WORDDIC".equals(jobSeCode)) {
			/** 용어사전신청상세조회 */
			WordDicRequestInfo wordDicRequestInfo = new WordDicRequestInfo();
			WordDic wordDic = new WordDic();

			wordDicRequestInfo.setReqstProcessId(reqstProcessId);
			wordDicRequestInfo.setReqstProcessSn(reqstProcessSn);			
			wordDicRequestInfo = egovOe1WordDicRequestService.selectWordDicRequestInfo(wordDicRequestInfo);
			
			/** mapping */
			wordDic.setWordEngAbrvNm(wordDicRequestInfo.getWordEngAbrvNm());
			wordDic.setWordNm(wordDicRequestInfo.getWordNm());
			wordDic.setWordEngNm(wordDicRequestInfo.getWordEngNm());
			wordDic.setWordDc(wordDicRequestInfo.getWordDc());
			
			wordDic.setFrstRegisterId(wordDicRequestInfo.getApplcntId());
			wordDic.setUseAt("Y");
			wordDic.setProcessPrvonsh(processPrvonsh);
			wordDic.setSttusCode(sttusCode);

			/** 용어사전명 중복확인 */
			WordDic resultWordDic = egovOe1WordDicService.selectWordEngAbrvNmCnt(wordDic);
			int totCnt = 0;
			if (resultWordDic != null) {
				if (!"".equals(resultWordDic.getWordEngAbrvNm())) {
					totCnt = 1;
					/** 중복 */
					rtnMessage = "등록된 용어가 있습니다.";
					return rtnMessage;
				}
			}

			/** 용어사전 등록처리 */
			egovOe1WordDicService.insertWordDic(wordDic);

			
		} else if ("SYNONYM".equals(jobSeCode)) {
			/** 동의어신청상세조회 */
			SynonymRequestInfo synonymRequestInfo = new SynonymRequestInfo();
			SynonymInfo synonymInfo = new SynonymInfo();

			synonymRequestInfo.setReqstProcessId(reqstProcessId);
			synonymRequestInfo.setReqstProcessSn(reqstProcessSn);			
			synonymRequestInfo = egovOe1SynonymRequestService.selectSynonymRequestInfo(synonymRequestInfo);
			
			/** mapping */
			synonymInfo.setWordEngAbrvNm(synonymRequestInfo.getWordEngAbrvNm());
			synonymInfo.setSynonmNm(synonymRequestInfo.getSynonmNm());
			
			synonymInfo.setFrstRegisterId(synonymRequestInfo.getApplcntId());
			synonymInfo.setUseAt("Y");
			synonymInfo.setProcessPrvonsh(processPrvonsh);
			synonymInfo.setSttusCode(sttusCode);
			
			/** 동의어명 중복확인 */
			SynonymInfo resultSynonymInfo = egovOe1SynonymService.selectSynonmNmCnt(synonymInfo);
			int totCnt = 0;
			if (resultSynonymInfo != null) {
				if (!"".equals(resultSynonymInfo.getSynonmNm())) {
					totCnt = 1;
					/** 중복 */
					rtnMessage = "등록된 동의어가 있습니다.";
					return rtnMessage;
				}
			}
			
			/** 동의어 등록처리 */
			egovOe1SynonymService.insertSynonym(synonymInfo);
		} else if ("DATADIC".equals(jobSeCode)) {
			/** 자료사전신청상세조회 */
			DataDicRequestInfo dataDicRequestInfo = new DataDicRequestInfo();
			DataDic dataDic = new DataDic();

			dataDicRequestInfo.setReqstProcessId(reqstProcessId);
			dataDicRequestInfo.setReqstProcessSn(reqstProcessSn);			
			dataDicRequestInfo = egovOe1DataDicRequestService.selectDataDicRequestInfo(dataDicRequestInfo);
			
			/** 자료사전상세 신청 목록조회 */
			DataDicDetailRequestInfo vo = new DataDicDetailRequestInfo();
			vo.setReqstProcessId(reqstProcessId);
			vo.setReqstProcessSn(reqstProcessSn);
			List<DataDicDetailRequestInfo> dataDicDetailRequestInfoList = egovOe1DataDicRequestService.selectDataDicDetailRequestList(vo);
			List<String> wordNmList = new ArrayList<String>();
			List<String> wordEngAbrvNmList = new ArrayList<String>();
			for(int i = 0; i < dataDicDetailRequestInfoList.size(); i ++){
				wordNmList.add(dataDicDetailRequestInfoList.get(i).getWordNm());
				wordEngAbrvNmList.add(dataDicDetailRequestInfoList.get(i).getWordEngAbrvNm());
			}
			
			/** mapping */
			dataDic.setDtaDicaryNm(dataDicRequestInfo.getDtaDicaryNm());
			dataDic.setDtaDicaryEngNm(dataDicRequestInfo.getDtaDicaryEngNm());
			dataDic.setDomnNm(dataDicRequestInfo.getDomnNm());
			dataDic.setDataTy(dataDicRequestInfo.getDataTy());
			dataDic.setDataLt(dataDicRequestInfo.getDataLt());
			dataDic.setWordNmList(wordNmList.toArray(new String[wordNmList.size()]));
			dataDic.setWordEngAbrvNmList(wordEngAbrvNmList.toArray(new String[wordEngAbrvNmList.size()]));
			
			dataDic.setFrstRegisterId(dataDicRequestInfo.getApplcntId());
			dataDic.setUseAt("Y");
			dataDic.setProcessPrvonsh(processPrvonsh);
			dataDic.setSttusCode(sttusCode);
			
			/** 자료사전명 중복확인 */
			DataDic resultDataDic = egovOe1DataDicService.selectDataDicNmCnt(dataDic);
	
			int totCnt = 0;
			if (resultDataDic != null) {
				if (!"".equals(resultDataDic.getDtaDicaryId())) {
					totCnt = 1;
					/** 중복 */
					rtnMessage = "등록된 자료사전이 있습니다.";
					return rtnMessage;
				}
			}
			
			/** 자료사전 등록처리 */
			egovOe1DataDicService.insertDataDic(dataDic);
			
		} else {
			// 처리없음
			log.info("none");
		}
		
		/** 메타데이터신청 승인처리 */
		approvalDAO.insertApproval(metaDataRequestInfo);
		
		return rtnMessage;
	}

	/**
	 * 신청 심의반려한다.
	 * 
	 * @param approval
	 * @exception Exception
	 */
	public void insertReject(MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		/** 반려('21') OE1104 */
		String processSttusCode = "21";
		
		/** 메타데이터신청 반려처리 */
		metaDataRequestInfo.setProcessSttusCode(processSttusCode);
		approvalDAO.insertApproval(metaDataRequestInfo);
	}
	
	/**
	 * 신청심의승인 정보를 조회한다.
	 * 
	 * @return Approval
	 * @param approval
	 * @exception Exception
	 */
	public MetaDataRequestInfo selectApproval(MetaDataRequestInfo metaDataRequestInfo) throws Exception {
		return approvalDAO.selectApproval(metaDataRequestInfo);
	}

	/**
	 * 신청심의승인 리스트 총 수를 조회한다.
	 * 
	 * @return int
	 * @param approval
	 * @exception Exception
	 */
	public int selectApprovalListTotCnt(MetaDataSearchVO searchVO) throws Exception {
		return approvalDAO.selectApprovalListTotCnt(searchVO);
	}

	/**
	 * 신청심의승인 리스트를 조회한다.
	 * 
	 * @return List
	 * @param approval
	 * @exception Exception
	 */
	public List selectApprovalList(MetaDataSearchVO searchVO) throws Exception {
		return approvalDAO.selectApprovalList(searchVO);
	}



}