package egovframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/**/context-*.xml" })
@ActiveProfiles("mysql")
@Slf4j
public class MenuManageDAOTest {

	@Resource(name = "menuManageDAO")
	private MenuManageDAO menuManageDAO;

	@Test
	public void test() throws Exception {
		@SuppressWarnings("unchecked")
		List<EgovMap> results = (List<EgovMap>) menuManageDAO.selectMenuList();
		log.debug("results: {}", results);

		for (EgovMap result : results) {
			log.debug("result: {}", result);
			log.debug("menuNo: {}", MapUtils.getString(result, "menuNo"));
			log.debug("menuNo: {}", MapUtils.getIntValue(result, "menuNo"));
			log.debug("menuNo: {}", MapUtils.getInteger(result, "menuNo"));
			log.debug("menuNm: {}", MapUtils.getString(result, "menuNm"));
		}
	}

}
