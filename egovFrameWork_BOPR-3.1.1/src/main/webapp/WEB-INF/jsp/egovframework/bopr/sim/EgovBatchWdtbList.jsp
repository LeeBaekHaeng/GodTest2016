<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchWdtbList.jsp
  * @Description : 배치배포 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.06  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.06
  *  @version 0.9
  */
%>

<c:set var="strSearchCondition" value="${empty parameter.searchCondition ? '' : parameter.searchCondition}" />
<c:set var="strWdtbAt" value="${parameter.wdtbAt=='' ? '' : parameter.wdtbAt}" />
<c:set var="strJobSeCode" value="${parameter.jobSeCode=='' ? '' : parameter.jobSeCode}" />

<!-- 공통 Util Javscript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
    /* form 객체 */
    var objParam;

    /*
        화면 OnLoad
    */
    $(document).ready(function()
    {
        objParam = document.getElementById('parameter');

        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 form.hidden에 설정 - 검색 조건 유지
        */
        objParam.searchCondition.value = '<c:out value="${parameter.searchCondition}"/>';  // searchCondition
        objParam.searchKeyword.value   = '<c:out value="${parameter.searchKeyword}"/>';    // searchKeyword
        objParam.searchUseYn.value     = '<c:out value="${parameter.searchUseYn}"/>';      // searchUseYn - 검색 조건 걸어 조회 한 경우 Y
        objParam.wdtbAt.value          = '<c:out value="${parameter.wdtbAt}"/>';           // wdtbAt
        objParam.jobSeCode.value       = '<c:out value="${parameter.jobSeCode}"/>';        // jobSeCode
        objParam.pageIndex.value       = '<c:out value="${parameter.pageIndex}"/>';        // pageIndex
	      		
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        document.getElementById("selSearchCondition").value = '<c:out value="${strSearchCondition}"/>';	// selSearchCondition
        fncSearchConditionChange();																									// txtSearchCondition
        objParam.selWdtbAt.value                            = '<c:out value="${strWdtbAt}"/>';
        objParam.selJobSeCode.value                         = '<c:out value="${strJobSeCode}"/>';
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
        '조회' Button onClick - 신규 목록 조회
    */
    function fncSelectNewList()
    {	        	
        var searchConditionValue = document.getElementById("selSearchCondition").value;    // Search Condition
        var searchKeywordValue   = document.getElementById("txtSearchKeyword").value;      // Search Keyword

        objParam.searchCondition.value = searchConditionValue;
        objParam.searchKeyword.value   = searchKeywordValue;

        objParam.wdtbAt.value          = objParam.selWdtbAt.value;
        objParam.jobSeCode.value       = objParam.selJobSeCode.value;
	        	
        objParam.pageIndex.value= "1";    // Page Index 초기화

        /* 배치배포 목록 조회 url 호출 */
        fncSelectList();
    }

    /*
        searchKeyword 입력 form에서 Keydown Event
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
        fncSelectList();                      // 배치배포 목록 조회 호출
    }

    /*
        배치배포 목록 조회 url 호출 - fncSelectNewList(), linkPage() 에서 공통으로 사용
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectBatchWdtbList.do'/>";
        objParam.submit();
    }

    /*
        배치명 클릭 시 상세정보 화면 호출 기능
    */
    function fncSelect(jobDlbrtNo, batchDlbrtNo)
    {
        objParam.jobDlbrtNo.value   = jobDlbrtNo;
        objParam.batchDlbrtNo.value = batchDlbrtNo;
        objParam.action = "<c:url value='/bopr/sim/selectBatchWdtb.do'/>";
        objParam.submit();
    }
-->
</script>

<form id="parameter" name="parameter" method="post" action="">

<!-- Parameter Start -->
    <!-- 배치배포 상세정보 화면 호출 위한 parameter -->
    <input type="hidden" name="jobDlbrtNo" id="jobDlbrtNo">
    <input type="hidden" name="batchDlbrtNo" id="batchDlbrtNo">

    <!-- 목록 조회 조건 설정 위한 parameter -->
    <input type="hidden" name="searchCondition" id="searchCondition">
    <input type="hidden" name="searchKeyword" id="searchKeyword">
    <input type="hidden" name="wdtbAt" id="wdtbAt">
    <input type="hidden" name="jobSeCode" id="jobSeCode">

    <!-- 배치배포 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
    <input type="hidden" name="searchUseYn" id="searchUseYn">

    <!-- 페이징 처리 위한 parameter -->
    <input type="hidden" name="pageIndex" value='1'>
<!-- Parameter End -->

    <div class="contsBody" >

        <!-- Title Start -->
        <h2>배치배포관리</h2>
        <div class="location">배치심의 &gt; 배치배포관리 &gt; <strong>목록</strong></div>
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
                <label for="selWdtbAt" class="disp_none">배포여부 검색조건 선택</label>
                <select id="selWdtbAt" name="selWdtbAt" class="serSel" style="" title="배포여부 검색조건 선택">
                    <option value="" >배포여부선택</option>
                    <option value="N">배포대기</option>
                    <option value="Y">배포완료</option>
                </select>
                <label for="selSearchCondition" class="disp_none">검색어 입력 선택</label>
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">업무심의번호</option>
                    <option value="B">배치심의번호</option>
                    <option value="C">배치ID</option>
                    <option value="D">Job이름(배치명)</option>
                </select>
                <label for="txtSearchKeyword" class="disp_none" >검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->

        <!-- List Start -->
        <div class="bbsList">
            <table id="listTab" summary="업무심의번호, 배치심의번호, 배치ID, 업무구분, Job이름(배치명), 배포구분, 배포여부 순서로 배치배포 목록을 나타낸 표입니다">
                <caption>배치배포 목록</caption>
                <colgroup>
                    <col style="width:13%" >
                    <col style="width:13%" >
                    <col style="width:13%" >
                    <col style="width:13%" >
                    <col style="width:auto">
                    <col style="width:13%" >
                    <col style="width:13%" >
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">업무심의번호</th>
                        <th scope="col">배치심의번호</th>
                        <th scope="col">배치ID</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">Job이름(배치명)</th>
                        <th scope="col">배포구분</th>
                        <th scope="col">배포여부</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${batchWdtbList}">
                    <tr>
                        <td align="center"><c:out value="${list.jobDlbrtNo}"   /></td>
                        <td align="center"><c:out value="${list.batchDlbrtNo}" /></td>
                        <td align="center"><c:out value="${list.batchId}"      /></td>
                        <td align="center"><c:out value="${list.jobSeCodeNm}"  /></td>
                        <td align="center">
                            <a href="javascript:fncSelect('${list.jobDlbrtNo}', '${list.batchDlbrtNo}')" title="배치배포 상세화면 호출"><c:out value="${list.batchNm}" /></a>
                        </td>
                        <td align="center"><c:out value="${list.processSeCode}"/></td>
                        <td align="center"><c:out value="${list.wdtbAt}"       /></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty batchWdtbList}">
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