<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	/**
	 * @Class Name : EgovBatchWdtbDetail.jsp
	 * @Description : 배치배포 상세정보 화면
	 * @Modification Information
	 * @
	 * @ 수정일      수정자    수정내용
	 * @ ----------  --------  ---------------------------
	 * @ 2012.07.11  이병권    최초 생성
	 *
	 *  @author SDS 이병권
	 *  @since 2012.07.11
	 *  @version 0.9 
	 *  @see
	 */
%>

<!-- 공통 Util Javascript -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cmm/jquery-1.4.2.min.js"/>"></script>

<script type="text/javaScript" language="javascript">
<!--
    /* form */
    var objParam;

    /*
        화면 onLoad
    */
    $(document).ready(function()
    {
        objParam = document.getElementById('parameter');

        objParam.jobDlbrtNo.value      = '<c:out value="${batchWdtb.jobDlbrtNo}" />';
        objParam.batchDlbrtNo.value    = '<c:out value="${batchWdtb.batchDlbrtNo}" />';
        objParam.batchId.value         = '<c:out value="${batchWdtb.batchId}" />';
        objParam.processSeCode.value   = '<c:out value="${batchWdtb.processSeCode}" />';
        objParam.wdtbAt.value          = '<c:out value="${batchWdtb.wdtbAt}" />';

        if (fncCallAlert("<c:out value='${message}'/>"))    // 조회 실패, 배포 성공 메시지
        {
            fncList();
        }
        fncCallAlert("<c:out value='${errorMssage}'/>");    // 배포 실패 메시지
    });

    /*
        '목록' Button OnClick
    */
    function fncList()
    {
        objParam.action = "<c:url value='/bopr/sim/selectBatchWdtbList.do'/>";
        
        if ("Y" == "${parameter.searchUseYn}")
        {
            objParam.jobSeCode.value       = "${parameter.jobSeCode}";
            objParam.wdtbAt.value          = "${parameter.wdtbAt}";
            objParam.searchCondition.value = "${parameter.searchCondition}";
            objParam.searchKeyword.value   = "${parameter.searchKeyword}";
            objParam.pageIndex.value       = "${parameter.pageIndex}";
        }
        else
        {
        	objParam.jobSeCode.value = "";
            objParam.wdtbAt.value    = "";
        }

         objParam.submit();
    }

    /*
        '버튼' Button OnClick
    */
    function fncWdtb()
    {
        if ("Y" == "<c:out value='${batchWdtb.wdtbAt}'/>")
        {
            alert("이미 배포되었습니다.");
            return;
        }

        if (fncCallAlert("<c:out value='${ftpNeed}'/>"))
        {
        	return;
        }
        
        var url = "<c:url value='/bopr/sim/selectFtpPasswordPopup.do' />";
        window.open(url, "FTPPasswordPopup", "toolbar=no, menubar=no, status=no, scrollbar=no, resizable=no, width=400, height=120");
    }
    
    function fncFtpPasswordCallback()
    {
    	objParam.action = "/bopr/sim/insertBatchWdtb.do";
        objParam.submit();
    }
-->
</script>

