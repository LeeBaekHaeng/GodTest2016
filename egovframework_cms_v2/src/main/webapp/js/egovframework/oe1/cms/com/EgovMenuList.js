/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
var imagePath         = "/mgt/images/egovframework/mgt/cmm/utl/";
var treeYeobu       = false;

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
function createTree(arrName, vYeobu) {
   var startNode, openNode;
	treeNodes = arrName;
	treeYeobu = vYeobu;
	if (treeNodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var treeValues = treeNodes[getTreeArrayId(startNode)].split("|");
			document.write("<a href='" + treeValues[3] + "' onmouseover='window.status='" + treeValues[3] + "';return true;' onmouseout='window.status=' ';return true;'><img src='"+imagePath+"m_folderopen.gif' border='0' align='absbottom' alt=''>" + treeValues[2] + "</a><br>");
		} else document.write("<img src='"+imagePath+"m_base.gif' border='0' align='absbottom' alt='' >root<br>");
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
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
			var isNodeOpen = isTreeNodeOpen(treeValues[0]);

			for (g=0; g<recursedNodes.length; g++) {
				if (recursedNodes[g] == 1) document.write("<img src='"+imagePath+"m_line.gif' border='0' align='absbottom' alt='' >");
				else  document.write("<img src='"+imagePath+"m_empty.gif' border='0' align='absbottom' alt='' >");
			}
			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);
			if (hasChildNode) {
				if (lastSibling) {
					document.write("<a href='javascript: openCloseEx(" + treeValues[0] + ", 1);'><img id='join" + treeValues[0] + "' src='"+imagePath);
					 	if (isNodeOpen) document.write("m_minus");
						else document.write("m_plus");
					document.write("bottom.gif' border='0' align='absbottom' alt='Open/Close node' ></a>");
				} else { 
					document.write("<a href='javascript: openCloseEx(" + treeValues[0] + ", 0);'><img id='join" + treeValues[0] + "' src='"+imagePath);
						if (isNodeOpen) document.write("m_minus");
						else document.write("m_plus");
					document.write(".gif' border='0' align='absbottom' alt='Open/Close node' /></a>");
				}
			} else {
				if (lastSibling) document.write("<img src='"+imagePath+"m_joinbottom.gif' border='0' align='absbottom' alt='' >");
				else document.write("<img src='"+imagePath+"m_join.gif' border='0' align='absbottom' alt='' >");
			}
			document.write("<a href=javascript:choiceNodes('" + i + "');>");
			if (hasChildNode) {
				document.write("<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_folder")
					if (isNodeOpen) document.write("open");
				document.write(".gif' border='0' alt='Folder' >");
			} else document.write("<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_page.gif' border='0' align='absbottom' alt='Page'>");
			document.write(treeValues[2]);
			document.write("</a><br>");
			if (hasChildNode) {
				document.write("<div id='div" + treeValues[0] + "'");
					if (!isNodeOpen) document.write(" style='display: none;'");
				document.write(">");
				addTreeNode(treeValues[0], recursedNodes);
				document.write("</div>");
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

function writeList(){
	var chk_Object = true;
    var chk_browse = "";
    if (eval(document.menuListForm.req_RetrunPath)=="[object]") chk_browse = "IE";
    if (eval(document.menuListForm.req_RetrunPath)=="[object NodeList]") chk_browse = "Fox";
    if (eval(document.menuListForm.req_RetrunPath)=="[object Collection]") chk_browse = "safai";

    var Tree = new Array;
    if(chk_browse=="IE"&&eval(document.menuListForm.tmp_menuNmVal)!="[object]"){
       alert("메뉴 목록 데이타가 존재하지 않습니다.");
       chk_Object = false;
    }
    if(chk_browse=="Fox"&&eval(document.menuListForm.tmp_menuNmVal)!="[object NodeList]"){
       alert("메뉴 목록 데이타가 존재하지 않습니다.");
       chk_Object = false;
    }
    if(chk_browse=="safai"&&eval(document.menuListForm.tmp_menuNmVal)!="[object Collection]"){
           alert("메뉴 목록 데이타가 존재하지 않습니다.");
           chk_Object = false;
    }
    if( chk_Object ){
        for (var j = 0; j < document.menuListForm.tmp_menuNmVal.length; j++) {
            Tree[j] = document.menuListForm.tmp_menuNmVal[j].value;
        }
        createTree(Tree);
    }else{
        alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
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

