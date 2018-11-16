package egovframework.bopr.com;

import java.util.List;

import egovframework.bopr.sim.service.EgovSchdulResultService;
import egovframework.bopr.sim.service.EgovSttusNtcnService;
import egovframework.bopr.sim.service.SchdulResultVO;
import egovframework.bopr.sim.service.SttusNtcnRecptnVO;
import egovframework.bopr.sim.service.SttusNtcnVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cop.ems.service.EgovSndngMailService;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.cop.sms.service.EgovSmsInfoService;
import egovframework.com.cop.sms.service.Sms;
import egovframework.com.uss.ion.noi.service.EgovNotificationService;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EgovBatchListnerUtl implements JobListener {
	
	/** 일정 실행 결과 Service Class */
	private EgovSchdulResultService egovSchdulResultService;
	
	/** 알림 Service Class */
	private EgovSttusNtcnService egovSttusNtcnService;
	
	/** SMS Service Class */
	private EgovSmsInfoService egovSmsInfoService;
	
	/** E-Mail Service Class */
	private EgovSndngMailService egovSndngMailService;
	
	/** Popup 알림 Service Class **/
	private EgovNotificationService egovNotificationService;
	
	/** Log Service */
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovBatchListnerUtl.class);
	
	/**
	 * egovSchdulResultService attribute 값을 설정한다
	 * @param egovSchdulResultService EgovSchdulResultService
	 */
	public void setEgovSchdulResultService(EgovSchdulResultService egovSchdulResultService)
	{
		this.egovSchdulResultService = egovSchdulResultService;
	}
	
	/**
	 * egovSttusNtcnService attribute 값을 설정한다
	 * @param egovSttusNtcnService EgovSttusNtcnService
	 */
	public void setEgovSttusNtcnService(EgovSttusNtcnService egovSttusNtcnService)
	{
		this.egovSttusNtcnService = egovSttusNtcnService;
	}
	
	/**
	 * egovSmsInfoService attribute 값을 설정한다
	 * @param egovSmsInfoService EgovSmsInfoService
	 */
	public void setEgovSmsInfoService(EgovSmsInfoService egovSmsInfoService)
	{
		this.egovSmsInfoService = egovSmsInfoService;
	}
	
	/**
	 * egovSndngMailService attribute 값을 설정한다
	 * @param egovSndngMailService EgovSndngMailService
	 */
	public void setEgovSndngMailService(EgovSndngMailService egovSndngMailService)
	{
		this.egovSndngMailService = egovSndngMailService;
	}
	
	/**
	 * egovNotificationService attribute 값을 설정한다
	 * @param egovNotificationService EgovNotificationService
	 */
	public void setEgovNotificationService(EgovNotificationService egovNotificationService)
	{
		this.egovNotificationService = egovNotificationService;
	}
	
	/**
	 * Class Name 값을 리턴한다
	 * @return String
	 */
	public String getName() {
		return this.getClass().getName();
	}

	/**
	 * Job 시작 시 수행
	 */
	public void jobToBeExecuted(JobExecutionContext context)
	{	
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

		String schdulNo = context.getJobDetail().getKey().getName();
		String batchId = jobDataMap.getString("batchProgrm");
		
		LOGGER.debug("▶▶▶jobToBeExecuted[schdulNo={}, batchId={}]", schdulNo, batchId);

		SchdulResultVO schdulResultVO = new SchdulResultVO();
		schdulResultVO.setSchdulNo(schdulNo);
		schdulResultVO.setBatchId(batchId);
		schdulResultVO.setProcessResult("IN PROGRESS");
		schdulResultVO.setFrstRegisterId("SYSTEM");
		schdulResultVO.setLastUpdusrId("SYSTEM");
		
		String schdulResultNo = "";
		
		try
		{
			schdulResultNo = egovSchdulResultService.insertSchdulResult(schdulResultVO);
			
			/*
			 * 2012.10.30 기능상 문제로 사용하지 않음
			 * 
			Notification notification = new Notification();
			
			// 시간 설정
			GregorianCalendar gc = new GregorianCalendar();
			Date time = gc.getTime(); // 스트링형식 (yyyyMMddHHmmss)
			
			time = DateUtils.addMinutes(time, 1);
			SimpleDateFormat aTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
			String strTime = aTime.format(time);
			
			// 결과 값 세팅.
			notification.setNtfcSj("알림제목::"+"["+batchId+"]");
			notification.setNtfcCn("알림내용::["+batchId+"]가 시작되었습니다");
			notification.setNtfcTime(strTime);
			notification.setBhNtfcIntrvlString("1");
			notification.setFrstRegisterId("system");
			
			// 팝업공지 내용을 등록한다.
			egovNotificationService.insertNotificationSch(notification);
			 */
			
		}
		catch(Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		}
		
		jobDataMap.put("schdulResultNo", schdulResultNo);
	}

	/**
	 * Job 호출 실패 시 수행
	 */
	public void jobExecutionVetoed(JobExecutionContext context) {
		
		try
		{
			String schdulResultNo = context.getJobDetail().getJobDataMap().getString("schdulResultNo");
			
			SchdulResultVO schdulResultVO = new SchdulResultVO();
			schdulResultVO.setSchdulResultNo(schdulResultNo);
			schdulResultVO.setLastUpdusrId("SYSTEM");
			schdulResultVO.setProcessResult("FAILED");
			schdulResultVO.setErrorCn("Scheduler 호출 도중 에러 발생");
			
			egovSchdulResultService.updateSchdulResult(schdulResultVO);
			
			
//			JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//			String batchId = jobDataMap.getString("batchProgrm");
//			
//			Notification notification = new Notification();
//			
//			// 시간 설정
//			GregorianCalendar gc = new GregorianCalendar();
//			Date time = gc.getTime(); // 스트링형식 (yyyyMMddHHmmss)
//			
//			time = DateUtils.addMinutes(time, 1);
//			SimpleDateFormat aTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
//			String strTime = aTime.format(time);
//			
//			// 결과 값 세팅.
//			notification.setNtfcSj("알림제목::"+"["+batchId+"]");
//			notification.setNtfcCn("알림내용::["+batchId+"]가 호출되지 않았습니다.");
//			notification.setNtfcTime(strTime);
//			notification.setBhNtfcIntrvlString("1");
//			notification.setFrstRegisterId("system");
//			
//			// 팝업공지 내용을 등록한다.
//			egovNotificationService.insertNotificationSch(notification);
		}
		catch(Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Job 수행 완료 시 수행
	 */
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException)
	{
		String schdulResultNo = context.getJobDetail().getJobDataMap().getString("schdulResultNo");
		long jobExecutionId = (Long)context.getResult();

		LOGGER.debug("▶▶▶jobWasExecuted[schdulResultNo={}, jobExecutionId={}]", schdulResultNo, jobExecutionId);
		
		SchdulResultVO schdulResultVO = new SchdulResultVO();
		schdulResultVO.setSchdulResultNo(schdulResultNo);
		schdulResultVO.setLastUpdusrId("SYSTEM");
		
		if (jobExecutionId < 0)
		{
			schdulResultVO.setProcessResult("FAILED");
			schdulResultVO.setErrorCn("Scheduler 호출 도중 에러 발생");
		}
		else
		{
			schdulResultVO.setJobExecutionId(Long.toString(jobExecutionId));
			schdulResultVO.setProcessResult("COMPLETED");
		}
		
		if (jobException != null)
		{
			schdulResultVO.setErrorCn(schdulResultVO.getErrorCn() + ", " + jobException.getMessage());
		}
		
		try
		{
			egovSchdulResultService.updateSchdulResult(schdulResultVO);
			
			SttusNtcnVO ntcnSearchVO = new SttusNtcnVO();
			ntcnSearchVO.setSearchCondition("B");								// 스케줄번호
			ntcnSearchVO.setSearchKeyword(context.getJobDetail().getKey().getName());	// 스케줄번호
			
			if (StringUtils.isEmpty(schdulResultVO.getErrorCn()))
			{
				ntcnSearchVO.setEventCode("A1");
			}
			else
			{
				ntcnSearchVO.setEventCode("A2");
			}

			List<SttusNtcnVO> ntcnList = egovSttusNtcnService.selectSttusNtcnList(ntcnSearchVO);
			LOGGER.debug("▶▶▶jobWasExecuted[ntcnListSize={}]", ntcnList.size());
			for (SttusNtcnVO ntcn : ntcnList)
			{	
				for (String ntcnCode : StringUtl.stringToList(ntcn.getNtcnCode(), ","))
				{
					/*
					 	SMS
					 */
					if ("SMS".equals(ntcnCode))
					{
						LOGGER.debug("▶▶▶jobWasExecuted SMS NTCN SERVICE");
						Sms sms = new Sms();
						
						
						
						SttusNtcnVO ntcnDetail = egovSttusNtcnService.selectSttusNtcn(ntcn);
						
						sms.setTrnsmitCn(ntcnDetail.getMssageDc());			// 문자 내용
						sms.setTrnsmitTelno(EgovProperties.getProperty("NTCN.SEND.SMS"));			// 보내는 사람 번호
						
						String [] recptnTelno = new String [ntcnDetail.getSttusNtcnRecptnVOList().size()];		// 받는 사람 번호
						
						for (int index = 0; index < ntcnDetail.getSttusNtcnRecptnVOList().size(); index++)
						{
							recptnTelno[index] = ntcnDetail.getSttusNtcnRecptnVOList().get(index).getMoblphonNo();
						}
						
						sms.setRecptnTelno(recptnTelno);
						sms.setFrstRegisterId("SYSTEM");				// 최초등록자ID
						
						egovSmsInfoService.insertSmsInf(sms);	// 2012.09.13 현재 SMS 서비스 계정이 활성화 되지 않아 테스트 불가
					}
					/*
					 	E-Mail
					 */
					else if ("E-Mail".equals(ntcnCode))
					{
						LOGGER.debug("▶▶▶jobWasExecuted EMAIL NTCN SERVICE");
						
						SndngMailVO mailVO = new SndngMailVO();
						
						SttusNtcnVO ntcnDetail = egovSttusNtcnService.selectSttusNtcn(ntcn);
						
						mailVO.setDsptchPerson(EgovProperties.getProperty("NTCN.SEND.EMAIL.id"));			// 보내는 사람 이메일
//						mailVO.setEmailCn(ntcn.getMssageDc() + "[schdulNo=" + ntcnSearchVO.getSearchKeyword() + ", ip=" + InetAddress.getLocalHost().getHostAddress() + "]");					// 이메일 내용
						mailVO.setEmailCn(ntcnDetail.getMssageDc());
						mailVO.setSj(ntcnDetail.getMssageNm());						// 이메일 제목
						
						for (SttusNtcnRecptnVO recptn : ntcnDetail.getSttusNtcnRecptnVOList())
						{
							mailVO.setRecptnPerson(recptn.getEmails());
							egovSndngMailService.sndngMail(mailVO);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		}
	}

}
