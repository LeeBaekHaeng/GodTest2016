package kosii.test.curd.comtnemplyrinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO;
import kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {
// "classpath:egovframework/spring/com/**/context-*.xml" })
@ContextConfiguration(locations = {
		"classpath:egovframework/spring/com/context-aspect.xml",
		"classpath:egovframework/spring/com/context-common.xml",
		"classpath:egovframework/spring/com/context-datasource.xml",
		"classpath:egovframework/spring/com/context-egovuserdetailshelper.xml",
		"classpath:egovframework/spring/com/context-mail.xml",
		"classpath:egovframework/spring/com/context-properties.xml",
		"classpath:egovframework/spring/com/context-sqlMap.xml",
		"classpath:egovframework/spring/com/context-transaction.xml",
		"classpath:egovframework/spring/com/context-validator.xml",
		"classpath:egovframework/spring/com/idgn/context-*.xml", })
public class ComtnemplyrinfoServiceImplTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	// @Autowired
	// @Qualifier("comtnemplyrinfoDAO")
	@Resource(name = "comtnemplyrinfoService")
	private ComtnemplyrinfoService comtnemplyrinfoService;

	@Test
	public void test() throws Exception {
		// fail("Not yet implemented");

		selectComtnemplyrinfoList();
	}

	public void selectComtnemplyrinfoList() throws Exception {
		ComtnemplyrinfoDefaultVO searchVO = new ComtnemplyrinfoDefaultVO();
		searchVO.setFirstIndex(0);
		// searchVO.setFirstIndex(1);
		searchVO.setLastIndex(10);

		@SuppressWarnings("unchecked")
		List<EgovMap> items = comtnemplyrinfoService
				.selectComtnemplyrinfoList(searchVO);

		egovLogger.debug("items=" + items);

		for (EgovMap item : items) {
			egovLogger.debug("item=" + item);
			egovLogger.debug("userNm=" + item.get("userNm"));
		}
	}

}
