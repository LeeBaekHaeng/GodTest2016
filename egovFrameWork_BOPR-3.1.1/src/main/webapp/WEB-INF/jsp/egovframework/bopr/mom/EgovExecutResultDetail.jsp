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
  * @Class Name : EgovExecutResultDetail.jsp
  * @Description : Job 실행결과 관리 상세 화면
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
<c:set var="registerFlagName" value="${'Job 실행결과 상세'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- 
<validator:javascript formName="executResult" staticJavascript="false" xhtml="true" cdata="false"/>
--Validation 부분 이후 추가 필요
 -->
<script type="text/javaScript" language="javascript">
function fncSelectExecutResultList() {
	var varFrom = document.getElementById("executResultManage");
	varFrom.action = "<c:url value='/bopr/mom/EgovExecutResultList.do'/>";
	varFrom.submit();       
}

function fncExecutResultUpdate() {
	var varFrom = document.getElementById("executResultManage");
	varFrom.action = "<c:url value='/bopr/mom/EgovExecutResultUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        //if(!validateExecutResultManage(varFrom)){ --Validation 부분 이후 추가 필요        
        //    return;
        //}else{
            varFrom.submit();
        //} 
    }
}

function fncExecutResultDelete() {
	var varFrom = document.getElementById("executResultManage");
	varFrom.action = "<c:url value='/bopr/mom/EgovExecutResultDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fn_egov_excution_message(jobExecutionId, stepExecutionId) {
	
	
	var url = "<c:url value='/bopr/mom/EgovExecutResultMessagePopup.do?jobExecutionId=" + jobExecutionId + "&stepExecutionId=" + stepExecutionId + "'/>";
	
	window.open(url, 'ExcutionMessage', 'toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=0, height=0');
}

</script>

