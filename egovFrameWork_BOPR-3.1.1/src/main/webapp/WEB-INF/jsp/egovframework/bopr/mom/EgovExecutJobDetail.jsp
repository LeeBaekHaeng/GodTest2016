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
  * @Class Name : EgovExecutJobDetail.jsp
  * @Description : 실행중 Job 관리 상세 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.18 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.18
  *  @version 1.0 
  *  @see
  */
%>
<c:set var="registerFlag" value="${'UPDATE'}"/>
<c:set var="registerFlagName" value="${'실행중 Job 관리 상세'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- 
<validator:javascript formName="executJob" staticJavascript="false" xhtml="true" cdata="false"/>
--Validation 부분 이후 추가 필요
 -->
<script type="text/javaScript" language="javascript">
function fncSelectExecutJobList() {
	var varFrom = document.getElementById("executJobManage");
	varFrom.action = "<c:url value='/bopr/mom/EgovExecutJobList.do'/>";
	varFrom.submit();       
}

function fncExecutJobUpdate() {
	var varFrom = document.getElementById("executJobManage");
	varFrom.action = "<c:url value='/bopr/mom/EgovExecutJobUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        //if(!validateExecutJobManage(varFrom)){ --Validation 부분 이후 추가 필요        
        //    return;
        //}else{
            varFrom.submit();
        //} 
    }
}

function fncExecutJobDelete() {
	var varFrom = document.getElementById("executJobManage");
	varFrom.action = "<c:out value='${executURL}' /><c:url value='/bopr/mom/EgovExecutJobDelete.do'/>";
	if(confirm("중지 하시겠습니까?")){
	    varFrom.submit();
	}
}

</script>

<form:form name="executJobManage" commandName="executJobManage" method="post">
<div class="contsBody">
	<h2>실행중 Job 관리</h2>
	<div class="location">배치운영 > 실행중 Job 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="실행중 Job 관리 목록" href="javascript:fncSelectExecutJobList()">목록</a></span>
		<c:if test="${registerFlag == 'UPDATE'}">
		<!-- <span class="bbsBtn"><a href="javascript:fncExecutJobUpdate()">수정</a></span> -->
		<span class="bbsBtn"><a title="실행중 Job 중지" href="javascript:fncExecutJobDelete()">중지</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="Job 실행 ID, 인스턴스ID, 버전, 수행일시, 상태, 최종수정시점 등의 작업이력관리 상세정보입니다.">
		<caption>실행중 Job 관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 실행 ID</th>
			<td>
			<label for="jobExecutionId" class="disp_none">Job 실행 ID</label>
			<input name="jobExecutionId" id="jobExecutionId" title="Job 실행 ID" type="text" readonly value="<c:out value='${executJob.jobExecutionId}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 인스턴스 ID</th>
			<td>
			<label for="jobInstanceId" class="disp_none">Job 인스턴스 ID</label>
			<input name="jobInstanceId" id="jobInstanceId" title="Job 인스턴스 ID" type="text" readonly value="<c:out value='${executJob.jobInstanceId}'/>"  maxLength="13" size="20" />
     		</td>
		</tr>
		<tr>
			<th>버전</th>
			<td>
			<label for="version" class="disp_none">버전</label>
			<input name="version" id="version" title="버전" type="text" value="<c:out value='${executJob.version}'/>" maxLength="50" size="20" /></td>
		</tr>
		<tr>
			<th>수행일시</th>
			<td>
			<label for="startTime" class="disp_none">수행일시</label>
			<input name="startTime" id="startTime" title="수행일시" type="text" readonly value="<c:out value="${executJob.startTime}"/>" maxLength="18" size="20" /></td>
		</tr>
		<tr>
			<th>상태</th>
			<td>
			<label for="sttus" class="disp_none">상태</label>
			<input name="sttus" id="sttus" title="상태" type="text" value="<c:out value="${executJob.sttus}"/>" maxLength="80" size="20" readonly /></td>
		</tr>
		<tr>
			<th>최종수정시점</th>
			<td>
			<label for="lastUpdtPnttm" class="disp_none">최종수정시점</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="최종수정시점" type="text" value="<c:out value="${executJob.lastUpdtPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		</tbody>
		</table>
	</div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${executJobVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${executJobVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${executJobVO.pageIndex}'/>"/>
<input type="hidden" name="loginId" id="loginId" value="<c:out value='${loginId}' />">
<input type="hidden" name="executURL" id="executURL" value="<c:out value='${executURL}' />">
<input type="hidden" name="returnURL" id="returnURL" value="<c:out value='${returnURL}' />">

</form:form>
