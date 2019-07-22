<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>eGovFrame 공통 컴포넌트</title>
</head>

<body>

<ol>
	<li>pageContext.request.requestURI: ${pageContext.request.requestURI}</li>
	<li>pageContext.request.requestURL: ${pageContext.request.requestURL}</li>
	<li>authenticatedUser: ${authenticatedUser}</li>
	<li>authenticatedUser.id: ${authenticatedUser.id}</li>
	<li>authenticatedUser.name: ${authenticatedUser.name}</li>
</ol>

</body>
</html>
