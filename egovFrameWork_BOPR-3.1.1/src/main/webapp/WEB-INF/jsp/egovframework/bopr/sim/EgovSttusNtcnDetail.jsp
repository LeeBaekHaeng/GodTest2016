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
  * @Class Name : EgovSttusNtcnDetail.jsp
  * @Description : Job상태알림관리 상세 조회 화면
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
    var userCount = 1;
	
    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        objParam = document.getElementById("parameter");
        
        objParam.batchId.value       = "<c:out value='${ntcn.batchId}'/>";
        objParam.batchNm.value       = "<c:out value='${ntcn.batchNm}'/>";
        objParam.jobSeCodeNm.value   = "<c:out value='${ntcn.jobSeCodeNm}'/>";
        objParam.schdulNo.value      = "<c:out value='${ntcn.schdulNo}'/>";
        objParam.schdulNm.value      = "<c:out value='${ntcn.schdulNm}'/>";
        objParam.ntcnNo.value        = "<c:out value='${ntcn.ntcnNo}'/>";
        objParam.ntcnSj.value        = "<c:out value='${ntcn.ntcnSj}'/>";
        objParam.eventCode.value     = "<c:out value='${ntcn.eventCode}'/>";
        objParam.mssageNm.value      = "<c:out value='${ntcn.mssageNm}'/>";
        objParam.remark.value            = "<c:out value='${ntcn.remark}'/>";
        objParam.frstRegistPnttm.value = "<c:out value='${ntcn.frstRegistPnttm}'/>";
        
      <c:forEach var="list" items="${ntcnCode}">
        document.getElementById("<c:out value='${list}'/>").checked = "checked";
      </c:forEach>
      
      fncCallAlert('<c:out value="${alertMssage}"/>');
    });
    
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectSttusNtcnList.do'/>";
        objParam.eventCode.value = "";
        
        if ("Y" == "${parameter.searchUseYn}")
        {
            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value = "${parameter.searchKeyword}";
            objParam.pageIndex.value = "${parameter.pageIndex}";
            objParam.jobSeCode.value = "${parameter.jobSeCode}";
            objParam.eventCode.value = "${parameter.eventCode}";
        }
        else
        {
        	objParam.jobSeCode.value = "${parameter.jobSeCode}";
            objParam.eventCode.value = "${parameter.eventCode}";
        }
        
        objParam.submit();
    }
    
    function fncUpdate()
    {
        if (fncInsertValidation())
        {
            objParam.action = "<c:url value='/bopr/sim/updateSttusNtcn.do'/>";

            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value = "${parameter.searchKeyword}";
            objParam.pageIndex.value = "${parameter.pageIndex}";
            
            objParam.submit();
        }
    }
    
    function fncInsertValidation()
    {
        if (fncIsEmpty(document.getElementById("batchId").value))
        {
            alert("배치ID는 필수입력 사항입니다.");
            return false;
        }
        
        if (fncIsEmpty(document.getElementById("ntcnSj").value))
        {
            alert("알림제목은 필수입력 사항입니다.");
            return false;
        }
        
        if (fncIsEmpty(document.getElementById("eventCode").value))
        {
            alert("이벤트는 필수입력 사항입니다.");
            return false;
        }
        
        var ntcnCodeChecked = false;
        
        for (var index = 0; index < document.getElementsByName("ntcnCodes").length; index++)
        {
            if (document.getElementsByName("ntcnCodes")[index].checked)
            {
                ntcnCodeChecked = true;
            }
        }
        
        if (!ntcnCodeChecked)
        {
            alert("알림종류는 필수입력 사항입니다.");
            return false;
        }
        
        if (document.getElementsByName("recptnUserId") == null || document.getElementsByName("recptnUserId").length <= 0)
        {
            alert("수신자는 필수입력 사항입니다.");
            return false;
        }
        else
        {
        	var recptnUserIds = document.getElementsByName("recptnUserId");
        	
        	for (var index1 = 0; index1 < recptnUserIds.length; index1++)
        	{
        		var userId1 = recptnUserIds[index1].value;
        		
        		for (var index2 = index1 + 1; index2 < recptnUserIds.length; index2++)
        		{
        			if (userId1 == recptnUserIds[index2].value)
        			{
        				alert("같은 수신자를 두 번 입력할 수 없습니다.");
        				return;
        			}
        		}
        	}
        }
        
        if (fncIsEmpty(document.getElementById("mssageNm").value))
        {
            alert("메시지명은 필수입력 사항입니다.");
            return false;
        }
        
        return true;
    }
    
    function fncDelete()
    {
        objParam.action = "<c:url value='/bopr/sim/deleteSttusNtcn.do'/>";
        objParam.submit();
    }
    
    function fncSelectRecptnUser()
    {
        var url = "<c:url value='/bopr/uam/EgovUserManagePopUp.do'/>";
        window.open(url, "UserListPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=640, height=600");
    }
    
    function fncUserManagePopupCallBack()
    {
        if (fncIsEmpty(document.getElementById("userId").value))
        {
            return;
        }
        
        var rowIndex = document.getElementById("recptnRow").rowIndex;
        var objTable= document.getElementById("ntcnTable");
        var objRow = objTable.insertRow(rowIndex + userCount);
        userCount++;
        document.getElementById("recptnHead").rowSpan = userCount;
        objRow.insertCell(0).innerHTML = document.getElementById("userId").value + "<input type='hidden' name='recptnUserId' value='" + document.getElementById("userId").value + "'/>";;
        objRow.insertCell(1).innerHTML = document.getElementById("userNm").value;
        objRow.insertCell(2).innerHTML = document.getElementById("moblPhon").value;
        objRow.insertCell(3).innerHTML = document.getElementById("email").value;
        objRow.insertCell(4).innerHTML = "<input type='button' value='삭제' onclick='fncUserDelete(\"" + document.getElementById("userId").value + "\")'/>";
        objRow.id = document.getElementById("userId").value;
        
        document.getElementById("userId").value = "";
    }
    
    function fncUserDelete(userId)
    {
        document.getElementById("ntcnTable").deleteRow(document.getElementById(userId).rowIndex);
        userCount--;
        document.getElementById("recptnHead").rowSpan = userCount;
    }
