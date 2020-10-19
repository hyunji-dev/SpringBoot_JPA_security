package com.cos.security.domain.user;
/*
 * 2020.10.19-5 
 * 선행: loginForm.jsp
 * 후행: joinForm.jsp
 * 
 * 테이블 생성 클래스 
 * 
 * domain 패키지 내에는 DB의 테이블과 동일하게 만들어줌 
 * 
 * dto: 벨리데이션 체크를 위해 만듦
 * dto를 만드는 방식 2가지: 
 * 
 * 데이터베이스 필드가 20개 정도 있는데 20개를 다 받아야 하면 
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 풀생성자 추가
@NoArgsConstructor // 빈생성자 추가
@Builder // 빌더패턴 사용 (빌더패턴을 사용하려면 빈생성자를 꼭 추가해줘야함)
@Data // getter, setter + toString() 추가 
@Entity // ORM(오브젝트 릴레이션 맵핑)
public class User {

	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	
	@Column(unique = true) // 유니크키
	private String username;
	
	private String password;
	private String email;
	private String role; // 권한
}
