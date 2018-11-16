<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%

/**
 * @Class Name : EgovAuthorRoleManage.java
 * @Description : EgovAuthorRoleManage.jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.02.01    lee.m.j     최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.03.21
 *  @version 1.0
 *  @see
 *
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
    var checkRegYn = document.listForm.regYn;
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;

	                if(returnValue == "") {
	                    returnValue = checkField[i].value;
	                    returnRegYns = checkRegYn[i].value;
	                }
	                else {
	                    returnValue = returnValue + ";" + checkField[i].value;
	                    returnRegYns = returnRegYns + ";" + checkRegYn[i].value;
	                }
                }
            }

            if(checkedCount > 0)
            	returnBoolean = true;
            else {
                alert("선택된  롤이 없습니다.");
                returnBoolean = false;
            }
        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("선택된 롤이 없습니다.");
            	returnBoolean = false;
            }
            else {
            	returnValue = checkId.value;
                returnRegYns = checkRegYn.value;

                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.roleCodes.value = returnValue;
    document.listForm.regYns.value = returnRegYns;

    return returnBoolean;

}

function fncSelectAuthorRoleList() {
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = "1";
    document.listForm.authorCode.value = document.listForm.searchKeyword.value;
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleList.do'/>";
    document.listForm.submit();
}
function fncSelectAuthorList(){
    var retVal;
    var url = "<c:url value='/sec/ram/EgovAuthorListPopUp.do'/>";
    var varParam = new Object();
    var openParam = "dialogWidth:700px;dialogHeight:500px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
    	document.listForm.searchKeyword.value = retVal;
    }
}
/* function fncSelectAuthorList(){
   // document.listForm.searchCondition.value = "1";
   // document.listForm.pageIndex.value = "1";
    document.listForm.searchKeyword.value = "";
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorList.do'/>";
    document.listForm.submit();
} */

function fncSelectAuthorRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/sec/ram/EgovRole.do'/>";
    document.listForm.submit();
}

function fncAddAuthorRoleInsert(roleCode,regYn) {
		
	document.listForm.roleCode.value = roleCode;
	document.listForm.regYn.value = regYn;
	
	document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleInsert.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/ram/EgovAuthorRoleList.do'/>";
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
	if(document.listForm.searchKeyword.value.replace(/(^\s*)|(\s*$)/gi, "") != ""){
		fncSelectAuthorRoleList();	
	}else{
		window.alert("권한코드 없이 검색할수 없습니다.");
		fncSelectAuthorList();
	}
}

</script>

<c:if test="${message ne null}"><script>alert("${message}");</script></c:if>

<form:form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;">
<div class="contsBody">
	<h2>권한 관리</h2>
	<div class="location">권한 관리 > 권한 관리 > <strong>권한 롤 관리</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" >권한코드 : </label>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" readonly="readonly" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();" 
				<c:if test="${authorRoleManageVO.searchKeyword ne ''}">value="<c:out value='${authorRoleManageVO.searchKeyword}'/>"</c:if>
				<c:if test="${authorRoleManageVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			/>
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
			<a href="javascript:fncSelectAuthorList()" title="권한목록 팝업">권한목록</a>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="bbsList">
		<table  summary="롤ID, 롤명, 롤타입, 롤Sort, 롤설명, 등록일시, 등록여부의 순서로 보여지는 권한롤관리입니다.">
		<caption>권한 롤 관리</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:20%" >
			<col style="width:10%" >
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:15%" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">롤 ID</th>
			<th scope="col">롤 명</th>
			<th scope="col">롤 타입</th>
			<th scope="col">롤 Sort</th>
			<th scope="col">롤 설명</th>
			<th scope="col">등록일시</th>
			<th scope="col">등록여부</th>
		</tr>
		</thead>
		<tbody>
		
		<c:if test="${fn:length(authorRoleList) == 0}">
		<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		
		<c:forEach var="authorRole" items="${authorRoleList}" varStatus="status">
		<tr>
			<td><c:out value="${authorRole.roleCode}"/></td>
			<td><c:out value="${authorRole.roleNm}"/></td>
			<td><c:out value="${authorRole.roleTyp}"/></td>
			<td><c:out value="${authorRole.roleSort}"/></td>
			<td><c:out value="${authorRole.roleDc}"/></td>
			<td>
				<c:if test="${empty authorRole.creatDt}">미등록</c:if>
				<c:if test="${not empty authorRole.creatDt}"><c:out value="${authorRole.creatDt}"/></c:if>
			</td>
			<td><span class="bbsBtn">
				<c:if test="${authorRole.regYn == 'Y'}"><a href="javascript:fncAddAuthorRoleInsert('<c:out value='${authorRole.roleCode}'/>','N')">삭제</a></c:if>
				<c:if test="${authorRole.regYn == 'N'}"><a href="javascript:fncAddAuthorRoleInsert('<c:out value='${authorRole.roleCode}'/>','Y')">등록</a></c:if>
				</span>
			</td>
		</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</div>
	
	<c:if test="${!empty authorRoleManageVO.pageIndex }">
	<!-- 페이징 -->
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	<!-- //페이징 -->
	</c:if>
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="roleCode"/>
	<input type="hidden" name="regYn"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${authorRoleManageVO.pageIndex}'/>"/>
	<input type="hidden" name="authorCode" value="<c:out value="${authorRoleManageVO.searchKeyword}"/>"/>
	<input type="hidden" name="searchCondition" value="1"/>
	
</form:form>
