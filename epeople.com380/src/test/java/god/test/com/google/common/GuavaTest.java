package god.test.com.google.common;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaTest {

	// @Test
	public void test() throws Exception {
		log.info("test");

		ImmutableSet<ClassInfo> allClasses = ClassPath.from(getClass().getClassLoader()).getAllClasses();
		log.debug("allClasses", allClasses);

		for (ClassInfo classInfo : allClasses) {
			// log.debug("classInfo: {}", classInfo);
			log.debug("getName: {}", classInfo.getName());
			// log.debug("getPackageName: {}", classInfo.getPackageName());
			// log.debug("getResourceName: {}", classInfo.getResourceName());
		}
	}

	// @Test
	public void test2() throws Exception {
		log.info("test");

		String packageName = "egovframework.com.sym.mnu.mpm.service.impl";
		// packageName = "egovframework.com";
		packageName = "egovframework.com.sym.mnu.mpm.service";
		// packageName = "egovframework.com.sym.mnu.mpm";
		ImmutableSet<ClassInfo> allClasses = ClassPath.from(getClass().getClassLoader())
				.getTopLevelClasses(packageName);
		log.debug("allClasses", allClasses);

		for (ClassInfo classInfo : allClasses) {
			// log.debug("classInfo: {}", classInfo);
			log.debug("getName: {}", classInfo.getName());
			// log.debug("getPackageName: {}", classInfo.getPackageName());
			// log.debug("getResourceName: {}", classInfo.getResourceName());
		}
	}

	@Test
	public void test3() throws Exception {
		log.info("test");

		String packageName = "egovframework.com.sym.mnu.mpm.service.impl";
		packageName = "egovframework.com";
		packageName = "egovframework.com.sym.mnu.mpm.service";
		packageName = "egovframework.com.sym.mnu.mpm";
		packageName = "egovframework.com";
		packageName = "egovframework";
		packageName = "egovframework.com.sym.mnu.mpm.service.impl";
		// packageName = "egovframework.com.sym.mnu.mpm.service.impl.MenuManageDAO";
		ImmutableSet<ClassInfo> allClasses = ClassPath.from(getClass().getClassLoader())
				.getTopLevelClassesRecursive(packageName);
		log.debug("allClasses", allClasses);

		for (ClassInfo classInfo : allClasses) {
			// log.debug("classInfo: {}", classInfo);
			log.debug("getName: {}", classInfo.getName());
			// log.debug("getPackageName: {}", classInfo.getPackageName());
			// log.debug("getResourceName: {}", classInfo.getResourceName());
		}
	}

}
