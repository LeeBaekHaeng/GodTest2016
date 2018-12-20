package org.apache.commons.lang3;

import org.junit.Test;

public class RandomStringUtilsTest {

	@Test
	public void test() {
		int count = 10;
		count = 2000;

		System.out.println(RandomStringUtils.random(count));
		System.out.println(RandomStringUtils.random(count, "god갓"));
		System.out.println(RandomStringUtils.randomAlphabetic(count));
		System.out.println(RandomStringUtils.randomAlphanumeric(count));
		System.out.println(RandomStringUtils.randomAscii(count));
		System.out.println(RandomStringUtils.randomNumeric(count));
	}

}
