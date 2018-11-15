<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovRoleManage.jsp
  * @Description : 롤 관리
  * @Modification Information
  * 
  *   수정일                   수정자                 수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    김아름                 최초 생성
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
<title>롤 관리</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">

<!-- script -->
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("#accordion").accordion({
		collapsible: true
	});	
$("#accordion").css({'background':'#f1f1f1','font-family':'굴림','border':'1px solid #d5d5d5','height':'390px'});	
$(".table_style tr").mouseover(function() {$(this).addClass("over");}).mouseout(function() {$(this).removeClass("over");});

$("div.linkdiv a").click(function(){ 	
	var topurl=$(this).attr("href");
	$('#content').load(topurl);
	});	
			
	$(".image_rollover").mouseover(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //ff
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "ff") $(this).attr("src", file_name + "n." + file_type);
	}).mouseout(function(){
	     var temp = $(this).attr("src");
	     var length = temp.length;
	     var file_name = temp.substring(0, length-6);
	     var status = temp.substring(length-6).substring(0,2); //on
	     var file_type = temp.substring(length-6).substring(3);
	     if (status == "on") $(this).attr("src", file_name + "off." + file_type);
	});			
});	
</script>

<!-- 업무 scrpit START -->
<script type="text/javaScript" language="javascript" defer="defer">
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
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else 
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0) 
                returnBoolean = true;
            else {
                alert("선택된  롤이 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 롤이 없습니다.");
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

    document.listForm.roleCodes.value = returnValue;
    return returnBoolean;
}

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1"; 
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1RoleList.do'/>";
    document.listForm.submit();
}

function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1Role.do'/>";
    document.listForm.submit();     
}

function fncAddRoleInsert() {
    document.listForm.searchCondition.value = "1";
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1RoleInsertView.do'/>";
    document.listForm.submit();	
}

function fncRoleListDelete() {
	if(fncManageChecked()) {
        if(confirm('<spring:message code="common.delete.msg" />')) {    
            document.listForm.action = "<c:url value='/cms/sys/EgovOe1RoleListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1RoleUpdate.do'/>";
    document.listForm.submit();     
}

function fn_egov_link_page(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1RoleList.do'/>";
    document.listForm.submit();
}

function init(){
	<c:if test="${not empty resultMsg}">
		alert('<c:out value="${resultMsg}"/>');
	</c:if>
}
</script>
<!-- 업무 scrpit END -->

</head>

<body onload="init();document.listForm.searchKeyword.focus();">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>	

<!-- 전체 레이어 시작 -->
<div id="wrap">

	<!-- header 시작 -->
	<div id="header"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovTop.jsp" /></div>
	<div id="topnavi">
		<c:import url="/cms/com/EgovOe1BarMenu.do" charEncoding="utf-8"/>
	</div>	
	<!-- //header 끝 -->	

	<!-- 메인 시작 -->
	<div id="container">
		<!-- 좌메뉴 시작 -->
		<div id="leftmenu">
			<c:import url="/cms/com/EgovOe1LeftMenu.do" charEncoding="utf-8"/>
		</div>
		<!-- 좌메뉴 끝 -->
		
		<div id="content">
		<!-- BODY 내용 START -->

			
			<div id="content_pop">			

				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>롤 관리</strong></h1></div>
				<!-- /타이틀 -->

				<form:form name="listForm" method="post" onsubmit="fncSelectRoleList('1'); return false;">
				<input type="hidden" name="roleCode"/>
				<input type="hidden" name="roleCodes"/>
				<input type="hidden" name="pageIndex" value="<c:out value="${searchVO.pageIndex}"/>" />
				<!-- 검색영역 -->
				<div class="search_area_submit">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="searchimg" title="검색"/></li>
					
					<li>
						<select name="searchCondition" title="searchCondition">
							<option value="1" />롤명</option>
						</select>
					</li>
	
					<li><input name="searchKeyword" value="${searchVO.searchKeyword}" size="15" class="input01" maxlength="30" title="롤명"/></li>
					<li>				
						<ul>
						    <li class="submit_btn01_left"></li>
						    <li><input type="submit" value="검색" onclick="fncSelectRoleList('1'); return false;" class="submit_btn01"></li>
						    <li class="submit_btn01_right"></li>
						</ul>	
					</li>
				</ul>	
				</div>
				<!-- /검색영역 -->

				<!-- List -->
				<div id="result_table">
				<table summary="롤ID, 롤명, 롤설명, 등록일자   목록입니다" class="table_style">
					<caption>메시지코드 목록 검색 결과</caption>
					
					<colgroup>
						<col width="20">
						<col width="160">
						<col width="150">
						<col width="200">
						<col width="80">
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col"><input type="checkbox" name="checkAll" id="checkbox7" onclick="javascript:fncCheckAll();" title="전체선택"/></th>
					        <th scope="col">롤ID</th>
							<th scope="col">롤명</th>
							<th scope="col">롤설명</th>
							<th scope="col">등록일자</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty roleList}"><tr><td colspan="5">검색 결과가 없습니다.</td></tr></c:if>
					<c:forEach var="role" items="${roleList}" varStatus="status">
						<tr>
							<td><label>
								<input type="checkbox" name="delYn"      id="<c:out value="${role.roleCode}"/>"  title="선택"/>
								<input type="hidden"   name="checkId" value="<c:out value="${role.roleCode}"/>" />
							</label></td>
							<td scope="row">
							
								<!-- 2010.11.11 
								<a href="#Link" onclick="fncSelectRole('<c:out value="${role.roleCode}"/>'); return false;" class="board_text_link">
									<c:out value="${role.roleCode}" />
								</a>
								-->
								
								<a href="<c:url value='/cms/sys/EgovOe1Role.do'/>?roleCode=<c:out value='${role.roleCode}'/>" onclick="fncSelectRole('<c:out value="${role.roleCode}"/>'); return false;" class="board_text_link" title="상세조회" >
									<c:out value="${role.roleCode}" />
								</a>
								
							</td>
							<td align="left"><c:out value="${role.roleNm}" /></td>
							<td align="left"><c:out value="${role.roleDc}"/></td>
							<td><c:set var="CreatDe" value="${role.roleCreatDe}"/>
								<c:out value="${fn:substring(CreatDe,0,4)}"/>-<c:out value="${fn:substring(CreatDe,4,6)}"/>-<c:out value="${fn:substring(CreatDe,6,8)}"/>
							</td>
							
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- /List -->				
				</form:form>
				
				<!-- 페이지 NAVI -->
				<div id="pagenav_div"><ui:pagination
					paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" /></div>
				<!-- /페이지 NAVI -->
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="등록" class="submit_btn_input"  onclick="fncAddRoleInsert(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fncRoleListDelete(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				</ul>
				</div>
				
				<!-- /버튼 -->				

			</div>

		<!-- BODY 내용 END -->
		</div>	

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>
