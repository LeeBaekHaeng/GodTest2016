<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovSchdulList.jsp
  * @Description : 스케줄관리 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.17  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.17
  *  @version 0.9
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>스케줄목록팝업</title>

<!-- 공통 Util Javscript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
<!-- Callendar javascript -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
    /* form */
    var objParam;
    
    /*
        화면 OnLoad
    */
    $(document).ready(function()
    {
        objParam = document.getElementById("parameter");
        
        /*
			검색 조건을 걸어 목록 조회 한 경우, 검색 조건 form.hidden에 설정
		*/
		objParam.searchCondition.value = '${parameter.searchCondition}';    // searchCondition
		objParam.searchKeyword.value   = '${parameter.searchKeyword}';      // searchKeyword
		objParam.searchUseYn.value     = '${parameter.searchUseYn}';        // searchUseYn - 검색 조건 걸어 조회 한 경우 Y
		objParam.executSchdulDe.value  = '${parameter.executSchdulDe}';
		objParam.jobSeCode.value       = '${parameter.jobSeCode}';
		objParam.pageIndex.value       = "${parameter.pageIndex}";
        
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        objParam.selSearchCondition.value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
        fncSearchConditionChange();
        objParam.txtExecutSchdulDe.value = '<c:out value="${parameter.executSchdulDe}"/>';
        objParam.selJobSeCode.value = '<c:out value="${parameter.jobSeCode}"/>';
        
    });
    
    /*
        searchCondition 변경에 따른 searchKeyword 설정
    */
    function fncSearchConditionChange()
    {
        if ( fncIsEmpty(document.getElementById("selSearchCondition").value) )
        {
            document.getElementById("txtSearchKeyword").value = "검색조건을 선택하세요";
            document.getElementById("txtSearchKeyword").disabled = "disabled";
        }
        else
        {
            document.getElementById("txtSearchKeyword").value = objParam.searchKeyword.value;
            document.getElementById("txtSearchKeyword").disabled = "";
        }
    }
    
    /*
        '조회' Button onClick 기능
    */
    function fncSelectNewList()
    {
        objParam.searchCondition.value = objParam.selSearchCondition.value;		// 조회조건 설정
        objParam.searchKeyword.value = objParam.txtSearchKeyword.value;			// 조회Keyword 설정
        
        objParam.executSchdulDe.value = objParam.txtExecutSchdulDe.value;
        objParam.jobSeCode.value = objParam.selJobSeCode.value;
        
        objParam.pageIndex.value= "1";											// 페이지Index '1'로 설정
        
        
        fncSelectList();														// 배치정보 목록 조회 호출
    }
    
    /*
        SearchKeyword 입력 시 ENTER 버튼 으로 조회 기능
    */
    function fncSelectByEnter()
    {
        if (event.keyCode==13)
        {
            fncSelectNewList();
        }
    }
    
    /*
        Page Link 기능
    */
    function linkPage(pageNo)
    {
        objParam.pageIndex.value = pageNo;    // 페이지Index 클릭한 번호로 설정
        fncSelectList();                      // 배치정보 목록 조회 호출
    }
    
    /*
        스케줄 목록 조회 호출
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSchdulList.do'/>";    // 스케줄목록 호출 URL
        objParam.submit();
    }
    
    function fncSelect(batchId, batchNm, schdulNo, schdulNm)
    {
    	if (opener.document.getElementById("batchId") != null)
    	{
    		opener.document.getElementById("batchId").value = batchId;
    	}
    	
    	if (opener.document.getElementById("batchNm") != null)
    	{
    		opener.document.getElementById("batchNm").value = batchNm;
    	}
    	
    	if (opener.document.getElementById("schdulNo") != null)
    	{
    		opener.document.getElementById("schdulNo").value = schdulNo;
    	}
    	
    	if (opener.document.getElementById("schdulNm") != null)
    	{
    		opener.document.getElementById("schdulNm").value = schdulNm;
    	}
    	
        opener.fncSchdulPopupCallback();
        
        self.close();
    }
-->
</script>    

</head>
    
<body>

<form id="parameter" name="parameter" method="post" action="">

    <div class="popupBody">

        <!-- Parameter Start -->
        <!-- 팝업 여부 구분 위한 parameter -->
        <input type="hidden" name="popupAt" id="popupAt" value="Y">
        <!-- 스케줄 상세정보 화면 호출 위한 parameter -->
        <input type="hidden" name="batchId" id="batchId">
        <input type="hidden" name="schdulNo" id="schdulNo">
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="executSchdulDe" id="executSchdulDe">
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <!-- 배치배포 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
        <input type="hidden" name="searchUseYn" id="searchUseYn">
        <!-- 페이징 처리 위한 parameter --><input type="hidden" name="pageIndex" value='1'>
        <!-- 달력 팝업 URL -->
        <input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>">
        <div><form:errors path="evlDe"/></div>
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>스케줄 목록 팝업</h2>
        <div class="location">스케줄 관리 &gt; <strong>팝업</strong></div>
        <!-- Title End -->
        
        <!-- 검색영역 Start -->
        <div class="search">
            <fieldset class="searchboxA">
                <legend>검색 영역</legend>
                <label for="selJobSeCode" class="disp_none">업무구분 검색조건 선택</label>
                <select id="selJobSeCode" name="selJobSeCode" class="serSel" style="" title="업무구분 검색조건 선택">
                  <c:forEach var="BO001List" items="${BO001}">
                    <option value="${BO001List.code}">${BO001List.codeNm}</option>
                  </c:forEach>
                </select>
                <label for="searchCondition" class="disp_none">검색어 입력 선택</label>
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 입력 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">배치ID</option>
                    <option value="B">배치명</option>
                    <option value="C">스케줄번호</option>
                    <option value="D">스케줄명</option>
                </select>
                <label for="searchKeyword" class="disp_none">검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                &nbsp;실행일자&nbsp;
                <label for="txtExecutSchdulDe" class="disp_none">실행일자 검색조건</label>
                <input type="text" name="txtExecutSchdulDe" id="txtExecutSchdulDe" size="10" maxlength="10" readonly="readonly">
                <a href="#LINK" onClick="fn_egov_NormalCalendar(parameter,'', parameter.txtExecutSchdulDe);">
                    <img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="실행일자 달력 팝업, 새창열림">
                </a>
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->
        
        <!-- List Start -->
        <div class="bbsList">
            <table  summary="배치I.D., Job이름(배치명), 업무구분, 스케줄번호, 스케줄명, 실행주기, 등록일자 순서로 나타낸 스케줄 목록 표입니다.">
                <caption>스케줄 목록</caption>
                <colgroup>
                    <col style="width:10%">
                    <col style="width:auto">
                    <col style="width:10%">
                    <col style="width:10%">
                    <col style="width:auto">
                    <col style="width:auto">
                    <col style="width:10%">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">배치ID</th>
                        <th scope="col">Job이름(배치명)</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">스케줄번호</th>
                        <th scope="col">스케줄명</th>
                        <th scope="col">실행주기</th>
                        <th scope="col">등록일자</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${schdulList}">
                    <tr>
                        <td align="center"><c:out value="${list.batchId}"         /></td>
                        <td align="left"  ><c:out value="${list.batchNm}"         /></td>
                        <td align="left"  ><c:out value="${list.jobSeCodeNm}"     /></td>
                        <td align="center"><c:out value="${list.schdulNo}"        /></td>
                        <td align="left"  >
                            <a href="javascript:fncSelect('${list.batchId}', '${list.batchNm}', '${list.schdulNo}', '${list.schdulNm}')" title="스케줄정보 입력 기능">
                                <c:out value="${list.schdulNm}" />
                            </a>
                        </td>
                        <td align="left"  ><c:out value="${list.strExecutCycle}"  /></td>
                        <td align="center"><c:out value="${list.frstRegistPnttm}" /></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty schdulList}">
                    <tr>
                        <td colspan="7"><c:out value="${message}"/></td>
                    </tr>
                  </c:if>
                </tbody>
            </table>
        </div>
        <!-- List End -->
        
        <!-- paging Start -->
        <div class="paging">
          <c:if test="${!empty parameter.pageIndex }">
            <ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
          </c:if>
        </div>
        <!-- paging End -->
        
    </div>
</form>

</body>
</html>