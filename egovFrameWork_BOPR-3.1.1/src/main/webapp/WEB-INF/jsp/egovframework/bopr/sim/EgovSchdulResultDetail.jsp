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
  * @Class Name : EgovSchdulResultDetail.jsp
  * @Description : 일정 실행 결과 상세 조회 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.09.06  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.09.06
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
    
    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        objParam = document.getElementById("parameter");
    });
    
    /*
        '목록' Button onClick
    */
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSchdulResultList.do'/>";
        
        if ("Y" == "${schdulResult.searchUseYn}")
        {
            objParam.searchCondition.value = "${schdulResult.searchCondition}";
            objParam.searchKeyword.value   = "${schdulResult.searchKeyword}";
            objParam.pageIndex.value       = "${schdulResult.pageIndex}";
            
            objParam.jobSeCode.value = "${schdulResult.jobSeCode}";
        }
        
        objParam.submit();
    }
-->
</script>
    
<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">
    
        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="pageIndex" value='1'>
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>스케줄결과관리</h2>
        <div class="location">배치정보 &gt; 스케줄결과관리 &gt; <strong>상세정보</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="스케줄결과 목록 화면 이동">목록</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table  summary="배치I.D., Job이름(배치명), 업무구분, 일정번호, 일정명, 일정결과번호, 잡실행I.D., 시작일시, 종료일시, 처리결과, 에러내용 순서로 나타낸 스케줄결과 상세정보 표입니다.">
                <caption>스케줄결과 상세정보</caption>
                <colgroup>
                    <col style="width:20%">
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <th>배치ID</th>
                        <td>
                            <c:out value="${schdulResult.batchId}" />
                        </td>
                    </tr>
                    <tr>
                        <th>Job이름(배치명)</th>
                        <td>
                            <c:out value="${schdulResult.batchNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>업무구분</th>
                        <td>
                            <c:out value="${schdulResult.jobSeCodeNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>일정번호</th>
                        <td>
                            <c:out value="${schdulResult.schdulNo}" />
                        </td>
                    </tr>
                    <tr>
                        <th>일정명</th>
                        <td>
                            <c:out value="${schdulResult.schdulNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>일정결과번호</th>
                        <td>
                            <c:out value="${schdulResult.schdulResultNo}" />
                        </td>
                    </tr>
                    <tr>
                        <th>잡실행ID</th>
                        <td>
                            <c:out value="${schdulResult.jobExecutionId}" />
                        </td>
                    </tr>
                    <tr>
                        <th>시작일시</th>
                        <td>
                            <c:out value="${schdulResult.startTime}" />
                        </td>
                    </tr>
                    <tr>
                        <th>종료일시</th>
                        <td>
                            <c:out value="${schdulResult.endTime}" />
                        </td>
                    </tr>
                    <tr>
                        <th>처리결과</th>
                        <td>
                            <c:out value="${schdulResult.processResult}" />
                        </td>
                    </tr>
                    <tr>
                        <th>에러내용</th>
                        <td>
                            <pre><c:out value="${schdulResult.errorCn}" /></pre>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- Contents End -->
        
    </div>
</form>