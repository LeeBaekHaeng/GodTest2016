<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>전자정부 모바일 프레임워크</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/cmm/EgovMobile-1.3.2.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/egovframework/mbl/com/uss/ussCommon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery-1.9.1.js"></script>	    
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/jquery.mobile-1.3.2.js"></script>   	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
</head>  
 
<body>
<!-- 모바일 페이지 start -->

<div data-role="page" >

<!-- header start -->
<div data-role="header" data-theme="z" class="com-egovHeaderBar">
	<h1><img src="${pageContext.request.contextPath}/images/egovframework/mbl/bod/logo.png" alt="logo"></h1>
</div>
<!-- header end -->

<!-- content start -->
<div data-role="content">
<p>모바일 전자정부 공통컴포넌트</p>
<br>
<p>** 메뉴 링크의 게시판 아이디를 현재 생성된 게시판 아이디로 바꿔주세요 **</p>
<c:import url="/WEB-INF/jsp/godsoft/mbl/com/cmm/EgovUnitMain.jsp"></c:import>
	<div data-role="collapsible-set">
		<div data-role="collapsible" data-collapsed="true">
			<h3>협업</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					
					<li><a href="${pageContext.request.contextPath}/cop/bbs/selectBoardList.mdo?bbsId=BBSMSTR_000000000001" data-ajax="false">일반게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/bbs/anonymous/selectBoardList.mdo?bbsId=BBSMSTR_000000000002" rel="external">익명게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/bbs/selectBoardList.mdo?bbsId=BBSMSTR_000000000003" data-ajax="false">공지게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/bbs/selectBoardList.mdo?bbsId=BBSMSTR_000000000004" data-ajax="false">유효게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/scp/selectScrapList.mdo" data-ajax="false">스크랩</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/cmy/CmmntyMainPage.mdo?cmmntyId=CMMNTY_0000000000001" rel="external">커뮤니티</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/sdm/EgovDeptSchdulManageDailyList.mdo" rel="external">부서일정관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/sim/EgovIndvdlSchdulManageDailyList.mdo" rel="external">일정관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/dsm/EgovDiaryManageList.mdo" rel="external">일지관리</a></li>
					<li><a href="${pageContext.request.contextPath}/cop/smt/sam/EgovAllSchdulManageList.mdo" rel="external">전체일정 목록</a></li>	
					<li><a href="${pageContext.request.contextPath}/cop/adb/selectAdbkList.mdo" rel="external">주소록관리</a></li>
				</ul>
		</div>
		<div data-role="collapsible" data-collapsed="true">
			<h3>사용자지원</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/uss/sam/stp/StplatListInqire.mdo" rel="external">약관</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/cns/CnsltListInqire.mdo" rel="external">상담관리</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/qnn/EgovQustnrRespondInfoManageListView.mdo" rel="external">설문참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olp/opp/listOnlinePollPartcptn.mdo" rel="external">온라인POLL참여</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/hpc/HpcmListInqire.mdo" rel="external">도움말</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/wor/WordDicaryListInqire.mdo" rel="external">용어사전</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/faq/FaqListInqire.mdo" rel="external">FAQ</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/qna/QnaListInqire.mdo" rel="external">Q&A</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/awa/listAdministrationWord.mdo" rel="external">행정전문용어사전</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/olh/omm/listOnlineManual.mdo" rel="external">온라인매뉴얼</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/nws/NewsInfoListInqire.mdo" rel="external">뉴스 목록</a></li>		
					<li><a href="${pageContext.request.contextPath}/sym/mnu/mcm/EgovWebSiteMapView.mdo" rel="external">사이트맵 Type A</a></li>
					<li><a href="${pageContext.request.contextPath}/sym/mnu/mcm/EgovSiteMapng.mdo" rel="external">사이트맵 Type B</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/rec/RecomendSiteListInqire.mdo" rel="external">추천사이트</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/ion/ecc/EgovEventCmpgnList.mdo" rel="external">행사/이벤트/캠페인</a></li>
					<li><a href="${pageContext.request.contextPath}/uss/mpe/selectIndvdlpgeResult.mdo" rel="external">마이페이지</a></li>
				</ul>
		</div>
		<div data-role="collapsible" data-collapsed="true">
			<h3>사용자인증</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/uat/uia/egovLoginUsr.mdo" rel="external">모바일 Login</a></li>
					<li><a href="${pageContext.request.contextPath}/index.do" rel="external">WEB Index</a></li>
				</ul>
		</div>
		
		<div data-role="collapsible" data-collapsed="true">
			<h3>신규공통컴포넌트(Front)</h3>
				<ul data-role="listview" data-inset="true" > 
					<li><a href="${pageContext.request.contextPath}/mbl/com/rns/goRealtimeNoticeMsg.mdo" rel="external">실시간 공지 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mpa/goMobilePhotoList.mdo" rel="external">모바일 사진 앨범</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/oas/goKoreaGovPortalSearchResultList.mdo" rel="external">OPEN-API 연계 서비스_대한민국정부포털 검색</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/oas/goWeatherInqire.mdo" rel="external">OPEN-API 연계 서비스_기상청 날씨 조회</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mcg/goMobileChartGraph.mdo" rel="external">모바일 차트/그래프</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mdi/goMobileDeviceIdent.mdo" rel="external">모바일 기기 식별</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/syn/goMobileSyncList.mdo" rel="external"">동기화 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mlt/goMobileMultimediaList.mdo" rel="external">멀티미디어 제어</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/ows/goMobileOfflineWebList.mdo" rel="external">오프라인 웹 서비스</a></li>
					<div data-role="collapsible-set">
						<div data-role="collapsible" data-collapsed="true">
							<h3>위치 정보 연계</h3>
							<ul data-role="listview" data-inset="true" data-theme="d">
								<li><a href="${pageContext.request.contextPath}/mbl/com/geo/goGeoLocationPublicInqire.mdo" rel="external">공공 지도 연계 서비스</a></li>
								<li><a href="${pageContext.request.contextPath}/mbl/com/geo/goGeoLocationPrivateInqire.mdo" rel="external">민간 지도 연계 서비스</a></li>
							</ul>
						</div>
					</div>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mms/goMmsWrite.mdo" rel="external">MMS 서비스 연계</a></li>
				</ul>
		</div>		
		<div data-role="collapsible" data-collapsed="true">
			<h3>신규공통컴포넌트(Back)</h3>
				<ul data-role="listview" data-inset="true" data-theme="d">
					<li><a href="${pageContext.request.contextPath}/mbl/com/rns/goRealtimeNoticeList.mdo" rel="external">실시간 공지 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mpa/selectPhotoList.mdo" rel="external">모바일 사진 앨범</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/oas/selectOpenApiInquiryHistoryList.mdo" rel="external">OPEN-API 연계 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mcg/selectChartGraphList.mdo" rel="external">모바일 차트/그래프</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mdi/selectDeviceIdentList.mdo" rel="external">모바일 기기 식별</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/syn/selectSyncList.mdo" rel="external">동기화 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mlt/selectMultimediaList.mdo" rel="external">멀티미디어 제어</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/ows/selectOfflineWebList.mdo" rel="external">오프라인 웹 서비스</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/geo/selectBuildingLocationInfoList.mdo" rel="external">위치 정보 연계</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mms/selectMmsTransResultList.mdo" rel="external">MMS 서비스 연계_MMS 전송 결과 조회</a></li>
					<li><a href="${pageContext.request.contextPath}/mbl/com/mms/selectMmsAttachFileList.mdo" rel="external">MMS 서비스 연계_MMS 첨부파일 관리</a></li>
				</ul>
		</div>		
	</div>
</div>
<!-- content end -->

<!-- footer start -->
<div data-role="footer" data-theme="z" class="com-egovFooterBar paddT10" data-position="fixed">
<h4>Copyright (c) MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION.</h4>
</div>
<!-- footer end -->

</div>
<!-- 모바일 페이지 end -->
</body>
</html>

