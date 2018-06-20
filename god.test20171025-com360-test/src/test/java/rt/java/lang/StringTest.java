package rt.java.lang;

import org.junit.Test;

public class StringTest {

	@Test
	public void test() {
		System.out.println("MD_A".indexOf("MD_"));
		System.out.println("MD_A".indexOf("MD_", 0));
		System.out.println("MD_A".startsWith("MD_"));
		System.out.println("TB_MD_A".startsWith("MD_"));
	}

}
