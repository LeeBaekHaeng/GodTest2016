package egovframework.sample.bat.web;

import java.util.HashMap;

import egovframework.com.cmm.EgovMessageSource;

import egovframework.rte.bat.core.launch.support.EgovBatchRunner;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.20
 * @version 1.0
 * @see <pre>
 *      개정이력(Modification Information)
 *
 *    수정일         수정자           수정내용
 *   -------    --------   ----------------
 * 2012.07.20  배치실행개발팀      최초 생성
 * </pre>
 */

@Controller
public class BatchRunController {

    @Autowired
	private EgovBatchRunner egovBatchRunner;

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchRunController.class);

    @RequestMapping(value="/batchList.do", method = RequestMethod.GET)
    public String getBatchList(ModelMap model){

    	LOGGER.info("========batchList.do======::: {}", egovBatchRunner.getJobNames());

		Object[] batNames = egovBatchRunner.getJobNames().toArray();

		for(int i=0; i<batNames.length; i++){
			LOGGER.info("========batName[{}]======::: {}",i , batNames[i].toString());
		}

		model.addAttribute("jobList", egovBatchRunner.getJobNames());
		LOGGER.info("========batchList.do======{}", model.toString());
		return "egovframework/sample/bat/web/batchList";
	}

	@RequestMapping(value="/batchRun.do", method = RequestMethod.POST)
    public String batchRun(@RequestParam(value = "jobName", required = false) String jobName, Model model){

		try {

			Long executionId = egovBatchRunner.start(jobName, getUniqueJobParameters(jobName));

			JobExecution jobExecution = egovBatchRunner.getJobExecution(executionId);
			JobInstance jobInstance = egovBatchRunner.getJobInstance(executionId);

			HashMap<String, Object> jobInstances = new HashMap<String, Object>();
			HashMap<String, Object> stepInfo = new HashMap<String, Object>();

			StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

			jobInstances.put("id", jobInstance.getId());
			jobInstances.put("name", jobInstance.getJobName());
			jobInstances.put("parameters", jobInstance.getJobParameters());
			jobInstances.put("startTime", jobExecution.getStartTime());
			jobInstances.put("endTime", jobExecution.getEndTime());
			jobInstances.put("isRunning", jobExecution.getStatus().isRunning());
			jobInstances.put("exitStatus",jobExecution.getExitStatus().getExitCode());

			stepInfo.put("stepId", stepExecution.getId());
			stepInfo.put("stepName", stepExecution.getStepName());
			stepInfo.put("readCount", stepExecution.getReadCount());
			stepInfo.put("writeCount", stepExecution.getWriteCount());
			stepInfo.put("readSkipCount", stepExecution.getReadSkipCount());
			stepInfo.put("processSkipCount", stepExecution.getProcessSkipCount());
			stepInfo.put("writeSkipCount", stepExecution.getWriteSkipCount());
			stepInfo.put("totalSkipCount", stepExecution.getSkipCount());
			stepInfo.put("commitCount", stepExecution.getCommitCount());
			stepInfo.put("rollbackCount", stepExecution.getRollbackCount());
			stepInfo.put("exitStatus", stepExecution.getExitStatus().getExitCode());

			model.addAttribute("jobInstances", jobInstances);
			model.addAttribute("stepInfo", stepInfo);

		} catch (Exception e) {
			LOGGER.debug("{}", e);
		}

		return "egovframework/sample/bat/web/batchRunResult";
	}

	protected String getUniqueJobParameters(String jobName) {

		String paramters = egovBatchRunner.createUniqueJobParameters();

		if ("fixedToJdbcJob".equals(jobName)) {
			paramters = egovBatchRunner.addJobParameter(paramters, "inputFile", "classpath:/egovframework/batch/data/inputs/txtData.txt");
		}else if("delimitedToDelimitedJob".equals(jobName)) {
			paramters = egovBatchRunner.addJobParameter(paramters, "inputFile", "classpath:/egovframework/batch/data/inputs/csvData.csv");
			paramters = egovBatchRunner.addJobParameter(paramters, "outputFile", "file:" + System.getProperty("user.home") + "/csvOutput.csv");
		}else if ("fixedToFixedJob".equals(jobName)) {
			paramters = egovBatchRunner.addJobParameter(paramters, "inputFile", "classpath:/egovframework/batch/data/inputs/txtData.txt");
			paramters = egovBatchRunner.addJobParameter(paramters, "outputFile", "file:" + System.getProperty("user.home") + "/txtOutput.txt");
		}else if ("fixedToIbatisJob".equals(jobName)) {
			paramters = egovBatchRunner.addJobParameter(paramters, "inputFile", "classpath:/egovframework/batch/data/inputs/txtData.txt");
		}

		return paramters;
	}
}
