<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

/**
  * @Class Name : EgovFileList.jsp
  * @Description : 파일 목록화면
  * @Modification Information
  * @
  * @  수정일   	수정자		수정내용
  * @ ----------	------		---------------------------
  * @ 2009.03.26	이삼섭		최초 생성
  * @ 2011.07.20	옥찬우		<Input> Tag id속성 추가( Line : 68 )
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.26
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- link href="<c:url value='/css/egovframework/com/cmm/com.css' />" rel="stylesheet" type="text/css"-->

<script type="text/javascript">

	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/com/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}

	function fn_egov_deleteFile(atchFileId, fileSn) {
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].atchFileId) != "undefined" &&
					typeof(forms[i].fileSn) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}

		//form = document.forms[0];
		form.delAtchFileId.value = atchFileId;
		form.delFileSn.value = fileSn;
		form.action = "<c:url value='/com/cmm/fms/deleteMultiFileInfs.do'/>";
		form.submit();
	}

	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		} else {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		}
	}
	/*
  	  설정파일 변경 기능
	*/
	function fncAddCfgFile(objFile)
	{
	    var strFile = objFile.value;
	    
	    var objCell = document.getElementById('cfgFileCell');
	    cfgInnerHtml = objCell.innerHTML;
	    objCell.innerHTML = "<label for='atchSeCode' class='disp_none'>배치설정파일유형</label>"
	                      + "<select name='atchSeCode' id='atchSeCode' title='배치설정파일유형'><option value='S' selected>배치설정</option></select>"
						  + "<input type='hidden' name='atchProcessSeCode' id='atchProcessSeCode' value='INS' >" 
	    				  + "<strong>상세경로 :&nbsp;</strong><input type='text' title='배치설정파일 상세경로 입력' id='batchPath' name='batchPath' size='50' style='ime-mode:disabled;' value='"
	                      + document.getElementById('batchPath').value + "' />"
	                      + "<br>" + strFile + "&nbsp;" + "<input type='button' title='설정파일 변경 취소' value='취소' onClick='fncCfgFileCancle()' />"
	                      + "<input type='hidden' id='cfgFileDel' name='cfgFileDel' value='DEL' />";
	}
	/*
	    설정파일 '취소' Button OnClick - 원래 첨부파일로 복귀
	*/
	function fncCfgFileCancle()
	{
	    document.getElementById("atchCfgFile").outerHTML = '<input type="file" id="atchCfgFile" title="설정파일 변경" name="atchCfgFile" onChange="fncAddCfgFile(this)" />';
	    
	    var objCell = document.getElementById('cfgFileCell');
	    objCell.innerHTML = cfgInnerHtml;
	}
</script>

<!-- <form name="fileForm" action="" method="post" >  -->
<input type="hidden" name="atchFileId" value="${atchFileId}">
<input type="hidden" name="fileSn">
<input type="hidden" name="fileListCnt" id="fileListCnt" value="${fileListCnt}">

<!-- </form>  -->

