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
  * @Class Name : EgovJobDlbrtDetail.jsp
  * @Description : 업무심의요청 수정 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.11 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.11
  *  @version 1.0 
  *  @see
  */
%>
<c:set var="registerFlag" value="${empty jobDlbrt.jobDlbrtNo ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty jobDlbrt.jobDlbrtNo ? '업무심의요청 등록' : '업무심의요청 수정'}"/>
<c:set var="updaterFlag" value="${empty jobDlbrt.jobDlbrtResultCode ? 'UPDATE' : ((jobDlbrt.jobDlbrtResultCode=='CN')? 'CONFIRM' : ((jobDlbrt.jobDlbrtResultCode=='RE')? 'RECONFIRM' : 'DISABLE'))}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>


<validator:javascript formName="jobDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>
<!-- JQuery -->
 <script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
 <!-- 첨부파일  -->
 <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javaScript" language="javascript">

/* 첨부파일을 위한 onLoad용 함수  */
function fn_egov_initManage(){

    var existFileNum = document.getElementById("jobDlbrtManage").fileListCnt.value;     
    var maxFileNum = document.getElementById("jobDlbrtManage").posblAtchFileNumber.value;

    if(existFileNum=="undefined" || existFileNum ==null){
        existFileNum = 0;
    }

    if(maxFileNum=="undefined" || maxFileNum ==null){
        maxFileNum = 0;
    }       

    var uploadableFileNum = maxFileNum - existFileNum;

    if(uploadableFileNum<0) {
        uploadableFileNum = 0;
    }
                    
    if(uploadableFileNum != 0){
        
        fn_egov_check_file2('Y');
        var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
        multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );

        
    }else{
        fn_egov_check_file2('N');
    }   
}

/* 첨부파일을 위한 onLoad용 함수에서 사용하는 함수  */
function fn_egov_check_file2(flag) {
	var registerFlag = document.getElementById("jobDlbrtManage").registerFlag.value;
	if(registerFlag=="CN"||registerFlag=="RT"){
		document.getElementById('file_upload_posbl').style.display = "none";
        document.getElementById('file_upload_imposbl').style.display = "block";
        
	}else{
		if(flag=="Y") {
	        document.getElementById('file_upload_posbl').style.display = "none";
	        document.getElementById('file_upload_imposbl').style.display = "block";
	    } else {
	        document.getElementById('file_upload_posbl').style.display = "none";
	        document.getElementById('file_upload_imposbl').style.display = "block";
	    }	
	}
} 
function fncSelectJobDlbrtList() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtList.do'/>";
	varFrom.submit();       
}

function fncJobDlbrtUpdate() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtUpdateView.do'/>";

    varFrom.submit();

}

function fncJobDlbrtDelete() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fncJobDlbrtReconfirm() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtReconfirm.do'/>";
	if(confirm("재심의요청 하시겠습니까?")){
	    varFrom.submit();
	}
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
$(document).ready(function() { 
	fn_egov_initManage();
});
</script>

