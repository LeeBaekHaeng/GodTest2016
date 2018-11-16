<%--
  Class Name : EgovMainView.jsp 
  Description : 메인화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2012.08.31   LBO       배치운영환경 메인화면
 
    author   : 배치운영환경개발팀 JJY
    since    : 2012.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>표준프레임워크 배치운영환경 메인화면</title>
<!-- 
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/jquery-1.4.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/jquery-ui-1.8.4.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/i18n/grid.locale-en.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/jquery.jqGrid.min.js'/>"></script> 
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/jquery.timer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/jquery/jquery.elastic-1.6.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/common.ajax.js'/>"></script>
--> 
<!-- JQuery -->
 <script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>
 <!-- 정보알림이 표시를 위한 스크립트  -->
<script type="text/javascript">
var noi_url = "<c:url value='/uss/ion/noi/getNotifications.do'/>";
</script>
<!-- <script type="text/javascript" language="javaScript" src="<c:url value='/js/egovframework/com/uss/ion/noi/EgovNotification.js' />"></script> -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/LeftMenu.js'/>"/></script>

<script type="text/javascript">
/* ********************************************************
* 숫자  자리수에 맞게 0붙이기 함수
******************************************************** */
function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (var i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}
/* ********************************************************
 * 숫자 format 처리 함수
 **********************************************************/
function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

/* ********************************************************
 * Export 처리 함수
 **********************************************************/
function fn_egov_go_download( url ){
    var popupWin = fn_egov_go_open_popup("popup");

    document.listForm.target = "popup"; 
    document.listForm.action = url; 
    document.listForm.submit();

    popupWin.focus();
}

/* ********************************************************
 * 데이터를 실시간 리프레쉬 하는 함수
 ******************************************************** */
function fn_egov_ajaxRefresh() {
    
	//fn_egov_refresh();
	var refreshTime = document.getElementById("selectRefreshTime").value;
	//var refreshTime = "600000";	// 매 10분마다 리프레쉬
    if(refreshTime != ''){
    	
    	document.getElementById("selectRefreshTime").value = refreshTime;
    	document.listForm.refreshCycle.value = refreshTime;
    	
        //설정한 시간에 한번씩 로딩한다.
    	setTimeout("document.listForm.submit();" , refreshTime);
    	
    }
	
} 

  
/* ********************************************************
 * 리프레쉬  함수
 ******************************************************** */
function fn_egov_refresh() {
	//setTimeout("document.listForm.submit();" , "1");
	
	document.listForm.refreshCycle.value = document.getElementById("selectRefreshTime").value;
	
	document.listForm.action = "<c:url value='/main/Main.do'/>";
	document.listForm.submit();
}


function fn_goSchdul(){
	
	goLeftMenu("<c:url value='/bopr/sim/selectSchdulList.do?executYn=Y'/>", "batchInfo", "batchInfo_1");
}


function fncUpdateSetupInfo() {
	
    document.listForm.action = "<c:url value='/main/updateSetupInfo.do'/>";
    document.listForm.submit(); 
    
}

function fn_egov_go_rehndnJobList(startTime) {
	
	goLeftMenu('<c:url value="/bopr/mom/EgovRehndnJobList.do?startTime=' + startTime + '"/>', "batchOpr", "batchOpr_1");
}
 
$(document).ready(function() { 
	fn_egov_ajaxRefresh();
}); 
</script>

