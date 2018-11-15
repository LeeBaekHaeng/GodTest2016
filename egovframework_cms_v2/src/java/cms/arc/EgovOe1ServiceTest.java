package cms.arc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;

import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoService;
import egovframework.oe1.cms.arc.service.EgovOe1ServiceInfoVO;



public class EgovOe1ServiceTest extends EgovOe1ComTestManage{

	@Resource(name = "egovOe1ServiceInfoService")
	private EgovOe1ServiceInfoService serviceInfoService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1ServiceInfoVO egovOe1ServiceInfoVO = new EgovOe1ServiceInfoVO();
        
    	String svcNm = "test0000001";
    	String useAt = "Y";
    	String svcOffer = "test";
    	egovOe1ServiceInfoVO.setSvcNm(svcNm);
    	egovOe1ServiceInfoVO.setUseAt(useAt);
    	egovOe1ServiceInfoVO.setSvcOffer(svcOffer);
    	
    	
        // JUNIT TEST CODE
        assertNotNull(egovOe1ServiceInfoVO);

        serviceInfoService.insertServiceInfo(egovOe1ServiceInfoVO);
        
        EgovOe1ServiceInfoVO result = serviceInfoService.selectServiceInfo(egovOe1ServiceInfoVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(svcNm, result.getSvcNm());

        serviceInfoService.deleteServiceInfo(egovOe1ServiceInfoVO);

    }
    
}
