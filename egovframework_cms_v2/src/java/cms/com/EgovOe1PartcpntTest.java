package cms.com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import egovframework.oe1.cms.com.service.EgovOe1PartcpntService;
import egovframework.oe1.cms.com.service.EgovOe1PartcpntVO;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngService;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;

public class EgovOe1PartcpntTest extends EgovOe1ComTestManage {

	@Resource(name = "partcpntService")
	private EgovOe1PartcpntService partcpntService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1PartcpntVO egovOe1PartcpntVO = new EgovOe1PartcpntVO();
        
    	String partcpntNm  = "test0000001";
    	String partcpntCd  = "01";
    	String partcpntOrg = "기관";
    	egovOe1PartcpntVO.setPartcpntNm(partcpntNm);
    	egovOe1PartcpntVO.setRelate(partcpntCd);
    	egovOe1PartcpntVO.setPartcpntOrg(partcpntOrg);
    	
        // JUNIT TEST CODE
        assertNotNull(egovOe1PartcpntVO);

        partcpntService.insertPartcpnt(egovOe1PartcpntVO);
        
        EgovOe1PartcpntVO result = partcpntService.selectPartcpnt(egovOe1PartcpntVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(partcpntNm, result.getPartcpntNm());

        partcpntService.deletePartcpnt(egovOe1PartcpntVO);

    }
}
