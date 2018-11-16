<%--
  Class Name : EgovBatchOpertListPopupFrame.jsp
  Description : 배치작업정보 목록 조회를 위한 Frame화면
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2010.08.23    김진만          최초 생성
 
    author   : 공통서비스 개발팀 김진만
    since    : 2010.08.23
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="imgUrl" value="/images/egovframework/com/sym/bat/"/>
<c:set var="cssUrl" value="/css/egovframework/com/sym/bat/"/>
<html lang="ko">
<head>
<title></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${cssUrl}com.css" />
<script type="text/javaScript" language="javascript">

</script>
</head>
<body>
<DIV id="content" style="width:775px">
<!-- iframe -->
<iframe title="배치작업목록" id="batchOpertPopupFrame" src="/com/sym/bat/getBatchOpertList.do?popupAt=Y" width="100%" height="575px" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" >
</iframe>
</DIV>
</body>
</html>
