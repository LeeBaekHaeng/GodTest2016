package egovframework.bopr.bam.service;

import java.util.List;


/**
 * 배치심의요청관리에 대한 Vo 클래스
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
public class BatchDlbrtVO extends BatchDlbrt {
	private static final long serialVersionUID = 1L;
	
	List <BatchDlbrtVO> batchDlbrtList;
	
	/**
	 * BatchDlbrt 를 리턴한다.
	 * @return BatchDlbrt
	 */
	public BatchDlbrt getBatchDlbrt()
    {
    	return getEgovBatchDlbrt();
    }
	/**
	 * BatchDlbrt 값을 설정한다.
	 * @param batchDlbrt BatchDlbrt
	 */	
    public void setBatchDlbrt(BatchDlbrt batchDlbrt)
    {
    	setEgovBatchDlbrt(batchDlbrt);
    }

	/**
	 * egovBatchDlbrtList attribute 를 리턴한다.
	 * @return List<BatchDlbrtVO>
	 */
	public List<BatchDlbrtVO> getBatchDlbrtList() {
		return batchDlbrtList;
	}

	/**
	 * egovBatchDlbrtList attribute 값을 설정한다.
	 * @param egovBatchDlbrtList List<BatchDlbrtVO> 
	 */
	public void setBatchDlbrtList(List<BatchDlbrtVO> batchDlbrtList) {
		this.batchDlbrtList = batchDlbrtList;
	}
}
