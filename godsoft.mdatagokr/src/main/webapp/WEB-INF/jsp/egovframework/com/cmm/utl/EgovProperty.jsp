<%--
  Class Name : EgovProperty.jsp
  Description : 프로퍼티 파일 내용을 파싱하여 key-value 구조체의 문자열 배열을 전달하는 JSP
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.02.02    박지욱          최초 생성

    author   : 박지욱
    since    : 2009.02.02

--%>
<link rel="stylesheet" href="/css/egovframework/com/cmm/utl/com.css" type="text/css">
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false" %>
<%@ page import="java.util.*"  %>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String execFlag = request.getParameter("execFlag");
if(execFlag==null || execFlag.equals("")) {
    execFlag="PROPERTY";
}
%>
<%
if(execFlag.equals("PROPERTY")){
%>
<!-- 프로퍼티 화면  시작-->
<form name="propForm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovProperty">
<input type = "hidden" name="execFlag" value="PROPERTY_ACTION">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
      <th width="150" height="23" class="required_text" nowrap>Property 조회</th>
      <td width="280" height="23" class="title_left" nowrap><input type = "button" method="post"  value="실행!" onclick="propForm1.submit()"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>Property 파일명</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="file" value="" size="40"></td>
    </tr>
    <tr>
      <th width="150" height="23" class="title_left" nowrap>Key</td>
      <td width="280" height="23" class="title_left" nowrap><input type = "text" name="key" value="" size="40"></td>
    </tr>
</table>
</form>
<!-- 프로퍼티 화면 끝 -->
<%
} else if(execFlag.equals("PROPERTY_ACTION")){

	String file = request.getParameter("file");
	String key = request.getParameter("key");

	ArrayList list = null;
	if (file != null && file.length() > 0
		&& key != null && key.length() > 0) {
		list = EgovProperties.loadPropertyFile(file);
	}

	String resultStr = "";
	if (list != null) {
		if (key != null && !"".equals(key)) {
			for (int i = 0; i < list.size(); i++) {
				Map prop = (Map)list.get(i);
				String str = (String)prop.get(key);
				if (str != null && !"".equals(str)) {
					resultStr = str;
				}
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				Map prop = (Map)list.get(i);
				String str = prop.toString();
				resultStr += str + "<p>";
			}
		}
	}

%>
<!-- 프로퍼티 결과화면 시작 -->
<form name="fm1" action ="EgovPageLink.do" method="post">
<input type = "hidden" name="link" value="cmm/utl/EgovProperty">
<input type = "hidden" name="execFlag" value="PROPERTY">
<table width="430" border="0" cellpadding="0" cellspacing="1" class="table-register">
    <tr>
        <td width="150" class="title_left">Value</td>
        <td><%=resultStr %></td>
    </tr>
</table>
<br>
<input type = "button" method="post"  value="화면으로 돌아가기" onclick="fm1.submit()">
</form>
<!-- 프로퍼티 결과화면 끝 -->
<%
}
%>