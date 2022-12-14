<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- /auth/loginProc <--- 이녀셕은 스프링이 알아서 처리 해줌 마법..아니라
.loginProcessingUrl("/auth/loginProc") 설정 되어 있음 
   -->
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">username:</label>
			<input type="text" class="form-control" placeholder="Enter usernmae" id="username" name="username" value="teco">
		</div>
		<div class="form-group">
			<label for="password">password:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password"  name="password" value="asd123">
		</div>
		<button type="submit"  id="btn--login" class="btn btn-primary">signIn</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
