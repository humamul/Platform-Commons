package com.platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	public static final String[] PUBLIC_URLS = {"/api/test/**", "/v3/api-docs", "/v2/api-docs",
            "/swagger-resources/**",  "/webjars/**"

    };

	@Autowired
	private UserDetailsServiceImpl userDetailService;
	  @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception
	    {
	      return configuration.getAuthenticationManager();
	    }


		@Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider() {
	
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(this.userDetailService);
	        provider.setPasswordEncoder(getPasswordEncoder());
	        return provider;
	
	    }
	
		 @Bean
		    public BCryptPasswordEncoder getPasswordEncoder() {
		        return new BCryptPasswordEncoder();
		    }
	 

		
			@Bean
			 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
				
				http
				.cors().and()
				.csrf()
				.disable()
				.authorizeRequests()
		        .antMatchers(PUBLIC_URLS).permitAll()
				.antMatchers("/api/admin").hasRole("ADMIN")
				.antMatchers("/api/course/**").permitAll()
				.antMatchers("/api/student/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
				
				 http.authenticationProvider(daoAuthenticationProvider());
		
			      return  http.build();
			}

	  

}
