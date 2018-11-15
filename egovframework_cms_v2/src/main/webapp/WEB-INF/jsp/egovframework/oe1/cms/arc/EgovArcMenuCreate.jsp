<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  /**
   * @JSP Name : EgovArcMenuCreat.jsp
   * @Description : 아키텍쳐메뉴정보
   * @Modification Information
   * 
   *   수정일         수정자                   수정내용
   *  -------    --------    ---------------------------
   *  2010.07.16  김연수          최초 생성
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
<title>아키텍쳐메뉴정보</title>

<!-- style -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/jquery.checkboxtree.css' />" rel="stylesheet" type="text/css"><!-- JQuery -->
<link href="<c:url value='/css/egovframework/oe1/cms/com/jquery.treeview.css' />" rel="stylesheet" type="text/css"><!-- JQuery -->
<!-- script -->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMenuList.js' />" ></script>
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js' />" ></script>
<script type="text/javascript" language="javascript" defer="defer" ></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.treeview.js'/>"></script><!-- JQuery -->
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery.checkboxtree.js'/>"></script><!-- JQuery -->

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


<script type="text/javaScript"  language="javascript" defer="defer">
function fInsertMenuCreat() {
        document.menuCreatManageForm.action = "<c:url value='/cms/sys/insertEgovOe1MenuCreateMng.do'/>";
        document.menuCreatManageForm.submit(); 
}

function fn_list(){
    document.menuCreatManageForm.action = "<c:url value='/cms/sys/selectEgovOe1MenuCreateMngList.do'/>";
    document.menuCreatManageForm.submit(); 	
}
</script>


<!-- JQuery 트리 시작-->
<script language="javascript" type="text/javascript">
//$(document).ready(function() {
 //   jQuery("#treeview").checkboxTree({
//    checkchildren: true, 
//    checkparents: true 
//    }).treeview({
//        animated : "fast"});
//});
$(document).ready(function() {
    jQuery("#treeview").treeview({
        animated : "fast"});
});
</script>
<!-- JQuery 트리 끝-->
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
		
		<!-- form 시작 -->
		<form:form commandName="menuCreateVO" name="menuCreatManageForm" method="post">
		
			<input name="req_menuNo" type="hidden" />
			
			<!-- content_pop 시작 -->
			<div id="content_pop">

			<!-- 타이틀 -->
			<div id="h2_topnav"><h1><strong> 아키텍쳐메뉴정보</strong></h1></div>
			<!-- // 타이틀 -->
			
			<!-- 리스트 시작 -->
			<div id="datail_table">

				<c:set var="presentUpperMenuOrdr" value="0"/>
				<ul id="treeview" class="filetree">
						<c:forEach var="result" items="${list_menulist}" varStatus="status">
				
								<c:if test="${presentUpperMenuOrdr  != 0}">
									<c:if test="${result.archtcMenuOrdr > presentUpperMenuOrdr}">
										<c:if test="${result.archtcMenuOrdr - presentUpperMenuOrdr == 1}">
											<ul>
										</c:if>
										
										<c:if test="${result.archtcMenuOrdr - presentUpperMenuOrdr == 2}">
											<ul>
												<li>
													<ul>
										</c:if>
									</c:if>
								</c:if>
								
								<c:if test="${result.archtcMenuOrdr < presentUpperMenuOrdr}">
									<c:if test="${presentUpperMenuOrdr - result.archtcMenuOrdr == 1}">
												</li>
											</ul>
										</li>
									</c:if>
								
									<c:if test="${presentUpperMenuOrdr - result.archtcMenuOrdr == 2}">
															</li>
														</ul>
													</li>
											</ul>
										</li>																									
									</c:if>
								</c:if>
				
								<c:if test="${result.archtcMenuOrdr == presentUpperMenuOrdr}">
									</li>
								</c:if>
				
								<li>
									
								<c:out value="${result.archtcMenuNm}"/>
								<c:set var="presentUpperMenuOrdr" value="${result.archtcMenuOrdr}"/>
								
						</c:forEach>
							</li>
						</ul>
					</li>
				</ul>
						
			</div>
			<!-- 리스트 끝 -->

  		</div>
  		<!-- content_pop 끝 -->
  					
  		</form:form>
  		<!-- form 끝 -->
  		
		</div>	
		<!-- BODY 내용 END -->

		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
		
	</div>	
	<!-- 메인 끝 -->

</div>
<!-- //전체 DIV끝 -->

</body>
</html>