<form id="parameter" name="parameter" method="post" action="">

    <div class="contsBody">

        <!-- Parameter Start -->
        <!-- 목록 조회 조건 설정 위한 parameter -->
        <input type="hidden" name="searchCondition" id="searchCondition">
        <input type="hidden" name="searchKeyword" id="searchKeyword">
        <!-- 페이징 처리 위한 parameter -->
        <input type="hidden" name="pageIndex" value='1'>
        <!-- FTP 비밀번호 parameter -->
        <input type="hidden" name="ftpPassword" id="ftpPassword">
        <!-- Parameter End -->

        <!-- Title Start -->
        <h2>배치배포관리</h2>
        <div class="location">배치심의 &gt; 배치배포관리 &gt; <strong>상세정보</strong></div>
        <!-- Title End -->

        <!-- Button Start -->
        <div class="Btn">
            <span class="bbsBtn"><a href="javascript:fncList()" title="배치배포 목록 화면 이동">목록</a></span>
          <c:if test="${batchWdtb.wdtbAt == 'N'}">
            <span class="bbsBtn"><a href="javascript:fncWdtb()" title="배치배포 기능, 새창열림">배포</a></span>
          </c:if>
        </div>
        <!-- Button End -->

        <!-- Contents Start -->
        <div class="bbsDetail">
            <table  summary="업무심의번호, 업무구분, 업무심의제목, 업무심의내용, 배치심의번호, 배치I.D., Job이름(배치명), 배치설명, 설정파일, 첨부파일, 처리구분, 배포여부 순서로 배치배포 상세정보를 나타낸 표입니다.">
                <caption>배치배포 상세정보</caption>
                <colgroup>
                    <col style="width:20%" >
                    <col style="width:auto">
                </colgroup>
                <tbody>
                    <tr>
                        <th>업무심의번호</th>
                        <td>
                            <input type="hidden" id="jobDlbrtNo" name="jobDlbrtNo" size="30" title="업무심의번호" readonly="readonly">
                            <c:out value="${batchWdtb.jobDlbrtNo}" />
                        </td>
                    </tr>
                    <tr>
                        <th>업무구분</th>
                        <td>
                            <input type="hidden" id="jobSeCode" name="jobSeCode" title="업무구분">
                            <c:out value="${batchWdtb.jobSeCodeNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>업무심의제목</th>
                        <td>
                            <c:out value="${batchWdtb.jobDlbrtNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>업무심의내용</th>
                        <td>
                            <pre><c:out value="${batchWdtb.jobDlbrtCn}" /></pre>
                        </td>
                    </tr>
                    <tr>
                        <th>배치심의번호</th>
                        <td>
                            <input type="hidden" id="batchDlbrtNo" name="batchDlbrtNo" size="30" title="배치심의번호" readonly="readonly">
                            <c:out value="${batchWdtb.batchDlbrtNo}" />
                        </td>
                    </tr>
                    <tr>
                        <th>배치ID</th>
                        <td>
                            <input type="hidden" id="batchId" name="batchId" size="30" title="배치ID" readonly="readonly">
                            <c:out value="${batchWdtb.batchId}" />
                        </td>
                    </tr>
                    <tr>
                        <th>Job이름(배치명)</th>
                        <td>
                            <c:out value="${batchWdtb.batchNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>배치설명</th>
                        <td>
                            <pre><c:out value="${batchWdtb.batchDc}" /></pre>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            설정파일&nbsp;
                            <img src="/images/egovframework/bopr/search.gif"
