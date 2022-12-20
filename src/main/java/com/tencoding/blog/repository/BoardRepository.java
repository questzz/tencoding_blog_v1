package com.tencoding.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	// native  쿼리 , naming 전략. ... QLRM... ... 
	// select * from board where title like '%q%' ; 
	Page<Board> findByTitleContaining(String q, Pageable pageable);
}





