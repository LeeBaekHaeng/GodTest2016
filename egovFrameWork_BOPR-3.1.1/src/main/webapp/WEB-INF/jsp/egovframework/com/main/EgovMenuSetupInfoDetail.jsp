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
   * @Class Name : EgovMenuSetupInfoDetail.jsp
   * @Description : 사용자 상세정보 화면
   * @Modification Information
   * @
   * @  수정일      수정자            수정내용
   * @ -------        --------    ---------------------------
   * @ 2012.11.06   이율경          최초 생성
   *
   *  @user 배치운영환경 이율경
   *  @since 2012.11.06
   *  @version 1.0 
   *  @see
   */
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>메인화면 설정</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
 
<script type="text/javaScript" language="javascript">

function fncMenuInfoUpdate() {
	
	var menuCode = document.getElementsByName("menuCodeArr");
	var menuList = "";
	
	for ( var i = 0; i < menuCode.length; i++) {
		
		if(menuCode[i].checked == true && menuCode[i].readOnly == false) {
			menuList += menuCode[i].value + ":";
		}
	}
	document.getElementById("menuCode").value = menuList;
	
	var menuForm = document.getElementById("menuInfo");
	menuForm.action = "<c:url value='/main/EgovMenuSetupInfoUpdate.do'/>";
	menuForm.submit();
}
</script>
</head>
 
<body>
<form name="menuInfo" id="menuInfo" method="post" >
<input name="menuCode" id="menuCode" type="hidden" value="" />

<div class="contsBody">
	<h2>메뉴 설정</h2>
	<div class="location">메뉴 관리  > <strong>메뉴 상세정보</strong></div>
	
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncMenuInfoUpdate()">수정</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="메뉴의 설정정보를 제공하는 화면입니다.">
		<caption>메뉴 설정내용</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th>메뉴 사용</th>
			<td>
		    	<input name="menuCodeArr" id="batchDlbrt" type="checkbox" title="배치심의선택" value="batchDlbrt" <c:if test="${menuInfo.batchDlbrt == 'Y'}">checked="checked"</c:if> /> 배치심의
		    	<input name="menuCodeArr" id="batchInfo" type="checkbox" title="배치정보선택" value="batchInfo" checked="checked" readonly="readonly" disabled="disabled" /> 배치정보
		    	<input name="menuCodeArr" id="batchOpr" type="checkbox" title="배치운영선택" value="batchOpr" checked="checked" readonly="readonly" disabled="disabled" /> 배치운영
		    	<input name="menuCodeArr" id="jobKnw" type="checkbox" title="Job지식선택" <c:if test="${menuInfo.jobKnw == 'Y'}">checked="checked"</c:if> value="jobKnw" /> Job지식
		    	<input name="menuCodeArr" id="com" type="checkbox" title="관리자메뉴선택" value="com" checked="checked" readonly="readonly" disabled="disabled" /> 관리자 메뉴
    		</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>

</form>
    
</body>
</html>

