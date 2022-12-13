package com.tencoding.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tencoding.blog.dto.User;

import lombok.Getter;

/**
 * 
 * 시큐리티가 로인 요청을 가로채서 
 * 로그인을 처리하고(DB...) 완료 되면 UserDetails 타입의 
 * 오브젝트를 시큐리티의 고유한 세션 저장소에 저장을 해준다. 
 * (우리가 새롭게 정의한 Object 로 처리할 예정) 
 */

public class PrincipalDetail implements UserDetails {
	
	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user; 
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 계정의 권한을 반환 처리 
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>(); 
		collection.add(() ->  {return "ROLE_" + user.getRole();} );
		return collection;
	}
	
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠김 여부 확인 
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비민번호 만료 여부를 알려 준다. 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	// 계정 활성화 여부 확인 
	@Override
	public boolean isEnabled() {
		return true;
	}
		
}
