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
  * @Class Name : EgovJobDlbrtConfmRegist.jsp
  * @Description : 업무심의 승인/반려 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.17 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.17
  *  @version 1.0 
  *  @see
  */
%>
<c:set var="registerFlag" value="${empty jobDlbrt.jobDlbrtResultCode ? 'NONE' : (jobDlbrt.jobDlbrtResultCode =='CN' ? 'CONFIRM' : (jobDlbrt.jobDlbrtResultCode=='RT'? 'REJECT' : 'RECONFIRM'))}"/>
<c:set var="registerFlagName" value="업무심의"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- 
<validator:javascript formName="jobDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>
--Validation 부분 이후 추가 필요
 -->
<script type="text/javaScript" language="javascript">

function fncSelectJobDlbrtList() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtConfmList.do'/>";
	varFrom.submit();       
}

function fncJobDlbrtConfm() {
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("사유는 500글자를 넘을수 없습니다.");
		return;
	}
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobConfm.do'/>";
	if(confirm("승인 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncJobDlbrtReject() {
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("사유는 500글자를 넘을수 없습니다.");
		return;
	}
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobReject.do'/>";
	if(confirm("반려 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncReturn() {
	history.back();
}
//내용 입력글자 제한하기
function CheckStrLength() {
  var f = document.jobDlbrtManage.jobDlbrtCn.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
</script>

<form:form name="jobDlbrtManage" commandName="jobDlbrtManage" method="post">
<div class="contsBody">
	<h2>업무심의 관리</h2>
	<div class="location">배치심의 > 업무심의 관리 > <strong>심의</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="업무심의관리 목록" href="javascript:fncSelectJobDlbrtList()">목록</a></span>
		<c:if test="${registerFlag == 'NONE' || registerFlag == 'RECONFIRM'}">
		<span class="bbsBtn"><a title="업무심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="업무심의 승인" href="javascript:fncJobDlbrtConfm()">승인</a></span>
		<span class="bbsBtn"><a title="업무심의 반려" href="javascript:fncJobDlbrtReject()">반려</a></span>
		</c:if>
		<c:if test="${registerFlag == 'CONFIRM'}">
		<span class="bbsBtn"><a title="업무심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="업무심의 반려" href="javascript:fncJobDlbrtReject()">반려</a></span>
		</c:if>
		<c:if test="${registerFlag == 'REJECT'}">
		<span class="bbsBtn"><a title="업무심의 취소" href="javascript:fncReturn()">이전</a></span>
		<span class="bbsBtn"><a title="업무심의 승인" href="javascript:fncJobDlbrtConfm()">승인</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="업무심의번호, 업무심의제목, 업무심의사유, 최종승인여부 등의 업무심의관리 등록화면입니다.">
		<caption>업무심의관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for='jobDlbrtNo' class='disp_none'>업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" readonly value="<c:out value='${jobDlbrt.jobDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의제목</th>
			<td>
			<label for='jobDlbrtNm' class='disp_none'>업무심의제목</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의제목" type="text" readonly value="<c:out value='${jobDlbrt.jobDlbrtNm}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th>업무심의사유</th>
			<td>
			<label for='jobDlbrtCn' class='disp_none'>업무심의사유</label>
			<textarea name="jobDlbrtCn" id="jobDlbrtCn" title="업무심의사유" rows="5" cols="80"><c:out value="${jobDlbrt.jobDlbrtCn}"/></textarea></td>
		</tr>
		<tr>
			<th>최종승인여부</th>
			<td>
			<label for='jobDlbrtResultCode' class='disp_none'>최종승인여부</label>
			<input name="jobDlbrtResultCode" id="jobDlbrtResultCode" title="최종승인여부" readonly 
			<c:if test="${empty jobDlbrt.jobDlbrtResultCode }">
			value="미승인"
			</c:if>
			<c:if test="${not empty jobDlbrt.jobDlbrtResultCode }">
			value="<c:out value="${jobDlbrt.jobDlbrtResultCodeNm}"/>"
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
