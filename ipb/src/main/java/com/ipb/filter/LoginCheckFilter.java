//package com.ipb.filter;
//
//import com.ipb.provider.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//@RequiredArgsConstructor
//public class LoginCheckFilter extends GenericFilterBean {
//  private final JwtProvider jwtProvider;
//
//  // request로 들어오는 Jwt의 유효성을 검증 - JwtProvider.validationToken() 을 필터로서 FilterChain에 추가
//  @Override
//  public void doFilter(ServletRequest request,
//                       ServletResponse response,
//                       FilterChain filterChain) throws IOException, ServletException {
//    String token = jwtProvider.resolveToken((HttpServletRequest) request);
//    System.out.println("필터토큰"+token);
//    if (token != null && jwtProvider.validationToken(token)) {
//      Authentication authentication = jwtProvider.getAuthentication(token);
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
//    filterChain.doFilter(request, response);
//  }
//}
