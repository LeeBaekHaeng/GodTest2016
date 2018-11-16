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
  * @Class Name : EgovBatchInfoRegist.jsp
  * @Description : 배치정보 신규 등록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.08.20  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.08.20
  *  @version 0.9 
  *  @see
  */
%>

<!-- 공통 Util Javascript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<script type="text/javaScript" language="javascript">
<!--
    /* form */
    var objParam;
    
    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        /* parameter form */
        objParam = document.getElementById("parameter");
        
        if (fncCallAlert("<c:out value='${alertMssage}'/>"))
        {
        	fncList();
        }
    });
    
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectBatchInfoList.do'/>";
        
        if ("Y" == "${parameter.searchUseYn}")
        {
            objParam.jobSeCode.value = "${parameter.jobSeCode}";
            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value = "${parameter.searchKeyword}";
            objParam.pageIndex.value = "${parameter.pageIndex}";
        }
        else
        {
        	objParam.jobSeCode.value = "";
        	objParam.pageIndex.value = "${parameter.pageIndex}";
        }
        
        objParam.submit();
    }
    
    function fncRegist()
    {
    	if (fncCallAlert("<c:out value='${ftpNeed}'/>"))
        {
        	return;
        }
    	
        if (!fncRegistValidation())
        {
            return;
        }

        var url = "<c:url value='/bopr/sim/selectFtpPasswordPopup.do' />";
        window.open(url, "FTPPasswordPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=400, height=120");
    }
    
    function fncFtpPasswordCallback()
    {
    	objParam.action = "<c:url value='/bopr/sim/insertBatchInfo.do' />";
        objParam.submit();
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
            newCell.innerHTML = "상세경로&nbsp;:&nbsp;"
                              + "<label for='wdtbPath" + fileAtchNo + "' class='disp_none'>첨부파일 배포 상세경로</label>"
                              + "<input type='text' id='wdtbPath" + fileAtchNo + "' name='wdtbPath" + fileAtchNo + "' size='50' style='ime-mode:disabled;' />"
                              + "<br>"
                              + strFile
                              + "&nbsp;"
                              + "<label for='delBtn" + fileAtchNo + "' class='disp_none'>첨부파일 삭제 기능</label>"
                              + "<input type='button' id='delBtn" + fileAtchNo + "' value='delete' onclick='fncFileDelete(this, " + fileAtchNo + ")' />";
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
            
            objCell.innerHTML = "상세경로&nbsp;:&nbsp;"
                              + "<label for='cfgWdtbPath' class='disp_none'>설정파일 배포 상세경로</label>"
                              + "<input type='text' id='cfgWdtbPath' name='cfgWdtbPath' size='50' style='ime-mode:disabled;' value='"
                              + document.getElementById("cfgWdtbPath").value
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
-->
</script>
    
<form id="parameter" name="parameter" method="post" enctype="multipart/form-data" action="">

    <div class="contsBody">

        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <!-- 페이징 처리 위한 parameter -->
        <input type="hidden" name="pageIndex" value='1'>
        <!-- FTP 비밀번호 parameter -->
        <input type="hidden" name="ftpPassword" id="ftpPassword">
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>배치정보관리</h2>
        <div class="location">배치정보 &gt; 배치정보관리 &gt; <strong>등록</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="배치정보 목록 화면 이동">목록</a></span>
            <span class="bbsBtn"><a href="javascript:fncRegist()" title="배치정보 등록 기능, 새창열림">등록</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table  summary="배치설명, 업무구분, 온라인실행여부, 설정파일, 첨부파일 순서로 배치정보 등록 데이터를 나타낸 표입니다.">
                <caption>배치정보 등록</caption>
                <colgroup>
                <col style="width:20%">
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <th>배치설명</th>
                        <td>
                            <label for="batchDc" class="disp_none">배치설명</label>
                            <textarea id="batchDc" name="batchDc" rows="5" cols="60" title="배치설명"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>업무구분</th>
                        <td>
                            <label for="jobSeCode" class="disp_none">업무구분 선택</label>
                            <select id="jobSeCode" name="jobSeCode" title="업무구분">
                              <c:forEach var="list" items="${BO001}">
                                <option value="<c:out value='${list.code}'/>"><c:out value='${list.codeNm}'/></option>
                              </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>온라인실행여부</th>
                        <td>
                            <label for="onlineExecutAt" class="disp_none">온라인실행여부 선택</label>
                            <select id="onlineExecutAt" name="onlineExecutAt" title="온라인실행여부">
                                <option value="Y">실행가능</option>
                                <option value="N" selected="selected">실행불가</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            설정파일&nbsp;
                            <img src="/images/egovframework/bopr/search.gif"
title="배치 설정 XML 파일을 등록합니다. 반드시 형식에 맞는 파일을 등록해야하고 다른 배치와 Job Id, bean Id가 중복될 수 없습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다. 파일은 배포경로/상세경로의 위치에 배포됩니다." />
                        </th>
                        <td>
                            <strong>배포경로 : <c:out value='${cfgPath}'/></strong>
                            <table id="atchCfgFileTable" summary="배치정보의 설정파일 정보를 나타낸 표입니다.">
                                <caption>설정파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td>
                                            <label for="atchCfgFile" class="disp_none">설정파일 선택</label>
                                            <input type="file" id="atchCfgFile" name="atchCfgFile" onchange="fncAddCfgFile(this)">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="cfgFileCell">
                                            <label for="cfgWdtbPath" class="disp_none">설정파일 배포 상세경로</label>
                                            <strong>상세경로&nbsp;:&nbsp;</strong><input type="text" id="cfgWdtbPath" name="cfgWdtbPath" size="50" style="ime-mode:disabled;">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            첨부파일&nbsp;
<img src="/images/egovframework/bopr/search.gif"
title="설정 XML 파일 이외의 배치 관련 파일을 등록합니다. class 파일, 파라미터 파일 등 형식에 구애 받지 않고 등록할 수 있습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다. 파일은 배포경로/상세경로의 위치에 배포됩니다." />
                        </th>
                        <td>
                            <strong>배포경로 : <c:out value='${batchPath}'/></strong>
                            <table id="atchFileTable" summary="배치정보의 첨부파일 정보를 나타낸 표입니다.">
                                <caption>첨부파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr id="fileRow">
                                        <td>
                                            <label for="objFile" class="disp_none">첨부파일 선택</label>
                                            <input type="file" id="objFile" name="atchFile" onChange="fncAddFile(this)">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- Contents End -->
    
    </div>
</form>