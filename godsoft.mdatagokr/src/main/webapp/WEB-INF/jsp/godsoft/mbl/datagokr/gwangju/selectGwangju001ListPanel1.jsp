<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- panel widget-->

<div data-role="panel" id="panel1">

	<ul class="ui-listview ui-group-theme-d" data-role="listview"
		data-theme="d">
		<li class="ui-first-child" style="height: 2.8em;" data-theme="z"
			data-icon="delete"><a
			class="ui-btn ui-btn-z ui-btn-icon-right ui-icon-delete"
			style="color: rgb(255, 255, 255);" href="#" data-rel="close">Close
				menu</a></li>
		<li><a class="ui-btn ui-btn-icon-right ui-icon-carat-r"
			href="<c:url value="/selectGwangju001List.mdo" />" data-ajax="false">의료기관
				현황(2015년)</a></li>
		<li><a class="ui-btn ui-btn-icon-right ui-icon-carat-r"
			href="<c:url value="/selectGwangju002List.mdo" />" data-ajax="false">정신보건시설
				현황(2015년)</a></li>
		<li><a class="ui-btn ui-btn-icon-right ui-icon-carat-r"
			href="<c:url value="/selectGwangju003List.mdo" />" data-ajax="false">의약단체
				현황(2015년)</a></li>
		<li><a class="ui-btn ui-btn-icon-right ui-icon-carat-r"
			href="<c:url value="/selectGwangju004List.mdo" />" data-ajax="false">산후조리원
				현황(2014년)</a></li>
	</ul>
</div>
