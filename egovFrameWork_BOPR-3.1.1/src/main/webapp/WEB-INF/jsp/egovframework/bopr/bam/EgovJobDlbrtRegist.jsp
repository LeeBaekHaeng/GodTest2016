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
  * @Class Name : EgovJobDlbrtInsert.jsp
  * @Description : 업무심의요청 입력 화면
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
<c:set var="registerFlag" value="${empty jobDlbrtVO.jobDlbrtNo ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty jobDlbrtVO.jobDlbrtNo ? '업무심의요청 등록' : '업무심의요청 수정'}"/>

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
        
        fn_egov_check_file('Y');
        
        var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
        multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
        
    }else{
        fn_egov_check_file('N');
    }   
}

/* 첨부파일을 위한 onLoad용 함수에서 사용하는 함수  */
function fn_egov_check_file(flag) {
    if(flag=="Y") {
        document.getElementById('file_upload_posbl').style.display = "block";
        document.getElementById('file_upload_imposbl').style.display = "none";          
    } else {
        document.getElementById('file_upload_posbl').style.display = "none";
        document.getElementById('file_upload_imposbl').style.display = "block";
    }
}

function fncSelectJobDlbrtList() {
	var varFrom = document.getElementById("jobDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtList.do'/>";
	varFrom.submit();       
}

function fncJobDlbrtInsert() {
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
    varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtInsert.do'/>";

    
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
	varFrom.action = "<c:url value='/bopr/bam/EgovJobDlbrtDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
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

<form:form commandName="jobDlbrtManage" method="post" name="jobDlbrtManage" enctype="multipart/form-data">
<div class="contsBody">
	<h2>업무심의요청</h2>
	<div class="location">배치심의 > 업무심의요청 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="업무심의요청 목록" href="javascript:fncSelectJobDlbrtList()">목록</a></span>
		<span class="bbsBtn"><a title="업무심의요청 등록" href="javascript:fncJobDlbrtInsert()">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="업무구분, 업무심의제목, 업무심의내용, 첨부파일, 비고 등의 업무심의요청 등록입니다.">
		<caption>업무심의요청 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<!-- 
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td><input name="jobDlbrtNo" id="jobDlbrtNo" type="text" readonly value="<c:out value='${jobDlbrt.jobDlbrtNo}'/>" maxLength = "8" size="20" /></td>
		</tr>
		 -->
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무구분</th>
			<td>
			<label for="jobSeCode" class="disp_none">업무 구분 선택</label>
			<select name="jobSeCode" id="jobSeCode" title="업무 구분 선택">
		     <c:forEach var="cmmCode" items="${cmmCode}" varStatus="status">
		     	<option value='<c:out value="${cmmCode.code}"/>' ><c:out value="${cmmCode.codeNm}"/>
		     </c:forEach>
		    </select>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의제목</th>
			<td>
			<label for="jobDlbrtNm" class="disp_none">업무심의제목</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의제목" type="text" value="<c:out value='${jobDlbrtManage.jobDlbrtNm}'/>"  maxLength="50" size="50" /></td>
		</tr>
		<tr>
			<th>업무심의내용</th>
			<td>
			<label for="jobDlbrtCn" class="disp_none">업무심의내용</label>
			<textarea name="jobDlbrtCn" id="jobDlbrtCn" title="업무심의내용" rows="5" cols="80"><c:out value="${jobDlbrtManage.jobDlbrtCn}"/></textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
	    	<c:if test="${jobDlbrtManage.atchFileId ne null && jobDlbrtManage.atchFileId ne ''}">
			<tr>
			<th height="23" class="required_text">첨부파일 목록</th>
			<td>
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfs.do">
					<c:param name="param_atchFileId" value="${jobDlbrtManage.atchFileId}" />
				</c:import>
			</td>
			</tr>
			</c:if>
			<div id="file_upload_posbl"  style="display:none;" >
	    		<input name="file_1" id="egovComFileUploader" title="파일첨부" type="file"  />
	    		<div id="egovComFileList"></div>
	    	</div>
			<div id="file_upload_imposbl"  style="display:none;" >
			</div>
			</td>
		</tr>
		<tr>
			<th rowspan="2">비고</th>
			<td>
			<label for="remark" class="disp_none">비고</label>
			<textarea name="remark" id="remark" title="비고" rows="5" cols="80"><c:out value="${jobDlbrtManage.remark}"/></textarea></td>
		</tr>
		<tr>
		    <td style="color:#828282">
		     	 배치등록 프로세스 : 업무심의요청 등록 -> 업무심의 승인 -> 승인된 업무에 대한 배치심의요청 등록 -> 배치심의 승인 -> 배포
		    </td>
		</tr>
		</tbody>
		</table>
	</div>
</div>

<!-- 첨부파일용 -->
<c:if test="${jobDlbrtManage.atchFileId eq null || jobDlbrtManage.atchFileId eq ''}">
	<input type="hidden" name="fileListCnt" value="0" />
	<input name="atchFileAt" type="hidden" value="N">
</c:if> 			
			
<c:if test="${jobDlbrtManage.atchFileId ne null && jobDlbrtManage.atchFileId ne ''}">
	<input name="atchFileAt" type="hidden" value="Y"> 
</c:if>

<!-- 첨부파일 갯수 한정 -->					  
<input type="hidden" name="posblAtchFileNumber" value="3" />

</form:form>
