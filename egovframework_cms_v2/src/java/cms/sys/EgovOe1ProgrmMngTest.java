package cms.sys;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngService;
import egovframework.oe1.cms.sys.service.EgovOe1ProgrmMngVO;

public class EgovOe1ProgrmMngTest extends EgovOe1ComTestManage {

	@Resource(name="progrmMngService")
	public EgovOe1ProgrmMngService egovOe1ProgrmMngService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1ProgrmMngVO egovOe1ProgrmMngVO = new EgovOe1ProgrmMngVO();
        
    	String fileUrl  = "/url/test/allTest.do";
    	egovOe1ProgrmMngVO.setFileUrl(fileUrl);
    	
        // JUNIT TEST CODE
        assertNotNull(egovOe1ProgrmMngVO);

        egovOe1ProgrmMngService.insertProgrmMng(egovOe1ProgrmMngVO);
        
        EgovOe1ProgrmMngVO result = egovOe1ProgrmMngService.selectProgrmMng(egovOe1ProgrmMngVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(fileUrl, result.getFileUrl());

        egovOe1ProgrmMngService.deleteProgrmMng(egovOe1ProgrmMngVO);

    }
}
