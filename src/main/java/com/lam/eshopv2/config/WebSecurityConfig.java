package com.lam.eshopv2.config;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import com.lam.eshopv2.services.UserDetailsServiceImpl;
import com.lam.eshopv2.utils.Eshop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
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

    /*
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();

            // Các yêu cầu phải login với vai trò ROLE_EMPLOYEE hoặc ROLE_MANAGER.
            // Nếu chưa login, nó sẽ redirect tới trang /admin/login.
            http.authorizeRequests().antMatchers("/admin/orderList", "/admin/order", "/admin/accountInfo")//
                    .access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

            // Các trang chỉ dành cho MANAGER
            http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");

            // Khi người dùng đã login, với vai trò XX.
            // Nhưng truy cập vào trang yêu cầu vai trò YY,
            // Ngoại lệ AccessDeniedException sẽ ném ra.
            http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

            // Cấu hình cho Login Form.
            http.authorizeRequests().and().formLogin()//
                    //
                    .loginProcessingUrl("/j_spring_security_check") // Submit URL
                    .loginPage("/login")//
                    .defaultSuccessUrl("/accountInfo")//
                    .failureUrl("/login?error=true")//
                    .usernameParameter("userName")//
                    .passwordParameter("password")

                    // Cấu hình cho trang Logout.
                    // (Sau khi logout, chuyển tới trang home)
                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        }
        */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/").hasRole(Eshop.EMPLOYEE_ROLE)
                    .antMatchers("/admin").hasRole(Eshop.ADMIN_ROLE)

                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("name")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/403");
    }
}