package godsoft.test.crud.comtnbbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import godsoft.test.crud.comtnbbs.service.ComtnbbsVO;
import godsoft.test.crud.comtnbbs.service.ComtnbbsDefaultVO;

/**
 * @Class Name : ComtnbbsDAO.java
 * @Description : Comtnbbs DAO Class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-05-01
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("comtnbbsDAO")
public class ComtnbbsDAO extends EgovAbstractDAO {

	/**
	 * COMTNBBS을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnbbsVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertComtnbbs(ComtnbbsVO vo) throws Exception {
        return (String)insert("comtnbbsDAO.insertComtnbbs_S", vo);
    }

    /**
	 * COMTNBBS을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnbbsVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateComtnbbs(ComtnbbsVO vo) throws Exception {
        update("comtnbbsDAO.updateComtnbbs_S", vo);
    }

    /**
	 * COMTNBBS을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnbbsVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteComtnbbs(ComtnbbsVO vo) throws Exception {
        delete("comtnbbsDAO.deleteComtnbbs_S", vo);
    }

    /**
	 * COMTNBBS을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnbbsVO
	 * @return 조회한 COMTNBBS
	 * @exception Exception
	 */
    public ComtnbbsVO selectComtnbbs(ComtnbbsVO vo) throws Exception {
        return (ComtnbbsVO) select("comtnbbsDAO.selectComtnbbs_S", vo);
    }

    /**
	 * COMTNBBS 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return COMTNBBS 목록
	 * @exception Exception
	 */
    public List<?> selectComtnbbsList(ComtnbbsDefaultVO searchVO) throws Exception {
        return list("comtnbbsDAO.selectComtnbbsList_D", searchVO);
    }

    /**
	 * COMTNBBS 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return COMTNBBS 총 갯수
	 * @exception
	 */
    public int selectComtnbbsListTotCnt(ComtnbbsDefaultVO searchVO) {
        return (Integer)select("comtnbbsDAO.selectComtnbbsListTotCnt_S", searchVO);
    }

}
