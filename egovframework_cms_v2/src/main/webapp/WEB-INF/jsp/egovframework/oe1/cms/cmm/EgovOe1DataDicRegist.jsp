<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @JSP Name : EgovOe1DataDicRegist.jsp
  * @Description : 자료사전등록
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2011.07.01  이중호         최초 생성
  *
  * author 실행환경팀
  * since 2011.07.01
  *  
  * Copyright (C) 2011 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta http-equiv="Content-language" content="ko">
<title>자료사전등록</title>
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
<script type="text/javascript" src="<c:url value='/js/egovframework/oe1/cms/com/jquery-latest.js'/>"></script>

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

<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/com/validator.do'/>"></script>
<validator:javascript formName="dataDic" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript" defer="defer">

var unUseCnt = 0;
var dtaDicaryNmDup = 0;
var rowCnt   = 1;
var rowMax   = 5;
var rowCount = rowMax;
/* ********************************************************
 * 자료사전명/자료사전영문명 확인
 ******************************************************** */
 function fn_egov_get_datadic(){
	 var dtaDicaryNmView    = "";
	 var dtaDicaryEngNmView = "";
	 var dataDic = document.getElementById("dataDic");
	 
	 if(rowCount == rowMax){
		 dtaDicaryNmView    = "";
		 dtaDicaryEngNmView = "";
	 } else if(rowCount == rowMax - 1) {
		 dtaDicaryNmView    = dataDic.wordNmList.value;
		 dtaDicaryEngNmView = dataDic.wordEngAbrvNmList.value;
	 } else {
		 count = dataDic.wordNmList.length;
		 for(i=0;i<count;i++){
			 dtaDicaryNmView    += dataDic.wordNmList[i].value;
			 dtaDicaryEngNmView += (i==0 ? "":"_")+dataDic.wordEngAbrvNmList[i].value;
		 }
	 }

	 dataDic.dtaDicaryNm.value    = dtaDicaryNmView;
	 dataDic.dtaDicaryEngNm.value = dtaDicaryEngNmView;
	 if (dtaDicaryNmView == "") {
		 dtaDicaryNmView    = "&nbsp;하단의 용어 [추가] 버튼으로 입력하시오.";
		 dtaDicaryEngNmView = "&nbsp;하단의 용어 [추가] 버튼으로 입력하시오.";
	 } else {
		 dtaDicaryNmView    = "";
		 dtaDicaryEngNmView = "";
	 }
	 //document.getElementById('dtaDicaryNmView').innerHTML    = dtaDicaryNmView;
	 //document.getElementById('dtaDicaryEngNmView').innerHTML = dtaDicaryEngNmView;
	 
	 // ajax 자료사전명 중복확인
	 $.get("${pageContext.request.contextPath}/cms/metadata/req/selectDataDicNmCnt.do"
			 ,{'dtaDicaryNm': dataDic.dtaDicaryNm.value, 'dtaDicaryId': ''}                           //[2] 쿼리스트링
			 ,function(data) {
				 dtaDicaryNmDup = 0;
				 $('#dtaDicaryNmDup').html(' ');
				 if(data.cnt > 0) {
					 dtaDicaryNmDup = 1;
					 $('#dtaDicaryNmDup').html('중복-자료사전명');
					 $('#dtaDicaryNmDup').css("color","#FF4444");
					 $('#dtaDicaryNmDupClick').html('            \
							    <div id="datail_table3" style="border:1px solid c3c3c3; padding:10px;">             \
							    <table summary="자료사전명,자료사전영문명 입니다.">                                \
							        <caption>중복자료사전명 조회<\/caption>                                         \
							        <colgroup><col width="150"><col width=""><\/colgroup>                        \
							        <tr><th scope="row">자료사전ID<\/th><td>'+data.dataDic.dtaDicaryId+'<\/td><\/tr>          \
							        <tr><th scope="row">자료사전명<\/th><td>'+data.dataDic.dtaDicaryNm+'<\/td><\/tr>          \
							        <tr><th scope="row">자료사전영문명<\/th><td>'+data.dataDic.dtaDicaryEngNm+'<\/td><\/tr>   \
							    <\/table>                                                                           \
                             <\/div>                                                                             \
							 ');
					 
				 }
			 }  //[3] 콜백함수
			 ); //end $.get()
}
/* ********************************************************
 * 용어 행 추가
 ******************************************************** */
