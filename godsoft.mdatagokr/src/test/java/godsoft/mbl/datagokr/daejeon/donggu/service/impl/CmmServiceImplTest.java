package godsoft.mbl.datagokr.daejeon.donggu.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:egovframework/spring/com/context-*.xml",
		"classpath:egovframework/spring/mbl/context-*.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-*.xml" })
public class CmmServiceImplTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CmmServiceImpl cmmServiceImpl;

	@Test
	public void test() throws Exception {
		cmmServiceImpl.test();
	}

}
