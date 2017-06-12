package godsoft.test.crud.test20170604lbhentrprsmber.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberVO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberDefaultVO;

/**
 * @Class Name : Test20170604LbhEntrprsMberDAO.java
 * @Description : Test20170604LbhEntrprsMber DAO Class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-06-04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("test20170604LbhEntrprsMberDAO")
public class Test20170604LbhEntrprsMberDAO extends EgovAbstractDAO {

	/**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 등록한다.
	 * @param vo - 등록할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        return (String)insert("test20170604LbhEntrprsMberDAO.insertTest20170604LbhEntrprsMber_S", vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 수정한다.
	 * @param vo - 수정할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        update("test20170604LbhEntrprsMberDAO.updateTest20170604LbhEntrprsMber_S", vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        delete("test20170604LbhEntrprsMberDAO.deleteTest20170604LbhEntrprsMber_S", vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 조회한다.
	 * @param vo - 조회할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 조회한 TEST_20170604_LBH_ENTRPRS_MBER
	 * @exception Exception
	 */
    public Test20170604LbhEntrprsMberVO selectTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        return (Test20170604LbhEntrprsMberVO) select("test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMber_S", vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 목록
	 * @exception Exception
	 */
    public List<?> selectTest20170604LbhEntrprsMberList(Test20170604LbhEntrprsMberDefaultVO searchVO) throws Exception {
        return list("test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMberList_D", searchVO);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 총 갯수
	 * @exception
	 */
    public int selectTest20170604LbhEntrprsMberListTotCnt(Test20170604LbhEntrprsMberDefaultVO searchVO) {
        return (Integer)select("test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMberListTotCnt_S", searchVO);
    }

}
