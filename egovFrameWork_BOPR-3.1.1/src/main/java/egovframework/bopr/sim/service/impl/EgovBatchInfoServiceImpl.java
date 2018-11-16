package egovframework.bopr.sim.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import egovframework.bopr.com.EgovSchdulUtl;
import egovframework.bopr.com.FileUtl;
import egovframework.bopr.com.FtpUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.com.StringUtl;
import egovframework.bopr.jim.service.EgovFtpIntrlManageService;
import egovframework.bopr.jim.service.FtpIntrlManageVO;
import egovframework.bopr.sim.service.BatchAtchFileVO;
import egovframework.bopr.sim.service.BatchBeanVO;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.bopr.sim.service.EgovBatchInfoService;
import egovframework.bopr.sim.service.EgovBatchRegistException;
import egovframework.bopr.sim.service.EgovSchdulService;
import egovframework.bopr.sim.service.SchdulVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.FileManageDAO;
import egovframework.com.utl.sim.service.EgovFileScrty;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 배치정보관리에 관한 ServiceImpl 클래스
 * @author SDS 이병권
 * @since 2012.07.09
 * @version 0.9
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      수정자    수정내용
 *  ----------  --------  ---------------------------
 *  2012.07.09  이병권    최초 생성
 *
 * </pre>
 */

@Service("egovBatchInfoService")
public class EgovBatchInfoServiceImpl extends EgovAbstractServiceImpl implements EgovBatchInfoService {

	/** 배치정보 DAO */
	@Resource(name="BatchInfoDAO")
	private BatchInfoDAO batchInfoDAO;

	/** 파일관리 DAO */
	@Resource(name="FileManageDAO")
	private FileManageDAO fileManageDAO;

	/** 파일관리 UTIL */
	@Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil egovFileMngUtil;

	/** 파일관리 Service */
	@Resource(name="EgovFileMngService")
	private EgovFileMngService egovFileMngService;

	/** FTP 정보 관리 Service */
	@Resource(name="egovFtpIntrlManageService")
	private EgovFtpIntrlManageService egovFtpIntrlManageService;

	/** 일정 관리 Service */
	@Resource(name="egovSchdulService")
	private EgovSchdulService egovSchdulService;

	/** 일정 Utility Class */
	@Resource(name="egovSchdulUtl")
	private EgovSchdulUtl egovSchdulUtl;

