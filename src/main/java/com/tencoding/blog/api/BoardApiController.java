package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, 
			@AuthenticationPrincipal PrincipalDetail detail) { 
		
		// 아직스 통신으로 넘겨 받아서 받은 데이터 콘솔에 뿌려 보기 ... 
		System.out.println(board);
		boardService.write(board, detail.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}
	
	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer> update(@PathVariable int boardId, @RequestBody Board board) {
		int result = boardService.modifyBoard(boardId, board); 
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
}
