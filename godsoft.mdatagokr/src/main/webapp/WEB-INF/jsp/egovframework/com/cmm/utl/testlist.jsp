<%-- *******************************************************
* Filename : testlist.jsp
* Class    :
* Function : 요소기술 테스트용 화면 목록 JSP
*
* @version   1.0
* @author    jjy
*******************************************************  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=euc-kr">
<TITLE></TITLE>
<link href="/css/egovframework/com/cmm/utl/com.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<TABLE class="table-line">
<TR>
    <TD width= "120">REQ-COM-071</TD>
    <TD width= "250">네트워크상태체크</TD>
    <TD width= "100">이용</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNetworkState&execFlag=PINGTEST')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-072</TD>
    <TD>네트워크정보확인</TD>
    <TD>이용</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNetworkState&execFlag=NETWORKINFO')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-073</TD>
    <TD>디렉토리권한체크</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileAuthor')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-074</TD>
    <TD>디렉토리감시</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryMntrg')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-075</TD>
    <TD>디렉토리복사</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryCopy')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-076 (REQ-COM-132)</TD>
    <TD>디렉토리삭제</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryFileDelete')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-077 (REQ-COM-130)</TD>
    <TD>디렉토리생성</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryCreat')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-078</TD>
    <TD>디렉토리속성정보체크</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryInfoCeck')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-079</TD>
    <TD>디렉토리압축/해제</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileCmprs')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-080 (REQ-COM-131)</TD>
    <TD>디렉토리이동</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryMvmn')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-081</TD>
    <TD>디렉토리일자체크</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryDeCeck')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-082</TD>
    <TD>디렉토리존재체크</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryExst')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-083</TD>
    <TD>디스크속성정보체크?</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDiskAttrb')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-084</TD>
    <TD>디스크유효용량체크</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDiskCpcty')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-085</TD>
    <TD>디스크존재체크</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDiskExst')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-086</TD>
    <TD>서버정보확인</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSysInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-087</TD>
    <TD>시스템정보확인?</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSysInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-088</TD>
    <TD>유효메모리체크?</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSysInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-089</TD>
    <TD>클라이언트정보확인</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovClntInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-090</TD>
    <TD>파일권한체크</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileAuthor')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-091</TD>
    <TD>파일변환?</TD>
    <TD>이용</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNetworkState&execFlag=CONVERTPDF')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-092</TD>
    <TD>파일보안?</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileScrty')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-093</TD>
    <TD>파일복사?</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileCopy')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-094</TD>
    <TD>파일비교?</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileCmpr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-095</TD>
    <TD>파일속성정보체크</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-096</TD>
    <TD>파일송/수신</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFtpTool')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-097</TD>
    <TD>파일일자체크</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileDate')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-098</TD>
    <TD>파일존재체크</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileExst')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-099</TD>
    <TD>파일파싱</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFilePars')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-100</TD>
    <TD>프로세스ID확인</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovProcsInfo')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-101</TD>
    <TD>웹에디터</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovWebEditor')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-102</TD>
    <TD>전자관인출력</TD>
    <TD>이중호</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovErncslOutpt')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-103</TD>
    <TD>프린터상태확인상태확인</TD>
    <TD>장동한</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovPrintStatus')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-104</TD>
    <TD>화면인쇄</TD>
    <TD>장동한</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovPrint')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-105</TD>
    <TD>메인메뉴?</TD>
    <TD>이용</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMenuGov&execFlag=MENUGOV')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-106</TD>
    <TD>트리메뉴</TD>
    <TD>이용</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMenuGov&execFlag=TREEMENUGOV')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-107</TD>
    <TD>세션처리</TD>
    <TD>이삼섭</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSession')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-108</TD>
    <TD>쿠키처리</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovCookieProcess')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-109</TD>
    <TD>날짜/시간/요일계산?</TD>
    <TD>이중호</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovWeekCalc')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-110</TD>
    <TD>날짜/시간/요일변환?</TD>
    <TD>이중호</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDateCnvr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-111</TD>
    <TD>날짜/시간/요일유효성체크</TD>
    <TD>이중호</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDateValidCeck')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-112</TD>
    <TD>날짜/시간/요일포맷변경</TD>
    <TD>이중호</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDfkCnvr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-113</TD>
    <TD>랜덤날짜구하기</TD>
    <TD>이중호</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDeRndmCreate')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-114</TD>
    <TD>랜덤문자열구하기?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovRandomStr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-115</TD>
    <TD>랜덤숫자구하기</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovRandomNumber')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-116</TD>
    <TD>숫자검색?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNumberSearch')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-117</TD>
    <TD>숫자변환?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNumberCnvr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-118</TD>
    <TD>숫자유효성체크?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNumberValidCeck')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-119</TD>
    <TD>숫자치환?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovNumberReplc')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-120</TD>
    <TD>실수/정수/음수체크?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovRlnoIntgrCeck')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-121</TD>
    <TD>양력/음력변환?</TD>
    <TD>이중호</TD><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSlrcldLrrCnvr')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-122</TD>
    <TD>인코딩/디코딩?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovEncdDcd')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-123</TD>
    <TD>특수문자열처리?</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovSpclStrProcess')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-124</TD>
    <TD>환율계산</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovEhgtCalc')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-125</TD>
    <TD>TIMESTAMP값구하기</TD>
    <TD>박정규</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovTimestamp')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-126</TD>
    <TD>경고메시지</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMessage')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-127</TD>
    <TD>에러메시지</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMessage')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-128</TD>
    <TD>정보메시지</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMessage')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-129</TD>
    <TD>확인메시지</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovMessage')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-130(REQ-COM-077)</TD>
    <TD>파일생성</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryCreat')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-131(REQ-COM-080)</TD>
    <TD>파일이동</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryMvmn')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-132(REQ-COM-076)</TD>
    <TD>파일삭제</TD>
    <TD>조재영</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovDrctryFileDelete')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-133</TD>
    <TD>파일업로드</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileUpload')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-134</TD>
    <TD>파일다운로드</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileDown')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-135</TD>
    <TD>파일압축/해제</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovFileCmprs')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-136</TD>
    <TD>XML데이터파싱</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovXMLDoc')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-137</TD>
    <TD>XML데이터조립</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovXMLDoc')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-138</TD>
    <TD>프로퍼티</TD>
    <TD>박지욱</TD><!--확인--><td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovProperty')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-139</TD>
    <TD>문자열변환</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovStringCase')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-140</TD>
    <TD>문자열치환</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovStringReplace')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-141</TD>
    <TD>문자열유효성체크</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovStringValidation')">열기</td>
</TR>
<TR>
    <TD>REQ-COM-142</TD>
    <TD>문자열검색</TD>
    <TD>이삼섭</TD>
    <td style="cursor:hand" onclick="javascript:window.open('EgovPageLink.do?link=cmm/utl/EgovStringIndex')">열기</td>
</TR>
</TABLE>

</BODY>
</HTML>
