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
   * @Class Name : EgovJobIssueDetail.jsp
   * @Description : JobIssue 상세정보 화면
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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="jobIssueManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
<script type="text/javaScript" language="javascript">

function fncSelectJobIssueList() {
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovJobIssueList.do'/>";
	varForm.submit();       
}

function fncJobIssueGoUpdate() {
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovJobIssueUpdateView.do'/>";
	varForm.submit();
}

function fncJobIssueDelete() {
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovJobIssueDelete.do'/>";
	if(confirm("비공개로 전환하시겠습니까?")){
	    varForm.submit();
	}
}
//답변 등록
function fncInsertIssueAnswer(){
	
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovInsertJobIssueAnwser.do'/>";
	
	if(fncTrim(varForm.answerCn.value) == ""){
		window.alert("답변을 입력해 주세요.");
		varForm.answerCn.focus();
	}else{
		if(confirm("답변을 등록하시겠습니까?")){
		    varForm.submit();
		}
	}
	
	
}

//답변 수정
function fncUpdateIssueAnswer(option,issueSttusCode){
	
	var varForm = document.getElementById("jobIssueManage");
	varForm.issueSttusCode.value = issueSttusCode;
	if(option == "view"){
		varForm.action = "<c:url value='/bopr/ikm/EgovJobIssue.do'/>";
		varForm.isUpdate.value="Y";
		varForm.submit();
		
	}else if(option == "update"){
		varForm.action = "<c:url value='/bopr/ikm/EgovUpdateJobIssueAnwser.do'/>";
		
		if(confirm("답변을 수정하시겠습니까?")){
			varForm.submit();
		}
	}
	
}
//답변 삭제
function fncDeleteIssueAnswer(issueSttusCode){
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovDeleteJobIssueAnwser.do'/>";
	
	varForm.issueSttusCode.value = issueSttusCode;
	
	if(confirm("답변을 비공개로 전환하시겠습니까?")){
	    varForm.submit();
	}
}
function issueCheck(){
	var isComment = "<c:out value='${isComment}'/>";
	var adminYn = "<c:out value='${adminYn}'/>";
	var issueAnswer = "<c:out value='${issueAnswer}'/>";
	var lastIssueSttusCode = "<c:out value='${lastIssueSttusCode}'/>";
	
	
	if(isComment == "Y" && issueAnswer.length != 0){
		document.getElementById('issueInsertTable').style.display = "block";
		
	}else if(isComment == "N" && adminYn == "Y"){
		document.getElementById('issueInsertTable').style.display = "block";
	}
	
	if(document.getElementById("jobIssueManage").issueSttusCode.value==lastIssueSttusCode){
		document.getElementById('nextBtn').style.visibility = "hidden";
	}

}
function fncNextIssueSttus(){
	var varForm = document.getElementById("jobIssueManage");
	varForm.action = "<c:url value='/bopr/ikm/EgovNextIssueSttus.do'/>";
	
	
	var listLength = "<c:out value='${fn:length(issueAnswerList)}'/>";
	var issueSttusIndex = "<c:out value='${issueSttusIndex}'/>";
	
	if(listLength < issueSttusIndex){
		window.alert("해당 이슈 상태에 대한 답변이 없습니다. 답변을 등록한 후 진행해주세요.");
	}
	else{
		if(confirm("이슈 상태를 다음 단계로 진행하시겠습니까?")){
		    varForm.submit();
		}	
	}
	
}

function fncTrim(str)
{
	return str.replace(/(^\s*)|(\s*$)/gi, "");
}

$(document).ready(function() {
	issueCheck();
});
</script>

