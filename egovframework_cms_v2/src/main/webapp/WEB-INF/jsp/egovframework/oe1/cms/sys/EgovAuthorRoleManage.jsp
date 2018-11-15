<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>

<%
  /**
  * @JSP Name : EgovAuthorRoleManage.jsp
  * @Description : 권한별 롤 정보
  * @Modification Information
  * 
  *   수정일                 수정자                    수정내용
  *  -------      --------    ---------------------------
  *  2010.07.02   이중호                    최초 생성
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
<title>권한별 롤 정보</title>

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
    var checkRegYn = document.listForm.regYn;
    var returnValue = "";
    var returnRegYns = "";
    var checkedCount = 0;
    var returnBoolean = false;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkedCount++;
                    checkField[i].value = checkId[i].value;
                
	                if(returnValue == "") {
	                    returnValue = checkField[i].value;
	                    returnRegYns = checkRegYn[i].value;
	                }
	                else { 
	                    returnValue = returnValue + ";" + checkField[i].value;
	                    returnRegYns = returnRegYns + ";" + checkRegYn[i].value;
	                }
                }
            }

            if(checkedCount > 0) 
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
                returnRegYns = checkRegYn.value;

                returnBoolean = true;
            }
        }
    } else {
        alert("조회된 결과가 없습니다.");
    }

    document.listForm.roleCodes.value = returnValue;
    document.listForm.regYns.value = returnRegYns;

    return returnBoolean;

}

function fncSelectAuthorRoleList() {
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = "1";
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorList(){
    // document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = "1";
    document.listForm.searchKeyword.value = "";
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorList.do'/>";
    document.listForm.submit();
}

function fncSelectAuthorRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/cmm/sec/ram/EgovRole.do'/>";
    document.listForm.submit();     
}

function fncAddAuthorRoleInsert() {
	if(fncManageChecked()) {
	    if(confirm('<spring:message code="common.save.msg" />'))  {
	        document.listForm.authorCode.value = document.listForm.searchKeyword.value;
            document.listForm.action = "<c:url value='/cms/sys/EgovAuthorRoleInsert.do'/>";
            document.listForm.submit();
	    }
	} else return;
}

function fn_egov_link_page(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sys/EgovOe1AuthorRoleList.do'/>";
    document.listForm.submit();
}
</script>
<!-- 업무 scrpit END -->

</head>

<body>
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

			<form:form commandName="authorRoleManageVO" name="listForm" method="post">
			<div id="content_pop">			

    		    <input type="hidden" name="authorCode"/>

    		    <input type="hidden" name="roleCodes"/>
    		    <input type="hidden" name="regYns"/>
    
				<!-- 타이틀 -->
				<div id="h2_topnav"><h1><strong>권한별 롤 정보</strong></h1></div>
				<!-- /타이틀 -->

				<!-- 검색영역 -->
				<div id="search_area01">
				<ul>
					<li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="Search" class="imgs" title="검색이미지"/></li>
					<li><label style="display: none;">권한별 롤 정보 검색</label>
						<form:select path="searchCondition"  tabindex="1" title="권한별 롤 정보 검색">
							<form:option value="1" label="권한코드" />
						</form:select></li>
					<li><form:input path="searchKeyword" size="15" maxlength="30" tabindex="2" cssClass="inputsmall01_readOnly" readonly="true"  title="권한별 롤 정보 검색"/></li>
					<li><a href="#LINK" onClick="fncSelectAuthorRoleList();">
							<img src="<c:url value='/images/egovframework/oe1/cms/com/btn_search01.gif'/>" alt="검색" class="btn_search" />
						</a>
					</li>
				</ul>
				</div>
				<!-- /검색영역 -->

				<!-- List -->
				<div id="result_table">
				<table summary="롤ID, 롤명, 롤타입, 롤SORT, 롤설명, 등록일자, 등록여부    목록입니다" class="table_style">
					<caption>권한별 롤 정보 검색 결과</caption>
					
					<colgroup>
						<col width="20">
						<col width="160">
						<col width="100">
						<col width="60">
						<col width="60">
						<col width="160">
						<col width="80">
						<col width="90">
					</colgroup>
					
					<thead>
						<tr>
							<th><input type="checkbox" name="checkAll"
								id="checkbox7" onclick="javascript:fncCheckAll();"  title="선택" /></th>
					        <th>롤ID</th>
					        <th>롤명</th>
					        <th>롤타입</th>
					        <th>롤SORT</th>
					        <th>롤설명</th>
					        <th>등록일자</th>
					        <th>등록여부</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="authorRole" items="${authorRoleList}" varStatus="status">
						<tr>
							<!-- 2010.9.7 
							<td class="listtd"><label>
								<input type="checkbox" name="delYn"      id="<c:out value="${authorRole.roleCode}"/>" title="선택" />
								<input type="hidden"   name="checkId" value="<c:out value="${authorRole.roleCode}"/>" />
							</label></td>
							<td class="listtd" align="left">
								<c:out value="${authorRole.roleCode}" />
							</td>
							<td class="listtd" align="left"><c:out value="${authorRole.roleNm}" /></td>
							<td class="listtd"><c:out value="${authorRole.roleTy}" /></td>
							<td class="listtd"><c:out value="${authorRole.roleSort}"/></td>
							<td class="listtd" align="left"><c:out value="${authorRole.roleDc}"/></td>
							<td class="listtd"><c:out value="${authorRole.creatDt}"/></td>
							<td class="listtd">
                                <select name="regYn" id="<c:out value="${authorRole.roleCode}"/>">
	                            	<option value="Y" <c:if test="${authorRole.regYn == 'Y'}">selected</c:if> >등록</option>
	                            	<option value="N" <c:if test="${authorRole.regYn == 'N'}">selected</c:if> >미등록</option>
	                            </select>
							</td>
							-->
							
							<td><label>
								<input type="checkbox" name="delYn"      id="<c:out value="${authorRole.roleCode}"/>" title="선택" />
								<input type="hidden"   name="checkId" value="<c:out value="${authorRole.roleCode}"/>" />
							</label></td>
							<td align="left">
								<c:out value="${authorRole.roleCode}" />
							</td>
							<td align="left"><c:out value="${authorRole.roleNm}" /></td>
							<td><c:out value="${authorRole.roleTy}" /></td>
							<td><c:out value="${authorRole.roleSort}"/></td>
							<td align="left"><c:out value="${authorRole.roleDc}"/></td>
							<td><c:out value="${authorRole.creatDt}"/></td>
							
							<td>
                                <select name="regYn" title="등록구분">
	                            	<option value="Y" <c:if test="${authorRole.regYn == 'Y'}">selected</c:if> >등록</option>
	                            	<option value="N" <c:if test="${authorRole.regYn == 'N'}">selected</c:if> >미등록</option>
	                            </select>
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
				
				<!-- 버튼 -->
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fncAddAuthorRoleInsert(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>
				
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="목록" class="submit_btn_input"  onclick="fncSelectAuthorList(); return false;" /></span></li>
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
