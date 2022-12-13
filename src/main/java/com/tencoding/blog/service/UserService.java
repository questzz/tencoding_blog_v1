package com.tencoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

// 스피링이 컴포넌트 스캔을 통해서 Bean 으로 등록해 준다 (IoC) 
@Service
public class UserService {
	
	/**
	 *  서비스를 만드는 이유 
	 *  트랜 잭션 관리를 하기 위해서 
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	/**
	 * 작업 단위 
	 * 하나의 기능 + 하나의 기능 들을 묶어서 단위의 기능을 처리 
	 * DB 수정시 롤백 처리도 가능 
	 */
	@Transactional
	public int saveUser(User user) {
		try {
			// 비밀번호를 넣을 때 여기서 암호화 처리 하고 DB 저장 하기 ! 
			String rawPassword = user.getPassword(); 
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; 
	}
	
//	public User login(User user) {
//		// 기본 Repository에 필요한 함수가 없을 경우 직접 생성하면 된다. 
//		//userRepository.get
//		//User userEntity =  userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//		User userEntity =  userRepository.login(user.getUsername(), user.getPassword());
//		System.out.println("userEntity : " + userEntity);
//		return userEntity; 
//	}
//	
	
}



