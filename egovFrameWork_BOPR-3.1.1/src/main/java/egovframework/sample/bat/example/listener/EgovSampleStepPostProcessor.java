/*
 * Copyright 2006-2007 the original author or authors. *
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
package egovframework.sample.bat.example.listener;

import egovframework.rte.bat.core.listener.EgovStepPreProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

/**
 * 스텝단계 이후에 호출되는 리스너 클래스
 *
 * @author 배치실행개발팀
 * @since 2012.06.27
 * @version 1.0
 * @see
 *
 *      <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012.06.27  배치실행개발팀     최초 생성
 * </pre>
 */
public class EgovSampleStepPostProcessor<T, S> extends EgovStepPreProcessor<T, S> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleStepPostProcessor.class);

	/**
	 * Step 수행 이후에 호출되는 부분
	 */
	public ExitStatus afterStep(StepExecution stepExecution) {
		LOGGER.info(">>>>>>>> afterStep ::: finish {}", stepExecution.getStepName());
		return null;
	}

}
