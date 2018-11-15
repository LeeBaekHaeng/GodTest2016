package cms.sim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestManageService;
import egovframework.oe1.cms.sim.service.EgovOe1ChangeRequestVO;


public class EgovOe1ChangeRequestManageTest  extends EgovOe1ComTestManage{
	@Resource(name = "egovOe1ChangeRequestManageService")
	private EgovOe1ChangeRequestManageService egovOe1ChangeRequestManageService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1ChangeRequestVO egovOe1ChangeRequestVO = new EgovOe1ChangeRequestVO();
        
    	String changeRqesterId = "test";
    	String changeRequstDe = "20101010";
    	String emrgncyRequstAt = "E";
    	String changeRequstNm = "test0000001";
    	String changeRequstCn = "test0000001";
    	String changeRequstResnCode = "01";

    	
    	egovOe1ChangeRequestVO.setChangeRqesterId(changeRqesterId);
    	egovOe1ChangeRequestVO.setChangeRequstDe(changeRequstDe);
    	egovOe1ChangeRequestVO.setEmrgncyRequstAt(emrgncyRequstAt);
    	egovOe1ChangeRequestVO.setChangeRequstNm(changeRequstNm);
    	egovOe1ChangeRequestVO.setChangeRequstCn(changeRequstCn);
    	egovOe1ChangeRequestVO.setChangeRequstResnCode(changeRequstResnCode);



        // JUNIT TEST CODE
        assertNotNull(egovOe1ChangeRequestVO);

        egovOe1ChangeRequestManageService.insertChangeRequest(egovOe1ChangeRequestVO);
        
        EgovOe1ChangeRequestVO result = egovOe1ChangeRequestManageService.selectChangeRequestInfo1(egovOe1ChangeRequestVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(changeRequstNm, result.getChangeRequstNm());

        egovOe1ChangeRequestManageService.deleteChangeRequest(egovOe1ChangeRequestVO);

    }
}
