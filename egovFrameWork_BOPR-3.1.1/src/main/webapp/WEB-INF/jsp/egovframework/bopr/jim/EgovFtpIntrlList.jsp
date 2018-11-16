<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovFtpIntrlList.jsp
  * @Description : FTP연동 관리 화면
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
                alert("선택된 FTP연동서비스가 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 FTP연동서비스가 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
                checkCount = 1;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.ftpIntrlckNo.value = returnValue;
    document.listForm.checkCount.value = checkCount;
    
    return returnBoolean;
}

function fncSelectFtpIntrlList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovFtpIntrlList.do'/>";
    document.listForm.submit();
}

function fncSelectFtpIntrl(ftpIntrlckNo) {
    document.listForm.ftpIntrlckNo.value = ftpIntrlckNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovFtpIntrl.do'/>";
    document.listForm.submit();     
}

function fncAddFtpIntrlInsert() {
    location.replace("<c:url value='/bopr/jim/EgovFtpIntrlInsertView.do'/>"); 
}

function fncFtpIntrlDeleteList() {

    if(fncManageChecked()) {	
        if(confirm(document.listForm.checkCount.value+"개의 FTP연동서비스를 삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/jim/EgovFtpIntrlListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddFtpIntrlView() {
    document.listForm.action = "<c:url value='/bopr/jim/EgovFtpIntrlUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/jim/EgovFtpIntrlList.do'/>";
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
function fncPress(){
	if (event.keyCode==13) {
		fncSearchList();
	}
}
function fncSearchList(){
	if(!document.listForm.searchKeyword.disabled){
		if(document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "") != ""){
			fncSelectFtpIntrlList('1');	
		}else{
			window.alert("검색어를 입력하세요");
			document.listForm.searchKeyword.select();
		}
	}else{
		window.alert("검색조건을 선택하세요");
		document.listForm.searchCondition.focus();
	}
	
}
</script>

<form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;" action="">
<div class="contsBody">
	<h2>FTP연동 관리</h2>
	<div class="location">연동서비스 관리 > FTP연동 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${ftpIntrlManageVO.searchCondition == '1'}">selected</c:if> >FTP연동명</option> 
			</select>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
				<c:if test="${ftpIntrlManageVO.searchKeyword ne ''}">value="<c:out value='${ftpIntrlManageVO.searchKeyword}'/>"</c:if>
				<c:if test="${ftpIntrlManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			/>                       
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddFtpIntrlInsert()">등록</a></span>
		<span class="bbsBtn"><a href="javascript:fncFtpIntrlDeleteList()">삭제</a></span>
	</div>

	<div class="bbsList">
		<table  summary="FTP연동관리 목록입니다.">
		<caption>FTP연동관리 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:12%" >
			<col style="width:20%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><label for="checkAll" class="disp_none">전체선택</label><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
			<th scope="col">FTP연동명</th>
			<th scope="col">FTP주소</th>
			<th scope="col">사용자ID</th>
			<th scope="col">등록일시</th>
		</tr>
		</thead>
		<tbody>
		
		<c:if test="${fn:length(ftpIntrlList) == 0}">
		<tr>
		<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		
		<c:forEach var="ftpIntrl" items="${ftpIntrlList}" varStatus="status">
		<tr>
			<td>
			<label for="delYn" class="disp_none">삭제여부체크</label>
			<input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${ftpIntrl.ftpIntrlckNo}"/>" /></td>
			<td><a href="javascript:fncSelectFtpIntrl('<c:out value="${ftpIntrl.ftpIntrlckNo}"/>')"><c:out value="${ftpIntrl.ftpIntrlckNm}"/></a></td>
			<td><c:out value="${ftpIntrl.ftpAdres}"/></td>
			<td><c:out value="${ftpIntrl.userId}"/></td>
			<td><c:out value="${ftpIntrl.frstRegistPnttm}"/></td>
		</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty ftpIntrlManageVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="ftpIntrlckNo"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${ftpIntrlManageVO.pageIndex}'/>"/>
	<input type="hidden" name="checkCount"/>
</form>
<c:if test="${message ne null}"><script>alert("${message}");</script></c:if>