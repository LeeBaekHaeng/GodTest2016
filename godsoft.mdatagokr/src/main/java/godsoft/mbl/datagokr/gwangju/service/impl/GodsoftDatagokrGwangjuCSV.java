package godsoft.mbl.datagokr.gwangju.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository
public class GodsoftDatagokrGwangjuCSV {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	/**
	 * 광주광역시 의료기관 현황 > 의료기관 현황(2015년) CSV
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectGwangju001List(Map<String, Object> vo)
			throws Exception {
		List<EgovMap> items = new ArrayList<EgovMap>();

		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/excel/mbl/datagokr/gwangju/의료기관 현황/의료기관+현황(2015).csv")
								.openStream()), "EUC-KR");

		final CSVParser parser = new CSVParser(reader,
				CSVFormat.EXCEL.withHeader());

		List<String> as = new ArrayList<String>();
		as.add("종합병원");
		as.add("병원");
		as.add("요양병원");
		as.add("노인전문");
		as.add("한방병원");
		as.add("정신병원");
		as.add("치과병원");

		String paramsA = MapUtils.getString(vo, "a");

		try {
			for (final CSVRecord record : parser) {
				final String a = record.get(0);
				final String b = record.get(1);
				final String c = record.get(2);
				final String d = record.get(3);
				final String e = record.get(4);
				final String f = record.get(5);

				if (as.contains(a)) {
					if (StringUtils.isEmpty(paramsA)) {
					} else if (StringUtils.isEmpty(paramsA) == false
							&& a.equals(paramsA)) {
					} else {
						continue;
					}
				} else {
					continue;
				}

				EgovMap item = new EgovMap();
				item.put("a", a);
				item.put("b", b);
				item.put("c", c);
				item.put("d", d);
				item.put("e", e);
				item.put("f", f);

				items.add(item);
			}
		} finally {
			parser.close();
			reader.close();
		}

		return items;
	}

	/**
	 * 광주광역시 의료기관 현황 > 정신보건시설 현황(2015년) CSV
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectGwangju002List(Map<String, Object> vo)
			throws Exception {
		List<EgovMap> items = new ArrayList<EgovMap>();

		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/excel/mbl/datagokr/gwangju/의료기관 현황/정신보건시설현황(2015년).csv")
								.openStream()), "EUC-KR");

		final CSVParser parser = new CSVParser(reader,
				CSVFormat.EXCEL.withHeader());

		String paramsA = MapUtils.getString(vo, "a");

		try {
			for (final CSVRecord record : parser) {
				final String a = record.get(0);
				final String b = record.get(1);
				final String c = record.get(2);
				final String d = record.get(3);
				final String e = record.get(4);
				final String f = record.get(5);

				if (StringUtils.isEmpty(paramsA)) {
				} else if (StringUtils.isEmpty(paramsA) == false
						&& a.equals(paramsA)) {
				} else {
					continue;
				}

				EgovMap item = new EgovMap();
				item.put("a", a);
				item.put("b", b);
				item.put("c", c);
				item.put("d", d);
				item.put("e", e);
				item.put("f", f);

				items.add(item);
			}
		} finally {
			parser.close();
			reader.close();
		}

		return items;
	}

	/**
	 * 광주광역시 의료기관 현황 > 의약단체 현황(2015년) CSV
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectGwangju003List(Map<String, Object> vo)
			throws Exception {
		List<EgovMap> items = new ArrayList<EgovMap>();

		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/excel/mbl/datagokr/gwangju/의료기관 현황/의약단체현황(2015년).csv")
								.openStream()), "EUC-KR");

		// final CSVParser parser = new CSVParser(reader,
		// CSVFormat.EXCEL.withHeader());

		final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL);

		// final CSVParser parser = new CSVParser(reader,
		// CSVFormat.EXCEL.withDelimiter(','), 4, 21);

		int i = 1;

		try {
			for (final CSVRecord record : parser) {
				final String a = record.get(0);
				final String b = record.get(1);
				final String c = record.get(2);
				final String d = record.get(3);
				final String e = record.get(4);

				if (egovLogger.isDebugEnabled()) {
					egovLogger.debug("i=" + i);
					egovLogger.debug("a=" + a);
				}

				if (i > 4 && i < 22 && i != 20) {
					EgovMap item = new EgovMap();

					if (i == 19) {
						item.put("a", a + "운동광주전남지부");
					} else {
						item.put("a", a);
					}

					item.put("b", b);
					item.put("c", c);
					item.put("d", d);
					item.put("e", e);

					items.add(item);
				}

				i++;
			}
		} finally {
			parser.close();
			reader.close();
		}

		return items;
	}

	/**
	 * 광주광역시 의료기관 현황 > 산후조리원 현황(2014년) CSV
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<EgovMap> selectGwangju004List(Map<String, Object> vo)
			throws Exception {
		List<EgovMap> items = new ArrayList<EgovMap>();

		final Reader reader = new InputStreamReader(
				new BOMInputStream(
						getClass()
								.getClassLoader()
								.getResource(
										"godsoft/excel/mbl/datagokr/gwangju/의료기관 현황/산후조리원현황.csv")
								.openStream()), "EUC-KR");

		final CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL);

		int i = 1;

		try {
			for (final CSVRecord record : parser) {
				final String a = record.get(0);
				final String b = record.get(1);
				final String c = record.get(2);
				final String d = record.get(3);
				final String e = record.get(4);

				if (egovLogger.isDebugEnabled()) {
					egovLogger.debug("i=" + i);
					egovLogger.debug("a=" + a);
				}

				if (i > 2) {
					EgovMap item = new EgovMap();

					item.put("a", a);
					item.put("b", b);
					item.put("c", c);
					item.put("d", d);
					item.put("e", e);

					items.add(item);
				}

				i++;
			}
		} finally {
			parser.close();
			reader.close();
		}

		return items;
	}

}
