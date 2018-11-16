<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>등록된 Job 리스트</title>

</head>
<body>

<form action="./batchRun.do" method="POST">
<ul id="menu">
    <li> 등록된 Job 리스트
        <ul>
        	<c:forEach items="${jobList}" var="item" varStatus="status">      
            	<li>
	            	<input type="radio" name="jobName" value="${item}" <c:if test="${status.first == true}">checked</c:if>/>
	            	Job(이름: '${item}')
            	</li>
            </c:forEach>
        </ul>
    </li>
</ul>
<input type="submit" value="배치 실행" />
</form>
</body>

</html>