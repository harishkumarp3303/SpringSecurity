package com.security.securityConfig;

import java.util.Collections;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.security.filters.CustomFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaltSecurityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cus -> cus.configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub
				CorsConfiguration configuration = new CorsConfiguration();
				configuration.setAllowedOriginPatterns(Collections.singletonList("http://localhost:4200"));
				configuration.setAllowedMethods(Collections.singletonList("*"));
				configuration.setAllowCredentials(true);
				configuration.setAllowedHeaders(Collections.singletonList("*"));
				configuration.setMaxAge(3600L);

				return configuration;
			}
		}))
				.csrf(csrf -> csrf.disable())
				.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((requests) -> {
					requests.requestMatchers("security/demo", "security/tournament").authenticated()
							.requestMatchers("security/teams", "security/players", "/register", "video/play",
									"video/pause")
							.permitAll();
				}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		return http.build();

	}

	/* configuration to deny all the request */

	// http.authorizeHttpRequests((requests)->{
	// requests.anyRequest().denyAll();
	// })
	// .formLogin(Customizer.withDefaults())
	// .httpBasic(Customizer.withDefaults());
	// return http.build();
	//

	/* Configuration to permit all the request */

	// http.authorizeHttpRequests((requests)->{
	// requests.anyRequest().permitAll();
	// })
	// .formLogin(Customizer.withDefaults())
	// .httpBasic(Customizer.withDefaults());
	// return http.build();

	///////////////////////////////////////////////////////////////////////////

	// InMemoryUserDetailsManager

	// Approach 1 where we use withDefaultPasswordEncoder() method
	// while creating the user details
	// @Bean
	// public InMemoryUserDetailsManager userDetailsService() {
	// UserDetails admin = User.withDefaultPasswordEncoder()
	// .username("admin")
	// .password("12345")
	// .authorities("admin")
	// .build();
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("12345")
	// .authorities("read")
	// .build();
	// return new InMemoryUserDetailsManager(admin, user);
	//
	// }

	// Approach 2 where we use NoOpPasswordEncoder Bean
	// while creating the user details
	// @Bean
	// public InMemoryUserDetailsManager userDetailsServiceWithPasswordEncrption() {
	//
	// UserDetails admin = User.withDefaultPasswordEncoder()
	// .username("admin")
	// .password("678910")
	// .authorities("admin")

	// .build();
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("678910")
	// .authorities("read")
	// .build();
	// return new InMemoryUserDetailsManager(admin, user);
	//
	// }
	//
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// jdbcUserdetailsManager

	// @Bean
	// public UserDetailsService userDetailsService(DataSource dataSource) {
	// return new JdbcUserDetailsManager(dataSource);
	// }

}
