<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
  /**
   * @Class Name : EgovFtpIntrlDetail.jsp
   * @Description : Ftp연동관리 상세정보 화면
   * @Modification Information
   * @
   * @  수정일      수정자            수정내용
   * @ -------        --------    ---------------------------
   * @ 2012.07.16   김지완          최초 생성
   *
   *  @author 배치운영환경 김지완
   *  @since 2012.07.16
   *  @version 1.0 
   *  @see
   */
%>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="ftpIntrlManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectFtpIntrlList() {
	var varFrom = document.getElementById("ftpIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovFtpIntrlList.do'/>";
	varFrom.submit();       
}

function fncFtpIntrlGoUpdate() {
	var varFrom = document.getElementById("ftpIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovFtpIntrlUpdateView.do'/>";
	varFrom.submit();
}

function fncFtpIntrlDelete() {
	var varFrom = document.getElementById("ftpIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovFtpIntrlDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

</script>

<form:form commandName="ftpIntrlManage" method="post" >
<div class="contsBody">
	<h2>FTP연동 관리</h2>
	<div class="location">연동서비스 관리 > FTP연동 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectFtpIntrlList()">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncFtpIntrlGoUpdate()">수정</a></span>
		<span class="bbsBtn"><a href="javascript:fncFtpIntrlDelete()">삭제</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="FTP연동관리 상세정보입니다.">
		<caption>FTP연동관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th>FTP연동명</th>
			<td>${ftpIntrlManage.ftpIntrlckNm}</td>
		</tr>
		
		<tr>  
			<th>FTP주소</th>
			<td>${ftpIntrlManage.ftpAdres}</td>		  
		</tr>
		
		<tr>  
			<th>사용자ID</th>
			<td>${ftpIntrlManage.userId}</td>
		</tr>
		
		<%-- <tr>  
			<th>비밀번호</th>
			<td>${ftpIntrlManage.password}</td>
		</tr> --%>
		
		<tr>  
			<th>첨부파일배포경로</th>
			<td>${ftpIntrlManage.batchWdtbPath}</td>
		</tr>
		
		<tr>  
			<th>설정파일배포경로</th>
			<td>${ftpIntrlManage.cfgWdtbPath}</td>
		</tr>

		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="ftpIntrlckNo" value="<c:out value='${ftpIntrlManage.ftpIntrlckNo}'/>"/>
	
</form:form>
<c:if test="${message ne null}"><script>alert("${message}");</script></c:if>