package cms.arc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;

import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoService;
import egovframework.oe1.cms.arc.service.EgovOe1ConnectionInfoVO;

public class EgovOe1ConnectionTest extends EgovOe1ComTestManage{
	@Resource(name = "egovOe1ConnectionInfoService")
	private EgovOe1ConnectionInfoService connectionInfoService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1ConnectionInfoVO egovOe1connectionInfoVO = new EgovOe1ConnectionInfoVO();
        
    	String cntcNm = "test0000001";
    	String provdInstt = "test0000001";
    	String useAt = "Y";
    	
    	egovOe1connectionInfoVO.setCntcNm(cntcNm);
    	egovOe1connectionInfoVO.setProvdInstt(provdInstt);
    	egovOe1connectionInfoVO.setUseAt(useAt);


        // JUNIT TEST CODE
        assertNotNull(egovOe1connectionInfoVO);

        connectionInfoService.insertConnectionInfo(egovOe1connectionInfoVO);
        
        EgovOe1ConnectionInfoVO result = connectionInfoService.selectConnectionInfo(egovOe1connectionInfoVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(cntcNm, result.getCntcNm());

        connectionInfoService.deleteConnectionInfo(egovOe1connectionInfoVO);

    }
}
