package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import tacos.User;
import tacos.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*@Bean("passwordEncoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}/*
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
				.withUser("buzz")
					.password("infinity")
					.authorities("ROLE_USER")
				.and()
				.withUser("woody")
					.password("bullseye")
					.authorities("ROLE_USER");
	}
	*/
	
	/*
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
					"select username, password, enabled from Users " + "where username = ?")
				.authoritiesByUsernameQuery(
					"select username, authority from UserAuthorities " + "where username = ?")
				.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
	}*/
	
	/*@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userSearchFilter("(uid={0})")
				.groupSearchFilter("member={0}");
	}*/
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<User> users = new ArrayList<>();
	    userRepository.findAll().forEach(i -> {users.add(i); });
	    
	    System.out.println("before printing user rep :)");
	    for (User c : users) {
	    	System.out.println("printing user repository...");
	    	System.out.println(c.getUsername());
	    }
		
		http
		.authorizeRequests()                                  
	            .mvcMatchers("/", "/**", "/design").permitAll()         
	            .mvcMatchers("/orders").hasRole("USER")
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/design");
	}
}
	
