package godsoft.test;

import org.junit.Test;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JsonLibTest {

	@Test
	public void test() {
		Aa1 aa1 = new Aa1();

		// aa1.setId(1l);
		// aa1.setId(null);

		Aa2 aa2 = new Aa2();
		Aa1 aa2Aa1 = new Aa1();
		aa2.setAa1(aa2Aa1);
		aa1.setAa2(aa2);

		Object object = null;
		JsonConfig jsonConfig = null;

		object = aa1;

		jsonConfig = new JsonConfig();

		// String[] excludes = { "" };
		String[] excludes = { "aa2" };
		// String[] excludes = { "aa1" };
		// String[] excludes = { "aa2", "aa1" };
		jsonConfig.setExcludes(excludes);

		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if ("aa2".equals(name)) {
					System.out.println("source=" + source);
					System.out.println("name=" + name);
					System.out.println("value=" + value);
					// return true;
				}
				return false;
			}
		});

		// jsonConfig.setJsonBeanProcessorMatcher(new JsonBeanProcessorMatcher() {
		// @Override
		// public Object getMatch(Class target, Set set) {
		// System.out.println("target=" + target);
		// System.out.println("set=" + set);
		// return null;
		// }
		// });

		// jsonConfig.setJsonPropertyNameProcessorMatcher(new
		// PropertyNameProcessorMatcher() {
		// @Override
		// public Object getMatch(Class target, Set set) {
		// System.out.println("target=" + target);
		// System.out.println("set=" + set);
		// return null;
		// }
		// });

		// jsonConfig.setJsonValueProcessorMatcher(new JsonValueProcessorMatcher() {
		// @Override
		// public Object getMatch(Class target, Set set) {
		// System.out.println("target=" + target);
		// System.out.println("set=" + set);
		// return null;
		// }
		// });

		// jsonConfig.setJavaPropertyFilter(new PropertyFilter() {
		// public boolean apply(Object source, String name, Object value) {
		// System.out.println("source=" + source);
		// System.out.println("name=" + name);
		// System.out.println("value=" + value);
		// return false;
		// }
		// });

		// JSON json = JSONSerializer.toJSON(object, jsonConfig);
		JSONObject json = (JSONObject) JSONSerializer.toJSON(object, jsonConfig);

		jsonConfig = new JsonConfig();
		// jsonConfig.setExcludes(new String[] { "aa1" });
		jsonConfig.setExcludes(new String[] { "" });
		JSONObject jsonAa2 = (JSONObject) JSONSerializer.toJSON(aa1.getAa2(), jsonConfig);
		json.put("aa2", jsonAa2);

		System.out.println("json=" + json);

	}

}
