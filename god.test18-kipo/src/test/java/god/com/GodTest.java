package god.com;

import org.junit.Test;

public class GodTest {

	@Test
	public void test() {
		String msg = "문자열";
		msg = "문자열abc123";
		msg = "대전광역시 유성구 지족북로 60, 205동 1501호(지족동, 한화꿈에그린 2블럭)";
		int 자르고싶은크기 = 90;
		자르고싶은크기 = 3;
		byte[] bytes = msg.getBytes();
		// for (byte b : bytes) {
		// System.out.println(b);
		// }
		if (bytes.length > 자르고싶은크기) {
			msg = new String(bytes, 0, 자르고싶은크기);
		}
		System.out.println(msg);

		// 출처: http://goni9071.tistory.com/entry/java-byte-substring [고니의꿈]

		msg = "대전광역시 유성구 지족북로 60, 205동 1501호(지족동, 한화꿈에그린 2블럭)";
		msg += "대전광역시 유성구 지족북로 60, 205동 1501호(지족동, 한화꿈에그린 2블럭)";
		System.out.println(msg.substring(0, 30));
		System.out.println(msg.substring(30, 60));
		System.out.println(msg.substring(60));
	}

}
