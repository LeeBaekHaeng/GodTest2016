package godsoft.mbl.datagokr.gwangju.service.impl;

import egovframework.rte.fdl.excel.EgovExcelService;
import godsoft.mbl.datagokr.gwangju.service.GodsoftDatagokrGwangjuService;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class GodsoftDatagokrGwangjuServiceImpl implements
		GodsoftDatagokrGwangjuService {

	protected Logger egovLogger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EgovExcelService egovExcelService;

	@Autowired
	private GodsoftDatagokrGwangjuCSV godsoftDatagokrGwangjuCSV;

	@Override
	public void selectGwangju001List(Map<String, Object> vo, ModelMap model)
			throws Exception {
		model.addAttribute("items",
				godsoftDatagokrGwangjuCSV.selectGwangju001List(vo));

		model.addAttribute("vo", vo);
	}

	@Override
	public void selectGwangju002List(Map<String, Object> vo, ModelMap model)
			throws Exception {
		model.addAttribute("items",
				godsoftDatagokrGwangjuCSV.selectGwangju002List(vo));

		model.addAttribute("vo", vo);
	}

	@Override
	public void selectGwangju003List(Map<String, Object> vo, ModelMap model)
			throws Exception {
		model.addAttribute("items",
				godsoftDatagokrGwangjuCSV.selectGwangju003List(vo));

		model.addAttribute("vo", vo);
	}

	@Override
	public void selectGwangju004List(Map<String, Object> vo, ModelMap model)
			throws Exception {
		model.addAttribute("items",
				godsoftDatagokrGwangjuCSV.selectGwangju004List(vo));

		model.addAttribute("vo", vo);
	}

}
