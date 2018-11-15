package cms.srm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqService;
import egovframework.oe1.cms.srm.service.EgovOe1OperImprovReqVO;


public class EgovOe1OperImprovReqTest   extends EgovOe1ComTestManage{
	@Resource(name = "operImprovReqService")
	private EgovOe1OperImprovReqService egovOe1OperImprovReqService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1OperImprovReqVO egovOe1OperImprovReqVO = new EgovOe1OperImprovReqVO();
        
    	String operImprvmRequstSj = "test0000001";
    	String operImprvmRequstCn = "test0000001";
    	String operJobSeCode = "00";
    	String comptRequstDe = "20101010";
    	String requstSttusCode = "01";
    	String  frstRegisterId = "test";
    	
    	egovOe1OperImprovReqVO.setOperImprvmRequstSj(operImprvmRequstSj);
    	egovOe1OperImprovReqVO.setOperImprvmRequstCn(operImprvmRequstCn);
    	egovOe1OperImprovReqVO.setOperJobSeCode(operJobSeCode);
    	egovOe1OperImprovReqVO.setComptRequstDe(comptRequstDe);
    	egovOe1OperImprovReqVO.setRequstSttusCode(requstSttusCode);
    	egovOe1OperImprovReqVO.setFrstRegisterId(frstRegisterId);


        // JUNIT TEST CODE
        assertNotNull(egovOe1OperImprovReqVO);

        egovOe1OperImprovReqService.insertOperImprovReq(egovOe1OperImprovReqVO);
        
        EgovOe1OperImprovReqVO result = egovOe1OperImprovReqService.selectOperImprovReq(egovOe1OperImprovReqVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(operImprvmRequstSj, result.getOperImprvmRequstSj());

        egovOe1OperImprovReqService.deleteOperImprovReq(egovOe1OperImprovReqVO);

    }
}
