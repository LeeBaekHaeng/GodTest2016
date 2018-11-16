<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovSchdulResultList.jsp
  * @Description : 일정 실행 결과 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.09.05  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.09.05
  *  @version 0.9
  */
%>

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
        objParam.searchCondition.value   = '${parameter.searchCondition}';    // searchCondition
        objParam.searchKeyword.value     = '${parameter.searchKeyword}';      // searchKeyword
        objParam.searchUseYn.value       = '${parameter.searchUseYn}';        // searchUseYn - 검색 조건 걸어 조회 한 경우 Y
        objParam.searchDe.value          = '${parameter.searchDe}';
        objParam.searchKeywordFrom.value = '${parameter.searchKeywordFrom}';
        objParam.searchKeywordTo.value   = '${parameter.searchKeywordTo}';
        objParam.jobSeCode.value         = '${parameter.jobSeCode}';
        objParam.pageIndex.value         = '${parameter.pageIndex}';
        
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        document.getElementById("selSearchCondition").value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
        objParam.selSearchDe.value = '${parameter.searchDe}';
        objParam.txtSearchKeywordFrom.value = '${parameter.searchKeywordFrom}';
        objParam.txtSearchKeywordTo.value = '${parameter.searchKeywordTo}';
        objParam.selJobSeCode.value = '${parameter.jobSeCode}';
        fncSearchConditionChange();
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
        일정 결과 상세정보 화면 호출
    */
    function fncSelect(schdulResultNo)
    {
        objParam.schdulResultNo.value = schdulResultNo;
        objParam.action = "<c:url value='/bopr/sim/selectSchdulResult.do'/>";
        objParam.submit();
    }
    
    /*
        일정 목록 조회 호출
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSchdulResultList.do'/>";
        objParam.submit();
    }
    
    /*
        '조회' 버튼 클릭 기능
    */
    function fncSelectNewList()
    {
        objParam.searchCondition.value = objParam.selSearchCondition.value;
        objParam.searchKeyword.value = objParam.txtSearchKeyword.value;
        
        objParam.jobSeCode.value = objParam.selJobSeCode.value;
        
        if (fncIsEmpty(objParam.selSearchDe.value))
        {
        	objParam.searchKeywordFrom.value = "";
        	objParam.searchKeywordTo.value = "";
        	objParam.searchDe.value = "";
        }
        else
        {
        	objParam.searchDe.value = objParam.selSearchDe.value;
        	
        	var strFrom = objParam.txtSearchKeywordFrom.value;
        	var strTo   = objParam.txtSearchKeywordTo.value;
        	
        	if (fncIsEmpty(strFrom))
        	{
        		if (fncIsEmpty(strTo))
        		{
        			objParam.searchDe.value = "";
        		}
        		else
        		{
        			strFrom = strTo;
        		}
        	}
        	else
        	{
        		if (fncIsEmpty(strTo))
        		{
        			strTo = strFrom;
        		}
        	}
        	
        	objParam.searchKeywordFrom.value = strFrom;
        	objParam.searchKeywordTo.value = strTo;
        }
        
        objParam.pageIndex.value= "1";
    
        fncSelectList();
    }
    
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
        document.getElementById("pageIndex").value = pageNo;
        fncSelectList();
    }
-->
</script>
    
