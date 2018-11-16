<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovExecutJobManage.jsp
  * @Description : 실행중 Job 관리 화면
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
            	if(confirm("[" + checkCount + "]건 중지하시겠습니까?")) {
	           		 returnBoolean = true;
	           	} else {
	           		 returnBoolean = false;
	           	}
            else {
                alert("선택된 실행중 Job 관리목록이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 실행중 Job 관리목록이 없습니다.");
                returnBoolean = false;
            }
            else {
            	if(confirm("[1]건 중지하시겠습니까?")) {
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

function fncSelectExecutJobList(pageNo){
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
	    document.listForm.action = "<c:url value='/bopr/mom/EgovExecutJobList.do'/>";
	    document.listForm.submit();	
	}else{
		return;
	}
}

function fncSelectExecutJob(executJob) {
    document.listForm.jobExecutionId.value = executJob;
    document.listForm.action = "<c:url value='/bopr/mom/EgovExecutJob.do'/>";
    document.listForm.submit();     
}

function fncExecutJobDeleteList() {
    if(fncManageChecked()) {	
        document.listForm.action = "<c:out value='${executURL}' /><c:url value='/bopr/mom/EgovExecutJobListDelete.do'/>";
        document.listForm.submit();
    }
}

function fncAddExecutJobView() {
    document.listForm.action = "<c:url value='/bopr/mom/EgovExecutJobUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/mom/EgovExecutJobList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectExecutJobList('1');
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

function fncOnLoad(){
//	var message = document.listForm.message.value;
//	if(message!=null && message!=''){
//		alert(message);
//	}
}
</script>

<form name="listForm" action="<c:url value='/bopr/mom/EgovExecutJobList.do'/>" method="post">
<div class="contsBody">
	<h2>실행중 Job 관리</h2>
	<div class="location">배치운영 > 실행중 Job 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${executJobVO.searchCondition == '1'}"> selected </c:if>>Job 이름</option>
				<option value="2" <c:if test="${executJobVO.searchCondition == '2'}"> selected </c:if>>Job인스턴스ID</option>       
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text" 
				<c:if test="${executJobVO.searchKeyword ne ''}">value="<c:out value='${executJobVO.searchKeyword}'/>"</c:if>
				<c:if test="${executJobVO.searchKeyword eq '' && executJobVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if> 
			onkeyup="press();return false;" />                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectExecutJobList('1');return false;"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a title="실행중 Job 중지" href="javascript:fncExecutJobDeleteList();">중지</a></span>
	</div>

	<div class="bbsList">
		<table  summary="Job 실행 ID, 인스턴스ID, 버전, 수행일시, 상태 등의 실행중 Job 목록입니다.">
		<caption>실행중 Job 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:20%" >
			<col style="width:20%" >
			<col style="width:auto" >
			<col style="width:20%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" name="checkAll" class="check2" onclick="javascript:fncCheckAll()"></th>
			<th scope="col">Job 이름</th>
			<th scope="col">Job 인스턴스 ID</th>
			<th scope="col">버전</th>
			<th scope="col">수행시간</th>
			<th scope="col">상태</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(executJobList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="executJob" items="${executJobList}" varStatus="status">
		<tr>
			<td><input title="실행중 Job 선택" type="checkbox" name="delYn" class="check2"><input type="hidden" name="checkId" value="<c:out value="${executJob.jobExecutionId}"/>" /></td>
		    <td><a href="javascript:fncSelectExecutJob('<c:out value="${executJob.jobExecutionId}"/>')"><c:out value="${executJob.jobName}"/></a></td>
		    <td><c:out value="${executJob.jobInstanceId}"/></td>
		    <td><c:out value="${executJob.version}"/></td>
		    <td><c:out value="${executJob.startTime}"/></td>
		    <td class="center"><c:out value="${executJob.sttus}"/></td>
		    <!-- 	
			<td><a href="javascript:fncSelectExecutJob('<c:out value="${executJob.jobExecutionId}"/>')"><img src="/images/egovframework/bopr/search.gif" alt="상세보기" /></a></td>
			 -->
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
 	<input type="hidden" name="executURL" id="executURL" value="<c:out value='${executURL}' />">
	<input type="hidden" name="returnURL" id="returnURL" value="<c:out value='${returnURL}' />">
</div>
</form>