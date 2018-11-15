/*
 * 노드 , 트리 구성 정보 선언
 */
var treeNodes			= new Array();
var openTreeNodes	    = new Array();
var treeIcons			= new Array(6);
var imagePath         = "/mgt/images/egovframework/mgt/cmm/utl/";

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
function createTree(arrName ) {
   var startNode, openNode;
	treeNodes = arrName;
	if (treeNodes.length > 0) {
		preloadIcons();
		if (startNode == null) startNode = 0;
		if (openNode != 0 || openNode != null) setOpenTreeNodes(openNode);
		if (startNode !=0) {
			var treeValues = treeNodes[getArrayId(startNode)].split("|");
		} else {
			document.write("<label for='checkAll'  class='hidden'>전체선택</label>");
			document.write("<input type='checkbox' name='checkAll' id='checkAll' class='check2' onclick='javascript:fCheckAll();'>메뉴목록<br>");
		}
		var recursedNodes = new Array();
		addTreeNode(startNode, recursedNodes);
	}
}
/*
* 노드위치 확인
*/
function getArrayId(node) {
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
//	강제적으로 오픈 상태 연출-생성메뉴
	return true;
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
		if (treeValues[1] == parentNode) lastChild = treeValues[0];
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
			for (g=0; g<recursedNodes.length; g++) {
				document.write("&nbsp;&nbsp;&nbsp;");
			}

			if (lastSibling) recursedNodes.push(0);
			else recursedNodes.push(1);
			document.write("&nbsp;&nbsp;&nbsp;");
			document.write("<label for='"+i+"'  class='hidden'>트리노드"+i+"선택</label>");
			document.write("<input type='checkbox' id='"+i+"' name='checkField' class='check2' ");
			if(treeValues[4] == 1){ document.write(" checked "); }
			document.write("onclick='javascript:fCheckDir(this.name, this.value,"+i+");' value=" + treeValues[0] + ">");
			if (hasChildNode) {
				document.write("<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_folder")
					if (ino) document.write("open");
				document.write(".gif' border='0' alt='Folder' >");
			} else document.write("<img id='icon" + treeValues[0] + "' src='"+imagePath+"m_page.gif' border='0' align='absbottom' alt='Page'>");
			document.write("<a href=javascript:parent.temp_aa('" + treeNodes[i] + "');>");
			document.write(treeValues[2]);
			document.write("</a><br>");
			if (hasChildNode) {
				document.write("<div id='div" + treeValues[0] + "'");
					if (!ino) document.write(" style='display: none;'");
				document.write(">");
				addTreeNode(treeValues[0], recursedNodes);
				document.write("</div>");
			}
			recursedNodes.pop();
		}
	}
}
if(!Array.prototype.push) {
	function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
	Array.prototype.push = array_push;
}
if(!Array.prototype.pop) {
	function array_pop(){
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
	Array.prototype.pop = array_pop;
}
 
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.menuCreatManageForm.checkField;
    if(document.menuCreatManageForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckDir(fCheckYB, fValue, fPath){
	var checkField = document.getElementsByName(fCheckYB);
}

 /* ********************************************************
  * 메뉴 목록 출력(생성 정보 포함)
  ******************************************************** */
function writeList(){
	var chk_Object = true;
    var chk_browse = "";
    if (eval(document.menuCreatManageForm.authorCode)=="[object]") chk_browse = "IE";
    if (eval(document.menuCreatManageForm.authorCode)=="[object NodeList]") chk_browse = "Fox";
    if (eval(document.menuCreatManageForm.authorCode)=="[object Collection]") chk_browse = "safai";

    var Tree = new Array;
    if(chk_browse=="IE"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object]"){
       alert("메뉴 목록 데이타가 존재하지 않습니다.");
       chk_Object = false;
    }
    if(chk_browse=="Fox"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object NodeList]"){
       alert("메뉴 목록 데이타가 존재하지 않습니다.");
       chk_Object = false;
    }
    if(chk_browse=="safai"&&eval(document.menuCreatManageForm.tmp_menuNmVal)!="[object Collection]"){
           alert("메뉴 목록 데이타가 존재하지 않습니다.");
           chk_Object = false;
    }
    if( chk_Object ){
        for (var j = 0; j < document.menuCreatManageForm.tmp_menuNmVal.length; j++) {
            Tree[j] = document.menuCreatManageForm.tmp_menuNmVal[j].value;
        }
        createTree(Tree);
    }else{
        alert("메뉴가 존재하지 않습니다. 메뉴 등록 후 사용하세요.");
        window.close();
    }
}
