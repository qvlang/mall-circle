package com.lang.gmall.config;

import com.lang.gmall.component.JwtAuthticationTokenFilter;
import com.lang.gmall.component.RestAuthenticationEntryPoint;
import com.lang.gmall.component.RestfulAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        /*允许静态资源访问*/
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()
                .antMatchers("/admin/login", "/admin/register") //允许登录注册匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) //跨域前的预请求允许访问
                .permitAll()
                .anyRequest()
                .authenticated();
        http.headers().cacheControl(); //禁用缓存
        //添加jwttoken过滤器
        http.addFilterBefore(getJwtAuthticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加异常处理器
        http.exceptionHandling()
                .accessDeniedHandler(getRestfulAccessDeniedHandler())
                .authenticationEntryPoint(getRestAuthenticationEntryPoint());
        //开启跨域
        http.cors();
    }

    //加密组件
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //异常处理：没有权限
    @Bean
    public RestfulAccessDeniedHandler getRestfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    //未登录或者token失效
    @Bean
    public RestAuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    //增加token验证过滤器
    @Bean
    public JwtAuthticationTokenFilter getJwtAuthticationTokenFilter() {
        return new JwtAuthticationTokenFilter();
    }
}
