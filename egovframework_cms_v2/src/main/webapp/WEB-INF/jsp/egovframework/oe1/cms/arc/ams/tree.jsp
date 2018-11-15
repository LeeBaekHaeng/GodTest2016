<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
 <meta http-equiv="content-type" content="text/html; charset=euc-kr">
 <meta http-equiv="Content-language" content="ko">
<title></title>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
 <script type="text/javascript">

  // li������ ul�� �ִ� element Ŭ����  �̺�Ʈ ����
  $(function(){
   $('li:has(ul)')
    .click(function(event){

       // �ش� li �ؿ� ul �ؿ� ���� li�� �ִ��� üũ
       // ������  getSubData�� ȣ���ؼ� ajax�� ������ ��������
       if($(this).children("ul").children("li").size() <=0){

        getSubData($(this)); // ���� ��ü�� ������ ���߿� �� ��ü ������ �߰��� �ؾ���
        return;
       }

       // �ؿ� ul �ؿ� ���� li�� �����ϸ�

       // ���迩�ο� ���� ���̰� �Ⱥ��̰� ó��
       if ($(this).children().is(':hidden')) {

        $(this)
         .css('list-style-image','url(minus.gif)') // ��Ÿ�Ϻ���(���� �̹����� ����)
         .children().slideDown(); // ȿ���� �༭ ���̱�
       }
       else {

        $(this)
         .css('list-style-image','url(plus.gif)') // ��Ÿ�Ϻ���(���� �̹����� ����)
         .children().slideUp();  // ȿ���� �༭ �����
       }
     })
    .css({cursor:'pointer','list-style-image':'url(plus.gif)'}) // ��Ÿ�Ϻ���(���� �̹����� ����)
    .children().hide(); // ������ �����ϱ����� ������ ����ó��

   // li�ؿ� ul�� ���°�� ��Ÿ�� ����
   $('li:not(:has(ul))')
    .css({cursor: 'default','list-style-image':'none'});
  });


  // li������ ul�� ���������� �� �ؿ� li�� �������� ajax�� ��������
  function getSubData($obj){

   jQuery.ajax({
    type :'get'
    , asyn : true
    , url:'http://192.168.100.62:8080/oe1/cms/arc/ams/getObjectTree.do?upperObjectId=COMP-000000000000000&objectType=COMPONENT'
    , data : ""
    , dataType:"json"
    , contentType:"application/x-www-form-urlencoded;charset=UTF-8"
    , success:function(jsonData){

     setSubTree($obj,jsonData);

     }
    , error:function(xhr,textStatus){
      alert(textStatus+"/"+xhr.status);
     }
    , complete:function(xhr,textStatus){

     }
   });
  }

  // ajax�� ���ؼ� ������ json �����͸� �ش��ϴ� li ��ü �ؿ� �߰��� ����
  function setSubTree($obj,jsonData){

   $(jsonData).each(function(){

    // ������ �� a �� li��ü�� ����
    $a = $("<a></a>")
      .text(this.name)
      .attr("href",this.url);
    $li = $("<li></li>");
    $li.append($a);

    //���� li��ü�� �߰�����
    $obj.children("ul").append($li);

   });

   // ���������� �ٽ� Ŭ���Ȱ�ó�� �̺�Ʈ�� �����ؼ� ������ ���̵���~
   $obj.trigger("click");
  }



// ������ �κ�


  $(document).ready(function(){
		$(".tree li span").click(
			function(){
				var clickedNode = $(this).parent();
				var nodeValue = clickedNode.attr("val");

				alert("��� Ŭ���� val : " + nodeValue);

				$.ajax(
					{
						dataType:"json",
						url: "http://localhost:8080/oe1/cms/arc/ams/getObjectTreeJSON.do",
						data : {"nodeValue" : nodeValue},

						success: function(data){
							$("#subnode", clickedNode).remove();

							var subNode = $("<ul/>");
							subNode.attr("id", "subnode");

							for( var i=0; i<data.length; i++ ){
								var temp = $("<li/>");
								temp.attr("val", data[i].id);
								temp.html(data[i].name);
								
								subNode.append(temp);
							}

							clickedNode.append(subNode);
						},

						error: function(m1, m2, m3){
					        alert("ȣ��� ��������! : " + m1 + " , " + m2 + " , " + m3);
					       }
					}
				);
			}
		);
	});


 </script>
 
