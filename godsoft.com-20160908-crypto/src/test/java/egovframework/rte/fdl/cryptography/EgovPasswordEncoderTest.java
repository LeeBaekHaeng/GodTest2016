package egovframework.rte.fdl.cryptography;

import org.junit.Test;

public class EgovPasswordEncoderTest {

	@Test
	public void test() {
		// String[] args = { "SHA-256", "egovframework" };
		String[] args = { "SHA-256", "egovframe" };

		EgovPasswordEncoder.main(args);
	}

}
