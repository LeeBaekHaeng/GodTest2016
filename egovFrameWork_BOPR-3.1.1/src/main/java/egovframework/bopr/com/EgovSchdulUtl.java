package egovframework.bopr.com;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import egovframework.bopr.bam.web.EgovBatchDlbrtController;
import egovframework.bopr.sim.service.EgovSchdulResultService;
import egovframework.bopr.sim.service.EgovSchdulService;
import egovframework.bopr.sim.service.EgovSttusNtcnService;
import egovframework.bopr.sim.service.SchdulVO;
import egovframework.com.cop.ems.service.EgovSndngMailService;
import egovframework.com.cop.sms.service.EgovSmsInfoService;
import egovframework.com.uss.ion.noi.service.EgovNotificationService;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 일정 등록 관련 Util 클래스
 * @author SDS
 *
 */
public class EgovSchdulUtl {

	/** 일정 Service Class */
	private EgovSchdulService egovSchdulService;
	
	/** 일정 실행 결과 Service Class */
	private EgovSchdulResultService egovSchdulResultService;
	
	/** QUARTZ Scheduler */
	private Scheduler scheduler;
	
	/** 알림 Service Class */
	private EgovSttusNtcnService egovSttusNtcnService;
	
	/** 팝업 알림 **/
	private EgovNotificationService egovNotificationService;
	
	/** E-Mail 알림 **/
	private EgovSndngMailService egovSndngMailService;
	
