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
<title>배치정보목록팝업</title>

        <script type="text/javaScript" language="javascript" defer="defer">
        <!--
            function fncSelect(batchId, batchNm)
        	{
	        	opener.document.getElementById("batchNm").value = batchNm;
	            opener.document.getElementById("batchId").value = batchId;
	            
	            opener.fncBatchPopupCallback();
	            
	            self.close();
        	}
        
        /* parameter form */
    	var objParam;
    
        /*
        	화면 OnLoad
        */
        function fncOnLoad()
        {
        	objParam = document.getElementById("parameter");
        	
        	/*
        		검색 조건을 걸어 목록 조회 한 경우, 검색 조건 화면에 표시
        		- 검색조건 없는 경우 기본값으로 설정(selSearchCondition:검색조건선택, txtSearchKeyword:공백)
        	*/
        	document.getElementById("selSearchCondition").value = '${(parameter.searchCondition=="") ? "" : parameter.searchCondition}';
      		document.getElementById("txtSearchKeyword"  ).value = "${parameter.searchKeyword}";
      		
      		/*
      			검색 조건을 걸어 목록 조회 한 경우, 검색 조건 form.hidden에 설정
      		*/
      		objParam.searchCondition.value = '${parameter.searchCondition}';	// searchCondition
      		objParam.searchKeyword.value   = '${parameter.searchKeyword}';		// searchKeyword
      		objParam.searchUseYn.value     = '${parameter.searchUseYn}';		// searchUseYn - 검색 조건 걸어 조회 한 경우 Y
      		objParam.pageIndex.value       = "${parameter.pageIndex}";
      		
			var alertMssage = "<c:out value='${alertMssage}'/>";
    		
    		if (!fncIsEmpty(alertMssage))
    		{
    			alert(alertMssage);
    		}
        }
        
        /*
        	'조회' Button onClick - 신규 목록 조회
        */
        function fncSelectNewList()
        {
        	var searchConditionValue = document.getElementById("selSearchCondition").value;		// List Search Condition
        	var searchKeywordValue   = document.getElementById("txtSearchKeyword").value;		// List Search Keyword
        	
        	objParam.searchCondition.value = searchConditionValue;
        	objParam.searchKeyword.value   = searchKeywordValue;
        	
        	objParam.pageIndex.value= "1";			// Page Index 초기화
        	
        	/* 배치정보 목록 조회 url 호출 */
        	fncSelectList();
        }
        
        function fncSelectByEnter()
        {
        	if (event.keyCode==13) {
            	fncSelectNewList();
            }
        }
        
        /*
        	Page Link 기능
        */
        function linkPage(pageNo)
        {
        	objParam.pageIndex.value = pageNo;		// 페이지Index 클릭한 번호로 설정
        	fncSelectList();						// 배치정보 목록 조회 호출
        }
        
        /*
        	배치정보 목록 조회 url 호출 - fncSelectNewList(), linkPage() 에서 공통으로 사용
        */
        function fncSelectList()
        {
            objParam.action = "<c:url value='/bopr/sim/selectBatchInfoList.do'/>";
            objParam.submit();
        }
        
        /*
			앞 뒤 공백 제거 기능 - trim
		*/
		function fncTrim(str)
		{
			return str.replace(/(^\s*)|(\s*$)/gi, "");
		}
		
		/*
			String null Check 기능
		*/
		function fncIsEmpty(str)
		{
			var strTrim = fncTrim(str);
	
			if (strTrim == null || strTrim == "" || strTrim.length <= 0)
			{
				return true;
			}
	
			return false;
		}	
        -->
        </script>
    </head>
    
    <body onload="fncOnLoad()">
    
	   <form id="parameter" name="parameter" method="post" action="">
	   
	   		<!-- Parameter Start -->
			
			<!-- 목록 조회 조건 설정 위한 parameter -->
			<input type="hidden" name="searchCondition" id="searchCondition" />
			<input type="hidden" name="searchKeyword" id="searchKeyword" />
			
			<!-- 배치배포 상세정보 화면 호출 후 목록 복귀 시 검색조건 유지하기 위한 parameter -->
			<input type="hidden" name="searchUseYn" id="searchUseYn" />
			
			<!-- 페이징 처리 위한 parameter -->
			<input type="hidden" name="pageIndex" value='1' />
			
			<input type="hidden" name="popupAt" value="Y" />
			<!-- Parameter End -->
	   
	   	<div class="popupBody">
			<h2>배치정보 목록 팝업</h2>
			<div class="location">배치정보 &gt; <strong>팝업</strong></div>
			
			<!-- 검색영역 -->
			<div class="search">
			<fieldset class="searchboxA">
				<legend>검색 영역</legend>
			    <label for="selSearchCondition" class="disp_none">검색어 입력 선택</label>
				<select id="selSearchCondition" name="selSearchCondition" class="serSel" style="" title="검색어 입력 선택">
					<option value="" >검색조건선택</option>
					<option value="A">배치ID</option>
					<option value="B">Job이름(배치명)</option>       
				</select>
				<label for="txtSearchKeyword" class="disp_none">검색어 입력</label>
				<input id="txtSearchKeyword" name="txtSearchKeyword" class="inptext" title="검색어 입력" type="text" value="" maxlength="50" onkeydown="fncSelectByEnter()">                       
				<input type="image" class="searchbtn" title="검색" src="/images/egovframework/bopr/btn_search.gif" alt="검색" onclick="fncSelectNewList()">
			</fieldset>
			</div>
			<!-- //검색영역 -->
			
			<!-- List Start -->
			<div class="bbsList">
				<table  summary="배치I.D., 업무구분, Job이름(배치명), 배치설명, 온라인실행여부의 순서로 나타낸 배치정보 목록 표입니다.">
					<caption>배치정보 목록</caption>
				<colgroup>
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:auto">
					<col style="width:auto">
					<col style="width:15%">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">배치ID</th>
						<th scope="col">업무구분</th>
						<th scope="col">Job이름(배치명)</th>
						<th scope="col">배치설명</th>
						<th scope="col">온라인실행여부</th>
					</tr>
				</thead>
				<tbody>
				  <c:forEach var="list" items="${batchInfoList}">
					<tr>
						<td align="center"><c:out value="${list.batchId}" /></td>
						<td align="center"><c:out value="${list.jobSeCodeNm}" /></td>
						<td align="center">
							<a name="batchId" href="javascript:fncSelect('<c:out value="${list.batchId}"/>', '<c:out value="${list.batchNm}')"/>" title="배치정보 입력 기능">
								<c:out value="${list.batchNm}"/>
							</a>
						</td>
						<td align="left" style="text-align:left;"><c:out value="${list.batchDc}" /></td>
						<td align="center"><c:out value="${list.onlineExecutAt}"  /></td>
					</tr>
				  </c:forEach>
				</tbody>
				</table>
			</div>
			<!-- List End -->
			
			<!-- paging Start -->
			<div class="paging">
			  <c:if test="${!empty parameter.pageIndex }">
				<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>
			  </c:if>
			</div>
			<!-- paging End -->
			
		</div>
	   
	   </form>
    
    </body>
</html>