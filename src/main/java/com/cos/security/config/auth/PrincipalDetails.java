package com.cos.security.config.auth;
/*
 * 2020.10.19-9
 * 선행: PrincipalDetailsService.jave
 * 후행: 없음
 *
 * 여기까지: 회원가입, 로그인, 권한부여 완료됨
 * 
 */

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	private User user; // 상속 아니고 포함하고 있는 has-a 관계 
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 여기서부터는 시큐리티가 필요로 하고 시큐리티가 관리함 
	// 리턴값이 하나라도 false면 리턴 안됨 
	// 권한 체크 함수 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		
		/*
		Collection<GrantedAuthority> collect = new ArrayList<>();
		// 권한이 여러개면 .add 계속해주면 됨 
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		*/
		collect.add(() -> user.getRole()); // 위의 주석을 줄인 것 
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 확인, true: 만료 안됨
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정이 잠겨있는지 확인, true: 잠기지 않음 
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되었는지 확인, true: 만료안됨 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되었는지 확인, true: 활성화
	@Override
	public boolean isEnabled() {
		return true;
	}

}