<!--<title>파일목록</title> -->

	<table>
		<c:forEach var="fileVO" items="${fileList}" varStatus="status">
			<c:choose>
				<c:when test="${updateFlag=='Y'}">
				<tr>
				<td>
				 <c:if test="${atchSeCode == 'S'}">
                 <table id="atchCfgFileTable" summary="배치정보 설정파일">
                     <caption>배치정보 설정파일</caption>
                     <colgroup>
                         <col style="width:auto">
                     </colgroup>
                     <tbody>
                         <tr>
                             <td id="cfgFileCell"><!-- 배포경로 -->
                                <label for='atchSeCode' class='disp_none'>배치첨부파일유형</label>
                             	<select name="atchSeCode" id="atchSeCode" title="배치설정파일유형">
							     <option value="S" <c:if test="${atchSeCode == 'S'}"> selected </c:if>>배치설정</option>
							    </select>
							    <!-- 
							    <select name="atchProcessSeCode" id="atchProcessSeCode">
							     <option value="INS" <c:if test="${atchProcessSeCode == 'INS'}"> selected </c:if>>신규등록</option>
							     <option value="UPT" <c:if test="${atchProcessSeCode == 'UPT'}"> selected </c:if>>수정</option>
					  		    </select>
					  		    -->
					  		     <input type="hidden" name="atchProcessSeCode" id="atchProcessSeCode" value="INS" >
                                 <strong>상세경로 :&nbsp;</strong><input type="text" title='배치설정파일 상세경로입력' id="batchPath" name="batchPath" size="50" maxlength="100" style="ime-mode:disabled;" value="<c:out value='${batchPath}' />">
                                 <!-- 파일정보 -->
                                 <br><c:out value="${fileVO.orignlFileNm}" />&nbsp;(<c:out value="${fileVO.fileMg}" />&nbsp;byte)
                                 <!-- 설정파일ID -->
                                 <input type="hidden" id="batchAtchFileId" name="batchAtchFileId" value="${fileVO.atchFileId}">
                             </td>
                         </tr>
                     <!-- 설정파일 End -->
                     </tbody>
                 </table>
				 </c:if>
				 <c:if test="${atchSeCode != 'S'}">
				 <table summary="첨부파일" id="atchFileTable">
                   <caption>첨부파일</caption>
                   <colgroup>
                       <col style="width:auto">
                   </colgroup>
                   <tbody>
                   <tr>
					<td>
					<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
					<img src="<c:url value='/images/egovframework/com/cmm/fms/icon/bu5_close.gif' />" 
						width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
					</td>
				   </tr>
				   <tr>
					<td>
					    <label for='atchSeCode' class='disp_none'>배치첨부파일유형 선택</label>
					 	<select name="atchSeCode" id="atchSeCode" title="배치첨부파일 유형">
					     <option value="B" <c:if test="${atchSeCode == 'B'}"> selected </c:if>>배치파일</option>
					     <option value="P" <c:if test="${atchSeCode == 'P'}"> selected </c:if>>실행파라미터</option>
					    </select>
					    <!-- 
					    <select name="atchProcessSeCode" id="atchProcessSeCode">
					     <option value="INS" <c:if test="${atchProcessSeCode == 'INS'}"> selected </c:if>>신규등록</option>
					     <option value="UPT" <c:if test="${atchProcessSeCode == 'UPT'}"> selected </c:if>>수정</option>
					     <option value="DEL" <c:if test="${atchProcessSeCode == 'DEL'}"> selected </c:if>>삭제</option>
					    </select>
					     -->
					     <input type="hidden" name="atchProcessSeCode" id="atchProcessSeCode" value="INS" >
					    <input name="batchPath" title="배치첨부파일 상세경로 입력" id="batchPath" value="${batchPath}" type="text" maxLength="100" size="60"/>
					    <!-- 설정파일ID -->
                        <input type="hidden" id="batchAtchFileId" name="batchAtchFileId" value="${fileVO.atchFileId}">
					</td>
			    	</tr>
                   </tbody>
                 </table>
				 </c:if>
				</td>
				</tr>
				</c:when>
				<c:otherwise>
				<tr>
					<td>
					<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
					<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]
					</a>
					</td>
				</tr>
				<tr>
					<td>
					    <label for='atchSeCode' class='disp_none'>배치첨부파일유형</label>
					 	<select name="atchSeCode" id="atchSeCode" title="배치첨부파일유형" disabled>
					     <option value="S" <c:if test="${atchSeCode == 'S'}"> selected </c:if>>배치설정</option>
					     <option value="B" <c:if test="${atchSeCode == 'B'}"> selected </c:if>>배치파일</option>
					     <option value="P" <c:if test="${atchSeCode == 'P'}"> selected </c:if>>실행파라미터</option>
					    </select>
					    <input type="hidden" name="atchProcessSeCode" id="atchProcessSeCode" value="INS" >
					    <input name="batchPath" id="batchPath" value="${batchPath}" title="배치첨부파일 상세경로" type="text" maxLength="100" size="60" readonly />
					</td>
				</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${fn:length(fileList) == 0}">
			<tr>
				<td></td>
			</tr>
	    </c:if>
	</table>
