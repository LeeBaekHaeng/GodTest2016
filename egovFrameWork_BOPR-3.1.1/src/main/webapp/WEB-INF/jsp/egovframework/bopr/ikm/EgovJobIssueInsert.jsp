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
   * @Class Name : EgovJobIssueInsert.jsp
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

<!-- 유효성검사 -->
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

function fncJobIssueInsert() {

    var varFrom = document.getElementById("jobIssueManage");
    varFrom.action = "<c:url value='/bopr/ikm/EgovJobIssueInsert.do'/>";
 
	if(!validateJobIssueManage(varFrom)){         
           return;
    }else{
    	
    	/*이슈제목 특수문자 사용여부확인*/
    	if(fnCheck(document.jobIssueManage.issueSj.value)){
    		alert("이슈제목에 특수문자는 사용할 수 없습니다.");
    		document.jobIssueManage.issueSj.select();
    		return;
    	}
    	
       	if(confirm("저장 하시겠습니까?")){
           varFrom.submit();
    	}
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

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>



<form:form commandName="jobIssueManage" method="post" name="jobIssueManage" enctype="multipart/form-data">
<div class="contsBody">
	<h2>Job 이슈 관리</h2>
	<div class="location">Job지식 > Job 이슈 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectJobIssueList()" title="지식 목록 화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncJobIssueInsert()" title="작성한 지식에 대한 등록을 수행">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="이슈유형, 이슈수준, 이슈상태, 등록자, 이슈제목, 이슈내용, 첨부파일의 순서로 입력하는 Job이슈관리 등록입니다.">
		<caption>이슈 등록</caption>
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
			<label for="issueTyCode" class="disp_none">이슈222유형</label>
			<form:select path="issueTyCode"  title="이슈유형">
				<form:option value="" label="이슈유형선택" />
				<form:options items="${issueTyCodeList}" itemValue="code" itemLabel="codeNm"  title="이슈유형"  />													
			</form:select> 
			</td>
			
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈수준</th>
			<td>
			<label for="issueLvCode" class="disp_none">이슈수준</label>
				<form:select path="issueLvCode"  title="이슈수준">
					<form:option value="" label="이슈수준선택" />
					<form:options items="${issueLvList}" itemValue="code" itemLabel="codeNm"  title="이슈수준"  />													
				</form:select> 
			</td>
			
			<th>이슈상태</th>
			<td>이슈신규등록중</td>
		</tr>
		
		<tr>  
			<th>등록자</th>
		    <td>${loginUser.name}(${loginUser.id})</td>
		    
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />발생일</th>
			<td colspan="3">
				<label for="issueOccrrncDe" class="disp_none">이슈발생일</label>
				<input name=issueOccrrncDe type="text" title="이슈발생일" size="10" value="${jobIssueManage.issueOccrrncDe}"  />
				<a href="#LINK" onClick="fn_egov_NormalCalendar(jobIssueManage,'', jobIssueManage.issueOccrrncDe);" title="새 창열림">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="Job이슈 등록 발생일 달력팝업">
	 			</a>
				<div><form:errors path="issueOccrrncDe"/></div>
	  		</td>
		</tr>
	
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈제목</th>
			<td colspan="5">
				<label for="issueSj" class="disp_none">이슈제목</label>
				<input name="issueSj" id="issueSj" type="text" value="<c:out value='${jobIssueManage.issueSj}'/>" maxLength="50" size="40"/>&nbsp;</td>
		</tr>
	
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />이슈내용</th>
			<td colspan="5">
			<label for="issueCn" class="disp_none">이슈내용</label>
			<textarea name="issueCn" id="issueCn" style="width: 80%;" rows="10" cols="100"><c:out value='${jobIssueManage.issueCn}'/></textarea>&nbsp;</td>
		</tr>
	
		<tr>  
			<th>첨부파222일</th>
			<td colspan="5">
	  	
				<!-- 첨부목록을 보여주기 위한 -->
				<c:if test="${jobIssueManage.atchFileId ne null && jobIssueManage.atchFileId ne ''}">
				<tr>
					<th>첨부파일 목11록</th>
					<td>
					<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfs.do">
						<c:param name="param_atchFileId" value="${jobIssueManage.atchFileId}" />
					</c:import>
					</td>
				</tr>
				</c:if>
				<div id="file_upload_posbl"  style="display:none;" >
					<label for="egovComFileUploader" class="disp_none">파일첨부</label>
			  		<input name="file_1" id="egovComFileUploader" title="파일첨부" type="file"  />
			  		<div id="egovComFileList"></div>
			  	</div>
				<div id="file_upload_imposbl"  style="display:none;" ></div> 
			</td>					  
	 	</tr>
		
		<scritp>
		<!--
/*********************************************************
* 파일첨부.
* ******************************************************** */
var maxFileNum = 3;//document.form.posblAtchFileNumber.value;
//if(maxFileNum==null || maxFileNum==""){
//maxFileNum = 3;
//}
var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
//-->
		</scritp>
		</tbody>
		</table>
	</div>
</div>

	<!-- 첨부파일용 -->
	<c:if test="${jobKnwldgManage.atchFileId eq null || jobKnwldgManage.atchFileId eq ''}">
		<input type="hidden" name="fileListCnt" value="0" />
		<input name="atchFileAt" type="hidden" value="N">
	</c:if> 						
	<c:if test="${jobKnwldgManage.atchFileId ne null && jobKnwldgManage.atchFileId ne ''}">
		<input name="atchFileAt" type="hidden" value="Y"> 
	</c:if>
	
	<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="3" />  
	
	<!-- 달력 팝업 URL -->
 	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
 	
 	<!-- 등록자 -->
 	<input type="hidden" name="frstRegisterId" value="${loginUser.id}" />
 	<!-- 최근 수정자 -->
 	<input type="hidden" name="lastUpdusrId" value="${loginUser.id}" />
</form:form>  
