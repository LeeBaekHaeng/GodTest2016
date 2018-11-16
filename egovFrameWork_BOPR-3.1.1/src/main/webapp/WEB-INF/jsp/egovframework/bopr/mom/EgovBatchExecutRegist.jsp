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
  * @Class Name : EgovBatchExecutRegist.jsp
  * @Description : 배치실행 등록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.18  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.18
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
    /* table */
    var objTable;
    /* parameter table Head table data */
    var objParamHead;
    
    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        /* parameter form */
        objParam = document.getElementById("parameter");
        
        /* detail table */
        objTable = document.getElementById("detailTable");
        
        /* parameter table Head table data */
        objParamHead = document.getElementById("tdParamtr");
        
        objParam.batchId.value = "<c:out value='${batchInfo.batchId}' />";
        objParam.batchNm.value = "<c:out value='${batchInfo.batchNm}' />";
        objParam.loginId.value = "<c:out value='${loginId}' />";
        objParam.returnURL.value = "<c:out value='${returnURL}'/>";
        
        if (fncCallAlert("<c:out value='${alertMssage}'/>"))
        {
        	alert(objParam.returnURL.value);
        	fncList();
        }
    });
    
    function fncList()
    {
        if ("Y" == "${parameter.searchUseYn}")
        {
            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value = "${parameter.searchKeyword}";
            objParam.pageIndex.value = "${parameter.pageIndex}";
            objParam.jobSeCode.value = "${parameter.jobSeCode}";
        }
        
        objParam.action = "<c:out value='${returnURL}'/><c:url value='/bopr/mom/selectBatchExecutList.do' />";
        objParam.submit();
    }
    
    function fncInsert()
    {
        var objBatchId = document.getElementById("batchId");
        
        if (objBatchId == null)
        {
            alert("배치ID는 필수입니다");
            return;
        }
        
        if (objBatchId.value == null || objBatchId.value == "")
        {
            alert("배치ID는 필수입니다");
            return;
        }
        
        var strParamNos = document.getElementsByName("paramNo");
        
        for (var index = 0; index < strParamNos.length; index++)
        {
            var strParam = strParamNos[index].value;
            var strParamtr = document.getElementById("paramtr" + strParam).value;
            var strParamtrNm = document.getElementById("paramtrNm" + strParam).value;
            
            if (fncIsEmpty(strParamtr))
            {
                alert(strParamtrNm + " 파라미터의 값을 입력하세요");
                return;
            }
        }
        
        objParam.action = "<c:out value='${executURL}'/><c:url value='/bopr/mom/insertBatchExecut.do' />";
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
        <input type="hidden" name="loginId" id="loginId">
        <input type="hidden" name="returnURL" id="returnURL">
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>배치실행관리</h2>
        <div class="location">배치운영 &gt; 배치실행관리 &gt; <strong>실행</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="배치실행 목록 화면 이동">목록</a></span>
            <span class="bbsBtn"><a href="javascript:fncInsert()" title="배치실행 기능">실행</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table id="detailTable" summary="배치I.D., Job이름(배치명), 파라미터 순서로 나타낸 배치실행 등록 데이터 표입니다.">
                <caption>배치실행 등록</caption>
                <colgroup>
                    <col style="width:20%">
                    <col style="width:auto">
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <th colspan="2"><img src="/images/egovframework/bopr/blt4.gif" alt="배치I.D. 필수입력" />배치ID</th>
                        <td>
                            <label for="batchId" class="disp_none">배치I.D.</label>
                            <input type="text" id="batchId" name="batchId" size="40" title="배치ID" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><img src="/images/egovframework/bopr/blt4.gif" alt="Job이름(배치명) 필수입력" />Job이름(배치명)</th>
                        <td>
                            <label for="batchNm" class="disp_none">Job이름(배치명)</label>
                            <input type="text" id="batchNm" name="batchNm" size="40" title="Job이름(배치명)" readonly="readonly">
                        </td>
                    </tr>
                  <c:forEach var="list" items="${batchInfo.paramtrList}" varStatus="cnt">
                    <tr>
                      <c:if test="${cnt.index == 0}">
                        <th id="paramtrRow" rowspan="<c:out value="${cnt.count}"/>">파라미터</th>
                      </c:if>
                        <th style="text-align:right;">
                            <input type="hidden" name="paramNo" value="<c:out value="${cnt.index}"/>">
                            <input type="hidden" id="paramtrNm<c:out value="${cnt.index}"/>" name="paramtrNm<c:out value="${cnt.index}"/>" value="<c:out value="${list.paramtrNm}"/>">
                            <img src="/images/egovframework/bopr/blt4.gif" alt="중요표시"><c:out value="${list.paramtrNm}"/>
                            <script type="text/javaScript" language="javascript">
                            <!--
                                document.getElementById("paramtrRow").rowSpan = '${cnt.count+1}';
                            -->
                            </script>
                        </th>
                        <td>
                            <label for="paramtr<c:out value="${cnt.index}"/>" class="disp_none">파라미터 <c:out value="${list.paramtrNm}"/> 입력</label>
                            <input type="text" id="paramtr<c:out value="${cnt.index}"/>" name="paramtr<c:out value="${cnt.index}"/>" size="60" maxlength="255" style="ime-mode:disabled;">
                        </td>
                    </tr>
                  </c:forEach>
                  <c:if test="${not empty batchInfo.paramtrList}">
                    <tr>
                        <th style="text-align:right;">example</th>
                        <td style="color:#0000EE;">
                            상대경로 = classpath:/egovframework/batch/data/inputs/txtData.txt<br>
                            절대경로 =  file:C:/workspace/egovframework/batch/data/inputs/txtData.txt
                        </td>
                    </tr>
                  </c:if>
                </tbody>
            </table>
        </div>
        <!-- Contents End -->
			
    </div>
</form>