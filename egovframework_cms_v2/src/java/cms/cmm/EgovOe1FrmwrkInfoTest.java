package cms.cmm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;

import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageService;
import egovframework.oe1.cms.cmm.service.EgovOe1FrmwrkInfoManageVO;


public class EgovOe1FrmwrkInfoTest  extends EgovOe1ComTestManage{
	@Resource(name = "egovOe1FrmwrkInfoManageService")
	private EgovOe1FrmwrkInfoManageService egovOe1FrmwrkInfoManageService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1FrmwrkInfoManageVO egovOe1FrmwrkInfoManageVO = new EgovOe1FrmwrkInfoManageVO();
        
    	String presnatnlyr = "01";
    	String persstnlyr = "01";
    	String dbmsKindCode = "01";
    	String websKindCode = "01";
    	String wasKindCode = "01";
    	String osKindCode = "01";
    	String jdkVerCode = "01";
    	String infoChghy = "test000000001";

    	
    	egovOe1FrmwrkInfoManageVO.setPresnatnlyr(presnatnlyr);
    	egovOe1FrmwrkInfoManageVO.setPersstnlyr(persstnlyr);
    	egovOe1FrmwrkInfoManageVO.setDbmsKindCode(dbmsKindCode);
    	egovOe1FrmwrkInfoManageVO.setWebsKindCode(websKindCode);
    	egovOe1FrmwrkInfoManageVO.setWasKindCode(wasKindCode);
    	egovOe1FrmwrkInfoManageVO.setOsKindCode(osKindCode);
    	egovOe1FrmwrkInfoManageVO.setJdkVerCode(jdkVerCode);
    	egovOe1FrmwrkInfoManageVO.setInfoChghy(infoChghy);
    


        // JUNIT TEST CODE
        assertNotNull(egovOe1FrmwrkInfoManageVO);

        egovOe1FrmwrkInfoManageService.insertFrmwrkInfoManage(egovOe1FrmwrkInfoManageVO);
        
        EgovOe1FrmwrkInfoManageVO result = egovOe1FrmwrkInfoManageService.selectFrmwrkInfoManage(egovOe1FrmwrkInfoManageVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(infoChghy, result.getInfoChghy());

        egovOe1FrmwrkInfoManageService.deleteFrmwrkInfoManage(egovOe1FrmwrkInfoManageVO);

    }
}
