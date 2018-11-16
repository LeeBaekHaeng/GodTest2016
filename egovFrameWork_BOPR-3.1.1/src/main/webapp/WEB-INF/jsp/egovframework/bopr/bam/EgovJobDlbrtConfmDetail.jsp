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
  * @Class Name : EgovJobDlbrtConfmDetail.jsp
  * @Description : 업무심의 수정 화면
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
<c:set var="registerFlag" value="${empty jobDlbrt.jobDlbrtResultCode ? 'UPDATE' : (jobDlbrt.jobDlbrtResultCode=='CN' ? 'CONFIRM' : (jobDlbrt.jobDlbrtResultCode=='RT'? 'REJECT' : 'RECONFIRM'))}"/>
<c:set var="registerFlagName" value="${empty jobDlbrt.jobDlbrtNo ? '업무심의 등록' : '업무심의 수정'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<validator:javascript formName="jobDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

function fncSelectJobDlbrtList() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtConfmList.do'/>";
	varFrom.submit();       
}

function fncJobDlbrtUpdate() {
	var checkCn = CheckStrLength1();
	if(!checkCn){
		alert("업무설명은 500글자를 넘을수 없습니다.");
		return;
	}
	var checkRm = CheckStrLength2();
	if(!checkRm){
		alert("비고는 500글자를 넘을수 없습니다.");
		return;
	}
	
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtConfmUpdate.do'/>";

	if(!validateJobDlbrt(varFrom)){
        return;
    }else{
    	if(confirm("저장 하시겠습니까?")){
      		varFrom.submit();
    	}else{
    		return;
    	}
    } 
}

function fncJobDlbrtDelete() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtConfmDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}
function fncJobConfm() {
	var varForm = document.getElementById("jobDlbrtManage");
	varForm.action = "<c:url value='/bopr/bam/EgovJobDlbrtRegistView.do'/>";
	varForm.submit();   
}

//내용 입력글자 제한하기
function CheckStrLength1() {
  var f = document.jobDlbrtManage.jobDlbrtCn.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}

