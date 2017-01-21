package godsoft.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MapTest {

	@Test
	public void test() {
		// fail("Not yet implemented");

		Map map = new HashMap();
		map.put("A1", 1);
		map.put("B1", "비1");

		List<Map> items = new ArrayList<Map>();
		Map item = new HashMap();
		item.put("AA1", 1.0d);
		item.put("BB1", "비비1");
		items.add(item);
		map.put("C1", items);

		// Map D1 = new HashMap();
		// D1.put("D1", "D1");
		// map.put("D1", D1);

		mapPrint(map);
	}

	private void mapPrint(Map<?, ?> map) {
		System.out.println(map);

		Iterator<?> keys = map.keySet().iterator();

		while (keys.hasNext()) {
			Object key = keys.next();
			Object value = map.get(key);

			String valueClassName = value.getClass().getName();

			System.out.println(key + ", " + value + ", " + valueClassName);

			if (valueClassName.equals("java.util.ArrayList")) {
				List<?> items = (List<?>) value;

				for (Object item : items) {
					System.out.println("item : " + item);

					mapPrint((Map<?, ?>) item);
				}
			}
		}
	}
}
