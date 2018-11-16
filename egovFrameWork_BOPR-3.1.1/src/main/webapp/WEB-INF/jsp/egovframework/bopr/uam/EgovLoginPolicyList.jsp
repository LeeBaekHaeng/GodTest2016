<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginPolicyList.jsp
  * @Description : 로그인정책 관리 화면
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SYSTEM HISTORY</title>

<script type="text/javaScript" language="javascript" >

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";

    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                	    returnValue = returnValue + ";" + checkField[i].value;
                    checkCount++;
                }
            }
            if(checkCount > 0) 
                returnBoolean = true;
            else {
                alert("선택된 권한이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 권한이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.emplyrId.value = returnValue;

    return returnBoolean;
}

function fncSelectLoginPolicyList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicyList.do'/>";
    document.listForm.submit();
}

function fncSelectLoginPolicy(emplyrId) {
    document.listForm.emplyrId.value = emplyrId;
    document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicy.do'/>";
    document.listForm.submit();     
}

function fncAddLoginPolicyInsert() {
    location.replace("<c:url value='/bopr/uam/EgovLoginPolicyInsertView.do'/>"); 
}

function fncLoginPolicyDeleteList() {

    if(fncManageChecked()) {	
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicyListDelete.do'/>";
            document.listForm.submit();
        } 
    }
}

function fncAddLoginPolicyView() {
    document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicyUpdate.do'/>";
    document.listForm.submit();     
}
// TODO job
function fncSelectLoginPolicyRole(author) {
	
    document.listForm.searchKeyword.value = author;
    document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicyList.do'/>";
    document.listForm.submit();     
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/bopr/uam/EgovLoginPolicyList.do'/>";
    document.listForm.submit();
}


function press() {

    if (event.keyCode==13) {
    	fncSelectAuthorList('1');
    }
}


</script>

</head>

<body>
<DIV id="main" style="display:">

<table border="0">
  <tr>
    <td width="700">

<form name="listForm" action="<c:url value='/egovframework/uam/EgovLoginPolicyList.do'/>" method="post">
<table width="100%" cellpadding="8" class="table-search" border="0">
 <tr>
  <td width="25%"class="title_left">
   <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle">&nbsp;로그인정책관리</td>

  <td width="55%">사용자 ID : <input name="searchKeyword" type="text" value="<c:out value="${loginPolicyManageVO.searchKeyword}"/>" size="25" title="검색" onkeypress="press();" /></td>
  <th width="20%">
   <table border="0" cellspacing="0" cellpadding="0">
    <tr> 
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif' />" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncSelectLoginPolicyList('1')" style="selector-dummy:expression(this.hideFocus=false);" id="searchBtn">조회</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>
      <td>&nbsp;&nbsp;</td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif'/>" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncAddLoginPolicyInsert()" style="selector-dummy:expression(this.hideFocus=false);" id="insertBtn">등록</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>    
      <td>&nbsp;&nbsp;</td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif'/>" width="8" height="20"></td>
      <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap><a href="javascript:fncLoginPolicyDeleteList()" style="selector-dummy:expression(this.hideFocus=false);" id="deleteBtn">삭제</a> 
      </td>
      <td><img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20"></td>           
    </tr>
   </table>
  </th>  
 </tr>
</table>
<table width="100%" cellpadding="8" class="table-line">
 <thead>
  <tr>
    <th class="title" width="3%" nowrap><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()"></th>  
    <th class="title" width="15%" nowrap>사용자 ID</th>
    <th class="title" width="25%" nowrap>사용자 IP</th>
    <th class="title" width="20%" nowrap>IP제한여부</th>
    <th class="title" width="20%" nowrap>중복허가여부</th>
    <th class="title" width="15%" nowrap>등록일자</th>
    <th class="title" width="5%" nowrap>사용자 정보</th>
  </tr>
 </thead>
 <tbody>
 <c:forEach var="loginPolicy" items="${loginPolicyList}" varStatus="status">
  <tr>
    <td class="lt_text3" nowrap><input type="checkbox" name="delYn" class="check2"><input type="hidden" name="checkId" value="<c:out value="${loginPolicy.emplyrId}"/>" /></td>
    <td class="lt_text" nowrap><a href="javascript:fncSelectLoginPolicy('<c:out value="${loginPolicy.emplyrId}"/>')"><c:out value="${loginPolicy.emplyrId}"/></a></td>
    <td class="lt_text" nowrap><c:out value="${loginPolicy.ipInfo}"/></td>
    <td class="lt_text3" nowrap><c:out value="${loginPolicy.lmttAt}"/></td>
    <td class="lt_text3" nowrap><c:out value="${loginPolicy.dplctPermAt}"/></td>
    <td class="lt_text3" nowrap><c:out value="${loginPolicy.frstRegistPnttm}"/></td>
    <td class="lt_text3" nowrap><a href="javascript:fncSelectLoginPolicy('<c:out value="${loginPolicy.emplyrId}"/>')"><img src="<c:url value='/images/egovframework/com/cmm/icon/search.gif'/>" width="15" height="15" border="0" align="absmiddle"  alt="사용자 정보"></a></td>
    
  </tr>
 </c:forEach>
 </tbody> 

 <!--tfoot>
  <tr class="">
   <td colspan=6 align="center"></td>
  </tr>
 </tfoot -->
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td height="10"></td>
  </tr>
</table>

<c:if test="${!empty loginPolicyManageVO.pageIndex }">
<div align="center">
    <div>
        <ui:pagination paginationInfo = "${paginationInfo}"
            type="default"
            jsFunction="linkPage"
            />
    </div>
    <div align="right">
        <input type="text" name="message" value="<c:out value='${message}'/>" size="30" readonly />
    </div>
</div>
</c:if>
<input type="hidden" name="emplyrId"/>
<input type="hidden" name="pageIndex" value="<c:out value='${loginPolicyManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form>
</td>
</tr>
</table>
</DIV>
</body>
</html>
