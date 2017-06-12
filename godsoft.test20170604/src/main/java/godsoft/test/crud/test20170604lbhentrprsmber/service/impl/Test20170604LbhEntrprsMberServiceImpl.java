package godsoft.test.crud.test20170604lbhentrprsmber.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberService;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberDefaultVO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.Test20170604LbhEntrprsMberVO;
import godsoft.test.crud.test20170604lbhentrprsmber.service.impl.Test20170604LbhEntrprsMberDAO;

/**
 * @Class Name : Test20170604LbhEntrprsMberServiceImpl.java
 * @Description : Test20170604LbhEntrprsMber Business Implement class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-06-04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("test20170604LbhEntrprsMberService")
public class Test20170604LbhEntrprsMberServiceImpl extends EgovAbstractServiceImpl implements
        Test20170604LbhEntrprsMberService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(Test20170604LbhEntrprsMberServiceImpl.class);

    @Resource(name="test20170604LbhEntrprsMberDAO")
    private Test20170604LbhEntrprsMberDAO test20170604LbhEntrprsMberDAO;
    
    /** ID Generation */
    //@Resource(name="{egovTest20170604LbhEntrprsMberIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 등록한다.
	 * @param vo - 등록할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	test20170604LbhEntrprsMberDAO.insertTest20170604LbhEntrprsMber(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 수정한다.
	 * @param vo - 수정할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        test20170604LbhEntrprsMberDAO.updateTest20170604LbhEntrprsMber(vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        test20170604LbhEntrprsMberDAO.deleteTest20170604LbhEntrprsMber(vo);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER을 조회한다.
	 * @param vo - 조회할 정보가 담긴 Test20170604LbhEntrprsMberVO
	 * @return 조회한 TEST_20170604_LBH_ENTRPRS_MBER
	 * @exception Exception
	 */
    public Test20170604LbhEntrprsMberVO selectTest20170604LbhEntrprsMber(Test20170604LbhEntrprsMberVO vo) throws Exception {
        Test20170604LbhEntrprsMberVO resultVO = test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMber(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 목록
	 * @exception Exception
	 */
    public List<?> selectTest20170604LbhEntrprsMberList(Test20170604LbhEntrprsMberDefaultVO searchVO) throws Exception {
        return test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMberList(searchVO);
    }

    /**
	 * TEST_20170604_LBH_ENTRPRS_MBER 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return TEST_20170604_LBH_ENTRPRS_MBER 총 갯수
	 * @exception
	 */
    public int selectTest20170604LbhEntrprsMberListTotCnt(Test20170604LbhEntrprsMberDefaultVO searchVO) {
		return test20170604LbhEntrprsMberDAO.selectTest20170604LbhEntrprsMberListTotCnt(searchVO);
	}
    
}