<form:form commandName="jobIssueManage" method="post" >
<div class="contsBody">
	<h2>Job 이슈 관리</h2>
	<div class="location">Job지식 > Job 이슈 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectJobIssueList()" title="이슈 목록 화면으로 이동">목록</a></span>
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncJobIssueGoUpdate()" title="이슈 수정 화면으로 이동">수정</a></span>
		<c:if test="${jobIssueManage.othbcAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncJobIssueDelete()" title="해당 이슈를 비공개로 전환">비공개로 전환</a></span>
		</c:if>
		</c:if>
		<c:if test="${adminYn == 'N' && loginUser.id == jobIssueManage.frstRegisterId}">
		<span class="bbsBtn"><a href="javascript:fncJobIssueGoUpdate()" title="이슈 수정 화면으로 이동">수정</a></span>
		<c:if test="${jobIssueManage.othbcAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncJobIssueDelete()" title="해당 이슈를 비공개로 전환">비공개로 전환</a></span>
		</c:if>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="이슈유형, 이슈수준, 이슈상태, 등록자, 발생일, 등록일시, 공개여부, 이슈제목, 이슈내용, 첨부파일목록, 이슈상태답변의 순서로 보여지는 Job이슈 상세정보입니다.">
		<caption>이슈관리 상세정보</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:10%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>  
	    	<th>이슈유형</th>
		    <td>
				<c:forEach var="issueTyCode" items="${issueTyCodeList}" varStatus="status">
					<c:if test="${issueTyCode.code == jobIssueManage.issueTyCode}">
						${issueTyCode.codeNm}
					</c:if>
				</c:forEach>
		    </td>
		    <th>이슈수준</th>
			<td>
				<c:forEach var="issueLvTy" items="${issueLvList}" varStatus="status">
					<c:if test="${issueLvTy.code == jobIssueManage.issueLvCode}">
						${issueLvTy.codeNm}
					</c:if>
				</c:forEach>
			</td>
			<th>이슈상태</th>
			<td>
				<c:forEach var="issueTy" items="${issueCodeList}" varStatus="status">
					<c:if test="${issueTy.code == jobIssueManage.issueSttusCode}">
						${issueTy.codeNm}
					</c:if>
				</c:forEach>
			</td>
	  	</tr>	
		<tr>  
		    <th>등록자ID</th>
		    <td>${jobIssueManage.frstRegisterId}</td>
		    
		    <th>발생일</th>
		    <td>${jobIssueManage.issueOccrrncDe}</td>
		    
		    <th>등록일시</th>
			<td>${jobIssueManage.frstRegistPnttm}</td>
		</tr>
		<tr>  
		    <th>공개여부</th>
			<td colspan="5">
				<c:if test="${jobIssueManage.othbcAt == 'Y'}">공개</c:if>
				<c:if test="${jobIssueManage.othbcAt == 'N'}">비공개</c:if>
			</td>
		</tr>
		
		<tr>  
			<th>이슈제목</th>
			<td colspan="5">
				${jobIssueManage.issueSj}
			</td>	
		</tr>
		
		<tr>  
	    	<th>이슈내용</th>
	    	<td colspan="5">
	    		<textarea name="issueCn" id="issueCn" style="width: 80%;" rows="10" cols="100" readonly="readonly"><c:out value='${jobIssueManage.issueCn}'/></textarea>
	    	</td>
		</tr>
		
		<c:if test="${jobIssueManage.atchFileId != null}">
		<tr>  
	    	<th>첨부파일</th>
	    	<td colspan="5">
		    	<!-- 첨부파일 테이블 레이아웃 설정 Start.. -->
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfs.do" > 
					<c:param name="param_atchFileId" value="${jobIssueManage.atchFileId}" /> 
				</c:import>       
				<!-- 첨부파일 테이블 레이아웃 End. -->
				<input name="atchFileId" type="hidden" value="<c:out value='${jobIssueManage.atchFileId}'/>"/>
			</td>
		</tr>
		</c:if>
		
		</tbody>
		</table>
	</div>
</div>



<!-- 이슈 상태 코멘트 -->
<c:if test="${adminYn == 'Y'}">
<div class="Btn" id="nextBtn" >
	<span class="bbsBtn"><a href="javascript:fncNextIssueSttus()">다음 이슈 상태로 진행</a></span>
