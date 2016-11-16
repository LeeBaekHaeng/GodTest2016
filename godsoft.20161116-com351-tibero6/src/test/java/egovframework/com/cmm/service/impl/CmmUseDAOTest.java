package egovframework.com.cmm.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:egovframework/spring/com/**/context-*.xml" })
public class CmmUseDAOTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test() {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		vo.setCodeId("COM001");

		try {
			List<CmmnDetailCode> items = cmmUseDAO.selectCmmCodeDetail(vo);

			logger.debug("items=" + items);

			for (CmmnDetailCode item : items) {
				logger.debug("item=" + item);
				logger.debug("getCodeNm=" + item.getCodeNm());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
