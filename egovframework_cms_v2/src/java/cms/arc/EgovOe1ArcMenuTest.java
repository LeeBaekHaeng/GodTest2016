package cms.arc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import cms.com.EgovOe1ComTestManage;
import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuService;
import egovframework.oe1.cms.arc.service.EgovOe1ArcMenuVO;

public class EgovOe1ArcMenuTest extends EgovOe1ComTestManage {

	@Resource(name="egovOe1ArcMenuService")
	public EgovOe1ArcMenuService egovOe1ArcMenuService;

    @Test
    public void testSelectQustnrRespondManageDetail() throws Exception { 
    	EgovOe1ArcMenuVO egovOe1ArcMenuVO = new EgovOe1ArcMenuVO();
        
    	String menuNm  = "레벨1메뉴";
    	egovOe1ArcMenuVO.setArchtcMenuNm(menuNm);
    	egovOe1ArcMenuVO.setArchtcMenuDc("레벨1메뉴설명입니다.");
    	egovOe1ArcMenuVO.setArchtcMenuOrdr(1);
    	egovOe1ArcMenuVO.setUseAt("Y");
    	
        // JUNIT TEST CODE
        assertNotNull(egovOe1ArcMenuVO);

        egovOe1ArcMenuService.insertArcMenu(egovOe1ArcMenuVO);
        
        EgovOe1ArcMenuVO result = egovOe1ArcMenuService.selectArcMenu(egovOe1ArcMenuVO);
        
        // JUNIT TEST CODE
        assertNotNull(result);
        
        // JUNIT TEST CODE
        assertEquals(menuNm, result.getArchtcMenuNm());

        egovOe1ArcMenuService.deleteArcMenu(egovOe1ArcMenuVO);

    }
}
