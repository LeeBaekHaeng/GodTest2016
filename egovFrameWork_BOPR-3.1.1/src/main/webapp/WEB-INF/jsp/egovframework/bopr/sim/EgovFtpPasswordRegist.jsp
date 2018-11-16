<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovBatchInfoListPopup.jsp
  * @Description : 배치정보 목록 팝업화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.08.28  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.08.28
  *  @version 0.9
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/css/egovframework/bopr/egovBopr.css' />" rel="stylesheet" type="text/css">
<title>FTP 비밀번호 입력 팝업</title>

<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
        <script type="text/javaScript" language="javascript" defer="defer">
        <!--
            function fncSelect()
        	{
        	    var strSend = document.getElementById("ftpPw").value;
        	
	        	if (fncIsEmpty(strSend))
	        	{
	        		alert("비밀번호를 입력해주세요");
	        		return;
	        	}
	            
	        	if (opener.document.getElementById("ftpPassword") != null)
	        	{
	        		opener.document.getElementById("ftpPassword").value = strSend;
	        	}
	            
	        	opener.fncFtpPasswordCallback();
	        	
	            self.close();
        	}
        
        	function fncKeyDown()
        	{
        		if (event.keyCode==13)
                {
        			fncSelect();
                }
        	}
        -->
        </script>
    </head>
    
    <body>
    
	   <form id="parameter" name="parameter" method="post" action="">
    
        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <input type="hidden" name="pageIndex" value='1'>
        <input type="hidden" name="jobSeCode" id="jobSeCode">
        <!-- Parameter End -->
        
        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncSelect()">등록</a></span>
        </div>
        <!-- Button End -->
        
        <!-- Contents Start -->
        <div class="bbsDetail">
            <table id="detailTable" summary="배치실행 등록">
                <caption>배치실행 등록</caption>
                <colgroup>
                    <col style="width:auto">
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <td>FTP 비밀번호</td>
                        <td>
                            <label for="ftpPw" class="disp_none">FTP비밀번호</label>
                            <input type="password" id="ftpPw" name="ftpPw" size="40" title="FTP비밀번호" onkeydown="fncKeyDown()">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
	   </form>
    
    </body>
</html>