<form:form name="executResultManage" commandName="executResultManage" method="post">
<div class="contsBody">
	<h2>Job 실행결과 관리</h2>
	<div class="location">배치운영 > Job 실행결과 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="Job 실행결과 관리 목록" href="javascript:fncSelectExecutResultList()">목록</a></span>
		<c:if test="${registerFlag == 'UPDATE'}">
		<span class="bbsBtn"><a title="Job 실행결과 수정" href="javascript:fncExecutResultUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="Job 실행결과 삭제" href="javascript:fncExecutResultDelete()">삭제</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="Job 실행 ID, 인스턴스ID, 버전, 수행일시, 종료일시, 상태, 수행결과코드, 수행결과메세지, 최종수정시점 등의 실행결과관리 상세정보입니다.">
		<caption>실행결과관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 실행 ID</th>
			<td>
			<label for="jobExecutionId" class="disp_none">Job 실행 ID</label>
			<input name="jobExecutionId" id="jobExecutionId" title="Job 실행 ID" type="text" readonly value="<c:out value='${executResult.jobExecutionId}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 인스턴스 ID</th>
			<td>
			<label for="jobInstanceId" class="disp_none">Job 인스턴스 ID</label>
			<input name="jobInstanceId" id="jobInstanceId" title="Job 인스턴스 ID" type="text" readonly value="<c:out value='${executResult.jobInstanceId}'/>"  maxLength="13" size="20" />
     		</td>
		</tr>
		<tr>
			<th>버전</th>
			<td>
			<label for="version" class="disp_none">버전</label>
			<input name="version" id="version" title="버전" type="text" value="<c:out value='${executResult.version}'/>" maxLength="11" size="20" /></td>
		</tr>
		<tr>
			<th>수행일시</th>
			<td>
			<label for="startTime" class="disp_none">수행일시</label>
			<input name="startTime" id="startTime" title="수행일시" type="text" readonly value="<c:out value="${executResult.startTime}"/>" maxLength="18" size="20" /></td>
		</tr>
		<tr>
			<th>종료일시</th>
			<td>
			<label for="endTm" class="disp_none">종료일시</label>
			<input name="endTm" id="endTm" title="종료일시" type="text" value="<c:out value="${executResult.endTm}"/>" maxLength="20" size="20" readonly /></td>
		</tr>
		<tr>
			<th>상태</th>
			<td>
			<label for="sttus" class="disp_none">상태</label>
			<input name="sttus" id="sttus" title="상태" type="text" value="<c:out value="${executResult.sttus}"/>" maxLength="80" size="20" readonly /></td>
		</tr>
		<tr>
			<th>수행결과코드</th>
			<td>
			<label for="exitCode" class="disp_none">수행결과코드</label>
			<input name="exitCode" id="exitCode" title="수행결과코드" type="text" value="<c:out value="${executResult.exitCode}"/>" maxLength="20"size="20" readonly/></td>
		</tr>
		<tr>
			<th>수행결과메세지</th>
			<td>
			<label for="exitMessage" class="disp_none">수행결과메세지</label>
			<textarea name="exitMessage" id="exitMessage" title="수행결과메세지" rows="5" cols="80"><c:out value="${executResult.exitMessage}"/></textarea></td>
		</tr>
		<tr>
			<th>최종수정시점</th>
			<td>
			<label for="lastUpdtPnttm" class="disp_none">최종수정시점</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="최종수정시점" type="text" value="<c:out value="${executResult.lastUpdtPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		</tbody>
		</table>
	</div>
	<div class="bbsList">
	<table id="attachInfo" summary="Step별 실행결과 정보입니다.">
		<caption>Step별 실행결과</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:3%" >
			<col style="width:10%" >
			<col style="width:8%" >
			<col style="width:8%" >
			<col style="width:8%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:5%" >
			<col style="width:30%" >
			
		</colgroup>
		<thead>
		<tr>
			<th>Step 실행ID</th>
		    <th>버전</th>
		    <th>Step명</th>
		    <th>수행일시</th>
		    <th>종료일시</th>
		    <th>상태</th>
		    <th>Commit</th>
		    <th>Read</th>
		    <th>Filter</th>
		    <th>Write</th>
		    <th>ReadSkip</th>
		    <th>WriteSkip</th>
		    <th>ProcessSkip</th>
		    <th>RollbackSkip</th>
		    <th>수행결과코드</th>
		    <th>수행결과메세지</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="stepHist" items="${stepHist}" varStatus="status">
		<tr>
		    <td><c:out value="${stepHist.stepExecutionId}"/></td>
		    <td><c:out value="${stepHist.version}"/></td>
		    <td><c:out value="${stepHist.stepName}"/></td>
		    <td><c:out value="${stepHist.startTime}"/></td>
		    <td><c:out value="${stepHist.endTm}"/></td>
		    <td><c:out value="${stepHist.sttus}"/></td>
		    <td><c:out value="${stepHist.commitCount}"/></td>
		    <td><c:out value="${stepHist.readCount}"/></td>
		    <td><c:out value="${stepHist.filterCount}"/></td>
		    <td><c:out value="${stepHist.writeCount}"/></td>
		    <td><c:out value="${stepHist.readSkipCount}"/></td>
		    <td><c:out value="${stepHist.writeSkipCount}"/></td>
		    <td><c:out value="${stepHist.processSkipCount}"/></td>
		    <td><c:out value="${stepHist.rollbackCount}"/></td>
		    <td><c:out value="${stepHist.exitCode}"/></td>
		    <td>
		    <c:if test="${stepHist.exitCode != 'COMPLETED'}">
		    	<a href="#LINK" onclick="javascript:fn_egov_excution_message('<c:out value="${executResult.jobExecutionId}"/>', '<c:out value="${stepHist.stepExecutionId}"/>');"><img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif'/>" width="15" height="15" align="middle" alt="수행결과메세지"></a>
		    </c:if>
		    </td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${executResultVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${executResultVO.searchKeyword}'/>"/>
<input type="hidden" name="searchKeywordFrom" value="<c:out value='${executResultVO.searchKeywordFrom}'/>"/>
<input type="hidden" name="searchKeywordTo" value="<c:out value='${executResultVO.searchKeywordTo}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${executResultVO.pageIndex}'/>"/>

</form:form>