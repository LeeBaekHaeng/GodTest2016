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
  * @Class Name : EgovSchdulRegist.jsp
  * @Description : 스케줄관리 등록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.17  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.17
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
<!-- Callendar javascript -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

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
        
        objParam.batchId.value = "<c:out value="${batchInfo.batchId}"/>";
        objParam.batchNm.value = "<c:out value="${batchInfo.batchNm}"/>";
        
        objParam.returnURL.value = "<c:out value='${returnURL}'/>";
        objParam.loginId.value = "<c:out value='${loginId}'/>";
    });
    
    /*
        '목록' Button onClick 기능
    */
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSchdulList.do'/>";
        
        if ("Y" == "${schdul.searchUseYn}")
        {
            objParam.searchCondition.value = "${schdul.searchCondition}";
            objParam.searchKeyword.value = "${schdul.searchKeyword}";
            objParam.pageIndex.value = "${schdul.pageIndex}";
            objParam.jobSeCode.value = "${schdul.jobSeCode}";
        }
        
        objParam.submit();
    }
    
    /*
        '등록' Button onClick 기능
    */
    function fncInsert()
    {
        var strBatchId = objParam.batchId.value;
        var strSchdulNm = objParam.schdulNm.value;
        
        if (fncIsEmpty(strBatchId))
        {
            alert("스케줄 등록할 배치를 선택해 주세요");
            return;
        }
        
        if (fncIsEmpty(strSchdulNm))
        {
            alert("스케줄명을 입력해 주세요");
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
        
        var strCycleSe = document.getElementById("executCycle").value;
        
        if (strCycleSe == "B")
        {
            var objWeek = objParam.executSchdulWeek;
            
            if (objWeek && objWeek.length > 0)
            {
                document.getElementById("executSchdulDe").value = "";
                
                for (var index = 0; index < objWeek.length; index++)
                {
                    if (objWeek[index].checked)
                    {
                        document.getElementById("executSchdulDe").value += "1";
                    }
                    else
                    {
                        document.getElementById("executSchdulDe").value += "0";
                    }
                }
            }
        }
        else if (strCycleSe == "C")
        {
            document.getElementById("executSchdulDe").value = "000000" + document.getElementById("executSchdulDay").value;
        }
        else if (strCycleSe == "D")
        {
            document.getElementById("executSchdulDe").value = "0000" + document.getElementById("executSchdulMonth").value + document.getElementById("executSchdulDay").value;
        }
        else if (strCycleSe == "E")
        {
            var strYmd = document.getElementById("executSchdulYmd").value;
            document.getElementById("executSchdulDe").value = strYmd.substring(0, 4) + strYmd.substring(5, 7) + strYmd.substring(8, 10);
        }
        
        objParam.insertYn.value = "Y";
        objParam.action = "<c:out value='${executURL}'/><c:url value='/bopr/sim/insertSchdul.do' />";
        objParam.submit();
    }
    
    function fncSelectBatchId()
    {
        var url = "<c:url value='/bopr/sim/selectBatchInfoList.do?popupAt=Y'/>";
        window.open(url, "BatchListPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=600");
    }
    
    function fncBatchPopupCallback()
    {
        if (objParam.batchId.value.length > 0)
        {
            objParam.insertYn.value = "N";
            objParam.action = "<c:url value='/bopr/sim/insertSchdul.do' />";
            objParam.submit();
        }
    }
    
    function fncCycleSeChange()
    {
        var strCycleSe = document.getElementById("executCycle").value;
        
        var divB = document.getElementById("divCycleB");
        var divC = document.getElementById("divCycleC");
        var divD = document.getElementById("divCycleD");
        var divE = document.getElementById("divCycleE");
        
        divB.style.display = "none";
        divC.style.display = "none";
        divD.style.display = "none";
        divE.style.display = "none";
        
        if (strCycleSe == "B")
        {
            divB.style.display = "block";
        }
        else if (strCycleSe == "C")
        {
            divC.style.display = "block";
        }
        else if (strCycleSe == "D")
        {
            divC.style.display = "block";
            divD.style.display = "block";
        }
        else if (strCycleSe == "E")
        {
            divE.style.display = "block";
        }
    }
    
    function fncMonthChange()
    {
        var strMonth = document.getElementById("executSchdulMonth").value;
        var objDaySel = document.getElementById("executSchdulDay");
        
        if (strMonth == "02")
        {
            objDaySel.options[30] = null;
            objDaySel.options[29] = null;
            objDaySel.options[28] = null;
        }
        else if (strMonth == "04" || strMonth == "06" || strMonth == "09" || strMonth == "11")
        {
            objDaySel.options[30] = null;
            objDaySel.options[29] = null;
            objDaySel.options[28] = null;
            
            objDaySel.options.add(new Option("29", "29"), 28);
            objDaySel.options.add(new Option("30", "30"), 29);
        }
        else
        {
            objDaySel.options[30] = null;
            objDaySel.options[29] = null;
            objDaySel.options[28] = null;
            
            objDaySel.options.add(new Option("29", "29"), 28);
            objDaySel.options.add(new Option("30", "30"), 29);
            objDaySel.options.add(new Option("31", "31"), 30);
        }
    }
-->
</script>

<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">
    
        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <input type="hidden" name="insertYn" id="insertYn" value="N">
        <input type="hidden" name="pageIndex" value='1' />
        <input type="hidden" name="returnURL" id="returnURL">
        <input type="hidden" name="loginId" id="loginId">
        <!-- 달력 팝업 URL -->
        <input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>스케줄관리</h2>
        <div class="location">배치정보 &gt; 스케줄관리 &gt; <strong>등록</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="스케줄 목록 화면 이동">목록</a></span>
            <span class="bbsBtn"><a href="javascript:fncInsert()" title="스케줄 등록 기능">등록</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table summary="배치I.D., Job이름(배치명), 스케줄명, 실행주기, 파라미터 순서로 나타낸 스케줄 등록 데이터 표입니다." id="detailTable">
            <caption>스케줄 등록</caption>
            <colgroup>
                <col style="width:20%">
                <col style="width:auto">
                <col style="width:auto">
            </colgroup>
            <tbody>
                <tr>
                    <th colspan="2"><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시">배치ID</th>
                    <td>
                        <label for="batchId" class="disp_none">배치I.D.</label>
                        <input type="text" id="batchId" name="batchId" size="40" title="배치ID" readonly="readonly">
                        <a href="javascript:fncSelectBatchId();" style="selector-dummy:expression(this.hideFocus=false);" title="배치조회팝업, 새창열림">
                            <img src="<c:url value='/images/egovframework/com/cmm/search2.gif' />" width="15" height="15" style="vertical-align: middle" alt="배치조회팝업, 새창열림">
                        </a>
                    </td>
                </tr>
                <tr>
                    <th colspan="2"><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시">Job이름(배치명)</th>
                    <td>
                        <label for="batchNm" class="disp_none">Job이름(배치명)</label>
                        <input type="text" id="batchNm" name="batchNm" size="40" title="Job이름(배치명)" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th colspan="2"><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시">스케줄명</th>
                    <td>
                        <label for="schdulNm" class="disp_none">스케줄명</label>
                        <input type="text" id="schdulNm" name="schdulNm" size="40" title="스케줄명">
                    </td>
                </tr>
                <tr>
                    <th style="border-right:0px"><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시">실행주기</th>
                    <th></th>
                    <td>
                        <div id="divCycleSe" style="float:left">
                            <label for="executCycle" class="disp_none">실행주기 선택</label>
                            <select id="executCycle" name="executCycle" onchange="fncCycleSeChange()" title="실행주기 선택">
                                <option value="A" selected="selected">매일</option>
                                <option value="B">매주</option>
                                <option value="C">매월</option>
                                <option value="D">매년</option>
                                <option value="E">1회실행</option>
                            </select>
                            <input type='hidden' name='executSchdulDe' id='executSchdulDe'>
                        </div>
                        <div id="divCycleE" style="display:none;float:left">
                            <label for="executSchdulYmd" class="disp_none">실행일자</label>
                            <input type="text" name="executSchdulYmd" id="executSchdulYmd" size="10" maxlength="10" readonly="readonly" title="실행일자">
                            <a href="#LINK" onClick="fn_egov_NormalCalendar(parameter,'', parameter.executSchdulYmd);">
                                <img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="실행일자 달력 팝업, 새창열림">
                            </a>
                            <div><form:errors path="evlDe"/></div>
                        </div>
                        <div id="divCycleB" style="display:none;float:left">
                            <label for="sun" class="disp_none">실행요일 일요일 선택</label>
                            <input type="checkbox" id="sun" name="executSchdulWeek" value="0">일요일
                            <label for="mon" class="disp_none">실행요일 월요일 선택</label>
                            <input type="checkbox" id="mon" name="executSchdulWeek" value="1">월요일
                            <label for="tue" class="disp_none">실행요일 화요일 선택</label>
                            <input type="checkbox" id="tue" name="executSchdulWeek" value="2">화요일
                            <label for="wed" class="disp_none">실행요일 수요일 선택</label>
                            <input type="checkbox" id="wed" name="executSchdulWeek" value="3">수요일
                            <label for="thu" class="disp_none">실행요일 목요일 선택</label>
                            <input type="checkbox" id="thu" name="executSchdulWeek" value="4">목요일
                            <label for="fri" class="disp_none">실행요일 금요일 선택</label>
                            <input type="checkbox" id="fri" name="executSchdulWeek" value="5">금요일
                            <label for="sat" class="disp_none">실행요일 토요일 선택</label>
                            <input type="checkbox" id="sat" name="executSchdulWeek" value="6">토요일
                        </div>
                        <div id="divCycleD" style="display:none;float:left">
                            <label for="executSchdulMonth" class="disp_none">실행월</label>
                            <select id="executSchdulMonth" onchange="fncMonthChange()">
                              <c:forEach var="i" begin="1" end="12" step="1">
                                <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                                <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
                              </c:forEach>
                            </select>월
                        </div>
                        <div id="divCycleC" style="display:none;float:left">
                            <label for="executSchdulDay" class="disp_none">실행일</label>
                            <select id="executSchdulDay">
                              <c:forEach var="i" begin="1" end="31" step="1">
                                <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                                <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
                              </c:forEach>
                            </select>일
                        </div>
                        <div id="divCycleA" style="display:block;float:left">
                            <label for="executSchdulHour" class="disp_none">실행시간</label>
                            <select id="executSchdulHour" name="executSchdulHour">
                              <c:forEach var="i" begin="0" end="23" step="1">
                                <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                                <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
                              </c:forEach>
                            </select>시
                            <label for="executSchdulMnt" class="disp_none">실행분</label>
                            <select id="executSchdulMnt" name="executSchdulMnt">
                              <c:forEach var="i" begin="0" end="59" step="1">
                                <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                                <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
                              </c:forEach>
                            </select>분
                            <label for="executSchdulSecnd" class="disp_none">실행초</label>
                            <select id="executSchdulSecnd" name="executSchdulSecnd">
                              <c:forEach var="i" begin="0" end="59" step="1">
                                <c:if test="${i < 10}"><option value="0${i}">0${i}</option></c:if>
                                <c:if test="${i >= 10}"><option value="${i}">${i}</option></c:if>
                              </c:forEach>
                            </select>초
                        </div>
                    </td>
                </tr>
              <c:forEach var="list" items="${paramtrList}" varStatus="cnt">
                <tr>
                  <c:if test="${cnt.index == 0}">
                    <th id="paramtrRow" rowspan="<c:out value="${cnt.count}"/>">파라미터</th>
                  </c:if>
                    <th style="text-align:right;">
                        <input type="hidden" id="paramNo" name="paramNo" value="<c:out value="${cnt.index}"/>">
                        <input type="hidden" id="paramtrNm<c:out value="${cnt.index}"/>" name="paramtrNm<c:out value="${cnt.index}"/>" value="<c:out value="${list.paramtrNm}"/>">
                        <img src="/images/egovframework/bopr/blt4.gif" alt="중요표시"><c:out value="${list.paramtrNm}"/>
                        <script language="javascript">
                        <!--
                            document.getElementById("paramtrRow").rowSpan = "<c:out value='${cnt.count+1}'/>";
                        -->
                        </script>
                    </th>
                    <td>
                        <label for="paramtr<c:out value="${cnt.index}"/>" class="disp_none">파라미터 <c:out value="${list.paramtrNm}"/> 입력</label>
                        <input type="text" id="paramtr<c:out value="${cnt.index}"/>" name="paramtr<c:out value="${cnt.index}"/>" size="60">
                    </td>
                </tr>
              </c:forEach>
              <c:if test="${not empty paramtrList}">
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