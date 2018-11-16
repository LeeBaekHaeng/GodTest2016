<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovRehndnList.jsp
  * @Description : 재처리관리 목록 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.18  이병권    최초 생성
  * @ 2012.09.04  유현웅   결과 관리로 인한 화면 변경
  *
  *  @author SDS 이병권
  *  @since 2012.07.18
  *  @version 0.9
  */
%>

<script type="text/javaScript" language="javascript" defer="defer">
     function fncCheckAll() {
         var checkField = document.rehndn.delYn;
         if(document.rehndn.checkAll.checked) {
             if(checkField) {
                 if(checkField.length > 1) {
                     for(var i=0; i < checkField.length; i++) {
                         checkField[i].checked = true;
                     }
                 } else {
                     checkField.checked = true;
                 }
             }
         } else {
             if(checkField) {
                 if(checkField.length > 1) {
                     for(var j=0; j < checkField.length; j++) {
                         checkField[j].checked = false;
                     }
                 } else {
                     checkField.checked = false;
                 }
             }
         }
     }

     function fncManageChecked() {

         var checkField = document.rehndn.delYn;
         var checkId = document.rehndn.checkId;
         var returnValue = "";

         var returnBoolean = false;
         var checkCount = 0;

         if(checkField) {
             if(checkField.length > 1) {
                 for(var i=0; i<checkField.length; i++) {
                     if(checkField[i].checked) {
                         checkField[i].value = checkId[i].value;
                         if(returnValue == "")
                             returnValue = checkField[i].value;
                         else 
                     	    returnValue = returnValue + ";" + checkField[i].value;
                         checkCount++;
                     }
                 }
                 if(checkCount > 0)  {
                	 
                	 if(confirm("[" + checkCount + "]건 삭제하시겠습니까?")) {
                		 
	                     returnBoolean = true;
                	 } else {
                		 
                		 returnBoolean = false;
                	 }
                 }
                 else {
                     alert("선택된 재실행결과 목록이 없습니다.");
                     returnBoolean = false;
                 }
             } else {
                 if(document.rehndn.delYn.checked == false) {
                     alert("선택된 재실행결과 목록이 없습니다.");
                     returnBoolean = false;
                 }
                 else {
                	 
					if(confirm("[1]건 삭제하시겠습니까?")) {
						
						returnValue = checkId.value;
						returnBoolean = true;
					} else {
					 
						returnBoolean = false;
					}
                 }
             }
         } else {
             alert("조회된 결과가 없습니다.");
         }

         document.rehndn.rehndnNos.value = returnValue;

         return returnBoolean;
     }
     function fncRehndnListDelete()
     {
     	if(fncManageChecked()){
     		
   			document.rehndn.action = "<c:url value='/bopr/mom/EgovRehndnListDelete.do'/>";
   			document.rehndn.submit();
     	}
     }
     
     function fncSelect(rehndnNo)
     {
 	    document.rehndn.rehndnNo.value = rehndnNo;

 	    document.rehndn.action = "<c:url value='/bopr/mom/selectRehndn.do'/>";
 	    document.rehndn.submit();
     }
     function fncSelectRehndnList(pageNo)
     {
     	var searchCondition = document.rehndn.searchCondition.value;
     	var searchKeyword = document.rehndn.searchKeyword.value;

     	var flag = 0;
     	document.rehndn.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "");
     	if(searchCondition!="" && searchKeyword==""){
     		alert("검색어를 입력하세요");
     		flag = 1;
     	}
     	if(flag==0){
     		document.rehndn.pageIndex.value = pageNo;
             document.rehndn.action = "<c:url value='/bopr/mom/selectRehndnList.do'/>";
             document.rehndn.submit();
     	}else{
     		return;
     	}
     }
     function linkPage(pageNo){
         document.rehndn.pageIndex.value = pageNo;
         document.rehndn.action = "<c:url value='/bopr/mom/selectRehndnList.do'/>";
         document.rehndn.submit();
     }
     function press() {
         if (event.keyCode==13) {
         	fncSelectRehndnList('1');
         }
     }
     
     function fncChangeCondition(){
     	if(document.rehndn.searchCondition.value == ''){
     		document.rehndn.searchKeyword.value="검색조건을 선택하세요";
     		document.rehndn.searchKeyword.disabled="disabled";
     	}else{
     		document.rehndn.searchKeyword.value="";
     		document.rehndn.searchKeyword.disabled="";
     	}
     }

     </script>
