package kosii.test.crud.comtnroleinfo.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoVO;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoDefaultVO;

/**
 * @Class Name : ComtnroleinfoDAO.java
 * @Description : Comtnroleinfo DAO Class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("comtnroleinfoDAO")
public class ComtnroleinfoDAO extends EgovAbstractDAO {

	/**
	 * COMTNROLEINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnroleinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        return (String)insert("comtnroleinfoDAO.insertComtnroleinfo_S", vo);
    }

    /**
	 * COMTNROLEINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnroleinfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        update("comtnroleinfoDAO.updateComtnroleinfo_S", vo);
    }

    /**
	 * COMTNROLEINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnroleinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        delete("comtnroleinfoDAO.deleteComtnroleinfo_S", vo);
    }

    /**
	 * COMTNROLEINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnroleinfoVO
	 * @return 조회한 COMTNROLEINFO
	 * @exception Exception
	 */
    public ComtnroleinfoVO selectComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        return (ComtnroleinfoVO) select("comtnroleinfoDAO.selectComtnroleinfo_S", vo);
    }

    /**
	 * COMTNROLEINFO 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return COMTNROLEINFO 목록
	 * @exception Exception
	 */
    public List<?> selectComtnroleinfoList(ComtnroleinfoDefaultVO searchVO) throws Exception {
        return list("comtnroleinfoDAO.selectComtnroleinfoList_D", searchVO);
    }

    /**
	 * COMTNROLEINFO 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return COMTNROLEINFO 총 갯수
	 * @exception
	 */
    public int selectComtnroleinfoListTotCnt(ComtnroleinfoDefaultVO searchVO) {
        return (Integer)select("comtnroleinfoDAO.selectComtnroleinfoListTotCnt_S", searchVO);
    }

}
