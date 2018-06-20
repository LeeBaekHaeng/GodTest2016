import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectTest {

	// http://gyrfalcon.tistory.com/entry/Java-Reflection

	@Test
	public void test() throws Exception {
		String className = "egovframework.com.cmm.ComDefaultVO";
		// className = "java.lang.Class";
		Class<?> clazz = Class.forName(className);

		// getConstructors(c);
		// getDeclaredMethods(c);
		// getMethods(clazz);

		// clazz.getModifiers();
		// clazz.getSuperclass();
		// clazz.getComponentType();
		// clazz.getName();
		// clazz.getAnnotations();
		// clazz.getCanonicalName();
		// clazz.getClassLoader();
		// clazz.getClasses();
		// clazz.getConstructors();
		// clazz.getDeclaredAnnotations();
		// clazz.getDeclaredClasses();
		// clazz.getDeclaredConstructors();
		// clazz.getDeclaredFields();
		// clazz.getDeclaredMethods();
		// clazz.getDeclaringClass();
		// clazz.getEnclosingClass();
		// clazz.getEnclosingConstructor();
		// clazz.getEnclosingMethod();
		// clazz.getEnumConstants();
		// clazz.getFields();
		// clazz.getGenericInterfaces();
		// clazz.getGenericSuperclass();
		// clazz.getInterfaces();
		// clazz.getMethods();
		// clazz.getPackage();
		// clazz.getProtectionDomain();
		// clazz.getSigners();
		// clazz.getSimpleName();
		// clazz.getTypeParameters();
		// clazz.getClass();

		Object obj = clazz.newInstance();
		Method method = null;
		System.out.println("obj=" + obj);

		method = clazz.getMethod("getFirstIndex");
		System.out.println(method.invoke(obj));

		Class<?>[] parameterTypes = new Class<?>[] { int.class };
		method = clazz.getMethod("setFirstIndex", parameterTypes);
		Object[] args = new Object[] { 2 };
		method.invoke(obj, args);

		method = clazz.getMethod("getFirstIndex");
		System.out.println(method.invoke(obj));
	}

	private void getConstructors(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getConstructors();
		System.out.println("constructors=" + constructors);
		for (Constructor<?> constructor : constructors) {
			System.out.println("constructor=" + constructor);
			System.out.println("constructor=" + constructor.getName());
		}
	}

	private void getDeclaredMethods(Class<?> clazz) {
		Method[] declaredMethods = clazz.getDeclaredMethods();
		System.out.println("declaredMethods=" + declaredMethods);
		for (Method method : declaredMethods) {
			System.out.println("method=" + method);
		}
	}

	private void getMethods(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		// System.out.println("methods=" + methods);
		for (Method method : methods) {
			String methodName = method.getName();

			// System.out.println("method=" + method);
			// System.out.println("method=" + method.getName());

			// System.out.println("methodName=" + methodName);

			if (methodName.indexOf("get") != -1) {
				// if (methodName.indexOf("Parameter") != -1) {
				// System.out.println("methodName=" + methodName);

				Class<?>[] parameterTypes = method.getParameterTypes();
				// System.out.println("parameterTypes.length=" +
				// parameterTypes.length);
				// for (Class<?> parameterType : parameterTypes) {
				// System.out.println("parameterType=" + parameterType);
				// System.out.println("parameterType=" +
				// parameterType.getName());
				// System.out.println("parameterType=" +
				// parameterType.getSimpleName());
				// }

				if (parameterTypes.length == 0) {
					System.out.println("clazz." + methodName + "();");
				}
			}
		}
	}

}
