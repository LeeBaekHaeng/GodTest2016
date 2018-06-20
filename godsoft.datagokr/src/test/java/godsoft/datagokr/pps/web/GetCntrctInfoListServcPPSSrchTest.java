package godsoft.datagokr.pps.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Test;

public class GetCntrctInfoListServcPPSSrchTest {

	@Test
	public void test() throws Exception {
		// test2();
		// test3();
		test4();
	}

	public void test2() throws Exception {
		// System.out.println("numOfRows");
		// System.out.println(URLEncoder.encode("numOfRows", "UTF-8"));
		// System.out.println(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D");
		// System.out.println(URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8"));

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D"); /*
																													 * Service
																													 * Key
																													 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		// urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") +
		// "="
		// + URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8")); /* 공공데이터포털에서 받은 인증키 */

		urlBuilder.append("&" + URLEncoder.encode("inqryDiv", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /* 검색하고자하는 조회구분, 1:계약체결일자, 2:확정계약번호, 3.요청번호, 4공고번호 */

		urlBuilder.append("&" + URLEncoder.encode("inqryBgnDate", "UTF-8") + "=" + URLEncoder.encode("20171101",
				"UTF-8")); /* 검색하고자하는 일시 범위 시작'YYYYMMDD", 조건구분이 1인 경우 필수 */

		urlBuilder.append("&" + URLEncoder.encode("inqryEndDate", "UTF-8") + "=" + URLEncoder.encode("20171131",
				"UTF-8")); /* 검색하고자하는 일시 종료 'YYYYMMDD", 조건구분이 1인 경우 필수 */

		// urlBuilder.append("&" + URLEncoder.encode("insttDivCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /*
		// * 검색하고자 하는 기관구분값, 1인 경우 계약기관, 2인 경우 수요기관. *입력값 없을시
		// * 기관구분 = '1' 조회
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttClsfcCd", "UTF-8") +
		// "=" + URLEncoder.encode("01",
		// "UTF-8")); /*
		// * 검색하고자하는 계약기관분류코드 입력 *기관분류 코드, 01:국가기관, 02:지방자치단체,
		// * 03:교육기관, 05:정부투자기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttCd", "UTF-8") + "=" +
		// URLEncoder.encode("1230000",
		// "UTF-8")); /*
		// * 검색하고자하는 기관코드 (조회구분1인 경우 선택), 기관구분 1인 경우 계약기관, 2인
		// * 경우 수요기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttNm", "UTF-8") + "=" +
		// URLEncoder.encode("조달청",
		// "UTF-8")); /*
		// * 검색하고자하는 기관명 (조회구분1인 경우 선택), 기관구분이 1인 경우 계약기관, 2인
		// * 경우 수요기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cnsttyNm", "UTF-8") + "="
		// + URLEncoder.encode("토목공사업",
		// "UTF-8")); /* 검색하고자하는 공종명 (조회구분1인 경우 선택) */

		urlBuilder.append("&" + URLEncoder.encode("cntrctNm", "UTF-8") + "=" + URLEncoder.encode("시스템",
				"UTF-8")); /* 검색하고자하는 용역명 (조회구분1인 경우 선택) */

		// urlBuilder.append("&" + URLEncoder.encode("cntrctMthdCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /*
		// * 검색 하고자 하는 계약방법 (조회구분이 1인 경우 선택),
		// * 1:일반경쟁,2:제한경쟁,3:지명경쟁,4:수의계약
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cntrctRefNo", "UTF-8") +
		// "=" + URLEncoder.encode("00166033106",
		// "UTF-8")); /*
		// * 검색하고자하는 계약참조번호 (조회구분이 1인 경우 선택). *나라장터화면에서 계약참조번호
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cntrctDivCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /* 검색하고자하는 계약구분코드 (조회구분이 1인 경우 선택) 1. 자체계약, 2.중앙조달 */
		// urlBuilder.append("&" + URLEncoder.encode("dcsnCntrctNo", "UTF-8") +
		// "=" + URLEncoder.encode("00166033106",
		// "UTF-8")); /* 검색하고자하는 확정계약번호 (조회구분이 2인 경우 필수). *나라장터화면에서 계약번호 */
		// urlBuilder.append("&" + URLEncoder.encode("reqNo", "UTF-8") + "=" +
		// URLEncoder.encode("2102318588",
		// "UTF-8")); /* 검색하고자하는 요청번호, 조회구분이 3인 경우 필수 */
		// urlBuilder.append("&" + URLEncoder.encode("ntceNo", "UTF-8") + "=" +
		// URLEncoder.encode("20151109261",
		// "UTF-8")); /* 검색하고자하는 공고번호(입찰공고번호) 조회구분이 4인 경우 필수 */

		String spec = urlBuilder.toString();
		System.out.println(spec);
		URL url = new URL(spec);
		System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}

	public void test3() throws Exception {
		// System.out.println("numOfRows");
		// System.out.println(URLEncoder.encode("numOfRows", "UTF-8"));
		// System.out.println(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D");
		// System.out.println(URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8"));

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch"); /* URL */
		urlBuilder.append("?" + "ServiceKey"
				+ "=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D"); /*
																													 * Service
																													 * Key
																													 */
		urlBuilder.append("&" + "numOfRows" + "=" + "10"); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + "pageNo" + "=" + "1"); /* 페이지 번호 */
		// urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") +
		// "="
		// + URLEncoder.encode(
		// "UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D",
		// "UTF-8")); /* 공공데이터포털에서 받은 인증키 */

		urlBuilder.append("&" + URLEncoder.encode("inqryDiv", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /* 검색하고자하는 조회구분, 1:계약체결일자, 2:확정계약번호, 3.요청번호, 4공고번호 */

		urlBuilder.append("&" + URLEncoder.encode("inqryBgnDate", "UTF-8") + "=" + URLEncoder.encode("20171101",
				"UTF-8")); /* 검색하고자하는 일시 범위 시작'YYYYMMDD", 조건구분이 1인 경우 필수 */

		urlBuilder.append("&" + URLEncoder.encode("inqryEndDate", "UTF-8") + "=" + URLEncoder.encode("20171131",
				"UTF-8")); /* 검색하고자하는 일시 종료 'YYYYMMDD", 조건구분이 1인 경우 필수 */

		// urlBuilder.append("&" + URLEncoder.encode("insttDivCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /*
		// * 검색하고자 하는 기관구분값, 1인 경우 계약기관, 2인 경우 수요기관. *입력값 없을시
		// * 기관구분 = '1' 조회
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttClsfcCd", "UTF-8") +
		// "=" + URLEncoder.encode("01",
		// "UTF-8")); /*
		// * 검색하고자하는 계약기관분류코드 입력 *기관분류 코드, 01:국가기관, 02:지방자치단체,
		// * 03:교육기관, 05:정부투자기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttCd", "UTF-8") + "=" +
		// URLEncoder.encode("1230000",
		// "UTF-8")); /*
		// * 검색하고자하는 기관코드 (조회구분1인 경우 선택), 기관구분 1인 경우 계약기관, 2인
		// * 경우 수요기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("insttNm", "UTF-8") + "=" +
		// URLEncoder.encode("조달청",
		// "UTF-8")); /*
		// * 검색하고자하는 기관명 (조회구분1인 경우 선택), 기관구분이 1인 경우 계약기관, 2인
		// * 경우 수요기관
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cnsttyNm", "UTF-8") + "="
		// + URLEncoder.encode("토목공사업",
		// "UTF-8")); /* 검색하고자하는 공종명 (조회구분1인 경우 선택) */

		// urlBuilder.append("&" + URLEncoder.encode("cntrctNm", "UTF-8") + "="
		// + URLEncoder.encode("시스템",
		// "UTF-8")); /* 검색하고자하는 용역명 (조회구분1인 경우 선택) */
		urlBuilder.append("&" + "cntrctNm" + "=" + URLEncoder.encode("시스템",
				"UTF-8")); /* 검색하고자하는 용역명 (조회구분1인 경우 선택) */

		// urlBuilder.append("&" + URLEncoder.encode("cntrctMthdCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /*
		// * 검색 하고자 하는 계약방법 (조회구분이 1인 경우 선택),
		// * 1:일반경쟁,2:제한경쟁,3:지명경쟁,4:수의계약
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cntrctRefNo", "UTF-8") +
		// "=" + URLEncoder.encode("00166033106",
		// "UTF-8")); /*
		// * 검색하고자하는 계약참조번호 (조회구분이 1인 경우 선택). *나라장터화면에서 계약참조번호
		// */
		// urlBuilder.append("&" + URLEncoder.encode("cntrctDivCd", "UTF-8") +
		// "=" + URLEncoder.encode("1",
		// "UTF-8")); /* 검색하고자하는 계약구분코드 (조회구분이 1인 경우 선택) 1. 자체계약, 2.중앙조달 */
		// urlBuilder.append("&" + URLEncoder.encode("dcsnCntrctNo", "UTF-8") +
		// "=" + URLEncoder.encode("00166033106",
		// "UTF-8")); /* 검색하고자하는 확정계약번호 (조회구분이 2인 경우 필수). *나라장터화면에서 계약번호 */
		// urlBuilder.append("&" + URLEncoder.encode("reqNo", "UTF-8") + "=" +
		// URLEncoder.encode("2102318588",
		// "UTF-8")); /* 검색하고자하는 요청번호, 조회구분이 3인 경우 필수 */
		// urlBuilder.append("&" + URLEncoder.encode("ntceNo", "UTF-8") + "=" +
		// URLEncoder.encode("20151109261",
		// "UTF-8")); /* 검색하고자하는 공고번호(입찰공고번호) 조회구분이 4인 경우 필수 */

		String spec = urlBuilder.toString();
		System.out.println(spec);
		URL url = new URL(spec);
		System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		// conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}

	public void test4() throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1230000/CntrctInfoService/getCntrctInfoListServcPPSSrch"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=UMEa5VvLLLGHOOzP2cVmtSF15EtCq4Ke7KBJR8OS63PB2EJgAZGnVZdy7saCYsrOvXzJKw4raynLW7AT0Ezsyg%3D%3D"); /*
																													 * Service
																													 * Key
																													 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode("1", "UTF-8")); /* 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("공공데이터포털에서 받은 인증키",
				"UTF-8")); /* 공공데이터포털에서 받은 인증키 */
		urlBuilder.append("&" + URLEncoder.encode("inqryDiv", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /* 검색하고자하는 조회구분, 1:계약체결일자, 2:확정계약번호, 3.요청번호, 4공고번호 */
		urlBuilder.append("&" + URLEncoder.encode("inqryBgnDate", "UTF-8") + "=" + URLEncoder.encode("20160831",
				"UTF-8")); /* 검색하고자하는 일시 범위 시작'YYYYMMDD", 조건구분이 1인 경우 필수 */
		urlBuilder.append("&" + URLEncoder.encode("inqryEndDate", "UTF-8") + "=" + URLEncoder.encode("20160831",
				"UTF-8")); /* 검색하고자하는 일시 종료 'YYYYMMDD", 조건구분이 1인 경우 필수 */
		urlBuilder.append("&" + URLEncoder.encode("insttDivCd", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /*
							 * 검색하고자 하는 기관구분값, 1인 경우 계약기관, 2인 경우 수요기관. *입력값 없을시
							 * 기관구분 = '1' 조회
							 */
		urlBuilder.append("&" + URLEncoder.encode("insttClsfcCd", "UTF-8") + "=" + URLEncoder.encode("01",
				"UTF-8")); /*
							 * 검색하고자하는 계약기관분류코드 입력 *기관분류 코드, 01:국가기관, 02:지방자치단체,
							 * 03:교육기관, 05:정부투자기관
							 */
		urlBuilder.append("&" + URLEncoder.encode("insttCd", "UTF-8") + "=" + URLEncoder.encode("1230000",
				"UTF-8")); /*
							 * 검색하고자하는 기관코드 (조회구분1인 경우 선택), 기관구분 1인 경우 계약기관, 2인
							 * 경우 수요기관
							 */
		urlBuilder.append("&" + URLEncoder.encode("insttNm", "UTF-8") + "=" + URLEncoder.encode("조달청",
				"UTF-8")); /*
							 * 검색하고자하는 기관명 (조회구분1인 경우 선택), 기관구분이 1인 경우 계약기관, 2인
							 * 경우 수요기관
							 */
		urlBuilder.append("&" + URLEncoder.encode("cnsttyNm", "UTF-8") + "=" + URLEncoder.encode("토목공사업",
				"UTF-8")); /* 검색하고자하는 공종명 (조회구분1인 경우 선택) */
		urlBuilder.append(
				"&" + URLEncoder.encode("cntrctNm", "UTF-8") + "=" + URLEncoder.encode("2016 서울반원초 교사동 외부 도장 공사",
						"UTF-8")); /* 검색하고자하는 용역명 (조회구분1인 경우 선택) */
		urlBuilder.append("&" + URLEncoder.encode("cntrctMthdCd", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /*
							 * 검색 하고자 하는 계약방법 (조회구분이 1인 경우 선택),
							 * 1:일반경쟁,2:제한경쟁,3:지명경쟁,4:수의계약
							 */
		urlBuilder.append("&" + URLEncoder.encode("cntrctRefNo", "UTF-8") + "=" + URLEncoder.encode("00166033106",
				"UTF-8")); /*
							 * 검색하고자하는 계약참조번호 (조회구분이 1인 경우 선택). *나라장터화면에서 계약참조번호
							 */
		urlBuilder.append("&" + URLEncoder.encode("cntrctDivCd", "UTF-8") + "=" + URLEncoder.encode("1",
				"UTF-8")); /* 검색하고자하는 계약구분코드 (조회구분이 1인 경우 선택) 1. 자체계약, 2.중앙조달 */
		urlBuilder.append("&" + URLEncoder.encode("dcsnCntrctNo", "UTF-8") + "=" + URLEncoder.encode("00166033106",
				"UTF-8")); /* 검색하고자하는 확정계약번호 (조회구분이 2인 경우 필수). *나라장터화면에서 계약번호 */
		urlBuilder.append("&" + URLEncoder.encode("reqNo", "UTF-8") + "=" + URLEncoder.encode("2102318588",
				"UTF-8")); /* 검색하고자하는 요청번호, 조회구분이 3인 경우 필수 */
		urlBuilder.append("&" + URLEncoder.encode("ntceNo", "UTF-8") + "=" + URLEncoder.encode("20151109261",
				"UTF-8")); /* 검색하고자하는 공고번호(입찰공고번호) 조회구분이 4인 경우 필수 */
		System.out.println(urlBuilder.toString());
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

	}

}
