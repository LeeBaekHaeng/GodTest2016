package egovframework.rte.fdl.excel.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:god/spring/com/context-excel.xml" })
public class EgovExcelServiceImplTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EgovExcelService egovExcelService;

	@Test
	public void test() {
		loadWorkbook();
	}

	public void loadWorkbook() {
		String filepath = "C:/Users/LeeBaekHaeng/Desktop/e.xls";
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

}
