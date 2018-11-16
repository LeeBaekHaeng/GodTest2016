<%--
  Class Name : EgovIncLeftmenu.jsp
  Description : 좌메뉴화면(include)
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성
 
    author   : 실행환경개발팀 JJY
    since    : 2011.08.31 
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%String openMenu = (String)session.getAttribute("openMenu");%>
<c:set var="openMenu" value="<%=openMenu%>"/>

<script type="text/javascript" src="<c:url value="/js/egovframework/com/LeftMenu.js"/>"/></script>
<script type="text/javascript">

function foldMenu(openYn,menuCode){
	if(openYn == "open"){
		document.getElementById(menuCode+"_1").style.display = "none";
		document.getElementById(menuCode+"_2").style.display = "block";	
	}else if(openYn == "close"){
		document.getElementById(menuCode+"_1").style.display = "block";
		document.getElementById(menuCode+"_2").style.display = "none";	
	}
	
}

function fncSelectUser(user) {
    goLeftMenu('/bopr/uam/EgovUser.do?userId='+user,'com','com_1'); 
}

</script>
<!-- 메뉴 시작 -->
<form name="menuListForm" id="menuListForm" action=""> 
<div class="logBox">
	<%
	LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
	String leftMenu = (String)session.getAttribute("leftMenu");
	String adminYn = (String)session.getAttribute("adminYn");
	
	if(loginVO == null){ 
	%>
	<ul>
	<li>
		<span class="nameLeft">로그인정보 없음</span>
		<span class="logoutRight"><a href="<c:url value='/uat/uia/egovLoginUsr.do'/>"><img src="/images/egovframework/bopr/new_main/btn_login.gif" alt="로그인" /></a></span>
	</li>
	</ul>
	<%
	}else{
  	%>
  	<c:set var="loginName" value="<%= loginVO.getName()%>"/>
  	
	<ul>
	<li>
		<span class="nameLeft"><a href="#content" onclick="javascript:fncSelectUser('<%=loginVO.getId()%>')"><c:out value="${loginName}"/> 님</a></span>
		<span class="logoutRight"><a href="<c:url value='/uat/uia/actionLogout.do'/>"><img src="/images/egovframework/bopr/new_main/btn_logout.gif" alt="로그아웃" /></a></span>
	</li>
	</ul>
	<%
	}
	%>
