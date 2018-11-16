<%
 /**
  * @Class Name  : EgovCcmCmmnCodeList.jsp
  * @Description : EgovCcmCmmnCodeList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리
 ******************************************************** */
function fnSearch(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fnRegist(){
	location.href = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeRegist.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fnModify(){
	location.href = "";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fnDetail(codeId){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeDetail.do'/>";
	varForm.codeId.value     = codeId;
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	//
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
	if(!document.listForm.searchKeyword.disabled){
		if(document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "") != ""){
			fnSearch();	
		}else{
			window.alert("검색어를 입력하세요");
			document.listForm.searchKeyword.select();
		}
	}else{
		window.alert("검색조건을 선택하세요");
		document.listForm.searchCondition.focus();
	}
	
}

</script>

<form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;" action="">
<div class="contsBody">
	<h2>공통코드 관리</h2>
	<div class="location">공통코드 관리 > 공통코드 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
				<option value="">검색조건선택</option>
				<option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>>코드ID</option>
				<option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>>코드명</option>
			</select>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
				<c:if test="${searchVO.searchKeyword ne ''}">value="<c:out value='${searchVO.searchKeyword}'/>"</c:if>
				<c:if test="${searchVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			/>                       
			
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fnRegist()" title="공통코드 등록화면으로 이동">등록</a></span>
	</div>

	<div class="bbsList">
		<table  summary="순번, 분류명, 코드ID, 코드명, 사용여부의 순서로 보여지는 공통코드관리 목록입니다.">
		<caption>공통코드관리 목록</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:20%" >
			<col style="width:30%" >
			<col style="width:auto" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">순번</th>
			<th scope="col">분류명</th>
			<th scope="col">코드ID</th>
			<th scope="col">코드명</th>
			<th scope="col">사용여부</th>
		</tr>
		</thead>
		<tbody>
		
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
			
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
		<tr>
			<td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
			<td>${resultInfo.clCodeNm}</td>
			<td>${resultInfo.codeId}</td>
			<td><a href="<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeDetail.do'/>?codeId=${resultInfo.codeId}" onclick="javascript:fnDetail('${resultInfo.codeId}');" title="공통코드 상세정보 화면으로 이동">
				${resultInfo.codeIdNm}
				</a>
			</td>
			<td><c:if test="${resultInfo.useAt == 'Y'}">사용</c:if><c:if test="${resultInfo.useAt == 'N'}">미사용</c:if></td>
		</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty searchVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>

</form>
<form name="Form" id="Form" method="post" action="">
	<input type=hidden name="codeId">
</form>
