package com.tencoding.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 *  시큐리티가 username, password 를 가로채서 처리하는데 
	 *  여기서는 username 을 확인 해 주어야 한다. 
	 */
	
	// DB 접근해서 사용자 username 있는지 없는지 여부를 확인해 주면 된다. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		System.out.println(" principal : " + principal.toString());
		return new PrincipalDetail(principal);
	}

}
