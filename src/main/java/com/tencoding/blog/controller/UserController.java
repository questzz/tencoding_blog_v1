package com.tencoding.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.tencoding.blog.dto.KakaoAccount;
import com.tencoding.blog.dto.KakaoProfile;
import com.tencoding.blog.dto.OAuthToken;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@Controller
public class UserController {
	
	// yml 초기 파라미터를 가져 올 수 있다. 
	@Value("${tenco.key}")
	private String tencoKey;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}

	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	@GetMapping("/user/update_form")
	public String updateForm() {

		return "user/update_form";
	}


	@GetMapping("/auth/kakao/callback")
	// @ResponseBody //  페이지에서 데이터를 리턴하는 방법 data 를 리턴 함
	public String kakaoCallback(@RequestParam String code) {
		// 여기서 카카오 서버에서 보내 준 code 값을 받을 수 있다.
		// 다음 단계은 토큰 발급 받기

		RestTemplate rt = new RestTemplate();
		// 헤더 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");

		// 바디 만들기
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "c4a84061a74906faf8a35a45c1e12d5b");
		params.add("redirect_uri", "http://localhost:9090/auth/kakao/callback");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> requestKakaoToken
			= new HttpEntity<>(params, headers);

		// 헤더 변조 해서 실행 시키는 메서드 RestTemplate exchange() 이다.
		ResponseEntity<OAuthToken> response = 
				rt.exchange("https://kauth.kakao.com/oauth/token",
						HttpMethod.POST,
						requestKakaoToken,
						OAuthToken.class);
		
		OAuthToken authToken = response.getBody(); 
		
		///////////////////  여기까지 토큰 받기 완료 /////////////////////
		// 다시 한번 더 kapi.kakao.com 로 토큰을 가지고 요청하여 사용자 정보를 응답 받아야 한다.
		RestTemplate rt2 = new RestTemplate();
		
		// 헤더 만들기 2 
		HttpHeaders headers2 = new HttpHeaders();
		// JWT - 주의 Bearer 다음에 무조건 한 칸 띄우기 
		headers2.add("Authorization", "Bearer " + authToken.accessToken);
		headers2.add("Content-Type", "application/x-www-form-urlencoded; ");
		
		// 바디 
		HttpEntity<MultiValueMap<String, String>> kakaoDataRequest = 
				new HttpEntity<>(headers2);
		
		// 파싱 받을 DTO 만들어야 함 
		ResponseEntity<KakaoProfile> kakaoDataReponse = rt2.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, kakaoDataRequest, KakaoProfile.class);
		
		// 우리 blog 서버에 로그인 처리해서 세션 처리 .... 진행을 해야 한다.
		
		// 1. User object 만들기 
		// 2 . 회원 가입 -- 자동으로 --> 우리 DB 넣을 예정 
		// 3.  소셜 로그인 사용자게 우리 사이트에 이미 회원 가입된 사용자라면 DB 저장 x 
		KakaoAccount account = kakaoDataReponse.getBody().kakaoAccount;
		
		// 우리 사이트 회원가입 시 필요한 사항 - username, email ,password 
		// insert2.eat_"a"
		User kakaoUser = User
				.builder()
				.username(account.profile.nickname + "_" + kakaoDataReponse.getBody().id) 
		        .email(account.email)
		        .password(tencoKey)
		        .oauth("kakao")
		        .build();
		
		// 여기서는 user id 를 당연히 사용 못한다. 그럼 username 으로 검색하는 기능을 만들어 주어야 한다. 
		User originUser = userService.searchUserName(kakaoUser.getUsername()); 
		if(originUser.getUsername() == null) {
			userService.saveUser(kakaoUser); 
		}
		
		// 신규로 회원 가입 이든 , 기존에 한번 가입 했던 유저이든 무조건 소셜 로그인 이면 세션 생성해 주어야 한다. 
		// 자동 로그인 처리 --> 시큐리티 세션에다가 강제 저장 (7	분) 
		Authentication authentication = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),
						tencoKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}

	//security에 맡기지 않고 직접 로그 아웃 처리 
	@GetMapping("/m-logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(req, res, authentication);
		}
		return "redirect:/";
	}
	
}
