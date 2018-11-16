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
  * @Class Name : EgovBatchWdtbManageDetail.jsp
  * @Description : 배치정보관리 상세 조회 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.16  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.16
  *  @version 0.9 
  *  @see
  */
%>

<!-- 공통 Util Javascript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
<!-- 유효성 검사 Javascript -->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="parameter" staticJavascript="false" xhtml="true" cdata="false" />

<script type="text/javaScript" language="javascript">
<!--
    /* form */
    var objParam;
    
    /* 첨부파일 시퀀스 */
    var fileAtchNo = 0;
    
    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        /* parameter form */
        objParam = document.getElementById("parameter");
        
        /* 첨부파일 시퀀스 초기화 - 등록된 첨부파일 개수와 일치시킴 */
        fileAtchNo = document.getElementById('atchFileTable').rows.length;
        
        /* initialize input value */
        objParam.batchId.value         = "<c:out value='${batchInfo.batchId}' />";
        objParam.batchNm.value         = "<c:out value='${batchInfo.batchNm}' />";
        objParam.jobDlbrtNo.value      = "<c:out value='${batchInfo.jobDlbrtNo}' />";
        objParam.frstRegistPnttm.value = "<c:out value='${batchInfo.frstRegistPnttm}' />";
        objParam.lastUpdtPnttm.value   = "<c:out value='${batchInfo.lastUpdtPnttm}' />";
        objParam.onlineExecutAt.value  = "<c:out value='${batchInfo.onlineExecutAt}' />";
        objParam.jobSeCode.value       = "<c:out value='${batchInfo.jobSeCode}' />";
        
        fncCallAlert("<c:out value='${alertMssage}'/>");
    });
    
    /*
        '목록' Button OnClick
    */
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectBatchInfoList.do'/>";
        
        if ("Y" == "${parameter.searchUseYn}")
        {
            objParam.jobSeCode.value       = "${parameter.jobSeCode}";
            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value   = "${parameter.searchKeyword}";
            objParam.pageIndex.value       = "${parameter.pageIndex}";
        }
        else
        {
        	objParam.jobSeCode.value = "";
        }
        
        objParam.submit();
    }
    
    /*
        '수정' Button OnClick
    */
    function fncUpdate()
    {
        if (fncCallAlert("<c:out value='${message}'/>"))
        {
            return;
        }
        
        var atchFileIdArray = document.getElementsByName("atchFileIds");
        
        var atchFileDelYn = false;
        var atchFilePathChangeYn = false;
    	
        if ( atchFileIdArray )
        {
        	for (var index = 0; index < atchFileIdArray.length; index++)
        	{
        		if ( document.getElementById(atchFileIdArray[index].value).value == "DEL" )
        		{
        			atchFileDelYn = true;
        		}
        		else
        		{
        			var orgPath = document.getElementById("orgWdtbPath" + atchFileIdArray[index].value).value;
        			var path = document.getElementById("wdtbPath" + atchFileIdArray[index].value).value;
        			
        			if ( orgPath != path )
        			{
        				atchFilePathChangeYn = true;
        			}
        		}
        	}
        }
    	
    	var fileAtchYn = false;
    	
    	for (var index = 0; index < fileAtchNo; index++)
    	{
    		if ( document.getElementById("atchFile" + index) )
    		{
    			fileAtchYn = true;
    		}
    	}

    	callbackAction = "<c:url value='/bopr/sim/updateBatchInfo.do'/>";
    	
        if ( document.getElementById("orgCfgWdtbPath").value != document.getElementById("cfgWdtbPath").value
        	 || !fncIsEmpty( document.getElementById("atchCfgFile").value )
        	 || atchFileDelYn || fileAtchYn || atchFilePathChangeYn
        		)
        {
       		var url = "<c:url value='/bopr/sim/selectFtpPasswordPopup.do' />";
      		window.open(url, "FTPPasswordPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=400, height=120");
        }
        else
    	{
    		fncFtpPasswordCallback();
    	}
    }
    
    /*
        '삭제' Button OnClick
    */
    function fncDelete()
    {
        callbackAction = "<c:url value='/bopr/sim/deleteBatchInfo.do'/>";
        var url = "<c:url value='/bopr/sim/selectFtpPasswordPopup.do' />";
        window.open(url, "FTPPasswordPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=400, height=120");
    }
    
    function fncFtpPasswordCallback()
    {
    	objParam.action = callbackAction;
        objParam.submit();
    }
    
    /*
        첨부파일추가 기능
    */
    function fncAddFile(objFile)
    {
        var strFile = objFile.value;
        
        if (strFile != null && strFile != "")
        {
            var objTable = document.getElementById("atchFileTable");
            var rowNum = objTable.rows.length;
            var newRow = objTable.insertRow(rowNum);
            var newCell = newRow.insertCell(0);
            newCell.innerHTML = "상세경로 :&nbsp;<input type='text' id='wdtbPath" + fileAtchNo + "' name='wdtbPath" + fileAtchNo + "' size='50' style='ime-mode:disabled;' />"
                              + "<br>"
                              + strFile
                              + "&nbsp;"
                              + "<input type='button' value='delete' onclick='fncFileDelete(this, " + fileAtchNo + ")' />";
        }
        
        objFile.style.display = "none";
        objFile.id = "atchFile" + fileAtchNo;
        objFile.name = "atchFile" + fileAtchNo;
        fileAtchNo++;
        
        var objFileRow = document.getElementById("fileRow");
        var newCell = objFileRow.insertCell(0);
        newCell.innerHTML = "<input type='file' id='objFile' name='atchFile' onChange='fncAddFile(this)' />";
    }
    
    /*
        첨부파일 'delete' Button OnClick
    */
    function fncFileDelete(element, seq)
    {
        var rowIndex = element.parentNode.parentNode.rowIndex;
        document.getElementById("atchFileTable").deleteRow(rowIndex);
        document.getElementById("atchFile" + seq).outerHTML = "";
    }
    
    /*
        이미 첨부된 첨부파일 'delete' Button OnClick
    */
    function fncReqFileDelete(atchFileId)
    {
        var objFileStatus = document.getElementById(atchFileId);
        var objRow = objFileStatus.parentNode.parentNode;
        objRow.style.display = "none";
        objFileStatus.value = "DEL";
    }
    
    /* 설정파일 배포경로 보존 String */
    var cfgInnerHtml = "";
    
    /*
        설정파일 변경 기능
    */
    function fncAddCfgFile(objFile)
    {
        var strFile = objFile.value;
        
        var objCell = document.getElementById('cfgFileCell');
        cfgInnerHtml = objCell.innerHTML;
        objCell.innerHTML = "상세경로 :&nbsp;<input type='text' id='cfgWdtbPath' name='cfgWdtbPath' size='50' style='ime-mode:disabled;' value='"
                          + document.getElementById('cfgWdtbPath').value + "' />"
                          + "<br>" + strFile + "&nbsp;" + "<input type='button' value='취소' onClick='fncCfgFileCancle()' />"
                          + "<input type='hidden' id='cfgFileDel' name='cfgFileDel' value='DEL' />";
    }
    
    /*
        설정파일 '취소' Button OnClick - 원래 첨부파일로 복귀
    */
    function fncCfgFileCancle()
    {
        document.getElementById("atchCfgFile").outerHTML = '<input type="file" id="atchCfgFile" name="atchCfgFile" onChange="fncAddCfgFile(this)" />';
        
        var objCell = document.getElementById('cfgFileCell');
        objCell.innerHTML = cfgInnerHtml;
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
        <div class="location">배치정보 &gt; 배치정보관리 &gt; <strong>상세정보</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="배치정보 목록 화면 호출">목록</a></span>
            <span class="bbsBtn"><a href="javascript:fncUpdate()" title="배치정보 수정 기능, 새창열림">수정</a></span>
            <span class="bbsBtn"><a href="javascript:fncDelete()" title="배치정보 삭제 기능, 새창열림">삭제</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table  summary="배치I.D., Job이름(배치명), 배치설명, 업무심의번호, 업무구분, 등록일자, 최종수정일자, 온라인실행여부, 설정파일, 첨부파일, 스케줄의 순서로 배치정보 상세를 나타낸 표입니다.">
                <caption>배치정보 상세정보</caption>
                <colgroup>
                    <col style="width:20%">
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <th>배치ID</th>
                        <td>
                            <label for="batchId" class="disp_none">배치ID</label>
                            <input type="text" id="batchId" name="batchId" size="30" title="배치ID" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>Job이름(배치명)</th>
                        <td>
                            <label for="batchNm" class="disp_none">Job이름(배치명)</label>
                            <input type="text" id="batchNm" name="batchNm" size="30" title="Job이름(배치명)" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>배치설명</th>
                        <td>
                            <label for="batchDc" class="disp_none">배치설명</label>
                            <textarea id="batchDc" name="batchDc" rows="5" cols="60" title="배치설명"><c:out value='${batchInfo.batchDc}' /></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>업무심의번호</th>
                        <td>
                            <label for="jobDlbrtNo" class="disp_none">업무심의번호</label>
                            <input type="text" id="jobDlbrtNo" name="jobDlbrtNo" size="30" title="업무심의번호" readonly="readonly">
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
                        <th>등록일자</th>
                        <td>
                            <label for="frstRegistPnttm" class="disp_none">등록일자</label>
                            <input type="text" id="frstRegistPnttm" name="frstRegistPnttm" size="30" title="등록일자" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>최종수정일자</th>
                        <td>
                            <label for="lastUpdtPnttm" class="disp_none">최종수정일자</label>
                            <input type="text" id="lastUpdtPnttm" name="latUpdtPnttm" size="30" title="최종수정일자" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>온라인실행여부</th>
                        <td>
                            <label for="onlineExecutAt" class="disp_none">온라인실행여부 선택</label>
                            <select id="onlineExecutAt" name="onlineExecutAt">
                                <option value="Y">실행가능</option>
                                <option value="N">실행불가</option>
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
                        <td style="padding:0px;margin:0px;"><!-- 설정파일 Start -->
                          <c:forEach var="list" items="${batchInfo.batchAtchFileVOList}">
                          <c:if test="${list.atchSeCode == 'S'}">
                            <table summary="배치정보 설정파일" style="border-top-width:0px;">
                                <caption>배치정보 설정파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td><!-- 파일첨부 form -->
                                            <label for="atchCfgFile" class="disp_none">설정파일 선택</label>
                                            <input type="file" id="atchCfgFile" name="atchCfgFile" onChange="fncAddCfgFile(this)">
                                            <!-- 설정파일ID -->
                                            <input type="hidden" id="cfgAtchFileId" name="cfgAtchFileId" value="${list.atchFileId}">
                                            <input type="hidden" id="orgCfgWdtbPath" name="orgCfgWdtbPath" value="${list.wdtbPath}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>배포경로 : <c:out value='${cfgPath}'/></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td id="cfgFileCell" style="border-bottom-width:0px;"><!-- 배포경로 -->
                                            <strong>상세경로 :&nbsp;</strong>
                                            <label for="cfgWdtbPath" class="disp_none">설정파일 배포경로</label>
                                            <input type="text" id="cfgWdtbPath" name="cfgWdtbPath" size="50" maxlength="100" style="ime-mode:disabled;" value="<c:out value='${list.wdtbPath}' />">
                                            <!-- 파일정보 -->
                                            <c:forEach var="detailList" items="${list.atchFileDetailList}">
                                            <br><c:out value="${list.batchFileNm}" />&nbsp;(<c:out value="${detailList.fileMg}" />&nbsp;byte)
                                            </c:forEach>
                                        </td>
                                    </tr>
                                <!-- 설정파일 End -->
                                </tbody>
                            </table>
                          </c:if>
                          </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            첨부파일&nbsp;
<img src="/images/egovframework/bopr/search.gif"
title="설정 XML 파일 이외의 배치 관련 파일을 등록합니다. class 파일, 파라미터 파일 등 형식에 구애 받지 않고 등록할 수 있습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다. 파일은 배포경로/상세경로의 위치에 배포됩니다." />
                        </th>
                        <td style="padding:0px;margin:0px;">
                            <table id="atchFileTable" summary="배치정보 첨부파일" style="border-top-width:0px;">
                                <caption>배치정보 첨부파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr id="fileRow">
                                        <td>
                                            <label for="objFile" class="disp_none">첨부파일 선택</label>
                                            <input type="file" id="objFile" name="objFile" onChange="fncAddFile(this)">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <strong>배포경로 : <c:out value='${batchPath}'/></strong>
                                        </td>
                                    </tr>
                                  <!-- 첨부파일 Start -->
                                  <c:forEach var="list" items="${batchInfo.batchAtchFileVOList}">
                                  <c:if test="${list.atchSeCode != 'S'}">
                                    <tr>
                                        <td style="border-bottom-width:0px;"><!-- 첨부파일ID -->
                                            <input type="hidden" id="atchFileIds" name="atchFileIds" value="${list.atchFileId}">
                                            <!-- 삭제대상 여부 - 삭제대상일 경우 'DEL' 값 설정 -->
                                            <input type="hidden" id="<c:out value='${list.atchFileId}' />" name="<c:out value='${list.atchFileId}' />">
                                            <!-- 원배포경로 -->
                                            <input type="hidden" id="orgWdtbPath<c:out value='${list.atchFileId}' />" name="orgWdtbPath<c:out value='${list.atchFileId}' />" value="<c:out value='${list.wdtbPath}' />" style="ime-mode: disabled;">
                                            <!-- 배포경로 -->
                                            <strong>상세경로 :&nbsp;</strong>
                                            <label for="wdtbPath<c:out value='${list.atchFileId}' />" class="disp_none">첨부파일 배포 상세경로</label>
                                            <input type="text" id="wdtbPath<c:out value='${list.atchFileId}' />" name="wdtbPath<c:out value='${list.atchFileId}' />" size="50" maxlength="100" style="ime-mode:disabled;" value="<c:out value='${list.wdtbPath}' />">
                                            <!-- 파일정보 -->
                                          <c:forEach var="detailList" items="${list.atchFileDetailList}">
                                            <br><c:out value="${detailList.orignlFileNm}" />&nbsp;(<c:out value="${detailList.fileMg}" />&nbsp;byte)
                                            &nbsp;<input type='button' value='delete' onclick="fncReqFileDelete('<c:out value="${list.atchFileId}" />')">
                                          </c:forEach>
                                        </td>
                                    </tr>
                                  </c:if>
                                  </c:forEach>
                                <!-- 첨부파일 End -->
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>스케줄</th>
                        <td style="padding:0px;margin:0px;">
                            <table summary="스케줄번호, 스케줄명, 실행주기의 순서로 나타낸 스케줄목록 표입니다." style="border-top-width:0px;">
                                <caption>스케줄 목록</caption>
                                <colgroup>
                                    <col style="width:25%">
                                    <col style="width:auto">
                                    <col style="width:25%">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col" style="text-align:center;border-right:0px;">스케줄번호</th>
                                        <th scope="col" style="text-align:center;border-right:0px;">스케줄명</th>
                                        <th scope="col" style="text-align:center;border-right:0px;">실행주기</th>
                                    </tr>
                                </thead>
                                <tbody>
                                  <c:forEach var="schdul" items="${schdulList}">
                                    <tr>
                                        <td style="text-align:center;"><c:out value="${schdul.schdulNo}" /></td>
                                        <td><c:out value="${schdul.schdulNm}" /></td>
                                        <td><c:out value="${schdul.strExecutCycle}" /></td>
                                    </tr>
                                  </c:forEach>
                                  <c:if test="${empty schdulList}">
                                    <tr>
                                        <td style="text-align:center;" colspan="3">등록된 스케줄이 없습니다</td>
                                    </tr>
                                  </c:if>
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