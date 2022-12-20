<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<input type="hidden" name="id" id="id"  value="${principal.user.id}">
		<div class="form-group">
			<label for="usernmae">username:</label>
			<input type="text" class="form-control" readonly="readonly" 
			placeholder="Enter usernmae" id="username"  name="username" value="${principal.user.username}">
		</div>
		
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">password:</label>
				<input type="password" class="form-control" placeholder="Enter password" id="password" value="">
			</div>
		</c:if>
		
		<div class="form-group">
			<label for="email">email:</label>
			<input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
		</div>
	</form>
	<button type="button" id="btn--update" class="btn btn-primary">회원정보수정완료</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>

