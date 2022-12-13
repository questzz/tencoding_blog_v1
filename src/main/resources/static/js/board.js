
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
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
		
	}
	
}

index.init(); 