package godsoft.test.crud.test20170604lbhentrprsmber.service;

import java.util.List;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberDefaultVO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberVO;

/**
 * @Class Name : Test20170604LbhEntrprsMberService.java
 * @Description : Test20170604LbhEntrprsMber Business class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-06-04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface Test20170604LbhEntrprsMberService {
	
	/**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 등록한다.
	 * @param vo - 등록할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception;
    
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 수정한다.
	 * @param vo - 수정할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형
	 * @exception Exception
	 */
    void updateTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception;
    
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception;
    
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 조회한다.
	 * @param vo - 조회할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 조회한 TEST_20170604_LBH_ENTRPRS_MBER
	 * @exception Exception
	 */
    Test20170604LbhEntrprsMberVO selectTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception;
    
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 목록
	 * @exception Exception
	 */
    List selectTest20170604LbhEntrprsMberList(Test20170604LbhEntrprsMberDefaultVO searchVO) throws Exception;
    
    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 총 갯수
	 * @exception
	 */
    int selectTest20170604LbhEntrprsMberListTotCnt(Test20170604LbhEntrprsMberDefaultVO searchVO);
    
}
