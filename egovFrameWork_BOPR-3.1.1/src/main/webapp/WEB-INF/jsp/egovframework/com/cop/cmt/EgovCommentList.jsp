<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovCommentList.jsp
  * @Description : 댓글
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.06.29   한성곤          최초 생성
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.29
  *  @version 1.0
  *  @see
  *
  */
%>


<c:if test="${type == 'head'}">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="comment" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
function fn_egov_insert_commentList() {
	if (!validateComment(document.jobKnwldgManage)){
		return;
	}
	
	if (confirm('<spring:message code="common.regist.msg" />')) {
		document.jobKnwldgManage.action = "<c:url value='/com/cop/cmt${prefix}/insertComment.do'/>";
		document.jobKnwldgManage.submit();
	}
}

function fn_egov_updt_commentList() {
	if (!validateComment(document.jobKnwldgManage)){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		document.jobKnwldgManage.isModified.value = "true";
		document.jobKnwldgManage.action = "<c:url value='/com/cop/cmt${prefix}/updateComment.do'/>";
		document.jobKnwldgManage.submit();
	}
}

function fn_egov_selectCommentForupdt(commentNo, index) {
	
	document.jobKnwldgManage.commentNo.value = commentNo;
	document.jobKnwldgManage.action = "<c:url value='/bopr/ikm/EgovJobKnwldg.do'/>";
	document.jobKnwldgManage.submit();
}

function fn_egov_deleteCommentList(commentNo, index) {
	<c:if test="${anonymous == 'true'}">
	var passwordObject;

	if (typeof(document.jobKnwldgManage.testPassword.length) == 'undefined') {
		password = document.jobKnwldgManage.testPassword;
	} else {
		password = document.jobKnwldgManage.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	document.jobKnwldgManage.confirmPassword.value = password.value;
	</c:if>

	if (confirm('정말로 삭제하시겠습니까?')) {
		document.jobKnwldgManage.isModified.value = "true";
		document.jobKnwldgManage.commentNo.value = commentNo;
		document.jobKnwldgManage.action = "<c:url value='/com/cop/cmt${prefix}/deleteComment.do'/>";
		document.jobKnwldgManage.submit();
	}
}

//페이지 이동
function fn_egov_select_commentList(pageNo) {
	document.jobKnwldgManage.subPageIndex.value = pageNo;
	document.jobKnwldgManage.commentNo.value = '';
	document.jobKnwldgManage.action = "<c:url value='/bopr/ikm/EgovJobKnwldg.do'/>";
	document.jobKnwldgManage.submit();
}
</script>

</c:if>

<c:if test="${type == 'body'}">
<input name="subPageIndex" type="hidden" value="<c:out value='${commentVO.subPageIndex}'/>">
<input name="commentNo" type="hidden" value="<c:out value='${commentVO.commentNo}'/>">
<input name="isModified" type="hidden" value="false">

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="commentPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<div class="locationComment">
<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" style="vertical-align: middle" alt="제목아이콘이미지">
   &nbsp;댓글 - <c:out value="${resultCnt}"/>개
</div>

<c:forEach var="result" items="${resultList}" varStatus="status">
	<div class="com-commentList">
		<p>
			<span>
				<c:choose>
	    			<c:when test="${not empty result.wrterNm}">
	    				<b><c:out value="${result.wrterNm}" /></b>&nbsp;
	    			</c:when>
	    			<c:otherwise>
	    				<b><c:out value="${result.frstRegisterNm}" /></b>&nbsp;
	    			</c:otherwise>
    			</c:choose>
    			
    			<c:if test="${result.cmtKnwldgEvl == '1'}">★</c:if>
		    	<c:if test="${result.cmtKnwldgEvl == '2'}">★★</c:if>
		    	<c:if test="${result.cmtKnwldgEvl == '3'}">★★★</c:if>
		    	<c:if test="${result.cmtKnwldgEvl == '4'}">★★★★</c:if>
		    	<c:if test="${result.cmtKnwldgEvl == '5'}">★★★★★ </c:if>
   			</span>
			<span class="uss-txtDate"> <c:out value="${result.frstRegisterPnttm}" /> </span>
		</p>
		
		<c:if test="${anonymous == 'true' || result.wrterId == sessionUniqId}">
   			<p class="com-delete">
   			 	<a href="javascript:fn_egov_deleteCommentList('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');"><spring:message code="button.delete" /></a>
			</p>
			<p class="com-modify">
				<a href="javascript:fn_egov_selectCommentForupdt('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');"><spring:message code="button.update" /></a>
   			</p>
		</c:if>
		
		<ul class="com-commContent2">
			<li><c:out value="${result.commentCn}" /></li>
		</ul>
		
   
	    <c:if test="${anonymous == 'true'}">
	    	패스워드 : <input name="testPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
	    </c:if>
	</div>	    
</c:forEach>

<c:if test="${fn:length(resultList) == 0}">
	댓글이 없습니다.
</c:if>
	
<div align="center">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_commentList" />
</div>

<div class="bbsDetail">
	<table  summary="댓글목록입니다.">
		<caption>댓글 목록</caption>
		<colgroup>
			<col style="width:10%" >
			<col style="width:auto" >
		</colgroup>
		
		<tbody>
			<tr>
				<th>
					<img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />작성자
				</th>
				
				<td>
					<c:out value="${commentVO.wrterNm}" /><input name="wrterNm" type="hidden" size="20" value='<c:out value="${commentVO.wrterNm}" />' maxlength="20" title="작성자이름입력">
					<form:errors path="wrterNm" />
				</td>
			</tr>
			
			<tr>
				<th>
					<img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />평가점수
				</th>
				
				<td>
					<label for="cmtKnwldgEvl" class="disp_none">평가점수</label>
					<select name="cmtKnwldgEvl"  title="지식평가">
						<option value="1" label="★"> ★ </option>
						<option value="2" label="★★"> ★★ </option>
						<option value="3" label="★★★"> ★★★ </option>
						<option value="4" label="★★★★"> ★★★★ </option>
						<option value="5" label="★★★★★"> ★★★★★ </option>									
		 			</select>
				</td>
			</tr>
			
			<tr>
				<th>
					<img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />내용
				</th>
				
				<td>
					<label for="commentCn" class="disp_none">댓글내용입력</label>
					<textarea name="commentCn" class="textarea"  cols="50" rows="4"  style="width:80%;" title="댓글내용입력"><c:out value="${commentVO.commentCn}" /></textarea>
	      			<form:errors path="commentCn" />
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="BtnComment">
		<c:choose>
			<c:when test="${commentVO.commentNo == ''}">
		      <span class="bbsBtn"><a href="javascript:fn_egov_insert_commentList();">댓글등록</a></span>
			</c:when>
			<c:otherwise>
		      <span class="bbsBtn"><a href="javascript:fn_egov_updt_commentList();">댓글수정</a></span>
			</c:otherwise>
		</c:choose>
	      <span class="bbsBtn"><a href="javascript:fn_egov_select_commentList('1')">댓글초기화</a></span>
</div>
<c:if test="${not empty subMsg}">
	<script>
		alert("<c:out value='${subMsg}'/>");
	</script>
</c:if>

</c:if>
