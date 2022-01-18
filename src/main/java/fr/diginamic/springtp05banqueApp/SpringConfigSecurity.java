package fr.diginamic.springtp05banqueApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import fr.diginamic.springtp05banqueApp.providers.AppAuthProvider;
import fr.diginamic.springtp05banqueApp.services.UserService;

@Configuration
@EnableWebSecurity
public class SpringConfigSecurity extends WebSecurityConfigurerAdapter{
	
	
	/**
	 * Ajout de l'implementation du provider
	 */
	
	@Autowired
	UserService userDetailsService;
		
	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		http.csrf().disable()
		.authenticationProvider(getProvider())
		.formLogin().loginProcessingUrl("/login").and()
		.logout().logoutUrl("/logout").invalidateHttpSession(true).and()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		// authorise toutes les connexions REST sur /api
		.antMatchers("/api/**").permitAll()
		.anyRequest().authenticated().and().httpBasic();
	}
	
}
