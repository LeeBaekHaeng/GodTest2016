<%--
  Class Name : EgovIncTopnav.jsp
  Description : 상단메뉴화면(include)
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성

    author   : 실행환경개발팀 JJY
    since    : 2011.08.31
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 상단메뉴 EgovIncTopnav.jsp START -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/LeftMenu.js"/>"/></script>

<form name="topnaviForm" method="post" action = "<c:url value='/bopr/LeftMenu.do'/>">
<ul>
	<c:if test="${menuInfo.batchDlbrt == 'Y'}"> <li><a href="javascript:goLeftMenu('/bopr/bam/EgovJobDlbrtList.do','batchDlbrt','batchDlbrt_1')">배치심의</a></li></c:if>
	<li><a href="javascript:goLeftMenu('/bopr/sim/selectBatchInfoList.do','batchInfo','batchInfo_1')">배치정보</a></li>
	<li><a href="javascript:goLeftMenu('/bopr/mom/EgovExecutJobList.do','batchOpr','batchOpr_1')">배치운영</a></li>
	<c:if test="${menuInfo.jobKnw == 'Y'}"><li><a href="javascript:goLeftMenu('/bopr/ikm/EgovJobIssueList.do','jobKnw','jobKnw_1')">Job지식</a></li></c:if>
	<li><a href="javascript:goLeftMenu('/bopr/uam/EgovUserList.do','com','com_1')">관리자메뉴</a></li>
</ul>
<input type="hidden" name="url" id="url"/>
<input type="hidden" name="leftMenu" id="leftMenu"/>
<input type="hidden" name="openMenu" id="openMenu"/>
</form>
<!-- 상단메뉴 EgovIncTopnav.jsp END -->