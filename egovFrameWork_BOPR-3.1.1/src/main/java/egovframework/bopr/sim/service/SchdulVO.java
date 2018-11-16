package egovframework.bopr.sim.service;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;

import org.springframework.util.StringUtils;

/**
 * 스케줄관리에 관한 Model 클래스
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
 *  2012.08.27  이병권    DB 변경에 따른 VO 변경
 *
 * </pre>
 */

public class SchdulVO extends ComDefaultVO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5844507306076140135L;

	/** 배치ID TN_BATCH, TN_SCHDUL */
	private String batchId;
	
	/** 배치명 TN_BATCH */
	private String batchNm;
	
	private String jobSeCode;
	
	private String jobSeCodeNm;
	
	/** 설정파일명 */
	private String setupFileNm;
	
	/** 일정번호 TN_SCHDUL */
	private String schdulNo;
	
	/** 일정명 TN_SCHDUL */
	private String schdulNm;
	
	/** 실행주기 TN_SCHDUL */
	private String executCycle;
	
	/** 실행일정일 TN_SCHDUL */
	private String executSchdulDe;
	
	/** 실행일정시 TN_SCHDUL */
	private String executSchdulHour;
	
	/** 실행일정분 TN_SCHDUL */
	private String executSchdulMnt;
	
	/** 실행일정초 TN_SCHDUL */
	private String executSchdulSecnd;
	
	/** 최초등록자ID */
	private String frstRegisterId;
	
	/** 최초등록시점 */
	private String frstRegistPnttm;
	
	/** 최종수정자ID */
	private String lastUpdusrId;
	
	/** 최종수정시점 */
	private String lastUpdtPnttm;
	
	/** 실행일정 변환 값 */
	private String strExecutCycle;
	
	/** 크론 형식 표현 값 */
	private String cronExpression;
	
	/** 금일실행 여부 */
	private String executYn;
	
	/** 일정 파라미터 목록 */
	private List<BatchParamtrVO> paramtrList;

	public String getJobSeCode() {
		return jobSeCode;
	}

	public void setJobSeCode(String jobSeCode) {
		this.jobSeCode = jobSeCode;
	}

	public String getJobSeCodeNm() {
		return jobSeCodeNm;
	}

	public void setJobSeCodeNm(String jobSeCodeNm) {
		this.jobSeCodeNm = jobSeCodeNm;
	}

	/**
	 * batchId attribute 값을 리턴한다
	 * @return String
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * batchId attribute 값을 설정한다
	 * @param batchId String
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * batchNm attribute 값을 리턴한다
	 * @return String
	 */
	public String getBatchNm() {
		return batchNm;
	}
	
	/**
	 * batchNm attribute 값을 설정한다
	 * @param batchNm String
	 */
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}
	
	/**
	 * setupFileNm attribute 값을 리턴한다
	 * @return String
	 */
	public String getSetupFileNm() {
		return setupFileNm;
	}
	
	/**
	 * setupFileNm attribute 값을 설정한다
	 * @param setupFileNm String
	 */
	public void setSetupFileNm(String setupFileNm) {
		this.setupFileNm = setupFileNm;
	}

	/**
	 * schdulNo attribute 값을 리턴한다
	 * @return String
	 */
	public String getSchdulNo() {
		return schdulNo;
	}

	/**
	 * schdulNo attribute 값을 설정한다
	 * @param schdulNo String
	 */
	public void setSchdulNo(String schdulNo) {
		this.schdulNo = schdulNo;
	}

	/**
	 * schdulNm attribute 값을 리턴한다
	 * @return String
	 */
	public String getSchdulNm() {
		return schdulNm;
	}

	/**
	 * schdulNm attribute 값을 설정한다
	 * @param schdulNm String
	 */
	public void setSchdulNm(String schdulNm) {
		this.schdulNm = schdulNm;
	}

	/**
	 * executCycle attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutCycle() {
		return executCycle;
	}

	/**
	 * executCycle attribute 값을 설정한다
	 * @param executCycle String
	 */
	public void setExecutCycle(String executCycle) {
		this.executCycle = executCycle;
	}

	/**
	 * executSchdulDe attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutSchdulDe() {
		return executSchdulDe;
	}

	/**
	 * executSchdulDe attribute 값을 설정한다
	 * @param executSchdulDe String
	 */
	public void setExecutSchdulDe(String executSchdulDe) {
		this.executSchdulDe = executSchdulDe;
	}

	/**
	 * executSchdulHour attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutSchdulHour() {
		return executSchdulHour;
	}

	/**
	 * executSchdulHour attribute 값을 설정한다
	 * @param executSchdulHour String
	 */
	public void setExecutSchdulHour(String executSchdulHour) {
		this.executSchdulHour = executSchdulHour;
	}

	/**
	 * executSchdulMnt attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutSchdulMnt() {
		return executSchdulMnt;
	}

	/**
	 * executSchdulMnt attribute 값을 설정한다
	 * @param executSchdulMnt String
	 */
	public void setExecutSchdulMnt(String executSchdulMnt) {
		this.executSchdulMnt = executSchdulMnt;
	}

	/**
	 * executSchdulSecnd attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutSchdulSecnd() {
		return executSchdulSecnd;
	}

	/**
	 * executSchdulSecnd attribute 값을 설정한다
	 * @param executSchdulSecnd String
	 */
	public void setExecutSchdulSecnd(String executSchdulSecnd) {
		this.executSchdulSecnd = executSchdulSecnd;
	}

	/**
	 * frstRegisterId attribute 값을 리턴한다
	 * @return String
	 */
	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	/**
	 * frstRegisterId attribute 값을 설정한다
	 * @param frstRegisterId String
	 */
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	/**
	 * frstRegistPnttm attribute 값을 리턴한다
	 * @return String
	 */
	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	/**
	 * frstRegistPnttm attribute 값을 설정한다
	 * @param frstRegistPnttm String
	 */
	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	/**
	 * lastUpdusrId attribute 값을 리턴한다
	 * @return String
	 */
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	/**
	 * lastUpdusrId attribute 값을 설정한다
	 * @param lastUpdusrId String
	 */
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	/**
	 * lastUpdtPnttm attribute 값을 리턴한다
	 * @return String
	 */
	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	/**
	 * lastUpdtPnttm attribute 값을 설정한다
	 * @param lastUpdtPnttm String
	 */
	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	/**
	 * serialVersionUID attribute 값을 리턴한다
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * strExecutCycle attribute 값을 리턴한다
	 * @return String
	 */
	public String getStrExecutCycle() {
		return strExecutCycle;
	}
	
	/**
	 * strExecutCycle attribute 값을 설정한다
	 * @param strExecutCycle String
	 */
	public void setStrExecutCycle(String strExecutCycle) {
		this.strExecutCycle = strExecutCycle;
	}
	
	/**
	 * cronExpression attribute 값을 설정한다
	 * @param cronExpression String
	 */
	public void setCronExpression(String cronExpression)
	{
		this.cronExpression = cronExpression;
	}
	
	/**
	 * executYn attribute 값을 리턴한다
	 * @return String
	 */
	public String getExecutYn() {
		return executYn;
	}
	
	/**
	 * executYn attribute 값을 설정한다
	 * @param executYn String
	 */
	public void setExecutYn(String executYn) {
		this.executYn = executYn;
	}
	
	/**
	 * paramtrList attribute 값을 리턴한다
	 * @return List<BatchParamtrVO>
	 */
	public List<BatchParamtrVO> getParamtrList() {
		return paramtrList;
	}
	
	/**
	 * paramtrList attribute 값을 설정한다
	 * @param paramtrList List<BatchParamtrVO>
	 */
	public void setParamtrList(List<BatchParamtrVO> paramtrList) {
		this.paramtrList = paramtrList;
	}

	/**
	 * SchdulVO의 모든 attribute 값을 출력 형태로 리턴한다
	 * @return String
	 */
	@Override
	public String toString() {
		return "SchdulVO [batchId=" + batchId + ", batchNm=" + batchNm
				+ ", jobSeCode=" + jobSeCode + ", jobSeCodeNm=" + jobSeCodeNm
				+ ", setupFileNm=" + setupFileNm + ", schdulNo=" + schdulNo
				+ ", schdulNm=" + schdulNm + ", executCycle=" + executCycle
				+ ", executSchdulDe=" + executSchdulDe + ", executSchdulHour="
				+ executSchdulHour + ", executSchdulMnt=" + executSchdulMnt
				+ ", executSchdulSecnd=" + executSchdulSecnd
				+ ", frstRegisterId=" + frstRegisterId + ", frstRegistPnttm="
				+ frstRegistPnttm + ", lastUpdusrId=" + lastUpdusrId
				+ ", lastUpdtPnttm=" + lastUpdtPnttm + ", strExecutCycle="
				+ strExecutCycle + ", cronExpression=" + cronExpression
				+ ", executYn=" + executYn + ", paramtrList=" + paramtrList
				+ "]";
	}
	
	/**
	 * SchdulVO의 실행일정을 CRON EXPRESSION 형태로 출력한다
	 * @return String
	 */
	public String getCronExpression()
	{
		cronExpression = "";
		
		cronExpression = executSchdulSecnd + " " + executSchdulMnt + " " + executSchdulHour;
		
		/*
		 * executCycle:	A = 매일
		 * 				B = 매주
		 * 				C = 매월
		 * 				D = 매년
		 * 				E = 1회실행
		 */
		if ("A".equals(executCycle))
		{
			cronExpression += " * * ?";
		}
		else if ("B".equals(executCycle))
		{
			cronExpression += " ? * ";
			
			if ('1' == executSchdulDe.charAt(0))
			{
				cronExpression += "SUN,";
			}
			if ('1' == executSchdulDe.charAt(1))
			{
				cronExpression += "MON,";
			}
			if ('1' == executSchdulDe.charAt(2))
			{
				cronExpression += "TUE,";
			}
			if ('1' == executSchdulDe.charAt(3))
			{
				cronExpression += "WED,";
			}
			if ('1' == executSchdulDe.charAt(4))
			{
				cronExpression += "THU,";
			}
			if ('1' == executSchdulDe.charAt(5))
			{
				cronExpression += "FRI,";
			}
			if ('1' == executSchdulDe.charAt(6))
			{
				cronExpression += "SAT,";
			}
			
			if (StringUtils.hasText(cronExpression) && ',' == cronExpression.charAt(cronExpression.length() - 1))
			{
				cronExpression = cronExpression.substring(0, cronExpression.length() -1);
			}
		}
		else if ("C".equals(executCycle))
		{
			cronExpression += " " + executSchdulDe.substring(6) + " * ?";
		}
		else if ("D".equals(executCycle))
		{
			cronExpression += " " + executSchdulDe.substring(6) + " " + executSchdulDe.substring(4, 6) + " ?";
		}
		else if ("E".equals(executCycle))
		{
			cronExpression += " " + executSchdulDe.substring(6) + " " + executSchdulDe.substring(4, 6) + " ? " + executSchdulDe.substring(0, 4);
		}
		
		initStrExecutCycle();
		
		return cronExpression;
	}
	
	

	private void initStrExecutCycle()
	{
		strExecutCycle = "";
		
		strExecutCycle = executSchdulHour + "시 " + executSchdulMnt + "분 " + executSchdulSecnd + "초";
		
		/*
		 * executCycle:	A = 매일
		 * 				B = 매주
		 * 				C = 매월
		 * 				D = 매년
		 * 				E = 1회실행
		 */
		if ("A".equals(executCycle))
		{
			strExecutCycle = "매일 " + strExecutCycle;
		}
		else if ("B".equals(executCycle))
		{
			strExecutCycle += " ? * ";
			
			String strWeek = "";
			
			if ('1' == executSchdulDe.charAt(0))
			{
				strWeek += "일요일,";
			}
			if ('1' == executSchdulDe.charAt(1))
			{
				strWeek += "월요일,";
			}
			if ('1' == executSchdulDe.charAt(2))
			{
				strWeek += "화요일,";
			}
			if ('1' == executSchdulDe.charAt(3))
			{
				strWeek += "수요일,";
			}
			if ('1' == executSchdulDe.charAt(4))
			{
				strWeek += "목요일,";
			}
			if ('1' == executSchdulDe.charAt(5))
			{
				strWeek += "금요일,";
			}
			if ('1' == executSchdulDe.charAt(6))
			{
				strWeek += "토요일,";
			}
			
			if (StringUtils.hasText(strWeek) && ',' == strWeek.charAt(strWeek.length() - 1))
			{
				strWeek = strWeek.substring(0, strWeek.length() -1);
			}
			
			strExecutCycle = "매주 " + strWeek + " " + strExecutCycle;
		}
		else if ("C".equals(executCycle))
		{
			strExecutCycle = "매월 " + executSchdulDe.substring(6) + "일 " + strExecutCycle;
		}
		else if ("D".equals(executCycle))
		{
			strExecutCycle = "매년 " + executSchdulDe.substring(4, 6) + "월 " + executSchdulDe.substring(6) + "일 " + strExecutCycle;
		}
		else if ("E".equals(executCycle))
		{
			strExecutCycle = executSchdulDe.substring(0, 4) + "년 " + executSchdulDe.substring(4, 6) + "월 " + executSchdulDe.substring(6) + "일 " + strExecutCycle;
		}
	}
}
