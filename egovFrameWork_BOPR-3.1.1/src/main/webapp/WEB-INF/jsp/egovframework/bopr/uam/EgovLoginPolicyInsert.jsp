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
   * @Class Name : EgovLoginPolicyInsert.jsp
   * @Description : 로그인정책 등록 화면
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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/egovframework/js/common/calendarPopup.js' />"></script>
<validator:javascript formName="loginPolicyManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">


function fncSelectLoginPolicyList() {
	var varFrom = document.getElementById("loginPolicyManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovLoginPolicyList.do'/>";
	varFrom.submit();       
}

function fncLoginPolicyInsert() {

    var varFrom = document.getElementById("loginPolicyManage");
    varFrom.action = "<c:url value='/bopr/uam/EgovLoginPolicyInsert.do'/>";

    if(confirm("저장 하시겠습니까?")){
/*validate는 보류    
		if(!validateLoginPolicyManage(varFrom)){         
            return;
        }else{
            varFrom.submit();
        }  
*/
        varFrom.submit();
    }
}
</script>
</head>

<body>
<DIV id="main">
<table border="0">
  <tr>
    <td width="700">
<form:form commandName="loginPolicyManage" method="post" name="loginPolicyManage">

<table width="100%" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%" class="title_left">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle">&nbsp;사용자 로그인정책 등록
  </td>
  <th width="10%" align="right">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif' />" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncSelectLoginPolicyList()" style="selector-dummy:expression(this.hideFocus=false);" id="listBtn">목록</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>
      <td>&nbsp;&nbsp;</td>
      
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif'/>" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncLoginPolicyInsert()" style="selector-dummy:expression(this.hideFocus=false);" id="insertBtn">등록</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>    
      <td>&nbsp;&nbsp;</td>
    </tr>
   </table>
  </th>  
 </tr>
</table>

<table width="100%" cellpadding="8" class="table-line">
  <tr>
    <th class="required_text" width="20%" nowrap>사용자 ID<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="emplyrId" id="emplyrId" type="text" value="<c:out value='${loginPolicyManage.emplyrId}'/>" size="40" />&nbsp;<form:errors path="emplyrId" /></td>
  </tr>
  
  <tr>  
    <th class="required_text" width="20%" nowrap>사용자 IP<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="ipInfo" id="ipInfo" type="text" value="<c:out value='${loginPolicyManage.ipInfo}'/>" required="true" fieldTitle="사용자 IP" maxLength="50" char="s" size="40" />&nbsp;</td>
  </tr>
  
  <tr>  
    <th class="required_text" width="20%" nowrap>중복허가여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="dplctPermAt" id="dplctPermAt" type="text" value="<c:out value='${loginPolicyManage.dplctPermAt}'/>" required="true" fieldTitle="중복허가여부" maxLength="50" char="s" size="40" />&nbsp;</td>
  </tr>
  
  <tr>  
    <th class="required_text" width="20%" nowrap>IP제한여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="lmttAt" id="lmttAt" type="text" value="<c:out value='${loginPolicyManage.lmttAt}'/>" required="true" fieldTitle="IP제한여부" maxLength="50" char="s" size="40" />&nbsp;</td>
  </tr>
  
</table>

<!--
    <button type="button" onclick="javascript:fncSelectLoginPolicyList();">List</button>
    <input type="submit" value="<c:out value='${registerFlag}'/>"/>
 -->
 <input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/egovframework/com/common/normalCalendarPopup.do'/>" />
</form:form>
    <div align="right">
        <input type="text" name="message" value="<c:out value='${message}'/>" size="30" readonly />
    </div>
    
</td>
</tr>
</table>    
</DIV>
</body>
</html>

