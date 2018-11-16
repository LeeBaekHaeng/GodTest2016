<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchInfoList.jsp
  * @Description : 배치정보 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.13  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.13
  *  @version 0.9
  */
%>

<!-- 공통 Util Javscript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<script type="text/javaScript" language="javascript" defer="defer">
<!--
    /* parameter form */
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
        objParam.jobSeCode.value       = '${parameter.jobSeCode}';          // jobSeCode
        objParam.pageIndex.value       = "${parameter.pageIndex}";
        
        /*
            검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
            - 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        */
        objParam.selSearchCondition.value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
        fncSearchConditionChange();
        objParam.selJobSeCode.value       = '${(parameter.jobSeCode=="") ? "" : parameter.jobSeCode}';
        
        fncCallAlert("<c:out value='${alertMssage}'/>");    // alert Message
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
        var searchConditionValue = document.getElementById("selSearchCondition").value;     // List Search Condition
        var searchKeywordValue   = document.getElementById("txtSearchKeyword").value;       // List Search Keyword

        objParam.searchCondition.value = searchConditionValue;
        objParam.searchKeyword.value   = searchKeywordValue;
        objParam.jobSeCode.value       = objParam.selJobSeCode.value;

        objParam.pageIndex.value= "1";    // Page Index 초기화

        /* 배치정보 목록 조회 url 호출 */
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
        objParam.pageIndex.value = pageNo;    // 페이지Index 클릭한 번호로 설정
        fncSelectList();                      // 배치정보 목록 조회 호출
    }

    /*
        배치정보 목록 조회 url 호출 - fncSelectNewList(), linkPage() 에서 공통으로 사용
    */
    function fncSelectList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectBatchInfoList.do'/>";
        objParam.submit();
    }

    /*
        배치명 클릭 시 상세정보 화면 호출 기능
    */
    function fncSelect(batchId)
    {
        objParam.batchId.value = batchId;
        objParam.action = "<c:url value='/bopr/sim/selectBatchInfo.do' />";
        objParam.submit();
    }

    /*
        '등록' Button onClick - 배치 등록 화면 호출
    */
    function fncRegist()
    {
        objParam.action = "<c:url value='/bopr/sim/insertBatchInfo.do' />";
        objParam.submit();
    }

    /*
        '삭제' Button onClick - 배치 정보 삭제 기능 호출
    */
    function fncDelete()
    {
        /* 체크된 항목 존재 여부 확인하고 체크된 항목의 batchId들을 objParam.batchIds에 설정 */
        if (!fncChecked())
        {
            return;
        }
        
        var url = "<c:url value='/bopr/sim/selectFtpPasswordPopup.do' />";
        window.open(url, "FTPPasswordPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=400, height=120");
    }
    
    function fncFtpPasswordCallback()
    {
    	objParam.action = "<c:url value='/bopr/sim/deleteBatchInfo.do'/>";	// 배치삭제 호출 URL
        objParam.submit();
    }

    /*
        다중 선택 처리 기능
        - 체크된 항목 존재하면 'true' 반환, 없으면 'false' 반환
        - 선택된 데이터의 배치ID를 ',' 구분자로 연결하여 objParam.batchIds에 설정
    */
    function fncChecked()
    {
        var checks = document.getElementsByName("checks");		// 체크박스 객체
        var checkCnt = 0;										// 체크된 항목 개수. 초기값=0
        var batchIds = "";										// 체크된 항목의 배치ID. 초기값=""
        
        if (checks)		// 체크박스 객체 존재 여부 확인
        {
            if (checks.length > 0)		// 체크박스 객체 유효 여부 확인
            {
                for (var index = 0; index < checks.length; index++)		// 체크박스 수 만큼 LOOP
                {
                    if (checks[index].checked)		// index 번째의 체크박스가 체크 된 경우
                    {
                        checkCnt++;										// 체크된 항목 개수 +1
                        batchIds += "," + checks[index].value;	// 체크된 항목의 배치ID 기록. 구분자=","
                    }
                }
            }
            
            if (checkCnt == 0)		// 체크된 항목이 없는 경우 false return
            {
                alert("선택된 데이터가 없습니다.");
                return false;
            }
            else
            {
                if (confirm(checkCnt + "개의 항목을 삭제하시겠습니까?"))	// 삭제 확인 메시지 return=[확인:true, 취소:false]
                {
                    objParam.batchIds.value = batchIds;
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            alert("조회된 데이터가 없습니다.");
            return false;
        }
    }

    function fncCheckAll(objCheck)
    {
        var objChecks = document.getElementsByName("checks");
        
        for (var index = 0; index < objChecks.length; index++)
        {
            objChecks[index].checked = objCheck.checked;
        }
    }
-->
</script>
    
<form id="parameter" name="parameter" method="post" action="">

    <!-- Parameter Start -->
    <!-- 배치배포 상세정보 화면 호출 위한 parameter -->
    <input type="hidden" name="batchId" id="batchId">
    <input type="hidden" name="batchIds" id="batchIds">
    
    <!-- 목록 조회 조건 설정 위한 parameter -->
    <input type="hidden" name="searchCondition" id="searchCondition">
    <input type="hidden" name="searchKeyword" id="searchKeyword">
    <input type="hidden" name="jobSeCode" id="jobSeCode">
    
    <!-- 배치배포 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
    <input type="hidden" name="searchUseYn" id="searchUseYn">
    
    <!-- 페이징 처리 위한 parameter -->
    <input type="hidden" name="pageIndex" value='1'>
    
    <!-- FTP 비밀번호 parameter -->
        <input type="hidden" name="ftpPassword" id="ftpPassword">
    <!-- Parameter End -->
    
    <div class="contsBody">
    
        <!-- Title Start -->
        <h2>배치정보관리</h2>
        <div class="location">배치정보 &gt; 배치정보관리 &gt; <strong>목록</strong></div>
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
                <select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 선택" onchange="fncSearchConditionChange()">
                    <option value="" >검색조건선택</option>
                    <option value="A">배치ID</option>
                    <option value="B">Job이름(배치명)</option>
                </select>
                <label for="txtSearchKeyword" class="disp_none">검색어 입력</label>
                <input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력란" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">
                <input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
            </fieldset>
        </div>
        <!-- 검색영역 End -->
        
        <!-- 버튼 Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncRegist()" title="배치정보 등록화면 호출">등록</a></span>
            <span class="bbsBtn"><a href="javascript:fncDelete()" title="배치정보 삭제 기능, 새창열림">삭제</a></span>
        </div>
        <!-- 버튼 End -->
        
        <!-- List Start -->
        <div class="bbsList">
            <table id="listTab" summary="배치ID, 업무구분, Job이름(배치명), 배치설명, 온라인실행여부 순서로 배치정보 목록을 나타낸 표입니다.">
                <caption>배치정보 목록</caption>
                <colgroup>
                    <col style="width:5%">
                    <col style="width:15%">
                    <col style="width:15%">
                    <col style="width:auto">
                    <col style="width:auto">
                    <col style="width:15%">
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">
                            <label for="checkAll" class="disp_none">목록 전체 선택/해제</label>
                            <input type="checkbox" name="checkAll" id="checkAll" class="check2" onclick="javascript:fncCheckAll(this)">
                        </th>
                        <th scope="col">배치ID</th>
                        <th scope="col">업무구분</th>
                        <th scope="col">Job이름(배치명)</th>
                        <th scope="col">배치설명</th>
                        <th scope="col">온라인실행여부</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="list" items="${batchInfoList}" varStatus="cnt">
                    <tr>
                        <td>
                            <label for="check${cnt.count}" class="disp_none">목록 선택/해제</label>
                            <input type="checkbox" id="check${cnt.count}" name="checks" value="${list.batchId}">
                        </td>
                        <td align="center"><c:out value="${list.batchId}" /></td>
                        <td align="center"><c:out value="${list.jobSeCodeNm}" /></td>
                        <td align="center">
                            <a href="javascript:fncSelect('${list.batchId}')" title="배치정보 상세화면 호출"><c:out value="${list.batchNm}"  /></a>
                        </td>
                        <td align="center" style="text-align:left;"><c:out value="${list.batchDc}" /></td>
                        <td align="center"><c:out value="${list.onlineExecutAt}"  /></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty batchInfoList}">
                    <tr>
                        <td colspan="6"><c:out value="${mssage}"/></td>
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