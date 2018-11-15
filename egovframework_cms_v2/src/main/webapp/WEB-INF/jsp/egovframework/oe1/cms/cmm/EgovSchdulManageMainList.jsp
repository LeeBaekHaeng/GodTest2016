<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovIndvdlSchdulManageMainList.jsp
  * @Description : 메인페이지/부서일정조회 
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2010.07.16  김민수          최초 생성
  *
  * author 운영환경 1팀
  * since 2010.07.16
  * Copyright (C) 2010 by MOPAS  All right reserved.
  *  
  */
%>
<script type="text/javaScript" language="javascript">
function fn_egov_DeptSchdulManage(schdulId){
	window.showModalDialog("/cop/smt/sdm/EgovOe1DeptSchdulManageDetail.do?schdulId=" + schdulId , self ,"dialogWidth=800px;dialogHeight=500px;resizable=yes;center=yes");
}
</script>
<!-- 부서일정관리정보 시작 height="80" -->
<table border="0" bgcolor="FFFFFF" width="430" >
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<tr>
	<td><a href="#" onClick="fn_egov_DeptSchdulManage('${resultInfo.schdulId}')">${resultInfo.toDay}  ${resultInfo.schdulNm}</a></td>
</tr>
</c:forEach>
</table>
<!-- 부서일정관리정보 종료 -->