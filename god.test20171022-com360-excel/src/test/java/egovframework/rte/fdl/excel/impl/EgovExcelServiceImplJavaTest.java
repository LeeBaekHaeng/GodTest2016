package egovframework.rte.fdl.excel.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.fdl.string.EgovDateUtil;

public class EgovExcelServiceImplJavaTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@Test
	public void test() {
		// loadWorkbookAllTables();
		loadWorkbookAllTablesHeader();
		// loadWorkbookAllTabCols();
		// loadWorkbookAllTabComments();
		// loadWorkbookAllColComments();
		egovLogger.debug("EgovDateUtil.toString: " + EgovDateUtil.toString(new Date(), "", null));
	}

	public void loadWorkbookAllTables() {
		Resource template = new ClassPathResource(
				"egovframework/rte/fdl/excel/impl/dic 2017-10-27 god/ALL_TABLES 테이블.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}
		if (filepath == null) {
			return;
		}

		Workbook wb = null;
		try {
			wb = egovExcelService.loadWorkbook(filepath);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		if (wb == null) {
			return;
		}

		egovLogger.debug("wb: " + wb);

		Sheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			// egovLogger.debug("row: " + row);

			if (row.getRowNum() < 1) {
				continue;
			}

			String owner = EgovExcelUtil.getValue(row.getCell(0));
			String tableName = EgovExcelUtil.getValue(row.getCell(1));

			egovLogger.debug("owner: " + owner);
			egovLogger.debug("tableName: " + tableName);
		}
	}

	public void loadWorkbookAllTablesHeader() {
		Resource template = new ClassPathResource(
				"egovframework/rte/fdl/excel/impl/dic 2017-10-27 god/ALL_TABLES 테이블 header.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}
		if (filepath == null) {
			return;
		}

		Workbook wb = null;
		try {
			wb = egovExcelService.loadWorkbook(filepath);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		if (wb == null) {
			return;
		}

		egovLogger.debug("wb: " + wb);

		Sheet sheet = wb.getSheetAt(0);

		Map<Integer, String> header = new HashMap<Integer, String>();

		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();

		for (Row row : sheet) {
			// egovLogger.debug("row: " + row);

			int rowNum = row.getRowNum();

			if (rowNum == 0) {
				for (Cell cell : row) {
					header.put(cell.getColumnIndex(), EgovExcelUtil.getValue(cell));
				}
			} else if (rowNum < 1) {
				continue;
			} else {
				Map<String, Object> record = new HashMap<String, Object>();
				for (Cell cell : row) {
					record.put(header.get(cell.getColumnIndex()), EgovExcelUtil.getValue(cell));
				}
				records.add(record);
			}
		}

		for (Integer key : header.keySet()) {
			System.out.println(String.format("키 : %s, 값 : %s", key, header.get(key)));
		}

		for (Map<String, Object> record : records) {
			System.out.println("record: " + record);
			System.out.println("ENTITY_NAME: " + record.get("ENTITY_NAME"));
			System.out.println("OWNER: " + record.get("OWNER"));
			System.out.println("TABLE_NAME: " + record.get("TABLE_NAME"));
		}
	}

	public void loadWorkbookAllTabCols() {
		Resource template = new ClassPathResource(
				"classpath:egovframework/rte/fdl/excel/impl/dic 2017-10-27 god/ALL_TAB_COLS 테이블컬럼.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}
		if (filepath == null) {
			return;
		}

		Workbook wb = null;
		try {
			wb = egovExcelService.loadWorkbook(filepath);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		if (wb == null) {
			return;
		}

		egovLogger.debug("wb: " + wb);

		Sheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			// egovLogger.debug("row: " + row);

			if (row.getRowNum() < 1) {
				continue;
			}

			String owner = EgovExcelUtil.getValue(row.getCell(0));
			String tableName = EgovExcelUtil.getValue(row.getCell(1));
			String columnName = EgovExcelUtil.getValue(row.getCell(2));
			String dataType = EgovExcelUtil.getValue(row.getCell(3));
			String dataLength = EgovExcelUtil.getValue(row.getCell(6));
			String dataPrecision = EgovExcelUtil.getValue(row.getCell(7));
			String dataScale = EgovExcelUtil.getValue(row.getCell(8));
			String nullable = EgovExcelUtil.getValue(row.getCell(9));
			String columnId = EgovExcelUtil.getValue(row.getCell(10));

			egovLogger.debug("owner: " + owner);
			egovLogger.debug("tableName: " + tableName);
			egovLogger.debug("columnName: " + columnName);
			egovLogger.debug("dataType: " + dataType);
			egovLogger.debug("dataLength: " + dataLength);
			egovLogger.debug("dataPrecision: " + dataPrecision);
			egovLogger.debug("dataScale: " + dataScale);
			egovLogger.debug("nullable: " + nullable);
			egovLogger.debug("columnId: " + columnId);
		}
	}

	public void loadWorkbookAllTabComments() {
		Resource template = new ClassPathResource(
				"classpath:egovframework/rte/fdl/excel/impl/dic 2017-10-27 god/ALL_TAB_COMMENTS 테이블코멘트.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}
		if (filepath == null) {
			return;
		}

		Workbook wb = null;
		try {
			wb = egovExcelService.loadWorkbook(filepath);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		if (wb == null) {
			return;
		}

		egovLogger.debug("wb: " + wb);

		Sheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			// egovLogger.debug("row: " + row);

			if (row.getRowNum() < 1) {
				continue;
			}

			String owner = EgovExcelUtil.getValue(row.getCell(0));
			String tableName = EgovExcelUtil.getValue(row.getCell(1));
			String tableType = EgovExcelUtil.getValue(row.getCell(2));
			String comments = EgovExcelUtil.getValue(row.getCell(3));

			egovLogger.debug("owner: " + owner);
			egovLogger.debug("tableName: " + tableName);
			egovLogger.debug("tableType: " + tableType);
			egovLogger.debug("comments: " + comments);
		}
	}

	public void loadWorkbookAllColComments() {
		Resource template = new ClassPathResource(
				"egovframework/rte/fdl/excel/impl/dic 2017-10-27 god/ALL_COL_COMMENTS 컬럼코멘트.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
		}
		if (filepath == null) {
			return;
		}

		Workbook wb = null;
		try {
			wb = egovExcelService.loadWorkbook(filepath);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}
		if (wb == null) {
			return;
		}

		egovLogger.debug("wb: " + wb);

		Sheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {
			// egovLogger.debug("row: " + row);

			if (row.getRowNum() < 1) {
				continue;
			}

			String owner = EgovExcelUtil.getValue(row.getCell(0));
			String tableName = EgovExcelUtil.getValue(row.getCell(1));
			String columnName = EgovExcelUtil.getValue(row.getCell(2));
			String comments = EgovExcelUtil.getValue(row.getCell(3));

			egovLogger.debug("owner: " + owner);
			egovLogger.debug("tableName: " + tableName);
			egovLogger.debug("columnName: " + columnName);
			egovLogger.debug("comments: " + comments);
		}
	}

}
