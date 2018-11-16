<%
 /**
  * @Class Name  : EgovCcmCmmnCodeRegist.jsp
  * @Description : EgovCcmCmmnCodeRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.04.01   이중호              최초 생성
  *
  *  @author 공통서비스팀 
  *  @since 2009.04.01
  *  @version 1.0
  *  @see
  *  
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnCode" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_CmmnCode(){
	location.href = "<c:url value='/sym/ccm/cca/EgovCcmCmmnCodeList.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_CmmnCode(form){
	//코드 중복체크 확인
	if(document.cmmnCode.codeCheckYn.value == "true"){
		if(!validateCmmnCode(form)){ 			
			return;
		}else{
			if(fnCheckNotKorean(document.cmmnCode.codeId.value)){
				window.alert("코드ID에 한글을 사용할 수 없습니다.");
				document.cmmnCode.codeId.select();
				return;
			}
			
			if(fnCheck(document.cmmnCode.codeId.value)){
				window.alert("코드ID에 특수문자를 사용할 수 없습니다.");
				document.cmmnCode.codeId.select();
				return;
			}
			
			if(fnCheck(document.cmmnCode.codeIdNm.value)){
				window.alert("코드명에 특수문자를 사용할 수 없습니다.");
				document.cmmnCode.codeIdNm.select();
				return;
			}
			
			if(confirm("<spring:message code='common.save.msg'/>")){
				form.submit();
			}
			
		}
	}else{
		window.alert("코드 중복체크는 필수 입니다.");
	}  
	
}

//중복권한코드 체크
function fnCodeCheck(){
    window.open("<c:url value='/sym/ccm/cca/EgovCcmCodeDplctCnfirmView.do'/>", "CmmnCodeListPopUp", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, center=yes, width=303, height=250");
}
 
/* 한글입력 체크 */
function fnCheckNotKorean(koreanStr){
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) {
        }else{
            //한글이 있을때
            return true;
        }
    }
    return false;
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

<form:form commandName="cmmnCode" name="cmmnCode" method="post">
<div class="contsBody">
	<h2>공통코드 관리</h2>
	<div class="location">공통코드 관리 > 공통코드 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fn_egov_list_CmmnCode()" title="공통코드관리 목록 화면으로 이동">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fn_egov_regist_CmmnCode(document.cmmnCode);" title="입력한 공통코드 정보를 저장">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="분류코드, 코드ID, 코드명, 코드설명, 사용여부의 순서로 입력하는 공통코드관리 등록입니다.">
		<caption>공통코드관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />분류코드</th>          
			<td colspan="3">
				<label for="clCode" class="disp_none">분류코드</label>
				<select name="clCode" class="select" id="clCode">
				<c:forEach var="result" items="${cmmnClCode}" varStatus="status">
				<option value='<c:out value="${result.clCode}"/>'><c:out value="${result.clCodeNm}"/></option>
				</c:forEach>			  		   
				</select>
			</td>
		</tr> 
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드ID</th>          
			<td colspan="3">
				<label for="codeId" class="disp_none">코드ID</label>
			<form:input  path="codeId" size="6" maxlength="6" id="codeId" onchange="document.cmmnCode.codeCheckYn.value=false;"/>
			<a href="#LINK" onclick="fnCodeCheck();" title="새 창열기">
					<img src="/images/egovframework/bopr/search.gif" alt="중복 코드 검색" />(중복 코드 검색)
				</a>
			</td>
		</tr> 
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드명</th>          
			<td>
				<label for="codeIdNm" class="disp_none">코드명</label>
				<form:input  path="codeIdNm" size="60" maxlength="60" id="codeIdNm"/>
			</td>    
		</tr> 
		<tr> 
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />코드설명</th>
			<td>
				<label for="codeIdDc" class="disp_none">코드설명</label>
				<form:textarea path="codeIdDc" rows="3" cols="60" id="codeIdDc"/>
			</td>
		</tr> 
		<tr> 
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용여부</th>
			<td colspan="3">
				<label for="useAt" class="disp_none">사용여부</label>
				<form:select path="useAt" id="useAt">
					<form:option value="Y" label="Yes"/>
					<form:option value="N" label="No"/>
				</form:select>
			</td>    
		</tr>  
		
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden값 -->
	<input name="cmd" type="hidden" value="<c:out value='save'/>">
	
	<!-- 공통코드 중복체크 여부 -->
	<input type="hidden" name="codeCheckYn" id="codeCheckYn" value="false"/>
</form:form>
