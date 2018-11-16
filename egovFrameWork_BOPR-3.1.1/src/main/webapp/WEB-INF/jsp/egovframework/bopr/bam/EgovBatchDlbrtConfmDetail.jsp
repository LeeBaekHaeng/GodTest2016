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
  * @Class Name : EgovBatchDlbrtConfmDetail.jsp
  * @Description : 배치심의 수정 화면
  * @Modification Information
  * @
  * @  수정일           수정자          수정내용
  * @ -------    --------  ---------------------------
  * @ 2012.07.16 유현웅           최초 생성
  *
  *  @author 유현웅
  *  @since 2012.07.16
  *  @version 1.0 
  *  @see
  */
%>
<c:set var="registerFlag" value="${barchDlbrt.wdtbAt=='Y' ? 'WDTB' : (empty batchDlbrt.batchDlbrtResultCode ? 'UPDATE' : (batchDlbrt.batchDlbrtResultCode=='CN' ? 'CONFIRM' : (batchDlbrt.batchDlbrtResultCode=='RT'? 'REJECT' : 'RECONFIRM')))}"/>
<c:set var="registerFlagName" value="${empty batchDlbrt.batchDlbrtNo ? '배치심의 등록' : '배치심의 수정'}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<validator:javascript formName="batchDlbrt" staticJavascript="false" xhtml="true" cdata="false"/>

<script type="text/javaScript" language="javascript">

function fncSelectBatchDlbrtList() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtConfmList.do'/>";
	varFrom.submit();       
}

function fncBatchDlbrtUpdate() {
	var checkCn = CheckStrLength();
	if(!checkCn){
		alert("배치설명은 500글자를 넘을수 없습니다.");
		return;
	}
	
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtConfmUpdate.do'/>";

	if(!validateBatchDlbrt(varFrom)){
        return;
    }else{
    	if(confirm("저장 하시겠습니까?")){
        	varFrom.submit();
    	}else{
    		return;
    	}
    }
}

function fncBatchDlbrtDelete() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/bam/EgovBatchDlbrtConfmDelete.do'/>";
	if(confirm("삭제 하시겠습니까?")){
	    varFrom.submit();
	}
}

function fncBatchConfm() {
	var varForm = document.getElementById("batchDlbrtManage");
	varForm.action = "<c:url value='/bopr/bam/EgovBatchDlbrtRegistView.do'/>";
	varForm.submit();   
}

function fncBatchWdtb() {
	var varFrom = document.getElementById("batchDlbrtManage");
	varFrom.action = "<c:url value='/bopr/sim/selectBatchWdtb.do'/>";
	varFrom.submit();
}

//내용 입력글자 제한하기
function CheckStrLength() {
  var f = document.batchDlbrtManage.batchDc.value.length;
  var msglen = 500; //최대 길이
	
  if(f > msglen){
	  return false;
  }else
	  return true;
}
</script>

