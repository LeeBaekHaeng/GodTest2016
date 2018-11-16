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
	 * @Class Name : EgovRehndnRegist.jsp
	 * @Description : 재처리관리 등록 화면
	 * @Modification Information
	 * @
	 * @ 수정일      수정자    수정내용
	 * @ ----------  --------  ---------------------------
	 * @ 2012.07.18  이병권    최초 생성
	 * @ 2012.09.04  유현웅    결과 관리로 인한 화면 변경
	 *
	 *  @author SDS 이병권
	 *  @since 2012.07.18
	 *  @version 0.9 
	 *  @see
	 */
%>

<!-- 
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false" />
 -->
<script type="text/javaScript" language="javascript">
function fncInsert(){
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("사유는 500글자를 넘을수 없습니다.");
		return;
	}
	
	var varFrom = document.getElementById("rehndn");
	varFrom.action = "<c:out value='${executURL}'/><c:url value='/bopr/mom/EgovExecutResultRestart.do'/>";
	if(confirm("현재의 Job을 재시작 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncReturn() {
	history.back();
}
//내용 입력글자 제한하기
function CheckStrLength() {
  var f = document.rehndn.rehndnResn.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
</script>

<form:form name="rehndn" commandName="rehndn" method="post">
<div class="contsBody">
	<h2>재처리 관리</h2>
	<div class="location">배치운영 > 재처리 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="재처리 대상Job 상세화면 복귀" href="javascript:fncReturn()">복귀</a></span>
		<span class="bbsBtn"><a title="재처리 수행" href="javascript:fncInsert()">재처리수행</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="Job 인스턴스ID, Job 실행ID, 재처리사유, 재처리시점, 배치ID, 배치명 등의 배치심의관리 등록화면입니다.">
		<caption>재처리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 인스턴스ID</th>
			<td>
			<label for="jobInstanceId" class="disp_none">Job 인스턴스ID</label>
			<input name="jobInstanceId" id="jobInstanceId" title="Job 인스턴스ID" type="text" readonly value="<c:out value='${rehndn.jobInstanceId}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />Job 실행ID</th>
			<td>
			<label for="jobExecutionId" class="disp_none">Job 실행ID</label>
			<input name="jobExecutionId" id="jobExecutionId" title="Job 실행ID" type="text" readonly value="<c:out value='${rehndn.jobExecutionId}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th>재처리사유</th>
			<td>
			<label for="rehndnResn" class="disp_none">재처리사유</label>
			<textarea name="rehndnResn" id="rehndnResn" title="재처리사유" rows="5" cols="80"><c:out value="${rehndn.rehndnResn}"/></textarea></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치ID</th>
			<td>
			<label for="batchId" class="disp_none">배치ID</label>
			<input name="batchId" id="batchId" title="배치ID" type="text" readonly value="<c:out value='${rehndn.batchId}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th>배치명</th>
			<td>
			<label for="batchNm" class="disp_none">배치명</label>
			<input name="batchNm" id="batchNm" title="배치명" type="text" readonly value="<c:out value='${rehndn.batchNm}'/>"  size="20" /></td>
		</tr>
	</tbody>
	</table>
	</div>
</div>
	 
<input type="hidden" name="loginId" id="loginId" value="<c:out value='${loginId}' />">
<input type="hidden" name="executURL" id="executURL" value="<c:out value='${executURL}' />">
<input type="hidden" name="returnURL" id="returnURL" value="<c:out value='${returnURL}' />">
	 
</form:form>