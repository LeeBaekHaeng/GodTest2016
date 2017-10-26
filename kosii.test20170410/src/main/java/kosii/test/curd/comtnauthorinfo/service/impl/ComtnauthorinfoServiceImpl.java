package kosii.test.curd.comtnauthorinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoService;
import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoDefaultVO;
import kosii.test.curd.comtnauthorinfo.service.ComtnauthorinfoVO;
import kosii.test.curd.comtnauthorinfo.service.impl.ComtnauthorinfoDAO;

/**
 * @Class Name : ComtnauthorinfoServiceImpl.java
 * @Description : Comtnauthorinfo Business Implement class
 * @Modification Information
 *
 * @author 이백행&lt;dlqorgod@naver.com&gt;
 * @since 2017-04-10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("comtnauthorinfoService")
public class ComtnauthorinfoServiceImpl extends EgovAbstractServiceImpl implements
        ComtnauthorinfoService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ComtnauthorinfoServiceImpl.class);

    @Resource(name="comtnauthorinfoDAO")
    private ComtnauthorinfoDAO comtnauthorinfoDAO;
    
    /** ID Generation */
    //@Resource(name="{egovComtnauthorinfoIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * COMTNAUTHORINFO을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ComtnauthorinfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	comtnauthorinfoDAO.insertComtnauthorinfo(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * COMTNAUTHORINFO을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ComtnauthorinfoVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception {
        comtnauthorinfoDAO.updateComtnauthorinfo(vo);
    }

    /**
	 * COMTNAUTHORINFO을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ComtnauthorinfoVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception {
        comtnauthorinfoDAO.deleteComtnauthorinfo(vo);
    }

    /**
	 * COMTNAUTHORINFO을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ComtnauthorinfoVO
	 * @return 조회한 COMTNAUTHORINFO
	 * @exception Exception
	 */
    public ComtnauthorinfoVO selectComtnauthorinfo(ComtnauthorinfoVO vo) throws Exception {
        ComtnauthorinfoVO resultVO = comtnauthorinfoDAO.selectComtnauthorinfo(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * COMTNAUTHORINFO 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNAUTHORINFO 목록
	 * @exception Exception
	 */
    public List<?> selectComtnauthorinfoList(ComtnauthorinfoDefaultVO searchVO) throws Exception {
        return comtnauthorinfoDAO.selectComtnauthorinfoList(searchVO);
    }

    /**
	 * COMTNAUTHORINFO 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return COMTNAUTHORINFO 총 갯수
	 * @exception
	 */
    public int selectComtnauthorinfoListTotCnt(ComtnauthorinfoDefaultVO searchVO) {
		return comtnauthorinfoDAO.selectComtnauthorinfoListTotCnt(searchVO);
	}
    
}
