package kosii.test.crud.comtnroleinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoService;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoDefaultVO;
import kosii.test.crud.comtnroleinfo.service.ComtnroleinfoVO;
import kosii.test.crud.comtnroleinfo.service.impl.ComtnroleinfoDAO;

/**
 * @Class Name : ComtnroleinfoServiceImpl.java
 * @Description : Comtnroleinfo Business Implement class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("comtnroleinfoService")
public class ComtnroleinfoServiceImpl extends EgovAbstractServiceImpl implements
        ComtnroleinfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ComtnroleinfoServiceImpl.class);

    @Resource(name="comtnroleinfoDAO")
    private ComtnroleinfoDAO comtnroleinfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovComtnroleinfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * COMTNROLEINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnroleinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	comtnroleinfoDAO.insertComtnroleinfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * COMTNROLEINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnroleinfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        comtnroleinfoDAO.updateComtnroleinfo(vo);
    }

    /**
	 * COMTNROLEINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnroleinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        comtnroleinfoDAO.deleteComtnroleinfo(vo);
    }

    /**
	 * COMTNROLEINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnroleinfoVO
	 * @return 조회한 COMTNROLEINFO
	 * @exception Exception
	 */
    public ComtnroleinfoVO selectComtnroleinfo(ComtnroleinfoVO vo) throws Exception {
        ComtnroleinfoVO resultVO = comtnroleinfoDAO.selectComtnroleinfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * COMTNROLEINFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNROLEINFO 목록
	 * @exception Exception
	 */
    public List<?> selectComtnroleinfoList(ComtnroleinfoDefaultVO searchVO) throws Exception {
        return comtnroleinfoDAO.selectComtnroleinfoList(searchVO);
    }

    /**
	 * COMTNROLEINFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNROLEINFO 총 갯수
	 * @exception
	 */
    public int selectComtnroleinfoListTotCnt(ComtnroleinfoDefaultVO searchVO) {
		return comtnroleinfoDAO.selectComtnroleinfoListTotCnt(searchVO);
	}
    
}
