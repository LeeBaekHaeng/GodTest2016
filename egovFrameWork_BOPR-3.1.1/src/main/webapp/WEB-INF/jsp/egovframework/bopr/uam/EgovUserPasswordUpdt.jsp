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
   * @Class Name : EgovUserPasswordUpdt.jsp
   * @Description : 사용자 등록 화면
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

<validator:javascript formName="userPasswordUpdate" staticJavascript="false" xhtml="true" cdata="false"/>
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

function fncUserUpdate() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserPasswordUpdate.do'/>";

	if (!validateUserPasswordUpdate(varFrom)) {
		return;
	} else {
		
		//기존비밀번호와 새비밀번호 확인
    	if(document.userManage.password.value == document.userManage.searchKeyword.value){
    		window.alert("기존비밀번호와 새비밀번호가 같을 수 없습니다. 비밀번호를 확인해 주세요.");
    		document.userManage.password.select();
    		return;
    	}
		
		//새비밀번호와 비밀번호확인 확인
    	if(document.userManage.password.value != document.userManage.password2.value){
    		window.alert("입력하신 새비밀번호와 비밀번호확인이 다릅니다. 비밀번호를 확인해 주세요.");
    		document.userManage.password2.select();
    		return;
    	}
		
		if (confirm("수정 하시겠습니까?")) {
			varFrom.submit();
		}
	}
}

</script>
<c:if test="${message != null}">
<script>
alert("${message}");
</script></c:if>
<form:form name="userManage" commandName="userManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>사용자 관리</h2>
	<div class="location">사용자 관리 > 사용자 관리 > <strong>비밀번호 수정</strong></div>
	<div class="Btn">
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncSelectUserList()" title="사용자 목록 화면으로 이동">목록</a></span>
		</c:if>
		<span class="bbsBtn"><a href="javascript:fncUserUpdate()" title="해당 사용자의 비밀번호를 수정">비밀번호 수정</a></span>
		<span class="bbsBtn"><a href="javascript:fncUserGoUpdate()" title="사용자 수정 화면으로 이동">일반수정으로 이동</a></span>
	</div>

	<div class="bbsDetail">
		<table summary="사용자ID, 사용자명, 기존비밀번호, 새 비밀번호, 비밀번호 확인 순으로 보여지는 사용자관리 비밀번호수정입니다.">
		<caption>사용자관리 비밀번호 수정</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자ID</th>
			<td>${userManage.userId}</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자명</th>
			<td>${userManage.userNm}</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />기존비밀번호</th>
			<td>
				<label for="searchKeyword" class="disp_none">기존비밀번호</label>
				<input name="searchKeyword" id="searchKeyword" type="password" value="" size="15" maxlength="12"/>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />새비밀번호</th>
			<td>
				<label for="password" class="disp_none">새비밀번호</label>
				<input name="password" id="password" type="password" value="" size="15" maxlength="12"/>
			</td>
		</tr>
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />비밀번호 확인</th>
			<td>
				<label for="password2" class="disp_none">비밀번호 확인</label>
				<input name="password2" id="password2" type="password" value="" size="15" maxlength="12"/>
			</td>
		</tr>
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden값 -->
	<input type="hidden" name="userId" value="<c:out value='${userManage.userId}'/>"/>
	
</form:form>