//내용 입력글자 제한하기
function CheckStrLength2() {
  var f = document.jobDlbrtManage.remark.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
</script>

<form:form name="jobDlbrtManage" commandName="jobDlbrtManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>업무심의 관리</h2>
	<div class="location">배치심의 > 업무심의 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="업무심의관리 목록" href="javascript:fncSelectJobDlbrtList()">목록</a></span>
		<c:if test="${registerFlag == 'CONFIRM'}">
		<span class="bbsBtn"><a title="업무심의관리 수정" href="javascript:fncJobDlbrtUpdate()">수정</a></span>
		</c:if>
		<c:if test="${registerFlag == 'REJECT' || registerFlag == 'RECONFIRM'}">
		<span class="bbsBtn"><a title="업무심의관리 수정" href="javascript:fncJobDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="업무심의관리 삭제" href="javascript:fncJobDlbrtDelete()">삭제</a></span>
		<span class="bbsBtn"><a title="업무심의관리 재심의요청" href="javascript:fncJobConfm()">재심의</a></span>
		</c:if>
		<c:if test="${registerFlag == 'UPDATE'}">
		<span class="bbsBtn"><a title="업무심의관리 수정" href="javascript:fncJobDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="업무심의관리 삭제" href="javascript:fncJobDlbrtDelete()">삭제</a></span>
		<span class="bbsBtn"><a title="업무심의관리 심의요청" href="javascript:fncJobConfm()">심의</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="업무심의번호, 업무구분, 업무심의제목, 업무심의내용, 첨부파일, 비고, 등록수정 정보 등의 업무심의관리 상세정보입니다.">
		<caption>업무심의 관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for='jobDlbrtNo' class='disp_none'>업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" readonly value="<c:out value='${jobDlbrt.jobDlbrtNo}'/>" maxLength = "8" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무구분</th>
			<td>
			<select name="jobSeCode" id="jobSeCode" title="업무구분 선택">
		     <c:forEach var="cmmCode" items="${cmmCode}" varStatus="status">
		     	<option value='<c:out value="${cmmCode.code}"/>' <c:if test="${cmmCode.code == jobDlbrt.jobSeCode}"> selected </c:if> ><c:out value="${cmmCode.codeNm}"/>
		     </c:forEach>
		     </select>
     		</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의제목</th>
			<td>
			<label for='jobDlbrtNm' class='disp_none'>업무심의제목</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의제목" type="text" value="<c:out value='${jobDlbrt.jobDlbrtNm}'/>" maxLength="50" size="50" /></td>
		</tr>
		<tr>
			<th>업무심의내용</th>
			<td>
			<label for='lastUpdtPnttm' class='disp_none'>수정일시</label>
			<textarea name="jobDlbrtCn" id="jobDlbrtCn" title="업무구분" rows="5" cols="80"><c:out value="${jobDlbrt.jobDlbrtCn}"/></textarea></td>
		</tr>
		<c:if test="${not empty jobDlbrt.atchFileId}">
		  <tr>  
		    <th class="required_text" width="20%" nowrap>첨부파일 목록</th>
		    <td nowrap>
		    	<c:import charEncoding="utf-8" url="/cmm/fms/selectFileInfs.do">
					<c:param name="param_atchFileId" value="${jobDlbrt.atchFileId}" />
				</c:import>
		    </td>
		  </tr>
		</c:if>
		<tr>
			<th>비고</th>
			<td>
			<label for='remark' class='disp_none'>비고</label>
			<textarea name="remark" id="remark" title="비고" rows="5" cols="80"><c:out value="${jobDlbrt.remark}"/></textarea></td>
		</tr>
		<tr>
			<th>최초등록자ID</th>
			<td>
			<label for='frstRegisterId' class='disp_none'>최초등록자ID</label>
			<input name="frstRegisterId" id="frstRegisterId" title="최초등록자ID" type="text" value="<c:out value="${jobDlbrt.frstRegisterId}"/>" maxLength="20"size="20" readonly/></td>
		</tr>
		<tr>
			<th>최종수정자ID</th>
			<td>
			<label for='lastUpdusrId' class='disp_none'>최종수정자ID</label>
			<input name="lastUpdusrId" id="lastUpdusrId" title="최종수정자ID" type="text" value="<c:out value="${jobDlbrt.lastUpdusrId}"/>"maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>
			<label for='frstRegistPnttm' class='disp_none'>등록일시</label>
			<input name="frstRegistPnttm" id="frstRegistPnttm" title="등록일시" type="text" value="<c:out value="${jobDlbrt.frstRegistPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>수정일시</th>
			<td>
			<label for='lastUpdtPnttm' class='disp_none'>수정일시</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="수정일시" type="text" value="<c:out value="${jobDlbrt.lastUpdtPnttm}"/>"  maxLength="20"  size="20" readonly/></td>
		</tr>
		
		</tbody>
		</table>
	</div>
	<div class="bbsList">
	<table id="attachInfo" summary="업무심의결과번호, 업무심의결과, 업무심의 사유 등의 업무심의 승인정보입니다.">
		<caption>업무심의 승인정보</caption>
		<colgroup>
			<col style="width:15%" >
			<col style="width:25%" >
			<col style="width:40%" >
		</colgroup>
		<thead>
		<tr>
			<th>업무심의결과번호</th>
			<th>업무심의결과</th>
			<th>업무심의사유</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="jobDlbrtResult" items="${jobDlbrtResult}" varStatus="status">
		<tr>
		    <td><c:out value="${jobDlbrtResult.jobDlbrtResultNo}"/></td>
		    <td class="center">
		    <c:if test="${empty jobDlbrtResult.jobDlbrtResultCode }">
		        재승인 요청
		    </c:if>
		    <c:if test="${not empty jobDlbrtResult.jobDlbrtResultCode }">
		    <c:out value="${jobDlbrtResult.jobDlbrtResultCodeNm}"/>
		    </c:if>
		    </td>
		    <td><c:out value="${jobDlbrtResult.jobDlbrtCn}"/></td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${dlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${dlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${dlbrtVO.pageIndex}'/>"/>

</form:form>
