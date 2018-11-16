package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 심의관리에 대한 Vo 클래스
 * @author  유현웅
 * @since 2012.07.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.16  유현웅            최초 생성
 *
 * </pre>
 */
public class DlbrtVO extends Dlbrt {
	private static final long serialVersionUID = 1L;
	
	List <DlbrtVO> dlbrtList;
	
	/**
	 * JobDlbrt 를 리턴한다.
	 * @return JobDlbrt
	 */
	public Dlbrt getDlbrt()
    {
    	return getEgovDlbrt();
    }
	/**
	 * JobDlbrt 값을 설정한다.
	 * @param egovJobDlbrt JobDlbrt
	 */	
    public void setDlbrt(Dlbrt dlbrt)
    {
    	setEgovDlbrt(dlbrt);
    }

	/**
	 * egovJobDlbrtList attribute 를 리턴한다.
	 * @return List<ExecutJobVO>
	 */
	public List<DlbrtVO> getDlbrtList() {
		return dlbrtList;
	}

	/**
	 * egovJobDlbrtList attribute 값을 설정한다.
	 * @param egovJobDlbrtList List<ExecutJobVO> 
	 */
	public void setDlbrtList(List<DlbrtVO> dlbrtList) {
		this.dlbrtList = dlbrtList;
	}
}
