package god.epeople;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ApiTest7 {

	// https://developer.android.com/training/articles/security-ssl?hl=ko#java

	@Test
	public void test() throws Exception {
		URL url = new URL("https://www.epeople.go.kr/shareApi/getIdeaList.do");
		URLConnection urlConnection = url.openConnection();
		InputStream in = urlConnection.getInputStream();
		String s = IOUtils.toString(in);
		System.out.println(s);
	}

	@Test
	public void test2() throws Exception {
		// Load CAs from an InputStream
		// (could be from a resource or ByteArrayInputStream or ...)
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// From https://www.washington.edu/itconnect/security/ca/load-der.crt
		InputStream caInput = new BufferedInputStream(
				new FileInputStream("C:\\Users\\godsoft\\Desktop\\epeoplegokr.crt"));
		Certificate ca;
		try {
			ca = cf.generateCertificate(caInput);
			System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
		} finally {
			caInput.close();
		}

		// Create a KeyStore containing our trusted CAs
		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		// Create a TrustManager that trusts the CAs in our KeyStore
		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
		tmf.init(keyStore);

		// Create an SSLContext that uses our TrustManager
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, tmf.getTrustManagers(), null);

		// Tell the URLConnection to use a SocketFactory from our SSLContext
		URL url = new URL("https://www.epeople.go.kr/shareApi/getIdeaList.do");
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
		urlConnection.setSSLSocketFactory(context.getSocketFactory());
		InputStream in = urlConnection.getInputStream();
		String s = IOUtils.toString(in);
		System.out.println(s);
	}

	@Test
	public void test3() throws Exception {
		// Create an HostnameVerifier that hardwires the expected hostname.
		// Note that is different than the URL's hostname:
		// example.com versus example.org
		HostnameVerifier hostnameVerifier = new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
				return hv.verify("epeople.go.kr", session);
			}
		};

		// Tell the URLConnection to use our HostnameVerifier
		URL url = new URL("https://www.epeople.go.kr/shareApi/getIdeaList.do");
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
		urlConnection.setHostnameVerifier(hostnameVerifier);
		InputStream in = urlConnection.getInputStream();
		String s = IOUtils.toString(in);
		System.out.println(s);
	}

}
