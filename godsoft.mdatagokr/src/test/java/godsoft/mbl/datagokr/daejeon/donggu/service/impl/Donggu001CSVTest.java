package godsoft.mbl.datagokr.daejeon.donggu.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		// "classpath:egovframework/spring/com/context-*.xml",
		// "classpath:egovframework/spring/com/idgn/context-*.xml",
		// "classpath:godsoft/spring/datagokr/context-*.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-common.xml",
		"classpath:godsoft/spring/mbl/datagokr/context-excel.xml" })
public class Donggu001CSVTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Donggu001CSV donggu001csv;

	@Test
	public void test() throws Exception {
		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/csv/mbl/datagokr/daejeon/donggu/대전광역시 동구 주차장정보/주차장정보(대전+동구+2014년+3월).csv")
								.openStream()), "EUC-KR");

		final CSVParser parser = new CSVParser(reader,
				CSVFormat.EXCEL.withHeader());

		try {
			// if (egovLogger.isDebugEnabled()) {
			// egovLogger.debug("getCurrentLineNumber="
			// + parser.getCurrentLineNumber());
			// egovLogger.debug("getRecordNumber=" + parser.getRecordNumber());
			//
			// egovLogger.debug("getHeaderMap=" + parser.getHeaderMap());
			//
			// // egovLogger.debug("getRecords=" + parser.getRecords());
			// }
			//
			// for (final CSVRecord record : parser) {
			// if (egovLogger.isDebugEnabled()) {
			// egovLogger.debug("record=" + record);
			// egovLogger.debug("toMap=" + record.toMap());
			// egovLogger.debug("get=" + record.get(0));
			// egovLogger.debug("get=" + record.get("주차장관리번호"));
			// }
			// }

			Map<String, Integer> getHeaderMap = parser.getHeaderMap();

			for (String key : getHeaderMap.keySet()) {
				System.out
						.println("<p>" + key + ": ${item['" + key + "']}</p>");
			}
		} finally {
			parser.close();
			reader.close();
		}
	}

	// @Test
	public void selectDonggu001List() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();

		List<Map<String, String>> items = donggu001csv.selectDonggu001List(vo);

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("items=" + items);
		}

		for (Map<String, String> item : items) {
			if (egovLogger.isDebugEnabled()) {
				egovLogger.debug("item=" + item);
				egovLogger.debug("item=" + item.get("급지구분"));
			}
		}
	}

}
