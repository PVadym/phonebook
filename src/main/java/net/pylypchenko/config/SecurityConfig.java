package net.pylypchenko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Spring Security configuration class.
 *
 * @author Vadym Pylypchenko
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * An instance of UserDetailsService for working with registered users.
     */
    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * The Method sets users access to pages of the site.
     *
     * @param httpSecurity An instance of {@link HttpSecurity} class
     * @throws Exception An exception that can be thrown by HttpSecurity class methods
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .invalidateHttpSession(false)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .csrf().disable();
    }

    /**
     * Method for configuration authentication of users
     *
     * @param builder An instance of {@link AuthenticationManagerBuilder} class
     * @throws Exception An exception that can be thrown by AuthenticationManagerBuilder class methods
     */
    @Override
    @Autowired
    protected void configure(final AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * The method configures an implementation of {@link PasswordEncoder}
     *
     * @return an instance of {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
