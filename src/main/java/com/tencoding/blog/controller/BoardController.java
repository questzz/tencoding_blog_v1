package com.tencoding.blog.controller;

import java.util.ArrayList;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	// ?page=2 
	@GetMapping({"", "/", "/board/search"})
	public String index(@RequestParam(required = false) String q, Model model, 
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
		// 검색 요청 값을 받아서 처리 
		String searchTitle = q == null ? "" : q;
		System.out.println("SearchTitle : " + searchTitle);
		//Page<Board> boards = boardService.getBoardList(pageable);
		Page<Board> boards = boardService.searchBoard(searchTitle, pageable);
		 
		int PAGENATION_BLOCK_COUNT = 3;
		// 1. 현재 페이지 앞 뒤로 2칸씩 보이기
		// 2. 현재 페이지 active 처리 하기 
		// 3. 페이지 숫자를 눌렀을 경우 해당 페이지로 화면 이동 하기 
		// 마지막에 보여야 하는 페이지를 잘 확인하자 !!! 
		System.out.println(">>>> 화면에 보여줄 게시글에 갯수이다.  " + boards.getSize());
		System.out.println(">>> 전체 페이지 크기 이다.  " + boards.getTotalPages());
		System.out.println(">>>> 현재 페이이 번호 " + boards.getPageable().getPageNumber());
		//System.out.println(boards.getTotalPages());
		int nowPage = boards.getPageable().getPageNumber() + 1;
		int startPageNumber = Math.max(nowPage - PAGENATION_BLOCK_COUNT, 1);
		int endPageNumber =  Math.min(nowPage + PAGENATION_BLOCK_COUNT,  boards.getTotalPages()); 
		System.out.println("시작해야 하는 번호 : " + startPageNumber);
		System.out.println("마지막에 보여할 번호 : " + endPageNumber);
		// 블럭 숫자에는 - 값을 보여 주면 안된다. 
		// 직접 숫자 길이를 배열로 만들어서 내려 주자 
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for(int i = startPageNumber; i <= endPageNumber; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("boards", boards); 
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPageNumber);
		model.addAttribute("endPage", endPageNumber);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("q", searchTitle);
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










