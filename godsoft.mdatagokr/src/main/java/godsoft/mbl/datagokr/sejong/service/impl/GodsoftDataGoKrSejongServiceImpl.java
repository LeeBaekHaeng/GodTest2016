package godsoft.mbl.datagokr.sejong.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import godsoft.mbl.datagokr.sejong.service.GodsoftDataGoKrSejongService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftDataGoKrSejongServiceImpl implements
		GodsoftDataGoKrSejongService {

	protected Logger egovLogger = LoggerFactory
			.getLogger(EgovAbstractServiceImpl.class);

	@Autowired
	private EgovExcelService egovExcelService;

	@Override
	public void selectSejong01List(Map<String, Object> vo, ModelMap model)
			throws Exception {

		String path = "godsoft/excel/mbl/datagokr/sejong/모범음식점+정보(2014.5월).xlsx";
		// String path = "godsoft/excel/datagokr/sejong/모범음식점 정보(2014.5월).xlsx";
		// String path = "godsoft/excel/datagokr/sejong/test.xlsx";
		Resource resource = new ClassPathResource(path);
		String filepath = resource.getFile().toString();

		// Workbook wb = egovExcelService.loadWorkbook(filepath);
		Workbook wb = egovExcelService.loadWorkbook(filepath,
				new XSSFWorkbook());

		Sheet sheet = wb.getSheet("모범음식점 정보");
		// Sheet sheet = wb.getSheetAt(0);

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("filepath=" + filepath);
			egovLogger.debug("wb=" + wb);
			egovLogger.debug("sheet=" + sheet);
		}

		List<EgovMap> items = new ArrayList<EgovMap>();

		for (Row row : sheet) {
			if (egovLogger.isDebugEnabled()) {
				egovLogger.debug("row=" + row);
			}

			// 식당명
			String a = EgovExcelUtil.getValue(row.getCell(0));

			// 주소
			String b = EgovExcelUtil.getValue(row.getCell(1));

			// 전화번호
			String c = EgovExcelUtil.getValue(row.getCell(2));

			// 추천메뉴
			String d = EgovExcelUtil.getValue(row.getCell(3));

			EgovMap item = new EgovMap();
			item.put("a", a);
			item.put("b", b);
			item.put("c", c);
			item.put("d", d);
			items.add(item);
		}

		if (egovLogger.isDebugEnabled()) {
			egovLogger.debug("items=" + items);
			egovLogger.debug("items.size=" + items.size());
		}

		model.addAttribute("items", items);
	}

}
