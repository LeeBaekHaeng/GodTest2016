<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
 <meta http-equiv="content-type" content="text/html; charset=euc-kr">
 <meta http-equiv="Content-language" content="ko">
<title></title>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
 <script type="text/javascript">

  // li하위에 ul이 있는 element 클릭시  이벤트 실행
  $(function(){
   $('li:has(ul)')
    .click(function(event){

       // 해당 li 밑에 ul 밑에 하위 li가 있는지 체크
       // 없으면  getSubData를 호출해서 ajax로 데이터 가져오기
       if($(this).children("ul").children("li").size() <=0){

        getSubData($(this)); // 현재 객체를 전달해 나중에 그 객체 하위에 추가를 해야지
        return;
       }

       // 밑에 ul 밑에 하위 li가 존재하면

       // 숨김여부에 따라 보이고 안보이고 처리
       if ($(this).children().is(':hidden')) {

        $(this)
         .css('list-style-image','url(minus.gif)') // 스타일변경(현재 이미지가 없음)
         .children().slideDown(); // 효과를 줘서 보이기
       }
       else {

        $(this)
         .css('list-style-image','url(plus.gif)') // 스타일변경(현재 이미지가 없음)
         .children().slideUp();  // 효과를 줘서 숨기기
       }
     })
    .css({cursor:'pointer','list-style-image':'url(plus.gif)'}) // 스타일변경(현재 이미지가 없음)
    .children().hide(); // 하위를 시작하기점에 무조건 숨김처리

   // li밑에 ul이 없는경우 스타일 변경
   $('li:not(:has(ul))')
    .css({cursor: 'default','list-style-image':'none'});
  });


  // li하위에 ul은 존재하지만 그 밑에 li가 없을때는 ajax로 가져오자
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

  // ajax를 통해서 가져온 json 데이터를 해당하는 li 객체 밑에 추가를 하자
  function setSubTree($obj,jsonData){

   $(jsonData).each(function(){

    // 하위에 들어갈 a 및 li객체를 만들어서
    $a = $("<a></a>")
      .text(this.name)
      .attr("href",this.url);
    $li = $("<li></li>");
    $li.append($a);

    //상위 li객체에 추가하자
    $obj.children("ul").append($li);

   });

   // 마지막으로 다시 클릭된것처럼 이벤트를 실행해서 하위를 보이도록~
   $obj.trigger("click");
  }



// 영맹이 부분


  $(document).ready(function(){
		$(".tree li span").click(
			function(){
				var clickedNode = $(this).parent();
				var nodeValue = clickedNode.attr("val");

				alert("노드 클릭됨 val : " + nodeValue);

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
					        alert("호출시 에러났음! : " + m1 + " , " + m2 + " , " + m3);
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
		<li val="1"><span>노드1</span></li>
		<li val="2"><span>노드2</span></li>
		<li val="3"><span>노드3</span></li>
		<li val="4"><span>노드4</span></li>
	</ul>

<fieldset>
<legend>트리</legend>

<ul>
 <li class="globalNav">(주)칼파테크
  <ul id="menu01_sub" class="localNav">
   <li><a href="#">회사연혁</a></li>
   <li><a href="#">개발일지쓰기</a></li>
   <li><a href="#">일지리스트보기</a></li>

    <li>관리
     <ul>
     <li><a href="#">도서 관리</a></li>
     <li><a href="#">장비 관리</a></li>
     <li><a href="#">차계부</a></li>
     </ul>
    </li>

   <li><a href="#">문서(이미지)올리기</a></li>
   <li><a href="#">문서(이미지)조회</a></li>
   <li><a href="#">이메일 보내기</a></li>
  </ul>
 </li>
 <li class="globalNav">프로그램팁
  <ul id="menu02_sub" class="localNav">
   <li><a href="#">프로그램개발팁 게시판</a></li>
   <li><a href="#">HTML 태그 샘플 만들기</a></li>
   <li><a href="#">소스 Finder 등록</a></li>
   <li><a href="#">숫자붙여서 소스만들기</a></li>
   <li><a href="#">테이블관련 소스 생성</a></li>
   <li><a href="#">소스ToHtml</a></li>
   <li><a href="#">문자열바꾸기</a></li>
   <li><a href="#">특정단어가 들어있는 라인뽑아내기</a></li>
   <li><a href="#">문자로 잘라내기</a></li>
   <li><a href="#">문자열치환</a></li>
   <li><a href="#">필드 Replace</a></li>
   <li><a href="#">RegEx로 문자열뽑아내기</a></li>
   <li><a href="#">테이블 CrtTbl</a></li>
   <li><a href="#">로칼PC응용프로그램</a></li>
   <li><a href="#">표준코드입력</a></li>
   <li><a href="#">표준코드조회</a></li>
  </ul>
 </li>
 <li class="globalNav">웹디팁
  <ul id="menu03_sub" class="localNav">
   <li><a href="#">색상표</a></li>
   <li><a href="#">글씨와 배경선택</a></li>
   <li><a href="#">한글특수문자표</a></li>
   <li><a href="#">HTML특수문자표</a></li>
   <li><a href="#">ASCII테이블</a></li>
   <li><a href="#">Oracle SQL</a></li>
   <li><a href="#">Ms SQL</a></li>
   <li><a href="#">자바스크립트와 PHP함수비교</a></li>
   <li><a href="#">메모</a></li>
  </ul>
 </li>   <li class="globalNav">유용한 사이트
  <ul id="menu04_sub" class="localNav">
   <li><a href="#">업무에 유용한사이트등록(주의: 아이사랑용)</a></li>
   <li><a href="#">프로그램 개발에 유용한사이트등록(주의: 프로그래머용)</a></li>
   <li><a href="#">프로그램 개발에 유용한사이트 리스트보기</a></li>
  </ul>
 </li>
 <li class="globalNav">사람 / 회사
  <ul id="menu05_sub" class="localNav">
   <li><a href="#">사람</a></li>
   <li><a href="#">회사</a></li>

  </ul>
 </li>
 <li class="globalNav">AjaxData 콜1
  <ul>
  </ul>
 </li>
 <li class="globalNav">AjaxData 콜2
  <ul>
  </ul>
 </li>
 <li class="globalNav">아무 작동안함(하위에ul이 없잖아)
 </li>
</ul>

</fieldset>
</body>
</html>
