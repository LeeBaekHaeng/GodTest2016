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
   * @Class Name : EgovFtpIntrlInsert.jsp
   * @Description : FTP연동 등록 화면
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
<script type="text/javascript" src="<c:url value='/egovframework/js/common/calendarPopup.js' />"></script>
<validator:javascript formName="ftpIntrlManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectFtpIntrlList() {
	var varFrom = document.getElementById("ftpIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovFtpIntrlList.do'/>";
	varFrom.submit();       
}


function fncFtpIntrlInsert() {

	var varFrom = document.getElementById("ftpIntrlManage");
	varFrom.action = "<c:url value='/bopr/jim/EgovFtpIntrlInsert.do'/>";

	if (!validateFtpIntrlManage(varFrom)) {
		return;
	} else {
		
		//FTP 연동명 특수문자 사용여부확인
    	if(fnCheck(document.ftpIntrlManage.ftpIntrlckNm.value)){
    		alert("FTP연동명에 특수문자는 사용할 수 없습니다.");
    		document.ftpIntrlManage.ftpIntrlckNm.select();
    		return;
    	}
		
		//FTP주소 확인
   		var regexObj =  /^([0-9]{1,3})[.]([0-9]{1,3})[.]([0-9]{1,3})[.]([0-9]{1,3})[:]([0-9]{1,4})$/;
		if (!regexObj.test(document.ftpIntrlManage.ftpAdres.value)) {
    		window.alert("FTP주소 형식에 맞지 않습니다.  ooo.ooo.ooo.ooo:oooo의 형식으로 주소를 다시 입력해 주세요");
    		document.ftpIntrlManage.ftpAdres.select();
    		return;
    	}
		
		//비밀번호 확인
    	if(document.ftpIntrlManage.password.value != document.ftpIntrlManage.password2.value){
    		window.alert("입력하신 비밀번호가 다릅니다. 비밀번호를 확인해 주세요.");
    		document.ftpIntrlManage.password2.select();
    		return;
    	}
		
		if(fnPassCheck(document.ftpIntrlManage.password.value)){
			if (confirm("저장 하시겠습니까?")) {
				varFrom.submit();
			}
		}else{
			return;
		}
		
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

function fnPassCheck(str){
    var blank_pattern = /[\s]/g;

    if(blank_pattern.test(str) == true ){
        alert("공백은 사용할 수 없습니다.");
        document.ftpIntrlManage.password.select();
        return false;
    } else {
   		return true;
	}
}
</script>

<form:form commandName="ftpIntrlManage" name="ftpIntrlManage" method="post" >
<!-- 상세내용 -->
<div class="contsBody">
	<h2>FTP연동 관리</h2>
	<div class="location">연동서비스 관리 > FTP연동 관리 > <strong>등록</strong></div>

	<div class="Btn">
		<span class="bbsBtn"><a href="javascript:fncSelectFtpIntrlList()">목록</a></span>
		<span class="bbsBtn"><a href="javascript:fncFtpIntrlInsert()">등록</a></span>
	</div>

	<div class="bbsDetail">
		<table  summary="FTP연동관리 등록입니다.">
		<caption>FTP연동관리 등록</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />FTP연동명</th>
			<td>
				<label for="ftpIntrlckNm" class="disp_none">FTP연동명</label>
				<input name="ftpIntrlckNm" id="ftpIntrlckNm" type="text" maxLength="50" size="50" />
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />FTP주소</th>
			<td>
				<label for="ftpAdres" class="disp_none">FTP주소</label>
				<input name="ftpAdres" id="ftpAdres" type="text" maxLength="20" size="20" />
				예)192.168.000.000:21(21은 FTP 포트번호)
			</td>
		</tr>
		 
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />사용자ID</th>
			<td>
				<label for="userId" class="disp_none">사용자ID</label>
				<input name="userId" id="userId" type="text" maxLength="20" size="20" />
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />비밀번호</th>
			<td>
				<label for="password" class="disp_none">비밀번호</label>
				<input name="password" id="password" type="password" maxLength="50" size="50" />
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />비밀번호 확인</th>
			<td>
				<label for="password2" class="disp_none">비밀번호 확인</label>
				<input name="password2" id="password2" type="password" maxLength="50" size="50" />
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />첨부파일배포경로</th>
			<td>	
				<label for="batchWdtbPath" class="disp_none">첨부파일배포경로</label>
				<input name="batchWdtbPath" id="batchWdtbPath" type="text" maxLength="100" size="100" />
			</td>
		</tr>
		
		<tr>  
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="중요표시" />설정파일배포경로</th>
			<td>	
				<label for="cfgWdtbPath" class="disp_none">설정파일배포경로</label>
				<input name="cfgWdtbPath" id="cfgWdtbPath" type="text" maxLength="100" size="100" />
			</td>
		</tr>
		
		</tbody>
		</table>
	</div>
</div>
	<!-- 등록자 -->
 	<input type="hidden" name="frstRegisterId" value="${loginUser.id}" />
 	
</form:form>
