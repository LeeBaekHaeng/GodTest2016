package egovframework.bopr.uam.service.impl;

import java.util.List;

import egovframework.bopr.uam.service.EgovStplatManageService;
import egovframework.bopr.uam.service.StplatVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 약관관리에 관한 ServiceImpl 클래스
 * @user 배치운영환경 김지완
 * @since 2012.07.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.12  김지완          최초 생성
 *
 * </pre>
 */

@Service("egovStplatManageService")
public class EgovStplatManageServiceImpl extends EgovAbstractServiceImpl implements EgovStplatManageService {
    
	@Resource(name="stplatManageDAO")
    private StplatManageDAO stplatManageDAO;

    /**
	 * 일반회원 약관확인
	 * @param stplatId 일반회원약관아이디
	 * @return 일반회원약관정보(List)
	 * @throws Exception
	 */
	public List selectStplat(StplatVO stplatVO)  throws Exception{
		return stplatManageDAO.selectStplat(stplatVO);
	}
	
	/**
	 * 약관 수정
 	 * @param stplatVO StplatVO
	 * @exception Exception
	 */
	public void updateStplat(StplatVO stplatVO) throws Exception{
		stplatManageDAO.updateStplat(stplatVO);
	}
	
	/**
	 * 약관 생성
 	 * @param stplatVO StplatVO
	 * @exception Exception
	 */
    public void insertStplat(StplatVO stplatVO) throws Exception {
    	stplatManageDAO.insertStplat(stplatVO);
    }


}
