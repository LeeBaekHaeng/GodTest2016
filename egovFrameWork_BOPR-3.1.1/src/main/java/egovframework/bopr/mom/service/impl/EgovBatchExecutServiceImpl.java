package egovframework.bopr.mom.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import egovframework.bopr.com.PageUtl;
import egovframework.bopr.com.SearchUtl;
import egovframework.bopr.mom.service.BatchExecutParamtrVO;
import egovframework.bopr.mom.service.BatchExecutVO;
import egovframework.bopr.mom.service.EgovBatchExecutException;
import egovframework.bopr.mom.service.EgovBatchExecutService;
import egovframework.bopr.sim.service.BatchInfoVO;
import egovframework.bopr.sim.service.BatchParamtrVO;
import egovframework.bopr.sim.service.impl.BatchInfoDAO;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 배치실행관리에 대한 ServiceImpl 클래스
 * @author  이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  이병권            최초 생성
 *
 * </pre>
 */

@Service(value="egovBatchExecutService")
public class EgovBatchExecutServiceImpl extends EgovAbstractServiceImpl implements
		EgovBatchExecutService {

	private EgovBatchRunner egovBatchRunner;

	@Resource(name="egovBatchRunner")
	private EgovBatchRunner egovBatchRunnerWAS;

	@Resource(name="BatchExecutDAO")
	private BatchExecutDAO batchexecutDAO;

	@Resource(name="BatchInfoDAO")
	private BatchInfoDAO batchInfoDAO;

	private ConfigurableApplicationContext context;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchExecutServiceImpl.class);

	/**
	 * 배치실행 등록된 목록 조회
	 * @param batchexecutVO
	 * @return List<BatchExecutVO>
	 * @throws Exception
	 */
	public List<BatchExecutVO> selectBatchExecutList(BatchExecutVO batchexecutVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		List<BatchExecutVO> batchExecutList;		// 조회된 배치배포 List

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 조회할 페이지 정보 설정
			STEP 2. 검색 조건 사용 여부 설정
			STEP 3. List 조회 Resource 호출
			SETP 4. 조회한 List Return
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		LOGGER.debug("\n▶▶▶selectBatchExecutList=[" +"searchCondition=" + batchexecutVO.getSearchCondition()
									                + ", serachKeyword=" + batchexecutVO.getSearchKeyword()
									                + ", pageIndex=" + batchexecutVO.getPageIndex()
									                + ", pageUnit=" + batchexecutVO.getPageUnit()
									                + ", pageSize=" + batchexecutVO.getPageSize() + "]");

		/* STEP 1. 조회할 페이지 정보 설정 */
		PageUtl.getPaginationInfo(batchexecutVO);

		/* STEP 2. 검색 조건 사용 여부 설정 */
		SearchUtl.initSearchParameter(batchexecutVO);

		/* STEP 3. List 조회 Service 호출 */
		batchExecutList = batchexecutDAO.selectBatchExecutList(batchexecutVO);
		LOGGER.debug("==batchExecutList==={}", batchExecutList);

		/* SETP 4. 조회한 List Return */
		return batchExecutList;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치실행 목록 Page List 정보 조회
	 * @param batchExecutVO BatchExecutVO
	 * @return List<BatchExecutVO>
	 * @throws Exception
	 */
	public PaginationInfo selectBatchExecutListPageInfo(BatchExecutVO batchExecutVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/

		PaginationInfo paginationInfo;			// Paging 정보
		int totalCount;							// 목록 Total Count

		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
			STEP 1. 배치배포 목록 Total Count 조회
			STEP 2. 조회한 Total Count PaginationInfo에 설정
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/

		/* STEP 1. 배치정보 목록 Total Count 조회 */
		totalCount = batchexecutDAO.selectBatchExecutListTotCnt(batchExecutVO);

		/* STEP 2. 조회한 Total Count PaginationInfo에 설정 */
		paginationInfo = PageUtl.getPaginationInfo(batchExecutVO);
		paginationInfo.setTotalRecordCount(totalCount);

		return paginationInfo;

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}

	/**
	 * 배치실행 등록된 대상 상세 조회
	 * @param batchexecutVO
	 * @return BatchExecutVO
	 * @throws Exception
	 */
	public BatchExecutVO selectBatchExecut(BatchExecutVO batchexecutVO) throws Exception
	{
		BatchExecutVO batchExecut = batchexecutDAO.selectBatchExecut(batchexecutVO);

		if (batchExecut != null)
		{
			SearchUtl.copySearchInfo(batchexecutVO, batchExecut);
		}

		return batchExecut;
	}

	/**
	 * 배치실행 대상 등록
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void insertBatchExecut(HttpServletRequest request, BatchExecutVO batchExecutVO) throws Exception
	{
		String [] paramNo = request.getParameterValues("paramNo");

		LOGGER.debug("▶▶▶insertBatchExecut["	+	  "batchId=" + batchExecutVO.getBatchId()
													+	", batchNm=" + batchExecutVO.getBatchNm()
													+	", batchExecutNo=" + batchExecutVO.getBatchExecutNo()
													+	", userId=" + batchExecutVO.getFrstRegisterId()
													+	", paramNo=" + Arrays.toString(paramNo) + "]");

		List<BatchExecutParamtrVO> paramtrList = new ArrayList<BatchExecutParamtrVO>();

		if (paramNo != null && paramNo.length > 0)
		{
			for (int index = 0; index < paramNo.length; index++)
			{
				String paramtrNm = request.getParameter("paramtrNm" + index);
				String paramtr = request.getParameter("paramtr" + index);

				LOGGER.debug("▶▶▶paramNo=" + index + ", paramNm=" + paramtrNm + ", paramtr=" + paramtr + "]");

				BatchExecutParamtrVO paramtrVO = new BatchExecutParamtrVO();
				paramtrVO.setBatchExecutNo(batchExecutVO.getBatchExecutNo());
				paramtrVO.setBatchId(batchExecutVO.getBatchId());
				paramtrVO.setParamtrNm(paramtrNm);
				paramtrVO.setParamtr(paramtr);
				paramtrVO.setFrstRegisterId(batchExecutVO.getFrstRegisterId());
				paramtrVO.setLastUpdusrId(batchExecutVO.getFrstRegisterId());

				paramtrList.add(paramtrVO);
			}
		}

		batchexecutDAO.insertBatchExecut(batchExecutVO);

		for (BatchExecutParamtrVO paramtr : paramtrList)
		{
			batchexecutDAO.insertBatchExecutParamtr(paramtr);
		}

		LOGGER.debug("●●●insertBatchExecut.runBatchStart!!!");

		long jobExecutionId = runBatch(batchExecutVO, paramtrList);

		LOGGER.debug("●●●insertBatchExecut.runBatchEnd[jobExecutionId={}]", jobExecutionId);

		batchExecutVO.setJobExecutionId(Long.toString(jobExecutionId));

		batchexecutDAO.updateBatchExecut(batchExecutVO);

		if (jobExecutionId < 0)
		{
			throw new EgovBatchExecutException();
		}
	}

	private long runBatch(BatchExecutVO batchExecutVO, List<BatchExecutParamtrVO> paramtrList) throws Exception
	{
		long jobExecutionId;
		if(batchExecutVO == null || "".equals(batchExecutVO.getBatchNm())) {
			jobExecutionId = -1;
			LOGGER.debug("●●●runBatch batchNm IS NULL!!!{}", batchExecutVO);
			return jobExecutionId;
		}

		boolean isJobName = false;
		boolean isContextActive = context != null && context.isActive();

		Object[] batNames = egovBatchRunnerWAS.getJobNames().toArray();
		for(int i=0; i<batNames.length; i++) {

			if(batNames[i] != null && !"".equals(batNames[i].toString()) && batNames[i].toString().equals(batchExecutVO.getBatchNm())) {

				isJobName = true;
				break;
			}
		}

		LOGGER.debug("●●●runBatch[isJobName{}]", isJobName);

		if(!isJobName) {

			if(isContextActive) {
				context.close();
				context = null;
			}
			String [] contextXml = {"egovframework/spring/com/context-batch-job-launcher.xml",
					"egovframework/spring/com/context-datasource.xml",
					"egovframework/spring/com/context-sqlMap.xml",
					"egovframework/spring/com/context-common.xml",
					"egovframework/spring/com/context-sqlMap.xml",
					"egovframework/spring/com/context-idgen.xml",
					"egovframework/spring/com/context-scheduling-sym-bat.xml",
					"egovframework/spring/com/context-mail.xml",
					"egovframework/spring/com/context-excel.xml",
					"classpath*:egovframework/batch/job/**/" + batchExecutVO.getBatchNm() + ".xml"};

			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(contextXml);
			egovBatchRunner = (EgovBatchRunner)context.getBean("egovBatchRunner");

			try
			{
				jobExecutionId = egovBatchRunner.start(batchExecutVO.getBatchNm(), getJobParameter(egovBatchRunner, paramtrList));
				LOGGER.debug("▶▶▶runBatch[jobExecutionId={}]", jobExecutionId);
			}
			catch (Exception e)
			{
				LOGGER.error("●●●runBatch.occurExecption!![{}]", e.getMessage());
				jobExecutionId = -1;
			}

		} else {

			try
			{
				jobExecutionId = egovBatchRunnerWAS.start(batchExecutVO.getBatchNm(), getJobParameter(egovBatchRunnerWAS, paramtrList));
				LOGGER.debug("▶▶▶runBatch[jobExecutionId={}]", jobExecutionId);
			}
			catch (Exception e)
			{
				LOGGER.error("●●●runBatch.occurExecption!![{}]", e.getMessage());
				jobExecutionId = -1;
			}
		}

		return jobExecutionId;
	}

	private String getJobParameter(EgovBatchRunner batchRunner, List<BatchExecutParamtrVO> paramtrList) throws Exception
	{
		String jobParameter = batchRunner.createUniqueJobParameters();

		for (BatchExecutParamtrVO paramtr : paramtrList)
		{
			jobParameter = batchRunner.addJobParameter(jobParameter, paramtr.getParamtrNm(), paramtr.getParamtr());
		}

		return jobParameter;
	}

	/**
	 * 배치실행 등록된 데이터 수정
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void updateBatchExecut(BatchExecutVO batchexecutVO) throws Exception {

		batchexecutDAO.updateBatchExecut(batchexecutVO);
	}

	/**
	 * 배치실행 등록된 데이터 삭제
	 * @param batchexecutVO
	 * @throws Exception
	 */
	public void deleteBatchExecut(BatchExecutVO batchexecutVO) throws Exception
	{
		LOGGER.debug("\n▶▶▶deleteBatchExecut[batchExecutNo={}]", batchexecutVO.getBatchExecutNo());

		batchexecutDAO.deleteBatchExecut(batchexecutVO);
	}

	public void deleteBatchExecut(String batchId) throws Exception
	{
		LOGGER.debug("▶▶▶deleteBatchExecut[batchId={}]", batchId);

		BatchExecutVO batchExecutVO = new BatchExecutVO();
		batchExecutVO.setBatchId(batchId);

		batchexecutDAO.deleteBatchExecutParamtr(batchExecutVO);
		batchexecutDAO.deleteBatchExecut(batchExecutVO);
	}

	/**
	 * 배치정보 조회
	 * @param batchExecutVO BatchExecutVO - batchId 필수
	 * @return BatchInfoVO - batchId, batchNm
	 */
	public BatchInfoVO selectBatchInfo(BatchExecutVO batchExecutVO) throws Exception
	{
		BatchInfoVO searchVO = new BatchInfoVO();
		BatchInfoVO returnVO = new BatchInfoVO();

		LOGGER.debug("\n▶▶▶selectBatchInfo=[batchId={}]", batchExecutVO.getBatchId());

		searchVO.setBatchId(batchExecutVO.getBatchId());
		returnVO = batchInfoDAO.selectBatchInfo(searchVO);
			LOGGER.debug("\n▶▶▶selectBatchInfo.result=[{}]", returnVO);

		BatchParamtrVO paramtr = new BatchParamtrVO();
		paramtr.setBatchId(batchExecutVO.getBatchId());
		returnVO.setParamtrList(batchInfoDAO.selectBatchParamtrList(paramtr));

		return returnVO;
	}

}
