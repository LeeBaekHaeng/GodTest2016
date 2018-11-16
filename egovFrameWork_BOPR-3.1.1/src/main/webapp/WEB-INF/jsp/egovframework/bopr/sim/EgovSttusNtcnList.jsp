<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovSttusNtcnList.jsp
  * @Description : Job상태알림관리 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.16  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.16
  *  @version 0.9
  */
%>

<!-- 공통 Util Javscript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

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
        objParam.searchCondition.value = '${parameter.searchCondition}';	// searchCondition
        objParam.searchKeyword.value   = '${parameter.searchKeyword}';		// searchKeyword
        objParam.searchUseYn.value     = '${parameter.searchUseYn}';		// searchUseYn - 검색 조건 걸어 조회 한 경우 Y
        objParam.jobSeCode.value       = '${parameter.jobSeCode}';
        objParam.eventCode.value       = '${parameter.eventCode}';
        objParam.pageIndex.value       = "${parameter.pageIndex}";
        
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        document.getElementById("selSearchCondition").value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
        fncSearchConditionChange();
        objParam.selJobSeCode.value = '${parameter.jobSeCode}';
        objParam.selEventCode.value = '${parameter.eventCode}';
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
        objParam.searchCondition.value = objParam.selSearchCondition.value;    // Search Condition
        objParam.searchKeyword.value = objParam.txtSearchKeyword.value;        // Search Keyword
        
        objParam.jobSeCode.value = objParam.selJobSeCode.value;    // jobSeCode
        objParam.eventCode.value = objParam.selEventCode.value;
        
        objParam.pageIndex.value= "1";    // Page Index 초기화
        
        /* 목록 조회 url 호출 */
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
        objParam.pageIndex.value = pageNo;					// 페이지Index 클릭한 번호로 설정
        fncSelectList();									// 배치정보 목록 조회 호출
    }
    
    /*
        알림 목록 조회 호출
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSttusNtcnList.do'/>";	// 알림목록 호출 URL
        objParam.submit();
    }
    
    /*
        '등록' Button onClick - 등록 화면 호출
    */
    function fncInsert()
    {
        objParam.action = "<c:url value='/bopr/sim/insertSttusNtcn.do' />";
        objParam.submit();
    }
    
    /*
        알림명 클릭 시 상세정보 화면 호출 기능
    */
    function fncSelect(ntcnNo)
    {
        objParam.ntcnNo.value = ntcnNo;
        objParam.action = "<c:url value='/bopr/sim/selectSttusNtcn.do'/>";
        objParam.submit();
    }
-->
</script>
    
<form id="parameter" name="parameter" method="post" action="">

    <!-- Parameter Start -->
    <!-- 상세정보 화면 호출 위한 parameter -->
    <input type="hidden" name="ntcnNo" id="ntcnNo">
    
    <!-- 목록 조회 조건 설정 위한 parameter -->
    <input type="hidden" name="searchCondition" id="searchCondition">
    <input type="hidden" name="searchKeyword" id="searchKeyword">
    <input type="hidden" name="jobSeCode" id="jobSeCode">
    <input type="hidden" name="eventCode" id="eventCode">
    
    <!-- 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
    <input type="hidden" name="searchUseYn" id="searchUseYn">
    
    <!-- 페이징 처리 위한 parameter -->
    <input type="hidden" name="pageIndex" value='1'>
    <!-- Parameter End -->
    
    <div class="contsBody">
    
        <!-- Title Start -->
        <h2>Job상태알림관리</h2>
        <div class="location">배치정보 &gt; Job상태알림관리 &gt; <strong>목록</strong></div>
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
                <label for="selEventCode" class="disp_none">이벤트 검색조건 선택</label>
                <select id="selEventCode" name="selEventCode" class="serSel" style="" title="이벤트 검색조건 선택">
                  <c:forEach var="BO008List" items="${BO008}">
                    <option value="${BO008List.code}">${BO008List.codeNm}</option>
                  </c:forEach>
                </select>
                <label for="selSearchCondition" class="disp_none">검색어 입력 선택</label>
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 입력 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">배치ID</option>
                    <option value="B">스케줄번호</option>
                    <option value="C">알림번호</option>
                    <option value="D">알림제목</option>
                </select>
                <label for="txtSearchKeyword" class="disp_none">검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->
        
        <!-- 버튼 Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncInsert()" title="Job상태알림 등록 기능">등록</a></span>
        </div>
        <!-- 버튼 End -->
        
        <!-- List Start -->
        <div class="bbsList">
            <table  summary="배치ID, 업무구분, 스케줄번호, 알림번호, 알림제목, 이벤트, 알림종류, 등록일자 순서로 Job상태알림 목록을 나타낸 표입니다.">
                <caption>Job상태알림 목록</caption>
                <colgroup>
                    <col style="width:10%" >
                    <col style="width:10%">
                    <col style="width:10%" >
                    <col style="width:13%" >
                    <col style="width:auto">
                    <col style="width:10%" >
                    <col style="width:10%" >
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">배치ID</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">스케줄번호</th>
                        <th scope="col">알림번호</th>
                        <th scope="col">알림제목</th>
                        <th scope="col">이벤트</th>
                        <th scope="col">알림종류</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${ntcnList}">
                    <tr>
                        <td align="center">${list.batchId}</td>
                        <td align="center">${list.jobSeCodeNm}</td>
                        <td align="center">${list.schdulNo}</td>
                        <td align="center">${list.ntcnNo}</td>
                        <td align="left">
                            <a href="javascript:fncSelect('${list.ntcnNo}')" title="Job상태알림 상세정보 화면 호출">${list.ntcnSj}</a>
                        </td>
                        <td align="center">${list.eventCodeNm}</td>
                        <td align="center">${list.ntcnCode}</td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty ntcnList}">
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