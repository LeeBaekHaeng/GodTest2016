<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovJobHistManage.jsp
  * @Description : 작업이력 관리 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.11 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.11
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
/*
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
                returnBoolean = true;
            else {
                alert("선택된 작업이력목록이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 작업이력목록이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.jobHistIds.value = returnValue;

    return returnBoolean;
}
*/
function fncSelectJobHistList(pageNo){
	var searchVal = document.listForm.searchKeyword.value;
	if(searchVal==""){
		alert("검색조건에 배치명을 필수로 입력하셔야 합니다.");
		return;
	}
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHistList.do'/>";
    document.listForm.submit();
}

function fncSelectJobHist(jobHist) {
    document.listForm.jobExecutionId.value = jobHist;
    document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHist.do'/>";
    document.listForm.submit();     
}

function fncJobHistDeleteList() {

    if(fncManageChecked()) {	
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHistListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddJobHistView() {
    document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHistUpdate.do'/>";
    document.listForm.submit();     
}

function fncSelectJobHistNm(jobHist) {
    document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHistList.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jhm/EgovJobHistList.do'/>";
    document.listForm.submit();
}

function fnBatchIdClick() {
	var url = "<c:url value='/bopr/bam/EgovBatchPopupList.do'/>";
	window.open(url, 'BatchPopup', 'toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=590');
}

function press() {

    if (event.keyCode==13) {
    	fncSelectJobHistList('1');
    }
}
function fncClearSearchCondition(){
	document.listForm.searchKeyword.value="";
	document.listForm.searchKeywordFrom.value="";
	document.listForm.searchKeywordTo.value="";
}
function fncOnLoad(){
//	var message = document.listForm.message.value;
//	if(message!=null && message!=''){
//		alert(message);
//	}
}
</script>

<form name="listForm" method="post" action="<c:url value='/bopr/bam/EgovBatchPopupList.do'/>">
<div class="contsBody">
	<h2>작업이력 관리</h2>
	<div class="location">배치운영 > 작업이력 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			배치명 : &nbsp;
			<label for="searchKeyword" class="disp_none">검색배치명</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="새창" type="text" value="<c:out value="${jobHistVO.searchKeyword}"/>" onkeyup="press();return false;" onclick="fnBatchIdClick();return false;"/>
			&nbsp;&nbsp;수행일자&nbsp;
			<label for="searchKeywordFrom" class="disp_none">검색 시작일자 입력</label>
			<input name="searchKeywordFrom" readonly type="text" title="검색 시작일자" size="10" value="<c:out value="${jobHistVO.searchKeywordFrom}"/>"  />
				<a href="#LINK" title="새창" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordFrom);">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="검색 시작일자 선택">
	 			</a>
	 		 ~ &nbsp;
	 		 <label for="searchKeywordTo" class="disp_none">검색 마지막일자 입력</label>
			<input name="searchKeywordTo" readonly type="text" title="검색 마지막일자" size="10" value="<c:out value="${jobHistVO.searchKeywordTo}"/>"  />
				<a href="#LINK" title="새창" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordTo);">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="검색 마지막일자 선택">
	 			</a>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectJobHistList('1');return false;"/>
			<input type="image" class="searchbtn" title="검색조건 초기화" src="/images/egovframework/bopr/btn_refresh.gif" alt="검색조건 초기화" onclick="javascript:fncClearSearchCondition();return false;" />
		</fieldset>
	</div>
	<!-- //검색영역 -->
	<!-- 
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncJobHistDeleteList();">삭제</a></span>
	</div>
	 -->
	 
	<div class="bbsList">
		<table  summary="Job 실행 ID, 인스턴스ID, 버전, 수행일시, 종료일시, 상태 등의 Job 실행이력 목록입니다.">
		<caption>Job 실행이력 목록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:20%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:15%" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">Job 실행 ID</th>
			<th scope="col">Job 인스턴스 ID</th>
			<th scope="col">버전</th>
			<th scope="col">수행일시</th>
			<th scope="col">종료일시</th>
			<th scope="col">상태</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(jobHistList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="jobHist" items="${jobHistList}" varStatus="status">
		<tr>
		    <td><a href="javascript:fncSelectJobHist('<c:out value="${jobHist.jobExecutionId}"/>')"><c:out value="${jobHist.jobExecutionId}"/></a></td>
		    <td><c:out value="${jobHist.jobInstanceId}"/></td>
		    <td><c:out value="${jobHist.version}"/></td>
		    <td><c:out value="${jobHist.startTime}"/></td>
		    <td><c:out value="${jobHist.endTm}"/></td>
		    <td class="center"><c:out value="${jobHist.sttus}"/></td>	
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
	<input type="hidden" name="pageIndex" value="<c:out value='${jobHistVO.pageIndex}'/>"/>
	<input type="hidden" name="searchCondition"/>
	<!-- 달력 팝업 URL -->
 	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
	<!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>