	/** Message Service */
	@Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchInfoServiceImpl.class);

	/**
	 * 배치정보관리 목록 조회
	 * @param searchVO BatchInfoVO
	 * @return List<BatchInfoVO>
	 * @exception Exception
	 */
	public List<BatchInfoVO> selectBatchInfoList(BatchInfoVO searchVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<BatchInfoVO> batchInfoList;		// 조회된 배치정보 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 Parameter 들의 존재 여부에 따라 검색 Parameter 재설정
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●" + "selectBatchInfoList" + "[" +   "searchCondition=" + searchVO.getSearchCondition()
                                                   	+ ", serachKeyword=" + searchVO.getSearchKeyword()
                                                   	+ ", pageIndex=" + searchVO.getPageIndex()
                                                   	+ ", pageUnit=" + searchVO.getPageUnit()
                                                   	+ ", pageSize=" + searchVO.getPageSize()
                                              + "]");
		/*
			STEP 1. 조회할 페이지 정보 설정
		 */
		SearchUtl.getPaginationInfo(searchVO);

		/*
		 	STEP 2. 검색 조건 사용 여부 설정
		 	    - STEP 2.1. 공통 검색 Parameter 사용 여부
		 	    - STEP 2.2. 배치배포 전용 검색 Parameter 사용 여부 - JOB_SE_CODE
		 */
		SearchUtl.initSearchParameter(searchVO);

		if (StringUtils.hasText(searchVO.getJobSeCode()))
		{
			searchVO.setSearchUseYn("Y");
		}

		/*
		 	STEP 3. List 조회 Service 호출
		 */
		batchInfoList = batchInfoDAO.selectBatchInfoList(searchVO);
			LOGGER.debug("{}", batchInfoList);

		/*
			SETP 4. 조회한 List Return
		*/
		return batchInfoList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보관리 목록 Page List 정보 조회
	 * @param batchInfoVO BatchInfoVO
	 * @return PaginationInfo
	 * @throws Exception
	 */
	public PaginationInfo selectBatchInfoListPageInfo(BatchInfoVO searchVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치정보 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
			STEP 3. 페이징 VO 반환
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. 배치정보 목록 Total Count 조회
		 */
		totalCount = batchInfoDAO.selectBatchInfoListTotcnt(searchVO);

		/*
		 	STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		paginationInfo = SearchUtl.getPaginationInfo(searchVO);
		paginationInfo.setTotalRecordCount(totalCount);

		/*
		 	STEP 3. 페이징 VO 반환
		 */
		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보 상세 데이터 조회
	 * @param BatchInfoVO batchInfoVO
	 * @return BatchInfoVO
	 * @exception Exception
	 */
	public BatchInfoVO selectBatchInfo(BatchInfoVO searchVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		BatchInfoVO batchInfo;		// 조회된 배치정보 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 배치정보 상세 데이터 조회
		 	STEP 2. 배치정보 상세 데이터가 존재하면 첨부파일 정보 조회
		 		- STEP 2.1. 배치정보 상세 데이터가 null 인지 여부 검사
		 		- STEP 2.2. 배치 첨부 파일 목록 조회
		 		- STEP 2.3. 배치 첨부 파일의 상세 정보 조회
		 	STEP 3. 조회 결과 return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●" + "selectBatchInfo" + "[" +   "batchId=" + searchVO.getBatchId()
                                                + ", searchCondition=" + searchVO.getSearchCondition()
                                                + ", serachKeyword=" + searchVO.getSearchKeyword()
                                                + ", pageIndex=" + searchVO.getPageIndex()
                                                + ", pageUnit=" + searchVO.getPageUnit()
                                                + ", pageSize=" + searchVO.getPageSize()
                                          + "]");
		/*
		 	STEP 1. 배치정보 상세 데이터 조회
		 */
		batchInfo = batchInfoDAO.selectBatchInfo(searchVO);

		/*
		 	STEP 2. 배치정보 상세 데이터가 존재하면 첨부파일 정보 조회
		 		- STEP 2.1. 배치정보 상세 데이터가 null 인지 여부 검사
		 		- STEP 2.2. 배치 첨부 파일 목록 조회
		 		- STEP 2.3. 배치 첨부 파일의 상세 정보 조회
		 */
		if (batchInfo != null)				// STEP 2.1. 배치정보 상세 데이터가 null 인지 여부 검사
		{
			batchInfo.setBatchAtchFileVOList(batchInfoDAO.selectBatchAtchFileList(searchVO));	// STEP 2.2. 배치 첨부 파일 목록 조회

			/*
			 	STEP 2.3. 배치 첨부 파일의 상세 정보 조회
			 */
			for (int index = 0; index < batchInfo.getBatchAtchFileVOList().size(); index++)
			{
				FileVO fileVO = new FileVO();
				fileVO.setAtchFileId(batchInfo.getBatchAtchFileVOList().get(index).getAtchFileId());
				batchInfo.getBatchAtchFileVOList().get(index).setAtchFileDetailList(fileManageDAO.selectFileInfs(fileVO));
			}
		}

		/*
		 	STEP 3. 조회 결과 return
		 */
		return batchInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보 복수 삭제
	 * @param batchIds String
	 * @param ftpPassword String
	 * @throws Exception
	 */
	public void deleteBatchInfo(String batchIds, String ftpPassword) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. 입력 받은 배치ID ',' 구분자로 구분하여 List 객체로 변환
		 	STEP 2. 배치 정보 삭제 서비스 호출
		 		- STEP 2.1. 배치정보VO 생성하여 입력 받은 배치ID 저장
		 		- STEP 2.2. 배치 정보 삭제 서비스 호출
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●deleteBatchinfo[batchIds={}]", batchIds);

		/*
		 	STEP 1. 입력 받은 배치ID ',' 구분자로 구분하여 List 객체로 변환
		 */
		List<String> batchIdList = StringUtl.stringToList(batchIds, ",");

		/*
		 	STEP 2. 배치 정보 삭제 서비스 호출
		 		- STEP 2.1. 배치정보VO 생성하여 입력 받은 배치ID, FTP 비밀번호 저장
		 		- STEP 2.2. 배치 정보 삭제 서비스 호출
		 */
		for (int index = 0; index < batchIdList.size(); index++)
		{
			BatchInfoVO batchInfoVO = new BatchInfoVO();         // STEP 2.1. 배치정보VO 생성하여 입력 받은 배치ID, FTP 비밀번호 저장
			batchInfoVO.setBatchId(batchIdList.get(index));          // 배치ID
			batchInfoVO.setFtpPassword(ftpPassword);                 // FTP 비밀번호
			this.deleteBatchInfo(batchInfoVO);                   // STEP 2.2. 배치 정보 삭제 서비스 호출
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보 삭제
	 * @param batchInfoVO BatchInfoVO
	 * @exception Exception
	 */
	public void deleteBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("●deleteBatchinfo[batchId={}]", batchInfoVO.getBatchId());

		/*
		 	STEP 1. 첨부파일 삭제
		 	    - STEP 1.1. 첨부파일 목록 조회
		 	    - STEP 1.2. 첨부파일 삭제
		 	        - STEP 1.2.1. 첨부파일이 설정 파일인 경우 서버에 등록된 파일도 같이 삭제
		 	        - STEP 1.2.2. 첨부파일이 배치 파일인 경우 DB 상의 데이터만 삭제(파일 공유하는 프로그램이 있을 수 있으므로)
		 */
		List<BatchAtchFileVO> atchFileList = batchInfoDAO.selectBatchAtchFileList(batchInfoVO);      // STEP 1.1. 첨부파일 목록 조회

		for (BatchAtchFileVO atchFile : atchFileList)      // STEP 1.2. 첨부파일 삭제
		{
			atchFile.setBatchId(batchInfoVO.getBatchId());

			if ("S".equals(atchFile.getAtchSeCode()))                                  // STEP 1.2.1. 첨부파일이 설정 파일인 경우 서버에 등록된 파일도 같이 삭제
			{
				this.deleteBatchAtchFile(atchFile, batchInfoVO.getFtpPassword());
			}
			else                                                                       // STEP 1.2.2. 첨부파일이 배치 파일인 경우 DB 상의 데이터만 삭제(파일 공유하는 프로그램이 있을 수 있으므로)
			{
				batchInfoDAO.deleteBatchAtchFile(atchFile);
			}
		}

		/*
		 	STEP 2. 배치 Bean 삭제
		 	    - STEP 2.1. 배치 Bean 삭제 위한 파라미터 VO 설정
		 	    - STEP 2.2. 배치 Bean 삭제 Service 호출
		 */
		BatchParamtrVO paramtr = new BatchParamtrVO();    // STEP 2.1. 배치 Bean 삭제 위한 파라미터 VO 설정
		paramtr.setBatchId(batchInfoVO.getBatchId());

		batchInfoDAO.deleteBatchBean(batchInfoVO);        // STEP 2.2. 배치 Bean 삭제 Service 호출

		/*
		 	STEP 3. 배치 삭제
		 	    - STEP 3.1. 배치정보 삭제
		 	    - STEP 3.2. 배치정보 삭제 히스토리 기록
		 */
		batchInfoDAO.delelteBatchInfo(batchInfoVO);          // STEP 3.1. 배치정보 삭제
		batchInfoDAO.insertBatchInfoHistory(batchInfoVO);    // STEP 3.2. 배치정보 삭제 히스토리 기록

		/*
		 	STEP 4. 일정삭제 서비스 호출
		 	    - STEP 4.1. 일정 List 조회
		 	    - STEP 4.2. 일정 삭제 서비스 호출
		 */
		List<SchdulVO> schdulList = egovSchdulService.selectSchdulList(batchInfoVO.getBatchId());    // STEP 4.1. 일정 List 조회

		for (SchdulVO schdul : schdulList)                                                           // STEP 4.2. 일정 삭제 서비스 호출
		{
			egovSchdulUtl.deleteSchdul(schdul);
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보 신규 등록
	 * @param batchInfoVO BatchInfoVO
	 * @throws Exception
	 */
	public void insertBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. 배치정보 등록
		 	    - STEP 1.1. INSERT INTO TN_BATCH
		 	    - STEP 1.2. INSERT INTO TH_BATCH
		 	SETP 2. 첨부파일 등록
		 	    - STEP 2.1. batchAtchFile 객체에 정보 설정
		 	    - STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("\n▶▶▶insertBatchInfo=[" + "batchId=" + batchInfoVO.getBatchId()
				                         + ", batchNm=" + batchInfoVO.getBatchNm()
				                         + ", batchDc=" + batchInfoVO.getBatchDc()
				                         + ", jobDlbrtNo=" + batchInfoVO.getJobDlbrtNo()
				                         + ", " + batchInfoVO.getBatchAtchFileVOList() + "]");

		/*
		 	STEP 1. 배치정보 등록
		 	    - STEP 1.1. INSERT INTO TN_BATCH
		 	    - STEP 1.2. INSERT INTO TH_BATCH
		 */
		batchInfoDAO.insertBatchInfo(batchInfoVO);				// STEP 1.1. INSERT INTO TN_BATCH
		batchInfoDAO.insertBatchInfoHistory(batchInfoVO);		// STEP 1.2. INSERT INTO TH_BATCH

		/*
		 	SETP 2. 첨부파일 등록
		 	    - STEP 2.1. batchAtchFile 객체에 정보 설정
		 	    - STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 */
		for (BatchAtchFileVO batchAtchFile : batchInfoVO.getBatchAtchFileVOList())
		{
			/*
			 	STEP 2.1. batchAtchFile 객체에 정보 설정
			 */
			batchAtchFile.setBatchId(batchInfoVO.getBatchId());						// 배치ID
			batchAtchFile.setFrstRegisterId(batchInfoVO.getFrstRegisterId());		// 최초등록자ID
			batchAtchFile.setLastUpdusrId(batchInfoVO.getLastUpdusrId());			// 최종수정자ID

			/*
			 	STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
			 */
			String atchProcessSeCode = batchAtchFile.getProcessSeCode();

			if ("INS".equals(atchProcessSeCode))
			{
				this.insertBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// INSERT
			}
			else if ("UPT".equals(atchProcessSeCode))
			{
				this.updateBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// UPDATE
			}
			else if ("DEL".equals(atchProcessSeCode))
			{
				this.deleteBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// DELETE
			}
			else
			{
				throw new EgovBizException("첨부파일의 프로세스구분코드(PROCESS_SE_CODE)가 잘못 되었습니다.[processSeCode=" + atchProcessSeCode + "]");
			}
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치정보관리 대상 정보 수정
	 * @param BatchInfoVO batchInfoVO
	 * @exception Exception
	 */
	public void updateBatchInfo(BatchInfoVO batchInfoVO) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. 배치정보 수정
		 	    - STEP 1.1. UPDATE TN_BATCH
		 	    - STEP 1.2. INSERT TH_BATCH
		 	STEP 2. STEP 2.2. 변경된 첨부파일 등록 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 	    - STEP 2.1. batchAtchFile 객체에 정보 설정
		 	    - STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("\n▶▶▶" + "updateBatchInfo=[" + "batchId=" + batchInfoVO.getBatchId()
                + ", batchNm=" + batchInfoVO.getBatchNm()
                + ", batchDc=" + batchInfoVO.getBatchDc()
                + ", jobDlbrtNo=" + batchInfoVO.getJobDlbrtNo()
                + ", " + batchInfoVO.getBatchAtchFileVOList() + "]");

		/*
		 	STEP 1. 배치정보 수정
		 	    - STEP 1.1. UPDATE TN_BATCH
		 	    - STEP 1.2. INSERT TH_BATCH
		 */
		batchInfoDAO.updateBatchInfo(batchInfoVO);				// STEP 1.1. UPDATE TN_BATCH
		batchInfoDAO.insertBatchInfoHistory(batchInfoVO);		// STEP 1.2. INSERT TH_BATCH

		if ( !StringUtils.hasText(batchInfoVO.getFtpPassword()) )
		{
			return;
		}

		/*
		 	STEP 2. STEP 2.2. 변경된 첨부파일 등록 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 	    - STEP 2.1. batchAtchFile 객체에 정보 설정
		 	    - STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
		 */
		for (BatchAtchFileVO batchAtchFile : batchInfoVO.getBatchAtchFileVOList())
		{
			/*
			 	STEP 2.1. batchAtchFile 객체에 정보 설정
			 */
			batchAtchFile.setBatchId(batchInfoVO.getBatchId());						// 배치ID
			batchAtchFile.setFrstRegisterId(batchInfoVO.getFrstRegisterId());		// 최초등록자ID
			batchAtchFile.setLastUpdusrId(batchInfoVO.getLastUpdusrId());			// 최종수정자ID

			/*
			 	STEP 2.2. 첨부파일 등록 서비스 호출 (첨부 프로세스 구분 코드에 따라 다른 서비스 호출)
			 */
			String atchProcessSeCode = batchAtchFile.getProcessSeCode();

			if ("INS".equals(atchProcessSeCode))
			{
				this.insertBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// INSERT
			}
			else if ("UPT".equals(atchProcessSeCode))
			{
				this.updateBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// UPDATE
			}
			else if ("DEL".equals(atchProcessSeCode))
			{
				this.deleteBatchAtchFile(batchAtchFile, batchInfoVO.getFtpPassword());		// DELETE
			}
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 첨부파일 등록
	 * @param batchAtchFileVO
	 * @throws Exception
	 */
	private void insertBatchAtchFile(BatchAtchFileVO batchAtchFileVO, String ftpPassword) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		FTPClient ftpClient = null;			// FTP 접속 Client 객체

		String atchSeCode;					// 첨부구분코드(S:설정파일, B:배치파일)
		FileVO atchFile;					// 첨부파일 VO
		FileVO fileSearchVO;				// 첨부파일 조회 정보 VO

		FtpIntrlManageVO ftpIntrlManageVO;		// FTP 접속 정보 VO

		/*------------------------------ field ------------------------------ field ------------------------------*/

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. 첨부파일 정보 조회
		 	    - STEP 1.1. 첨부파일 정보 조회 용 fileSearchVO 생성
		 	    - STEP 1.2. fileSearchVO에 atchFileId 설정
		 	    - STEP 1.3. FileManageDAO.selectFileInfs 서비스 호출하여 조회 결과의 첫 번째 값 추출
		 	                (atchFileId 당 하나의 파일만 저장하도록 함 - this.getBatchAtchFileList)
		 	STEP 2. FTP 접속 정보 VO 객체 생성
		 	STEP 3. 첨부구분코드 추출
		 	STEP 4. 첨부구분코드 값에 따라 FTP 접속 정보 VO 객체의 ftpIntrlckNo 값 구분 (FTP_0000000000000000:설정파일, FTP_1000000000000000:배치파일)
		 	    - STEP 4.1. FTP 접속 정보 VO 객체의 ftpIntrlckNo 값 설정
		 	    - STEP 4.2. 첨부구분이 설정파일인 경우 설정 xml 파일의 job 노드 id, bean 노드 id 변경
		 	STEP 5. FTP 접속 정보 조회
		 	STEP 6. FTP 접속 Client 생성
		 	STEP 7. FTP Client 에 파일 전송
		 	STEP 8. 첨부 파일 정보 DB에 INSERT
		 	STEP 9. FTPClient 종료
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		LOGGER.debug("▶▶▶insertBatchAtchFile[{}]", batchAtchFileVO);

		try
		{
			/*
			 	STEP 1. 첨부파일 정보 조회
		 	    - STEP 1.1. 첨부파일 정보 조회 용 fileSearchVO 생성
		 	    - STEP 1.2. fileSearchVO에 atchFileId 설정
		 	    - STEP 1.3. FileManageDAO.selectFileInfs 서비스 호출하여 조회 결과의 첫 번째 값 추출
		 	                (atchFileId 당 하나의 파일만 저장하도록 함 - this.getBatchAtchFileList)
			 */
			fileSearchVO = new FileVO();										// STEP 1.1. 첨부파일 정보 조회 용 fileSearchVO 생성
			fileSearchVO.setAtchFileId(batchAtchFileVO.getAtchFileId());		// STEP 1.2. fileSearchVO에 atchFileId 설정
			atchFile = fileManageDAO.selectFileInfs(fileSearchVO).get(0);		// STEP 1.3. FileManageDAO.selectFileInfs 서비스 호출하여 조회 결과의 첫 번째 값 추출

			/*
			 	STEP 2. FTP 접속 정보 VO 객체 생성
			 */
			ftpIntrlManageVO = new FtpIntrlManageVO();

			/*
			 	STEP 3. 첨부구분코드 추출
			 */
			atchSeCode = batchAtchFileVO.getAtchSeCode();

			/*
			 	STEP 4. 첨부구분코드 값에 따라 FTP 접속 정보 VO 객체의 ftpIntrlckNo 값 구분 (FTP_0000000000000000:설정파일, FTP_1000000000000000:배치파일)
			 	    - STEP 4.1. FTP 접속 정보 VO 객체의 ftpIntrlckNo 값 설정
			 	    - STEP 4.2. 첨부구분이 설정파일인 경우 설정 xml 파일의 job 노드 id, bean 노드 id 변경
			 */

			/*
			 	STEP 4.1. FTP 접속 정보 VO 객체의 ftpIntrlckNo 값 설정
			 */
			ftpIntrlManageVO.setFtpIntrlckNo(EgovProperties.getProperty("FTP.BATCH.id"));
			batchAtchFileVO.setBatchFileNm(atchFile.getOrignlFileNm());

			/*
			 	STEP 5. FTP 접속 정보 조회
			 */
			FtpIntrlManageVO ftpInfo = egovFtpIntrlManageService.selectFtpIntrl(ftpIntrlManageVO);

			String wdtbPath;
			if ("S".equals(atchSeCode))
			{
				wdtbPath = ftpInfo.getCfgWdtbPath();

				/*
				 	STEP 4.2. 첨부구분이 설정파일인 경우 설정 xml 파일의 job 노드 id, bean 노드 id 변경
				 */
				this.getBatchParamtr(atchFile.getFileStreCours(), atchFile.getStreFileNm(), batchAtchFileVO);

				FileUtl fileUtl = new FileUtl();
				String jobId = fileUtl.getXmlJobId(atchFile.getFileStreCours(), atchFile.getStreFileNm());
				List<String> stepIdList = fileUtl.getXmlStepId(atchFile.getFileStreCours(), atchFile.getStreFileNm());

				List<String> beanIdList = new ArrayList<String>();	// TN_BATCH_BEAN 테이블에 저장할 bean ID 리스트

				beanIdList.add(jobId);
				beanIdList.addAll(stepIdList);

				if (!CollectionUtils.isEmpty(beanIdList))
				{
					this.insertBatchBean(batchAtchFileVO.getBatchId(), beanIdList);
				}
			}
			else
			{
				wdtbPath = ftpInfo.getBatchWdtbPath();
			}

			/*
			 	STEP 6. FTP 접속 Client 생성
			 */
			if (!EgovFileScrty.encryptPassword(ftpPassword).equals(ftpInfo.getPassword()))
			{
				throw new EgovBatchRegistException("패스워드가 맞지 않습니다." + "[id:" + ftpInfo.getUserId() + "]");
			}
			ftpClient = FtpUtl.getConnection(   ftpInfo.getFtpAdres().substring(0, ftpInfo.getFtpAdres().indexOf(":"))
					                          , Integer.valueOf(ftpInfo.getFtpAdres().substring(ftpInfo.getFtpAdres().indexOf(":")+1, ftpInfo.getFtpAdres().length()))
					                          , ftpInfo.getUserId()
					                          , ftpPassword );

			LOGGER.debug("●●●insertBatchAtchFile.sendFile" + "[" +   "fileStreCours=" + atchFile.getFileStreCours()
					                                          + ", strFileNm=" + atchFile.getStreFileNm()
					                                          + ", wdtbPath=" + wdtbPath + "/" + batchAtchFileVO.getWdtbPath()
					                                          + ", batchFileNm=" + batchAtchFileVO.getBatchFileNm()
					                                    + "]");
			/*
			 	STEP 7. FTP Client 에 파일 전송
			 */
			FtpUtl.sendFile(  ftpClient
					        , atchFile.getFileStreCours()
					        , atchFile.getStreFileNm(), wdtbPath + "/" + batchAtchFileVO.getWdtbPath()
					        , batchAtchFileVO.getBatchFileNm()
					        , true );


			/*
			 	STEP 8. 첨부 파일 정보 DB에 INSERT
			 */
			batchInfoDAO.insertBatchAtchFile(batchAtchFileVO);
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
			throw e;
		}
		finally
		{
			/*
			 	STEP 9. FTPClient 종료
			 */
			if (ftpClient != null)
			{
				ftpClient.disconnect();
			}
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	private void getBatchParamtr(String streCours, String streFileNm, BatchAtchFileVO batchAtchFileVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		DocumentBuilderFactory docBuilderFactory;		// DocumentBuilder 생성하기 위한 DocumentBuilderFactory 객체
		DocumentBuilder docBuilder;						// 첨부파일 Document 객체 생성하기 위한 DocumentBuilder 객체
		Document doc;									// 첨부파일 변경을 위한 Document 객체

		String streFilePath = streCours + streFileNm;

		/*------------------------------ field ------------------------------ field ------------------------------*/

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. 첨부파일의 Document 객체 생성
		 	    - STEP 1.1. DocumentBuilerFactory 객체 생성
		 	    - STEP 1.2. DocuementBuiler 객체 생성
		 	    - STEP 1.3. Document 객체 생성
		 	STEP 2. job 노드의 id batchId로 변경
			 	- STEP 2.1. job 노드 List 호출
			 	- STEP 2.2. job 노드 id 변경
			STEP 3. bean 노드의 id 값에 batchId 추가 (batchId.기존Id)
		 	    - STEP 3.1. bean 노드 List 호출
		 	    - STEP 3.2. bean id 값이 존재할 경우 id 값에 batchId 추가
			 	      - STEP 3.2.1. bean 노드 호출
			 	      - STEP 3.2.2. bean 노드의 id 값 추출
			 	      - STEP 3.2.3. bean 노드이 id 값 존재할 경우 batchId 추가
			STEP 4. chunk 노드의 reader, writer, processor 값에 batchId 추가
		 	    - STEP 4.1. chunk 노드 List 호출
		 	    - STEP 4.2. reader, writer, processor 값 변경
		 	        - STEP 4.2.1. chunk 노드 호출
		 	        - STEP 4.2.2. reader 값 변경
		 	        - STEP 4.2.3. writer 값 변경
		 	        - STEP 4.2.4. processor 값 변경
		 	STEP 5. 변경된 Document 반영
		 	    - STEP 5.1. TransformerFactory 객체 생성
		 	    - STEP 5.2. Transformer 객체 생성
		 	    - STEP 5.3. 변경된 Document 반영한 DOMSource 객체 생성
		 	    - STEP 5.4. 설정파일의 StreamResult 객체 생성
		 	    - STEP 5.5. 변경된 Document 반영
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		try
		{
			/*
			 	STEP 1. 첨부파일의 Document 객체 생성
		 	    - STEP 1.1. DocumentBuilerFactory 객체 생성
		 	    - STEP 1.2. DocuementBuiler 객체 생성
		 	    - STEP 1.3. Document 객체 생성
			 */
			docBuilderFactory = DocumentBuilderFactory.newInstance();								// STEP 1.1. DocumentBuilerFactory 객체 생성
			docBuilder = docBuilderFactory.newDocumentBuilder();									// STEP 1.2. DocuementBuiler 객체 생성
			doc = docBuilder.parse(new BufferedInputStream(new FileInputStream(streFilePath)));		// STEP 1.3. Document 객체 생성
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
			throw new EgovBatchRegistException("첨부파일이 서버에 존재하지 않습니다.");
		}

		try
		{
			NodeList nodeList = doc.getElementsByTagName("property");

			List<BatchParamtrVO> paramtrList = new ArrayList<BatchParamtrVO>();

			if (nodeList != null && nodeList.getLength() > 0)
			{
				for (int index = 0; index < nodeList.getLength(); index++)
				{
					Element property = (Element)nodeList.item(index);

					String value = property.getAttribute("value");

					if (value.startsWith("#{jobParameters") && value.endsWith("}"))
					{
						String paramNm;

						if (value.indexOf("'") > 0)
						{
							paramNm = value.substring(value.indexOf("'")+1, value.lastIndexOf("'"));
						}
						else if (value.indexOf("[") > 0 && value.indexOf("]") > 0)
						{
							paramNm = value.substring(value.indexOf("[")+1, value.indexOf("]"));
						}
						else
						{
							paramNm = value;
						}

						BatchParamtrVO paramtr = new BatchParamtrVO();
						paramtr.setBatchId(batchAtchFileVO.getBatchId());
						paramtr.setParamtrNm(paramNm);
						paramtr.setFrstRegisterId(batchAtchFileVO.getFrstRegisterId());
						paramtr.setLastUpdusrId(batchAtchFileVO.getLastUpdusrId());
						paramtrList.add(paramtr);
					}
				}
			}

			LOGGER.debug("▶▶▶paramtrList=[{}]", paramtrList);

			BatchParamtrVO deleteBatchParam = new BatchParamtrVO();
			deleteBatchParam.setBatchId(batchAtchFileVO.getBatchId());

			batchInfoDAO.deleteBatchParamtrs(deleteBatchParam);

			for (BatchParamtrVO paramtr : paramtrList)
			{
				batchInfoDAO.insertBatchParamtr(paramtr);

			}
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
			throw new EgovBatchRegistException(e.getMessage());
		}
	}

	private void updateBatchAtchFile(BatchAtchFileVO batchAtchFileVO, String ftpPassword) throws Exception
	{
		this.deleteBatchAtchFile(batchAtchFileVO, ftpPassword);
		this.insertBatchAtchFile(batchAtchFileVO, ftpPassword);
	}

	private void deleteBatchAtchFile(BatchAtchFileVO batchAtchFileVO, String ftpPassword) throws Exception
	{
		FTPClient ftpClient = null;
		String wdtbPath;

		try
		{
			BatchAtchFileVO presentFileVO = batchInfoDAO.selectBatchAtchFile(batchAtchFileVO);
			FtpIntrlManageVO ftpIntrlManageVO = new FtpIntrlManageVO();

			if (presentFileVO == null)
			{
				return;
			}


			/*
			 	STEP 5. FTP 접속 정보 조회
			 */
			ftpIntrlManageVO.setFtpIntrlckNo(EgovProperties.getProperty("FTP.BATCH.id"));
			FtpIntrlManageVO ftpInfo = egovFtpIntrlManageService.selectFtpIntrl(ftpIntrlManageVO);

			if ("S".equals(presentFileVO.getAtchSeCode()))
			{
				wdtbPath = ftpInfo.getCfgWdtbPath();

				BatchInfoVO batchInfoVO = new BatchInfoVO();
				batchInfoVO.setBatchId(batchAtchFileVO.getBatchId());
				batchInfoDAO.deleteBatchBean(batchInfoVO);
			}
			else
			{
				wdtbPath = ftpInfo.getBatchWdtbPath();
			}

			/*
			 	STEP 6. FTP 접속 Client 생성
			 */
			if (!EgovFileScrty.encryptPassword(ftpPassword).equals(ftpInfo.getPassword()))
			{
				throw new EgovBatchRegistException("패스워드가 맞지 않습니다." + "[id:" + ftpInfo.getUserId() + "]");
			}
			ftpClient = FtpUtl.getConnection(   ftpInfo.getFtpAdres().substring(0, ftpInfo.getFtpAdres().indexOf(":"))
					                          , Integer.valueOf(ftpInfo.getFtpAdres().substring(ftpInfo.getFtpAdres().indexOf(":")+1, ftpInfo.getFtpAdres().length()))
					                          , ftpInfo.getUserId()
					                          , ftpPassword );



			FileVO atchFile = new FileVO();
			atchFile.setAtchFileId(batchAtchFileVO.getAtchFileId());
			atchFile = fileManageDAO.selectFileInf(atchFile);

			LOGGER.debug("\n★★★delYn=[{}", FtpUtl.deleteFile(ftpClient, wdtbPath + "/" + presentFileVO.getWdtbPath(), presentFileVO.getBatchFileNm()));

			LOGGER.debug("\n★★★{}", batchAtchFileVO);

			batchInfoDAO.deleteBatchAtchFile(batchAtchFileVO);
		}
		catch (Exception e)
		{
			LOGGER.debug(e.getMessage(), e);
			throw e;
		}
		finally
		{
			if (ftpClient != null)
			{
				ftpClient.disconnect();
			}
		}
	}

	/**
	 * 배치첨부파일 서버 등록
	 * @param request HttpServlerRequest - 파일정보
	 * @param batchInfoVO BatchInfoVO - 배치정보
	 * @return List<BatchAtchFileVO>
	 * @throws Exception
	 */
	public List<BatchAtchFileVO> getBatchAtchFileList(HttpServletRequest request, BatchInfoVO batchInfoVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<BatchAtchFileVO> atchFileVOList = null;		// 반환 할 첨부파일List

		LoginVO loginVO;									// Session 로그인 정보
		String userId;										// 사용자ID

		MultipartHttpServletRequest multiRequest;			// 첨부파일 처리 위한 MultipartHttpServletRequest 객체

		Map<String, MultipartFile> filesMap;				// 첨부파일 정보 저장하고 있는 Map 객체
		Map<String, MultipartFile> fileMap;					// 첨부파일 단건 처리시 사용할 Map 객체
		Iterator<Entry<String, MultipartFile>> fileItr;		// 첨부파일 정보 추출할 Iterator 객체
		Entry<String, MultipartFile> fileEntry;				// 첨부파일 정보 Entry 객체
		List<FileVO> fileInfoList;							// 첨부파일 정보 저장한 FileVO List

		String atchFileId;									// 첨부파일ID
		FileVO fileVO;										// 첨부파일 상세 조회 시 사용할 VO

		List<String> beanIdList = new ArrayList<String>();	// TN_BATCH_BEAN 테이블에 저장할 bean ID 리스트

		/*------------------------------ field ------------------------------ field ------------------------------*/

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. HttpServletRequest 객체의 Session 정보에서 userId 갖고 오기
		 	    - STEP 1.1. HttpServletRequest 객체의 Session 정보에서 LoginVO 추출
		 	    - STEP 1.2. LoginVO에서 userId 추출 (LoginVO가 null일 경우 공백 값으로 설정)
		 	STEP 2. HttpServlerRequest 객체를 파일 처리 위한 MultipartHttpServletRequest 객체로 형 변환
		 	STEP 3. MultipartHttpServletRequest 객체에서 Map 객체 추출
		 	    - STEP 3.1. filesMap 객체가 null 이거나 비어 있으면 첨부파일 없는 것이므로 로직 종료
		 	    - STEP 3.2. filesMap 객체에 데이터가 존재하면 filesMap 객체에서 Iterator 추출
		 	STEP 4. 첨부파일정보 저장할 List 생성
		 	STEP 5. Iterator 객체에 저장된 Entry 객체에서 파일 정보 추출하여 첨부파일List 객체에 저장
		 	STEP 6. 첨부파일 서버에 등록하고 첨부파일ID 추출
		 	STEP 7. 첨부파일VO 작성
			 		- STEP 7.1. BatchAtchFileVO 객체 생성
			 	    - STEP 7.2. 배치ID 등록
			 	    - STEP 7.3. 첨부파일ID 등록
			 	    - STEP 7.4. 첨부파일 처리 구분 코드 등록 (INS:신규등록)
			 	    - STEP 7.5. 최초등록자ID 등록
			 	    - STEP 7.6. 최종수정자ID 등록
			 	    - STEP 7.7. 첨부파일 종류에 따라 배포경로, 배포파일명, 첨부구분코드 구분(설정파일=cfgWdtbPath, 배치파일=atchFile~)
			 	    	- STEP 7.7.1. 배포경로 등록
			 	    	- STEP 7.7.2. 배포파일명 등록 (설정파일=배치ID, 배치파일=원본파일명)
			 	    	- STEP 7.7.3. 첨부구분코드 (설정파일=S, 배치파일=B)
			 	    - STEP 7.8. 파일상세List 등록 (FileManageDAO.selectFileInfs 메소드 이용)
			 	    	- STEP 7.8.1. FileVO 객체 생성
			 	    	- STEP 7.8.2. FileVO 객체에 atchFileId 등록
			 	    	- STEP 7.8.3. FileManageVO.selectFileInfs 메소드 호출하여 파일상세List 등록
			STEP 8. 첨부파일정보 List 객체에 첨부파일VO 등록
			STEP 9. 첨부파일정보 List 객체 반환
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/*
		 	STEP 1. HttpServletRequest 객체의 Session 정보에서 userId 갖고 오기
		 	    - STEP 1.1. HttpServletRequest 객체의 Session 정보에서 LoginVO 추출
		 	    - STEP 1.2. LoginVO에서 userId 추출 (LoginVO가 null일 경우 공백 값으로 설정)
		 */
		loginVO = (LoginVO)request.getSession().getAttribute("loginVO");		// STEP 1.1. HttpServletRequest 객체의 Session 정보에서 LoginVO 추출
		userId = (loginVO!=null)?loginVO.getId():"";							// STEP 1.2. LoginVO에서 userId 추출 (LoginVO가 null일 경우 공백 값으로 설정)

		/*
		 	STEP 2. HttpServlerRequest 객체를 파일 처리 위한 MultipartHttpServletRequest 객체로 형 변환
		 */
		multiRequest = (MultipartHttpServletRequest) request;


		/*
		 	STEP 3. MultipartHttpServletRequest 객체에서 Map 객체 추출
		 	    - STEP 3.1. filesMap 객체가 null 이거나 비어 있으면 첨부파일 없는 것이므로 로직 종료
		 	    - STEP 3.2. filesMap 객체에 데이터가 존재하면 filesMap 객체에서 Iterator 추출
		 */
		filesMap = multiRequest.getFileMap();
		if (filesMap == null || filesMap.isEmpty())
		{
			// STEP 3.1. Map 객체가 null 이거나 비어 있으면 첨부파일 없는 것이므로 로직 종료
			return null;
		}
		else
		{
			// STEP 3.2. FileMap 객체에 데이터가 존재하면 filesMap 객체에서 Iterator 추출
			fileItr = filesMap.entrySet().iterator();
		}

		/*
		 	STEP 4. 첨부파일정보 저장할 List 생성
		 */
		atchFileVOList = new ArrayList<BatchAtchFileVO>();


		/*
		 	STEP 5. Iterator 객체에 저장된 Entry 객체에서 파일 정보 추출하여 첨부파일List 객체에 저장
		 	    - STEP 5.1. Iterator 객체에서 Entry 객체 추출
		 	    - STEP 5.2. fileMap 객체에 entry 등록
		 	    - STEP 5.3. fileMap 객체에서 파일 정보 추출하여 fileInfoList 객체에 저장
			        - STEP 5.3.1. fileInfoList 객체에 정보가 들어오지 않은 경우 유효하지 않은 파일이므로 다음 Entry 진행)
		 */
		while(fileItr.hasNext())
		{
			fileEntry = fileItr.next();			// STEP 5.1. Iterator 객체에서 Entry 객체 추출

			fileMap = new HashMap<String, MultipartFile>();		// STEP 5.2. fileMap 객체에 entry 등록
			fileMap.put("atchFile", fileEntry.getValue());

			/*
			 	STEP 5.3. fileMap 객체에서 파일 정보 추출하여 fileInfoList 객체에 저장
			        - STEP 5.3.1. fileInfoList 객체에 정보가 들어오지 않은 경우 유효하지 않은 파일이므로 다음 Entry 진행)
			 */
			fileInfoList = egovFileMngUtil.parseFileInf(fileMap, "DSCH_", 0, null, null);
			if (CollectionUtils.isEmpty(fileInfoList))
			{
				continue;		// STEP 5.3.1. fileInfoList 객체에 정보가 들어오지 않은 경우 유효하지 않은 파일이므로 다음 Entry 진행)
			}

			/*
			 	STEP 6. 첨부파일 서버에 등록하고 첨부파일ID 추출
			 */
			atchFileId = egovFileMngService.insertFileInfs(fileInfoList);

			/*
			 	STEP 7. 첨부파일VO 작성
			 		- STEP 7.1. BatchAtchFileVO 객체 생성
			 	    - STEP 7.2. 배치ID 등록
			 	    - STEP 7.3. 첨부파일ID 등록
			 	    - STEP 7.4. 첨부파일 처리 구분 코드 등록 (INS:신규등록)
			 	    - STEP 7.5. 최초등록자ID 등록
			 	    - STEP 7.6. 최종수정자ID 등록
			 	    - STEP 7.7. 첨부파일 종류에 따라 배포경로, 배포파일명, 첨부구분코드 구분(설정파일=cfgWdtbPath, 배치파일=atchFile~)
			 	    	- STEP 7.7.1. 배포경로 등록
			 	    	- STEP 7.7.2. 배포파일명 등록 (설정파일=배치ID, 배치파일=원본파일명)
			 	    	- STEP 7.7.3. 첨부구분코드 (설정파일=S, 배치파일=B)
			 	    - STEP 7.8. 파일상세List 등록 (FileManageDAO.selectFileInfs 메소드 이용)
			 	    	- STEP 7.8.1. FileVO 객체 생성
			 	    	- STEP 7.8.2. FileVO 객체에 atchFileId 등록
			 	    	- STEP 7.8.3. FileManageVO.selectFileInfs 메소드 호출하여 파일상세List 등록
			 */
			BatchAtchFileVO batchAtchFileVO = new BatchAtchFileVO();		// STEP 7.1. BatchAtchFileVO 객체 생성

			batchAtchFileVO.setBatchId(batchInfoVO.getBatchId());			// STEP 7.2. 배치ID 등록
			batchAtchFileVO.setAtchFileId(atchFileId);						// STEP 7.3. 첨부파일ID 등록
			batchAtchFileVO.setProcessSeCode("INS");						// STEP 7.4. 첨부파일 처리 구분 코드 등록 (INS:신규등록)
			batchAtchFileVO.setFrstRegisterId(userId);						// STEP 7.5. 최초등록자ID 등록
			batchAtchFileVO.setLastUpdusrId(userId);						// STEP 7.6. 최종수정자ID 등록

			/*
			 	STEP 7.7. 첨부파일 종류에 따라 배포경로, 배포파일명, 첨부구분코드 구분(설정파일=cfgWdtbPath, 배치파일=atchFile~)
			 	    - STEP 7.7.1. 배포경로 등록
			 	    - STEP 7.7.2. 배포파일명 등록 (설정파일=배치ID, 배치파일=원본파일명)
			 	    - STEP 7.7.3. 첨부구분코드 (설정파일=S, 배치파일=B)
			 */

			LOGGER.debug("\n\n\n\n\n\n\n\n\n\n");
			LOGGER.debug("★★★{}", fileEntry.getKey());
			LOGGER.debug("\n\n\n\n\n\n\n\n\n\n");

			if ("atchCfgFile".equals(fileEntry.getKey()))
			{
				batchAtchFileVO.setWdtbPath((String)request.getParameter("cfgWdtbPath"));		// STEP 7.7.1. 배포경로 등록
				batchAtchFileVO.setBatchFileNm(fileInfoList.get(0).getOrignlFileNm());			// STEP 7.7.2. 배포파일명 등록
				batchAtchFileVO.setAtchSeCode("S");												// STEP 7.7.3. 첨부구분코드 (설정파일=S, 배치파일=B)

				FileVO fileSearchVO = new FileVO();
				fileSearchVO.setAtchFileId(atchFileId);
				FileUtl fileUtl = new FileUtl();

//				try
//				{
					String jobId = fileUtl.getXmlJobId(fileInfoList.get(0).getFileStreCours(), fileInfoList.get(0).getStreFileNm());
					List<String> stepIdList = fileUtl.getXmlStepId(fileInfoList.get(0).getFileStreCours(), fileInfoList.get(0).getStreFileNm());
					batchInfoVO.setBatchNm(jobId);
					beanIdList.add(jobId);
					beanIdList.addAll(stepIdList);
//				}
//				catch (Exception e)
//				{
//					LOGGER.debug(e.getMessage(), new EgovBatchRegistException(e.getMessage()));
//				}
			}
			else
			{
				batchAtchFileVO.setWdtbPath(													// STEP 7.7.1. 배포경로 등록
								(String)request.getParameter("wdtbPath" + fileEntry.getKey().replaceAll("atchFile", "")));
				batchAtchFileVO.setBatchFileNm(fileInfoList.get(0).getOrignlFileNm());			// STEP 7.7.2. 배포파일명 등록
				batchAtchFileVO.setAtchSeCode("B");												// STEP 7.7.3. 첨부구분코드 (설정파일=S, 배치파일=B)
			}

			/*
			 	STEP 7.8. 파일상세List 등록 (FileManageDAO.selectFileInfs 메소드 이용)
			 	    - STEP 7.8.1. FileVO 객체 생성
			 	    - STEP 7.8.2. FileVO 객체에 atchFileId 등록
			 	    - STEP 7.8.3. FileManageVO.selectFileInfs 메소드 호출하여 파일상세List 등록
			 */
			fileVO = new FileVO();														// STEP 7.8.1. FileVO 객체 생성
			fileVO.setAtchFileId(atchFileId);													// STEP 7.8.2. FileVO 객체에 atchFileId 등록
			batchAtchFileVO.setAtchFileDetailList(fileManageDAO.selectFileInfs(fileVO));		// STEP 7.8.3. FileManageVO.selectFileInfs 메소드 호출하여 파일상세List 등록

			/*
			 	STEP 8. 첨부파일정보 List 객체에 첨부파일VO 등록
			 */
			atchFileVOList.add(batchAtchFileVO);

			LOGGER.debug("▶▶▶getBatchAtchFileList.returnVO[{}]", batchAtchFileVO);
		}

		LOGGER.debug("★★★batchId={}", batchInfoVO.getBatchId());

		for (BatchAtchFileVO atchFileVO : atchFileVOList)
		{
			atchFileVO.setBatchId(batchInfoVO.getBatchId());
		}

		if (!StringUtils.hasText(batchInfoVO.getBatchNm()))
		{
			// 설정파일에 JOB ID가 존재하지 않습니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchinfo.jobid"));
		}

//		if (!CollectionUtils.isEmpty(beanIdList))
//		{
//			this.insertBatchBean(batchInfoVO.getBatchId(), beanIdList);
//		}

		/*
		 * STEP 9. 첨부파일정보 List 객체 반환
		 */
		return atchFileVOList;
	}

	/**
	 * 배치 context 등록
	 * @param batchInfoVO BatchInfoVO
	 * @throws Exception
	 */
//	public void registBatchContext(BatchInfoVO batchInfoVO) throws Exception
//	{
//		String [] contextXml = {"classpath*:egovframework/spring/com/context-*.xml", "classpath*:egovframework/batch/job/**/*.xml"};
//
//		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(contextXml);
//		context.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
//	}

	public List<BatchParamtrVO> selectBatchParamtrList(BatchParamtrVO paramtr) throws Exception
	{
		return batchInfoDAO.selectBatchParamtrList(paramtr);
	}

	private void insertBatchBean(String batchId, List<String> beanIdList) throws Exception
	{
		List<BatchBeanVO> beanList = new ArrayList<BatchBeanVO>();

		LOGGER.debug("beanIdList={}", beanIdList);

		for (String beanId : beanIdList)
		{
			for (BatchBeanVO bean : beanList)
			{
				if (beanId.equals(bean.getBeanId()))
				{
					LOGGER.debug("▶▶▶insertBatchBean[beanId={}, bean.getBeanId={}]", beanId, bean.getBeanId());
					// JOB ID, STEP ID 중 중복된 ID가 존재합니다.
					throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchinfo.beandup") + "[" + beanId + "]");
				}
			}

			BatchBeanVO batchBean = new BatchBeanVO();
			batchBean.setBeanId(beanId);
			beanList.add(batchBean);
		}

		BatchInfoVO searchVO = new BatchInfoVO();
		searchVO.setBeanList(beanList);

		List<BatchBeanVO> beanExistList = batchInfoDAO.selectBatchBeanList(searchVO);
			LOGGER.debug("▶▶▶beanExistList=[{}]", beanExistList);

		if (CollectionUtils.isEmpty(beanExistList))
		{
			for (BatchBeanVO bean : beanList)
			{
				bean.setBatchId(batchId);
				batchInfoDAO.insertBatchBean(bean);
			}
		}
		else
		{
			// 설정파일의 JOB ID, STEP ID 중 이미 등록된 ID가 존재합니다.
			throw new EgovBatchRegistException(egovMessageSource.getMessage("fail.sim.batchinfo.beanid"));
		}
	}
}
