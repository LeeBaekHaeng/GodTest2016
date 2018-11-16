<%--
/**
 * @Class Name  : EgovRoleInsert.java
 * @Description : EgovRoleInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="registerFlag" value="${empty roleManageVO.roleCode ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty roleManageVO.roleCode ? '롤 등록' : '롤 수정'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectRoleList() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleList.do'/>";
    varFrom.submit();
}

function fncRoleInsert() {

    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncRoleUpdate() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleUpdate.do'/>";

    if(confirm("저장 하시겠습니까?")){
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncRoleDelete() {
    var varFrom = document.getElementById("roleManage");
    varFrom.action = "<c:url value='/sec/rmt/EgovRoleDelete.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

</script>

<form:form commandName="roleManage" method="post" >

<div class="contsBody">
	<h2>롤 관리</h2>
	<div class="location">권한 관리 > 롤 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectRoleList()" title="롤관리 목록화면으로 이동">목록</a></span>
		
		<c:if test="${registerFlag == 'INSERT'}">
			<span class="bbsBtn"><a href="javascript:fncRoleInsert()" title="해당 롤정보를 저장">등록</a></span>
		</c:if>
		
		<c:if test="${registerFlag == 'UPDATE'}">
			<span class="bbsBtn"><a href="javascript:fncRoleUpdate()" title="해당 롤정보를 저장">수정</a></span>
			<span class="bbsBtn"><a href="javascript:fncRoleDelete()" title="해당 롤을 삭제">삭제</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="롤명, 롤패턴, 롤타입, 롤인증순서의 순서로 입력하는 롤관리 등록입니다.">
		<caption>롤관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />롤 명</th>
			<td>
				<label for="roleNm" class="disp_none">롤명</label>
				<input name="roleNm" id="roleNm" type="text" value="<c:out value='${roleManage.roleNm}'/>" maxLength="50" size="30" title="롤명" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />롤 패턴</th>
			<td>
				<label for="rolePtn" class="disp_none">롤패턴</label>
				<input name="rolePtn" id="rolePtn" type="text" value="<c:out value='${roleManage.rolePtn}'/>" maxLength="200" size="50" title="롤패턴" /></td>
		</tr>
		<tr>
			<th>설명</th>
			<td>
				<label for="roleDc" class="disp_none">설명</label>
				<input name="roleDc" id="roleDc" type="text" value="<c:out value='${roleManage.roleDc}'/>" maxLength="50" size="50" title="설명" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />롤 타입</th>
			<td>
				<label for="roleTyp" class="disp_none">롤타입</label>
				<select name="roleTyp" title="롤타입">
					<c:forEach var="cmmCodeDetail" items="${cmmCodeDetailList}" varStatus="status">
					<option value="<c:out value="${cmmCodeDetail.code}"/>" <c:if test="${cmmCodeDetail.code == roleManage.roleTyp}">selected</c:if> ><c:out value="${cmmCodeDetail.codeNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />롤인증순서</th>
			<td>
				<label for="roleSort" class="disp_none">롤인증순서</label>
				<input name="roleSort" id="roleSort" type="text" value="<c:out value='${roleManage.roleSort}'/>" maxLength="10" size="10" title="롤인증순서" /></td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

	<input name="roleCode" id="roleCode" type="hidden" value="<c:out value='${roleManage.roleCode}'/>"/>
	
	<!-- 검색조건 유지 -->
	<c:if test="${registerFlag == 'UPDATE'}">
	<input type="hidden" name="searchCondition" value="<c:out value='${roleManageVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${roleManageVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${roleManageVO.pageIndex}'/>"/>
	</c:if>
</form:form>
