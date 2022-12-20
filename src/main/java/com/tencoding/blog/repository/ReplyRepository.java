package com.tencoding.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tencoding.blog.dto.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
}
