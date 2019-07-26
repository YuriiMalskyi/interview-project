package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.config.jwt.JWTTokenFilterConfigurer;
import com.example.demo.config.jwt.JWTTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.csrf().disable().cors()
		 
		 .and()
		 
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 
		 .and()
		 
		 .authorizeRequests().antMatchers("/client/**", "/transactions-history/**", "/credit-card/**").hasAnyAuthority("", "CLIENT")
		 
		 .and()
			
		 .authorizeRequests().antMatchers("/auth/signin*").anonymous()
		 
		 
/*		 .antMatcher("/**").authorizeRequests()/*.anyRequest().anonymous()/*.authenticated()
		 	.and()
		 		.antMatcher("/auth/**").authorizeRequests().anyRequest().anonymous();*/
		 .and()
		 
		 .apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
