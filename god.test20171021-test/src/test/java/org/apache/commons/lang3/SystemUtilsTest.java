package org.apache.commons.lang3;

import org.junit.Test;

public class SystemUtilsTest {

	@Test
	public void test() {
		System.out.println("SystemUtils.USER_DIR: " + SystemUtils.USER_DIR);
		System.out.println("SystemUtils.USER_HOME: " + SystemUtils.USER_HOME);
		System.out.println("SystemUtils.getUserDir(): " + SystemUtils.getUserDir());
		System.out.println("SystemUtils.getUserHome(): " + SystemUtils.getUserHome());
	}

}