<form:form name="batchDlbrtManage" commandName="batchDlbrtManage" method="post">
<div class="contsBody">
	<h2>배치심의 관리</h2>
	<div class="location">배치심의 > 배치심의 관리 > <strong>상세정보</strong></div>

	<div class="Btn">
		<span class="bbsBtn" title="배치심의관리 목록"><a href="javascript:fncSelectBatchDlbrtList()">목록</a></span>
		<c:if test="${registerFlag == 'WDTB'}">
		<span class="bbsBtn" title="배치심의관리 수정"><a href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		</c:if>
		<c:if test="${registerFlag == 'CONFIRM'}">
		<span class="bbsBtn" title="배치심의관리 수정"><a href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn" title="배치심의관리 배포"><a href="javascript:fncBatchWdtb()">배포</a></span>
		</c:if>
		<c:if test="${registerFlag == 'REJECT' || registerFlag == 'RECONFIRM'}">
		<span class="bbsBtn" title="배치심의관리 수정"><a href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn" title="배치심의관리 삭제"><a href="javascript:fncBatchDlbrtDelete()">삭제</a></span>
		<span class="bbsBtn" title="배치심의관리 재심의요청"><a href="javascript:fncBatchConfm()">재심의</a></span>
		</c:if>
		<c:if test="${registerFlag == 'UPDATE'}">
		<span class="bbsBtn" title="배치심의관리 수정"><a href="javascript:fncBatchDlbrtUpdate()">수정</a></span>
		<span class="bbsBtn" title="배치심의관리 삭제"><a href="javascript:fncBatchDlbrtDelete()">삭제</a></span>
		<span class="bbsBtn" title="배치심의관리 심의요청"><a href="javascript:fncBatchConfm()">심의</a></span>
		</c:if>
	</div>

	<div class="bbsDetail">
		<table  summary="배치심의번호, 업무심의번호, 업무심의명, 배치ID, 배치명, 배치설명, 최초등록자ID, 최종수정자ID, 등록일시, 수정일시 등의  배치심의요청관리 상세정보입니다.">
		<caption>배치심의관리 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		
		<!-- 기존 테이블 내용 입력 START -->
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치심의번호</th>
			<td>
			<label for="batchDlbrtNo" class="disp_none">배치심의번호</label>
			<input name="batchDlbrtNo" id="batchDlbrtNo" title="배치심의번호" type="text" readonly value="<c:out value='${batchDlbrt.batchDlbrtNo}'/>" maxLength = "13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />업무심의번호</th>
			<td>
			<label for="jobDlbrtNo" class="disp_none">업무심의번호</label>
			<input name="jobDlbrtNo" id="jobDlbrtNo" title="업무심의번호" type="text" readonly value="<c:out value='${batchDlbrt.jobDlbrtNo}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th>업무심의명</th>
			<td>
			<label for="jobDlbrtNo" class="disp_none">업무심의명</label>
			<input name="jobDlbrtNm" id="jobDlbrtNm" title="업무심의명" type="text" readonly value="<c:out value='${batchDlbrt.jobDlbrtNm}'/>"  maxLength="13" size="20" /></td>
		</tr>
		<tr>
			<th><img src="/images/egovframework/bopr/blt4.gif" alt="필수입력" />배치ID</th>
			<td>
			<label for="batchId" class="disp_none">배치ID</label>
			<input name="batchId" id="batchId" title="배치ID" type="text" readonly value="<c:out value='${batchDlbrt.batchId}'/>"  maxLength="13" size="50" /></td>
		</tr>
		<tr>
			<th>배치명</th>
			<td>
			<label for="batchNm" class="disp_none">배치명</label>
			<input name="batchNm" id="batchNm" title="배치명" type="text" readonly value="<c:out value="${batchDlbrt.batchNm}"/>" maxLength="100" size="80" /></td>
		</tr>
		<tr>
			<th>배치설명</th>
			<td>
			<label for="batchDc" class="disp_none">배치설명</label>
			<textarea name="batchDc" id="batchDc" title="배치설명" rows="5" cols="80"><c:out value="${batchDlbrt.batchDc}"/></textarea></td>
		</tr>
		<tr>
			<th>최초등록자ID</th>
			<td>
			<label for="frstRegisterId" class="disp_none">최초등록자ID</label>
			<input name="frstRegisterId" id="frstRegisterId" title="최초등록자ID" type="text" value="<c:out value="${batchDlbrt.frstRegisterId}"/>" maxLength="100" size="20" readonly /></td>
		</tr>
		<tr>
			<th>최종수정자ID</th>
			<td>
			<label for="lastUpdusrId" class="disp_none">최종수정자ID</label>
			<input name="lastUpdusrId" id="lastUpdusrId" title="최종수정자ID" type="text" value="<c:out value="${batchDlbrt.lastUpdusrId}"/>" maxLength="100" size="20" readonly /></td>
		</tr>
		<tr>
			<th>등록일시</th>
			<td>
			<label for="frstRegistPnttm" class="disp_none">등록일시</label>
			<input name="frstRegistPnttm" id="frstRegistPnttm" title="등록일시" type="text" value="<c:out value="${batchDlbrt.frstRegistPnttm}"/>" maxLength="20" size="20" readonly/></td>
		</tr>
		<tr>
			<th>수정일시</th>
			<td>
			<label for="lastUpdtPnttm" class="disp_none">수정일시</label>
			<input name="lastUpdtPnttm" id="lastUpdtPnttm" title="수정일시" type="text" value="<c:out value="${batchDlbrt.lastUpdtPnttm}"/>"  maxLength="20"  size="20" readonly/></td>
		</tr>
		</tbody>
		</table>
		
		<table id="attachInfo" summary="배치심의관리 첨부파일입니다.">
		<caption>배치심의관리 첨부파일 상세정보</caption>
		<colgroup>
			<col style="width:20%" >
			<col style="width:auto" >
		</colgroup>
		<tbody>
		<c:forEach var="batchDlbrtAtch" items="${batchDlbrtAtch}" varStatus="status">
		<tr>
			<c:if test="${batchDlbrtAtch.atchSeCode =='S'}">
				<th>설정파일</th>
			</c:if>
			<c:if test="${batchDlbrtAtch.atchSeCode !='S'}">
				<th>첨부파일</th>
			</c:if>
			<td>
				<c:import charEncoding="utf-8" url="/com/cmm/fms/selectMultiFileInfs.do">
					<c:param name="param_atchFileId" value="${batchDlbrtAtch.atchFileId}" />
					<c:param name="param_atchSeCode" value="${batchDlbrtAtch.atchSeCode}" />
					<c:param name="param_batchPath" value="${batchDlbrtAtch.batchPath}" />
					<c:param name="param_atchProcessSeCode" value="${batchDlbrtAtch.atchProcessSeCode}" />
				</c:import>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</div>
	<div class="bbsList">
	<table id="attachInfo" summary="배치심의결과번호, 업무심의번호, 배치심의결과, 배치심의내용 등의 배치심의관리 승인정보입니다.">
		<caption>배치심의관리 승인정보</caption>
		<colgroup>
			<col style="width:15%" >
			<col style="width:15%" >
			<col style="width:10%" >
			<col style="width:40%" >
		</colgroup>
		<thead>
		<tr>
			<th>배치심의결과번호</th>
			<th>업무심의번호</th>
			<th>배치심의결과</th>
			<th>배치심의내용</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="batchDlbrtResult" items="${batchDlbrtResult}" varStatus="status">
		<tr>
		    <td><c:out value="${batchDlbrtResult.batchDlbrtResultNo}"/></td>
		    <td><c:out value="${batchDlbrtResult.jobDlbrtNo}"/></td>
		    <td class="center">
		    <c:if test="${empty batchDlbrtResult.batchDlbrtResultCode }">
		        재승인 요청
		    </c:if>
		    <c:if test="${not empty batchDlbrtResult.batchDlbrtResultCode }">
		    <c:out value="${batchDlbrtResult.batchDlbrtResultCodeNm}"/>
		    </c:if>
		    </td>
		    <td class="left"><c:out value="${batchDlbrtResult.batchDlbrtCn}"/></td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</div>
	 
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${dlbrtVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${dlbrtVO.searchKeyword}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${dlbrtVO.pageIndex}'/>"/>

</form:form>
