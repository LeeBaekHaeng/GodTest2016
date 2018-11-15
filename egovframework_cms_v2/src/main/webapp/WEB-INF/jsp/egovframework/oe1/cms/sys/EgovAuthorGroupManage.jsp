<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovAuthorGroupManage.jsp
  * @Description : 권한그룹 관리
  * @Modification Information
  * 
  *   수정일        	        수정자                   수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02    이중호                   최초 생성
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
<title>권한그룹 관리</title>

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
	var resultCheck = false;

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var selectAuthor = document.listForm.authorManageCombo;
    var booleanRegYn = document.listForm.regYn;
        
    var returnId = "";
    var returnAuthor = "";
    var returnRegYn = "";

    var checkedCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnId == "") {
                        returnId = checkField[i].value;
                        returnAuthor = selectAuthor[i].value;
                        returnRegYn = booleanRegYn[i].value;
                    }
                    else {
                    	returnId = returnId + ";" + checkField[i].value;
                    	returnAuthor = returnAuthor + ";" + selectAuthor[i].value;
                    	returnRegYn = returnRegYn + ";" + booleanRegYn[i].value;
                    }
                }
            }
            if(checkedCount > 0) { 
            	resultCheck = true;
            } else {
                alert("선택된  항목이 없습니다.");
                resultCheck = false;
            }
        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("선택 항목이 없습니다.");
                resultCheck = false;
            }
            else {
                returnId = checkId.value;
                returnAuthor = selectAuthor.value;
                returnRegYn = booleanRegYn.value;

                resultCheck = true;
            }
        } 
        
    } else {
        alert("조회된 결과가 없습니다.");
    }
    document.listForm.userIds.value = returnId;
    document.listForm.authorCodes.value = returnAuthor;
    document.listForm.regYns.value = returnRegYn;
    return resultCheck;
}

function fncSelectAuthorGroupList(){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = "1";
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorGroupList.do'/>";
    document.listForm.submit();
}

function fncAddAuthorGroupInsert() {
	if(!fncManageChecked()){
		return false;
	}
    if(confirm("<spring:message code="common.save.msg"/>")) {
        document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorGroupInsert.do'/>";
        document.listForm.submit();
    }
}

function fncAuthorGroupDeleteList() {
 
	if(!fncManageChecked()) return;

    if(confirm("<spring:message code="common.delete.msg"/>")) {
        document.listForm.action = "<c:url value='/cms/sys/EgovAuthorGroupDelete.do'/>";
        document.listForm.submit(); 
    }
}

function fn_egov_link_page(pageNo){
    //document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorGroupList.do'/>";
    document.listForm.submit();
}

function onSearchCondition() {
	document.listForm.searchKeyword.value = "";
	if(document.listForm.searchCondition.value == '3') {
        document.listForm.searchKeyword.readonly = true;
	} else {
		document.listForm.searchKeyword.readonly = false;
	}
}
</script>
<!-- 업무 scrpit END -->

</head>

<body onload="document.listForm.searchKeyword.focus();">
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

			<form:form commandName="authorGroupVO" name="listForm" method="post" onsubmit="fncSelectAuthorGroupList(); return false;">
			<div id="content_pop">			

			    <input type="hidden" name="userIds"/>
			    <input type="hidden" name="authorCodes"/>
			    <input type="hidden" name="regYns"/>

				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>권한그룹 관리</strong></h1></div>
				<!-- /타이틀 -->

				<!-- 검색영역 -->
				<div id="search_area01">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색"/></li>
					<li><label style="display: none;">권한그룹 검색</label>
						<form:select path="searchCondition"  title="검색조건" >
							<form:option value="2" label="사용자명" />
							<form:option value="1" label="사용자ID" />
						</form:select></li>
					<li><form:input path="searchKeyword" size="15" maxlength="30" title="검색" /></li>
					<li>
						
						<a href="#LINK" onClick="fncSelectAuthorGroupList(); return false;">
							<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
						</a>
					</li>
				</ul>
				</div>
				<!-- /검색영역 -->

				<!-- List -->
				<div id="result_table">
				<table class="table_style" summary="선택, 사용자ID, 사용자명, 권한명, 등록여부  목록입니다">
					<caption>권한그룹 검색 결과</caption>
					
					<colgroup>
						<col width="50">
						<col width="200">
						<col width="200">
						<col width="180">
						<!--<col width="90">-->
					</colgroup>
					
					<thead>
						<tr>
							<th scope="col"><input type="checkbox" name="checkAll"
								id="checkbox7" onclick="javascript:fncCheckAll();" title="전체선택" /></th>
					        <th scope="col">사용자ID</th>
					        <th scope="col">사용자명</th>
					        <th scope="col">권한명</th>
					        <th scope="col">등록여부</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${empty authorGroupList}"><tr><td colspan="5">검색 결과가 없습니다.</td></tr></c:if>
					<c:forEach var="authorGroup" items="${authorGroupList}" varStatus="status">
						
						<tr>
							<td><label>
								<input type="checkbox" name="delYn"      id="<c:out value="${authorGroup.uniqId}"/>" title="선택"  />
								<input type="hidden"   name="checkId" value="<c:out value="${authorGroup.uniqId}"/>" />
							</label></td>
							<td align="left">
								<c:out value="${authorGroup.userId}" />
							</td>
							<td align="left"><c:out value="${authorGroup.userNm}" /></td>
							<td>
                                <select name="authorManageCombo" id="sel_${authorGroup.uniqId}" title="권한명">
                                <c:if test="${authorGroup.regYn == 'Y'}">
	                            	<c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
		       							<option value="<c:out value="${authorManage.authorCode}"/>" <c:if test="${authorManage.authorCode == authorGroup.authorCode}">selected='selected'</c:if>><c:out value="${authorManage.authorNm}"/></option>
		       						</c:forEach>
		       					</c:if>
	                            <c:if test="${authorGroup.regYn == 'N'}">
	                            	<option value="" selected='selected'>미등록</option>
   	                            	<c:forEach var="authorManage" items="${authorManageList}" varStatus="status">
		       							<option value="<c:out value="${authorManage.authorCode}"/>" <c:if test="${authorManage.authorCode == authorGroup.authorCode}"></c:if>><c:out value="${authorManage.authorNm}"/></option>
		       						</c:forEach>
	                            </c:if>
	                            </select>
							</td>
							<td>
								<c:if test="${authorGroup.regYn == 'Y'}">등록</c:if>
								<c:if test="${authorGroup.regYn == 'N'}">미등록</c:if>
								<input type="hidden" name="regYn" value="<c:out value="${authorGroup.regYn}"/>" />
							</td>
						</tr>
						
					</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- /List -->				
				
				<!-- 페이지 NAVI -->
				<div id="pagenav_div"><ui:pagination
					paginationInfo="${paginationInfo}" type="image"
					jsFunction="fn_egov_link_page" /> <form:hidden
					path="pageIndex" /></div>
				<!-- /페이지 NAVI -->
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fncAddAuthorGroupInsert(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="삭제" class="submit_btn_input"  onclick="fncAuthorGroupDeleteList(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				</ul>
				</div>
								
				<!-- /버튼 -->				

			</div>
			</form:form>

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
