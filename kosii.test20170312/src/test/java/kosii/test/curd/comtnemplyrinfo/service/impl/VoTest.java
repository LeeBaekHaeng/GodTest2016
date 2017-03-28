package kosii.test.curd.comtnemplyrinfo.service.impl;

import kosii.test.sub.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class VoTest {

	@Test
	public void test() throws Exception {
		ComtnemplyrinfoDefaultVO searchVO = new ComtnemplyrinfoDefaultVO();
		searchVO.setFirstIndex(10);

		kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO searchVO2 = new kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO();

		System.out.println(searchVO.getFirstIndex());
		System.out.println(searchVO2.getFirstIndex());

		// kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO
		// searchVO2 =
		// (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO)
		// BeanUtils
		// .cloneBean(searchVO);
		//
		// System.out.println(searchVO2);

		// kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO
		// searchVO2 = new
		// kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO();
		//
		// searchVO2 =
		// (kosii.test.curd.comtnemplyrinfo.service.ComtnemplyrinfoDefaultVO)
		// BeanUtils
		// .cloneBean(searchVO);

		BeanUtils.copyProperties(searchVO2, searchVO);

		System.out.println(searchVO.getFirstIndex());
		System.out.println(searchVO2.getFirstIndex());
	}
}
