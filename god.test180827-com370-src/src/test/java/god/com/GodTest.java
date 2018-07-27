package god.com;

import java.io.File;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;
import egovframework.rte.fdl.string.EgovDateUtil;

public class GodTest {

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@Test
	public void test() throws Exception {
		String pathname = "D:/egov/eGovFrameDev-3.7.0-64bit/workspace/god.test180827-com370-src/target/test180827-com370-src-1.0.0";

		System.out.println("pathname=" + pathname);

		File directory = new File(pathname);
		String[] extensions = { "jsp" };
		extensions = null;
		boolean recursive = true;

		Collection<File> listFiles = FileUtils.listFiles(directory, extensions, recursive);

		System.out.println("size=" + listFiles.size());

		System.out.println("SystemUtils.USER_HOME=" + SystemUtils.USER_HOME);

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		String today = EgovDateUtil.toString(new Date(), "yyyy-MM-dd HH-mm-ss", null);
		String filepath = SystemUtils.USER_HOME + "/Desktop/god/god_" + today + ".xlsx";

		int rownum = 0;

		for (File listFile : listFiles) {
			System.out.println("listFile=" + listFile);

			Row row = sheet.createRow(rownum);

			Cell cell = row.createCell(0);
			cell.setCellValue(listFile.getAbsolutePath());

			cell = row.createCell(1);
			cell.setCellValue(listFile.getName());

			cell = row.createCell(2);
			cell.setCellValue(listFile.isDirectory());

			cell = row.createCell(3);
			cell.setCellValue(listFile.lastModified());

			rownum++;
		}

		wb = egovExcelService.createWorkbook(wb, filepath);
	}

}
