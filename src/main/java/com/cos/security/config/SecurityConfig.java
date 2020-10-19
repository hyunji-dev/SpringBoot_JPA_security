package com.cos.security.config;
/*
 * 2020.10.19-1
 * 선행: yml
 * 		pom.xml에 jstl, comcat jasper 라이브러리 추가
 * 후행: IndexController.java
 * 
 * jsp 파일의 폼 사용 시 규칙 3가지 
 * 		1.username, password 키 값 맞추기
 * 		2.method: post방식으로만 전송
 * 		3.MIME타입: x-www-form-urlEncoded만 가능  
 * 	
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 시큐리티 설정파일 활성화(기록)
@Configuration// IoC 등록(설정 파일 메모리에 띄움)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 싱글톤 방식으로 암호화
	// BCryptPasswordEncoder 사용이유: 스프링 시큐리티의 디폴트 라서
	@Bean // 메소드의 리턴값을 IoC에 등록하는 어노테이션 
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // form태그 요청만 가능한 것을 비활성화한다.(postman 등으로 요청할 수 있도록) 
		
		// . . . 으로 계속 이어지는 게 빌더패턴
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() // /user 경로로 들어오면 인증이 필요함 // 403에러
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 권한 확인
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 권한 확인
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm") // 미인증시 로그인폼으로 감 
			.loginProcessingUrl("/loginProc") // loginProc 이 주소가 요청이 오면 시큐리티필터가 가로채서 로그인 진행함  
			.defaultSuccessUrl("/")// 로그인 완료 후 어디로 갈 것인지 설정 
			.and()
			.logout()
			.logoutSuccessUrl("/logoutProc")
			; 
	}
}
