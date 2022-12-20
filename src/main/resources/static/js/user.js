
let index =  {
	init : function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		
		$("#btn--login").bind("click", () => {
			this.login();
		});
		
		$("#btn--update").bind("click", () => {
			this.update();	
		});
		
	}, 
	
	save: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		console.log("token 확인 : " + token);
		console.log("csrfHeader 확인 : " + csrfHeader);
		 
		let data = {
			username: $("#username").val(), 
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader , token)
			},
			type: "POST", 
			url: "/auth/joinProc", 
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
	update: function() {
		
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		let data = {
			id : $("#id").val(), 
			password : $("#password").val(), 
			email : $("#email").val(), 
			username : $("#username").val()
		};
		
		// 방어적 코드 ....
		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader , token)
			},
			type: "PUT", 
			url : "/api/user", 
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8", 
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			if(data.status == "OK") {
				alert("회원 정보 수정을 완료 하였습니다");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("회원 정보 수정에 실패 하였습니다")
		} );
	} 
}

index.init(); 
