<%--
  Class Name : EgovWebEditor.jsp
  Description : 웹에디터(HTMLAREA)를 사용하는 jsp (테스트용)
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.05    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.02.05

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<script type="text/javascript">
_editor_area = "htmlarea";
_editor_url = "<c:url value='/html/egovframework/com/cmm/utl/htmlarea3.0/'/>";
</script>
<script type="text/javascript" src="/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.js"></script>
<%
String execFlag = request.getParameter("execFlag");
String savedata = "";
if(execFlag==null || execFlag.equals("")) {
    execFlag="SAVE";
}
%>

<script type="text/JavaScript">
function goSaveData() {
	document.edit.onsubmit();
	var htmldata = document.edit.htmlarea.value;
	fm.htmldata.value = htmldata;
	fm.action = "EgovPageLink.do";
	fm.submit();
}
</script>

<%
if(execFlag.equals("SAVE")){
%>
<!-- 웹에디터 화면  시작-->
<body onload="javascript:HTMLArea.init(); HTMLArea.onload = initEditor;">
  	<table>
	  	<tr>
	      <td class="title_left">웹에디터(HTMLAREA) 테스트</td>
	    </tr>
  	</table>

  	<!-- 무료웹에디터로 대체될 textarea -->
  	<form name="edit">
	  <textarea id="htmlarea" name="htmlarea" cols="75" rows="14"  style="width:450px; height:400px"></textarea><p>
	</form>
	<!-- 웹에디터로 작성한 데이터를 저장하기 위한 form -->
	<form name="fm" method=POST action="javascript:goSaveData();">
	  <input type = "hidden" name="execFlag" value="DATA_SAVE">
	  <input type = "hidden" name="htmldata" value="">
	  <input type = "hidden" name="link" value="cmm/utl/EgovWebEditor">
	  <input type = "button" method="post"  value="저장합니다." onclick="fm.submit()">
	</form>

</body>
<!-- 웹에디터 화면  끝-->
<%
} else if(execFlag.equals("DATA_SAVE")){

	String data = request.getParameter("htmldata");

%>
<!-- 웹에디터를 통한 데이터 저장 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do">
<input type = "hidden" name="execFlag" value="SAVE">
<input type = "hidden" name="link" value="cmm/utl/EgovWebEditor">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="100" class="title_left">웹에디터 내용</td>
        <td><%=data %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 웹에디터를 통한 데이터 저장 결과화면 끝 -->
<%
}
%>