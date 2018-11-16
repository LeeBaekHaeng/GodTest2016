<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%

/**
 * @Class Name : EgovRoleHierarchyManage.java
 * @Description : EgovRoleHierarchyManage.jsp
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

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRole.do'/>";
    document.listForm.submit();
}

function fncAddRoleInsert() {
	
	if(document.listForm.parentRole.value == ''){
		window.alert("등록되지 않은 권한이 없습니다.");	
	}else{
		document.listForm.action = "<c:url value='/sec/rmt/EgovRoleHierarchyInsert.do'/>";
	    document.listForm.submit();
	}
}

function fncRoleListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/sec/rmt/EgovRoleListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleUpdate.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectRoleList('1');
    }
}

</script>

<form:form name="listForm" method="post">
<div class="contsBody">
	<h2>롤 상하관계 관리</h2>
	<div class="location">권한 관리 > 롤 상하관계 관리 > <strong>롤 상하관계 관리</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncAddRoleInsert()" title="해당 하위롤을 상위롤에 등록">등록</a></span>
	</div>

	<div class="bbsList">
		<table  summary="상위롤, 하위롤의 순서로 보여지는 롤 상하관계 관리 목록입니다.">
		<caption>롤 상하관계 관리 목록</caption>
		<colgroup>
			<col style="width:50%" >
			<col style="width:50%" >
		</colgroup>
		<thead>
		<tr>
			<th scope="col">상위 롤</th>
			<th scope="col">하위 롤</th>
		</tr>
		</thead>
		<tbody>

		<c:if test="${fn:length(roleHierachyList) == 0}">
		<tr>
		<td colspan="1"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
		
		<c:forEach var="roleHierachy" items="${roleHierachyList}" varStatus="status">
		<tr>
			<td>${roleHierachy.childRole}</td>
			<td>${roleHierachy.parentRole}</td>			
		</tr>
		</c:forEach>

		</tbody>
		</table>
	</div>

	<!-- 페이징 -->
	<c:if test="${!empty roleHierarchyManageVO.pageIndex }">
	<div class="paging">
		<!-- 적용불가 -->
		<!-- <a href="#" title="처음"><img src="/images/egovframework/bopr/bbs_btn_frist.gif" alt="처음" /></a><a href="#1" title="이전"><img src="images/new/bbs_btn_prev.gif" alt="이전" class="pre"/></a><span> 1</span><span><a href="#2" title="2"><strong>2</strong></a></span><span><a href="#3" title="3">3</a></span><span><a href="#4" title="4" >4</a></span><span><a href="#5" title="5" >5</a></span><span><a href="#6" title="6">6</a></span><span><a href="#7" title="7" >7</a></span><span><a href="#8" title="8">8</a></span><span><a href="#9" title="9" >9</a></span><span><a href="#10" title="10" >10</a></span><a href="#11" title="다음"><img src="images/new/bbs_btn_next.gif" alt="다음" class="next"/></a><a href="#158" title="마지막"><img src="/images/egovframework/bopr/bbs_btn_end.gif" alt="마지막" /></a> -->
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
	</div>
	</c:if>
	<!-- //페이징 -->
	
	<div class="bbsDetail">
		<table  summary="상위롤, 하위롤의 순서로 보여지는 롤 상하관계 등록입니다.">
		<caption>롤 상하관계 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />상위 롤</th>
			<td>
				<label for="childRole" class="disp_none">상위롤</label>
				<select id="childRole" name="childRole" style="width:200px; white-space:nowrap;  border:1px solid #d3d3d3; vertical-align:top; height:20px;" title="상위롤 선택">
				<c:if test="${fn:length(authorList) == 0}">
				<option><spring:message code="common.nodata.msg" /></option>				
				</c:if>
				<c:forEach var="author" items="${authorList}" varStatus="status">
					<option value="${author.authorCode}">${author.authorCode}</option>      
				</c:forEach>
				</select>
			</td>
			
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />하위 롤</th>
			<td>
				<label for="parentRole" class="disp_none">하위롤</label>
				<select id="parentRole" name="parentRole" style="width:200px; white-space:nowrap;  border:1px solid #d3d3d3; vertical-align:top; height:20px;" title="하위롤 선택">
				<c:if test="${fn:length(noHierarchyList) == 0}">
				<option value="">상하관계를 등록할 새 권한이 없습니다.</option>				
				</c:if>
				<c:forEach var="noHierachy" items="${noHierarchyList}" varStatus="status">
					<option value="${noHierachy}">${noHierachy}</option>      
				</c:forEach>
				</select>
			</td>
		</tr>
				
		</tbody>
		</table>
	</div>
	
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="pageIndex" value="<c:out value='${roleHierarchyManageVO.pageIndex}'/>"/>
</form:form>

