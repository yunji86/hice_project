package com.greedy.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.coffee.member.service.AuthenticationService;

@EnableWebSecurity
public class SecurityConfig {

	private final AuthenticationService authenticationService;
	
	@Autowired
	public SecurityConfig(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	/* 정적인 것들이 로그인 인가의 여부와 관계없이 계속 실행되도록 무시하는 Bean */
	@Bean
	public WebSecurityCustomizer configure() {
		
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
		
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http
        		.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/board/**", "/thumbnail/**", "/member/update", "/member/delete").hasAnyAuthority("ROLE_MEMBER", "ROLE_ADMIN")
                .mvcMatchers("/**", "/member/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/member/login")             
                    .defaultSuccessUrl("/")  
                    .failureForwardUrl("/member/loginfailed")
                    .usernameParameter("memId")			// 아이디 파라미터명 설정
                    .passwordParameter("memPwd")			// 패스워드 파라미터명 설정
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
    			.and()
    				.build();
		
	}
	
	/* 인증 위해 사용할 Service 등록, 사용 할 비밀번호 인코딩 방식 설정 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(authenticationService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
		
	}
	
}
