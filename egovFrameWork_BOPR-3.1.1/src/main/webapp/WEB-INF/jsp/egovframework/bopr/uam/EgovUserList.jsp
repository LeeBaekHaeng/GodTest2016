<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovUserList.jsp
  * @Description : 사용자 관리 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2012.07.12   김지완          최초 생성
  *
  *  @author 배치운영환경 김지완
  *  @since 2012.07.12
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
	
    //추가
    var useAt = document.listForm.useAt;
    //끝
    
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkField[i].value = checkId[i].value;
                    
                    if(useAt[i].value == "N"){
                    	window.alert(checkId[i].value+"은(는) 이미 사용불가 입니다.");
                    	return false;
                    }
                    
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                	    returnValue = returnValue + ";" + checkField[i].value;
                    checkCount++;
                }
            }
            if(checkCount > 0){
            	returnBoolean = true;
            }else {
                alert("선택된 사용자가 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 사용자가 없습니다.");
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

    document.listForm.userId.value = returnValue;
    document.listForm.checkCount.value = checkCount;
    
    return returnBoolean;
}

function fncSelectUserList(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/uam/EgovUserList.do'/>";
    document.listForm.submit();
}

function fncSelectUser(user) {
    document.listForm.userId.value = user;
    document.listForm.action = "<c:url value='/bopr/uam/EgovUser.do'/>";
    document.listForm.submit();     
}

function fncAddUserInsert() {
    location.replace("<c:url value='/bopr/uam/EgovUserInsertView.do'/>"); 
}

function fncUserDeleteList() {

    if(fncManageChecked()) {	
        if(confirm(document.listForm.checkCount.value+"명의 사용자를 사용 불가로 전환하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/uam/EgovUserListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddUserView() {
    document.listForm.action = "<c:url value='/bopr/uam/EgovUserUpdate.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/uam/EgovUserList.do'/>";
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
			fncSelectUserList('1');	
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

<!-- 상세내용 -->
<div class="contsBody">
	<h2>사용자 관리</h2>
	<div class="location">사용자 관리 > 사용자 관리 > <strong>목록</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
	<fieldset class="searchboxA">
		<legend>검색 영역</legend>
		<label for="searchCondition" class="disp_none">검색조건</label>
		<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
			<option value="" >검색조건선택</option>
			<option value="1" <c:if test="${userManageVO.searchCondition == '1'}">selected</c:if> >사용자ID</option>
			<option value="2" <c:if test="${userManageVO.searchCondition == '2'}">selected</c:if> >사용자명</option>              
		</select>
		<label for="searchKeyword" class="disp_none">검색어</label>
		<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
			<c:if test="${userManageVO.searchKeyword ne ''}">value="<c:out value='${userManageVO.searchKeyword}'/>"</c:if>
			<c:if test="${userManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
		/>                       
		<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
	</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddUserInsert();" title="사용자 등록 화면으로 이동">등록</a></span>
		<span class="bbsBtn"><a href="javascript:fncUserDeleteList();" title="선택한 사용자를 사용불가로 전환">사용불가로 전환</a></span>
	</div>

	<div class="bbsList">
		<table summary="사용자ID, 사용자명, 전화번호, 사용여부, 등록일시 순서로 보여진 사용자관리 목록입니다.">
		<caption>사용자관리 목록</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:18%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:12%" >
			<col style="width:20%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><label for="checkAll" class="disp_none">전체선택</label><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
			<th scope="col">사용자ID</th>
			<th scope="col">사용자명</th>
			<th scope="col">전화번호</th>
			<th scope="col">사용여부</th>
			<th scope="col">등록일시</th>
		</tr>
		</thead>
		<tbody>
			<c:if test="${fn:length(userList) == 0}">
			<tr>
			<td colspan="6"><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
			
			<c:forEach var="user" items="${userList}" varStatus="status">
			<tr>
			<td>
			<label for="delYn" class="disp_none">삭제여부체크</label>
			<input type="checkbox" name="delYn" class="check2" title="삭제여부체크"><input type="hidden" name="checkId" value="<c:out value="${user.userId}"/>" /></td>
			<td class="left"><a href="javascript:fncSelectUser('<c:out value="${user.userId}"/>')"><c:out value="${user.userId}"/></a></td>
			<td><c:out value="${user.userNm}"/></td>
			<td><c:out value="${user.moblphonNo}"/></td>
			<td>
				<input type="hidden" name="useAt" value="<c:out value="${user.useAt}"/>" />
				<c:if test="${user.useAt == 'Y'}">사용가능</c:if>
				<c:if test="${user.useAt == 'N'}">사용불가</c:if>
			</td>
			<td><c:out value="${user.registDe}"/></td>
			</tr>
			</c:forEach>
 
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty userManageVO.pageIndex }">
	<div class="paging">
		<!-- 어떻게 하지? -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
</div>

<!-- Hidden값 -->
<input type="hidden" name="userId"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userManageVO.pageIndex}'/>"/>
<input type="hidden" name="checkCount"/>

</form>
