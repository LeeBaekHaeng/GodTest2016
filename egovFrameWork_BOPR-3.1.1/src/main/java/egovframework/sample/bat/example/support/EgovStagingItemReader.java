/*
 * Copyright 2006-2007 the original author or authors.
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

package egovframework.sample.bat.example.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ReaderNotOpenException;
import org.springframework.batch.support.SerializationUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.util.Assert;

/**
 * 작업수행에 필요한 자료를 가져오기 위해 BATCH_STAGING 테이블을 Select 하는 리더
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @see <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *  </pre>
 */
public class EgovStagingItemReader<T> implements ItemReader<EgovProcessIndicatorItemWrapper<T>>, StepExecutionListener,
		InitializingBean, DisposableBean {


	private static final Logger LOGGER = LoggerFactory.getLogger(EgovStagingItemReader.class);

	//stepExecution
	private StepExecution stepExecution;

	private final Object lock = new Object();

	//초기화 프래그
	private volatile boolean initialized = false;
    //아이디가 저장될 변수
	private volatile Iterator<Long> keys;
	//DB 사용을 위한  SimpleJdbcTemplate
	private JdbcTemplate jdbcTemplate;

	/**
	 * dataSource 설정
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

    /**
     * 초기화
     */
	public void destroy() throws Exception {
		initialized = false;
		keys = null;
	}
    /**
     * 설정확인
     */
	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(jdbcTemplate, "You must provide a DataSource.");
	}
     /**
      * BATCH_STAGING의 ID값을 가져온다.
      * @return List<Long> :BATCH_STAGING의 Id값들
      */
	private List<Long> retrieveKeys() {

		synchronized (lock) {

			return jdbcTemplate.query(

			"SELECT ID FROM BATCH_STAGING WHERE JOB_ID=? AND PROCESSED=? ORDER BY ID",

			new ParameterizedRowMapper<Long>() {
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong(1);
				}
			},

			stepExecution.getJobExecution().getJobId(), EgovStagingItemWriter.NEW);

		}

	}
    /**
     * BATCH_STAGING의 value값을 읽는다
     * @return EgovProcessIndicatorItemWrapper : 결과값
     */
	public EgovProcessIndicatorItemWrapper<T> read() throws DataAccessException {

		if (!initialized) {
			throw new ReaderNotOpenException("Reader must be open before it can be used.");
		}

		Long id = null;
		synchronized (lock) {
			if (keys.hasNext()) {
				id = keys.next();
			}
		}
		LOGGER.debug("Retrieved key from list: {}", id);

		if (id == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T result = (T) jdbcTemplate.queryForObject("SELECT VALUE FROM BATCH_STAGING WHERE ID=?",
				new ParameterizedRowMapper<Object>() {
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						byte[] blob = rs.getBytes(1);
						return SerializationUtils.deserialize(blob);
					}
				}, id);

		return new EgovProcessIndicatorItemWrapper<T>(id, result);

	}
    /**
     * 배치작업 후 실행
     */
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}
    /**
     * 배치작업 전 실행
     */
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		synchronized (lock) {
			if (keys == null) {
				keys = retrieveKeys().iterator();
				LOGGER.info("Keys obtained for staging.");
				initialized = true;
			}
		}
	}

}
