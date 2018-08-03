package com.lam.eshopv2.config;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import com.lam.eshopv2.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/products").permitAll()
                .antMatchers("/saveproduct").permitAll()
                .antMatchers("/shop/authorizedShoppingCart").access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

        // Các yêu cầu phải login với vai trò ROLE_EMPLOYEE hoặc ROLE_MANAGER.
        // Nếu chưa login, nó sẽ redirect tới trang /admin/login.

        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                //
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/shop/login")//
                .defaultSuccessUrl("/")//
              //  .successHandler(successHandler()) //redirect to current page
                .failureUrl("/login?error=true")//
                .usernameParameter("name")//
                .passwordParameter("password")

                // Cấu hình cho trang Logout.
                // (Sau khi logout, chuyển tới trang home)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
}