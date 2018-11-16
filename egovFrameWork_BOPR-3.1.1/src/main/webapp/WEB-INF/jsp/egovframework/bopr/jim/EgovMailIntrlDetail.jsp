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
   * @Class Name : EgovMailIntrlDetail.jsp
   * @Description : E-Mail연동관리 상세정보 화면
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

<title></title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mailIntrlManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectMailIntrlList() {
	var varFrom = document.getElementById("mailIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovMailIntrlList.do'/>";
	varFrom.submit();       
}

function fncMailIntrlGoUpdate() {
	var varFrom = document.getElementById("mailIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovMailIntrlUpdateView.do'/>";
	varFrom.submit();
}

function fncMailIntrlDelete() {
	var varFrom = document.getElementById("mailIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovMailIntrlDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

</script>

<form:form commandName="mailIntrlManage" method="post" >
<div class="contsBody">
	<h2>메일연동 관리</h2>
	<div class="location">메일연동 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectMailIntrlList()">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncMailIntrlGoUpdate()">수정</a></span>
		<span class="bbsBtn"><a href="javascript:fncMailIntrlDelete()">삭제</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="메일연동관리 상세정보입니다.">
		<caption>메일연동관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th>이메일연동명</th>
			<td>${mailIntrlManage.emailIntrlckNm}</td>
		</tr>
		<tr>  
			<th>이메일주소</th>
			<td>${mailIntrlManage.emailAdres}</td>
		  
		</tr>
		<tr>  
			<th>IP정보</th>
			<td>${mailIntrlManage.ipInfo}</td>
		  
		</tr>
		<tr>  
			<th>사용자ID</th>
			<td>${mailIntrlManage.userId}</td>
		</tr>
		<tr>  
			<th>비밀번호</th>
			<td>${mailIntrlManage.password}</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="emailIntrlckNo" value="<c:out value='${mailIntrlManage.emailIntrlckNo}'/>"/>
	
</form:form>
