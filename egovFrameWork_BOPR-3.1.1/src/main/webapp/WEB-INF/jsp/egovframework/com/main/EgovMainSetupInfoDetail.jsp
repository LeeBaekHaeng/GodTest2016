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
   * @Class Name : EgovUserDetail.jsp
   * @Description : 사용자 상세정보 화면
   * @Modification Information
   * @
   * @  수정일      수정자            수정내용
   * @ -------        --------    ---------------------------
   * @ 2012.07.12   김지완          최초 생성
   *
   *  @user 배치운영환경 김지완
   *  @since 2012.07.12
   *  @version 1.0 
   *  @see
   */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메인화면 설정</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
 
<script type="text/javaScript" language="javascript">

function fncSelectBatchDlbrtList() {
	var varFrom = document.getElementById("mainSetup");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtConfmList.do'/>";
	varFrom.submit();       
}

function fncBatchDlbrtInsert() {

	var jobSeCodeaaa = "";
	var stringJob = document.getElementsByName("jobSeCodeTemp");
	
	for(var i=0;i<stringJob.length;i++){
		if(stringJob[i].checked==true){
			
			if(i==0){
				jobSeCodeaaa = stringJob[i].value;
			}else{
				jobSeCodeaaa += ":" + stringJob[i].value;	
			}
		}
	}
	
	document.getElementById("jobSeCode").value = jobSeCodeaaa; 
    var varFrom = document.getElementById("mainSetup");
    varFrom.action = "<c:url value='/main/EgovMainSetupInfoInsert.do'/>";
    varFrom.submit();

}


function fncBatchDlbrtUpdate() {
	
	var jobSeCodeaaa = "";
	var stringJob = document.getElementsByName("jobSeCodeTemp");
	
	for(var i=0;i<stringJob.length;i++){
		if(stringJob[i].checked==true){
			
			if(i==0){
				jobSeCodeaaa = stringJob[i].value;
			}else{
				jobSeCodeaaa += ":" + stringJob[i].value;	
			}
		}
	}
	
	document.getElementById("jobSeCode").value = jobSeCodeaaa; 
	
	var varFrom = document.getElementById("mainSetup");
	varFrom.action = "<c:url value='/main/EgovMainSetupInfoUpdate.do'/>";
    varFrom.submit(); 

}

function fncBatchDlbrtDelete() {
	var varFrom = document.getElementById("mainSetup");
	varFrom.action = "<c:url value='/main/EgovMainSetupInfoDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fncBatchWdtbInsert() {
	location.replace("<c:url value='/egovframework/bam/EgovBatchWdtbInsertView.do'/>");
}

function fnChecked(){
	
	var tempCode = document.getElementById("jobSeCode").value;
	
	var tempSplit = tempCode.split(":");
	for(var i=0;i<tempSplit.length;i++){
		if(null != tempSplit[i] && "" != tempSplit[i]){
			document.getElementById(tempSplit[i]).checked=true;			
		}
	}
	
}
 
</script>
</head>
 
<body onload="fnChecked()">
<form:form commandName="mainSetup" id="mainSetup" method="post" >
<input type="hidden" name="jobSeCode" id="jobSeCode" value="<c:out value='${main.jobSeCode}'/>">

<div class="contsBody">
	<h2>메인화면 설정</h2>
	<div class="location">메인화면 설정 > <strong>메인화면설정 상세정보</strong></div>
	
	<c:if test="${mainFalg == 'INSERT'}">
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncBatchDlbrtInsert()">등록</a></span>
	</div>
	</c:if>
	<c:if test="${mainFalg == 'UPDATE'}">
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn"><a href="javascript:fncBatchDlbrtDelete()">삭제</a></span>
	</div>
	</c:if>

	<div class="bbsDetail">
		<table  summary="메인화면의 설정정보를 제공하는 화면입니다.">
		<caption>메인화면 설정내용</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />리프레쉬주기</th>
			<td>
				<label for="refreshCycle" class="disp_none">리프레쉬주기</label> 
				<select name="refreshCycle" id="refreshCycle">
		            <option value="" <c:if test="${main.refreshCycle == ''}">selected="selected"</c:if> >없음</option>
		            <option value="60000" <c:if test="${main.refreshCycle == '60000'}">selected="selected"</c:if> >1분</option>
		            <option value="300000" <c:if test="${main.refreshCycle == '300000'}">selected="selected"</c:if> >5분</option>
		            <option value="600000" <c:if test="${main.refreshCycle == '600000'}">selected="selected"</c:if> >10분</option>   
		            <option value="900000" <c:if test="${main.refreshCycle == '900000'}">selected="selected"</c:if> >15분</option>
		            <option value="1200000" <c:if test="${main.refreshCycle == '1200000'}">selected="selected"</c:if> >20분</option>
		        </select>
	        </td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />스케줄주기</th>
			<td> 
				<label for="executCycle" class="disp_none">스케줄주기</label> 
				<select name="executCycle" id="executCycle">
		            <option value="A" <c:if test="${main.executCycle == 'A'}">selected="selected"</c:if> >매일</option>
		            <option value="B" <c:if test="${main.executCycle == 'B'}">selected="selected"</c:if> >매주</option>
		            <option value="C" <c:if test="${main.executCycle == 'C'}">selected="selected"</c:if> >매월</option>
		            <option value="" <c:if test="${main.executCycle == ''}">selected="selected"</c:if> >전체</option>   
		        </select>
	        </td>
		</tr>
		
		<tr>
			<th>업무구분</th>
			<td>
				<c:forEach var="cmmCodeDetail" items="${cmmCode}" varStatus="status">
			    	<input name="jobSeCodeTemp" id="${cmmCodeDetail.code}" type="checkbox" value="<c:out value='${cmmCodeDetail.code}'/>" /><c:out value='${cmmCodeDetail.codeNm}'/></input>
			    </c:forEach>
    		</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />목록건수</th>
			<td>
				<label for="listCount" class="disp_none">목록건수</label> 
				<select name="listCount" id="listCount">
		            <option value="3" <c:if test="${main.listCount == '3'}">selected="selected"</c:if> >3건</option>
		            <option value="5" <c:if test="${main.listCount == '5'}">selected="selected"</c:if> >5건</option>
		            <option value="10" <c:if test="${main.listCount == '10'}">selected="selected"</c:if> >10건</option>
		        </select>
			</td>
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />배치상태</th>
			<td>
				<label for="batchSttus" class="disp_none">배치상태</label> 
				<select name="batchSttus" id="batchSttus">
		            <option value="" <c:if test="${main.batchSttus == ''}">selected="selected"</c:if> >전체</option>
		            <option value="ING" <c:if test="${main.batchSttus == 'ING'}">selected="selected"</c:if> >진행중</option>
		            <option value="COMPLETED" <c:if test="${main.batchSttus == 'COMPLETED'}">selected="selected"</c:if> >완료</option>
		        </select>
			</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

</form:form>
    
</body>
</html>

