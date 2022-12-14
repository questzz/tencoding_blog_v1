
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		
		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});
		
		$("#btn--update").bind("click", () => {
			this.update();
		});
		
	}, 
	save : function() {
		
		let data = {
			title: $("#title").val(), 
			content: $("#content").val()
		};
		
		// ajax 통신 요청 
		$.ajax({
			type:"POST", 
			url: "/api/board", 
			data: JSON.stringify(data),
			contentType : "application/json; charset=utf-8", 
			dataType: "json"
		}).done(function(data, textSatus, xhr) {
			if(data.status == "OK") {
				alert("글쓰기 성공");
				location.href = "/";
			}
		}).fail((error) => {
			console.log(error);
			alert(error.responseJSON.error);
			
		} );
		
	}, 
	deleteById: function () {
		let id = $("#board-id").text();
		// 통신 ---> ajax 
		$.ajax({
			type: "DELETE", 
			url: "/api/board/" + id
		}).done(function(data, textStatus, xhr) {
			if(data.status == "OK") {
				alert("글 삭제가 완료 되었습니다");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 삭제하기에 실패 하였습니다");
		}); 
	}, 
	update: function() {
		// HTML 태그에 직접 속성을 정의할 수 있다. 규칙은 data-* 
		// data-* 값을 가지고 오기 위해서 Jquery --> (선택자).attr("data-[id]")
		let boardId = $("#board-id").attr("data-id");
		
		let data = {
			title : $("#title").val(), 
			content : $("#content").val()
		}
		
		$.ajax({
			type: "PUT", 
			url : "/api/board/" + boardId, 
			data : JSON.stringify(data), 
			contentType: "application/json; charset=utf-8",
			dataType: "json"	
		}).done(function(data, textStatus, xhr) {
			if(data.status) {
				alert("글 수정이 완료 되었습니다");
				location.href = "/";
			}
		}).fail(function(error) {
			console.log(error);
			alert("글 수정에 실패 하였습니다");
		}); 
		
	}
}

index.init(); 