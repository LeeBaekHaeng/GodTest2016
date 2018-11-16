package egovframework.bopr.com;

import java.util.List;

import egovframework.bopr.sim.service.BatchParamtrVO;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 배치쉘스크립트를 실행하는 Quartz Job 클래스를 정의한다.
 * 
 * @author 김진만
 * @see <pre>
 * == 개정이력(Modification Information) ==
 * 
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.08.30   김진만     최초 생성
 * </pre>
 */
public class BatchShellScriptJob implements Job {

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchShellScriptJob.class);

	@Autowired
	private EgovBatchRunner egovBatchRunner;

	private ConfigurableApplicationContext context;

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext jobContext)
			throws JobExecutionException {
		
		if(context != null && context.isActive())
		{	
			context.close();
		}

		JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();

		LOGGER.debug("job[{}] Trigger이름 : {}", jobContext.getJobDetail().getKey().getName(), jobContext.getTrigger().getKey().getName());
		LOGGER.debug("job[{}] BatchOpert이름 : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("batchOpertId"));
		LOGGER.debug("job[{}] BatchProgram이름 : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("batchProgrm"));
		LOGGER.debug("job[{}] Parameter이름 : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("paramtr"));
		LOGGER.debug("job[{}] ParameterInputFile : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("paramtrIn"));
		LOGGER.debug("job[{}] ParameterOutputFile : {}", jobContext.getJobDetail().getKey().getName(), dataMap.getString("paramtrOut"));
		LOGGER.debug("▶▶▶paramtrList=[{}]", dataMap.get("paramtrList"));

		// xml설정 파일 명과 경로를 파라미터로 받아서 돌려도 잘 될듯하네...
		// String xml[] =
		// {"egovframework/spring/*.xml","egovframework/batch/job/**/"+dataMap.getString("batchProgrm")+".xml"};
		String xml[] = {
				"egovframework/spring/com/context-batch-job-launcher.xml",
				"egovframework/spring/com/context-datasource.xml",
				"egovframework/spring/com/context-sqlMap.xml",
				"egovframework/batch/job/**/"
						+ dataMap.getString("batchProgrm") + ".xml" };
		// String xml = "egovframework/spring/*.xml";

		/**
		 * Job 실행을 위한 관련 설정 XML을 로드한다. EgovBatchRunner 실행을 위한 Bean과 Job의 설정
		 * Properties
		 */
		context = new ClassPathXmlApplicationContext(xml);
		context.getAutowireCapableBeanFactory().autowireBeanProperties(this,
				AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);

		long result = executeProgram2(dataMap.getString("batchProgrm"), (List<BatchParamtrVO>)dataMap.get("paramtrList"));

		// jobContext에 결과값을 저장한다.
		jobContext.setResult(result);

//		context.close();
	}

	public long executeProgram2(String jobName, List<BatchParamtrVO> paramtrList) {

		long result = 0;
		try
		{

			String paramters = egovBatchRunner.createUniqueJobParameters();
			
			if (paramtrList != null)
			{
				for (BatchParamtrVO paramtr : paramtrList)
				{
					paramters = egovBatchRunner.addJobParameter(paramters, paramtr.getParamtrNm(), paramtr.getParamtr());
				}
			}
			
			LOGGER.debug("▶▶▶schdulParameter=[{}]", paramters);
			
			result = egovBatchRunner.start(jobName, paramters);
		}
		catch (Exception e)
		{
			LOGGER.debug("error::{}", e);
			result = -1;
		}
		
		return result;
	}
}
