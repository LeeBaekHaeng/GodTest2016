package godsoft.test.curd.comtnemplyrinfo.service.impl;

import godsoft.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoVO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:egovframework/spring/com/**/context-*.xml" })
public class ComtnemplyrinfoDAOTest {

	@Autowired
	private ComtnemplyrinfoDAO comtnemplyrinfoDAO;

	@Test
	public void test() {
		// fail("Not yet implemented");

		System.out.println("comtnemplyrinfoDAO : " + comtnemplyrinfoDAO);

		ComtnemplyrinfoVO vo = new ComtnemplyrinfoVO();

		vo.setEmplyrId("god");

		vo.setUserNm("이백행");

		vo.setPassword("rhdxhd12");

		vo.setHouseAdres("대전");

		vo.setPasswordHint("P01");

		vo.setPasswordCnsr("전자정부표준프레임워크센터");

		vo.setHouseEndTelno("5678");

		vo.setAreaNo("010");

		vo.setZip("12345");

		vo.setHouseMiddleTelno("1234");

		vo.setEmplyrSttusCode("P");

		vo.setEsntlId("USRCNFRM_00000000001");

		try {
			String insert = comtnemplyrinfoDAO.insertComtnemplyrinfo(vo);

			System.out.println("insert : " + insert);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