<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">
    
        <!-- Parameter Start -->
        <!-- 일정결과 상세정보 화면 호출 위한 parameter -->
        <input type="hidden" name="schdulResultNo" id="schdulResultNo">
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="searchDe" id="searchDe">
        <input type="hidden" name="searchKeywordFrom" id="searchKeywordFrom">
        <input type="hidden" name="searchKeywordTo" id="searchKeywordTo">
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <!-- 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
        <input type="hidden" name="searchUseYn" id="searchUseYn">
        <!-- 페이징 처리 위한 parameter -->
        <input type="hidden" name="pageIndex" value='1'>
        <!-- 달력 팝업 URL -->
        <input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
        <div><form:errors path="evlDe"/></div>
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>스케줄결과관리</h2>
        <div class="location">배치정보 &gt; 스케줄결과관리 &gt; <strong>목록</strong></div>
        <!-- Title End -->
        
        <!-- 검색영역 Start -->
        <div class="search">
            <fieldset class="searchboxA">
                <legend>검색 영역</legend>
                <label for="selSearchDe" class="disp_none">일시 검색조건 선택</label>
                <select id="selSearchDe" name="selSearchDe" class="serSel" style="" title="일시 검색조건 선택">
                    <option value="">일자선택</option>
                    <option value="START_TIME">시작일자</option>
                    <option value="END_TIME">종료일자</option>
                </select>
                <label for="txtSearchKeywordFrom" class="disp_none">일시 검색조건 최소일자</label>
                <input type="text" name="txtSearchKeywordFrom" id="txtSearchKeywordFrom" size="10" maxlength="10" readonly="readonly">
                <a href="#LINK" onClick="fn_egov_NormalCalendar(parameter,'', parameter.txtSearchKeywordFrom);">
                    <img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="일시 검색조건 최소일자 달력 팝업, 새창열림">
                </a>
                ~
                <label for="txtSearchKeywordTo" class="disp_none">일시 검색조건 최대일자</label>
                <input type="text" name="txtSearchKeywordTo" id="txtSearchKeywordTo" size="10" maxlength="10" readonly="readonly">
                <a href="#LINK" onClick="fn_egov_NormalCalendar(parameter,'', parameter.txtSearchKeywordTo);">
                    <img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="일시 검색조건 최대일자 달력 팝업, 새창열림">
                </a>
                <label for="selJobSeCode" class="disp_none">업무구분 검색조건 선택</label>
                <select id="selJobSeCode" name="selJobSeCode" class="serSel" style="" title="업무구분 검색조건 선택">
                  <c:forEach var="BO001List" items="${BO001}">
                    <option value="${BO001List.code}">${BO001List.codeNm}</option>
                  </c:forEach>
                </select>
                <label for="selSearchCondition" class="disp_none">검색어 입력 선택</label>
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 입력 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">배치ID</option>
                    <option value="B">스케줄번호</option>
                    <!-- <option value="C">스케줄명</option> -->
                    <option value="D">스케줄결과번호</option>
                    <option value="E">처리결과</option>
                </select>
                <label for="txtSearchKeyword" class="disp_none">검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->
            
        <!-- List Start -->
        <div class="bbsList">
            <table summary="배치ID, 업무구분, 스케줄번호, 스케줄결과번호, 잡실행ID, 시작일시, 종료일시, 처리결과 순서로 스케줄결과 목록을 나타낸 표입니다.">
                <caption>스케줄결과 목록</caption>
                <colgroup>
                    <col style="width:13%">
                    <col style="width:10%">
                    <col style="width:13%">
                    <col style="width:13%">
                    <col style="width:13%">
                    <col style="width:15%">
                    <col style="width:15%">
                    <col style="width:8%">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">배치ID</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">스케줄번호</th>
                        <th scope="col">스케줄결과번호</th>
                        <th scope="col">잡실행ID</th>
                        <th scope="col">시작일시</th>
                        <th scope="col">종료일시</th>
                        <th scope="col">처리결과</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${schdulResultList}">
                    <tr>
                        <td align="center"><c:out value="${list.batchId}"         /></td>
                        <td align="center"><c:out value="${list.jobSeCodeNm}"     /></td>
                        <td align="center"><c:out value="${list.schdulNo}"        /></td>
                        <td align="left"  >
                            <a href="javascript:fncSelect('${list.schdulResultNo}')" title="스케줄결과 상세정보 화면 호출"><c:out value="${list.schdulResultNo}" /></a>
                        </td>
                        <td align="center"  ><c:out value="${list.jobExecutionId}"  /></td>
                        <td align="center"><c:out value="${list.startTime}" /></td>
                        <td align="center"><c:out value="${list.endTime}" /></td>
                        <td align="center"><c:out value="${list.processResult}" /></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty schdulResultList}">
                    <tr>
                        <td colspan="8"><c:out value="${message}"/></td>
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