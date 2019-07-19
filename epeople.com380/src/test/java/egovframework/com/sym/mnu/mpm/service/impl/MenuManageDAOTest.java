package egovframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import god.com.GodAbstractTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuManageDAOTest extends GodAbstractTest {

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
