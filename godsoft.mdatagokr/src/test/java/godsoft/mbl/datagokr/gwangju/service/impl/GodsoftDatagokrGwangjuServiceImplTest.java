package godsoft.mbl.datagokr.gwangju.service.impl;

import godsoft.mbl.datagokr.gwangju.service.GodsoftDatagokrGwangjuService;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class GodsoftDatagokrGwangjuServiceImplTest {

	@Autowired
	private GodsoftDatagokrGwangjuService godsoftDatagokrGwangjuService;

	// @Test
	public void gwangju001() throws Exception {
		Map<String, Object> vo = new HashMap<String, Object>();
		ModelMap model = new ModelMap();

		// godsoftDatagokrGwangjuService.gwangju001(vo, model);
	}

	@Test
	public void test() throws Exception {
		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/excel/mbl/datagokr/gwangju/의료기관 현황/의료기관+현황(2015).csv")
								.openStream()), "EUC-KR");

		final CSVParser parser = new CSVParser(reader,
				CSVFormat.EXCEL.withHeader());
		try {
			for (final CSVRecord record : parser) {
				final String A = record.get(0);
				final String B = record.get(1);
				final String C = record.get(2);
				final String D = record.get(3);
				final String E = record.get(4);
				final String F = record.get(5);
				// final String G = record.get(6);

				System.out.println(A);
				System.out.println(B);
				System.out.println(C);
				System.out.println(D);
				System.out.println(E);
				System.out.println(F);
				// System.out.println(G);
			}
		} finally {
			parser.close();
			reader.close();
		}
	}

}
