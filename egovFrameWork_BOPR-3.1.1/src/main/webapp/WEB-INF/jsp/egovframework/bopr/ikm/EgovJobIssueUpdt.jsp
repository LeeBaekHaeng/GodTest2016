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
   * @Class Name : EgovJobIssueUpdt.jsp
   * @Description : JobIssue 등록 화면
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

<!-- 유효성검사  -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="jobIssueManage" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 첨부파일  -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovFileInit.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<!-- 달력팝업  -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

<script type="text/javaScript" language="javascript">

function fncSelectJobIssueList() {
	var varFrom = document.getElementById("jobIssueManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobIssueList.do'/>";
	varFrom.submit();       
}


function fncJobIssueUpdate() {
	var varFrom = document.getElementById("jobIssueManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobIssueUpdate.do'/>";

	if (!validateJobIssueManage(varFrom)) {
		return;
	} else {
		
		//이슈제목 특수문자 사용여부확인
    	if(fnCheck(document.jobIssueManage.issueSj.value)){
    		alert("이슈제목에 특수문자는 사용할 수 없습니다.");
    		document.jobIssueManage.issueSj.select();
    		return;
    	}
		
		if (confirm("수정 하시겠습니까?")) {
			varFrom.submit();
		}
	}

}

function fncJobIssueDelete() {
	var varFrom = document.getElementById("jobIssueManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobIssueDelete.do'/>";
	if (confirm("비공개로 전환하시겠습니까?")) {
		varFrom.submit();
	}
}

/* 특수문자체크 */
function fnCheck(str){
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    if(special_pattern.test(str)){
    	/* 걸리면 true반환 */
        return true;
    } else {
   		return false;
	}
}
$(document).ready(function() {
	fn_egov_initManage('jobIssueManage');
});
</script>

<form:form commandName="jobIssueManage" name="jobIssueManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>Job 이슈 관리</h2>
	<div class="location">Job지식 > Job 이슈 관리 > <strong>수정</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectJobIssueList()" title="이슈 목록 화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncJobIssueUpdate()" title="해당 이슈에 대한 수정사항을 저장">수정</a></span>
		<c:if test="${jobIssueManage.othbcAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncJobIssueDelete()" title="해당 이슈를 비공개로 전환">비공개로 전환</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="이슈유형, 이슈수준, 이슈상태, 등록자, 발생일, 등록일시, 공개여부, 이슈제목, 이슈내용, 첨부파일의 순서로 보여지는 Job이슈 수정입니다.">
		<caption>이슈관리 수정</caption>
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
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈유형</th>
			<td>
				<label for="issueTyCode" class="disp_none">이슈유형</label>
				<form:select path="issueTyCode"  title="이슈유형">
				<form:options items="${issueTyCodeList}" itemValue="code" itemLabel="codeNm"  title="이슈유형"  />													
				</form:select> 
			</td>
		
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈수준</th>
			<td>
				<label for="issueLvCode" class="disp_none">이슈수준</label>
				<form:select path="issueLvCode"  title="이슈수준">
				<form:options items="${issueLvList}" itemValue="code" itemLabel="codeNm"  title="이슈수준"  />													
				</form:select> 
			</td>
			
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈상태</th>
			<td>
			<c:choose>
				<c:when test="${adminYn == 'Y'}">
					<label for="issueSttusCode" class="disp_none">이슈상태</label>
					<form:select path="issueSttusCode"  title="이슈상태">
					<form:options items="${issueCodeList}" itemValue="code" itemLabel="codeNm"  title="이슈상태"  />													
					</form:select>
				</c:when>
	
				<c:otherwise>
					<c:forEach var="issueTy" items="${issueCodeList}" varStatus="status">
					<c:if test="${issueTy.code == jobIssueManage.issueSttusCode}">
						${issueTy.codeNm}
						<input type="hidden" name="issueSttusCode" value="${jobIssueManage.issueSttusCode}"/>
					</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	  		</td>
		</tr>
	
		<tr>  
			<th>등록자</th>
			<td>${loginUser.name}(${loginUser.id})</td>
	 
			<th>발생일</th>
			<td>
				<label for="issueOccrrncDe" class="disp_none">이슈발생일</label>
				<input name="issueOccrrncDe" type="text" title="이슈발생일" size="10" value="${jobIssueManage.issueOccrrncDe}"  readonly />
				<a href="#LINK" onClick="fn_egov_NormalCalendar(jobIssueManage,'', jobIssueManage.issueOccrrncDe);" title="새 창열림">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="Job이슈 수정 발생일 달력팝업">
		 		</a>
				<div><form:errors path="issueOccrrncDe"/></div>
			</td>
			
			<th>등록일시</th>
			<td>${jobIssueManage.frstRegistPnttm}</td>
		</tr>
		<tr>  
			<th>공개여부</th>
			<td colspan="5">
				<label for="othbcAt" class="disp_none">공개여부</label>
				<form:select path="othbcAt"  title="공개여부">
					<form:option value="Y" label="공개"/>
					<form:option value="N" label="비공개"/>											
				</form:select>
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈제목</th>
			<td colspan="5">
				<label for="issueSj" class="disp_none">이슈제목</label>
				<input name="issueSj" id="issueSj" type="text" value="<c:out value='${jobIssueManage.issueSj}'/>" maxLength="50" size="40" /></td>
		</tr>
	
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈내용</th>
			<td colspan="5">
				<label for="issueCn" class="disp_none">이슈내용</label>
				<textarea name="issueCn" id="issueCn" style="width: 80%;" rows="10" cols="100"><c:out value='${jobIssueManage.issueCn}'/></textarea></td>
		</tr>
	
		<tr>  
			<th>첨부파일</th>
			<td colspan="5">
	  		<!-- 첨부파일 입력 -->
			<div id="file_upload_posbl"  style="display:none;" >   
				<label for="file_1" class="disp_none">첨부파일</label> 
				<input name="file_1" id="egovComFileUploader" title="파일첨부" type="file"  />
				<div id="egovComFileList"></div>
			</div>
			<div id="file_upload_imposbl"  style="display:none;" >더 이상 첨부파일을 입력할 수 없습니다.</div>
			</td>
		</tr>
		<!-- 첨부목록을 보여주기 위한 -->
		<tr>  
			<th>첨부파일목록</th>
	  		<td colspan="5">
	  		<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfsForUpdate.do">
			<c:param name="param_atchFileId" value="${jobIssueManage.atchFileId}" />
			</c:import>
	  		</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>


	<!-- 첨부파일용 -->
	<c:if test="${jobKnwldgManage.atchFileId eq null || jobKnwldgManage.atchFileId eq ''}">
		<input name="atchFileAt" type="hidden" value="N">
	</c:if> 						
	<c:if test="${jobKnwldgManage.atchFileId ne null && jobKnwldgManage.atchFileId ne ''}">
		<input name="atchFileAt" type="hidden" value="Y"> 
	</c:if>
	
	<input type="hidden" name="returnUrl" value="<c:url value='/bopr/ikm/EgovJobIssueUpdateView.do'/>"/>
	<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="3" />  
	
	<!-- 달력 팝업 URL -->
 	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
 	
 	<!-- 최근 수정자 -->
 	<input type="hidden" name="lastUpdusrId" value="${loginUser.id}" />
 	
 	<!-- Hidden 값 -->
 	<input type="hidden" name="issueNo" value="<c:out value='${jobIssueManage.issueNo}'/>"/>
</form:form>  