<form:form name="jobDlbrtManage" commandName="jobDlbrtManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>업무심의요청</h2>
	<div class="location">배치심의 > 업무심의요청 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="업무심의요청 목록" href="javascript:fncSelectJobDlbrtList()">목록</a></span>
		<c:if test="${updaterFlag == 'UPDATE'}">
		<span class="bbsBtn"><a title="업무심의요청 수정" href="javascript:fncJobDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="업무심의요청 삭제" href="javascript:fncJobDlbrtDelete()">삭제</a></span>
		</c:if>
		<c:if test="${updaterFlag == 'DISABLE'}">
		<span class="bbsBtn"><a title="업무심의요청 재심의요청" href="javascript:fncJobDlbrtReconfirm()">재심의요청</a></span>
		</c:if>
		<c:if test="${updaterFlag == 'RECONFIRM'}">
		<span class="bbsBtn"><a title="업무심의요청 수정" href="javascript:fncJobDlbrtUpdate()">수정</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="업무심의번호, 업무구분, 업무심의제목, 업무심의내용, 첨부파일 , 최종심의결과, 최종심의사유, 등록수정 정보 등의 업무심의요청관리 상세정보입니다.">
		<caption>업무심의요청관리 상세정보</caption>
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
			<label for='jobSeCode' class='disp_none'>업무구분</label>
			<select name="jobSeCode" id="jobSeCode" title="업무구분" disabled="disabled">
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
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의제목" type="text" value="<c:out value='${jobDlbrt.jobDlbrtNm}'/>" readonly="readonly" maxLength="50" size="50" /></td>
		</tr>
		<tr>
			<th>업무심의내용</th>
			<td>
			<label for='jobDlbrtCn' class='disp_none'>업무심의내용</label>
			<textarea name="jobDlbrtCn" title="업무심의내용" readonly="readonly" id="jobDlbrtCn" rows="5" cols="80"><c:out value="${jobDlbrt.jobDlbrtCn}"/></textarea></td>
		</tr>
		<c:if test="${jobDlbrt.atchFileId == ''}">
			<input type="hidden" name="fileListCnt" value="0" />
  		</c:if>
  		
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
			    <div id="file_upload_posbl"  style="display:none;" >
			           <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
					    <tr>
					        <td><input name="file_" id="egovComFileUploader" type="file" title="첨부파일명 입력"/></td>
					    </tr>
					    <tr>
					        <td>
					        	<div id="egovComFileList"></div>
					        </td>
					    </tr>
			  	        </table>
				</div>
				<div id="file_upload_imposbl"  style="display:none;" >
			           <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
					    <tr>
					        <td>&nbsp;</td>
					    </tr>
			  	        </table>
				</div>
		</tr>
		 
		<c:if test="${not empty jobDlbrt.atchFileId}">
		  <tr>  
		    <th class="required_text" width="20%" nowrap>첨부파일 목록</th>
		    <td nowrap>
	    		<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfs.do">
					<c:param name="param_atchFileId" value="${jobDlbrt.atchFileId}" />
				</c:import>
		    </td>
		  </tr>
		</c:if>
		<tr>
			<th>최종심의결과</th>
			<td>
			<label for='jobDlbrtResultCode' class='disp_none'>최종심의결과</label>
			<input name="jobDlbrtResultCode" id="jobDlbrtResultCode" type="text" title="최종심의결과"  
			<c:if test="${empty jobDlbrt.jobDlbrtResultCode }">
			value="미승인"
			</c:if>
			<c:if test="${not empty jobDlbrt.jobDlbrtResultCode }">
			value="<c:out value="${jobDlbrt.jobDlbrtResultCodeNm}"/>"
			</c:if>
			maxLength="20" size="20" readonly /></td>
		</tr>
		<tr>
			<th>최종심의사유</th>
			<td>
			<label for='jobFnlDlbrtCn' class='disp_none'>최종심의사유</label>
			<input name="jobFnlDlbrtCn" id="jobFnlDlbrtCn" title="최종심의사유" type="text" value="<c:out value="${jobDlbrt.jobFnlDlbrtCn}"/>" maxLength="80" size="80" readonly /></td>
		</tr>
		<tr>
			<th>비고</th>
			<td>
			<label for='remark' class='disp_none'>비고</label>
			<textarea name="remark" id="remark" title="비고" readonly="readonly" rows="5" cols="80"><c:out value="${jobDlbrt.remark}"/></textarea></td>
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
</div>
<!-- 첨부파일용 -->
	<c:if test="${jobDlbrt.atchFileId eq null || jobDlbrt.atchFileId eq ''}">
		<input type="hidden" name="fileListCnt" value="0" />
		<input name="atchFileAt" type="hidden" value="N">
	</c:if>
	<c:if test="${jobDlbrt.atchFileId eq null || jobDlbrt.atchFileId eq ''}"> 						
		<input name="atchFileAt" type="hidden" value="Y"> 
	</c:if>
	
<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="10" />
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${jobDlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${jobDlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${jobDlbrtVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/bopr/bam/EgovJobDlbrtUpdate.do'/>"/>
<input type="hidden" name="registerFlag" value="<c:out value='${jobDlbrt.jobDlbrtResultCode}'/>"/>

</form:form>