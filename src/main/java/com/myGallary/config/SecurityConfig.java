package com.myGallary.config;


import com.myGallary.Repository.ERole;
import com.myGallary.error.CustomAuthFailureHandler;
import com.myGallary.error.CustomAuthSuccessHandler;
import com.myGallary.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 암호화 처리

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    ///////////////////////////////////////

    // 권한 처리 기능

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /////////////////////////////////////////

    // 웹 데이터 소스 처리
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/css/**","/js/**","/images/**","/lib/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/registration", "/h2/**").permitAll()

                .antMatchers("/home/guest").permitAll()

                .antMatchers("/home/**").hasAuthority(ERole.ADMIN.getValue())

                .antMatchers( "/home/**").hasAuthority(ERole.MANAGER.getValue())

                //.antMatchers("/home/guest").hasAuthority(ERole.GUEST.getValue())

                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions().disable()
                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .usernameParameter("username")
                .passwordParameter("password")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied") );
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthFailureHandler();
    }

}
