<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchDlbrtManage.jsp
  * @Description : 배치심의요청 관리 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.16 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.16
  *  @version 1.0 
  *  @see
  */
%>

<!-- 달력팝업  -->
<script type="text/javascript" src="/js/egovframework/com/sym/cal/EgovCalPopup.js" ></script>

<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
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

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var checkCn = document.listForm.checkCn;
    var returnValue = "";

    var returnCn = true;
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
                    if(checkCn[i].value=="CN"||checkCn[i].value=="RT"||checkCn[i].value=="RE")
                    	returnCn = false;
                }
            }
            if(returnCn==false){
            	alert("이미 심의완료된 항목은 삭제할수 없습니다.");
            	return returnCn;
            }
            if(checkCount > 0) 
            	if(confirm("[" + checkCount + "]건 삭제하시겠습니까?")) {
	           		 returnBoolean = true;
	           	} else {
	           		 returnBoolean = false;
	           	}
            else {
                alert("선택된 배치심의목록이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 배치심의목록이 없습니다.");
                returnBoolean = false;
            }
            else {
            	if(checkCn.value=="CN"||checkCn.value=="RT"||checkCn.value=="RE"){
            		alert("이미 심의완료된 항목은 삭제할수 없습니다.");
                	return false;
            	}
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

    document.listForm.batchDlbrtNos.value = returnValue;
    
    return returnBoolean;
}

function fncChangeCondition(){
	if(document.listForm.searchCondition.value == ''){
		document.listForm.searchKeyword.value="검색조건을 선택하세요";
		document.listForm.searchKeyword.disabled="disabled";
	}else{
		document.listForm.searchKeyword.value="";
		document.listForm.searchKeyword.disabled="";
	}
}

function fncSelectBatchDlbrtList(pageNo){
	var searchCondition = document.listForm.searchCondition.value;
	var searchKeyword = document.listForm.searchKeyword.value;
	var searchKeywordFrom = document.listForm.searchKeywordFrom.value;
	var searchKeywordTo = document.listForm.searchKeywordTo.value;
	var flag = 0;
	document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "");
	if(searchCondition!="" && searchKeyword==""){
		alert("검색어를 입력하세요");
		flag = 1;
	}else if(searchKeywordFrom!="" && searchKeywordTo!=""){
		if(searchKeywordFrom>searchKeywordTo){
			alert("검색시작일이 검색종료일보다 클 수 없습니다.");
			flag = 2;
		}
	}
	
	if(flag==0){
		document.listForm.pageIndex.value = pageNo;
	    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtList.do'/>";
	    document.listForm.submit();
	}else{
		return;
	}  
}

function fncSelectBatchDlbrt(batchDlbrt) {
    document.listForm.batchDlbrtNo.value = batchDlbrt;
    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrt.do'/>";
    document.listForm.submit();     
}

function fncAddBatchDlbrtInsert() {
	document.listForm.processSeCode.value = 'INS';
	document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtInsertView.do'/>";
    document.listForm.submit();
}

function fncBatchDlbrtDeleteList() {

    if(fncManageChecked()) {	
        document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtListDelete.do'/>";
        document.listForm.submit();
    }
}

function fncAddBatchDlbrtView() {
    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectBatchDlbrtList('1');
    }
}
function fncClearSearchCondition(){
	document.listForm.searchKeyword.value="검색조건을 선택하세요";
	document.listForm.searchKeyword.disabled="disabled";
	document.listForm.searchResultCode.value="";
	document.listForm.searchCondition.value="";
	document.listForm.searchKeywordFrom.value="";
	document.listForm.searchKeywordTo.value="";
}

function fncOnLoad(){
//	var message = document.listForm.message.value;
//	if(message!=null && message!=''){
//		alert(message);
//	}
}
</script>