<form name="rehndn" action="<c:url value='/bopr/mom/selectRehndnList.do'/>" method="post">
<div class="contsBody">
	<h2>재처리결과 관리</h2>
	<div class="location">배치운영 > 재처리결과 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchResultCode" class="disp_none">상태 검색조건 선택</label>
			<select id="searchResultCode" name="searchResultCode" class="serSel" style="" title="상태 검색조건 선택">
				<option value="">상태선택</option>
				<option value="COMPLETED" <c:if test="${rehndnVO.searchResultCode == 'COMPLETED'}"> selected </c:if>>Completed</option>
				<option value="FAILED" <c:if test="${rehndnVO.searchResultCode == 'FAILED'}"> selected </c:if>>Failed</option>
				<option value="STOPPED" <c:if test="${rehndnVO.searchResultCode == 'STOPPED'}"> selected </c:if>>Stopped</option>
			</select>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${rehndnVO.searchCondition == '1'}"> selected </c:if>>재처리수행번호</option>    
				<option value="2" <c:if test="${rehndnVO.searchCondition == '2'}"> selected </c:if>>배치ID</option>
				<option value="3" <c:if test="${rehndnVO.searchCondition == '3'}"> selected </c:if>>배치명</option>
				<option value="4" <c:if test="${rehndnVO.searchCondition == '4'}"> selected </c:if>>Job인스턴스ID</option>
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text"
				<c:if test="${rehndnVO.searchKeyword ne ''}">value="<c:out value='${rehndnVO.searchKeyword}'/>"</c:if>
				<c:if test="${rehndnVO.searchKeyword eq '' && rehndnVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if> 
			onkeyup="press();return false;" />                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectRehndnList('1');return false;"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a title="재처리결과 삭제" href="javascript:fncRehndnListDelete()">삭제</a></span>
	</div>

	<div class="bbsList">
		<table  summary="재처리수행번호, Job이름, 배치명, Job 인스턴스ID, 상태, 결과코드, 재처리시점 등의 재처리결과 목록입니다.">
		<caption>재처리 결과 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:15%" >
			<col style="width:20%" >
			<col style="width:15" >
			<col style="width:10%" >
			<col style="width:10%" >
			<col style="width:10%" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" name="checkAll" class="check2" onclick="javascript:fncCheckAll()"></th>
			<th scope="col">재처리수행번호</th>
			<th scope="col">JOB 이름</th>
			<th scope="col">배치명</th>
			<th scope="col">Job인스턴스ID</th>
			<th scope="col">상태</th>
			<th scope="col">결과코드</th>
			<th scope="col">재처리시점</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(rehndnList) == 0}">
		<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="rehndn" items="${rehndnList}" varStatus="status">
		<tr>
			<td><input type="checkbox" title="재처리결과 선택" name="delYn" class="check2"><input type="hidden" name="checkId" value="<c:out value="${rehndn.rehndnNo}"/>" /></td>
		    <td><a href="javascript:fncSelect('<c:out value="${rehndn.rehndnNo}"/>')"><c:out value="${rehndn.rehndnNo}"/></a></td>
		    <td><c:out value="${rehndn.batchId}"/></td>
		    <td><c:out value="${rehndn.batchNm}"/></td>
		    <td><c:out value="${rehndn.jobInstanceId}"/></td>
		    <td><c:out value="${rehndn.status}"/></td>	
		    <td><c:out value="${rehndn.exitCode}"/></td>	
		    <td><c:out value="${rehndn.rehndnPnttm}"/></td>	
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<div class="paging">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	<!-- //페이징 -->
	<input type="hidden" name="rehndnNo"/>
	<input type="hidden" name="rehndnNos"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${rehndnVO.pageIndex}'/>"/>
    <!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>
