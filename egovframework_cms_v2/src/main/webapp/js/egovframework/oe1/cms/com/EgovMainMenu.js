/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
var imagePath         = "/mgt/images/egovframework/mgt/cmm/utl/";
var treeYeobu       = false;
var chkValue        = "";
var vHtmlCode       = "";
 
/*
 * 노드 , 트리 구성 이미지 정보
 */
function preloadIcons() {
	treeIcons[0] = new Image();
	treeIcons[0].src = imagePath+"m_plus.gif";
	treeIcons[1] = new Image();
	treeIcons[1].src = imagePath+"m_plusbottom.gif";
	treeIcons[2] = new Image();
	treeIcons[2].src = imagePath+"m_minus.gif";
	treeIcons[3] = new Image();
	treeIcons[3].src = imagePath+"m_minusbottom.gif";
	treeIcons[4] = new Image();
	treeIcons[4].src = imagePath+"m_folder.gif";
	treeIcons[5] = new Image();
	treeIcons[5].src = imagePath+"m_folderopen.gif";
} 
/*
* 트리생성함수
*/
function createTree(arrName, vYeobu, checkValue) {
   var startNode, openNode;
	treeNodes = arrName;
	treeYeobu = vYeobu;
	chkValue = checkValue;//"2000000"
	startNode = chkValue;
	if (treeNodes.length > 0) {
		preloadIcons();
		
		vHtmlCode +="<table width='181' height='94' border='1' align='center' cellpadding='0' cellspacing='0'><tr>";
		vHtmlCode +="<td valign='bottom' background='/mgt/images/egovframework/left_m_top.gif' style='background-repeat:no-repeat'>";
	
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var _getTreeArrayId = getTreeArrayId(startNode)
			var treeValues = treeNodes[getTreeArrayId(startNode)].split("|");
			vHtmlCode +="<div class='LeftMenuTitle'><font color='#ffffff'>" + treeValues[2] + "</font></div></td></tr>"
			vHtmlCode +="<tr>";
		} else vHtmlCode +="<img src='"+imagePath+"m_base.gif' border='0' align='absbottom' alt='' >메뉴목록<br>";
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
		vHtmlCode +="<tr><td height='30' valign='bottom' background='/mgt/images/egovframework/left_menu_btm.gif' style='background-repeat:no-repeat'>&nbsp;</td></tr></table>";
		document.write(vHtmlCode);
	}
}
/*
* 노드위치 확인
*/
function getTreeArrayId(node) {
	for (i=0; i<treeNodes.length; i++) {
		var treeValues = treeNodes[i].split("|");
		if (treeValues[0]==node) return i;
	}
	return 0;
}
/*
* 트리 노드 열기
*/
function setOpenTreeNodes(openNode) {
	for (i=0; i<treeNodes.length; i++) {
		var treeValues = treeNodes[i].split("|");
		if (treeValues[0]==openNode) {
			openTreeNodes.push(treeValues[0]);
			setOpenTreeNodes(treeValues[1]);
		}
	} 
}
/*
* 트리노드 오픈 여부 확인
*/
function isTreeNodeOpen(node) {
   if (treeYeobu){ return true; }
   for (i=0; i<openTreeNodes.length; i++){
	   if (openTreeNodes[i]==node){ return true; }
   }
   return false;
}
/*
* 하위 트리노드 존재여부 확인
*/
function hasChildTreeNode(parentNode) {
	for (i=0; i< treeNodes.length; i++) {
		var treeValues = treeNodes[i].split("|");
		if (treeValues[1] == parentNode) return true;
	}
	return false;
}
/*
* 트리노드 최하위 여부 확인
*/
function lastTreeSibling (node, parentNode) {
	var lastChild = 0;
	for (i=0; i< treeNodes.length; i++) {
		var treeValues = treeNodes[i].split("|");
		if (treeValues[1] == parentNode)
			lastChild = treeValues[0];
	}
	if (lastChild==node) return true;
	return false;
}

