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
   * @Class Name : EgovJobKnwldgDetail.jsp
   * @Description : Job지식 상세정보 화면
   * @Modification Information
   * @
   * @  수정일      수정자            수정내용
   * @ -------        --------    ---------------------------
   * @ 2012.07.16   김지완          최초 생성
   *
   *  @author 배치운영환경 김지완
   *  @since 2012.07.16
   *  @version 1.0 
   *  @see
   */
%>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="jobKnwldgManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectJobKnwldgList() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgList.do'/>";
	varFrom.submit();       
}

function fncJobKnwldgGoUpdate() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgUpdateView.do'/>";
	varFrom.submit();
}

function fncJobKnwldgDelete() {
	var varFrom = document.getElementById("jobKnwldgManage");
	varFrom.action = "<c:url value='/bopr/ikm/EgovJobKnwldgDelete.do'/>";
	if(confirm("비공개로 전환하시겠습니까?")){
	    varFrom.submit();
	}
}

</script>

<!-- 댓글 head Start-->
<c:import url="/com/cop/cmt/selectCommentList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
	<c:param name="cmtNttId" value="${jobKnwldgManage.knwldgNo}" />
</c:import>
<!-- 댓글 head End-->

<form name="jobKnwldgManage" id="jobKnwldgManage" method="post" action="">
<div class="contsBody">
	<h2>Job 지식 관리</h2>
	<div class="location">Job지식 > Job 지식 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectJobKnwldgList()" title="지식 목록 화면으로 이동">목록</a></span>
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncJobKnwldgGoUpdate()" title="지식 수정 화면으로 이동">수정</a></span>
		<c:if test="${jobKnwldgManage.othbcAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncJobKnwldgDelete()" title="해당 지식을 비공개로 전환">비공개로 전환</a></span>
		</c:if>
		</c:if>
		<c:if test="${adminYn == 'N' && loginUser.id == jobKnwldgManage.frstRegisterId}">
		<span class="bbsBtn"><a href="javascript:fncJobKnwldgGoUpdate()" title="지식 수정 화면으로 이동">수정</a></span>
		<c:if test="${jobKnwldgManage.othbcAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncJobKnwldgDelete()" title="해당 지식을 비공개로 전환">비공개로 전환</a></span>
		</c:if>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table summary="공개여부, 조회수, 평가점수, 지식유형, 폐기유무, 폐기일, 지식명, 지식내용, 첨부파일, 댓글의 순서로 보여지는 Job지식 상세정보입니다.">
		<caption>지식관리 상세정보</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:10%" >
			<col style="width:auto" >
			<col style="width:10%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th>공개여부</th>
			<td>
				<c:if test="${jobKnwldgManage.othbcAt == 'Y'}">공개</c:if>
				<c:if test="${jobKnwldgManage.othbcAt == 'N'}">비공개</c:if>
			</td>
		   
			<th>조회수</th>
			<td colspan="3">${jobKnwldgManage.rdcnt}</td>
		</tr>
	
		<tr>  
		    <th>평가점수</th>
		    <td colspan="5">
		    	${jobKnwldgManage.knwldgEvl}
		    </td>
		</tr>
		    
	
		<tr>  
			<th>지식유형</th>
			<td>
				<c:forEach var="knwldgTy" items="${knwldgTyCodeList}" varStatus="status">
					<c:if test="${knwldgTy.code == jobKnwldgManage.knwldgTyCode}">
					${knwldgTy.codeNm}
					</c:if>
				</c:forEach>
			</td>
	    
			<th>폐기유무</th>
		    <td>
		    	<c:if test="${jobKnwldgManage.dsuseEnnc == 'N'}">사용중</c:if>
		    	<c:if test="${jobKnwldgManage.dsuseEnnc == 'Y'}">폐기완료</c:if>
		    </td>
		    
			<th>폐기일</th>
		    <td>${jobKnwldgManage.dsuseDe}</td>
		</tr>
		
		<tr>  
		    <th>지식명</th>
		    <td colspan="5">${jobKnwldgManage.knwldgNm}</td>
		</tr>
	  
		<tr>  
			<th>지식내용</th>
			<td colspan="5">
				<label for="knwldgCn" class="disp_none">지식 내용</label>
				<textarea name="knwldgCn" id="knwldgCn" style="width: 80%;" rows="10" cols="100" readonly="readonly"><c:out value='${jobKnwldgManage.knwldgCn}'/></textarea></td>
		</tr>
		
		<tr>  
		    <th>첨부파일</th>
		    <td colspan="5">
		    	<!-- 첨부파일 테이블 레이아웃 설정 Start.. -->
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfs.do" > 
					<c:param name="param_atchFileId" value="${jobKnwldgManage.atchFileId}" /> 
				</c:import>       
				<!-- 첨부파일 테이블 레이아웃 End. -->
				<input name="atchFileId" type="hidden" value="<c:out value='${jobKnwldgManage.atchFileId}'/>"/>
			</td>
		</tr>
		
		</tbody>
		</table>
	</div>

	<!-- 댓글 BODY -->
	<c:import url="/com/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
		<c:param name="type" value="body" />
		<c:param name="cmtNttId" value="${jobKnwldgManage.knwldgNo}" />
	</c:import>
</div>

	<!-- 댓글 변수 재호출시 필요 -->
	<input type="hidden" name="nttId" value="${jobKnwldgManage.knwldgNo}">
	<input type="hidden" name="addCountYn" value="N">
	
	<!-- Hidden값 -->
	<input type="hidden" name="knwldgNo" value="<c:out value='${jobKnwldgManage.knwldgNo}'/>"/>
	<input type="hidden" name="knwldgNm"  value="<c:out value='${jobKnwldgManage.knwldgNm}'/>"/>
</form>
