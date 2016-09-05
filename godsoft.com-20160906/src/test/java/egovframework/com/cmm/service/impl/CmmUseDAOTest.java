package egovframework.com.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:egovframework/spring/com/context-*.xml",
		"classpath:egovframework/spring/com/idgn/context-*.xml" })
public class CmmUseDAOTest {

	@Resource(name = "cmmUseDAO")
	private CmmUseDAO cmmUseDAO;

	@Test
	public void test() throws Exception {
		// fail("Not yet implemented");

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM001");

		List<CmmnDetailCode> items = cmmUseDAO.selectCmmCodeDetail(vo);

		System.out.println("items=" + items);

		for (CmmnDetailCode item : items) {
			System.out.println(item.getCode());
			System.out.println(item.getCodeNm());
		}
	}

}