<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	
	<!-- container 시작 -->
	<div id="main_container">
		<form name="listForm" id="listForm" method="post" action='<c:url value="/main/Main.do"/>'>
        <input name="formSubmit"    type="hidden" value="전송" title="전송" class="hidden"/>
        <input name="executYn"       type="hidden" value="" id="executYn" />
        <input name="refreshCycle"   type="hidden" value="<c:out value='${mainVO.refreshCycle}'/>" id="refreshCycle" />
   		
 	<!-- content Start -->
	<div id="contents">
		<div class="BtnTOp">
		    <table align="right">
		    <tr>
		    	<td>RefreshTime&nbsp;</td>
			    <td>
			    	<label for="selectRefreshTime" class="disp_none">페이지새로고침시간</label>
				    <select name="selectRefreshTime" id="selectRefreshTime" onchange="javascript:fn_egov_ajaxRefresh();" title="ajaxRefreshTime">
			           	<option value="" <c:if test="${mainVO.refreshCycle == ''}">selected="selected"</c:if> >없음</option>
			            <option value="60000" <c:if test="${mainVO.refreshCycle == '60000'}">selected="selected"</c:if> >1분</option>
			            <option value="300000" <c:if test="${mainVO.refreshCycle == '300000'}">selected="selected"</c:if> >5분</option>
			            <option value="600000" <c:if test="${mainVO.refreshCycle == '600000'}">selected="selected"</c:if> >10분</option>   
			            <option value="900000" <c:if test="${mainVO.refreshCycle == '900000'}">selected="selected"</c:if> >15분</option>
			            <option value="1200000" <c:if test="${mainVO.refreshCycle == '1200000'}">selected="selected"</c:if> >20분</option>
				    </select>&nbsp;
				</td>
				<td>
					<a href="javascript:fn_egov_refresh();"><img align="top" src="/images/egovframework/bopr/btn_refrash.gif" alt="새로고침" /></a>&nbsp;
				</td>
				<td>
					<a href="javascript:fncUpdateSetupInfo();"><img align="top" src="/images/egovframework/bopr/btn_setting.gif" alt="설정" /></a>
				</td>
			</tr>
			</table>
		</div>
		<!-- 오늘의 할일/금일 실행예정 배치목록 start -->
		<div class="today_wrap">
			<!-- 오늘의 할일 -->
			<div class="metoday">
				<div class="today">
					<div class="left"><img src="/images/egovframework/bopr/new_main/img_metoday_left.gif" alt=""/></div>
					<div class="center">
						<h2>
							<span class="me_tit">오늘의 할일</span>
						</h2>
						<div class="today_list">
							<ul>
								<c:forEach var="result" items="${todoList}" varStatus="status">
									<li>
										<c:if test="${result.jobSeCode == '배치심의'}">
											<p class="today_list_jeb"><a href='javascript:goLeftMenu("/bopr/bam/EgovBatchDlbrtConfm.do?batchDlbrtNo=${result.jobDlbrtNo}","batchDlbrt","batchDlbrt_1")'><span>[배치심의]<c:out value="${result.jobDlbrtNm}" /></span></a></p>
										</c:if>
										<c:if test="${result.jobSeCode == '업무심의'}">
											<p class="today_list_jeb"><a href='javascript:goLeftMenu("/bopr/bam/EgovJobDlbrtConfm.do?jobDlbrtNo=${result.jobDlbrtNo}","batchDlbrt","batchDlbrt_1")'><span>[업무심의]<c:out value="${result.jobDlbrtNm}" /></span></a></p>
										</c:if>
										<p class="today_list_date"><c:out value="${result.jobUpdtPnttm}" /></p>
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="btn_more"><a href='javascript:goLeftMenu("/bopr/bam/EgovJobDlbrtList.do","batchDlbrt","batchDlbrt_1")'><img src="/images/egovframework/bopr/new_main/btn_more.gif" alt="오늘의 할일 더보기" /></a></div>
					</div>
					<div class="right"><img src="/images/egovframework/bopr/new_main/img_metoday_right.gif" alt=""/></div>
				</div>
			</div>
			<!-- 오늘의 할일 -->

			<!-- 금일 실행예정 배치목록 -->
			<div class="todayexe">
				<div class="exetoday">
					<div class="left"><img src="/images/egovframework/bopr/new_main/img_todayexe_left.gif" alt=""/></div>
					<div class="execenter">
						<h2>
							<span class="exe_tit">금일 실행예정 배치목록</span>
						</h2>
						<div class="exe_list">
							<ul>
								<c:forEach var="result" items="${batList}" varStatus="status">
									<li>
										<p class="exe_list_jeb"><span>[<c:out value="${result.jobSeCode}" />]</span>
											<span><c:out value="${result.batchNm}" /></span></p>
										<p class="exe_list_date"><c:out value="${result.batExecutTime}" /></p>
									</li>
								</c:forEach>
									<c:if test="${fn:length(batList) == 0}">
										  <li>
											<p class="exe_list_jeb"><span><spring:message code="main.bat.nodata.msg" /></span></p>  
										  </li>      
									</c:if>
							</ul>
						</div>
						<div class="btn_more"><a href="javascript:fn_goSchdul();"><img src="/images/egovframework/bopr/new_main/btn_more.gif" alt="오늘의 할일 더보기" /></a></div>
					</div>
					<div class="right"><img src="/images/egovframework/bopr/new_main/img_todayexe_right.gif" alt=""/></div>
				</div>
			</div>
		<!-- 금일 실행예정 배치목록 end -->
		</div>
		<!-- 오늘의 할일/금일 실행예정 배치목록 end -->

		<!-- 배치모니터링 -->
		<div class="maincontsBody">
		<h4>배치모니터링목록</h4>
			<div class="bbsListMa">
				<table  summary="Job이름, 시작시간, 현재상태, 종료시간, 종료시상태, step정보등을 확인하는 목록입니다.">
				<caption>배치모니터링 목록</caption>
				<colgroup>
					<col style="width:3%" >
					<col style="width:8%" >
					<col style="width:12%" >
					<col style="width:9%" >
					<col style="width:12%" >
					<col style="width:8%" >
					<col style="width:12%" >
					<col style="width:7%" >
					<col style="width:7%" >
					<col style="width:7%" >
					<col style="width:7%" >
					<col style="width:8%" >
				</colgroup>
				<thead>
				<tr>
					<th scope="col" rowspan="2"></th>
					<th scope="col" rowspan="2">Job이름</th>
					<th scope="col" rowspan="2">시작시간</th>
					<th scope="col" rowspan="2">현재상태</th>
					<th scope="col" rowspan="2">종료시간</th>
					<th scope="col" rowspan="2">종료시상태</th>
					<th scope="col" colspan="6" class="last">step정보</th>
				</tr>
				<tr>
					<th scope="col">step이름</th>
					<th scope="col">Read<br/>Count</th>
					<th scope="col">Write<br/>Count</th>
					<th scope="col">Commit<br/>Count</th>
					<th scope="col">Rollback<br/>Count</th>
					<th scope="col" class="last">종료상태</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${mainList}" varStatus="status">
					 	<tr>
						   	<td> 
						   		<c:if test="${result.jobStatus == 'FAILED'}">	
						    		<img src='<c:url value='/images/egovframework/bopr/status/red_16_16.gif'/>' alt=""/>		
						    	</c:if>
						    	<c:if test="${result.jobStatus == 'COMPLETED'}">	
						    		<img src='<c:url value='/images/egovframework/bopr/status/blue_16_16.gif'/>' alt=""/>		
						    	</c:if>
						    	<c:if test="${result.jobStatus == 'STARTED'}">	
						    		<img src='<c:url value='/images/egovframework/bopr/status/yellow_16_16.gif'/>' alt=""/>		
						    	</c:if>
						    	<c:if test="${result.jobStatus == 'STARTING'}">	
						    		<img src='<c:url value='/images/egovframework/bopr/status/yellow_16_16.gif'/>' alt=""/>		
						    	</c:if>				    
						    	<c:if test="${result.jobStatus == 'STOPPED'}">	
						    		<img src='<c:url value='/images/egovframework/bopr/status/gray_16_16.gif'/>' alt=""/>		
						    	</c:if>				    
							</td>
					 		<td>
					 		<a href='javascript:goLeftMenu("/bopr/mom/EgovExecutResult.do?jobExecutionId=${result.jobExecutionId}","batchOpr","batchOpr_1")'><c:out value="${result.batchNm}" /></a>
					 		</td>
					 		<td>
					 		    <c:out value="${result.jobStartTime}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.jobStatus}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.jobEndTime}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.jobExitCode}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.stepName}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.stepReadCount}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.stepWriteCount}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.stepCommitCount}" />
					 		</td>
					 		<td>
					 		    <c:out value="${result.stepRollbackCount}" />
					 		</td>
					 		<td class="last">
					 		    <c:out value="${result.stepExitCode}" />
					 		</td>
					 	</tr>
					</c:forEach>
						<c:if test="${fn:length(mainList) == 0}">
							<tr><td colspan="12">
								<spring:message code="main.nodata.msg" /></span>  
							  </td></tr>      
						</c:if>					
				</tbody>
				</table>
			</div>
		</div>
		<!-- //배치모니터링 -->
		
		<div class="monitoringList">
			<!-- 배치등록현황 모니터링 -->
			<div class="arrangementLeft">
				<h4>배치등록현황 모니터링</h4>
				<div class="bbsListMa">
					<table  summary="업무구분, 업무심의완료, 배치심의완료, 배치등록대기, 배치등록현황을 확인하는 목록입니다.">
					<caption>배치등록현황 모니터링 목록</caption>
					<colgroup>
						<col style="width:24%" >
						<col style="width:19%" >
						<col style="width:19%" >
						<col style="width:19%" >
						<col style="width:19%" >
					</colgroup>
					<thead>
					<tr>
						<th scope="col">업무구분</th>
						<th scope="col">업무심의완료</th>
						<th scope="col">배치심의완료</th>
						<th scope="col">배치등록대기</th>
						<th scope="col" class="last">배치등록현황</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${regList}" varStatus="status">
						 	<tr>
						 		<td>
						 		    <c:out value="${result.jobSeCode}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.jobCnCnt}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.batCnCnt}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.batWdtbCnt}" />
						 		</td>
						 		<td class="last">
						 		    <c:out value="${result.batRegCnt}" />
						 		</td>
						 	</tr>
						</c:forEach>
							<c:if test="${fn:length(regList) == 0}">
								<tr><td colspan="5">
									<spring:message code="main.nodata.msg" /></span>  
								  </td></tr>      
							</c:if>	
					</tbody>
					</table>
				</div>
			</div>
			<!-- //배치등록현황 모니터링 -->

			<!-- 배치실행현황 모니터링 -->
			<div class="arrangementRight">
				<h4>배치실행현황 모니터링</h4>
				<div class="bbsListMa">
					<table  summary="일자, 배치실행, 성공, 실패, 재처리가능 확인하는 목록입니다.">
					<caption>배치실행현황 모니터링 목록</caption>
					<colgroup>
						<col style="width:24%" >
						<col style="width:19%" >
						<col style="width:19%" >
						<col style="width:19%" >
						<col style="width:19%" >
					</colgroup>
					<thead>
					<tr>
						<th scope="col">일자</th>
						<th scope="col">배치실행</th>
						<th scope="col">성공</th>
						<th scope="col">실패</th>
						<th scope="col" class="last">재처리가능</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${exeList}" varStatus="status">
						 	<tr>
						 		<td>
						 		    <c:out value="${result.jobStartTime}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.completed + result.failed}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.completed}" />
						 		</td>
						 		<td>
						 		    <c:out value="${result.failed}" />
						 		</td>
						 		<td class="last">
						 			<font color="red">
						 				<a href='javascript:fn_egov_go_rehndnJobList("${result.jobStartTime}");'> <c:out value="${result.rehndn}"/></a>
						 			</font>
						 		</td>
						 	</tr>
						</c:forEach>
							<c:if test="${fn:length(exeList) == 0}">
								<tr><td colspan="5">
									<spring:message code="main.nodata.msg" /></span>
								  </td></tr>      
							</c:if>						
					</tbody>
					</table>
				</div>
			</div>
			<!-- //배치실행현황 모니터링 -->
		</div>
	</div>
	<!-- //content End -->
	</form>
	</div>
