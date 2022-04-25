package com.example.managementlibrary.config;

import com.example.managementlibrary.common.ERole;
import com.example.managementlibrary.security.CustomLogoutSuccessHandler;
import com.example.managementlibrary.security.CustomSuccessHandler;
import com.example.managementlibrary.security.CustomUserDetailsService;
import com.example.managementlibrary.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

    }


    static JwtUtils jwtUtils;

    @Autowired
    public SecurityConfig(JwtUtils jwtUtils){
        this.jwtUtils=jwtUtils;
    }


    @Bean
    public static AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler(jwtUtils);
    }

    @Bean
    public static LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }




    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Configuration
    public static class SecurityConfigJwt extends WebSecurityConfigurerAdapter{

        @Autowired
        private AuthEntryPointJwt authEntryPointJwt;



        @Bean
        public AuthTokenFilter authenticationJwtTokenFilter(){
            return new AuthTokenFilter();
        }

        @Override
        @Bean
        protected AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()//xu ly khi request 403
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//khong tao session
                    .and()
                    .antMatcher("/v1/api/**")
                    .authorizeRequests()
                    .antMatchers("/v1/api/auth/**",
                            "/v1/api/users/reset_password")
                    .permitAll()
                    .antMatchers(HttpMethod.GET,"/v1/api/books/*","/v1/api/books","/v1/api/categories",
                            "/v1/api/comments/book/{id}","/v1/api/users/{id}/logout").permitAll()
                    .anyRequest().authenticated();
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }



}