title="배치 설정 XML 파일을 등록합니다. 반드시 형식에 맞는 파일을 등록해야하고 다른 배치와 Job Id, bean Id가 중복될 수 없습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다. 파일은 배포경로/상세경로의 위치에 배포됩니다." />
                        </th>
                        <td style="padding:0px;margin:0px">
                            <table id="atchCfgFileTable" summary="배치배포 설정파일" style="border-top-width:0px;">
                                <caption>배치배포 설정파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <td><strong>배포경로 : <c:out value='${cfgPath}'/></strong></td>
                                    </tr>
                                <!-- 설정파일 Start -->
                                  <c:forEach var="list" items="${atchFileList}">
                                  <c:if test="${list.atchSeCode == 'S'}">
                                    <tr>
                                        <td style="border-bottom-width:0px;">
                                            <!-- 설정파일ID -->
                                            <input type="hidden" id="cfgAtchFileId" name="cfgAtchFileId" value="${list.atchFileId}">
                                            <!-- 배포경로 -->
                                            <strong>상세경로:</strong>
                                            <label for="cfgWdtbPath" class="disp_none">설정파일 상세경로</label>
                                            <input type="text" id="cfgWdtbPath" name="cfgWdtbPath" size="50" maxlength="100" style="ime-mode:disabled;" value="<c:out value='${list.wdtbPath}' />" <c:if test="${batchWdtb.wdtbAt == 'Y'}">readonly="readonly"</c:if>>
                                            <!-- 파일정보 -->
                                          <c:forEach var="detailList" items="${list.atchFileDetailList}">
                                            <br><c:out value="${detailList.orignlFileNm}" />&nbsp;(<c:out value="${detailList.fileMg}" />&nbsp;byte)&nbsp;<c:out value="${list.errorMsg}" />
                                          </c:forEach>
                                        </td>
                                    </tr>
                                  </c:if>
                                  </c:forEach>
                                <!-- 설정파일 End -->
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            첨부파일&nbsp;
<img src="/images/egovframework/bopr/search.gif"
title="설정 XML 파일 이외의 배치 관련 파일을 등록합니다. class 파일, 파라미터 파일 등 형식에 구애 받지 않고 등록할 수 있습니다.
배포 경로는 공통(사용자관리)>연동서비스관리>FTP연동서비스관리에서 변경할 수 있습니다. 파일은 배포경로/상세경로의 위치에 배포됩니다." />
                        </th>
                        <td style="padding:0px;margin:0px;">
                            <table id="atchFileTable" summary="배치배포 첨부파일" style="border-top-width:0px;">
                                <caption>배치배포 첨부파일</caption>
                                <colgroup>
                                    <col style="width:auto">
                                </colgroup>
                                <tbody>
                                    <tr><td><strong>배포경로 : <c:out value='${batchPath}'/></strong></td></tr>
                                <!-- 첨부파일 Start -->
                                  <c:forEach var="list" items="${atchFileList}">
                                  <c:if test="${list.atchSeCode != 'S'}">
                                    <tr>
                                        <td style="border-bottom-width:0px;">
                                            <!-- 첨부파일ID -->
                                            <input type="hidden" id="atchFileIds" name="atchFileIds" value="${list.atchFileId}">
                                            <label for="atchSeCode<c:out value='${list.atchFileId}'/>" class="disp_none">첨부파일 첨부구분</label>
                                            <select id="atchSeCode<c:out value='${list.atchFileId}'/>" name="atchSeCode<c:out value='${list.atchFileId}'/>" title="첨부파일 첨부구분" disabled="disabled">
                                              <c:forEach var="BO014list" items="${BO014}">
                                                <option value="<c:out value='${BO014list.code}'/>" <c:if test="${list.atchSeCode == BO014list.code}">selected</c:if>><c:out value='${BO014list.codeNm}'/></option>
                                              </c:forEach>
                                            </select>
                                            <!-- 배포경로 -->
                                            <strong>상세경로:</strong>
                                            <label for="wdtbPath<c:out value='${list.atchFileId}' />" class="disp_none">첨부파일 상세경로</label>
                                            <input type="text" id="wdtbPath<c:out value='${list.atchFileId}' />" name="wdtbPath<c:out value='${list.atchFileId}' />" size="50" maxlength="100" style="ime-mode:disabled;" value="<c:out value='${list.wdtbPath}' />" <c:if test="${batchWdtb.wdtbAt == 'Y'}">readonly="readonly"</c:if>>
                                            <!-- 파일정보 -->
                                          <c:forEach var="detailList" items="${list.atchFileDetailList}">
                                            <br><c:out value="${detailList.orignlFileNm}" />&nbsp;(<c:out value="${detailList.fileMg}" />&nbsp;byte)&nbsp;<c:out value="${list.errorMsg}" />
                                          </c:forEach>
                                        </td>
                                    </tr>
                                  </c:if>
                                  </c:forEach>
                                  <c:if test='${empty atchFileList}'>
                                    <tr>
                                        <td>첨부파일이 없습니다</td>
                                    </tr>
                                  </c:if>
                                <!-- 첨부파일 End -->
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th>처리구분</th>
                        <td>
                            <input type="hidden" id="processSeCode" name="processSeCode" title="처리구분">
                            <c:out value="${batchWdtb.processSeCodeNm}" />
                        </td>
                    </tr>
                    <tr>
                        <th>배포여부</th>
					    <td>
						    <input type="hidden" id="wdtbAt" name="wdtbAt" title="배포여부">
						    <c:out value="${batchWdtb.wdtbAtNm}" />
					    </td>
				    </tr>
				</tbody>
			</table>
			<br>
		</div>
		<!-- Contents End -->
		
	</div>
</form>