</div>
</c:if>
<div class="bbsDetail">

	<!-- 답변이 없을 경우 -->
	<c:if test="${fn:length(issueAnswerList) == 0}">
	<table>
	<colgroup>
		<col style="width:20%" >
		<col style="width:auto" >
	</colgroup>
	<tbody>
	<tr><th colspan="2">
	이 이슈에 대한 답변이 없습니다.
	</th></tr>
	</tbody>
	</table>
	<BR><BR>
	</c:if>
	
	<!-- 답변 리스트 -->
	<c:if test="${fn:length(issueAnswerList) != 0}">
	<c:forEach var="issueAnswerVO" items="${issueAnswerList}" varStatus="status">
	<table>
	<colgroup>
		<col style="width:20%" >
		<col style="width:auto" >
	</colgroup>
	<tbody>
	<tr>
		<th colspan="2">
		<c:forEach var="issueTy" items="${issueCodeList}" varStatus="status">
			<c:if test="${issueTy.code == issueAnswerVO.issueSttusCode}">
				${issueTy.codeNm}
			</c:if>
		</c:forEach>
		상태에 대한 답변 입니다.
		<c:if test="${adminYn =='Y'}">
		<span class="bbsBtn"><a href="javascript:fncUpdateIssueAnswer('view','${issueAnswerVO.issueSttusCode}')">답변수정</a></span>
		<%-- <span class="bbsBtn"><a href="javascript:fncDeleteIssueAnswer('${issueAnswerVO.issueSttusCode}')">답변삭제</a></span> --%>
		</c:if>
		</th>
	</tr>
	
	<tr>  
    	<th>등록자ID</th>
	    <td>${issueAnswerVO.answerRegisterId} 님 께서 등록하셨습니다.</td>
  	</tr>
	
	<tr>  
	    <th>답변일시</th>
	    <td>${issueAnswerVO.answerRegistPnttm}</td>
	</tr>
	
	<tr>  
	    <th>이슈에 대한 코멘트</th>
	    <td>${issueAnswerVO.answerCn}</td>
	</tr>
	</tbody>
	</table>
	<BR><BR>
	</c:forEach>
	</c:if>
	
	<!-- 등록 및 수정 화면 -->
	<table id="issueInsertTable"  style="display:none;">
	<colgroup>
		<col style="width:20%" >
		<col style="width:auto" >
	</colgroup>
	<tbody>
	
	<tr>
		<th colspan="2">
		<c:forEach var="issueTy" items="${issueCodeList}" varStatus="status">
			<c:if test="${issueTy.code == jobIssueManage.issueSttusCode}">
				${issueTy.codeNm}
			</c:if>
		</c:forEach>
		상태에 대한 답변을
		<c:if test="${not empty issueAnswer.answerCn}">
		수정중 입니다.
			<span class="bbsBtn"><a href="javascript:fncUpdateIssueAnswer('update','${issueAnswer.issueSttusCode}')">수정완료</a></span>
			<%-- <span class="bbsBtn"><a href="javascript:fncDeleteIssueAnswer('${issueAnswer.issueSttusCode}')">답변삭제</a></span> --%>
		</c:if>
		<c:if test="${empty issueAnswer.answerCn}">
		등록중 입니다.
			<span class="bbsBtn"><a href="javascript:fncInsertIssueAnswer()">답변등록</a></span>
		</c:if>
		</th>
	</tr>
	<tr>  
    	<th>등록자</th>
	    <td>
	    	<c:if test="${not empty issueAnswer.answerRegisterId}">
	    	${issueAnswer.answerRegisterId} 님 께서 등록한 답변
	    	<BR>
	    	<BR>
	    	${loginUser.id} 님 께서 수정중입니다.
	    	</c:if>	 
	    	<c:if test="${empty issueAnswer.answerRegisterId}">
	    	${loginUser.id} 님 께서 작성중입니다.
	    	</c:if>	  	
	    </td>
  	</tr>
  	
  	<c:if test="${not empty issueAnswer.answerRegisterId}">
  	<tr>  
    	<th>답변일시</th>
	    <td>
	    	${issueAnswer.answerRegistPnttm}
	    </td>
  	</tr>
	</c:if>
	<tr>  
	    <th>이슈에 대한 코멘트</th>
	    <td><textarea name="answerCn" id="answerCn" style="width: 80%;" rows="10" cols="100"><c:out value='${issueAnswer.answerCn}'/></textarea></td>
	</tr>

	</tbody>
	</table>
	
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="issueNo" value="<c:out value='${jobIssueManage.issueNo}'/>"/>
	<input type="hidden" name="issueSj"  value="<c:out value='${jobIssueManage.issueSj}'/>"/>
	
	<!--  답글 값 -->
	<input type="hidden" name="answerNo" value="<c:out value='${jobIssueManage.issueNo}'/>"/>
	<input type="hidden" name="issueSttusCode" value="<c:out value='${jobIssueManage.issueSttusCode}'/>"/>	
	<input type="hidden" name="answerRegisterId" value="<c:out value='${loginUser.id}'/>"/>
	<input type="hidden" name="answerRegisterPnttm" />
	<input type="hidden" name="isUpdate" />
</form:form>
