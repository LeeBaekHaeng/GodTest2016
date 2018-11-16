package egovframework.bopr.jim.service;

import java.util.List;

/**
 * FTP연동관리에 관한 서비스 인터페이스 클래스
 * @ftpIntrl 배치운영환경 김지완
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

public interface EgovFtpIntrlManageService {
	
    /**
	 * 모든 FtpIntrl를 조회
	 * @param ftpIntrlManageVO FtpIntrlManageVO
	 * @return List<FtpIntrlManageVO>
	 * @exception Exception
	 */
	public List<FtpIntrlManageVO> selectFtpIntrlList(FtpIntrlManageVO ftpIntrlManageVO) throws Exception;
	
	/**
	 * FtpIntrl 등록
	 * @param ftpIntrlManage FtpIntrlManage
	 * @exception Exception
	 */
	public void insertFtpIntrl(FtpIntrlManage ftpIntrlManage) throws Exception;
	
	/**
	 * FtpIntrl 수정
 	 * @param ftpIntrlManage FtpIntrlManage
	 * @exception Exception
	 */
	public void updateFtpIntrl(FtpIntrlManage ftpIntrlManage) throws Exception;
	
	/**
	 * FtpIntrl 삭제
	 * @param ftpIntrlManage FtpIntrlManage
	 * @exception Exception
	 */
	public void deleteFtpIntrl(FtpIntrlManage ftpIntrlManage) throws Exception;

	/**
	 * 목록조회 카운트를 반환한다
	 * @param ftpIntrlManageVO FtpIntrlManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectFtpIntrlListTotCnt(FtpIntrlManageVO ftpIntrlManageVO) throws Exception;	
	
	/**
	 * 개별 FtpIntrl 조회
	 * @param ftpIntrlManageVO FtpIntrlManageVO
	 * @exception Exception
	 */
	public FtpIntrlManageVO selectFtpIntrl(FtpIntrlManageVO ftpIntrlManageVO) throws Exception;
//  여기서부터는 개발 중단
//	/**
//	 * 개별사용자에게 할당된 권한리스트 조회
//	 * @param ftpIntrlManageVO FtpIntrlManageVO
//	 * @return List<FtpIntrlManageVO>
//	 * @exception Exception
//	 */
//	public List<FtpIntrlManageVO> selectFtpIntrlList(FtpIntrlManageVO ftpIntrlManageVO) throws Exception;

}
