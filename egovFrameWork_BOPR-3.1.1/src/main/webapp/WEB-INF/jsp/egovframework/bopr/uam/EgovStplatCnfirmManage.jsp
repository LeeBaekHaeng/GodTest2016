<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovStplatCnfirmManage.jsp
  * @Description : 약관관리 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.31    조재영          최초 생성
  *
  *  @author 공통서비스 개발팀 조재영
  *  @since 2009.03.31
  *  @version 1.0
  *  @see
  *  
  */
%>
<script type="text/javaScript" language="javascript" defer="defer">
function fnUpdate(){
	if(confirm("수정하시겠습니까?")){
		document.stplatForm.action = "<c:url value='/uss/umt/stplatCnfirmUpdate.do'/>";
	    document.stplatForm.submit();	
	}
}

</script>

<form name="stplatForm" method="post" action="">
<div class="contsBody">
	<h2>회원가입 약관관리</h2>
	<div class="location">사용자 관리 > <strong>회원가입 약관관리</strong></div>
	
	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fnUpdate();" title="해당 약관을 수정">수정</a></span>
	</div>
	
	<div class="bbsDetail">
		<table summary="등록자, 등록일시, 수정자, 수정일시, 약관동의내용, 정보사용동의내용 순으로 보여지는 사용자관리 회원가입 약관관리 입니다.">
		<colgroup>
			<col style="width:100" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<c:forEach var="result" items="${stplatList}" varStatus="status">
		<tr>
	       	<th>등록자</th>
	       	<td><c:out value="${result.frstRegisterId}"/></td>
	       	<th>등록일시</th>
	       	<td><c:out value="${result.frstRegistPnttm}"/></td>
	    </tr>
        <tr>
	       	<th>수정자</th>
	       	<td><c:out value="${result.lastUpdusrId}"/></td>
	       	<th>수정일시</th>
	       	<td><c:out value="${result.lastUpdtPnttm}"/></td>
	    </tr>
	    <tr>
	    	<th>약관내용</th>
			
			<td colspan="3">
				<label for="useStplatCn" class="disp_none">약관내용</label>
                <textarea name="useStplatCn" id="useStplatCn" cols="150" rows="10"><c:out value="${result.useStplatCn}" escapeXml="false" /></textarea>
            </td>
        </tr>
        <tr>
        	<th>정보동의내용</th>
        	
        	<td colspan="3">
        		<label for="infoProvdAgeCn" class="disp_none">정보동의내용</label>
                <textarea name="infoProvdAgeCn" id="infoProvdAgeCn" cols="150" rows="10"><c:out value="${result.infoProvdAgeCn}" escapeXml="false" /></textarea>
            </td>
        </tr>
        </c:forEach>
        
		</tbody>
		</table>
	</div>
	
	
</div>
<br><br>

</form>
