package com.cos.security.web;
/*
 * 2020.10.19-2
 * 선행: SecurityConfig.java
 * 후행: index.jsp(3) / loginForm.jsp(4)
 * 
 * 회원가입 시 규칙
 * 		BCryptPasswordEncoder로 해쉬로 암호화한 비밀번호를 DB에 추가해야함 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security.config.auth.PrincipalDetails;
import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"", "/"}) // 이 주소로는 누구나 접근 가능
	public String index() {
		return "index";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	// 회원가입
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	// 던지는 값: username, password, email
	// json이 아니니까 @RequestBody로 받지 않아도 됨 
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		
		// 비밀번호를 암호화 방식으로 DB에 저장  
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole("ROLE_USER"); // 디폴트로 들어감 
		
		//UserRepository만들어서 save()
		userRepository.save(user);
		return "index";
	}

	// 세션만 있으면 출입가능(권한체크)
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails principal) { // @AuthenticationPrincipal: 세션에 접근하는 방법 
		return "user 페이지: " + principal.getUser().getEmail();
	}
	
	// 세션만 있으면 출입가능(권한체크) 
	@GetMapping("/manager")
	@ResponseBody
	public String manager(@AuthenticationPrincipal PrincipalDetails principal) { // @AuthenticationPrincipal: 세션에 접근하는 방법 
		return "manager 페이지: " + principal.getUser().getEmail();
	}
	
	// 세션만 있으면 출입가능(권한체크)
	@GetMapping("/admin")
	@ResponseBody
	public String admin(@AuthenticationPrincipal PrincipalDetails principal) { // @AuthenticationPrincipal: 세션에 접근하는 방법 
		return "admin 페이지: " + principal.getUser().getEmail();
	}
}
