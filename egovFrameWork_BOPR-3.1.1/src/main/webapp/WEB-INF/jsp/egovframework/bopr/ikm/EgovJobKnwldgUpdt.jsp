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
   * @Class Name : EgovJobKnwldgUpdt.jsp
   * @Description : Job지식 수정 화면
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

<!-- 유효성 검사  -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="jobKnwldgManage" staticJavascript="false" xhtml="true" cdata="false"/>

<!-- 첨부파일  -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovFileInit.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<!-- 달력팝업  -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

<script type="text/javaScript" language="javascript">

function fncSelectJobKnwldgList() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgList.do'/>";
	varFrom.submit();
}


function fncJobKnwldgUpdate() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgUpdate.do'/>";

	if (!validateJobKnwldgManage(varFrom)) {
		return;
	} else {
		
		//지식명 특수문자 사용여부확인
    	if(fnCheck(document.jobKnwldgManage.knwldgNm.value)){
    		alert("지식명에 특수문자는 사용할 수 없습니다.");
    		document.jobKnwldgManage.knwldgNm.select();
    		return;
    	}
		
    	//폐기일 확인
    	if(document.jobKnwldgManage.dsuseEnnc.value == 'Y' && fncTrim(document.jobKnwldgManage.dsuseDe.value) ==''){
    		alert("폐기완료시 폐기일을 입력해야합니다.");
    		document.jobKnwldgManage.dsuseDe.select();
    		return;
    	}
		
		if (confirm("수정 하시겠습니까?")) {
			varFrom.submit();
		}
	}

}

function fncJobKnwldgDelete() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgDelete.do'/>";
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

function fncTrim(str)
{
	return str.replace(/(^\s*)|(\s*$)/gi, "");
}


/* 폐기유무에 따른 변경 */
function dsuseChange(){
	if(document.jobKnwldgManage.dsuseEnnc.value == 'Y'){
		document.jobKnwldgManage.dsuseDe.disabled="";
		document.getElementById("calendar").style.visibility="visible";
		document.getElementById("calendarImg").style.visibility="visible";
	}else{
		document.jobKnwldgManage.dsuseDe.disabled="disabled";
		document.getElementById("calendar").style.visibility="hidden";
		document.getElementById("calendarImg").style.visibility="hidden";
	}
}

$(document).ready(function() {
	fn_egov_initManage('jobKnwldgManage');
	dsuseChange();
});
</script>

<form:form commandName="jobKnwldgManage" name="jobKnwldgManage" method="post" enctype="multipart/form-data">

<div class="contsBody">
	<h2>Job 지식 관리</h2>
	<div class="location">Job지식 > Job 지식 관리 > <strong>수정</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectJobKnwldgList()" title="지식 목록 화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncJobKnwldgUpdate()" title="수정한 지식을 저장">수정</a></span>
		<c:if test="${jobKnwldgManage.othbcAt == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncJobKnwldgDelete()" title="해당 지식을 비공개로 전환">비공개로 전환</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="공개여부, 조회수, 평가점수, 지식유형, 폐기유무, 폐기일, 지식명, 지식내용, 첨부파일의 순서로 보여지는 Job지식 수정입니다.">
		<caption>지식관리 수정</caption>
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
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />공개여부</th>
			<td>
				<label for="othbcAt" class="disp_none">공개여부</label>
				<form:select path="othbcAt"  title="공개여부">
					<form:option value="Y" label="공개"/>
					<form:option value="N" label="비공개"/>											
				</form:select>
			</td>
			
			<th>조회수</th>
			<td colspan="3">${jobKnwldgManage.rdcnt}</td>

		</tr>
	  
		<tr>  
		    <th>평가점수</th>
		    <td colspan="5">
		    	${jobKnwldgManage.knwldgEvl}
		    </td>
		</tr>
			
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />지식유형</th>
			<td>
				<label for="knwldgTyCode" class="disp_none">지식유형</label>
			<form:select path="knwldgTyCode"  title="소속부서">
				<form:options items="${knwldgTyCodeList}" itemValue="code" itemLabel="codeNm"  title="지식유형"  />													
			</form:select>
			</td>
			
			<th>폐기유무</th>
			<td>
				<label for="dsuseEnnc" class="disp_none">폐기유무</label>
			   	<form:select path="dsuseEnnc"  title="폐기유무" onchange="javascript:dsuseChange()">
					<form:option value="N" label="사용중"/>
					<form:option value="Y" label="폐기완료"/>			
				</form:select>
			</td>
		
			<th><img id="calendarImg" style="visibility: hidden;" src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />폐기일</th>
			<td>
				<label for="dsuseDe" class="disp_none">폐기일</label>
				<input name="dsuseDe" type="text" title="수집일달력" size="10" value="${jobKnwldgManage.dsuseDe}" 
				<c:if test="${jobKnwldgManage.dsuseEnnc == 'N'}">disabled="disabled"</c:if>/>
				<a id="calendar" href="#LINK" 
					<c:if test="${jobKnwldgManage.dsuseEnnc == 'N'}">style="visibility: hidden;"</c:if>
				onClick="fn_egov_NormalCalendar(jobKnwldgManage,'', jobKnwldgManage.dsuseDe);" title="새 창열림" >
				<img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="Job지식 등록 폐기일 달력 팝업">
				</a>
				<div><form:errors path="dsuseDe"/></div>
			</td>
		 </tr>
	   
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />지식명</th>
			<td colspan="5">
				<label for="knwldgNm" class="disp_none">지식명</label>
				<input name="knwldgNm" id="knwldgNm" type="text" value="<c:out value='${jobKnwldgManage.knwldgNm}'/>" maxLength="50" size="40" /></td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />지식 내용</th>
			<td colspan="5">
				<label for="knwldgCn" class="disp_none">지식 내용</label>
				<textarea name="knwldgCn" id="knwldgCn" style="width: 80%;" rows="10" cols="100"><c:out value='${jobKnwldgManage.knwldgCn}'/></textarea></td>
		</tr>
		
		<tr>  
			<th>첨부파일</th>
			<td colspan="5">
		<!-- 첨부파일 입력 -->
			<div id="file_upload_posbl"  style="display:none;" >    
				<label for="file_" class="disp_none">첨부파일</label>
				<input name="file_" id="egovComFileUploader" title="파일첨부" type="file"  />
				<div id="egovComFileList"></div>
			</div>
			<div id="file_upload_imposbl"  style="display:none;" >더 이상 첨부파일을 입력할 수 없습니다.</div>
	
			</td>
		</tr>
		
		<!-- 첨부목록을 보여주기 위한 -->
		<tr>  
			<th class="required_text" width="10%" nowrap>첨부파일목록</th>
			<td colspan="5" nowrap>
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfsForUpdate.do">
			<c:param name="param_atchFileId" value="${jobKnwldgManage.atchFileId}" />
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
	<input type="hidden" name="returnUrl" value="<c:url value='/bopr/ikm/EgovJobKnwldgUpdateView.do'/>"/>
	
	<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="3" />  
	
	<!-- 달력 팝업 URL -->
	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
	
	<!-- Hidden 값 -->
 	<input type="hidden" name="lastUpdusrId" value="${loginUser.id}" /> 	
 	<input type="hidden" name="knwldgNo" value="<c:out value='${jobKnwldgManage.knwldgNo}'/>"/>
 	
</form:form>   
