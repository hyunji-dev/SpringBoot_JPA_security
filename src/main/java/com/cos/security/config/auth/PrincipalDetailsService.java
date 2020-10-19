package com.cos.security.config.auth;
/*
 * 2020.10.19-8
 * 선행: UserRepository.java
 * 후행: PrincipalDetails.java
 * 
 *  princpal: 접근 주체(인증주체) 
 *  
 *  개발자 대신 스프링이 세션을 관리해줌(모든 인증에 대한 처리를 자동으로 해줌) 
 *  
 *  username, userpassword를 받아서 UserDetailsService 호출되고 loadUserByUsername -> 세션 안에 Authication(UserDetails)
 *  loadUserByUsername를 호출해서 Authication를 세션에 
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security.domain.user.User;
import com.cos.security.domain.user.UserRepository;

@Service // 메모리에 띄움 
public class PrincipalDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	// 원래 UserDetailsService가 IoC에 떠있는데 안에 아무것도 구현이 되어 있지 않으니까 PrincipalDetailsService로 바꿔치기한 뒤
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // UserDetails -> Authication
		System.out.println(username + "님의 로그인 요청됨");
		
		// pw 체크는 스프링시큐리티가 알아서 체크해줌
		// DB에 해당 아이디가 있는지 조회 
		User user = userRepository.findByUsername(username);
		
		//user 오브젝트가 null이면 사용자 없음, user 오브젝트가 있으면 사용자 있으니까 pw 체크해야 함 
		if(user == null) {
			return null;
		} else {
			return new PrincipalDetails(user);
			/*
			return new UserDetails() {
				
				@Override
				public boolean isEnabled() {
					// 아이디가 활성화 되어있는지 비활성화되어있는지 (몇년 이상 미접속 시 비활성화되는거)
					return true; // 신경 안씀
				}
				
				@Override
				public boolean isCredentialsNonExpired() {
					// 비밀번호 오래쓰면 비활성화 됨 -> pw변경권고 
					return true; // 신경 안씀
				}
				
				@Override
				public boolean isAccountNonLocked() {
					// pw 오류 시 락 걸림 
					return true; // 신경 안씀
				}
				
				@Override
				public boolean isAccountNonExpired() {
					// 계정 활성화 여부 
					return true; // 신경 안씀
				}
				
				@Override
				public String getUsername() {
					// TODO Auto-generated method stub
					return user.getUsername();
				}
				
				@Override
				public String getPassword() {
					// TODO Auto-generated method stub
					return user.getPassword();
				}
				
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					// 권한
					return null;
				}
			};
			*/
		}
	}

}
