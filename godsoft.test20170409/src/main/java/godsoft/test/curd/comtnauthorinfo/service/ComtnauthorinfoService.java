package godsoft.test.curd.comtnauthorinfo.service;

import java.util.List;
import godsoft.test.curd.comtnauthorinfo.service.ComtnauthorinfoDefaultVO;
import godsoft.test.curd.comtnauthorinfo.service.ComtnauthorinfoVO;

/**
 * @Class Name : ComtnauthorinfoService.java
 * @Description : Comtnauthorinfo Business class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-09
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ComtnauthorinfoService {
	
	/**
	 * COMTNAUTHORINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnauthorinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception;
    
    /**
	 * COMTNAUTHORINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnauthorinfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception;
    
    /**
	 * COMTNAUTHORINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnauthorinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception;
    
    /**
	 * COMTNAUTHORINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnauthorinfoVO
	 * @return 조회한 COMTNAUTHORINFO
	 * @exception Exception
	 */
    ComtnauthorinfoVO selectComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception;
    
    /**
	 * COMTNAUTHORINFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNAUTHORINFO 목록
	 * @exception Exception
	 */
    List selectComtnauthorinfoList(ComtnauthorinfoDefaultVO searchVO) throws Exception;
    
    /**
	 * COMTNAUTHORINFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNAUTHORINFO 총 갯수
	 * @exception
	 */
    int selectComtnauthorinfoListTotCnt(ComtnauthorinfoDefaultVO searchVO);
    
}
