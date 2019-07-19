package god.com;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodTest {

	@Test
	public void test() throws ClassNotFoundException {

		log.info("test");

		String className = "egovframework.com.sym.mnu.mpm.service.impl.MenuManageDAO";
		// className = "GodAbstractTest";
		// className = "god.com.GodAbstractTest";

		@SuppressWarnings("rawtypes")
		Class c = Class.forName(className);
		// log.debug("c: {}", c);
		//
		// log.debug("getName: {}", c.getName());
		// log.debug("getSimpleName: {}", c.getSimpleName());
		String simpleName = c.getSimpleName();
		// String simpleNameCamelCase = CamelUtil.convert2CamelCase(simpleName);
		// String simpleNameCamelCase = EgovStringUtil.convertToCamelCase(simpleName);
		// String simpleNamePascalCase =
		// NamingUtils.convertCamelcaseToPascalcase(simpleName);
		String simpleNamePascalCase = toPascalCase(simpleName);

		// log.debug("simpleName: {}", simpleName);
		// // log.debug("simpleNameCamelCase: {}", simpleNameCamelCase);
		// log.debug("simpleNamePascalCase: {}", simpleNamePascalCase);
		// log.debug(EgovStringUtil.convertToCamelCase("Menu_Manage_DAO"));

		//
		// Field[] fields = c.getFields();
		// log.debug("fields: {}", fields.toString());
		// for (Field field : fields) {
		// log.debug("field: {}", field);
		// }
		//
		// Field[] declaredFields = c.getDeclaredFields();
		// log.debug("declaredFields: {}", declaredFields.toString());
		// for (Field field : declaredFields) {
		// log.debug("field: {}", field);
		// }

		// Method[] methods = c.getMethods();
		// log.debug("methods: {}", methods.toString());
		// for (Method method : methods) {
		// log.debug("method: {}", method);
		// }

		Method[] declaredMethods = c.getDeclaredMethods();
		// log.debug("declaredMethods: {}", declaredMethods.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (Method method : declaredMethods) {
			// log.debug("method: {}", method);
			// log.debug("name: {}", method.getName());
			// log.debug("name: {}", method.getParameters());

			String name = method.getName();
			Parameter[] parameters = method.getParameters();

			// if (!"selectMenuManageList".equals(name)) {
			// continue;
			// }

			// log.debug("name: {}", name);
			// log.debug("parameters: {}", parameters.toString());
			// for (Parameter parameter : parameters) {
			// log.debug("parameter: {}", parameter);
			// log.debug("getType: {}", parameter.getType());
			// log.debug("getParameterizedType: {}", parameter.getParameterizedType());
			// log.debug("getName: {}", parameter.getName());
			// }

			sb.append(getParameters2(parameters));

			// sb.append(simpleName);
			// sb.append(simpleNameCamelCase);
			sb.append(simpleNamePascalCase);
			sb.append(".");
			sb.append(name);
			sb.append(getParameters(parameters));
			sb.append("\n\n");
		}
		String s = sb.toString();
		log.debug(s);
	}

	private String toPascalCase(String name) {
		StringBuffer result = new StringBuffer();
		result.append(name.substring(0, 1).toLowerCase());
		result.append(name.substring(1));
		return result.toString();
	}

	private String getParameters(Parameter[] parameters) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		if (parameters.length == 0) {
			sb.append("();");
		} else {
			sb.append("(");
			for (Parameter parameter : parameters) {
				// sb2.append(parameter.getName());
				String simpleName = parameter.getType().getSimpleName();
				if (simpleName.lastIndexOf("VO") > -1) {
					sb2.append("vo");
				} else {
					sb2.append(toPascalCase(simpleName));
				}
				sb2.append(", ");
			}
			sb.append(sb2.subSequence(0, sb2.length() - 2));
			sb.append(");");
		}
		return sb.toString();
	}

	private String getParameters2(Parameter[] parameters) {
		StringBuilder sb = new StringBuilder();
		for (Parameter parameter : parameters) {
			// sb2.append(parameter.getName());
			String simpleName = parameter.getType().getSimpleName();
			if (simpleName.lastIndexOf("VO") > -1) {
				sb.append("");
				sb.append(simpleName);
				sb.append(" vo = new ");
				sb.append(simpleName);
				sb.append("();");
			} else {
				sb.append(toPascalCase(simpleName));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
