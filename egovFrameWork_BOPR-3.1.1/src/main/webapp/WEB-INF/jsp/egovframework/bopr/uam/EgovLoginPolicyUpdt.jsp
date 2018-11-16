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
<validator:javascript formName="loginPolicyManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectLoginPolicyList() {
	var varFrom = document.getElementById("loginPolicyManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovLoginPolicyList.do'/>";
	varFrom.submit();       
}

function fncLoginPolicyUpdate() {
	var varFrom = document.getElementById("loginPolicyManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovLoginPolicyUpdate.do'/>";
/* validate는 보류
    if(confirm("수정 하시겠습니까?")){
        if(!validateLoginPolicyManage(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
*/
    varFrom.submit();
}

function fncLoginPolicyDelete() {
	var varFrom = document.getElementById("loginPolicyManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovLoginPolicyDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
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
<form:form commandName="loginPolicyManage" method="post" >

<table width="100%" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="40%" class="title_left">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle">&nbsp;사용자 로그인정책 수정
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
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncLoginPolicyUpdate()" style="selector-dummy:expression(this.hideFocus=false);" id="updateBtn">수정</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>    
      <td>&nbsp;&nbsp;</td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif'/>" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncLoginPolicyDelete()" style="selector-dummy:expression(this.hideFocus=false);" id="deleteBtn">삭제</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>           

    </tr>
   </table>
  </th>  
 </tr>
</table>

<table width="100%" cellpadding="8" class="table-line">
  <tr>
    <th class="required_text" width="20%" nowrap>사용자 ID<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap>${loginPolicyManage.emplyrId}<input type="hidden" name="emplyrId" value="<c:out value='${loginPolicyManage.emplyrId}'/>"/></td>
     
  </tr>
  <tr>  
    <th class="required_text" width="20%" nowrap>사용자 IP<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="ipInfo" id=ipInfo type="text" value="<c:out value='${loginPolicyManage.ipInfo}'/>" required="true" fieldTitle="사용자 IP" maxLength="50" char="s" size="40" />&nbsp;</td>
    
  </tr>
  <tr>  
    <th class="required_text" width="20%" nowrap>중복허가여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="dplctPermAt" id="dplctPermAt" type="text" value="<c:out value='${loginPolicyManage.dplctPermAt}'/>" required="true" fieldTitle="중복허가여부" maxLength="50" char="s" size="40" />&nbsp;</td>
    
  </tr>
   <tr>  
    <th class="required_text" width="20%" nowrap>IP제한여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"></th>
    <td nowrap><input name="lmttAt" id="lmttAt" type="text" value="<c:out value='${loginPolicyManage.lmttAt}'/>" required="true" fieldTitle="IP제한여부" maxLength="50" char="s" size="40" />&nbsp;</td>
  </tr>
  <tr>  
    <th class="required_text" width="20%" nowrap>최초등록자ID</th>
    <td nowrap><input name="mbtlnum" id="mbtlnum" type="text" value="<c:out value="${loginPolicyManage.frstRegisterId}"/>" required="true" fieldTitle="최초등록자ID" maxLength="50" char="s" size="20"/></td>
  </tr>
  
  <tr>  
    <th class="required_text" width="20%" nowrap>최초등록시간</th>
    <td nowrap><input name="adres" id="adres" type="text" value="<c:out value="${loginPolicyManage.frstRegistPnttm}"/>" required="true" fieldTitle="최초등록시간" maxLength="50" char="s" size="20"/></td>
  </tr>
  
  <tr>  
    <th class="required_text" width="20%" nowrap>최종수정자ID</th>
    <td nowrap><input name="imageFile" id="imageFile" type="text" value="<c:out value="${loginPolicyManage.lastUpdusrId}"/>" required="true" fieldTitle="최종수정자ID" maxLength="50" char="s" size="20"/></td>
  </tr>
  
   <tr>  
    <th class="required_text" width="20%" nowrap>최종수정시간</th>
    <td nowrap><input name="email" id="email" type="text" value="<c:out value="${loginPolicyManage.lastUpdtPnttm}"/>" required="true" fieldTitle="이메일" maxLength="최종수정시간" char="s" size="20"/></td>
  </tr>
</table>

<!--
    <button type="button" onclick="javascript:fncSelectLoginPolicyList();">List</button>
    <input type="submit" value="<c:out value='${registerFlag}'/>"/>
 -->
<!-- 검색조건 유지 -->
<c:if test="${registerFlag == 'UPDATE'}">
<input type="hidden" name="searchCnd" value="<c:out value='${loginPolicyManageVO.searchCnd}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${loginPolicyManageVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${loginPolicyManageVO.pageIndex}'/>"/>
</c:if>
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

