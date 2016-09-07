package egovframework.rte.fdl.cryptography.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.fdl.cryptography.EgovCryptoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
// "classpath:egovframework/spring/com/**/context-*.xml",
// "classpath:egovframework/spring/com/context-common.xml",
"classpath:godsoft/spring/com/**/context-*.xml" })
public class EgovARIACryptoServiceImplTest {

	@Resource(name = "ARIACryptoService")
	private EgovCryptoService egovCryptoService;

	@Test
	public void test() throws Exception {
		// EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new
		// EgovARIACryptoServiceImpl();
		// EgovARIACryptoService
		// EgovCryptoService

		// byte[] encrypt = egovCryptoService.encrypt("data".getBytes("utf-8"),
		// "egovframe");
		byte[] encrypt = egovCryptoService.encrypt("data".getBytes(),
				"egovframe");

		System.out.println("encrypt=" + encrypt);
		System.out.println("encrypt=" + encrypt.toString());
		System.out.println("encrypt=" + new String(encrypt));

		byte[] decrypt = egovCryptoService.decrypt(encrypt, "egovframe");

		System.out.println("decrypt=" + decrypt);
		System.out.println("decrypt=" + decrypt.toString());
		System.out.println("decrypt=" + new String(decrypt));
	}

}
