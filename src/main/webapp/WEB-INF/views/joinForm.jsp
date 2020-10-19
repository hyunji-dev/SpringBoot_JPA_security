<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	2020.10.19-6
	선행: User.java
	후행: UserRepository.java
 -->
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>joinForm 페이지입니다.</h1>
		<hr />
		<!-- 주의사항 3가지 
			1.username, password 키 값은 변경 불가능 
			2.method: post방식으로만 전송
			3.MIME타입; x-www-form-urlEncoded만 가능  
		-->
		<form action="/joinProc" method="post">
			<input type="text" name="username" placeholder="유저네임" /> 
			<input type="password" name="password" placeholder="패스워드" />
			<input type="email" name="email" placeholder="이메일" /> 
			
			<button>회원가입</button>
		</form>
	</body>
</html>