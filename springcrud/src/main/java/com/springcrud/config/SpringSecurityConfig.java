package com.springcrud.config;

//import static com.springcrud.constant.ServiceConstants.*;
//import static com.springcrud.utils.ServiceUtils.getenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // user can only access get and admin get both
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("root").roles("ADMIN").and()
                .withUser("user").password("user123").roles("USER");

        // auth.inMemoryAuthentication().withUser(getenv(BASIC_AUTH_USERNAME))
        // .password(getenv(BASIC_AUTH_PASSWORD)).roles("ADMIN");
        // .and().withUser("user").password("password").roles("USER");
        // auth.inMemoryAuthentication().withUser(getenv(BASIC_AUTH_USERNAME));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
        // http.httpBasic().and().authorizeRequests().antMatchers("/api/getemp").hasRole("USER")
        // .antMatchers("/api/addemp",
        // "/api/emp/{id}").hasRole("ADMIN").and().csrf().disable().headers()
        // .frameOptions().disable();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
