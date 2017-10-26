package kosii.test.curd.comtnemplyrinfo.service;

import java.util.List;
import kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO;
import kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO;

/**
 * @Class Name : ComtnemplyrinfoService.java
 * @Description : Comtnemplyrinfo Business class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ComtnemplyrinfoService {
	
	/**
	 * COMTNEMPLYRINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnemplyrinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception;
    
    /**
	 * COMTNEMPLYRINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnemplyrinfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception;
    
    /**
	 * COMTNEMPLYRINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnemplyrinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception;
    
    /**
	 * COMTNEMPLYRINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnemplyrinfoVO
	 * @return 조회한 COMTNEMPLYRINFO
	 * @exception Exception
	 */
    ComtnemplyrinfoVO selectComtnemplyrinfo(ComtnemplyrinfoVO vo) throws Exception;
    
    /**
	 * COMTNEMPLYRINFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNEMPLYRINFO 목록
	 * @exception Exception
	 */
    List selectComtnemplyrinfoList(ComtnemplyrinfoDefaultVO searchVO) throws Exception;
    
    /**
	 * COMTNEMPLYRINFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNEMPLYRINFO 총 갯수
	 * @exception
	 */
    int selectComtnemplyrinfoListTotCnt(ComtnemplyrinfoDefaultVO searchVO);
    
}
