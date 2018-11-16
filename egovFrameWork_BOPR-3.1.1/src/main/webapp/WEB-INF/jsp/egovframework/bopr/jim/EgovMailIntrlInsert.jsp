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
   * @Class Name : EgovMailIntrlInsert.jsp
   * @Description : Mail연동 등록 화면
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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mailIntrlManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectMailIntrlList() {
	var varFrom = document.getElementById("mailIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovMailIntrlList.do'/>";
	varFrom.submit();       
}

function fncMailIntrlInsert() {

    var varFrom = document.getElementById("mailIntrlManage");
    varFrom.action = "<c:url value='/bopr/jim/EgovMailIntrlInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
		if(!validateMailIntrlManage(varFrom)){         
            return;
        }else{
            varFrom.submit();
        }  
    }
}
</script>

<form:form commandName="mailIntrlManage" method="post" name="mailIntrlManage">
<div class="contsBody">
	<h2>메일연동 관리</h2>
	<div class="location">메일연동 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectMailIntrlList()">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncMailIntrlInsert()">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="메일연동관리 등록입니다.">
		<caption>메일연동관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />이메일연동명</th>
			<td><input name="emailIntrlckNm" id="emailIntrlckNm" type="text" maxLength="50" size="40" /></td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />이메일주소</th>
			<td><input name="emailAdres" id="emailAdres" type="text" maxLength="50" size="40" /></td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />IP정보</th>
			<td><input name="ipInfo" id="ipInfo" type="text" maxLength="50" size="40" /></td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />사용자ID</th>
			<td><input name="userId" id="userId" type="text" maxLength="50" size="40" /></td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />비밀번호</th>
			<td><input name="password" id="password" type="text" maxLength="50" size="40" /></td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>
</form:form>
