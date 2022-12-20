<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="usernmae">username:</label>
			<input type="text" class="form-control" placeholder="Enter usernmae" id="username" value="teco">
		</div>
		<div class="form-group">
			<label for="password">password:</label>
			 <input type="password" class="form-control" placeholder="Enter password" id="password" value="asd123">
		</div>
		<div class="form-group">
			<label for="email">email:</label>
			 <input type="email" class="form-control" placeholder="Enter email" id="email" value="abc@naver.com">
		</div>
	</form>
		<button type="button"  id="btn--save" class="btn btn-primary">signUp</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
