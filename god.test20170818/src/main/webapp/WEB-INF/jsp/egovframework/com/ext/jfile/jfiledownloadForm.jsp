<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.egovframe.go.kr/tags/ext/jfile/jsp" prefix="jwork"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<input type="hidden" id="totalFileSize" value="0" />
<div id="swfupload-control" class="swfupload-control">

	<div class="middle">
		<div class="title">
			<div class="title_left">*첨부파일</div>
			<div class="title_right">
				파일총 사이즈 : <span id="totalFileSizeDisplay">0 Byte</span>
				<span id="spanButtonAllDown"  class="allDown"><button onclick="return false;"/></span>
			</div>
		</div>
		
		<div class="content">
			<div class="content_left">
				<span id="spanThumbnail" class="thumbnail"><img src="${ctx}/resource/jfile/swfupload/images/preview_img.gif" /></span> 	
			</div>
			<div class="content_right">
				<table id="fileAreaTable"  width="100%" cellpadding="0" cellspacing="0" style="border-collapse: collapse;" class="board_view2">
					<col /><col width="100px"/><col width="100px"/><col width="70px"/>
					<tr>
						<th class="top" style="background-image: url('${ctx}/resource/jfile/swfupload/images/text-bg.gif');background-repeat: repeat-x;">파일명</th>
						<th class="top" style="background-image: url('${ctx}/resource/jfile/swfupload/images/text-bg.gif');background-repeat: repeat-x;">사이즈</th>
						<th class="top" style="background-image: url('${ctx}/resource/jfile/swfupload/images/text-bg.gif');background-repeat: repeat-x;">다운로드 수</th>
						<th class="topend" style="background-image: url('${ctx}/resource/jfile/swfupload/images/text-bg.gif');background-repeat: repeat-x;">다운로드</th>
					</tr>
					<tr id="noDataRow">
						<td class="all_txt" colspan="4">첨부된 파일이 없습니다.</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>