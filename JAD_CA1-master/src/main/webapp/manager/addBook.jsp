<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Book</title>
</head>
<body>
	<p>Add Book Image</p>
	<form action="../UploadBookImage" method="post" enctype="multipart/form-data">
        <input type="file" name="imageFile">
        <input type="submit" value="Upload">
    </form>
</body>
</html>