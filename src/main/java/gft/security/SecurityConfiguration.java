package gft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gft.filter.FiltroAutenticacao;
import gft.services.AutenticacaoService;
import gft.services.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UsuarioService usuarioService;
	private final AutenticacaoService autenticacaoService;

	public SecurityConfiguration(UsuarioService usuarioService, AutenticacaoService autenticacaoService) {
		this.usuarioService = usuarioService;
		this.autenticacaoService = autenticacaoService;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	private static final String[] AUTH_WHITELIST = {
		"/swagger-ui/**",
		"/swagger-ui.html",
		"/v3/api-docs/**",
		"/h2-console/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers(HttpMethod.POST, "/v1/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService), UsernamePasswordAuthenticationFilter.class);
		
		http.headers().frameOptions().disable();
	}
}