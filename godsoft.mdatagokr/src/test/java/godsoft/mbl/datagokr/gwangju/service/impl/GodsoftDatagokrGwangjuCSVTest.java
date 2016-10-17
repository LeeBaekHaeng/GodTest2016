package godsoft.mbl.datagokr.gwangju.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		// "classpath:egovframework/spring/com/context-*.xml",
		// "classpath:egovframework/spring/com/idgn/context-*.xml",
		// "classpath:godsoft/spring/datagokr/context-*.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-common.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-excel.xml" })
public class GodsoftDatagokrGwangjuCSVTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GodsoftDatagokrGwangjuCSV godsoftDatagokrGwangjuCSV;

	private void debug(List<EgovMap> items) throws Exception {
		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("items=" + items);
			egovLogger.debug("items.size=" + items.size());
		}

		for (EgovMap item : items) {
			if (egovLogger.isDebugEnabled()) {
				egovLogger.debug("item=" + item);
			}
		}
	}

	// @Test
	public void selectGwangju001List() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();
		vo.put("a", "종합병원");

		List<EgovMap> items = godsoftDatagokrGwangjuCSV
				.selectGwangju001List(vo);

		debug(items);
	}

	// @Test
	public void selectGwangju002List() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();
		// vo.put("a", "정신건강증진센터");

		List<EgovMap> items = godsoftDatagokrGwangjuCSV
				.selectGwangju002List(vo);

		debug(items);
	}

	// @Test
	public void selectGwangju003List() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();
		// vo.put("a", "정신건강증진센터");

		List<EgovMap> items = godsoftDatagokrGwangjuCSV
				.selectGwangju003List(vo);

		debug(items);
	}

	@Test
	public void selectGwangju004List() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();
		// vo.put("a", "정신건강증진센터");

		List<EgovMap> items = godsoftDatagokrGwangjuCSV
				.selectGwangju004List(vo);

		debug(items);
	}

}
