package com.dpk.ToDoApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	
	//@Autowired
	//public UserDetailsService getDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getDetailsService() {
		
		return new CustomUserDetailsService();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		//daoAuthenticationProvider.setUserDetailsService(getDetailsService);
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
//		//.requestMatchers("/admin/**").hasRole("ADMIN")
//		.requestMatchers("/**").permitAll()
//		.and().formLogin().loginPage("/").loginProcessingUrl("/userLogin")
//		//.usernameParameter("email")
//		//.passwordParameter("password")
//		.defaultSuccessUrl("/user/todo/add")
//		.permitAll();
		
		// ===if we write this above , then we set role ("ROLE_USER) in database[i.e. in service impl]
		
		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/user/**").hasAuthority("USER")
		//.requestMatchers("/admin/**").hasAuthority("ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin().loginPage("/").loginProcessingUrl("/userLogin")
		//.usernameParameter("email")
		//.passwordParameter("password")
		.defaultSuccessUrl("/user/todo/add")
		.permitAll();
		
		
		
	
		
		
		return http.build();
		
	}
	
	
	
}