/*
* 신규 트리노드 추가
*/
function addTreeNode(parentNode, recursedNodes) {
	for (var i = 0; i < treeNodes.length; i++) {

		var treeValues = treeNodes[i].split("|");
		if (treeValues[1] == parentNode) {
		
			var lastSibling	= lastTreeSibling(treeValues[0], treeValues[1]);
			var hasChildNode	= hasChildTreeNode(treeValues[0]);
			var ino = isTreeNodeOpen(treeValues[0]);
			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);

			if (hasChildNode) {
				if (lastSibling) {
					vHtmlCode +="<tr><td><a href='javascript: openCloseEx(" + treeValues[0] + ", 1);'><img id='join" + treeValues[0] + "' src='"+imagePath;
					 	if (ino) vHtmlCode +="m_minus";
						else vHtmlCode +="m_plus";
					vHtmlCode +="bottom.gif' border='0' align='absbottom' alt='Open/Close node' ></a>";
				} else { 
					vHtmlCode +="<tr><td><a href='javascript: openCloseEx(" + treeValues[0] + ", 0);'><img id='join" + treeValues[0] + "' src='"+imagePath;
						if (ino) vHtmlCode +="m_minus";
						else vHtmlCode +="m_plus";
					vHtmlCode +=".gif' border='0' align='absbottom' alt='Open/Close node' /></a>";
				}
			} else {
				if (lastSibling) vHtmlCode +="<tr><td><img src='"+imagePath+"m_joinbottom.gif' border='0' align='absbottom' alt='' >";
				else vHtmlCode +="<tr><td><img src='"+imagePath+"m_join.gif' border='0' align='absbottom' alt='' >";
			}
			vHtmlCode +="<a href=javascript:fn_MovePage('" + i + "');>";
			if (hasChildNode) {
				vHtmlCode +="<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_folder";
					if (ino) vHtmlCode +="open";
				vHtmlCode +=".gif' border='0' alt='Folder' >"+treeValues[2]+"</a></td></tr>";
			} else{
				vHtmlCode +="<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_page.gif' border='0' align='absbottom' alt='Page'>"+treeValues[2]+"</a></td></tr>";
			}
			if (hasChildNode) {
				vHtmlCode +="<div id='div" + treeValues[0] + "'";
					if (!ino) vHtmlCode +=" style='display: none;'";
				vHtmlCode +="><tr><td>";
				addTreeNode(treeValues[0], recursedNodes);
				vHtmlCode +="</td></tr></div>";
			}
			recursedNodes.pop();
		}
	}
}

/*
* 트리노드 액션(열기,닫기)
*/
function openCloseEx(treeNode, treeBottom) {
	var treeDiv = document.getElementById("div" + treeNode);
	var treeJoin	= document.getElementById("join" + treeNode);
	var treeIcon = document.getElementById("icon" + treeNode);
	
	if (treeDiv.style.display == 'none') {
		if (treeBottom==1) treeJoin.src = treeIcons[3].src;
		else treeJoin.src = treeIcons[2].src;
		treeIcon.src = treeIcons[5].src;
		treeDiv.style.display = '';
	} else {
		if (treeBottom==1) treeJoin.src = treeIcons[1].src;
		else treeJoin.src = treeIcons[0].src;
		treeIcon.src = treeIcons[4].src;
		treeDiv.style.display = 'none';
	}
}
if(!Array.prototype.push) {
	function fnArrayPush() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = fnArrayPush;
}
if(!Array.prototype.pop) {
	function fnArrayPop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = fnArrayPop;
}

/* ********************************************************
* 상세내역조회 함수
******************************************************** */
function fn_MovePage(nodeNum) {
		var treeValues = treeNodes[nodeNum].split("|");
		//parent.main_right.location.href = treeValues[5];
		parent.main_right.location.href = "/mgt"+treeValues[5];
}

