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
   * @Class Name : EgovUserUpdt.jsp
   * @Description : 사용자 등록 화면
   * @Modification Information
   * @
   * @  수정일      수정자            수정내용
   * @ -------        --------    ---------------------------
   * @ 2012.07.12   김지완          최초 생성
   *
   *  @user 배치운영환경 김지완
   *  @since 2012.07.12
   *  @version 1.0 
   *  @see
   */
%>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<!-- 첨부파일  -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovFileInit.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<validator:javascript formName="userUpdate" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectUserList() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserList.do'/>";
	varFrom.submit();       
}


function fncUserUpdate() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserUpdate.do'/>";

	if (!validateUserUpdate(varFrom)) {
		return;
	} else {

		//사용자명 특수문자 사용여부확인
		if (fnCheck(document.userManage.userNm.value)) {
			alert("사용자명에 특수문자는 사용할 수 없습니다.");
			document.userManage.userNm.select();
			return;
		}

		//주소 특수문자 사용여부확인
		if (fnCheck(document.userManage.adres.value)) {
			alert("주소에 특수문자는 사용할 수 없습니다.");
			document.userManage.adres.select();
			return;
		}

		//전화번호 확인
		var regexObj = /^([0-9]{2,3})[-]([0-9]{3,4})[-]([0-9]{4})$/;
		if (!regexObj.test(document.userManage.moblphonNo.value)) {
			window
					.alert("전화번호 형식에 맞지 않습니다. ooo-oooo-oooo의 형식으로 전화번호를 다시 입력해 주세요");
			document.userManage.moblphonNo.select();
			return;
		}
		if (confirm("수정 하시겠습니까?")) {
			varFrom.submit();
		}
	}
}

function fncUserDelete() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserDelete.do'/>";
	if (confirm("사용불가로 전환하시겠습니까?")) {
		varFrom.submit();
	}
}
function fncUserPasswordView() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserPasswordUpdateView.do'/>";
	varFrom.submit();
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

$(document).ready(function() {
	fn_egov_initManage('userManage');
});
</script>

<form:form name="userManage" commandName="userManage" method="post" enctype="multipart/form-data">
<div class="contsBody">
	<h2>사용자 관리</h2>
	<div class="location">사용자 관리 > 사용자 관리 > <strong>수정</strong></div>

	<div class="Btn">
		<c:if test="${adminYn == 'Y'}">
		<span class="bbsBtn"><a href="javascript:fncSelectUserList()" title="사용자 목록 화면으로 이동">목록</a></span>
		</c:if>
		
		<span class="bbsBtn"><a href="javascript:fncUserUpdate()" title="사용자 수정 화면으로 이동">수정</a></span>
		
		<c:if test="${adminYn == 'Y'}">
		<c:if test="${userManage.useAt == 'Y'}">
			<span class="bbsBtn"><a href="javascript:fncUserDelete()" title="선택한 사용자를 사용불가로 전환">사용불가로 전환</a></span>
		</c:if>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table summary="사용자ID, 사용자명, 비밀번호변경버튼, 사용여부, 소속부서, 전화번호, 주소, 이메일, 첨부파일 순으로 보여지는 사용자 수정입니다.">
		<caption>사용자관리 수정</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자ID</th>
			<td>${userManage.userId}</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자명</th>
			<td>
				<label for="userNm" class="disp_none">사용자명</label>
				<input name="userNm" id="userNm" type="text" value="<c:out value='${userManage.userNm}'/>"  size="25" maxlength="20" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><span class="bbsBtn"><a href="javascript:fncUserPasswordView()" title="비밀번호 변경 화면으로 이동">비밀번호변경</a></span></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용여부</th>
			<td>
				<label for="useAt" class="disp_none">사용여부</label>
				<form:select path="useAt"  title="사용여부">
					<form:option value="Y" label="사용가능"/>
					<form:option value="N" label="사용불가"/>											
				</form:select>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />소속부서</th>
			<td>
				<label for="deptId" class="disp_none">소속부서</label>
				<form:select path="deptId"  title="소속부서">
					<form:options items="${deptList}" itemValue="code" itemLabel="codeNm"  title="소속부서"  />													
				</form:select>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />전화번호</th>
			<td>
				<label for="moblphonNo" class="disp_none">전화번호</label>
				<input name="moblphonNo" id="moblphonNo" type="text" value="<c:out value="${userManage.moblphonNo}"/>" maxLength="20" size="20" /></td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<label for="adres" class="disp_none">주소</label>
				<input name="adres" id="adres" type="text" value="<c:out value="${userManage.adres}"/>" maxLength="50" size="50" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />E-mail</th>
			<td>
				<label for="emails" class="disp_none">E-mail</label><input name="emails" id="emails" type="text" value="<c:out value="${userManage.emails}"/>" maxLength="25" size="25" /></td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>${userManage.registDe}</td>
		</tr>
		
		<tr>  
			<th>첨부파일</th>
			<td>
				<div id="file_upload_posbl"  style="display:none;" > 
					<label for="file_1" class="disp_none">첨부파일</label>   
					<input name="file_1" id="egovComFileUploader" title="파일첨부" type="file" size="1px"/>
					<div id="egovComFileList"></div>
				</div>
				<div id="file_upload_imposbl"  style="display:none;" ></div>
				
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectImageFileInfs.do" > 
					<c:param name="atchFileId" value="${userManage.imageFile}" /> 
				</c:import>    
			</td>
		</tr>
	
		<!-- 이미지 첨부파일목록 -->
		<tr>  
			<th>첨부파일목록</th>
			<td>
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectFileInfsForUpdate.do">
					<c:param name="param_atchFileId" value="${userManage.imageFile}" />
				</c:import>
			</td>
		</tr>
		</tbody>
		</table>
	</div>
</div>

	<!-- Hidden값 -->
	<input type="hidden" name="userId" value="<c:out value='${userManage.userId}'/>"/>
	
	<!-- 첨부 파일 유무 확인 -->
	<c:if test="${userManage.imageFile eq null || userManage.imageFile eq ''}">
		<input type="hidden" name="fileListCnt" value="0" />
		<input name="atchFileAt" type="hidden" value="N">
	</c:if> 						
	<c:if test="${userManage.imageFile ne null && userManage.imageFile ne ''}">
		<input name="atchFileAt" type="hidden" value="Y"> 
	</c:if>
	
	<!-- 첨부 파일 삭제후 return되는 URL -->
	<input type="hidden" name="returnUrl" value="<c:url value='/bopr/uam/EgovUserUpdateView.do'/>"/>
	
	<!-- 첨부파일 갯수 한정 -->			  
	<input type="hidden" name="posblAtchFileNumber" value="1" />  
	
</form:form>
