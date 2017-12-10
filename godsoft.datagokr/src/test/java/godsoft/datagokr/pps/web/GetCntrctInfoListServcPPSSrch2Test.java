package godsoft.datagokr.pps.web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;

import org.jsoup.Connection;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class GetCntrctInfoListServcPPSSrch2Test {

	@Test
	public void test() throws Exception {
		// connect
		String url = "http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch";

		Connection connection = Jsoup.connect(url);

		// connection.timeout(300000);

		// connection.header("Content-type", "application/json");

		// 한 페이지 결과 수
		connection.data(URLEncoder.encode("numOfRows", "UTF-8"), URLEncoder.encode("10", "UTF-8"));

		// 페이지 번호
		connection.data(URLEncoder.encode("pageNo", "UTF-8"), URLEncoder.encode("1", "UTF-8"));

		// 서비스키
		// connection.data(URLEncoder.encode("ServiceKey", "UTF-8"),
		// URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8"));
		// connection.data("ServiceKey",
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D");
		// connection.data("ServiceKey",
		// URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8"));
		connection.data("ServiceKey",
				URLDecoder.decode(
						"UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
						"UTF-8"));

		// 조회구분
		connection.data(URLEncoder.encode("inqryDiv", "UTF-8"), URLEncoder.encode("1", "UTF-8"));

		// 조회시작일자
		connection.data(URLEncoder.encode("inqryBgnDate", "UTF-8"), URLEncoder.encode("20171101", "UTF-8"));

		// 조회종료일자
		connection.data(URLEncoder.encode("inqryEndDate", "UTF-8"), URLEncoder.encode("20171131", "UTF-8"));

		// // 기관구분코드
		// connection.data(URLEncoder.encode("insttDivCd", "UTF-8"),
		// URLEncoder.encode("1", "UTF-8"));
		//
		// // 기관분류코드
		// connection.data(URLEncoder.encode("insttClsfcCd", "UTF-8"),
		// URLEncoder.encode("01", "UTF-8"));
		//
		// // 기관코드
		// connection.data(URLEncoder.encode("insttCd", "UTF-8"),
		// URLEncoder.encode("1230000", "UTF-8"));
		//
		// // 기관명
		// connection.data(URLEncoder.encode("insttNm", "UTF-8"),
		// URLEncoder.encode("조달청", "UTF-8"));
		//
		// // 공종명
		// connection.data(URLEncoder.encode("cnsttyNm", "UTF-8"),
		// URLEncoder.encode("토목공사업", "UTF-8"));

		// 용역명
		connection.data(URLEncoder.encode("cntrctNm", "UTF-8"), URLEncoder.encode("시스템", "UTF-8"));

		// // 계약방법코드
		// connection.data(URLEncoder.encode("cntrctMthdCd", "UTF-8"),
		// URLEncoder.encode("1", "UTF-8"));
		//
		// // 계약참조번호
		// connection.data(URLEncoder.encode("cntrctRefNo", "UTF-8"),
		// URLEncoder.encode("00166033106", "UTF-8"));
		//
		// // 계약구분코드
		// connection.data(URLEncoder.encode("cntrctDivCd", "UTF-8"),
		// URLEncoder.encode("1", "UTF-8"));
		//
		// // 확정계약번호
		// connection.data(URLEncoder.encode("dcsnCntrctNo", "UTF-8"),
		// URLEncoder.encode("00166033106", "UTF-8"));
		//
		// // 요청번호
		// connection.data(URLEncoder.encode("reqNo", "UTF-8"),
		// URLEncoder.encode("2102318588", "UTF-8"));
		//
		// // 공고번호
		// connection.data(URLEncoder.encode("ntceNo", "UTF-8"),
		// URLEncoder.encode("20151109261", "UTF-8"));

		Collection<KeyVal> data = connection.request().data();
		for (KeyVal keyVal : data) {
			System.out.println("keyVal=" + keyVal);
		}

		Document document = connection.get();

		System.out.println("document=" + document);
	}

}