function fn_egov_add_worddicrow(wordNm,wordEngAbrvNm,useAt) {
	if (rowCount<=0){
		alert('용어조합은 최대 ' + rowMax + '개 입니다.');
		return;
	}
	table = document.getElementById('dataDicDetail');

	itr = table.rows.length;
	row = table.insertRow(itr);
	rowid = 'row'+rowCnt++;
	row.id = rowid;
	
	cell2 = row.insertCell(-1);
	cell2.align = "center";
	cell3 = row.insertCell(-1);
	cell3.align = "center";
	cell4 = row.insertCell(-1);
	cell4.align = "center";
	cell2.innerHTML = '<td><input type="hidden" name="wordNmList" value="'+wordNm+'"/>'+wordNm+'<\/td>';
	cell3.innerHTML = '<td><input type="hidden" name="wordEngAbrvNmList" value="'+wordEngAbrvNm+'"/>'+wordEngAbrvNm+'<\/td>';
	cell4.innerHTML = '<td><input type="button" value=" 삭제 " onclick="javascript:fn_egov_delete_worddicrow(\''+rowid+'\');"/><\/td>';

	if (useAt == '0'){
		cell4.innerHTML = '<td><input type="button" value=" 사용불가 " onclick="javascript:fn_egov_delete_worddicrow(\''+rowid+'\',\'0\');"/><\/td>';
		unUseCnt++;
	}
	rowCount--;
	
	fn_egov_get_datadic();
}
/* ********************************************************
 * 용어 행 삭제
 ******************************************************** */
function fn_egov_delete_worddicrow(rowid,useAt) {
	var row = document.getElementById(rowid);
    row.parentNode.removeChild(row);
    
	if (useAt == '0'){
		unUseCnt--;
	}
    rowCount++;
    
    fn_egov_get_datadic();
}

/* ********************************************************
 * 목록조회 화면으로 가기
 ******************************************************** */
function fn_egov_list(){
	var varForm				 = document.getElementById("dataDic");
	varForm.action           = "<c:url value='/cms/metadata/common/selectDataDicList.do'/>";
	varForm.submit();  
}
/* ********************************************************
 * 저장 처리
 ******************************************************** */
function fn_egov_save(){
	if (unUseCnt>0){
		alert('사용불가 용어가 있습니다. 확인 후 다시 저장하십시오.');
		return;
	}
	if (dtaDicaryNmDup > 0) {
		alert('등록된 자료사전명이 있습니다. 확인 후 다시 저장하십시오.');
		return;	
	}
	
	var varForm	= document.getElementById("dataDic");
	if(confirm("<spring:message code='common.save.msg'/>")){
		if(!validateDataDic(varForm)){ 			
			return;
		}else{
			varForm.cmd.value = "Regist";
			varForm.submit();
		}
	}
}

/* ********************************************************
 * 용어 선택 팝업 호출
 ******************************************************** */
function fn_egov_worddic_call() {
	var url   = "<c:url value='/cms/metadata/common/selectWordDicListPopup.do'/>";
	var id    = "wordDicPopup";
	var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";

	window.open(url, id, param);
}
/* ********************************************************
 * 용어 선택 팝업 콜백
 ******************************************************** */
function fn_egov_worddic_callback(wordNm,wordEngAbrvNm,useAt) {
	fn_egov_add_worddicrow(wordNm,wordEngAbrvNm,useAt);
}

/* ********************************************************
 * 도메인 팝업 호출
 ******************************************************** */
function fn_egov_domain_call() {
	var url = "<c:url value='/cms/metadata/common/selectDomainInfoListPopup.do'/>";
	var id    = "domainPopup";
	var param = "width=800px,height=500px,toolbar=no,location=no,status=no,scrollbar=yes,resizable=no,left=200,top=200";

	window.open(url, id, param);
}
/* ********************************************************
 * 도메인 팝업 콜백
 ******************************************************** */
