package br.ufscar.dc.dsw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/error", "/login/**", "/js/**").permitAll()
                        .requestMatchers("/css/**", "/image/**", "/webjars/**").permitAll()
                        .requestMatchers("/consultas/cadastrarConsulta").hasAnyRole("CLIENTE", "PROFISSIONAL")
                        .requestMatchers("/consultas/minhasConsultas").hasAnyRole("CLIENTE", "PROFISSIONAL")
                        .requestMatchers("/consultas/**").hasRole("ADMIN")
                        .requestMatchers("/consultas/salvar").hasAnyRole("CLIENTE", "PROFISSIONAL")
                        .requestMatchers("/profissionais/**").hasAnyRole("ADMIN", "PROFISSIONAL")
                        .requestMatchers("/clientes/**").hasAnyRole("ADMIN", "CLIENTE")
                        .requestMatchers("/usuarios/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/pesquisar").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}