	/** SMS 알림 **/
	private EgovSmsInfoService egovSmsInfoService;
	
	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchDlbrtController.class);
	
	/**
	 * egovSchdulService attribute 값을 설정한다
	 * @param egovSchdulService EgovSchdulService
	 */
	public void setEgovSchdulService(EgovSchdulService egovSchdulService) {
		this.egovSchdulService = egovSchdulService;
	}
	
	/**
	 * egovSchdulResultService attribute 값을 설정한다
	 * @param egovSchdulResultService EgovSchdulResultService
	 */
	public void setEgovSchdulResultService(EgovSchdulResultService egovSchdulResultService) {
		this.egovSchdulResultService = egovSchdulResultService;
	}
	
	/**
	 * egovSttusNtcnService attribute 값을 설정한다
	 * @param egovSttusNtcnService EgovSttusNtcnService
	 */
	public void setEgovSttusNtcnService(EgovSttusNtcnService egovSttusNtcnService) {
		this.egovSttusNtcnService = egovSttusNtcnService;
	}
	
	/**
	 * egovNotificationService attribue 값을 설정한다
	 * @param egovNotificationService egovNotificationService
	 */
	public void setEgovNotificationService(EgovNotificationService egovNotificationService)
	{
		this.egovNotificationService = egovNotificationService;
	}
	
	/**
	 * egovSndngMailService attribue 값을 설정한다
	 * @param egovSndngMailService egovSndngMailService
	 */
	public void setEgovSndngMailService(EgovSndngMailService egovSndngMailService)
	{
		this.egovSndngMailService = egovSndngMailService;
	}
	
	/**
	 * egovSmsInfoService attribue 값을 설정한다
	 * @param egovSmsInfoService egovSmsInfoService
	 */
	public void setEgovSmsInfoService(EgovSmsInfoService egovSmsInfoService)
	{
		this.egovSmsInfoService = egovSmsInfoService;
	}
	
	/**
	 * EgovSchdulUtl Class 초기화 메소드
	 * @throws Exception
	 */
	public void init() throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		List<SchdulVO> schdulList;			// 등록될 Schedule List
		SchdulVO searchVO;					// Schedule List 조회 조건 VO
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. 스케줄 List 조회
		 	    - STEP 1.1. 스케줄 조회 조건 설정
		 	    - STEP 1.2. 스케줄 List 조회 서비스 호출
		 	STEP 2. Scheduler 생성
		 	STEP 3. JobListener 생성
		 	    - STEP 3.1. JobListener Class 생성
		 	    - STEP 3.2. SchdulService 설정
		 	    - STEP 3.3. SchdulResultService 설정
		 	    - STEP 3.4. Scheduler 객체에 JobListener 설정
		 	STEP 4. Schedule 등록 (LOOP - 스케줄 List)
		 	STEP 5. Scheduler 시작
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		/*
		 	STEP 1. 스케줄 List 조회
		 	    - STEP 1.1. 스케줄 조회 조건 설정
		 	    - STEP 1.2. 스케줄 List 조회 서비스 호출
		 */
		searchVO = new SchdulVO();				// STEP 1.1. 스케줄 조회 조건 설정
		searchVO.setPageIndex(1);
		searchVO.setPageUnit(10000);
		PageUtl.getPaginationInfo(searchVO);
		
		schdulList = egovSchdulService.selectSchdulList(searchVO);		// STEP 1.2. 스케줄 List 조회 서비스 호출
		LOGGER.debug("▶▶▶init[schdulSize={}]", schdulList.size());
		
		/*
		 	STEP 2. Scheduler 생성
		 */
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		scheduler = schedulerFactory.getScheduler();
		
		/*
		 	STEP 3. JobListener 생성
		 	    - STEP 3.1. JobListener Class 생성
		 	    - STEP 3.2. SchdulService 설정
		 	    - STEP 3.3. SchdulResultService 설정
		 	    - STEP 3.4. Scheduler 객체에 JobListener 설정
		 */
		EgovBatchListnerUtl egovBatchListenerUtl = new EgovBatchListnerUtl();					// STEP 3.1. JobListener Class 생성
		egovBatchListenerUtl.setEgovSchdulResultService(egovSchdulResultService);				// STEP 3.3. SchdulResultService 설정
		egovBatchListenerUtl.setEgovSttusNtcnService(egovSttusNtcnService);						// STEP 3.4. 알림 Service 설정
		egovBatchListenerUtl.setEgovSmsInfoService(egovSmsInfoService);							// STEP 3.5. SMS Service 설정
		egovBatchListenerUtl.setEgovSndngMailService(egovSndngMailService);						// STEP 3.6. E-Mail Service 설정
		egovBatchListenerUtl.setEgovNotificationService(egovNotificationService);
		scheduler.getListenerManager().addJobListener(egovBatchListenerUtl);											// STEP 3.7. Scheduler 객체에 JobListener 설정
		
		/*
		 	STEP 4. Schedule 등록 (LOOP - 스케줄 List)
		 */
		for (SchdulVO schdul : schdulList)
		{
			LOGGER.debug("▶▶▶init[{}]", schdul);
			
			schdul.setParamtrList(egovSchdulService.selectSchdulParamtr(schdul));
			
			insertSchdul(schdul);
		}
		
		/*
		 	STEP 5. Scheduler 시작
		 */
		scheduler.start();
		LOGGER.debug("▶▶▶init.jobNames[{}]", scheduler.getJobGroupNames());
			
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * EgovSchdulUtl Class Destroy 메소드
	 * @throws Exception
	 */
	public void destroy() throws Exception
	{
		LOGGER.debug("▶▶▶destroy method is called!!");
		
		if (scheduler != null)
		{
			scheduler.shutdown();
		}
	}
	
	/**
	 * Scheduler 에 일정 등록
	 * @param schdulVO SchdulVO
	 * @throws Exception
	 */
	public void insertSchdul(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ field ------------------------------ field ------------------------------*/
		
		JobDetail jobDetail;				// Job 상세 정보 VO
		CronTrigger cronTrigger;			// Trigger 객체
		
		/*------------------------------ field ------------------------------ field ------------------------------*/
		/*
		 	STEP 1. JobDetail 생성
		 	    - STEP 1.1. JobDetail Class 생성
		 	    - STEP 1.2. JobDetail name 설정
		 	    - STEP 1.3. JobDetail JobClass 설정
		 	    - STEP 1.4. JobDetail JobListener 추가
		 	    - STEP 1.5. JobDetail batchProgrm 설정
		 	    - STEP 1.6. JobDetail paramtr 설정
		 	    - STEP 1.7. JobDetail paramtrin 설정
		 	    - STEP 1.8. JobDetail paramtrout 설정
		 	STEP 2. Trigger 생성
		 	    - STEP 2.1. CronTrigger Class 생성 (String name, String group, String schedule-cronExpression)
		 	    - STEP 2.2. CronTrigger jobName 설정
		 	STEP 3. Schedule 추가
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("▶▶▶insertSchdul[{}]", schdulVO);
		
		/*
		 	STEP 1. JobDetail 생성
		 	    - STEP 1.1. JobDetail Class 생성
		 	    - STEP 1.2. JobDetail name 설정
		 	    - STEP 1.3. JobDetail JobClass 설정
		 	    - STEP 1.4. JobDetail JobListener 추가
		 	    - STEP 1.5. JobDetail batchProgrm 설정
		 	    - STEP 1.6. JobDetail paramtr 설정
		 	    - STEP 1.7. JobDetail paramtrin 설정
		 	    - STEP 1.8. JobDetail paramtrout 설정
		 */
		//jobDetail = new JobDetail();												// STEP 1.1. JobDetail Class 생성
		//jobDetail.setName(schdulVO.getSchdulNo());									// STEP 1.2. JobDetail name 설정
		//jobDetail.setJobClass(BatchShellScriptJob.class);							// STEP 1.3. JobDetail JobClass 설정
		
		jobDetail = newJob(BatchShellScriptJob.class)
	 			.withIdentity(schdulVO.getSchdulNo())
	 			//.storeDurably()
	 			//.requestRecovery()
	 			//.usingJobData("someKey", "someValue")
	 			.build();
		
		// ------jobDetail.addJobListener(EgovBatchListnerUtl.class.getName());				// STEP 1.4. JobDetail JobListener 추가
		
		jobDetail.getJobDataMap().put("batchId", schdulVO.getBatchId());
		jobDetail.getJobDataMap().put("batchProgrm", schdulVO.getBatchNm());		// STEP 1.5. JobDetail batchProgrm 설정
//		jobDetail.getJobDataMap().put("paramtr", schdulVO.getParamtr());			// STEP 1.6. JobDetail paramtr 설정
//		jobDetail.getJobDataMap().put("paramtrIn", schdulVO.getParamtrin());		// STEP 1.7. JobDetail paramtrin 설정
//		jobDetail.getJobDataMap().put("paramtrOut", schdulVO.getParamtrout());		// STEP 1.8. JobDetail paramtrout 설정
		jobDetail.getJobDataMap().put("paramtrList", schdulVO.getParamtrList());	// STEP 1.9. jobDetail paramtrList 설정
		
		/*
		 	STEP 2. Trigger 생성
		 	    - STEP 2.1. CronTrigger Class 생성 (String name, String group, String schedule-cronExpression)
		 	    - STEP 2.2. CronTrigger jobName 설정
		 */
		//cronTrigger = new CronTrigger(schdulVO.getSchdulNo(), null, schdulVO.getCronExpression());		// STEP 2.1. CronTrigger Class 생성 (String name, String group, String schedule-cronExpression)
		//cronTrigger.setJobName(schdulVO.getSchdulNo());													// STEP 2.2. CronTrigger jobName 설정
		LOGGER.debug("===schdulVO.getSchdulNo()===:::{}", schdulVO.getSchdulNo());
		cronTrigger = newTrigger()
			    .withIdentity(schdulVO.getSchdulNo())
			    .withSchedule(cronSchedule(schdulVO.getCronExpression()))
			    .forJob(schdulVO.getSchdulNo())
			    .build();
		
		/*
		 	STEP 3. Schedule 추가
		 */
		try
		{
			scheduler.scheduleJob(jobDetail, cronTrigger);
				//log.debug("▶▶▶" + "insertSchdul.registedScheduleList[" + Arrays.toString(scheduler.getJobNames(Scheduler.DEFAULT_GROUP)) + "]");
			LOGGER.debug("▶▶▶insertSchdul.registedScheduleList[{}]", scheduler.getJobGroupNames());
		}
		catch (SchedulerException e)
		{
			// 트리거의 실행시각이 현재 시각보다 이전이면 SchedulerException이 발생한다.
			LOGGER.error("▶▶▶insertSchdul[스케줄러 추가 중 에러 발생. (schdulNo=" + schdulVO.getSchdulNo() + ", batchId=" + schdulVO.getBatchId() + ", cronExpression=" + schdulVO.getCronExpression() + ")]");
			LOGGER.error("▶▶▶{}", e.getMessage());
		}
		
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * Scheduler 에서 일정 삭제
	 * @param schdulVO SchdulVO
	 * @throws Exception
	 */
	public void deleteSchdul(SchdulVO schdulVO) throws Exception
	{	
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. Scheduler 에 등록된 일정 삭제(KeyValue=schdulNo)
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		
		LOGGER.debug("▶▶▶deleteSchdul[{}]", schdulVO);
		
		try
		{
			/*
			 	STEP 1. Scheduler에 등록된 일정 삭제(KeyValue=schdulNo)
			 */
			scheduler.deleteJob(JobKey.jobKey(schdulVO.getSchdulNo()));
			
			LOGGER.debug("▶▶▶deleteSchdul.registedScheduleList[{}]", scheduler.getJobGroupNames());
		}
		catch(SchedulerException e)
		{
			LOGGER.error("▶▶▶deleteSchdul[스케줄러 삭제 중 에러 발생. (schdulNo={}, batchId={}]", schdulVO.getSchdulNo(), schdulVO.getBatchId());
			LOGGER.error("▶▶▶{}", e.getMessage());
		}

		/*------------------------------ logic ------------------------------ logic ------------------------------*/
	}
	
	/**
	 * Scheduler 에 등록된 일정 수정
	 * @param schdulVO
	 * @throws Exception
	 */
	public void updateSchdul(SchdulVO schdulVO) throws Exception
	{
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		/*
		 	STEP 1. Scheduler 에 등록된 일정 삭제
		 	STEP 2. 신규 일정 Scheduler 에 등록
		 */
		/*------------------------------ logic ------------------------------ logic ------------------------------*/
		LOGGER.debug("▶▶▶updateSchdul[{}]", schdulVO);
		
		/*
		 	STEP 1. Scheduler 에 등록된 일정 삭제
		 */
		deleteSchdul(schdulVO);
		
		/*
		 	STEP 2. 신규 일정 Scheduler 에 등록
		 */
		insertSchdul(schdulVO);
	}
}
