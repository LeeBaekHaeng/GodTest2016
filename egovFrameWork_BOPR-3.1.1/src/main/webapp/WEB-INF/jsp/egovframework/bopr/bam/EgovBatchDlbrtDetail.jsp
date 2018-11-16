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
<c:set var="registerFlag" value="${empty batchDlbrt.batchDlbrtResultCode ? 'UPDATE' : ((batchDlbrt.batchDlbrtResultCode=='RT')? 'DISABLE' : (batchDlbrt.batchDlbrtResultCode=='RE') ? 'RECONFIRM' : 'WDTB')}"/>
<c:set var="registerFlagName" value="${batchDlbrt.batchDlbrtResultCode=='CN' ? '배치심의요청 내용(승인상태)' : '배치심의요청 상세'}"/>
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
    var existFileNum = document.getElementById("batchDlbrtManage").fileListCnt.value;
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
	if(registerFlag=="CN"||registerFlag=="RT"){
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
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtUpdateView.do'/>";

    varFrom.submit();
}

function fncBatchDlbrtDelete() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fncBatchDlbrtReconfirm() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtReconfirm.do'/>";
	if(confirm("재심의요청 하시겠습니까?")){
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
</script>

<form:form name="batchDlbrtManage" commandName="batchDlbrtManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>배치심의요청</h2>
	<div class="location">배치심의 > 배치심의요청 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="배치심의요청 목록" href="javascript:fncSelectBatchDlbrtList()">목록</a></span>
		<c:if test="${registerFlag == 'UPDATE'}">
		<span class="bbsBtn"><a title="배치심의요청 수정" href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn"><a title="배치심의요청 삭제" href="javascript:fncBatchDlbrtDelete()">삭제</a></span>
		</c:if>
		<c:if test="${registerFlag == 'DISABLE'}">
		<span class="bbsBtn"><a title="배치심의요청 재심의요청" href="javascript:fncBatchDlbrtReconfirm()">재심의요청</a></span>
		</c:if>
		<c:if test="${registerFlag == 'RECONFIRM'}">
		<span class="bbsBtn"><a title="배치심의요청 수정" href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="배치심의번호, 업무심의번호, 업무심의명, 배치ID, 배치명, 배치설명, 처리유형, 심의결과, 심의사유, 최초등록자ID, 최종수정자ID, 등록일시, 수정일시 등의 배치심의요청관리 상세정보입니다.">
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
			<label for="batchDlbrtNo" class="disp_none">배치심의번호</label>
			<input name="batchDlbrtNo" id="batchDlbrtNo" title="배치심의번호" type="text" readonly value="<c:out value='${batchDlbrt.batchDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for="jobDlbrtNo" class="disp_none">업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" value="<c:out value='${batchDlbrt.jobDlbrtNo}'/>"  maxLength="13" size="20" readonly="readonly"/></td>
		</tr>
		<tr>
			<th>업무심의명</th>
			<td>
			<label for="jobDlbrtNm" class="disp_none">업무심의명</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의명" type="text" readonly value="<c:out value='${batchDlbrt.jobDlbrtNm}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치ID</th>
			<td>
			<label for="batchId" class="disp_none">배치ID</label>
			<input name="batchId" id="batchId" title="배치ID" type="text" readonly value="<c:out value='${batchDlbrt.batchId}'/>"  maxLength="13" size="50" /></td>
		</tr>
		<tr>
			<th>배치명</th>
			<td>
			<label for="batchNm" class="disp_none">배치명</label>
			<input name="batchNm" id="batchNm" title="배치명" type="text" value="<c:out value="${batchDlbrt.batchNm}"/>" readonly maxLength="100" size="80" /></td>
		</tr>
		<tr>
			<th>배치설명</th>
			<td>
			<label for="batchDc" class="disp_none">배치설명</label>
			<textarea name="batchDc" id="batchDc" title="배치설명" readonly="readonly" rows="5" cols="80"><c:out value="${batchDlbrt.batchDc}"/></textarea></td>
		</tr>
		<tr>
			<th>처리유형</th>
			<td>
			<label for="processSeCode" class="disp_none">처리유형</label>
			<input name="processSeCode" id="processSeCode" title="처리유형" type="text" value="<c:if test="${batchDlbrt.processSeCode == 'INS'}">신규등록</c:if><c:if test="${batchDlbrt.processSeCode == 'UPT'}">수정</c:if><c:if test="${batchDlbrt.processSeCode == 'DEL'}">삭제</c:if>" maxLength="100" size="20" readonly /></td>
		</tr>
		<tr>
			<th>심의결과</th>
			<td>
			<label for="batchDlbrtResultCodeNm" class="disp_none">심의결과</label>
			<input name="batchDlbrtResultCodeNm" id="batchDlbrtResultCodeNm" title="심의결과" type="text"
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
			<label for="batchDlbrtCn" class="disp_none">심의사유</label>
			<input name="batchDlbrtCn" id="batchDlbrtCn" title="심의사유" type="text" value="<c:out value="${batchDlbrt.batchDlbrtCn}"/>" maxLength="100"  size="80" readonly /></td>
		</tr>
		<tr>
			<th>최초등록자ID</th>
			<td>
			<label for="frstRegisterId" class="disp_none">최초등록자ID</label>
			<input name="frstRegisterId" id="frstRegisterId" title="최초등록자ID" type="text" value="<c:out value="${batchDlbrt.frstRegisterId}"/>" maxLength="20"size="20" readonly/></td>
		</tr>
		<tr>
			<th>최종수정자ID</th>
			<td>
			<label for="lastUpdusrId" class="disp_none">최종수정자ID</label>
			<input name="lastUpdusrId" id="lastUpdusrId" title="최종수정자ID" type="text" value="<c:out value="${batchDlbrt.lastUpdusrId}"/>"maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>
			<label for="frstRegistPnttm" class="disp_none">등록일시</label>
			<input name="frstRegistPnttm" id="frstRegistPnttm" title="등록일시" type="text" value="<c:out value="${batchDlbrt.frstRegistPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>수정일시</th>
			<td>
			<label for="lastUpdtPnttm" class="disp_none">수정일시</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="수정일시" type="text" value="<c:out value="${batchDlbrt.lastUpdtPnttm}"/>"  maxLength="20"  size="20" readonly/></td>
		</tr>
		
		</tbody>
		</table>
		<c:if test="${batchDlbrt.processSeCode=='UPT' || batchDlbrt.processSeCode=='INS' }">
		<table id="attachInfo" summary="배치심의요청관리 첨부파일입니다.">
		<caption>배치심의요청관리 첨부파일 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>		

		<c:forEach var="batchDlbrtAtch" items="${batchDlbrtAtch}" varStatus="status">
		<tr>
			<c:if test="${batchDlbrtAtch.atchSeCode =='S'}">
				<th>설정파일</th>
			</c:if>
			<c:if test="${batchDlbrtAtch.atchSeCode !='S'}">
				<th>첨부파일</th>
			</c:if>
			<td>
		        <c:import charEncoding="utf-8" url="/com/cmm/fms/selectMultiFileInfs.do">
					<c:param name="param_atchFileId" value="${batchDlbrtAtch.atchFileId}" />
					<c:param name="param_atchSeCode" value="${batchDlbrtAtch.atchSeCode}" />
					<c:param name="param_batchPath" value="${batchDlbrtAtch.batchPath}" />
					<c:param name="param_atchProcessSeCode" value="${batchDlbrtAtch.atchProcessSeCode}" />
				</c:import>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:if>
	</div>
</div>

 <!-- 첨부파일용 -->
	<input type="hidden" name="fileListCnt" value="1" />
	<input name="atchFileAt" type="hidden" value="N">
	<input type="hidden" id="atchType" name="atchType" value="UPT"/>
	
	<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="1" /> 
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${batchDlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${batchDlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${batchDlbrtVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/bopr/bam/EgovBatchDlbrt.do'/>"/>
<input type="hidden" name="batchDlbrtAtchSize" id ="batchDlbrtAtchSize" value="${batchDlbrtAtchSize}"/>
<input type="hidden" name="registerFlag" value="<c:out value='${batchDlbrt.batchDlbrtResultCode}'/>"/>
<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
<input type="hidden" name="delAtchFileId" />
<input type="hidden" name="delFileSn" />
<input type="hidden" id="atchCnt" value="${batchDlbrtAtchSize}"/>

</form:form>
