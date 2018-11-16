package egovframework.bopr.uam.service.impl;

import java.util.List;

import egovframework.bopr.uam.service.StplatVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

import org.springframework.stereotype.Repository;
 
/**
 * 약관관리에 대한 DAO 클래스
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

@Repository("stplatManageDAO")
public class StplatManageDAO extends EgovAbstractDAO {
	
    /**
     * 일반회원 약관확인
     * @param stplatVO StplatVO
     * @return List 일반회원약관정보
     */
    public List selectStplat(StplatVO stplatVO){
    	return list("stplatManageDAO.selectStplat", stplatVO);
    }
    
    /**
	 * 약관 수정
 	 * @param stplatVO StplatVO
	 * @exception Exception
	 */
    public void updateStplat(StplatVO stplatVO) throws Exception {
    	update("stplatManageDAO.updateStplat", stplatVO);
    }
    
    /**
	 * 약관 생성
 	 * @param stplatVO StplatVO
	 * @exception Exception
	 */
    public void insertStplat(StplatVO stplatVO) throws Exception {
    	insert("stplatManageDAO.insertStplat", stplatVO);
    }
    
    
}
