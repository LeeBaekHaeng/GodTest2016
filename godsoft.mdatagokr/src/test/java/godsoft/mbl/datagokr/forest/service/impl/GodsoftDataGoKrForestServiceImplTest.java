package godsoft.mbl.datagokr.forest.service.impl;

import godsoft.mbl.datagokr.forest.service.GodsoftMblDatagokrForestService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		// "classpath:egovframework/spring/com/context-*.xml",
		// "classpath:egovframework/spring/com/idgn/context-*.xml",
		// "classpath:godsoft/spring/datagokr/context-*.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-common.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-excel.xml" })
public class GodsoftDataGoKrForestServiceImplTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GodsoftMblDatagokrForestService godsoftDataGoKrForestService;

	// @Test
	public void mountListSearch() throws Exception {
		Map<String, String> data = new HashMap<String, String>();
		ModelMap model = new ModelMap();

		godsoftDataGoKrForestService.mountListSearch(data, model);

		@SuppressWarnings("unchecked")
		Map<String, Object> metadata = (Map<String, Object>) model
				.get("metadata");

		@SuppressWarnings("unchecked")
		Map<String, Object> resultSummary = (Map<String, Object>) metadata
				.get("resultSummary");

		String totalCnt = (String) resultSummary.get("totalCnt");

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("totalCnt=" + totalCnt);
		}
	}

	@Test
	public void frstEduInfoOpenAPI() throws Exception {
		Map<String, String> data = new HashMap<String, String>();
		ModelMap model = new ModelMap();
	}

}
