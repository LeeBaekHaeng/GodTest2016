package godsoft.com.sub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository
public class Sub01DAO extends EgovComAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<EgovMap> selectList(Map<String, Object> vo) {
		return (List<EgovMap>) list("GodCmmnDetailCodeDAO.selectList", vo);
	}

	public int selectListCount(Map<String, Object> vo) {
		return (int) select("GodCmmnDetailCodeDAO.selectListCount", vo);
	}

}
