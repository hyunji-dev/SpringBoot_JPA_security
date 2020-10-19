package com.cos.security.config;
/*
 * 2020.10.19-1
 * 선행: yml
 * 		pom.xml에 jstl, comcat jasper 라이브러리 추가
 * 후행: IndexController.java
 * 
 * jsp 파일의 폼 주의사항 3가지 
 * 		1.username, password 키 값은 변경 불가능 
 * 		2.method: post방식으로만 전송
 * 		3.MIME타입; x-www-form-urlEncoded만 가능  
 * 	
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 시큐리티 설정파일 활성화(기록)
@Configuration// IoC 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // form태그 요청만 가능한 것을 비활성화한다.(postman 등으로 요청할 수 있도록) 
		
		// . . . 으로 계속 이어지는 게 빌더패턴
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() // /user 경로로 들어오면 인증이 필요함 // 403에러
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm")
			.loginProcessingUrl("/loginProc") // loginProc 이 주소가 요청이 오면 시큐리티필터가 가로채서 로그인 진행함  
			; //
	}
}
