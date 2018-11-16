<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovJobKnwldgList.jsp
  * @Description : Job지식 관리 화면
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
	
    var othbcAt = document.listForm.othbcAt;
    var knwldgNm = document.listForm.knwldgNm;
    
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkField[i].value = checkId[i].value;
                    
                    if(othbcAt[i].value == "N"){
                    	window.alert(knwldgNm[i].value+"은(는) 이미 비공개 지식입니다.");
                    	return false;
                    }
                    
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
                alert("선택된 지식이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 지식이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
                checkCount = 1;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.knwldgNo.value = returnValue;
    document.listForm.checkCount.value = checkCount;
    
    return returnBoolean;
}

function fncSelectJobKnwldgList(pageNo){

    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/ikm/EgovJobKnwldgList.do'/>";
    document.listForm.submit();
}

function fncSelectJobKnwldg(knwldgNo) {
    document.listForm.knwldgNo.value = knwldgNo;
    document.listForm.action = "<c:url value='/bopr/ikm/EgovJobKnwldg.do'/>";
    document.listForm.submit();     
}

function fncAddJobKnwldgInsert() {
    location.replace("<c:url value='/bopr/ikm/EgovJobKnwldgInsertView.do'/>"); 
}

function fncJobKnwldgDeleteList() {

    if(fncManageChecked()) {	
        if(confirm(document.listForm.checkCount.value+"개의 지식을 비공개로 전환하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/ikm/EgovJobKnwldgListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddJobKnwldgView() {
    document.listForm.action = "<c:url value='/bopr/ikm/EgovJobKnwldgUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/ikm/EgovJobKnwldgList.do'/>";
    document.listForm.submit();
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
function fncPress(){
	if (event.keyCode==13) {
		fncSearchList();
	}
}
function fncSearchList(){
	var searchKeywordFrom = document.listForm.searchKeywordFrom.value;
	var searchKeywordTo = document.listForm.searchKeywordTo.value;
	
	if(!document.listForm.searchKeyword.disabled){
		if(document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "") != ""){
			if(searchKeywordFrom != '' && searchKeywordTo != ''){
				if(searchKeywordFrom>searchKeywordTo){
					alert("검색시작일이 검색종료일보다 클 수 없습니다.");
					return;
				}
				fncSelectJobKnwldgList('1');		
			}
			
			fncSelectJobKnwldgList('1');	
		}else{
			window.alert("검색어를 입력하세요");
			document.listForm.searchKeyword.select();
		}
	}else if(searchKeywordFrom != '' && searchKeywordTo != ''){
		if(searchKeywordFrom>searchKeywordTo){
			alert("검색시작일이 검색종료일보다 클 수 없습니다.");
			return;
		}
		fncSelectJobKnwldgList('1');		
	}else{
		window.alert("검색조건을 선택하세요");
		document.listForm.searchCondition.focus();
	}
	
}
function fncClearSearchCondition(){
	document.listForm.searchKeyword.value="검색조건을 선택하세요";
	document.listForm.searchKeyword.disabled="disabled";
	document.listForm.searchCondition.value="";
	document.listForm.searchKeywordFrom.value="";
	document.listForm.searchKeywordTo.value="";
}
</script>

<form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;" action=""  enctype="multipart/form-data">

<!-- 상세내용 -->

<div class="contsBody">
	<h2>Job 지식 관리</h2>
	<div class="location">Job지식 > Job 지식 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			<select name="searchCondition" id="searchCondition" class="serSel" title="검색어 선택" onchange="javascript:fncChangeCondition();">
	            <option value="">검색조건선택</option>
				<option value="1" <c:if test="${jobKnwldgManageVO.searchCondition == '1'}">selected</c:if>>지식명</option>
				<option value="2" <c:if test="${jobKnwldgManageVO.searchCondition == '2'}">selected</c:if>>지식내용</option>
				<option value="3" <c:if test="${jobKnwldgManageVO.searchCondition == '3'}">selected</c:if>>등록자</option>   
			</select>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
				<c:if test="${jobKnwldgManageVO.searchKeyword ne ''}">value="<c:out value='${jobKnwldgManageVO.searchKeyword}'/>"</c:if>
				<c:if test="${jobKnwldgManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			/>
			&nbsp;&nbsp;등록일자&nbsp;
			<input name="searchKeywordFrom" readonly type="text" title="수행일자" size="10" value="<c:out value="${jobKnwldgManageVO.searchKeywordFrom}"/>"  />
				<a href="#LINK" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordFrom);" title="새 창열림">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="Job지식 목록 등록일자 검색 시작일">
	 			</a>
	 		 ~ &nbsp;
			<input name="searchKeywordTo" readonly type="text" title=" ~ " size="10" value="<c:out value="${jobKnwldgManageVO.searchKeywordTo}"/>"  />
				<a href="#LINK" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordTo);" title="새 창열림">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="Job지식 목록 등록일자 검색 종료일">
	 			</a>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
			<input type="image" class="searchbtn" title="초기화" src="/images/egovframework/bopr/btn_refresh.gif" alt="초기화" onclick="javascript:fncClearSearchCondition();return false;" />                       
			
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddJobKnwldgInsert()" title="지식 등록 화면으로 이동">등록</a></span>
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncJobKnwldgDeleteList()" title="선택된 지식을 비공개로 전환">비공개로 전환</a></span>
		</c:if>
	</div>

	<div class="bbsList">
		<table  summary="지식명, 지식공개여부, 조회수, 등록자, 등록일시의 순서로 보여지는 Job지식 목록입니다.">
		<caption>지식관리 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:12%" >
			<col style="width:12%" >
			<col style="width:20%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><label for="checkAll" class="disp_none">전체선택</label><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
			<th scope="col">지식명</th>
			<th scope="col">지식공개여부</th>
			<th scope="col">조회수</th>
			<th scope="col">등록자</th>
			<th scope="col">등록일시</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(jobKnwldgList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="jobKnwldg" items="${jobKnwldgList}" varStatus="status">
		<tr>
			<td>
				<label for="delYn" class="disp_none">삭제여부체크</label>
				<input type="checkbox" name="delYn" class="check2" title="삭제여부체크"><input type="hidden" name="checkId" value="<c:out value="${jobKnwldg.knwldgNo}"/>" /></td>
			<td><a href="javascript:fncSelectJobKnwldg('<c:out value="${jobKnwldg.knwldgNo}"/>')">${jobKnwldg.knwldgNm}</a></td>
			<td>
				<input type="hidden" name="knwldgNm" value="${jobKnwldg.knwldgNm}"/>
				<input type="hidden" name="othbcAt" value="${jobKnwldg.othbcAt}"/>
				<c:if test="${jobKnwldg.othbcAt == 'Y'}">공개</c:if>
    			<c:if test="${jobKnwldg.othbcAt == 'N'}">비공개</c:if>
			</td>
			<td>${jobKnwldg.rdcnt}</td>
			<td>${jobKnwldg.frstRegisterId}</td>
			<td>${jobKnwldg.frstRegistPnttm}</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty jobKnwldgManageVO.pageIndex }">
	<div class="paging">
		<!-- 어떻게 하지? -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>
<!-- //상세내용 -->
	
	<!-- Hidden값 -->
	<input type="hidden" name="knwldgNo"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${jobKnwldgManageVO.pageIndex}'/>"/>
	<input type="hidden" name="checkCount"/>
	<!-- 달력 팝업 URL -->
 	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />	
</form>
