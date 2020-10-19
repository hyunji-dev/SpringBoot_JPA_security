package com.cos.security.domain.user;
/*
 * 2020.10.19-7
 * 선행: joinForm.jsp
 * 후행: PrincipalDetailsService.java
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}