<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui" %>
<%
  /**
   * @JSP Name : EgovMethodStructureAll.jsp
   * @Description : 메서드 구조 전체정보
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
<title>Architecture Structure</title>

<style type="text/css">
.treeview .expandable-hitarea-nodeClass { background-image: url('/oe1/images/egovframework/oe1/cms/arc/class.gif'); }
.treeview .expandable-hitarea-nodeMethod { background-image: url('/oe1/images/egovframework/oe1/cms/arc/method.gif'); }
.treeview .expandable-hitarea-nodeParam { background-image: url('/oe1/images/egovframework/oe1/cms/arc/parameter.gif'); }
.treeview .expandable-hitarea-nodeQuery { background-image: url('/oe1/images/egovframework/oe1/cms/arc/query.gif'); }
</style>

<link href="<c:url value='/css/egovframework/oe1/cms/com/common.css'/>"	rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/themes/ui-lightness/jquery.ui.all.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/css/egovframework/oe1/cms/com/jquery.treeview.css' />" rel="stylesheet" type="text/css">
 
<script type="text/javascript" language="javascript" src="<c:url value='/js/egovframework/oe1/cms/com/EgovMainMenu.js'/>"></script>
<script type="text/javascript" language="javascript" defer="defer"></script>

<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" 
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.core.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.widget.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.ui.accordion.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/egovframework/oe1/cms/com/jquery.treeview.js'/>"></script>

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>

<script type="text/javascript">
  $(document).ready(function(){
    $("#treeArea").load("<c:url value='/cms/arc/ams/seletcStructureTreeAll.do?compId='/>", 
		function() {
			$("#treeview").treeview({
		  	collapsed: true,
			animated: "fast"
	});} );

  	$("#keyword").keypress(function(e){
    	  var keyword = $("#keyword").val();
          if(e.keyCode==13) {
        	  if(keyword.length < 7){
         		alert("검색조건은 7자 이상 입력하세요");
         		return;
         	  }
      	  if(keyword.length > 30){
     			alert("검색조건은  30자 이하로 입력하세요");
     			return;
     		  }	    	  
          	$("#treeArea")
          	.load("<c:url value='/cms/arc/ams/getObjectCompnTreeAll.do?condition='/>"+$("#condition").val()+"&searchKeyword="+$("#keyword").val(),
          			function() {
          				$("#treeview").treeview({
          				animated: "fast"
          			});} );
          }
  	});
  	    
  $("#search").click(function() {
	  var keyword = $("#keyword").val();
	  if(keyword.length < 7){
		alert("검색조건은 7자 이상 입력하세요");
		return;
	  }
	  if(keyword.length > 30){
		alert("검색조건은  30자 이하로 입력하세요");
		return;
	  }	
	  $("#treeArea")
	  .load("<c:url value='/cms/arc/ams/getObjectCompnTreeAll.do?condition='/>"+$("#condition").val()+"&searchKeyword="+$("#keyword").val(),
		function() {
			$("#treeview").treeview({
			animated: "fast"
		});} );
  });

  });

  $(document).ready(function(){

	  $(".nodeComp .hitarea").live('click', function(){
			var clickedNode = $(this).parent();
			var nodeValue = clickedNode.attr("val");
			var childNode = $(this).parent().parent();
			var nodeClass = childNode.attr("class");
			var nodeexClass = $(".expandable", clickedNode);
			if("nodeComp" == nodeClass && null == nodeexClass.html()){
				$.ajax(
					{
						dataType:"json",
						url: "<c:url value='/cms/arc/ams/getObjectTree.do?objectType=2&upperObjectId='/>"+nodeValue,
						data : {"nodeValue" : nodeValue},

						success: function(data){
							$(".nodeClass", clickedNode).remove();

							var subNode = $("<ul></ul>");
							subNode.attr("id", "nodeClass");
							subNode.attr("class", "nodeClass");
							
							for( var i=0; i<data.length; i++ ){
								var temp = $("<li></li>");
								temp.attr("class", "expandable");
								temp.attr("val", data[i].id);

								temp.html(data[i].name);

								var tempDiv = $("<div></div>");
								tempDiv.attr("class", "hitarea expandable-hitarea-nodeClass lastExpandable-hitarea");

								temp.append(tempDiv);
								subNode.append(temp);
							}
							clickedNode.append(subNode);
						},

						error: function(m1, m2, m3){
					        alert("호출시 에러났음! : " + m1 + " , " + m2 + " , " + m3);
					    }
					}
				);
			}
		}
		);
	});

	  $(".treeview .expandable-hitarea-nodeClass").live('click', function(){
	  //$(".nodeClass li").live('click', function(){
			var clickedNode = $(this).parent();
			var nodeValue = clickedNode.attr("val");
							
			var childNode = $(this).parent().parent();
			var nodeClass = childNode.attr("class");
			
			var nodeexClass = $(".nodeMethod", clickedNode);

			//alert("==clickedNode=="+clickedNode.html());
			//alert("==childNode=="+childNode.html());
			//alert("==nodeClass=="+nodeClass);
			//alert("==hitArea2=="+nodeexClass.html());
			if("nodeClass" == nodeClass && null == nodeexClass.html()){
				$.ajax(
					{
						dataType:"json",
						url: "<c:url value='/cms/arc/ams/getObjectTree.do?objectType=4&upperObjectId='/>"+nodeValue,
						data : {"nodeValue" : nodeValue},
	
						success: function(data){
							$(".nodeMethod", clickedNode).remove();
	
							var subNode = $("<ul></ul>");
							subNode.attr("id", "nodeMethod");
							subNode.attr("class", "nodeMethod");
							for( var i=0; i<data.length; i++ ){
								var temp = $("<li></li>");
								temp.attr("val", data[i].id);
								temp.html(data[i].name);

								var tempDiv = $("<div></div>");
								tempDiv.attr("class", "hitarea expandable-hitarea-nodeMethod lastExpandable-hitarea");

								temp.append(tempDiv);
								subNode.append(temp);
							}
							clickedNode.append(subNode);
						},
						error: function(m1, m2, m3){
					        alert("호출시 에러났음! : " + m1 + " , " + m2 + " , " + m3);
					    }
					}
				);
			}else{
				$(".nodeMethod", clickedNode).remove();
			}
		}
	);

	$(".treeview .expandable-hitarea-nodeMethod").live('click', function(){
	//$(".nodeMethod li").live('click', function(){
		var clickedNode = $(this).parent();
		var nodeValue = clickedNode.attr("val");

		var childNode = $(this).parent().parent();
		var nodeClass = childNode.attr("class");
		var nodeexClass = $(".nodeParam", clickedNode);
		if("nodeMethod" == nodeClass && null == nodeexClass.html()){
			$.ajax(
				{
					dataType:"json",
					url: "<c:url value='/cms/arc/ams/getObjectTree.do?objectType=8&upperObjectId='/>"+nodeValue,
					data : {"nodeValue" : nodeValue},

					success: function(data){
						$(".nodeParam", clickedNode).remove();

						var subNode = $("<ul></ul>");
						subNode.attr("id", "nodeParam");
						subNode.attr("class", "nodeParam");
						
						for( var i=0; i<data.length; i++ ){
							var temp = $("<li></li>");
							temp.attr("val", data[i].id);
							temp.html(data[i].name);

							var tempDiv = $("<div></div>");
							if("Param" == data[i].name.substring(0,5)){
								tempDiv.attr("class", "hitarea expandable-hitarea-nodeParam lastExpandable-hitarea");
							}else if("Query" == data[i].name.substring(0,5)){
								tempDiv.attr("class", "hitarea expandable-hitarea-nodeQuery lastExpandable-hitarea");
							}else{
								// 쿼리나 파라미터가 없을 경우 노드를 만들지 않음
								return;
							}

							temp.append(tempDiv);
							subNode.append(temp);
						}
						clickedNode.append(subNode);
					},

					error: function(m1, m2, m3){
				        alert("호출시 에러났음! : " + m1 + " , " + m2 + " , " + m3);
				       }
				}
			);
		}else{
			$(".nodeParam", clickedNode).remove();
		}
	}
);
	  
</script>

<script type="text/javascript">

	function fn_egov_openPopup(url) {
		//window.open("<c:url value='/cms/arc/ams/getMethodDetail.do?popupAt=Y&id=' />"+rowNullAT1,"methdDetail","width=850,height=550,left=0,top=0");
		window.open(url, "detail", "width=1024,height=768,left=0,top=0, scrollbars=yes");
	}
	function fn_openInit() {
		document.detailForm.action = "<c:url value='/cms/arc/ams/getObjectCompnPkgTree.do'/>";
		document.detailForm.submit();
	}
	function fn_openClass(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getClassDetail.do?id=' />"+id);
	}

	function fn_openMethod(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getMethodDetail.do?id=' />"+id);
	}

	function fn_openParam(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getParameterDetail.do?id=' />"+id);
	}

	function fn_openQuery(id) {
		fn_egov_openPopup("<c:url value='/cms/arc/ams/getQueryDetail.do?id=' />"+id);
	}
	
	 function init(){
		<c:if test="${not empty resultMsg}">
			alert('<c:out value="${resultMsg}"/>');
		</c:if>
	}

</script>


</head>
<body onload="init();">
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
		<form:form commandName="vo" name="detailForm" method="post" onsubmit="return false;" >		
		<div id="content_pop">
		<!-- 타이틀 -->
		<div id="h2_topnav"><h1><strong> 아키텍쳐 정보 조회</strong></h1></div>
		<!-- // 타이틀 -->
		
		<!-- 검색 시작  -->
		<div class="search_area_submit">
	    <ul>
	       <li><img src="<c:url value='/images/egovframework/oe1/cms/com/img_search01.gif'/>" alt="search" class="searchimg" /></li>
	       <li>
			<select id="condition" title="condition" >
				<option value="2">클래스</option>
				<option value="4">메소드</option>
				<option value="16">쿼리</option>
			</select>
	       </li>
	       <li>
	         <input name="keyword" id="keyword" size="30" maxlength="30" class="input01"  style="width:200px;" title="keyword"/>
	       </li>
	       <li>             
	           <div class="submit_gray_btn_top">       
	              <ul>
	                  <li class="submit_gray_btn_left"></li>
	                  <li><span class="submit_gray_btn_middle"><input type="button" value="검색" id="search" class="submit_btn_input" /></span></li>
	                  <li class="submit_gray_btn_right"></li>
	              </ul>
	          </div>  
	       </li>
	     </ul>   
	 	</div>	
		<!-- 검색 끝 -->

			<div id="treeArea">
			Loading...
			</div> 

		</div>
		<a href="#top_menu" class="hide_a"> </a>
		</form:form>
		</div>
		<!-- 카피라이트 시작 -->
		<div id="footer"><jsp:include page="/WEB-INF/jsp/egovframework/oe1/cms/com/EgovBottom.jsp" /></div>
		<!-- //카피라이트 끝 -->
	</div>
</div>
</body>

</html>

