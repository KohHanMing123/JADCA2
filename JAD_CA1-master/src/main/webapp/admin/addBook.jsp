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
	<form action="<%=request.getContextPath()%>/EditBook" method="post" enctype="multipart/form-data">
        <input name="bookID" type="hidden" value="1">
        <input type="submit" value="Upload">
    </form>
</body>
</html>