<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
  /**
   * @JSP Name : EgovMsgListPopup.jsp
   * @Description : 메시지 조회 팝업
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.02  김정수          최초 생성
   *
   * author 운영환경 1팀 
   * Copyright (C) 2010 by MOPAS  All right reserved.
   *  
   */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-language" content="ko">
<title>메시지 조회 팝업</title>
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>"
	rel="stylesheet" type="text/css">

<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
	
<script type="text/javaScript" language="javascript" defer="defer">
<!--

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.msgManage.popupAt.value = "Y";
	document.msgManage.action = "<c:url value='/cms/arc/selectMsgListPopUp.do'/>";
   	document.msgManage.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.msgManage.pageIndex.value = pageNo;
	document.msgManage.popupAt.value = "Y";
	document.msgManage.action = "<c:url value='/cms/arc/selectMsgListPopUp.do'/>";
   	document.msgManage.submit();
}

function fn_egov_select(id){
	opener.document.getElementById('mssageId').value = id; 
	window.close();
}


-->
</script>
</head>
<body>
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

	
<form:form commandName="msgManage" name="msgManage" method="post" onsubmit="fn_egov_selectList(); return false;">
<input type="hidden" name="mssageId" />
<input type="hidden" name="popupAt" />

<div id="content_pop">
	<!-- 타이틀 -->
	<div id="h2_topnav"><h1><strong> 메시지 목록</strong></h1></div>
	<!-- // 타이틀 -->
	<div class="search_area_submit">
    <ul>
       <li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="searchimg" /></li>
       <li>
       <label style="display: none;">메세지리스트목록  검색</label>
           <select name="searchCondition" id="searchCondition" title="searchCondition" >
           <option value='0' <c:if test="${msgManage.searchCondition == '0'}">selected="selected"</c:if>>메시지명</option>
           <option value='1' <c:if test="${msgManage.searchCondition == '1'}">selected="selected"</c:if>>메시지ID</option>
          </select>
       </li>
       <li>
         <input name="searchKeyword" id="searchKeyword" maxlength="20" value="<c:out value='${msgManage.searchKeyword}'/>" class="input01"  style="width:150px;"  title="searchKeyword"/>
       </li>
       <li>                
           <div class="submit_gray_btn_top">       
              <ul>
                  <li class="submit_gray_btn_left"></li>
                  <li><span class="submit_gray_btn_middle"><input type="submit" value="검색" onclick="fn_egov_selectList(); return false;" class="submit_btn_input" /></span></li>
                  <li class="submit_gray_btn_right"></li>
              </ul>
          </div>  
       </li>
     </ul>   
 	</div>
	<!-- List -->
	<div id="result_table_p">
		<table summary="메세지ID, 메세지명, 설명, 등록일입니다." class="table_style">
		<caption>메시지 목록</caption>
			<colgroup>				
				<col width="70">
				<col width="100">
				<col width="200">
				<col width="50">
			</colgroup>		 
			<thead>	   
			<tr>
				<th align="center" scope="col">메시지ID</th>
				<th align="center" scope="col">메시지명</th>
				<th align="center" scope="col">설명</th>
				<th align="center" scope="col">등록일</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
			
				<!-- 2010.9.6 
				<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.mssageId}"/>')"><c:out value="${result.mssageId}"/></a></td>
				<td align="left" class="list_td_left"><c:out value="${result.mssageNm}"/>&nbsp;</td>
				<td align="left" class="list_td_left"><c:out value="${result.mssageDesc}"/>&nbsp;</td>
				<td align="left" class="list_td_left"><c:out value="${result.frstRegisterPnttm}"/>&nbsp;</td>
				-->
				
				<td align="center"><a href="javascript:fn_egov_select('<c:out value="${result.mssageId}"/>')"><c:out value="${result.mssageId}"/></a></td>
				<td align="left" class="list_td_left"><c:out value="${result.mssageNm}"/>&nbsp;</td>
				<td align="left" class="list_td_left"><c:out value="${result.mssageDc}"/>&nbsp;</td>
				<td align="left" class="list_td_left"><c:out value="${result.frstRegisterPnttm}"/>&nbsp;</td>

			</tr>
			</c:forEach>
			
			 <c:if test="${fn:length(resultList) == 0}">
			  <tr>
			    <td align="center" class="listtd" colspan="4">검색된 데이터가 없습니다.</td>  
			  </tr>		 
			 </c:if>
			</tbody>			 			
		</table>
	</div>
	<!-- /List -->
	<div id="pagenav_div"><ui:pagination
		paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_egov_link_page" /> <form:hidden
		path="pageIndex" /></div>

</div>
</form:form>
	
</body>
</html>
