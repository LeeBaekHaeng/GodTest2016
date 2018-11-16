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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectUserList() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserList.do'/>";
	varFrom.submit();       
}

function fncUserGoUpdate() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserUpdateView.do'/>";
	varFrom.submit();
}

function fncUserDelete() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserDelete.do'/>";
	if(confirm("사용불가로 전환하시겠습니까?")){
	    varFrom.submit();
	}
}

</script>

<form:form commandName="userManage" method="post" >
<div class="contsBody">
	<h2>사용자 관리</h2>
	<div class="location">사용자 관리 > 사용자 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncSelectUserList()" title="사용자 목록 화면으로 이동">목록</a></span>
		</c:if>
		
		<span class="bbsBtn"><a href="javascript:fncUserGoUpdate()" title="사용자 수정 화면으로 이동">수정</a></span>
		
		<c:if test="${adminYn == 'Y'}">
		<c:if test="${userManage.useAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncUserDelete()" title="선택한 사용자를 사용불가로 전환">사용불가로 전환</a></span>
		</c:if>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table summary="사용자ID, 사용자명, 사용여부, 소속부서, 전화번호, 주소, E-mail, 정보제공동의여부, 등록일시 순서로 보여진 사용자관리 상세정보입니다.">
		<caption>사용자관리 상세정보</caption>
		<colgroup>
			<col style="width:100" >
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>  
			<td rowspan="11" valign="top" width="100">
			<c:if test="${not empty userManage.imageFile}">
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectImageFileInfs.do" > 
					<c:param name="atchFileId" value="${userManage.imageFile}" /> 
				</c:import>       
			</c:if>
			<c:if test="${empty userManage.imageFile}">
				<img src="/images/egovframework/bopr/noImage.png" alt="사용자정보 해당 이미지가 없습니다."/>
			</c:if>
			
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자ID</th>
			<td>${userManage.userId}</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자명</th>
			<td>${userManage.userNm}</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용여부</th>
			<td>
				<c:if test="${userManage.useAt == 'Y'}">사용가능</c:if>
    			<c:if test="${userManage.useAt == 'N'}">사용불가</c:if>
			</td>
		</tr>
		<tr>
			<th>소속부서</th>
			<td>
				<c:forEach var="dept" items="${deptList}" varStatus="status">
	    		<c:if test="${dept.code == userManage.deptId}">
	    			${dept.codeNm}
	    		</c:if>
    	</c:forEach>
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${userManage.moblphonNo}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${userManage.adres}</td>
		</tr>
		<tr>
			<th>E-mail</th>
			<td>${userManage.emails}</td>
		</tr>
		<tr>
			<th>정보제공 동의 여부</th>
			<td>
				<c:if test="${userManage.agreAt == 'Y'}">동의</c:if>
    			<c:if test="${userManage.agreAt == 'N'}">비동의</c:if>
			</td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>${userManage.registDe}</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden 값 -->
	<input type="hidden" name="userId" value="<c:out value='${userManage.userId}'/>"/>
	<input name="imageFile" type="hidden" value="<c:out value='${userManage.imageFile}'/>"/>
</form:form>
