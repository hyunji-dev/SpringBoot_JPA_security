package com.cos.security.web;
/*
 * 2020.10.19-2
 * 선행: SecurityConfig.java
 * 후행: index.jsp(3) / loginForm.jsp(4)
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping({"", "/"})
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
		user.setRole("ROLE_USER"); // 디폴트로 들어감 
		
		//UserRepository만들어서 save()
		userRepository.save(user);
		return "index";
	}

}
