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
  * @Class Name : EgovBatchDlbrtConfmDetail.jsp
  * @Description : 배치심의 수정 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.16 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.16
  *  @version 1.0 
  *  @see
  */
%>
<c:set var="registerFlag" value="UPDATE"/>
<c:set var="registerFlag" value="${empty batchDlbrt.batchDlbrtResultCode ? 'NONE' : (batchDlbrt.batchDlbrtResultCode =='CN' ? 'CONFIRM' : (batchDlbrt.batchDlbrtResultCode=='RT'? 'REJECT' : 'RECONFIRM'))}"/>
<c:set var="registerFlagName" value="배치심의"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- 
<validator:javascript formName="batchDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>
--Validation 부분 이후 추가 필요
 -->
<script type="text/javaScript" language="javascript">

function fncSelectBatchDlbrtList() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtConfmList.do'/>";
	varFrom.submit();       
}

function fncBatchDlbrtConfm() {
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("배치설명은 500글자를 넘을수 없습니다.");
		return;
	}
	
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchConfm.do'/>";
	if(confirm("승인 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncBatchDlbrtReject() {
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("사유는 500글자를 넘을수 없습니다.");
		return;
	}
	
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchReject.do'/>";
	if(confirm("반려 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncReturn() {
	history.back();
}
//내용 입력글자 제한하기
function CheckStrLength() {
  var f = document.batchDlbrtManage.batchDlbrtCn.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
</script>

<form:form name="batchDlbrtManage" commandName="batchDlbrtManage" method="post">
<div class="contsBody">
	<h2>배치심의 관리</h2>
	<div class="location">배치심의 > 배치심의 관리 > <strong>심의</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectBatchDlbrtList()">목록</a></span>
		<c:if test="${registerFlag == 'NONE' || registerFlag == 'RECONFIRM' }">
		<span class="bbsBtn"><a title="배치심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="배치심의 승인" href="javascript:fncBatchDlbrtConfm()">승인</a></span>
		<span class="bbsBtn"><a title="배치심의 반려" href="javascript:fncBatchDlbrtReject()">반려</a></span>
		</c:if>
		<c:if test="${registerFlag == 'CONFIRM'}">
		<span class="bbsBtn"><a title="배치심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="배치심의 반려" href="javascript:fncBatchDlbrtReject()">반려</a></span>
		</c:if>
		<c:if test="${registerFlag == 'REJECT'}">
		<span class="bbsBtn"><a title="배치심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="배치심의 승인" href="javascript:fncBatchDlbrtConfm()">승인</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="배치심의번호, 업무심의번호, 배치ID, 배치심의내용/사유 등의 배치심의관리 등록화면입니다.">
		<caption>배치심의관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<!-- 기존 테이블 내용 입력 START -->
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치심의번호</th>
			<td>
			<label for="batchDlbrtNo" class="disp_none">배치심의번호</label>
			<input name="batchDlbrtNo" id="batchDlbrtNo" title="배치심의번호" type="text" readonly value="<c:out value='${batchDlbrt.batchDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for="jobDlbrtNo" class="disp_none">업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" readonly value="<c:out value='${batchDlbrt.jobDlbrtNo}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치ID</th>
			<td>
			<label for="batchId" class="disp_none">배치ID</label>
			<input name="batchId" id="batchId" title="배치ID" type="text" readonly value="<c:out value='${batchDlbrt.batchId}'/>"  maxLength="13" size="50" /></td>
		</tr>
		<tr>
			<th>배치심의내용/사유</th>
			<td>
			<label for="batchDlbrtCn" class="disp_none">배치심의내용/사유</label>
			<textarea name="batchDlbrtCn" id="batchDlbrtCn" title="배치심의내용/사유" rows="5" cols="80"><c:out value="${batchDlbrt.batchDlbrtCn}"/></textarea></td>
		</tr>
		<tr>
			<th>최종승인여부</th>
			<td>
			<label for="batchDlbrtResultCode" class="disp_none">최종승인여부</label>
			<input name="batchDlbrtResultCode" id="batchDlbrtResultCode" title="최종승인여부" readonly 
			<c:if test="${empty batchDlbrt.batchDlbrtResultCode }">
			value="미승인"
			</c:if>
			<c:if test="${not empty batchDlbrt.batchDlbrtResultCode }">
			value="<c:out value="${batchDlbrt.batchDlbrtResultCodeNm}"/>"
			</c:if>
			size="20"></td>
		</tr>
	</tbody>
	</table>
	</div>
</div>
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${dlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${dlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${dlbrtVO.pageIndex}'/>"/>

</form:form>
