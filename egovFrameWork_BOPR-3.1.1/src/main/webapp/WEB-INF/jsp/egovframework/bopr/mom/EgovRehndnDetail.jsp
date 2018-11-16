<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%
	/**
	 * @Class Name : EgovRehndnDetail.jsp
	 * @Description : 재처리관리 상세 조회 화면
	 * @Modification Information
	 * @
	 * @ 수정일              수정자           수정내용
	 * @ ----------  --------  ---------------------------
	 * @ 2012.07.18    이병권           최초 생성
	 * @ 2012.09.18    유현웅           화면 내용 변경 
	 *
	 *  @author SDS 이병권
	 *  @since 2012.07.18
	 *  @version 0.9 
	 *  @see
	 */
%>

<!-- <script type="text/javascript" src="<c:url value="/validator.do"/>"></script> -->
<!--<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>-->
<script type="text/javaScript" language="javascript">            
function fncList()
{
 	var varForm = document.getElementById("rehndnManage");
 	varForm.action = "<c:url value='/bopr/mom/selectRehndnList.do'/>";
 	varForm.submit();
}

function fncUpdate()
{
	var varForm = document.getElementById("rehndnManage");
	varForm.action = "<c:url value='/bopr/mom/updateRehndn.do'/>";
	if(confirm("저장 하시겠습니까?")){
 		varForm.submit();
	}
}

function fncDelete()
{
	var varForm = document.getElementById("rehndnManage");
	varForm.action = "<c:url value='/bopr/mom/deleteRehndn.do'/>";
	if(confirm("삭제 하시겠습니까?")){
		varForm.submit();
	}
}
function fncReturn() {
	history.back();
}
</script>

<form:form name="rehndnManage" commandName="rehndnManage" method="post">
<div class="contsBody">
	<h2>재처리결과 관리</h2>
	<div class="location">배치운영 > 재처리결과 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="재처리결과 관리 목록" href="javascript:fncList()">목록</a></span>
		<span class="bbsBtn"><a title="재처리결과 내용 수정" href="javascript:fncUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="재처리결과 내용 삭제" href="javascript:fncDelete()">삭제</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="재처리번호, Job 인스턴스ID, 재처리사유, 재처리시점, 배치ID, 배치명, 상태, 결과코드 등의 재처리수행관리 상세정보입니다.">
		<caption>재처리결과관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />재처리번호</th>
			<td>
			<label for="rehndnNo" class="disp_none">재처리번호</label>
			<input name="rehndnNo" id="rehndnNo" title="재처리번호" type="text" readonly value="<c:out value='${rehndn.rehndnNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 인스턴스 ID</th>
			<td>
			<label for="jobInstanceId" class="disp_none">Job 인스턴스 ID</label>
			<input name="jobInstanceId" id="jobInstanceId" title="Job 인스턴스 ID" type="text" readonly value="<c:out value='${rehndn.jobInstanceId}'/>"  maxLength="13" size="20" />
     		</td>
		</tr>
		<tr>
			<th>재처리사유</th>
			<td>
			<label for="rehndnResn" class="disp_none">재처리사유</label>
			<textarea name="rehndnResn" id="rehndnResn" title="재처리사유" rows="5" cols="80"><c:out value="${rehndn.rehndnResn}"/></textarea></td>
		</tr>
		<tr>
			<th>재처리시점</th>
			<td>
			<label for="rehndnPnttm" class="disp_none">재처리시점</label>
			<input name="rehndnPnttm" id="rehndnPnttm" title="재처리시점" type="text" readonly value="<c:out value="${rehndn.rehndnPnttm}"/>" maxLength="18" size="30" /></td>
		</tr>
		<tr>
			<th>배치ID</th>
			<td>
			<label for="startTime" class="disp_none">수행일시</label>
			<input name="batchId" id="batchId" title="수행일시" type="text" value="<c:out value="${rehndn.batchId}"/>" size="20" readonly /></td>
		</tr>
		<tr>
			<th>배치명</th>
			<td>
			<label for="batchNm" class="disp_none">배치ID</label>
			<input name="batchNm" id="batchNm" title="배치ID" type="text" value="<c:out value="${rehndn.batchNm}"/>" size="20" readonly/></td>
		</tr>
		<tr>
			<th>상태</th>
			<td>
			<label for="status" class="disp_none">상태</label>
			<input name="status" id="status" title="상태" type="text" value="<c:out value="${rehndn.status}"/>" size="20" readonly/></td>
		</tr>
		<tr>
			<th>결과코드</th>
			<td>
			<label for="exitCode" class="disp_none">결과코드</label>
			<input name="exitCode" id="exitCode" title="결과코드" type="text" value="<c:out value="${rehndn.exitCode}"/>" size="20" readonly/></td>
		</tr>
		</tbody>
		</table>
	</div>
</div>

<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition"	value="<c:out value='${rehndnVO.searchCondition}'/>" />
<input type="hidden" name="searchKeyword" value="<c:out value='${rehndnVO.searchKeyword}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${rehndnVO.pageIndex}'/>" />

</form:form>
