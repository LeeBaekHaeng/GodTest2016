package org.apache.commons.collections;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapUtilsTest {

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("DATA_LENGTH", "22");
		map.put("DATA_LENGTH", "");
		// map.put("DATA_LENGTH", null);

		map.put("DATA_PRECISION", "");
		map.put("DATA_SCALE", "");
		map.put("COLUMN_ID", "1");

		// System.out.println(MapUtils.getInteger(map, "DATA_LENGTH"));
		System.out.println(MapUtils.getInteger(map, "DATA_LENGTH"));
		// System.out.println(MapUtils.getInteger(map, "DATA_LENGTH"));
	}

}
