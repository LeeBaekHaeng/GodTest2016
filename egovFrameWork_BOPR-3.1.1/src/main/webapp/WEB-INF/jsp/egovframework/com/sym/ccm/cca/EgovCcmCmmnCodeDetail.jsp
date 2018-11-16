<%
 /**
  * @Class Name  : EgovCcmCmmnCodeDetail.jsp
  * @Description : EgovCcmCmmnCodeDetail 화면
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
 * 초기화
 ******************************************************** */
function fnInit(){
}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fnList(){
	location.href = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeList.do' />";
}
/* ********************************************************
 * 수정화면으로  바로가기
 ******************************************************** */
function fnModify(){
	var varForm				 = document.getElementById("Form");
	varForm.action           = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeModify.do'/>";
	varForm.codeId.value     = "${result.codeId}";
	varForm.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fnDelete(){
	if (confirm("비사용으로 전환하시겠습니까?")) {
		var varForm				 = document.getElementById("Form");
		varForm.action           = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeRemove.do'/>";
		varForm.codeId.value     = "${result.codeId}";
		varForm.submit();
	}
}

</script>

<div class="contsBody">
	<h2>공통코드 관리</h2>
	<div class="location">공통코드 관리 > 공통코드 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fnList()" title="공통코드 상세정보 목록으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fnModify()" title="공통코드 상세정보 수정화면으로 이동">수정</a></span>
		<c:if test="${result.useAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fnDelete()" title="해당 공통코드를 비사용으로 전환">비사용으로 전환</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="분류코드명, 코드ID, 코드명, 코드설명, 사용여부의 순서로 보여지는 공통코드관리 상세정보입니다.">
		<caption>공통코드관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th>분류코드명</th>
			<td>${result.clCodeNm}</td>
		</tr>
		<tr>
			<th>코드ID</th>
			<td>${result.codeId}</td>
		</tr>
		<tr>
			<th>코드명</th>
			<td>${result.codeIdNm}</td>
		</tr>
		<tr>
			<th>코드설명</th>
			<td>
				<label for="codeIdDc" class="disp_none">코드설명</label>
				<textarea class="textarea"  cols="75" rows="14"  style="width:450px;" disabled id="codeIdDc">${result.codeIdDc}</textarea></td>
		</tr>
		<tr>
		<th>사용여부</th>
			<td>
				<label for="useAt" class="disp_none">사용여부</label>
				<select name="useAt" disabled id="useAt">
					<option value="Y" <c:if test="${result.useAt == 'Y'}">selected="selected"</c:if> >Yes</option>
					<option value="N" <c:if test="${result.useAt == 'N'}">selected="selected"</c:if> >No</option>
				</select>
			</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

<form name="Form" id="Form" method="post" action="">
	<input name="codeId" type="hidden">
</form>
