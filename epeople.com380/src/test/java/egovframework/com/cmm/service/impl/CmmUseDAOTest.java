package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })
@ActiveProfiles("mysql")
@Slf4j
public class CmmUseDAOTest {

	@Resource(name = "cmmUseDAO")
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test() throws Exception {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");
		log.debug("vo: {}", vo);

		List<CmmnDetailCode> results = cmmUseDAO.selectCmmCodeDetail(vo);
		log.debug("results: {}", results);

		for (CmmnDetailCode result : results) {
			log.debug("result: {}", result);
			log.debug("getCodeId: {}", result.getCodeId());
			log.debug("getCode: {}", result.getCode());
			log.debug("getCodeNm: {}", result.getCodeNm());
		}
	}

}
