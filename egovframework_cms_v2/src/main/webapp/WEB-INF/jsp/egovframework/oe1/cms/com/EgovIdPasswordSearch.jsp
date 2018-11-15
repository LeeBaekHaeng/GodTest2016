<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @JSP Name : EgovIdPasswordSearch.jsp
  * @Description : 아이디/비밀번호 찾기 화면
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    조수정                 최초 생성
  *
  * author 운영환경 1팀
  * since  2010.07.02
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>아이디/비밀번호 찾기 화면</title>

<!-- 페이지 style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/login.css'/>" rel="stylesheet" type="text/css">

<!-- 페이지 script -->
<script type="text/javascript" language='javascript'>
function searchMberId() {
	if (document.idForm.mberNm.value =="") {
		alert("이름을 입력하세요");
		return;
	} else if (document.idForm.mberEmailAdres.value =="") {
		alert("가입시 이메일주소를 입력하세요");
		return;
	} else {
		document.idForm.action = "<c:url value='/cms/com/searchId.do'/>";
		document.idForm.submit();
	}
}

function searchPw() {
	if (document.passwordForm.mberId.value =="") {
		alert("아이디를 입력하세요");
		return;
	} else if (document.passwordForm.mberNm.value =="") {
		alert("이름을 입력하세요");
		return;	
	} else if (document.passwordForm.mberEmailAdres.value =="") {
		alert("가입시 이메일주소를 입력하세요");
		return;
	} else if (document.passwordForm.passwordHint.value =="") {
		alert("비밀번호 힌트를 선택하세요");
		return;
	} else if (document.passwordForm.passwordCnsr.value =="") {
		alert("비밀번호 정답을 입력하세요");
		return;		
	} else {
		document.passwordForm.action = "<c:url value='/cms/com/searchPassword.do'/>";
		document.passwordForm.submit();
	}
}
</script>
</head>
<!-- 전체 DIV시작 -->
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">

    <!-- header 시작 -->
    <div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
    <!-- header 끝 -->

    <!-- 메인 시작 -->
    <div id="container">
        
		<!-- BODY 내용 START -->
		<div id="content">
		        
		<!-- index_content 시작 -->
		<div id="index_content">        
			<!-- 회원가입/아이디패스워드 css ▼ -->
			<div id="idpw_box">
<form name="idForm" action ="" method="post" style="display:inline">
				<div id="id_search01">
					<ul class="boxin">
						<li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_name.gif" alt="이름" /><label for="mberNm">이름 입력</label>
			              <input id="mberNm" maxlength="20" name="mberNm" />
						</li>
						<li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_email.gif" alt="이메일" /><label for="mberEmailAdres">이메일 입력</label>
						  <input id="mberEmailAdres" maxlength="30" name="mberEmailAdres" />
						</li>
						<li class="pl40">
						<input type="submit" name="submit_btn" value="찾기" class="submit_btn_input"  onclick="searchMberId(); return false;" />
						</li>
					</ul>
			  </div>
</form>
<form name="passwordForm" action ="" method="post" style="display:inline">
			  <div id="id_search02">
			    	<ul class="boxin">
						<li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_id.gif" alt="이름" /><label for="mberId">아이디 입력</label>
						  <input id="mberId" maxlength="15" name="mberId" />
						</li>
						<li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_name.gif" alt="이름" /><label for="mberNm">이름 입력</label>
						  <input id="mberNm" maxlength="20" name="mberNm" />
						</li>
			          <li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_email.gif" alt="이메일" />
			            <input id="mberEmailAdres" maxlength="30" name="mberEmailAdres" /><label for="mberEmailAdres">이메일 입력</label>
			          </li>
						<li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_hint.gif" alt="비밀번호 정답힌트" /><label for="passwordHint">비밀번호 힌트 선택</label>
                            <select name="passwordHint" id="passwordHint">
						      <option selected="selected" value=''>--선택하세요--</option>
							  <c:forEach var="result" items="${pwhtCdList}" varStatus="status">
							    <option value='<c:out value="${result.code}"/>'><c:out value="${result.codeNm}"/></option>
							  </c:forEach>
                          </select>
						</li>
			            <li><img src="/oe1/images/egovframework/oe1/cms/com/login/login_anwer.gif" alt="비밀번호 정답" /><label for="passwordCnsr">비밀번호 정답 입력</label>
			              <input id="passwordCnsr" maxlength="50" name="passwordCnsr" />
			            </li>
						<li class="pl40">
						<input type="submit" name="submit_btn" value="찾기" class="submit_btn_input"  onclick="searchPw(); return false;" />
						</li>
					</ul>    
			  </div>
</form>			  
			</div>
			<!-- 회원가입/아이디패스워드 css ▲ -->
					
		</div>		
		<!-- index_content 끝 -->			
			        
		</div>
		<!-- BODY 내용 END -->
			
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>        
        
