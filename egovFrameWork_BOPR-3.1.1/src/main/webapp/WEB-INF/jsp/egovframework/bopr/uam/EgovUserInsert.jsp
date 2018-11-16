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
   * @Class Name : EgovUserInsert.jsp
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

<validator:javascript formName="userInsert" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fnIdCheck(){
    window.open("<c:url value='/uss/umt/EgovIdDplctCnfirmView.do'/>", "UserListPopUp", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, center=yes, width=303, height=250");
}

function fncSelectUserList() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/bopr/uam/EgovUserList.do'/>";
	varFrom.submit();
}

function fncUserInsert() {
	var varFrom = document.getElementById("userManage");
	varFrom.action = "<c:url value='/uss/umt/EgovStplatCnfirmMber.do'/>";
	
	
	//ID 중복체크 확인
	if(document.userManage.idCheckYn.value == "true"){
		
		//유효성검사
		if(!validateUserInsert(varFrom)){         
            return;
        }else{
        	
        	//비밀번호 확인
        	if(document.userManage.password.value != document.userManage.password2.value){
        		window.alert("입력하신 비밀번호가 다릅니다. 비밀번호를 확인해 주세요.");
        		document.userManage.password2.select();
        		return;
        	}
        	
        	//사용자명 특수문자 사용여부확인
        	if(fnCheck(document.userManage.userNm.value)){
        		alert("사용자명에 특수문자는 사용할 수 없습니다.");
        		document.userManage.userNm.select();
        		return;
        	}
        	
        	//주소 특수문자 사용여부확인
        	if(fnCheck(document.userManage.adres.value)){
        		alert("주소에 특수문자는 사용할 수 없습니다.");
        		document.userManage.adres.select();
        		return;
        	}
        		
        	//전화번호 확인
       		var regexObj = /^([0-9]{2,3})[-]([0-9]{3,4})[-]([0-9]{4})$/;
			if (!regexObj.test(document.userManage.moblphonNo.value)) {
        		window.alert("전화번호 형식에 맞지 않습니다. ooo-oooo-oooo의 형식으로 전화번호를 다시 입력해 주세요");
        		document.userManage.moblphonNo.select();
        		return;
        	}
        	
        	//정보제공동의
        	if(confirm("등록을 위해 정보제공동의를 하시겠습니까?")){
        		varFrom.submit(); 
    	    }	
            
        }
		
	}else{
		window.alert("ID 중복체크는 필수 입니다.");
	}    
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

function sel_email_type() {
    var formObj = document.userManage;

    if(formObj.email3[formObj.email3.selectedIndex].value == "1") {
		document.getElementById("email2").style.display = "";
    } else {
		document.getElementById("email2").style.display = "none";
		formObj.email2.value = "";
    }
}

$(document).ready(function() {
	fn_egov_initManage('userManage');
});
</script>

<form:form method="post" commandName="userManage" name="userManage" enctype="multipart/form-data">
<div class="contsBody">
<c:if test="${leftMenu == 'regist'}">
	<h2>회원 가입</h2>
	<div class="location"><strong>회원 가입</strong></div>
</c:if>
<c:if test="${leftMenu != 'regist'}">
	<h2>사용자 관리</h2>
	<div class="location">사용자 관리 > 사용자 관리 > <strong>등록</strong></div>
</c:if>
	

	<div class="Btn">
		<c:if test="${leftMenu != 'regist'}">
		<span class="bbsBtn"><a href="javascript:fncSelectUserList()" title="사용자 목록 화면으로 이동">목록</a></span>
		</c:if>
		<span class="bbsBtn"><a href="javascript:fncUserInsert()" title="입력한 정보를 바탕으로 하는 사용자를 저장">등록</a></span>
		
	</div>

	<div class="bbsDetail">
		<table summary="사용자ID, 사용자명, 비밀번호, 비밀번호 확인, 소속부서, 전화번호, 주소, E-mail, 첨부파일 순서로 입력하는 사용자관리 등록입니다.">
		<caption>사용자관리 목록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자ID</th>
			<td>
				<label for="userId" class="disp_none">사용자ID</label>
				<input name="userId" id="userId" type="text" value="" size="25" maxlength="20" readonly="readonly"/>
				<a href="#LINK" onclick="fnIdCheck();" title="새 창열림">
					<img src="/images/egovframework/bopr/search.gif" alt="중복아이디 검색" />(중복아이디 검색)
				</a>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />사용자명</th>
			<td>
				<label for="userNm" class="disp_none">사용자명</label>
				<input name="userNm" id="userNm" type="text" value="" size="25" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />비밀번호</th>
			<td>
				<label for="password" class="disp_none">비밀번호</label>
				<input name="password" id="password" type="password" value="" size="15" maxlength="12"/>
			</td>
		</tr>
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />비밀번호 확인</th>
			<td>
				<label for="password2" class="disp_none">비밀번호 확인</label>
				<input name="password2" id="password2" type="password" value="" size="15" maxlength="12"/>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />소속부서</th>
			<td>
				<label for="deptId" class="disp_none">소속부서</label>
				<form:select path="deptId"  title="소속부서" >
					<form:option value="" label="소속부서선택"/>
					<form:options items="${deptList}" itemValue="code" itemLabel="codeNm"  title="소속부서"  />													
				</form:select>
			</td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />전화번호</th>
			<td>
				<label for="moblphonNo" class="disp_none">전화번호</label>
				<input name="moblphonNo" id="moblphonNo" type="text" value="" alt="sdfg" maxLength="20" size="20"/>
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<label for="adres" class="disp_none">주소</label>
				<input name="adres" id="adres" type="text" value="" maxLength="60" size="60"/>
			</td>
		</tr>

		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />E-mail</th>
			<td>
				<label for="emails" class="disp_none">E-mail</label>
				<input name="emails" id="emails" type="text" value="" maxLength="25" size="25"/>
				<%-- @
				<input name="email2" id="email2" type="text" size="20" style="display: none;"/>
				
				<select id="email3" name="email3" style="" title="검색어 선택" onChange="javascript:sel_email_type()">
					<option value="0">이메일선택</option>
					<option value="1">직접입력</option>
					<c:forEach var="email2" items="${emailList}" varStatus="status">
					<option value="${email2.codeNm}">${email2.codeNm}</option>
					</c:forEach>
				</select> --%>
			</td>
		</tr>
		
		<tr>  
			<th>첨부파일</th>
			<td>
				<div id="file_upload_posbl"  style="display:none;" >
					<label for="file_1" class="disp_none">첨부파일</label>
					<input name="file_1" id="egovComFileUploader" title="파일첨부" type="file"  />
					<div id="egovComFileList"></div>
				</div>
				<div id="file_upload_imposbl"  style="display:none;" ></div> 
			</td>
		</tr>
		
		<!-- 이미지 첨부파일목록 -->
		<c:if test="${userManage.imageFile ne null && userManage.imageFile ne ''}">
		<tr>
			<th>첨부파일목록</th>
			<td>
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectImageFileInfs.do">
					<c:param name="param_atchFileId" value="${userManage.imageFile}" />
				</c:import>
			</td>
		</tr>
		</c:if>
	 
		</tbody>
		</table>
	</div>
</div>

	<!-- 첨부 파일 유무 확인 -->
	<c:if test="${userManage.imageFile eq null || userManage.imageFile eq ''}">
		<input type="hidden" name="fileListCnt" value="0" />
		<input name="atchFileAt" type="hidden" value="N">
	</c:if> 						
	<c:if test="${userManage.imageFile ne null && userManage.imageFile ne ''}">
		<input name="atchFileAt" type="hidden" value="Y"> 
	</c:if>
	
	<!-- 첨부파일 갯수 한정 -->					  
	<input type="hidden" name="posblAtchFileNumber" value="1" />  
	
	<!-- ID중복체크 여부 -->
	<input type="hidden" name="idCheckYn" id="idCheckYn" value="false"/>
	
</form:form>   
