<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 – Classic editor</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/11.1.1/classic/ckeditor.js"></script>
    <script src="https://cdn.ckeditor.com/ckeditor5/11.1.1/classic/translations/ko.js"></script>
</head>
<body>
    <h1>Classic editor</h1>
    <textarea name="content" id="editor">
        &lt;p&gt;This is some sample content.&lt;/p&gt;
    </textarea>
    <script>
        ClassicEditor
            .create( document.querySelector( '#editor' ), {
                language: 'ko'
            } )
            .catch( error => {
                console.error( error );
            } );
    </script>
</body>
</html>