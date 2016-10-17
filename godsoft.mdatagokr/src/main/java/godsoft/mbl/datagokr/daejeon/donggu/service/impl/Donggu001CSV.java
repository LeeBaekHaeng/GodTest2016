package godsoft.mbl.datagokr.daejeon.donggu.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Donggu001CSV {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	/**
	 * 대전광역시 동구 주차장정보
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> selectDonggu001List(Map<String, Object> vo)
			throws Exception {
		List<Map<String, String>> items = new ArrayList<Map<String, String>>();

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
			for (final CSVRecord record : parser) {
				items.add(record.toMap());
			}
		} finally {
			parser.close();
			reader.close();
		}

		return items;
	}

}
