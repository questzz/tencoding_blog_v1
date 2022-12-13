package com.tencoding.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;

import com.tencoding.blog.dto.User;

// DAO
// 여기서는 굳이 Bean 으로 등록 요청을 하지 않아도 등록을 시켜 준다. --> JpaRepository
// @Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// SELECT * FROM user WHERE username = ?1;
	Optional<User> findByUsername(String username);
	
}

//없는 함수는 직접 함수를 만들거나 또는 spring JAP 네이밍 전략 

//	// SELECT * FROM user where username = ?1 and password = ?2
//	// SELECT * FROM user where username = 'teco' and password = 'asd1234'
//	User findByUsernameAndPassword(String username, String password);
//	
//	
//	// 두번째 방법 네이티브 쿼리 만들기  
//	@Query(value = " SELECT * "
//			+ " FROM user "
//			+ " where username = ?1 "
//			+ " and password = ?2 ", nativeQuery = true)
//	User login(String username, String password);
