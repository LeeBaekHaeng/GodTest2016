package godsoft.com.cmm.service.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 페이징
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 *
 */
@Service
public class GodPaginationInfoServiceImpl extends EgovAbstractServiceImpl {

	@Autowired
	// @Resource(name = "propertiesService")
	protected EgovPropertyService egovPropertyService;

	public PaginationInfo selectListPrefix(ComDefaultVO vo) {
		// vo.setPageUnit(egovPropertyService.getInt("pageUnit"));
		vo.setPageSize(egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		return paginationInfo;
	}

	public PaginationInfo selectListPrefix(Map<String, Object> vo) {
		// vo.setPageUnit(egovPropertyService.getInt("pageUnit"));
		vo.put("pageSize", egovPropertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(MapUtils.getIntValue(vo, "pageIndex", 1));
		paginationInfo.setRecordCountPerPage(MapUtils.getIntValue(vo, "pageUnit", 10));
		paginationInfo.setPageSize(MapUtils.getIntValue(vo, "pageSize", 10));

		vo.put("firstIndex", paginationInfo.getFirstRecordIndex());
		vo.put("lastIndex", paginationInfo.getLastRecordIndex());
		vo.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());

		return paginationInfo;
	}

}
