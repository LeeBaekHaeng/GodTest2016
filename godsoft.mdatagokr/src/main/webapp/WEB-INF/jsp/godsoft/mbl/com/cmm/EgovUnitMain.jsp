<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div data-role="collapsible-set">

	<div data-role="collapsible" data-collapsed="true">
		<h3>대전광역시</h3>
		<ul data-role="listview" data-inset="true" data-theme="d">
			<li><a
				href="${pageContext.request.contextPath}/getKidSafeDaejeonList.mdo">대전광역시
					어린이보호구역 정보</a></li>
			<li><a
				href="${pageContext.request.contextPath}/selectDonggu001List.mdo">대전광역시
					동구 주차장정보</a></li>
		</ul>
	</div>

	<div data-role="collapsible" data-collapsed="true">
		<h3>산림청</h3>
		<ul data-role="listview" data-inset="true" data-theme="d">
			<li><a
				href="${pageContext.request.contextPath}/ecoFrstyOpenAPI.mdo">산림자원
					> 경제림육성단지구역도</a></li>
			<li><a
				href="${pageContext.request.contextPath}/bkmntBdOpenAPI.mdo">산림자원
					> 백두대간보호구역도</a></li>
			<li><a href="${pageContext.request.contextPath}/tree.mdo">산림자원
					> 해외산림투자정보서비스</a></li>
			<li><a
				href="${pageContext.request.contextPath}/rcrfrInfoOpenAPI.mdo">산림자원
					> 휴양림고시구역도</a></li>

			<li><a
				href="${pageContext.request.contextPath}/iSpecimenOpenAPI.mdo">국가생물종
					지식정보 > 곤충표본</a></li>

			<li><a
				href="${pageContext.request.contextPath}/gdTrailInfoOpenAPISelectList.mdo">휴양문화
					> 명산등산로</a></li>
			<li><a
				href="${pageContext.request.contextPath}/frstEduInfoOpenAPI.mdo">휴양문화
					> 산림교육프로그램안내</a></li>
			<li><a
				href="${pageContext.request.contextPath}/mntInfoOpenAPI.mdo">휴양문화
					> 산정보</a></li>
			<li><a
				href="${pageContext.request.contextPath}/fStoryOpenAPI.mdo">휴양문화
					> 숲에사는 식물정보</a></li>
			<li><a
				href="${pageContext.request.contextPath}/traVllgFrstOpenAPI.mdo">휴양문화
					> 전통마을숲위치도</a></li>
			<li><a
				href="${pageContext.request.contextPath}/forest/frstFireOpenAPI.mdo">산림재해
					> 산불발생위치도</a></li>
			<li><a
				href="${pageContext.request.contextPath}/forest/mountListSearch.mdo">산림재해
					> 산악기상정보</a></li>
		</ul>
	</div>
	<div data-role="collapsible" data-collapsed="true">
		<h3>광주광역시</h3>
		<ul data-role="listview" data-inset="true" data-theme="d">
			<li><a
				href="${pageContext.request.contextPath}/selectGwangju001List.mdo">광주광역시
					의료기관 현황</a></li>
		</ul>
	</div>

	<div data-role="collapsible" data-collapsed="true">
		<h3>농림축산식품부</h3>
		<ul data-role="listview" data-inset="true">
			<li><a
				href="${pageContext.request.contextPath}/Grid_20150407000000000218_1.mdo">낙농체험
					목장 정보</a></li>
		</ul>
	</div>

	<div data-role="collapsible" data-collapsed="true">
		<h3>세종특별자치시</h3>
		<ul data-role="listview" data-inset="true" data-theme="d">
			<li><a
				href="${pageContext.request.contextPath}/sejong/selectSejong01List.mdo">세종시
					모범음식점</a></li>
		</ul>
	</div>

</div>
