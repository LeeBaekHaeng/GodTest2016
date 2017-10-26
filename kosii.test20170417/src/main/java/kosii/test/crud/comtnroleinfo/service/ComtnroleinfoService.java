package kosii.test.crud.comtnroleinfo.service;

import java.util.List;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoDefaultVO;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoVO;

/**
 * @Class Name : ComtnroleinfoService.java
 * @Description : Comtnroleinfo Business class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ComtnroleinfoService {
	
	/**
	 * COMTNROLEINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnroleinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertComtnroleinfo(ComtnroleinfoVO vo) throws Exception;
    
    /**
	 * COMTNROLEINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnroleinfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateComtnroleinfo(ComtnroleinfoVO vo) throws Exception;
    
    /**
	 * COMTNROLEINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnroleinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteComtnroleinfo(ComtnroleinfoVO vo) throws Exception;
    
    /**
	 * COMTNROLEINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnroleinfoVO
	 * @return 조회한 COMTNROLEINFO
	 * @exception Exception
	 */
    ComtnroleinfoVO selectComtnroleinfo(ComtnroleinfoVO vo) throws Exception;
    
    /**
	 * COMTNROLEINFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNROLEINFO 목록
	 * @exception Exception
	 */
    List selectComtnroleinfoList(ComtnroleinfoDefaultVO searchVO) throws Exception;
    
    /**
	 * COMTNROLEINFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNROLEINFO 총 갯수
	 * @exception
	 */
    int selectComtnroleinfoListTotCnt(ComtnroleinfoDefaultVO searchVO);
    
}
