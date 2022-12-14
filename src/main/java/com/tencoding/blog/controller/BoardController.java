package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// ?page=2 
	@GetMapping({"", "/"})
	public String index(Model model, 
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.getBoardList(pageable); 
		
		//page.first = true, false <-- 첫번째 페이지 true  
		//page.last = true, false  <--- 마지막 페이지면 true
		
		model.addAttribute("boards", boards); 
		// jsp 파일에서 model 추상객체를 이용해서 컨트롤러에서 내려 준 데이터에 접근이 가능하다.  
		return "index";
	}
	
	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}
	
	@GetMapping("/board/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {
		
		model.addAttribute("board", boardService.boardDetail(boardId));
		
		return "/board/update_form";
	}
	
}