<form name="listForm" action="<c:url value='/bopr/bam/EgovBatchDlbrtList.do'/>" method="post">
<div class="contsBody">
	<h2>배치심의요청</h2>
	<div class="location">배치심의 > 배치심의요청 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchResultCode" class="disp_none">승인여부 검색조건</label>
			<select id="searchResultCode" name="searchResultCode" class="serSel" style="" title="승인여부 검색조건 선택">
				<option value="">승인여부선택</option>
				<option value="NU" <c:if test="${batchDlbrtVO.searchResultCode == 'NU'}"> selected </c:if>>미승인</option>
				<option value="CN" <c:if test="${batchDlbrtVO.searchResultCode == 'CN'}"> selected </c:if>>승인</option>
				<option value="RT" <c:if test="${batchDlbrtVO.searchResultCode == 'RT'}"> selected </c:if>>반려</option>
				<option value="RE" <c:if test="${batchDlbrtVO.searchResultCode == 'RE'}"> selected </c:if>>재요청</option>
			</select>
			<label for="searchCondition" class="disp_none">검색조건 선택</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색조건 선택" onchange="javascript:fncChangeCondition();return false;">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${batchDlbrtVO.searchCondition == '1'}"> selected </c:if>>배치심의번호</option>    
				<option value="2" <c:if test="${batchDlbrtVO.searchCondition == '2'}"> selected </c:if>>배치명</option>       
			</select>
			<label for="searchKeyword" class="disp_none">검색어 입력</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력" type="text" 
			    <c:if test="${batchDlbrtVO.searchKeyword ne ''}">value="<c:out value='${batchDlbrtVO.searchKeyword}'/>"</c:if>
				<c:if test="${batchDlbrtVO.searchKeyword eq '' && batchDlbrtVO.searchCondition eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			 onkeyup="press();return false;" />
			&nbsp;&nbsp;등록일자&nbsp;
			<label for="searchKeywordFrom" class="disp_none">검색 시작일자 입력</label>
			<input name="searchKeywordFrom" readonly type="text" title="검색 시작일자" size="10" value="<c:out value="${batchDlbrtVO.searchKeywordFrom}"/>"  />
				<a href="#LINK" title="새창" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordFrom);">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="검색 시작일자 선택">
	 			</a>
	 		 ~ &nbsp;
	 		<label for="searchKeywordTo" class="disp_none">검색 마지막일자 입력</label>
			<input name="searchKeywordTo" readonly type="text" title="검색 마지막일자" size="10" value="<c:out value="${batchDlbrtVO.searchKeywordTo}"/>"  />
				<a href="#LINK" title="새창" onClick="fn_egov_NormalCalendar(listForm,'', listForm.searchKeywordTo);">
				<img name="calendarImg" src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="검색 마지막일자 선택">
	 			</a>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectBatchDlbrtList('1');return false;"/>
			<input type="image" class="searchbtn" title="초기화" src="/images/egovframework/bopr/btn_refresh.gif" alt="검색조건 초기화" onclick="javascript:fncClearSearchCondition();return false;" />
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a title="배치심의요청 등록" href="javascript:fncAddBatchDlbrtInsert()">등록</a></span>
		<span class="bbsBtn"><a title="배치심의요청 삭제" href="javascript:fncBatchDlbrtDeleteList()">삭제</a></span>
		
	</div>

	<div class="bbsList">
		<table  summary="배치명, 배치심의번호, 업무심의명, 등록일시, 승인여부 등의 배치심의요청 목록입니다.">
		<caption>업무심의요청목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:15%" >
			<col style="width:10%" >
			<col style="width:15%" >
			<col style="width:10%" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><input type="checkbox" title="전체선택" name="checkAll" class="check2" onclick="javascript:fncCheckAll()"></th>
			<th scope="col">배치명</th>
			<th scope="col">배치심의번호</th>
			<th scope="col">업무심의명</th>
			<th scope="col">등록일시</th>
			<th scope="col">승인여부</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(batchDlbrtList) == 0}">
		<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="batchDlbrt" items="${batchDlbrtList}" varStatus="status">
		<tr>
			<td><input type="checkbox" title="배치심의요청 선택" name="delYn" class="check2"><input type="hidden" name="checkId" value="<c:out value="${batchDlbrt.batchDlbrtNo}"/>" /></td>
			<td><a href="javascript:fncSelectBatchDlbrt('<c:out value="${batchDlbrt.batchDlbrtNo}"/>')"><c:out value="${batchDlbrt.batchNm}"/></a></td>
		    <td><c:out value="${batchDlbrt.batchDlbrtNo}"/></td>
		    <td><c:out value="${batchDlbrt.jobDlbrtNm}"/></td>
		    <td class="center"><c:out value="${batchDlbrt.frstRegistPnttm}"/></td>
		    <td class="center">
		    <c:if test="${empty batchDlbrt.batchDlbrtResultCode }">
			미승인
			</c:if>
			<c:if test="${not empty batchDlbrt.batchDlbrtResultCode }">
			<c:out value="${batchDlbrt.batchDlbrtResultCodeNm}"/>
			</c:if>
		    <input type="hidden" name="checkCn" value="<c:out value="${batchDlbrt.batchDlbrtResultCode}"/>" /></td>	
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
	<input type="hidden" name="batchDlbrtNo"/>
	<input type="hidden" name="batchDlbrtNos"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${batchDlbrtVO.pageIndex}'/>"/>
	<input type="hidden" name="processSeCode"/>	
	<!-- 달력 팝업 URL -->
 	<input type="hidden" name="cal_url" value="<c:url value='/com/sym/cal/EgovNormalCalPopup.do'/>" />
 	<!-- 메시지 -->
 	<input type="hidden" name="message" value="<c:out value='${message}'/>"/>
</div>
</form>
