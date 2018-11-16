<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
 /**
  * @Class Name : EgovAuthorGroupManage.java
  * @Description : EgovAuthorGroupManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자                    수정내용
  * @ -------       --------    ---------------------------
  * @ 2009.03.23    Lee.m.j       최초 생성
  *
  *  @author Lee.m.j
  *  @since 2009.03.23
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

	var resultCheck = false;

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var selectAuthor = document.listForm.authorManageCombo;
    var booleanRegYn = document.listForm.regYn;
    var listMberTyCode = document.listForm.mberTyCode;

    var returnId = "";
    var returnAuthor = "";
    var returnRegYn = "";
    //var returnmberTyCode = "";

    var checkedCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;
                    
                    /* if(booleanRegYn[i].value == "N"){
                    	window.alert(checkId[i].value+"은(는) 미등록된 사용자 입니다.");
                    	return false;
                    } */
                    
                    if(returnId == "") {
                        returnId = checkField[i].value;
                        returnAuthor = selectAuthor[i].value;
                        returnRegYn = booleanRegYn[i].value;
                        //returnmberTyCode = listMberTyCode[i].value;
                        
                    }
                    else {
                    	returnId = returnId + ";" + checkField[i].value;
                    	returnAuthor = returnAuthor + ";" + selectAuthor[i].value;
                    	returnRegYn = returnRegYn + ";" + booleanRegYn[i].value;
                    	//returnmberTyCode = returnmberTyCode + ";" + listMberTyCode[i].value;

                    }
                }
            }

            if(checkedCount > 0)
            	resultCheck = true;
            else {
                alert("선택된  항목이 없습니다.");
                resultCheck = false;
            }

        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("선택 항목이 없습니다.");
                resultCheck = false;
            }
            else {
                returnId = checkId.value;
                returnAuthor = selectAuthor.value;
                returnRegYn = booleanRegYn.value;
                //returnmberTyCode = listMberTyCode.value;

                resultCheck = true;
                checkCount = 1;
                
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.userIds.value = returnId;
    document.listForm.authorCodes.value = returnAuthor;
    document.listForm.regYns.value = returnRegYn;
    //document.listForm.mberTyCodes.value = returnmberTyCode;
    
    document.listForm.checkCount.value = checkedCount;
    
    return resultCheck;
}

function fncSelectAuthorGroupList(pageNo){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupList.do'/>";
    document.listForm.submit();
}

function fncAddAuthorGroupInsert() {

	if(!fncManageChecked()) return;

    if(confirm("등록하시겠습니까?")) {

        document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupInsert.do'/>";
        document.listForm.submit();
    }
}

function fncAuthorGroupDeleteList() {

	if(!fncManageChecked()) return;

    if(confirm(document.listForm.checkCount.value+"명의 사용자 권한을 제거하시겠습니까?")) {
        document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupDelete.do'/>";
        document.listForm.submit();
    }
}

function linkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rgm/EgovAuthorGroupList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorGroupPop() {

    if(document.listForm.searchCondition.value == '3') {
    	window.open("<c:url value='/sec/gmt/EgovGroupSearchList.do'/>","notice","height=500, width=485, top=50, left=20, scrollbars=no, resizable=no");
    } else {
        alert("그룹을 선택하세요.");
        return;
    }

    /*
    var url = "<c:url value='/sec/gmt/EgovGroupSearchView.do'/>";
    var varParam = new Object();
    var openParam = "dialogWidth:500px;dialogHeight:485px;scroll:no;status:no;center:yes;resizable:yes;";
    var retVal;

    if(document.listForm.searchCondition.value == '3') {
        retVal = window.showModalDialog(url, varParam, openParam);
        if(retVal) {
            document.listForm.searchKeyword.value = retVal;
        }
    } else {
        alert("그룹을 선택하세요.");
        return;
    }
    */

}

