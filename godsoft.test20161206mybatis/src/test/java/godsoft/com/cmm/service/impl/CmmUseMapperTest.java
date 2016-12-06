package godsoft.com.cmm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
@ContextConfiguration(locations = {
// "classpath:egovframework/spring/com/**/context-*.xml",
		"classpath:egovframework/spring/com/context-datasource.xml",
		// "classpath:egovframework/spring/com/context-aspect.xml",
		// "classpath:egovframework/spring/com/context-common.xml",
		// "classpath:egovframework/spring/com/idgn/context-*.xml",
		"classpath:godsoft/spring/com/**/context-*.xml" })
public class CmmUseMapperTest {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private CmmUseMapper cmmUseMapper;

	@Test
	public void test() {
		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		vo.setCodeId("COM001");

		logger.debug("isEmpty=" + StringUtils.isEmpty(vo.getCodeId()));

		List<CmmnDetailCode> items = cmmUseMapper.selectCmmCodeDetail(vo);

		logger.debug("items=" + items);

		for (CmmnDetailCode item : items) {
			logger.debug("item=" + item);
			logger.debug("getCodeNm=" + item.getCodeNm());
		}
	}

}
