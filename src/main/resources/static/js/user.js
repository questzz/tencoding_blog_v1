
let index =  {
	init : function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		
		$("#btn--login").bind("click", () => {
			this.login();
		});
	}, 
	
	save: function() {
		// form 태그 사용자가 입력한 값을 가지고 오기 --> 자바스크립트 변수로 
		let data = {
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
		}
		// console.log(data);
		// todo -> ajax 로 통신 ( data -> json 변환 자바 서버로 전송 )
		
		// ajax 통신 구현 
		//$.ajax().done().fail();
		$.ajax({
			// 회원 가입 요청 
			type: "POST", 
			url: "/api/user", 
			data: JSON.stringify(data), // http 메세지 body 영역에 들어감
			contentType: "application/json; charset=utf-8", // 보낼때 데이터 타입 
			dataType: "json"  // 응답이 왔을 때 MIME TYPE 지정  JSON -->  javascript Object 자동 변환
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			// data <-- object 
			if(data.status == "OK") {
				alert("회원가입 완료");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("회원가입 실패 : " + error.responseJSON.message);
		});
	}, 
	
	login: function() {
		
		let data = {
			username: $("#username").val(), 
			password: $("#password").val()
		};
		
		$.ajax({
			type: "POST", 
			url: "/api/user/login",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json"	
		}).done(function(data, textStatus, xhr) {
			alert("로그인 성공");
			console.log(data);
			location.href = "/";
		}).fail(function(error) {
			alert("로그인 실패");
		});
	}
}

index.init(); 
