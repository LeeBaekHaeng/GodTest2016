package egovframework.bopr.mom.service;

import java.util.List;


/**
 * 재처리관리에 대한 Vo 클래스
 * @author  이병권
 * @since 2012.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일              수정자             수정내용
 *   -------     --------   ---------------------------
 *   2012.07.18  이병권            최초 생성
 *   2012.08.30  유현웅            VO방식 수정
 *
 * </pre>
 */

public class RehndnVO extends Rehndn {
	private static final long serialVersionUID = 1L;
	
	List<RehndnVO> rehndnList;
	
	/**
	 * rehndn 를 리턴한다.
	 * @return rehndn
	 */
	public Rehndn getRehndn()
	{
		return getRehndn();
	}
	/**
	 * rehndn 값을 설정한다.
	 * @param Rehndn rehndn
	 */	
	public void setRehndn(Rehndn rehndn)
	{
		setRehndn(rehndn);
	}
	/**
	 * rehndnList attribute 를 리턴한다.
	 * @return List<RehndnVO>
	 */
	public List<RehndnVO> getRehndnList(){
		return rehndnList;
	}
	/**
	 * rehndnList attribute 값을 설정한다.
	 * @param rehndnList List<RehndnVO> 
	 */
	public void setRehndnList(List<RehndnVO> rehndnList){
		this.rehndnList = rehndnList;
	}
}
