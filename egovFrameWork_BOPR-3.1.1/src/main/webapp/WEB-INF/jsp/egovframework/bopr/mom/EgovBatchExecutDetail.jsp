<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovBatchExecutDetail.jsp
  * @Description : 배치실행 상세 조회 화면
  * @Modification Information
  * @
  * @ 수정일      수정자    수정내용
  * @ ----------  --------  ---------------------------
  * @ 2012.07.18  이병권    최초 생성
  *
  *  @author SDS 이병권
  *  @since 2012.07.18
  *  @version 0.9 
  *  @see
  */
%>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
        <validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>
        <script type="text/javaScript" language="javascript">
        <!--
			/* form */
	    	var objParam;
	    
	    	/*
	    		화면 onLoad
	    	*/
	        function fncOnLoad()
	        {
	        	objParam = document.getElementById("parameter");
	        }
        
            function fncList()
            {
        	    if ("Y" == "${batchExecut.searchUseYn}")
        	    {
        	    	objParam.searchCondition.value = "${batchExecut.searchCondition}";
        	    	objParam.searchKeyword.value = "${batchExecut.searchKeyword}";
        	    	objParam.searchKeywordFrom.value = "${batchExecut.searchKeywordFrom}";
        	    	objParam.searchKeywordTo.value = "${batchExecut.searchKeywordTo}";
        	    	objParam.pageIndex.value = "${batchExecut.pageIndex}";
        	    }
        	    
        	    objParam.action = "<c:url value='/bopr/mom/selectBatchExecutList.do'/>";
        	    objParam.submit();
            }
            
            function fncDelete()
            {
            	objParam.action = "<c:url value='/bopr/mom/deleteBatchExecut.do'/>";
            	objParam.submit();
            }
        -->
        </script>
    </head>

    <body onLoad="fncOnLoad()">
    
    	<form id="parameter" name="parameter" method="post">
		<div class="contsBody">
		
			<!-- Parameter Start -->
			<!-- 목록 조회 조건 설정 위한 parameter -->
			<input type="hidden" name="searchCondition" id="searchCondition" />
			<input type="hidden" name="searchKeyword" id="searchKeyword" />
			<!-- 페이징 처리 위한 parameter -->
			<input type="hidden" name="pageIndex" value='1' />
			<!-- Parameter End -->
			
			<!-- Title Start -->
			<h2>일정결과 관리</h2>
			<div class="location">일정결과 관리 > <strong>상세정보</strong></div>
			<!-- Title End -->
			
			<!-- Button Start -->
			<div class="Btn">
				<span class="bbsBtn"><a href="javascript:fncList()">목록</a></span>
			</div>
			<!-- Button End -->
			
			<!-- Contents Start -->
			<div class="bbsDetail">
				<table  summary="일정결과 상세정보">
					<caption>일정결과 상세정보</caption>
					<colgroup>
						<col style="width:20%" />
						<col style="width:auto" />
					</colgroup>
					<tbody>
						<tr>
							<th>배치ID</th>
							<td>
								<c:out value="${schdulResult.batchId}" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
		</div>
		</form>
		
		
        <DIV id="main">
            <table border="0">
                <tr>
                    <td width="700">
                        <form:form commandName="batchExecut" method="post" >
                        	<input type="hidden" name="searchCondition" />
                            <input type="hidden" name="searchKeyword" />
                            <input type="hidden" name="searchKeywordFrom" />
                            <input type="hidden" name="searchKeywordTo" />
                            <input type="hidden" name="pageIndex" value="1" />
                        
                            <table width="100%" cellpadding="8" class="table-search" border="0">
                                <tr>
                                    <td width="40%" class="title_left">
                                        <img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="absmiddle">
                                        &nbsp;배치실행 상세조회
                                    </td>
                                    <th width="10%" align="right">
                                        <table border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td>
                                                    <img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif' />" width="8" height="20">
                                                </td>
                                                <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap>
                                                    <a href="javascript:fncList()" style="selector-dummy:expression(this.hideFocus=false);">
                                                        목록
                                                    </a> 
                                                </td>
                                                <td>
                                                    <img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20">
                                                </td>
                                                <td>&nbsp;&nbsp;</td>
                                                <td>
                                                    <img src="<c:url value='/images/egovframework/com/cmm/button/bu2_left.gif'/>" width="8" height="20">
                                                </td>
                                                <td background="<c:url value='/images/egovframework/com/cmm/button/bu2_bg.gif'/>" class="text_left" nowrap>
                                                    <a href="javascript:fncDelete()" style="selector-dummy:expression(this.hideFocus=false);">
                                                        삭제
                                                    </a> 
                                                </td>
                                                <td>
                                                    <img src="<c:url value='/images/egovframework/com/cmm/button/bu2_right.gif'/>" width="8" height="20">
                                                </td>
                                            </tr>
                                        </table>
                                    </th>
                                </tr>
                            </table>
                            <table width="100%" cellpadding="8" class="table-line">
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        배치실행번호
                                        <!-- <img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15"> -->
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.batchExecutNo}'/>
                                        <input type="hidden" name="batchExecutNo" value="<c:out value='${batchExecut.batchExecutNo}'/>" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        배치ID
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.batchId}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        배치명
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.batchNm}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        실행시간
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.executTime}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        잡상태
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.status}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        잡인스턴스ID
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.jobInstanceId}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        잡실행ID
                                    </th>
                                    <td class="lt_text" nowrap>
                                        <c:out value='${batchExecut.jobExecutionId}'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        잡시작시간
                                    </th>
                                    <td class="lt_text" nowrap>
                                        ${batchExecut.jobStartTime}
                                    </td>
                                </tr>
                                <tr>
                                    <th class="required_text" width="20%" nowrap>
                                        잡종료시간
                                    </th>
                                    <td class="lt_text" nowrap>
                                        ${batchExecut.jobEndTime}
                                    </td>
                                </tr>
                            </table>
                        </form:form>
                        <div align="right">
                            <!-- <input type="text" name="message" value="" size="30" readonly /> -->
                        </div>
                    </td>
                </tr>
            </table>
        </DIV>
    </body>
</html>