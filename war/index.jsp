<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/func.js"></script>

</head>
<body>
	<form id="form1">
		<h1>Tweets Search</h1>
		Enter keyword: 
		<input type="text" id="keyword" /> 
		<input type="button" id="search" value="search" /> 
		<br/>
		<div id="results"></div>
	</form>
</body>
</html>