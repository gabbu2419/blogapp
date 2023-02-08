package com.myblog.config;


import com.myblog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
    /*
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Overrride
    protected void configure(HttpSecurity http) throws Exception{
    http.csrf().disable()
    .authorizeRequests()
    .antMatchers(HttpMethod.GET, "/api/**").permitAll() //get can be used by anyone
    .anyRequests()
    .authenticated()
    .and()
    .httpBasic();
    }
    @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

     }

*/
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Override
//      @Bean
//    protected  AuthenticationManager authenticationManager() throws Exception{
//        return super.authenticationManager();
//    }

}
