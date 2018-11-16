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
  * @Class Name : EgovBatchDlbrtInsert.jsp
  * @Description : 배치심의요청 등록 화면
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
<c:set var="registerFlag" value="${empty batchDlbrtVO.batchDlbrtNo ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty batchDlbrtVO.batchDlbrtNo ? '배치심의요청 등록' : '배치심의요청 수정'}"/>
<c:set var="processSeFlag" value="<c:out value='${processSeCode}'/>"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="batchDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>
<!-- JQuery -->
 <script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
 <!-- 첨부파일  -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovBatchMultiFile.js'/>" ></script>
<script type="text/javaScript" language="javascript">

function fncSelectBatchDlbrtList() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtList.do'/>";
	varFrom.submit();       
}

function fncBatchDlbrtInsert() {
	var varFrom = document.getElementById("batchDlbrtManage");
	
    if(!validateBatchDlbrt(varFrom)){
        return;
    }else{
    	var checkCn = CheckStrLength();
    	if(!checkCn){
    		alert("배치설명은 500글자를 넘을수 없습니다.");
    		return;
    	}
    	
        varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtInsert.do'/>";

        var validateValue = document.getElementById("batchId").value;
        var validateFlag = document.getElementById("processSeCode").value;
        if(validateFlag=="UPT" || validateFlag=="DEL"){
        	if(validateValue==null||validateValue==""){
        		alert("수정/삭제 시에는 배치ID가 필수입니다.");
        		return;
        	}
        }
        
        if(validateFlag!="DEL"){
        	if (!fncRegistValidation())
            {
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
	var url = "<c:url value='/bopr/bam/EgovJobDlbrtPopupList.do'/>";
	window.open(url, 'JobDlbrtPopup', 'toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=590');
}

function fnBatchIdClick() {
	var funcString = document.getElementById('jobDlbrtNo').value;
	if(funcString=="" || funcString==null){
		alert("업무심의번호를 먼저 선택하셔야 합니다.");
		return;
	}
	var url = "<c:url value='/bopr/bam/EgovBatchDlbrtPopupList.do?jobDlbrtNo="+ funcString + "'/>";
	window.open(url, 'BatchDlbrtPopup', 'toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=590');
}

function fnProcessSeChange(){
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtInsertView.do'/>";
	varFrom.submit();
}

function fncRegistValidation()
{
    var strCfgFile = document.getElementById("atchCfgFile").value;
    
    if (strCfgFile == null || strCfgFile == "")
    {
        alert("설정파일을 선택해주세요");
        return false;
    }
    
    return true;
}

var fileAtchNo = 0;		// 파일첨부 구분 시퀀스

function fncAddFile(objFile)
{
    var strFile = objFile.value;
    
    if (strFile != null && strFile != "")
    {
        var objTable = document.getElementById("atchFileTable");
        var rowNum = objTable.rows.length;
        var newRow = objTable.insertRow(rowNum);
        var newCell = newRow.insertCell(0);
        newCell.innerHTML = "<label for='atchSeCode' class='disp_none'>배치첨부파일유형 선택</label>"
        	              + "<select title='배치첨부파일유형' name='atchSeCode' id='atchSeCode'><option value='B' selected>배치파일</option><option value='P'>실행파라미터</option>	</select>"
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

function fncAddCfgFile(objFile)
{
    var strFile = objFile.value;
    
    if (strFile != null && strFile != "")
    {
        var objCell = document.getElementById("cfgFileCell");
        
        objCell.innerHTML = "<label for='atchSeCode' class='disp_none'>배치설정파일유형</label>"
        	              + "<select title='배치설정파일유형' name='atchSeCode' id='atchSeCode'><option value='S' selected>배치설정</option></select>"
           				  + "<input type='hidden' name='atchProcessSeCode' id='atchProcessSeCode' value='INS' >"
        				  + "<strong>상세경로&nbsp;:&nbsp;</strong>"
                          + "<input type='text' title='배치설정파일 상세경로 입력' id='batchPath' name='batchPath' size='50' style='ime-mode:disabled;' value='"
                          + document.getElementById("batchPath").value
                          + "' />" + "<br>" + strFile;
    }
}

function fncFileChange(rowNum)
{
    var objCell = document.getElementById("fileHead" + rowNum);
    objCell.innerHTML += "<br>" + document.getElementById("file" + rowNum).value;
}

function fncFileDelete(element, seq)
{
    var rowIndex = element.parentNode.parentNode.rowIndex;
    document.getElementById("atchFileTable").deleteRow(rowIndex);
    document.getElementById("atchFile" + seq).disabled = "disabled";
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
function fn_egov_initManage(){
	var alertMessage = document.getElementById("batchDlbrtManage").message.value;
	if(alertMessage!=null && alertMessage!=''){
		alert(alertMessage);
	}
}
$(document).ready(function() { 
	fn_egov_initManage();
});
</script>

<form:form commandName="batchDlbrtManage" method="post" name="batchDlbrtManage" enctype="multipart/form-data">
<div class="contsBody">
	<h2>배치심의요청</h2>
	<div class="location">배치심의 > 배치심의요청 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a title="배치심의요청 목록" href="javascript:fncSelectBatchDlbrtList()">목록</a></span>
		<span class="bbsBtn"><a title="배치심의요청 등록" href="javascript:fncBatchDlbrtInsert()">등록</a></span>
	</div>
	
	<div class="bbsDetail">
	<c:if test="${processSeCode=='INS' }">
	<div class="location">배치ID와 배치명은 설정파일을 통해 자동생성됩니다.</div>
	</c:if>
		<table  summary="업무심의번호, 업무심의명, 배치ID, 배치명, 배치설명, 처리유형, 첨부파일 등의 배치심의요청 등록입니다.">
		<caption>배치심의요청 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<!-- 
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치심의번호</th>
			<td><input name="batchDlbrtNo" id="batchDlbrtNo" type="text" disabled="disabled" value="<c:out value='${batchDlbrtManage.batchDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		 -->
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for='jobDlbrtNo' class='disp_none'>업무심의번호</label>
			<input title="새창" name="jobDlbrtNo" id="jobDlbrtNo" type="text" value="<c:out value='${batchDlbrtManage.jobDlbrtNo}'/>" maxLength="13" size="20" onclick="javascript:fnJobDlbrtClick();" /></td>
		</tr>
		<tr>
			<th>업무심의명 &nbsp;
			  <img src="/images/egovframework/bopr/search.gif" title="배치 신규등록/삭제 요청시 관련 업무(이미 승인된 건에 한하여)를 지정해야 합니다.
			  업무심의번호 입력필드를 클릭하시면 승인된 업무심의 선택 팝업이 호출됩니다.
			  배치 삭제 요청시에는 관련 업무를 선택한 후 
			  배치ID 입력 필드를 클릭하여 배포완료된 배치 중 
			  삭제할 배치를 선택하셔야 합니다." />
			</th>
			<td>
			<label for='jobDlbrtNm' class='disp_none'>업무심의명</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의명" readonly type="text" value="" maxLength="80" size="80"/>
			</td>
		</tr>
		<tr>
			<th>배치ID</th>
			<td>
			<label for='batchId' class='disp_none'>배치ID</label>
			 <input name="batchId" id="batchId" title="배치ID" type="text" value="<c:out value='${batchDlbrtManage.batchId}'/>" maxLength="13" size="50" 
			    <c:if test="${processSeCode=='INS' }">
			     disabled="disabled"
			    </c:if>
			    <c:if test="${processSeCode=='UPT' || processSeCode=='DEL' }">
			     onclick="javascript:fnBatchIdClick();" title="새창"
			    </c:if>
			 />
			</td>
		</tr>
		<tr>
			<th>배치명</th>
			<td>
			<label for='batchNm' class='disp_none'>배치명</label>
			<input name="batchNm" id="batchNm" title="배치명" type="text" value="<c:out value="${batchDlbrtManage.batchNm}"/>" disabled="disabled" maxLength="100" size="80" /></td>
		</tr>
		<tr>
			<th>배치설명</th>
			<td>
			<label for='batchDc' class='disp_none'>배치설명</label>
			<textarea name="batchDc" id="batchDc" title="배치설명" rows="5" cols="80" ><c:out value="${batchDlbrtManage.batchDc}"/></textarea></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />처리유형</th>
			<td>
			<label for='processSeCode' class='disp_none'>처리유형 선택</label>
			<select title="처리유형 선택" name="processSeCode" id="processSeCode" onchange="javascript:fnProcessSeChange();">
		     <option value="INS" <c:if test="${processSeCode == 'INS'}"> selected </c:if>>신규등록</option>
		     <!-- <option value="UPT" <c:if test="${processSeCode == 'UPT'}"> selected </c:if>>수정</option> -->
		     <option value="DEL" <c:if test="${processSeCode == 'DEL'}"> selected </c:if>>삭제</option>
		    </select>
			</td>
		</tr>
		</tbody>
		</table>
		<c:if test="${processSeCode=='UPT' || processSeCode=='INS' }">
		<table id="attachInfo" summary="배치심의요청 첨부파일 테이블입니다.">
		<caption>배치심의요청 등록 첨부파일</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>
           <th>설정파일 &nbsp;
           <img src="/images/egovframework/bopr/search.gif" title="배치 설정 XML 파일을 등록합니다.
반드시 형식에 맞는 파일을 등록해야 하고 
다른 배치와 Job Id, bean Id 가 중복될 수 없습니다. 
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다.
파일은 배포경로/상세경로의 위치에 배포됩니다. 
배포경로는 배치배포에서 관리합니다." />
			  </th>
           <td>
              <table summary="배치설정파일 관리" id="atchCfgFileTable">
                  <caption>설정파일</caption>
                  <colgroup>
                     <col style="width:auto">
                  </colgroup>
                  <tbody>
                      <tr>
                          <td>
                              <input type="file" id="atchCfgFile" name="atchCfgFile" onchange="fncAddCfgFile(this)">
                          </td>
                      </tr>
                      <tr>
                          <td id="cfgFileCell">
                          	  <label for='atchSeCode' class='disp_none'>배치설정유형</label>
                              <select title='배치설정유형' name="atchSeCode" id="atchSeCode">
						      	<option value="S" selected>배치설정</option>
						      </select>
						      <input type='hidden' name='atchProcessSeCode' id='atchProcessSeCode' value='INS' >
                              <strong>상세경로&nbsp;:&nbsp;</strong><input type="text" id="batchPath" title='배치설정파일 상세경로' name="batchPath" size="50" style="ime-mode:disabled;">
                          </td>
                      </tr>
                  </tbody>
              </table>
           </td>
        </tr>
		<tr>
           <th>첨부파일 &nbsp;
             <img src="/images/egovframework/bopr/search.gif" title="설정 XML 파일 이외의 배치 관련 파일을 등록합니다.
class 파일, 파라미터 파일 등 형식에 구애 받지 않고 등록할 수 있습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다.
파일은 배포경로/상세경로의 위치에 배포됩니다.
배포경로는 배치배포에서 관리합니다." />
           </th>
           <td>
               <table summary="배치첨부파일 관리" id="atchFileTable">
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
		</c:if>
	</div>
</div>


<input type="hidden" id="atchType" name="atchType" value="INS"/>
<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
<input type="hidden" id="atchCnt" value="0"/>
</form:form>
