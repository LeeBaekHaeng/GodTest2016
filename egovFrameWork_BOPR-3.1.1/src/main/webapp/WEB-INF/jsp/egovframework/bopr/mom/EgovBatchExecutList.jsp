<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchExecut.jsp
  * @Description : 배치실행 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.18  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.18
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
        objParam.pageIndex.value       = "${parameter.pageIndex}";
        
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        document.getElementById("selSearchCondition").value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
        fncSearchConditionChange();
        objParam.selJobSeCode.value = '${parameter.jobSeCode}';
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
        배치정보 목록 조회 호출
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/mom/selectBatchExecutList.do'/>";	// 배치실행 목록 호출 URL
        objParam.submit();
    }
    
    /*
        '조회' 버튼 클릭 기능
    */
    function fncSelectNewList()
    {
        objParam.searchCondition.value = objParam.selSearchCondition.value;		// 조회조건 설정
        objParam.searchKeyword.value = objParam.txtSearchKeyword.value;			// 조회Keyword 설정
        
        objParam.jobSeCode.value = objParam.selJobSeCode.value;
        
        objParam.pageIndex.value= "1";													// 페이지Index '1'로 설정
        
        fncSelectList();																			// 배치실행 목록 조회 호출
    }
    
    /*
        Page Link 기능
    */
    function linkPage(pageNo)
    {
        // 조회조건, 조회Keyword는 이전 조회시 설정 되었던 것을 그대로 사용
        objParam.pageIndex.value = pageNo;		// 페이지Index 클릭한 번호로 설정
        fncSelectList();									// 배치실행 목록 조회 호출
    }
    
    function fncInsert(batchId)
    {
        objParam.batchId.value = batchId;
        objParam.onlineExecutAt.value = "Y";
        objParam.action = "<c:url value='/bopr/mom/insertBatchExecut.do' />";
        objParam.submit();
    }
    
    function fncSelectByEnter()
    {
        if (event.keyCode==13)
        {
            fncSelectNewList();
        }
    }
-->
</script>

<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">

        <!-- Parameter Start -->
        <!-- 일정결과 상세정보 화면 호출 위한 parameter -->
        <input type="hidden" name="batchId" id="batchId">
        <input type="hidden" name="onlineExecutAt" id="onlineExecutAt" value="Y">
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <!-- 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
        <input type="hidden" name="searchUseYn" id="searchUseYn">
        <!-- 페이징 처리 위한 parameter -->
        <input type="hidden" name="pageIndex" value='1'>
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>배치실행관리</h2>
        <div class="location">배치운영 &gt; 배치실행관리 &gt; <strong>목록</strong></div>
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
                <label for="selSearchCondition" class="disp_none">검색어 입력 선택</label>
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 입력 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">배치ID</option>
                    <option value="B">Job이름(배치명)</option>
                </select>
                <label for="txtSearchKeyword" class="disp_none">검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->
        
        <!-- List Start -->
        <div class="bbsList">
            <table summary="배치ID, 업무구분, Job이름(배치명), 등록일자, 최종수정일자, 실행 순서로 배치실행 목록을 나타낸 표입니다.">
                <caption>배치실행 목록</caption>
                <colgroup>
                    <col style="width:20%">
                    <col style="width:10%">
                    <col style="width:auto">
                    <col style="width:10%">
                    <col style="width:10%">
                    <col style="width:10%">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">배치ID</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">Job이름(배치명)</th>
                        <th scope="col">등록일자</th>
                        <th scope="col">최종수정일자</th>
                        <th scope="col">실행</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${batchExecutList}">
                    <tr>
                        <td align="center"><c:out value="${list.batchId}"         /></td>
                        <td align="left"  ><c:out value="${list.jobSeCodeNm}"     /></td>
                        <td align="left"  ><c:out value="${list.batchNm}"         /></td>
                        <td align="center"><c:out value="${list.frstRegistPnttm}" /></td>
                        <td align="center"><c:out value="${list.lastUpdtPnttm}"   /></td>
                        <td align="center"><span class="bbsBtn"><a href="javascript:fncInsert('<c:out value='${list.batchId}' />')" title="배치실행 등록화면 호출">실행</a></span></td>
                    </tr>
                  </c:forEach>
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