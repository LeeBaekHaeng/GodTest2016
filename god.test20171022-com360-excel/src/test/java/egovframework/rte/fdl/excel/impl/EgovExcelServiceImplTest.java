package egovframework.rte.fdl.excel.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.fdl.string.EgovDateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:egovframework/spring/**/context-*.xml" })
public class EgovExcelServiceImplTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("excelZipService")
	private EgovExcelService egovExcelService;

	@Autowired
	private ApplicationContext ctx;

	@Test
	public void test() {
		loadWorkbookAllTables();
		// loadWorkbookAllTabComments();
		// loadWorkbookAllTabCols();
		// loadWorkbookAllColComments();
		egovLogger.debug("EgovDateUtil.toString: " + EgovDateUtil.toString(new Date(), "", null));
	}

	public void loadWorkbookAllTables() {
		Resource template = ctx.getResource("classpath:egovframework/rte/fdl/excel/impl/테이블 2017-10-22.xls");
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

	public void loadWorkbookAllTabComments() {
		Resource template = ctx.getResource("classpath:egovframework/rte/fdl/excel/impl/테이블코멘트 2017-10-22.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
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

	public void loadWorkbookAllTabCols() {
		Resource template = ctx.getResource("classpath:egovframework/rte/fdl/excel/impl/테이블컬럼 2017-10-22.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
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

	public void loadWorkbookAllColComments() {
		Resource template = ctx.getResource("classpath:egovframework/rte/fdl/excel/impl/컬럼코멘트 2017-10-22.xls");
		String filepath = null;
		try {
			filepath = template.getFile().toString();
		} catch (IOException e) {
			egovLogger.error(e.getMessage());
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
