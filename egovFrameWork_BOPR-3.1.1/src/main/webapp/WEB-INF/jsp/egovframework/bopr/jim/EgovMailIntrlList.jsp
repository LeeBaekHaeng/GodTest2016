<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovMailIntrlList.jsp
  * @Description : Mail연동 관리 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2012.07.16   김지완          최초 생성
  *
  *  @author 배치운영환경 김지완
  *  @since 2012.07.16
  *  @version 1.0 
  *  @see
  */
%>

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
            if(checkCount > 0) 
                returnBoolean = true;
            else {
                alert("선택된 권한이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 권한이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.emailIntrlckNo.value = returnValue;

    return returnBoolean;
}

function fncSelectMailIntrlList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovMailIntrlList.do'/>";
    document.listForm.submit();
}

function fncSelectMailIntrl(emailIntrlckNo) {
    document.listForm.emailIntrlckNo.value = emailIntrlckNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovMailIntrl.do'/>";
    document.listForm.submit();     
}

function fncAddMailIntrlInsert() {
    location.replace("<c:url value='/bopr/jim/EgovMailIntrlInsertView.do'/>"); 
}

function fncMailIntrlDeleteList() {

    if(fncManageChecked()) {	
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/jim/EgovMailIntrlListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddMailIntrlView() {
    document.listForm.action = "<c:url value='/bopr/jim/EgovMailIntrlUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovMailIntrlList.do'/>";
    document.listForm.submit();
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

</script>

<form name="listForm" method="post" action="">
<div class="contsBody">
	<h2>메일연동 관리</h2>
	<div class="location">메일연동 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${mailIntrlManageVO.searchCondition == '1'}">selected</c:if> >이메일연동명</option>          
			</select>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" 
				<c:if test="${mailIntrlManageVO.searchKeyword ne ''}">value="<c:out value='${mailIntrlManageVO.searchKeyword}'/>"</c:if>
				<c:if test="${mailIntrlManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			
			/>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSelectMailIntrlList('1')"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddMailIntrlInsert()">등록</a></span>
		<span class="bbsBtn"><a href="javascript:fncMailIntrlDeleteList()">삭제</a></span>
	</div>

	<div class="bbsList">
		<table  summary="메일연동 목록입니다.">
		<caption>메일연동 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:12%" >
			<col style="width:20%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()"></th>
		    <th>E-Mail연동명</th>
		    <th>E-Mail주소</th>
		   	<th>사용자ID</th>
		    <th>등록일시</th>
		</tr>
		</thead>
		<tbody>
		<c:if test="${fn:length(mailIntrlList) == 0}">
		<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		<c:forEach var="mailIntrl" items="${mailIntrlList}" varStatus="status">
		<tr>
			<td><input type="checkbox" name="delYn" class="check2"><input type="hidden" name="checkId" value="<c:out value="${mailIntrl.emailIntrlckNo}"/>" /></td>
			<td><a href="javascript:fncSelectMailIntrl('<c:out value="${mailIntrl.emailIntrlckNo}"/>')"><c:out value="${mailIntrl.emailIntrlckNm}"/></a></td>
			<td><c:out value="${mailIntrl.emailAdres}"/></td>
			<td><c:out value="${mailIntrl.userId}"/></td>
			<td><c:out value="${mailIntrl.frstRegistPnttm}"/></td>
		</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty mailIntrlManageVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="emailIntrlckNo"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${mailIntrlManageVO.pageIndex}'/>"/>
	
</form>