-->
</script>
    
<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">
    
        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="pageIndex" value='1'>
        <!-- 달력 팝업 URL -->
        <input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>">
        <!-- 사용자 조회 팝업 callback 처리 위한 parameter -->
        <input type="hidden" name="userId" id="userId">
        <input type="hidden" name="userNm" id="userNm">
        <input type="hidden" name="moblPhon" id="moblPhon">
        <input type="hidden" name="email" id="email">
        <!-- Parameter End -->
        
        <!-- Title Start -->
        <h2>Job상태알림관리</h2>
        <div class="location">배치정보 &gt; Job상태알림관리 &gt; <strong>상세정보</strong></div>
        <!-- Title End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="Job상태알림 목록 화면 이동">목록</a></span>
            <span class="bbsBtn"><a href="javascript:fncUpdate()" title="Job상태알림 수정 기능">수정</a></span>
            <span class="bbsBtn"><a href="javascript:fncDelete()" title="Job상태알림 삭제 기능">삭제</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table  summary="배치I.D., Job이름(배치명), 업무구분, 스케줄번호, 스케줄명, 알림번호, 알림제목, 이벤트, 알림종류, 수신자, 메시지명, 메시지설명, 비고, 등록일자의 순서로 나타낸 Job상태알림 상세정보 표입니다." id="ntcnTable">
                <caption>Job상태알림 상세정보</caption>
                <colgroup>
                    <col style="width:20%">
                    <col style="width:15%">
                    <col style="width:15%">
                    <col style="width:15%">
                    <col style="width:auto">
                    <col style="width:10%">
                </colgroup>
                <tbody>
                    <tr>
                        <th>배치ID</th>
                        <td colspan="5">
                            <label for="batchId" class="disp_none">배치I.D.</label>
                            <input type="text" id="batchId" name="batchId" size="40" title="배치ID" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>Job이름(배치명)</th>
                        <td colspan="5">
                            <label for="batchNm" class="disp_none">Job이름(배치명)</label>
                            <input type="text" id="batchNm" name="batchNm" size="40" title="Job이름(배치명)" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>업무구분</th>
                        <td colspan="5">
                            <label for="jobSeCodeNm" class="disp_none">업무구분</label>
                            <input type="text" id="jobSeCodeNm" name="jobSeCodeNm" size="40" title="업무구분" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>스케줄번호</th>
                        <td colspan="5">
                            <label for="schdulNo" class="disp_none">스케줄번호</label>
                            <input type="text" id="schdulNo" name="schdulNo" size="40" title="스케줄번호" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>스케줄명</th>
                        <td colspan="5">
                            <label for="schdulNm" class="disp_none">스케줄명</label>
                            <input type="text" id="schdulNm" name="schdulNm" size="40" title="스케줄명" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>알림번호</th>
                        <td colspan="5">
                            <label for="ntcnNo" class="disp_none">알림번호</label>
                            <input type="text" id="ntcnNo" name="ntcnNo" size="40" title="알림번호" readonly="readonly">
                        </td>
                    </tr>
                    <tr>
                        <th>알림제목</th>
                        <td colspan="5">
                            <label for="ntcnSj" class="disp_none">알림제목</label>
                            <input type="text" id="ntcnSj" name="ntcnSj" size="40" title="알림제목">
                        </td>
                    </tr>
                    <tr>
                        <th>이벤트</th>
                        <td colspan="5">
                            <label for="eventCode" class="disp_none">이벤트 선택</label>
                            <select id="eventCode" name="eventCode" title="이벤트 선택">
                                <option value="A1">실행완료</option>
                                <option value="A2">실행오류</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>알림종류</th>
                        <td colspan="5">
                            <label for="SMS" class="disp_none">알림종류 SMS 선택</label>
                            <input type="checkbox" id="SMS" name="ntcnCodes" value="SMS">SMS
                            <label for="E-Mail" class="disp_none">알림종류 E-Mail 선택</label>
                            <input type="checkbox" id="E-Mail" name="ntcnCodes" value="E-Mail">E-Mail
                        </td>
                    </tr>
                    <tr id="recptnRow">
                        <th id="recptnHead">
                            수신자&nbsp;
                            <label for="recptnBtn" class="disp_none">수신자 추가 버튼</label>
                            <input type="button" id="recptnBtn" value="추가" onclick="fncSelectRecptnUser()" title="수신자 추가 버튼">
                        </th>
                        <th>사용자ID</th>
                        <th>사용자명</th>
                        <th>휴대폰번호</th>
                        <th>이메일</th>
                        <th>삭제</th>
                    </tr>
                  <c:forEach var="recptnList" items="${ntcn.sttusNtcnRecptnVOList}" varStatus="cnt">
                    <tr id="${recptnList.recptnUserId}">
                        <td>
                            ${recptnList.recptnUserId}
                            <input type='hidden' name='recptnUserId' value='${recptnList.recptnUserId}'>
                            <script type="text/javaScript" language="javascript">
                            <!--
                                document.getElementById('recptnHead').rowSpan = "${cnt.count+1}";
                                userCount++;
                            -->
                            </script>
                        </td>
                        <td>${recptnList.recptnUserNm}</td>
                        <td>${recptnList.moblphonNo}</td>
                        <td>${recptnList.emails}</td>
                        <td><input type="button" value="삭제" onclick="fncUserDelete('${recptnList.recptnUserId}')"></td>
                    </tr>
                  </c:forEach>
                    <tr>
                        <th>메시지명</th>
                        <td colspan="5">
                            <label for="mssageNm" class="disp_none">메시지명</label>
                            <input type="text" id="mssageNm" name="mssageNm" size="60" title="메시지명">
                        </td>
                    </tr>
                    <tr>
                        <th>메시지설명</th>
                        <td colspan="5">
                            <label for="mssageDc" class="disp_none">메시지설명</label>
                            <textarea rows="5" cols="60" id="mssageDc" name="mssageDc"><c:out value="${ntcn.mssageDc}"/></textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>비고</th>
                        <td colspan="5">
                            <label for="remark" class="disp_none">비고</label>
                            <input type="text" id="remark" name="remark" size="60" title="비고">
                        </td>
                    </tr>
                    <tr>
                        <th>등록일자</th>
                        <td colspan="5">
                            <label for="frstRegistPnttm" class="disp_none">등록일자</label>
                            <input type="text" id="frstRegistPnttm" name="frstRegistPnttm" size="40" title="등록일자" readonly="readonly">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- Contents End -->
        
    </div>
</form>