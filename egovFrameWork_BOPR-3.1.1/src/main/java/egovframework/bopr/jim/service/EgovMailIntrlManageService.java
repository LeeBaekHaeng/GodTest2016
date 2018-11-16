package egovframework.bopr.jim.service;

import java.util.List;

/**
 * Mail연동관리에 관한 서비스 인터페이스 클래스
 * @mailIntrl 배치운영환경 김지완
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2012.07.16  김지완          최초 생성
 *
 * </pre>
 */

public interface EgovMailIntrlManageService {
	
    /**
	 * 모든 MailIntrl를 조회
	 * @param mailIntrlManageVO MailIntrlManageVO
	 * @return List<MailIntrlManageVO>
	 * @exception Exception
	 */
	public List<MailIntrlManageVO> selectMailIntrlList(MailIntrlManageVO mailIntrlManageVO) throws Exception;
	
	/**
	 * MailIntrl 등록
	 * @param mailIntrlManage MailIntrlManage
	 * @exception Exception
	 */
	public void insertMailIntrl(MailIntrlManage mailIntrlManage) throws Exception;
	
	/**
	 * MailIntrl 수정
 	 * @param mailIntrlManage MailIntrlManage
	 * @exception Exception
	 */
	public void updateMailIntrl(MailIntrlManage mailIntrlManage) throws Exception;
	
	/**
	 * MailIntrl 삭제
	 * @param mailIntrlManage MailIntrlManage
	 * @exception Exception
	 */
	public void deleteMailIntrl(MailIntrlManage mailIntrlManage) throws Exception;

	/**
	 * 목록조회 카운트를 반환한다
	 * @param mailIntrlManageVO MailIntrlManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMailIntrlListTotCnt(MailIntrlManageVO mailIntrlManageVO) throws Exception;	
	
	/**
	 * 개별 MailIntrl 조회
	 * @param mailIntrlManageVO MailIntrlManageVO
	 * @exception Exception
	 */
	public MailIntrlManageVO selectMailIntrl(MailIntrlManageVO mailIntrlManageVO) throws Exception;
//  여기서부터는 개발 중단
//	/**
//	 * 개별사용자에게 할당된 권한리스트 조회
//	 * @param mailIntrlManageVO MailIntrlManageVO
//	 * @return List<MailIntrlManageVO>
//	 * @exception Exception
//	 */
//	public List<MailIntrlManageVO> selectMailIntrlList(MailIntrlManageVO mailIntrlManageVO) throws Exception;

}
