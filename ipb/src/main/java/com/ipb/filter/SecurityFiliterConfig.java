//package com.ipb.filter;
//
//import com.ipb.provider.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@RequiredArgsConstructor
//@Configuration
//public class SecurityFiliterConfig extends WebSecurityConfigurerAdapter {
//
//  private final JwtProvider jwtProvider;
//
//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//        .httpBasic().disable()
//        .csrf().disable()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//        .antMatchers("/staff/*","/product/*").permitAll()
//        .anyRequest().hasRole("USER")
//        .and()
//        .addFilterBefore(new LoginCheckFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
//  }
//
//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
//        "/swagger-ui.html", "/webjars/**", "/swagger/**", "/webwars/**", "/webwar/**");
//  }
//}