</div>
<div class="leftMenu">
	<h2>
		<%if(leftMenu != null && !leftMenu.equals("")){
		if(leftMenu.equals("batchDlbrt")){%>
		배치심의
		<%}else if(leftMenu.equals("batchInfo")){%>
		배치정보
		<%}else if(leftMenu.equals("batchOpr")){%>
		배치운영
		<%}else if(leftMenu.equals("jobKnw")){%>
		Job지식
		<%}else if(leftMenu.equals("com")){%>
			<%if(adminYn.equals("Y")){%>
		관리자메뉴
			<%}else{%>
		사용자정보
			<%}%>	
		
		<%}else if(leftMenu.equals("regist")){%>
		회원 가입
		<%}%>
	<%}%>
	</h2>

	<ul>
	<%if(leftMenu != null && !leftMenu.equals("")){
		if(leftMenu.equals("batchDlbrt")){%>
		<li id="batchDlbrt_1_1" <c:if test="${openMenu == 'batchDlbrt_1'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','batchDlbrt_1')">배치심의</a></li>
		<li class="deth" id="batchDlbrt_1_2" <c:if test="${openMenu == 'batchDlbrt_1'}">style="display:block;"</c:if><c:if test="${openMenu != 'batchDlbrt_1'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','batchDlbrt_1')">배치심의</a>
			<ol class="deth2" >
    			<li><a href="javascript:goLeftMenu('/bopr/bam/EgovJobDlbrtList.do','batchDlbrt','batchDlbrt_1')">업무심의요청</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/bam/EgovBatchDlbrtList.do','batchDlbrt','batchDlbrt_1')">배치심의요청</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/bam/EgovJobDlbrtConfmList.do','batchDlbrt','batchDlbrt_1')">업무심의관리</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/bam/EgovBatchDlbrtConfmList.do','batchDlbrt','batchDlbrt_1')">배치심의관리</a></li>
		    	<li class="last"><a href="javascript:goLeftMenu('/bopr/sim/selectBatchWdtbList.do','batchDlbrt','batchDlbrt_1')">배치배포관리</a></li>		
    		</ol>
    	</li>
		
		
		<%}else if(leftMenu.equals("batchInfo")){%>
		<li id="batchInfo_1_1" <c:if test="${openMenu == 'batchInfo_1'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','batchInfo_1')">배치정보</a></li>
		<li class="deth" id="batchInfo_1_2" <c:if test="${openMenu == 'batchInfo_1'}">style="display:block;"</c:if><c:if test="${openMenu != 'batchInfo_1'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','batchInfo_1')">배치정보</a>
			<ol class="deth2" >
    			<li><a href="javascript:goLeftMenu('/bopr/sim/selectBatchInfoList.do','batchInfo','batchInfo_1')">배치정보관리</a></li>
		   		<li><a href="javascript:goLeftMenu('/bopr/sim/selectSttusNtcnList.do','batchInfo','batchInfo_1')">Job상태알림관리</a></li>
		   		<li><a href="javascript:goLeftMenu('/bopr/sim/selectSchdulList.do','batchInfo','batchInfo_1')">스케줄관리</a></li>
		   		<li class="last"><a href="javascript:goLeftMenu('/bopr/sim/selectSchdulResultList.do','batchInfo','batchInfo_1')">스케줄결과관리</a></li>		
    		</ol>
    	</li>
   		
		
		<%}else if(leftMenu.equals("batchOpr")){%>
		<li id="batchOpr_1_1" <c:if test="${openMenu == 'batchOpr_1'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','batchOpr_1')">배치운영</a></li>
		<li class="deth" id="batchOpr_1_2" <c:if test="${openMenu == 'batchOpr_1'}">style="display:block;"</c:if><c:if test="${openMenu != 'batchOpr_1'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','batchOpr_1')">배치운영</a>
			<ol class="deth2" >
    			<li><a href="javascript:goLeftMenu('/bopr/mom/EgovExecutJobList.do','batchOpr','batchOpr_1')">실행중 Job 관리</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/mom/EgovExecutResultList.do','batchOpr','batchOpr_1')">Job 실행결과 관리</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/jhm/EgovJobHistList.do','batchOpr','batchOpr_1')">작업이력관리</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/mom/selectBatchExecutList.do','batchOpr','batchOpr_1')">배치실행관리</a></li>
		    	<li><a href="javascript:goLeftMenu('/bopr/mom/EgovRehndnJobList.do','batchOpr','batchOpr_1')">재처리 관리</a></li>
		    	<li class="last"><a href="javascript:goLeftMenu('/bopr/mom/selectRehndnList.do','batchOpr','batchOpr_1')">재처리결과 관리</a></li>		
    		</ol>
    	</li>
		

		<%}else if(leftMenu.equals("jobKnw")){%>
		<li id="jobKnw_1_1" <c:if test="${openMenu == 'jobKnw_1'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','jobKnw_1')">Job지식</a></li>
		<li class="deth" id="jobKnw_1_2" <c:if test="${openMenu == 'jobKnw_1'}">style="display:block;"</c:if><c:if test="${openMenu != 'jobKnw_1'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','jobKnw_1')">Job지식</a>
			<ol class="deth2" >
    			<li><a href="javascript:goLeftMenu('/bopr/ikm/EgovJobIssueList.do','jobKnw','jobKnw_1')">Job 이슈 관리</a></li>
    			<li class="last"><a href="javascript:goLeftMenu('/bopr/ikm/EgovJobKnwldgList.do','jobKnw','jobKnw_1')">Job 지식 관리</a></li>		
    		</ol>
    	</li>

		<%}else if(leftMenu.equals("com")){%>
			<%if(adminYn.equals("Y")){%>
		<li id="com_1_1" <c:if test="${openMenu == 'com_1'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','com_1')">사용자 관리</a></li>
		<li class="deth" id="com_1_2" <c:if test="${openMenu == 'com_1'}">style="display:block;"</c:if><c:if test="${openMenu != 'com_1'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','com_1')">사용자 관리</a>
			<ol class="deth2" >
    			<li><a href="javascript:goLeftMenu('/bopr/uam/EgovUserList.do','com','com_1')">사용자 관리 </a></li>
    			<li class="last"><a href="javascript:goLeftMenu('/uss/umt/stplatCnfirmView.do','com','com_1')">회원가입 약관관리</a></li>
    		</ol>
    	</li>
    	
		<li id="com_2_1" <c:if test="${openMenu == 'com_2'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','com_2')">권한 관리</a></li>
		<li class="deth" id="com_2_2" <c:if test="${openMenu == 'com_2'}">style="display:block;"</c:if><c:if test="${openMenu != 'com_2'}">style="display:none;"</c:if>><a href="javascript:foldMenu('close','com_2')">권한 관리</a>
			<ol class="deth2">
			<li><a href="javascript:goLeftMenu('/sec/ram/EgovAuthorList.do','com','com_2')">권한 관리</a></li>
			<li><a href="javascript:goLeftMenu('/sec/rmt/EgovRoleList.do','com','com_2')">롤 관리</a></li>
			<!-- <li><a href="javascript:goLeftMenu('/sec/ram/EgovAuthorRoleListView.do','com','com_2')">권한별 롤관리</a></li> -->
			<li><a href="javascript:goLeftMenu('/sec/rmt/EgovRoleHierarchyList.do','com','com_2')">롤 상하관계 관리</a></li>
			<li class="last"><a href="javascript:goLeftMenu('/sec/rgm/EgovAuthorGroupList.do','com','com_2')">권한 부여 관리</a></li>
			</ol>
    	</li>
    	
    	<li id="com_3_1" <c:if test="${openMenu == 'com_3'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','com_3')">연동서비스 관리</a></li>
		<li class="deth" id="com_3_2" <c:if test="${openMenu == 'com_3'}">style="display:block;"</c:if><c:if test="${openMenu != 'com_3'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','com_3')">연동서비스 관리</a>
			<ol class="deth2" >
				<!-- <li><a href="javascript:goLeftMenu('/bopr/jim/EgovMailIntrlList.do','com','com_3')">Mail 연동 서비스</a></li> -->
    			<li class="last"><a href="javascript:goLeftMenu('/bopr/jim/EgovFtpIntrlList.do','com','com_3')">FTP 연동 서비스</a></li>
    		</ol>
    	</li>
    	
    	<li id="com_4_1" <c:if test="${openMenu == 'com_4'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','com_4')">공통코드 관리</a></li>
    	<li class="deth" id="com_4_2" <c:if test="${openMenu == 'com_4'}">style="display:block;"</c:if><c:if test="${openMenu != 'com_4'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','com_4')">공통코드 관리</a>
    		<ol class="deth2">
				<li><a href="javascript:goLeftMenu('/sym/ccm/cca/EgovCcmCmmnCodeList.do','com','com_4')">공통코드 관리</a></li>
    			<li class="last"><a href="javascript:goLeftMenu('/sym/ccm/cde/EgovCcmCmmnDetailCodeList.do','com','com_4')">공통상세코드 관리</a></li>
    		</ol>
		</li>
		
		<li id="com_5_1" <c:if test="${openMenu == 'com_5'}">style="display:none;"</c:if>><a href="javascript:foldMenu('open','com_5')">메뉴 관리</a></li>
    	<li class="deth" id="com_5_2" <c:if test="${openMenu == 'com_5'}">style="display:block;"</c:if><c:if test="${openMenu != 'com_5'}">style="display:none;"</c:if> ><a href="javascript:foldMenu('close','com_5')">메뉴 관리</a>
    		<ol class="deth2">
    			<li class="last"><a href="javascript:goLeftMenu('/main/EgovSelectMenuSetupInfo.do','com','com_5')">메뉴 관리</a></li>
    		</ol>
		</li>
<!--  	<br>
		<br><a href="javascript:goLeftMenu('/bopr/uam/EgovLoginPolicyList.do','com')">로그인정책 관리</a>
		<br>
    	<br>메뉴 관리		
		<br>&nbsp;&nbsp;<a href="javascript:goLeftMenu('/sym/mnu/mpm/EgovMenuManageSelect.do','com')">메뉴목록 관리</a>
		<br>&nbsp;&nbsp;<a href="javascript:goLeftMenu('/sym/mnu/mcm/EgovMenuCreatManageSelect.do','com')">메뉴생성 관리</a>
		<br>
		<br>프로그램 관리
		<br>&nbsp;<a href="javascript:goLeftMenu('/sym/prm/EgovProgramListManageSelect.do','com')">프로그램목록 관리</a>
		<br>&nbsp;<a href="javascript:goLeftMenu('/sym/prm/EgovProgramChangeRequstSelect.do,'com')">프로그램변경 관리</a>
		<br>
		<br><a href="javascript:goLeftMenu('/com/guide/selectNotificationList.do','com')">정보알림이</a> -->
			<%}else{%>

			<%}%>
		<%}else if(leftMenu.equals("regist")){%>
		
		<%}%>
	<%}%>
<!-- 		<a href="#">사용자 관리</a></li>
		<li><a href="#">롤 관리</a></li>
		<li><a href="#">권한 관리</a></li>
		<li><a href="#">권한별 롤관리</a></li>
		<li><a href="#">권한 그룹 관리</a></li>
		<li>연동서비스 관리
			<ul class="deth2">
				<li><a href="#">Mail 연동 서비스</a></li>
				<li class="last"><a href="#">FTP 연동 서비스</a></li>
			</ul>
		</li> -->
	</ul>	
</div>
</form>
