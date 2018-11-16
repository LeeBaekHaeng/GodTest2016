<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovExecutJobManage.jsp
  * @Description : 재처리가능 Job 관리 화면
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

<!-- 달력팝업  -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";

    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                	    returnValue = returnValue + ";" + checkField[i].value;
                    checkCount++;
                }
            }
            if(checkCount > 0) 
            	if(confirm("[" + checkCount + "]건 삭제하시겠습니까?")) {
	           		 returnBoolean = true;
	           	 } else {
	           		 
	           		 returnBoolean = false;
	           	 }
            else {
                alert("선택된 재처리 Job 관리목록이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 재처리 Job 관리목록이 없습니다.");
                returnBoolean = false;
            }
            else {
            	if(confirm("[1]건 삭제하시겠습니까?")) {
					
					returnValue = checkId.value;
					returnBoolean = true;
				} else {
				 
					returnBoolean = false;
				}
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.executJobIds.value = returnValue;

    return returnBoolean;
}

function fncSelectRehndnJobList(pageNo){
	var searchCondition = document.listForm.searchCondition.value;
	var searchKeyword = document.listForm.searchKeyword.value;

	var flag = 0;
	document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "");
	if(searchCondition!="" && searchKeyword==""){
		alert("검색어를 입력하세요");
		flag = 1;
	}
	if(flag==0){
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:url value='/bopr/mom/EgovRehndnJobList.do'/>";
	    document.listForm.submit();
	}else{
		return;
	}
}

function fncSelectRehndnJob(executJob) {
    document.listForm.jobExecutionId.value = executJob;
    document.listForm.action = "<c:url value='/bopr/mom/EgovRehndnJob.do'/>";
    document.listForm.submit();     
}

function fncRehndnJobDeleteList() {

    if(fncManageChecked()) {	
        document.listForm.action = "<c:url value='/bopr/mom/EgovRehndnJobListDelete.do'/>";
        document.listForm.submit();
    }
}

function fncSelectRehndnJobNm(executJob) {
    document.listForm.action = "<c:url value='/bopr/mom/EgovRehndnJobList.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/mom/EgovRehndnJobList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectRehndnJobList('1');
    }
}

function fncChangeCondition(){
	if(document.listForm.searchCondition.value == ''){
		document.listForm.searchKeyword.value="검색조건을 선택하세요";
		document.listForm.searchKeyword.disabled="disabled";
	}else{
		document.listForm.searchKeyword.value="";
		document.listForm.searchKeyword.disabled="";
	}
}

function fncClearSearchCondition(){
	document.listForm.searchKeyword.value="검색조건을 선택하세요";
	document.listForm.searchKeyword.disabled="disabled";
	document.listForm.searchCondition.value="";
	document.listForm.startTime.value="";
}
function fncOnLoad(){
//	var message = document.listForm.message.value;
//	if(message!=null && message!=''){
//		alert(message);
//	}
}

</script>

<form name="listForm" id="listForm" action="<c:url value='/bopr/mom/EgovRehndnJobList.do'/>" method="post">

<!-- 달력 팝업 URL -->
<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />

<div class="contsBody">
	<h2>재처리 관리</h2>
	<div class="location">배치운영 > 재처리 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${executJobVO.searchCondition == '1'}"> selected </c:if>>Job이름</option>    
				<option value="2" <c:if test="${executJobVO.searchCondition == '2'}"> selected </c:if>>Job인스턴스ID</option>       
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text"
				<c:if test="${executJobVO.searchKeyword ne ''}">value="<c:out value='${executJobVO.searchKeyword}'/>"</c:if>
				<c:if test="${executJobVO.searchKeyword eq '' && executJobVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if> 
			onkeyup="press();return false;" />
			<label for="startTime">수행일자</label>
			<label for="searchKeywordFrom" class="disp_none">검색 수행일자 입력</label>
			<input name=startTime type="text" readonly title="수행일자" size="10" value="${executJobVO.startTime}"  />
			<a href="#" title="새창" onclick="javascript:fn_egov_NormalCalendar(listForm,'', listForm.startTime);">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="검색 일자 선택">
 			</a>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectRehndnJobList('1');return false;" />
			<input type="image" class="searchbtn" title="검색조건 초기화" src="/images/egovframework/bopr/btn_refresh.gif" alt="검색조건 초기화" onclick="javascript:fncClearSearchCondition();return false;" />
		</fieldset>
	</div>
	<!-- //검색영역 -->
	<!-- 
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncRehndnJobDeleteList()">삭제</a></span>
	</div>
 	-->
	<div class="bbsList">
		<table  summary="Job 이름(배치명), Job 실행ID, 버전, 수행일시, 종료일시, 상태 등의 재실행 대상 Job 목록입니다.">
		<caption>Job 실행결과 목록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:20%" >
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:15%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">Job 이름(배치명)</th>
			<th scope="col">Job 실행 ID</th>
			<th scope="col">버전</th>
			<th scope="col">수행일시</th>
			<th scope="col">종료일시</th>
			<th scope="col">상태</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(rehndnJobList) == 0}">
		<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="executJob" items="${rehndnJobList}" varStatus="status">
		<tr>
		    <td><a href="javascript:fncSelectRehndnJob('<c:out value="${executJob.jobExecutionId}"/>')"><c:out value="${executJob.jobName}"/></a></td>
		    <td><c:out value="${executJob.jobExecutionId}"/></td>
		    <td><c:out value="${executJob.version}"/></td>
		    <td><c:out value="${executJob.startTime}"/></td>
		    <td><c:out value="${executJob.endTm}"/></td>
		    <td class="center"><c:out value="${executJob.sttus}"/></td>	
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<div class="paging">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	<!-- //페이징 -->
	<input type="hidden" name="jobExecutionId"/>
	<input type="hidden" name="executJobIds"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${executJobVO.pageIndex}'/>"/>
	<!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>