package egovframework.com.utl.sim.service;

import org.junit.Test;

public class EgovFileScrtyTest {

	@Test
	public void test() throws Exception {
		System.out.println("테스트");

		encryptPassword();

		// EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new
		// EgovARIACryptoServiceImpl();
		// EgovARIACryptoService
		// EgovCryptoService
	}

	public void encryptPassword() throws Exception {
		String password = "rhdxhd12"; // 공통12
		String id = "id";

		String encryptPassword = EgovFileScrty.encryptPassword(password, id);
		System.out.println("encryptPassword=" + encryptPassword);

		String encodeBinary = EgovFileScrty.encodeBinary("test".getBytes());
		System.out.println("encodeBinary=" + encodeBinary);

		byte[] decodeBinary = EgovFileScrty.decodeBinary(encodeBinary);
		System.out.println("decodeBinary=" + decodeBinary);
		System.out.println("decodeBinary=" + decodeBinary.toString());
		System.out.println("decodeBinary=" + new String(decodeBinary));
	}
}
