<%--
/**
 * @Class Name  : EgovAuthorInsert.java
 * @Description : EgovAuthorInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j          최초 생성
 *
 *  @author lee.m.j
 *  @since 2009.03.11
 *  @version 1.0
 *  @see
 *
 */
--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="registerFlag" value="${empty authorManageVO.authorCode ? 'INSERT' : 'UPDATE'}"/>
<c:set var="registerFlagName" value="${empty authorManageVO.authorCode ? '권한 등록' : '권한 수정'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="authorManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
function fncSelectAuthorList() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/sec/ram/EgovAuthorList.do'/>";
	varFrom.submit();
}

function fncAuthorInsert() {

    var varFrom = document.getElementById("authorManage");
    varFrom.action = "<c:url value='/sec/ram/EgovAuthorInsert.do'/>";

  //권한코드 중복체크 확인
	if(document.authorManage.codeCheckYn.value == "true"){
		if(!validateAuthorManage(varFrom)){
		    return;
		}else{
			
			//권한명 특수문자 사용여부확인
			if (fnCheck(document.authorManage.authorNm.value)) {
				alert("권한명에 특수문자는 사용할 수 없습니다.");
				document.authorManage.authorNm.select();
				return;
			}
			
			if(confirm("저장 하시겠습니까?")){
		    	varFrom.submit();
			}
		}
	}else{
		window.alert("권한코드 중복체크는 필수 입니다.");
	}  
  
    
}

function fncAuthorUpdate() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/sec/ram/EgovAuthorUpdate.do'/>";

	if(!validateAuthorManage(varFrom)){
	    return;
	}else{
		
		//권한명 특수문자 사용여부확인
		if (fnCheck(document.authorManage.authorNm.value)) {
			alert("권한명에 특수문자는 사용할 수 없습니다.");
			document.authorManage.authorNm.select();
			return;
		}
		
		if(confirm("수정 하시겠습니까?")){
	    	varFrom.submit();
		}
	}

}

function fncAuthorDelete() {
	var varFrom = document.getElementById("authorManage");
	varFrom.action = "<c:url value='/sec/ram/EgovAuthorDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

//중복권한코드 체크
function fnCodeCheck(){
    window.open("<c:url value='/sec/ram/EgovCodeDplctCnfirmView.do'/>", "CodeListPopUp", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, center=yes, width=303, height=250");
    document.authorManage.codeCheckYn.value=true;
}
/* 특수문자체크 */
function fnCheck(str){
    var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;

    if(special_pattern.test(str)){
    	/* 걸리면 true반환 */
        return true;
    } else {
   		return false;
	}
}
</script>

<form:form commandName="authorManage" name="authorManage" method="post" >
<div class="contsBody">
	<h2>권한 관리</h2>
	<div class="location">권한 관리 > 권한 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectAuthorList()" title="권한관리 목록화면으로 이동">목록</a></span>
		
		<c:if test="${registerFlag == 'INSERT'}">
			<span class="bbsBtn"><a href="javascript:fncAuthorInsert()" title="해당 권한정보를 저장">등록</a></span>
		</c:if>
		
		<c:if test="${registerFlag == 'UPDATE'}">
			<span class="bbsBtn"><a href="javascript:fncAuthorUpdate()" title="해당 권한정보를 저장">수정</a></span>
			<span class="bbsBtn"><a href="javascript:fncAuthorDelete()" title="해당 권한을 삭제">삭제</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="권한코드, 권한명, 설명의 순서로 입력하는 권한관리 등록입니다.">
		<caption>권한관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />권한코드</th>
			<td>
				<label for="authorCode" class="disp_none">권한코드</label>
				<input name="authorCode" id="authorCode" type="text" value="<c:out value='${authorManage.authorCode}'/>" maxLength="25" size="25" title="권한코드" readonly="readonly" />
				<a href="#LINK" onclick="fnCodeCheck();" title="새 창열기">
					<img src="/images/egovframework/bopr/search.gif" alt="중복 권한코드 검색" />(중복 권한코드 검색)
				</a>
			</td>		
		</tr>
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />권한명</th>
			<td>
				<label for="authorNm" class="disp_none">권한명</label>
				<input name="authorNm" id="authorNm" type="text" value="<c:out value='${authorManage.authorNm}'/>" maxLength="40" size="40" title="권한명" /></td>
		</tr>
		
		<tr>
			<th>설명</th>
			<td>
				<label for="authorDc" class="disp_none">설명</label>
				<input name="authorDc" id="authorDc" type="text" value="<c:out value='${authorManage.authorDc}'/>" maxLength="80" size="80" title="설명" /></td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>
	
	<!-- 권한코드 중복체크 여부 -->
	<input type="hidden" name="codeCheckYn" id="codeCheckYn" value="false"/>
	
	<!-- 검색조건 유지 -->
	<c:if test="${registerFlag == 'UPDATE'}">
	<input type="hidden" name="searchCondition" value="<c:out value='${authorManageVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${authorManageVO.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${authorManageVO.pageIndex}'/>"/>
	</c:if>
	
</form:form>