function onSearchCondition() {
	document.listForm.searchKeyword.value = "";
	if(document.listForm.searchCondition.value == '3') {
        document.listForm.searchKeyword.readOnly = true;
	} else {
		document.listForm.searchKeyword.readOnly = false;
	}
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
			fncSelectAuthorGroupList('1');	
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

<form:form name="listForm" method="post" onkeypress="if(event.keyCode==13) return false;">
<div class="contsBody">
	<h2>권한 부여 관리</h2>
	<div class="location">권한 관리 > 권한 부여 관리 > <strong>권한 부여 관리</strong></div>
	
	<!-- 검색영역 -->
	<div class="search">
		<fieldset class="searchboxA">
			<legend>검색 영역</legend>
			<label for="searchCondition" class="disp_none">검색조건</label>
			<select id="searchCondition" name="searchCondition" class="serSel" style="" title="검색어 선택" onchange="javascript:fncChangeCondition();">
				<option value="">검색조건선택</option>
				<option value="1" <c:if test="${authorGroupVO.searchCondition == '1'}">selected</c:if> >사용자ID</option>
				<option value="2" <c:if test="${authorGroupVO.searchCondition == '2'}">selected</c:if> >사용자명</option>
			</select>
			<label for="searchKeyword" class="disp_none">검색어</label>
			<input id="searchKeyword" name="searchKeyword" class="inptext" title="검색어 입력란" type="text" onkeyPress="javascript:fncPress();"
				<c:if test="${authorGroupVO.searchKeyword ne ''}">value="<c:out value='${authorGroupVO.searchKeyword}'/>"</c:if>
				<c:if test="${authorGroupVO.searchKeyword eq ''}">value="검색조건을 선택하세요" disabled="disabled"</c:if>
			/>
			<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="javascript:fncSearchList(); return false;"/>
		</fieldset>
	</div>
	<!-- //검색영역 -->

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddAuthorGroupInsert()" title="선택된 사용자의 권한을 등록">등록</a></span>
		<span class="bbsBtn"><a href="javascript:fncAuthorGroupDeleteList()" title="선택된 사용자의 권한을 삭제">삭제</a></span>
	</div>

	<div class="bbsList">
		<table  summary="사용자 ID 사용자명, 권한, 등록여부의 순서로 보여지는 권한부여 관리 입니다.">
		<caption>권한부여 관리</caption>
		<colgroup>
			<col style="width:5%" >
			<col style="width:10%" >
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:10%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col"><label for="checkAll" class="disp_none">전체선택</label><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택체크박스"></th>
		    <th scope="col">사용자 ID</th>
		    <th scope="col">사용자 명</th>
		    <th scope="col">권한</th>
			<th scope="col">등록 여부</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="authorGroup" items="${authorGroupList}" varStatus="status">
		<tr>
			<td><label for="delYn" class="disp_none">등록할 사용자 체크</label><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${authorGroup.userId}"/>"/></td>
			<td><c:out value="${authorGroup.userId}"/></td>
			<td><c:out value="${authorGroup.userNm}"/></td>
			<td>
			<label for="authorManageCombo" class="disp_none">등록할 권한선택</label>
			<select name="authorManageCombo" title="권한">
			<c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
			<option value="<c:out value="${authorManage.authorCode}"/>" <c:if test="${authorManage.authorCode == authorGroup.authorCode}">selected</c:if> ><c:out value="${authorManage.authorNm}"/></option>
			</c:forEach>
			</select></td>
			<td><c:out value="${authorGroup.regYn}"/><input type="hidden" name="regYn" value="<c:out value="${authorGroup.regYn}"/>"></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	<!-- //페이징 -->
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="mberTyCode" value="${authorGroup.mberTyCode}"/>
	<input type="hidden" name="userId"/>
	<input type="hidden" name="userIds"/>
	<input type="hidden" name="authorCodes"/>
	<input type="hidden" name="regYns"/>
	
	<input type="hidden" name="pageIndex" value="<c:out value='${authorGroupVO.pageIndex}'/>"/>
	<input type="hidden" name="checkCount"/>
</form:form>