<style>
	.tree li span{ cursor:hand; cursor:pointer; }
</style>

 <style>
  fieldset { width: 320px }
 </style>
</head>
<body>

	<ul class="tree">
		<li val="1"><span>���1</span></li>
		<li val="2"><span>���2</span></li>
		<li val="3"><span>���3</span></li>
		<li val="4"><span>���4</span></li>
	</ul>

<fieldset>
<legend>Ʈ��</legend>

<ul>
 <li class="globalNav">(��)Į����ũ
  <ul id="menu01_sub" class="localNav">
   <li><a href="#">ȸ�翬��</a></li>
   <li><a href="#">������������</a></li>
   <li><a href="#">��������Ʈ����</a></li>

    <li>����
     <ul>
     <li><a href="#">���� ����</a></li>
     <li><a href="#">��� ����</a></li>
     <li><a href="#">�����</a></li>
     </ul>
    </li>

   <li><a href="#">����(�̹���)�ø���</a></li>
   <li><a href="#">����(�̹���)��ȸ</a></li>
   <li><a href="#">�̸��� ������</a></li>
  </ul>
 </li>
 <li class="globalNav">���α׷���
  <ul id="menu02_sub" class="localNav">
   <li><a href="#">���α׷������� �Խ���</a></li>
   <li><a href="#">HTML �±� ���� �����</a></li>
   <li><a href="#">�ҽ� Finder ���</a></li>
   <li><a href="#">���ںٿ��� �ҽ������</a></li>
   <li><a href="#">���̺���� �ҽ� ����</a></li>
   <li><a href="#">�ҽ�ToHtml</a></li>
   <li><a href="#">���ڿ��ٲٱ�</a></li>
   <li><a href="#">Ư���ܾ ����ִ� ���λ̾Ƴ���</a></li>
   <li><a href="#">���ڷ� �߶󳻱�</a></li>
   <li><a href="#">���ڿ�ġȯ</a></li>
   <li><a href="#">�ʵ� Replace</a></li>
   <li><a href="#">RegEx�� ���ڿ��̾Ƴ���</a></li>
   <li><a href="#">���̺� CrtTbl</a></li>
   <li><a href="#">��ĮPC�������α׷�</a></li>
   <li><a href="#">ǥ���ڵ��Է�</a></li>
   <li><a href="#">ǥ���ڵ���ȸ</a></li>
  </ul>
 </li>
 <li class="globalNav">������
  <ul id="menu03_sub" class="localNav">
   <li><a href="#">����ǥ</a></li>
   <li><a href="#">�۾��� ��漱��</a></li>
   <li><a href="#">�ѱ�Ư������ǥ</a></li>
   <li><a href="#">HTMLƯ������ǥ</a></li>
   <li><a href="#">ASCII���̺�</a></li>
   <li><a href="#">Oracle SQL</a></li>
   <li><a href="#">Ms SQL</a></li>
   <li><a href="#">�ڹٽ�ũ��Ʈ�� PHP�Լ���</a></li>
   <li><a href="#">�޸�</a></li>
  </ul>
 </li>   <li class="globalNav">������ ����Ʈ
  <ul id="menu04_sub" class="localNav">
   <li><a href="#">������ �����ѻ���Ʈ���(����: ���̻����)</a></li>
   <li><a href="#">���α׷� ���߿� �����ѻ���Ʈ���(����: ���α׷��ӿ�)</a></li>
   <li><a href="#">���α׷� ���߿� �����ѻ���Ʈ ����Ʈ����</a></li>
  </ul>
 </li>
 <li class="globalNav">��� / ȸ��
  <ul id="menu05_sub" class="localNav">
   <li><a href="#">���</a></li>
   <li><a href="#">ȸ��</a></li>

  </ul>
 </li>
 <li class="globalNav">AjaxData ��1
  <ul>
  </ul>
 </li>
 <li class="globalNav">AjaxData ��2
  <ul>
  </ul>
 </li>
 <li class="globalNav">�ƹ� �۵�����(������ul�� ���ݾ�)
 </li>
</ul>

</fieldset>
</body>
</html>
