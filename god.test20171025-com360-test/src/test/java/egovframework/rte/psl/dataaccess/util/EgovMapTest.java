package egovframework.rte.psl.dataaccess.util;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EgovMapTest {

	protected Logger egovLogger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		EgovMap egovMap = new EgovMap();

		egovMap.put("col", "일");
		egovMap.put("col2", "이");

		System.out.println("egovMap: " + egovMap);

		@SuppressWarnings("rawtypes")
		List list = egovMap.valueList();
		int size = list.size();
		int size2 = size - 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			Object o = list.get(i);
			System.out.println("o: " + o);
			sb.append(o);
			if (i < size2) {
				System.out.println(",");
				sb.append(",");
			}
		}

		System.out.println("sb: " + sb);

		egovLogger.debug("이백행");
	}

}
