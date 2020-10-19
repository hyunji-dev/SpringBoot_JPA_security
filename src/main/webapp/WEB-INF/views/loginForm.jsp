<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	2020.10.19-4
	선행: index.jsp
	후행: User.java
	
	로그인만 스프링시큐리티가 관리해줌
 -->
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>loginForm 페이지입니다.</h1>
		<hr />
		<!-- 주의사항 3가지 (로그인만 스프링시큐리티가 관리해줌)
			1.username, password 키 값은 변경 불가능 
			2.method: post방식으로만 전송
			3.MIME타입; x-www-form-urlEncoded만 가능  
		-->
		<form action="/loginProc" method="post">
			<input type="text" name="username" /> 
			<input type="password" name="password" />
			<button>로그인</button>
		</form>
	</body>
</html>