function fn_egov_domain_callback(domnNm,dataTy,dataLt) {
	var varForm				 = document.getElementById("dataDic");
	varForm.domnNm.value     = domnNm;
	varForm.dataTy.value     = dataTy;
	varForm.dataLt.value     = dataLt;
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

			<form:form commandName="dataDic" name="dataDic" action="${pageContext.request.contextPath}/cms/metadata/admin/insertDataDic.do" method="post" >
			<input name="cmd"      type="hidden" value="<c:out value='Regist'/>"/>
			<div id="content_pop">
				<div id="h2_topnav"><h1><strong>자료사전등록</strong></h1></div>
				
				<div id="datail_table">
				<table summary="자료사전ID,자료사전명,사용여부">
					<caption>자료사전등록</caption>
					<colgroup>
						<col width="150">
						<col width="">
					</colgroup>
					<tr>
						<th scope="row"><label for="dtaDicaryId">자료사전ID</label></th>
						<td>
							자동생성
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="dtaDicaryNm">자료사전명</label></span></th>
						<td>
							<input type="text" id="dtaDicaryNm" name="dtaDicaryNm" readonly="readonly" size="60" maxlength="60" class="inputsmall02" title="자료사전명"/>
							<input type="button" value=" 추가 " onclick="fn_egov_worddic_call(); return false;"  title="새창"/>
							<!--  <div id="dtaDicaryNmView" style="float:left;">&nbsp;하단의 용어 [추가] 버튼으로 입력하시오.</div>   -->
							<div id="dtaDicaryNmDup" style="float:left;margin-left:50px;"></div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="dtaDicaryEngNm">자료사전영문명</label></span></th>
						<td>
							<input type="text" id="dtaDicaryEngNm" name="dtaDicaryEngNm" readonly="readonly" size="60" maxlength="60" class="inputsmall02" title="자료사전영문명"/>
							<!--  <div id="dtaDicaryEngNmView">&nbsp;하단의 용어 [추가] 버튼으로 입력하시오.</div>  -->
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="domnNm">도메인명</label></span></th>
						<td>
							<input type="text" name="domnNm" readonly="readonly" size="60" maxlength="60" class="inputsmall02" title="도메인명"/>
							<input type="button" name="도메인선택버튼" value="선택" onclick="fn_egov_domain_call(); return false;" title="새창">
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="dataTy">데이터유형</label></th>
						<td>
							<input type="text" name="dataTy" readonly="readonly" size="60" maxlength="60" class="inputsmall02" title="데이터유형"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="dataLt">데이터길이</label></th>
						<td>
							<input type="text" name="dataLt" readonly="readonly" size="60" maxlength="60" class="inputsmall02" title="데이터길이"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="useAt">사용여부</label></span></th>
						<td>
							<form:select path="useAt" title="사용여부">
								<form:option value="Y" label="사용"/>
								<form:option value="N" label="미사용"/>
							</form:select>
						</td>
					</tr>
					<tr>
						<th scope="row"><span class="th_add"><label for="processPrvonsh">처리사유</label></span></th>
						<td>
							<form:input  path="processPrvonsh" size="60" maxlength="60" cssClass="inputsmall01" title="처리사유"/>
							<form:errors path="processPrvonsh"/>
						</td>
					</tr>
				</table>
			    </div>

				<!-- List -->
				<div id="result_table">
				<table summary="순번, 처리일자, 처리자, 도메인명, 사용여부 목록입니다" class="table_style">
					<caption>사용자 권한 검색 결과</caption>
					<colgroup>
						<col width="*">
						<col width="250">
						<col width="100">
					</colgroup>
					
					<thead>
						<tr>
					        <th scope="col">용어명</th>
					        <th scope="col">용어영문약어명</th>
					        <th scope="col">
					        	<input type="button" value=" 추가 " onclick="fn_egov_worddic_call();"/>
							</th>
						</tr>
					</thead>
					<tbody id="dataDicDetail" >
						<tr id="rowInit"><td></td><td></td><td></td></tr>
					</tbody>
				</table>
				</div>
				<!-- /List -->	
				
				<div class="subbtn_align">          
				<ul>
				    <li class="submit_btn01_left"></li>
				    <li><span class="submit_btn01"><input type="submit" name="submit_btn" value="저장" class="submit_btn_input"  onclick="fn_egov_save(); return false;" /></span></li>
				    <li class="submit_btn01_right"></li>

				    <li class="btn02_leftbg"></li>
				    <li class="btn02_middlebg"><a href="<c:url value='/cms/metadata/common/selectDataDicList.do'/>" onclick="fn_egov_list();return false;" class="btn_link">목록</a></li>
				    <li class="btn02_rightbg"></li>					    
				</ul>
				</div>
				
				<!-- 검색조건 유지 -->
		        <input type="hidden" name="pageIndex"        value="<c:out value="${searchVO.pageIndex}"/>"        />
		        <input type="hidden" name="srchUseAt"        value="<c:out value="${searchVO.srchUseAt}"/>"        />
		        <input type="hidden" name="srchWordNm"     value="<c:out value="${searchVO.srchWordNm}"/>"     />
		        <input type="hidden" name="srchLastUpdusrNm" value="<c:out value="${searchVO.srchLastUpdusrNm}"/>" />
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

<!-- 용어중복확인용 LAYER -->
<div id="dtaDicaryNmDupClick" style="position:absolute;overflow:auto;display:none;width:380px;height:100px;z-index:10; background-color:#fafafa;"></div>
<script type="text/javaScript" language="javascript" defer="defer">
 $("#dtaDicaryNmDup").click(function(e) {
	var x = e.pageX - this.offsetLeft;
	var y = e.pageY - this.offsetTop;
 	$("#dtaDicaryNmDupClick").css("display", "");
 	$("#dtaDicaryNmDupClick").css("left", x + 60);
 	$("#dtaDicaryNmDupClick").css("top" , y - 10);
 });
 $("#dtaDicaryNmDupClick").click(function() {
 	$(this).css("display", "none");
 });
</script>
<script type="text/javaScript" language="javascript" defer="defer">
	var row = document.getElementById("rowInit");
    row.parentNode.removeChild(row);
</script>
</body>
</html>


