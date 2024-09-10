package com.example.studentmanagement.config;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.studentmanagement.filter.JwtFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String[] PUBLIC_URL = { "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
			"/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
			"/api/test/**", "/authenticate","/register/**", "/login/**" };
	
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors(c->c.configurationSource(configurationSource()))
		.csrf(c->c.disable())
		.headers(h->h.frameOptions(f->f.disable()))
		.authorizeHttpRequests(a->a
				.requestMatchers(PUBLIC_URL).permitAll()
				.anyRequest().authenticated())
		.userDetailsService(userDetailsService)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	

	
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
//	@Bean
//	public OpenAPI swaggerApi() {
//		return new OpenAPI()
//				.components(new Components().addSecuritySchemes("bearer-key", new SecurityScheme()
//						.name("bearerAuth")
//						.type(SecurityScheme.Type.HTTP)
//						.scheme("bearer")
//						.bearerFormat("JWT")
//						));
//	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info().title("Student Management System"))				
				.addSecurityItem(new SecurityRequirement().addList("JavaInUseSecurityScheme"))
				.components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
						.name("JavaInUseSecurityScheme").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
		
	}

	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	

}
