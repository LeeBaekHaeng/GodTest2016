<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovImgFileList.jsp
  * @Description : 이미지 파일 조회화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.31  이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.31
  *  @version 1.0
  *  @see
  *
  */
%>
<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<table>
      	
	      <tr>
	      	<td></td>
	      </tr>
	      <tr>
	       <td>
	       		<img src='<c:url value='/com/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&amp;fileSn=<c:out value="${fileVO.fileSn}"/>'  width="100" alt="해당파일이미지"/>
	       </td>
	      </tr>
	      <tr>
	      	<td></td>
	      </tr>
        
      </table>
      </c:forEach>
