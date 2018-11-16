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
  * @Class Name : EgovBatchDlbrtDetail.jsp
  * @Description : 배치심의요청 수정 화면
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

<c:set var="registerFlag" value="${empty batchDlbrt.batchDlbrtResultCode ? 'UPDATE' : ((batchDlbrt.batchDlbrtResultCode=='RT')? 'DISABLE' : ((reconfirm=='RE') ? 'RECONFIRM' : 'WDTB'))}"/>
<c:set var="registerFlagName" value="${batchDlbrt.batchDlbrtResultCode=='CN' ? '배치심의요청 내용(승인상태)' : '배치심의요청 수정'}"/>
<c:set var="updaterFlag" value="${empty batchDlbrt.batchDlbrtResultCode ? 'UPDATE' : ((batchDlbrt.batchDlbrtResultCode=='RE' || reconfirm=='Y') ? 'RECONFIRM' : 'DISABLE')}"/>
<c:set var="processSeFlag" value="<c:out value='${batchDlbrt.processSeCode}'/>"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<validator:javascript formName="batchDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>
<!-- JQuery -->
 <script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
 <!-- 첨부파일  -->
 <script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovBatchMultiFile.js'/>" ></script>
<script type="text/javaScript" language="javascript">

/* 첨부파일을 위한 onLoad용 함수  */
function fn_egov_initManage(){
	var alertMessage = document.getElementById("batchDlbrtManage").message.value;
	if(alertMessage!=null && alertMessage!=''){
		alert(alertMessage);
	}
	var existFileNum = 0;

//	if(typeof(document.getElementById("batchDlbrtManage").fileListCnt.value)=='undefined'){
		
		existFileNum = document.getElementById("batchDlbrtManage").atchCnt.value;
	//}else{
	  //  existFileNum = document.getElementById("batchDlbrtManage").fileListCnt.value;
	//}    
    var maxFileNum = document.getElementById("batchDlbrtManage").posblAtchFileNumber.value;
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
    	fn_egov_check_files('Y');
        var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
        multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
    }else{
    	fn_egov_check_files('N');
    }   	
}

/* 첨부파일을 위한 onLoad용 함수에서 사용하는 함수  */
function fn_egov_check_files(flag) {
	var registerFlag = document.getElementById("batchDlbrtManage").registerFlag.value;
	if(registerFlag=="CN"){
		if(document.getElementById('file_upload_posbl')!=null && document.getElementById('file_upload_imposbl')!=null){
			document.getElementById('file_upload_posbl').style.display = "none";
	        document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}else{
		if(flag=="Y") {
			if(document.getElementById('file_upload_posbl')!=null && document.getElementById('file_upload_imposbl')!=null){
		        document.getElementById('file_upload_posbl').style.display = "block";
		        document.getElementById('file_upload_imposbl').style.display = "none";
			}
	    } else {
	    	if(document.getElementById('file_upload_posbl')!=null && document.getElementById('file_upload_imposbl')!=null){
		        document.getElementById('file_upload_posbl').style.display = "none";
		        document.getElementById('file_upload_imposbl').style.display = "block";
	    	}
	    }	
	}
} 

function fncSelectBatchDlbrtList() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtList.do'/>";
	varFrom.submit();       
}

function fncBatchDlbrtUpdate() {
	var varFrom = document.getElementById("batchDlbrtManage");

	if(!validateBatchDlbrt(varFrom)){
        return;
    }else{
    	var checkCn = CheckStrLength();
    	if(!checkCn){
    		alert("배치설명은 500글자를 넘을수 없습니다.");
    		return;
    	}
    	
        varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtUpdate.do'/>";

        var validateValue = document.getElementById("batchId").value;
        var validateFlag = document.getElementById("processSeCode").value;
        if(validateFlag=="UPT" || validateFlag=="DEL"){
        	if(validateValue==null||validateValue==""){
        		alert("수정/삭제 시에는 배치ID가 필수입니다.");
        		return;
        	}
        }
        
        if(validateFlag!="DEL"){
        	var checkPath = document.batchDlbrtManage.atchSeCode;
        	var pathCount = 0;
        	for(var i = 0;i < checkPath.length;i++){
        		if(checkPath[i].value=="S")
        			pathCount++;
        	}
        	if(pathCount!=1){
        		alert("배치설정파일은 반드시 한 개 등록해야 합니다.");
        		return;
        	}
        	
    	    var atchCnt = document.getElementById("atchCnt").value;
    	    if(atchCnt < 1){
    	    	alert("배치 심의에는 첨부파일이 필수입니다.");
    	    	return;
    	    }
        }
    	if(confirm("저장 하시겠습니까?")){
        	varFrom.submit();
    	}else{
    		return;
    	}
    }
}

