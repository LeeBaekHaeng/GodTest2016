package godsoft.com.sub.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import godsoft.com.cmm.service.impl.GodPaginationInfoServiceImpl;
import godsoft.com.cmm.web.GodAbstractContoller;
import godsoft.com.sub.service.impl.Sub01DAO;

@Controller
public class Sub01Controller extends GodAbstractContoller {

	@Autowired
	@Qualifier("EgovCmmUseService")
	private EgovCmmUseService egovCmmUseService;

	@Autowired
	private GodPaginationInfoServiceImpl godPaginationInfo;

	@Autowired
	@Qualifier("EgovFileMngService")
	private EgovFileMngService egovFileMngService;

	// @Resource(name = "EgovBBSManageService")
	@Autowired
	@Qualifier("EgovBBSManageService")
	private EgovBBSManageService egovBBSManageService;

	@Autowired
	private Sub01DAO sub01DAO;

	@RequestMapping("/jqGridSub01.do")
	public String grid(ComDefaultCodeVO vo, Model model) {
		return "godsoft/com/sub/sub01JqGrid";
	}

	@RequestMapping(value = "/selectSub01List.do")
	public String selectList(ComDefaultCodeVO vo, Model model) {
		if (StringUtils.isEmpty(vo.getCodeId())) {
			vo.setCodeId("COM001");
		}

		List<CmmnDetailCode> results = null;

		try {
			results = egovCmmUseService.selectCmmCodeDetail(vo);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}

		model.addAttribute("results", results);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "1");
		map.put("records", "10");
		map.put("total", "2");
		map.put("rows", results);

		// root: "rows",
		// page: "page",
		// total: "total",
		// records: "records",

		model.addAllAttributes(map);

		return "jsonView";
	}

	@RequestMapping("/selectSub01List2.do")
	@ResponseBody
	public Map<String, Object> selectList2(ComDefaultCodeVO vo) {
		if (StringUtils.isEmpty(vo.getCodeId())) {
			vo.setCodeId("COM001");
		}

		List<CmmnDetailCode> results = null;

		try {
			results = egovCmmUseService.selectCmmCodeDetail(vo);
		} catch (Exception e) {
			egovLogger.error(e.getMessage());
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", "1");
		map.put("records", "10");
		map.put("total", "2");
		map.put("rows", results);

		// root: "rows",
		// page: "page",
		// total: "total",
		// records: "records",

		return map;
	}

	// @RequestMapping("/selectSub01List3.do")
	// @ResponseBody
	// public Map<String, Object> selectList3(Map<String, Object> vo) {
	// Map<String, Object> model = new HashMap<String, Object>();
	//
	// PaginationInfo paginationInfo = godPaginationInfo.selectListPrefix(vo);
	//
	// FileVO fvo = new FileVO();
	// Map<String, Object> files = null;
	// try {
	// files = egovFileMngService.selectFileListByFileNm(fvo);
	// } catch (Exception e) {
	// egovLogger.error(e.getMessage());
	// }
	//
	// @SuppressWarnings("unchecked")
	// List<FileVO> resultList = (List<FileVO>) files.get("resultList");
	//
	// int resultCnt = (int) files.get("resultCnt");
	// paginationInfo.setTotalRecordCount(resultCnt);
	//
	// // jsonReader
	// model.put("rows", resultList);
	// model.put("page", paginationInfo.getCurrentPageNo());
	// model.put("total", paginationInfo.getTotalPageCount());
	// model.put("records", paginationInfo.getTotalRecordCount());
	//
	// return model;
	// }

	@RequestMapping("/selectSub01List3.do")
	@ResponseBody
	public Map<String, Object> selectList3(@RequestParam Map<String, Object> vo) {
		Map<String, Object> model = new HashMap<String, Object>();

		vo.put("pageIndex", vo.get("page"));

		PaginationInfo paginationInfo = godPaginationInfo.selectListPrefix(vo);

		List<EgovMap> rows = sub01DAO.selectList(vo);

		int totalRecordCount = sub01DAO.selectListCount(vo);
		paginationInfo.setTotalRecordCount(totalRecordCount);

		// jsonReader
		model.put("rows", rows);
		// model.put("page", paginationInfo.getCurrentPageNo());
		model.put("page", vo.get("page"));
		model.put("total", paginationInfo.getTotalPageCount());
		model.put("records", paginationInfo.getTotalRecordCount());

		return model;
	}

}