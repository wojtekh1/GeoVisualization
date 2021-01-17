package com.handel.geo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/** Klasa konfiguracyjna */
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    /** Metoda konfiguracyjna sposób autentykacji*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    /** Metoda konfiguracyjna zabezpieczeń połączeń http */
    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                .httpBasic()
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/h2-console/*").permitAll()
//                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/locators").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/location").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/allLocators").hasAnyAuthority("ADMIN")
//                .antMatchers("/registration").permitAll()
                .antMatchers("/locators/*").authenticated()
                .antMatchers("/location/*").authenticated()
                .antMatchers("/registration").permitAll()
                .and().csrf().disable().formLogin()
                .loginPage("/login")
//                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/",true)
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and().logout().deleteCookies("JSESSIONID").and().rememberMe().key("remember-me-new")
                .and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**",
                        "/images/**","/webjars/**","/h2-console/**");
    }
}