function fncBatchDlbrtDelete() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fnJobDlbrtClick() {
	document.batchDlbrtManage.jobDlbrtNm.value = "";
	var url = "<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>";
	window.open(url, 'JobDlbrtPopup', 'toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=320');
}

function fncBatchWdtb() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/sim/selectBatchWdtb.do'/>";
	varFrom.submit();
}

//내용 입력글자 제한하기
function CheckStrLength() {
  var f = document.batchDlbrtManage.batchDc.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
$(document).ready(function() { 
	fn_egov_initManage();
});
var fileAtchNo = 0;		// 파일첨부 구분 시퀀스

function fncAddFile(objFile)
{
    var strFile = objFile.value;
    
    if (strFile != null && strFile != "")
    {
        var objTable = document.getElementById("atchFileTable2");
        var rowNum = objTable.rows.length;
        var newRow = objTable.insertRow(rowNum);
        var newCell = newRow.insertCell(0);
        newCell.innerHTML = "<label for='atchSeCode' class='disp_none'>배치첨부파일유형 선택</label>" 
        	              + "<select name='atchSeCode' id='atchSeCode' title='배치첨부파일유형'><option value='B' selected>배치파일</option><option value='P'>실행파라미터</option>	</select>"
                          + "<input type='hidden' name='atchProcessSeCode' id='atchProcessSeCode' value='INS' >"
        	              + "<strong>상세경로&nbsp;:&nbsp;</strong><input type='text' title='배치첨부파일 상세경로 입력' id='batchPath' name='batchPath' size='50' style='ime-mode:disabled;' />"
                          + "<br>"
                          + strFile
                          + "&nbsp;"
                          + "<span class='bbsBtn' title='파일 삭제' onclick='javascript:fncFileDelete(this, " + fileAtchNo + ");'>Delete</span>";
    }
    
    objFile.style.display = "none";
    objFile.id = "atchFile" + fileAtchNo;
    objFile.name = "atchFile" + fileAtchNo;
    fileAtchNo++;
    
    var objFileRow = document.getElementById("fileRow");
    var newCell = objFileRow.insertCell(0);
    newCell.innerHTML = "<input type='file' id='objFile' name='atchFile' onChange='fncAddFile(this)' />";
}
function fncFileDelete(element, seq)
{
    var rowIndex = element.parentNode.parentNode.rowIndex;
    document.getElementById("atchFileTable2").deleteRow(rowIndex);
    document.getElementById("atchFile" + seq).disabled = "disabled";
}
</script>

<form:form name="batchDlbrtManage" commandName="batchDlbrtManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>배치심의요청</h2>
	<div class="location">배치심의 > 배치심의요청 > <strong>수정</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="배치심의요청 목록" href="javascript:fncSelectBatchDlbrtList()">목록</a></span>
		<c:if test="${updaterFlag == 'UPDATE'}">
		<span class="bbsBtn"><a title="배치심의요청 수정 저장" href="javascript:fncBatchDlbrtUpdate()">저장</a></span>
		<span class="bbsBtn"><a title="배치심의요청 삭제" href="javascript:fncBatchDlbrtDelete()">삭제</a></span>
		</c:if>
		<c:if test="${updaterFlag == 'RECONFIRM'}">
		<span class="bbsBtn"><a title="배치심의요청 수정 저장" href="javascript:fncBatchDlbrtUpdate()">저장</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="배치심의번호, 업무심의번호, 업무심의명, 배치ID, 배치제목, 배치설명, 처리유형, 심의결과, 심의사유, 등록수정 정보, 첨부파일 등의 배치심의요청관리 상세정보입니다.">
		<caption>배치심의요청관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<!-- 기존 테이블 내용 입력 START -->
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치심의번호</th>
			<td>
			<label for='batchDlbrtNo' class='disp_none'>배치심의번호</label>
			<input name="batchDlbrtNo" id="batchDlbrtNo" title="배치심의번호" type="text" readonly value="<c:out value='${batchDlbrt.batchDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for='jobDlbrtNo' class='disp_none'>업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" value="<c:out value='${batchDlbrt.jobDlbrtNo}'/>"  maxLength="13" size="20" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>업무심의명</th>
			<td>
			<label for='jobDlbrtNm' class='disp_none'>업무심의명</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의명" type="text" readonly value="<c:out value='${batchDlbrt.jobDlbrtNm}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치ID</th>
			<td>
			<label for='batchId' class='disp_none'>배치ID</label>
			<input name="batchId" id="batchId" title="배치ID" type="text" readonly value="<c:out value='${batchDlbrt.batchId}'/>"  maxLength="13" size="50" /></td>
		</tr>
		<tr>
			<th>배치제목</th>
			<td>
			<label for='batchNm' class='disp_none'>배치제목</label>
			<input name="batchNm" id="batchNm" title="배치제목" type="text" value="<c:out value="${batchDlbrt.batchNm}"/>" readonly maxLength="100" size="80" /></td>
		</tr>
		<tr>
			<th>배치설명</th>
			<td>
			<label for='batchDc' class='disp_none'>배치설명</label>
			<textarea name="batchDc" id="batchDc" title="배치설명" <c:if test="${registerFlag != 'UPDATE' && updaterFlag != 'RECONFIRM'}">readonly="readonly"</c:if> rows="5" cols="80"><c:out value="${batchDlbrt.batchDc}"/></textarea></td>
		</tr>
		<tr>
			<th>처리유형</th>
			<td>
			<label for='processSeCodeNm' class='disp_none'>처리유형</label>
			<input name="processSeCodeNm" id="processSeCodeNm" title="처리유형" type="text" value="<c:if test="${batchDlbrt.processSeCode == 'INS'}">신규등록</c:if><c:if test="${batchDlbrt.processSeCode == 'UPT'}">수정</c:if><c:if test="${batchDlbrt.processSeCode == 'DEL'}">삭제</c:if>" maxLength="100" size="20" readonly /></td>
		</tr>
		<tr>
			<th>심의결과</th>
			<td>
			<label for='batchDlbrtResultCodeNm' class='disp_none'>심의결과</label>
			<input name="batchDlbrtResultCodeNm" id="batchDlbrtResultCodeNm" type="text" title="심의결과" 
			<c:if test="${empty batchDlbrt.batchDlbrtResultCode }">
			value="미승인"
			</c:if>
			<c:if test="${not empty batchDlbrt.batchDlbrtResultCode }">
			value="<c:out value="${batchDlbrt.batchDlbrtResultCodeNm}"/>"
			</c:if> 
			maxLength="20" size="20" readonly /></td>
		</tr>
		<tr>
			<th>심의사유</th>
			<td>
			<label for='batchDlbrtCn' class='disp_none'>심의사유</label>
			<input name="batchDlbrtCn" id="batchDlbrtCn" title="심의사유" type="text" value="<c:out value="${batchDlbrt.batchDlbrtCn}"/>" maxLength="100"  size="80" readonly /></td>
		</tr>
		<tr>
			<th>최초등록자ID</th>
			<td>
			<label for='frstRegisterId' class='disp_none'>최초등록자ID</label>
			<input name="frstRegisterId" id="frstRegisterId" title="최초등록자ID" type="text" value="<c:out value="${batchDlbrt.frstRegisterId}"/>" maxLength="20"size="20" readonly/></td>
		</tr>
		<tr>
			<th>최종수정자ID</th>
			<td>
			<label for='lastUpdusrId' class='disp_none'>최종수정자ID</label>
			<input name="lastUpdusrId" id="lastUpdusrId" title="최종수정자ID" type="text" value="<c:out value="${batchDlbrt.lastUpdusrId}"/>"maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>
			<label for='frstRegistPnttm' class='disp_none'>등록일시</label>
			<input name="frstRegistPnttm" id="frstRegistPnttm" title="등록일시" type="text" value="<c:out value="${batchDlbrt.frstRegistPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>수정일시</th>
			<td>
			<label for='lastUpdtPnttm' class='disp_none'>수정일시</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="수정일시" type="text" value="<c:out value="${batchDlbrt.lastUpdtPnttm}"/>"  maxLength="20"  size="20" readonly/></td>
		</tr>
		
		</tbody>
		</table>
		<table id="attachInfo" summary="배치심의요청관리 첨부파일입니다.">
		<caption>배치심의요청관리 첨부파일 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<c:forEach var="batchDlbrtAtch" items="${batchDlbrtAtch}" varStatus="status">
		<tr>
			<c:if test="${batchDlbrtAtch.atchSeCode=='S'}">
			<th>배치설정파일</th>
			</c:if>
			<c:if test="${batchDlbrtAtch.atchSeCode!='S'}">
			<th>배치첨부파일</th>
			</c:if>
			<td>
		        <c:import charEncoding="utf-8" url="/com/cmm/fms/selectMultiFileInfsForUpdate.do">
					<c:param name="param_atchFileId" value="${batchDlbrtAtch.atchFileId}" />
					<c:param name="param_atchSeCode" value="${batchDlbrtAtch.atchSeCode}" />
					<c:param name="param_batchPath" value="${batchDlbrtAtch.batchPath}" />
					<c:param name="param_atchProcessSeCode" value="${batchDlbrtAtch.atchProcessSeCode}" />
				</c:import>
	        </td>
        </tr>
		</c:forEach>
		<tr>
           <th>첨부파일 &nbsp;
             <img src="/images/egovframework/bopr/search.gif" title="설정 XML 파일 이외의 배치 관련 파일을 등록합니다.
class 파일, 파라미터 파일 등 형식에 구애 받지 않고 등록할 수 있습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다.
파일은 배포경로/상세경로의 위치에 배포됩니다.
배포경로는 배치배포에서 관리합니다." />
           </th>
           <td>
               <table summary="배치첨부파일 관리" id="atchFileTable2">
                   <caption>첨부파일</caption>
                   <colgroup>
                       <col style="width:auto">
                   </colgroup>
                   <tbody>
                       <tr id="fileRow">
                           <td>
                               <input type="file" id="atchFile" name="atchFile" onChange="fncAddFile(this)">
                           </td>
                       </tr>
                   </tbody>
            	</table>
        	</td>
    	</tr>
    	
		</tbody>
		</table>
	</div>
</div>

 <!-- 첨부파일용 -->
	<input type="hidden" name="fileListCnt" value="1" />
	<input name="atchFileAt" type="hidden" value="N">
	<input type="hidden" id="atchType" name="atchType" value="UPT"/>
	
	<!-- 첨부파일 갯수 한정 -->					  
<c:if test="${batchDlbrt.processSeCode=='DEL'}">
<input type="hidden" name="posblAtchFileNumber" value="0" />
</c:if>
<c:if test="${batchDlbrt.processSeCode=='INS' || batchDlbrt.processSeCode=='UPT'}">
<input type="hidden" name="posblAtchFileNumber" value="1" />
</c:if>
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${batchDlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${batchDlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${batchDlbrtVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/bopr/bam/EgovBatchDlbrtUpdateView.do'/>"/>
<input type="hidden" name="batchDlbrtAtchSize" id ="batchDlbrtAtchSize" value="${batchDlbrtAtchSize}"/>
<input type="hidden" name="registerFlag" value="<c:out value='${batchDlbrt.batchDlbrtResultCode}'/>"/>
<input type="hidden" name="processSeCode" id="processSeCode" value="<c:out value='${batchDlbrt.processSeCode}'/>" />
<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
<input type="hidden" name="delAtchFileId" />
<input type="hidden" name="delFileSn" />
<input type="hidden" id="atchCnt" value="${batchDlbrtAtchSize}"/>
<input type="hidden" name="reconfirm" id="reconfirm" value="<c:out value='${reconfirm}'/>" />

</form:form>
