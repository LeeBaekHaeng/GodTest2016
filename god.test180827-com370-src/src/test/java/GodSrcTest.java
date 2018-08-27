
import java.io.File;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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

public class GodSrcTest {

	private EgovExcelService egovExcelService = new EgovExcelServiceImpl();

	@Test
	public void test() throws Exception {
		// String pathname = SystemUtils.USER_HOME +
		// "/Desktop/god/target/test180827-com370-src-1.0.0";

		// String pathname = SystemUtils.USER_HOME + "/Desktop/god/FPsolution";

		String pathname = SystemUtils.USER_HOME + "/Desktop/god/egovframework-all-in-one_v3.7.2 (1)";

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
		Sheet sheetController = wb.createSheet("Controller");

		String today = EgovDateUtil.toString(new Date(), "yyyy-MM-dd HH-mm-ss", null);
		String filepath = SystemUtils.USER_HOME + "/Desktop/god/god_" + today + ".xlsx";

		int rownum = 0;
		int rownumController = 0;

		for (File listFile : listFiles) {
			System.out.println("listFile=" + listFile);

			String name = listFile.getName();
			String absolutePath = listFile.getAbsolutePath();

			Row row = sheet.createRow(rownum);

			int column = 0;

			Cell cell = row.createCell(column++);
			cell.setCellValue(absolutePath);

			cell = row.createCell(column++);
			cell.setCellValue(name);

			cell = row.createCell(column++);
			cell.setCellValue(FilenameUtils.getExtension(name));

			cell = row.createCell(column++);
			cell.setCellValue(listFile.isDirectory());

			cell = row.createCell(column++);
			cell.setCellValue(listFile.lastModified());

			cell = row.createCell(column++);
			cell.setCellValue(EgovDateUtil.toString(new Date(listFile.lastModified()), "", null));

			rownum++;

			if (name.indexOf("Controller") > -1) {
				Row rowController = sheetController.createRow(rownumController);

				int columnController = 0;

				Cell cellController = rowController.createCell(columnController++);
				cellController.setCellValue(listFile.getAbsolutePath());

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getBaseName(absolutePath).replaceAll("Controller", ""));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getBaseName(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getExtension(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getFullPath(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getFullPathNoEndSeparator(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getName(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getPath(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getPathNoEndSeparator(absolutePath));

				cellController = rowController.createCell(columnController++);
				cellController.setCellValue(FilenameUtils.getPrefix(absolutePath));

				rownumController++;
			}
		}

		wb = egovExcelService.createWorkbook(wb, filepath);
	}

}
