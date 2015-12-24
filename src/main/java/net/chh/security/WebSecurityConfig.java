package net.chh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
      .httpBasic()
    .and()
      .authorizeRequests()
      .antMatchers("/index.html",
        "/app/common/**",
        "/app/login.js", 
        "/app/boot.js",
        "/app/login/**",
        "/app/app.component.js",
        "/app/boot.js",
        "/js/**"
        ).permitAll()
      .anyRequest().authenticated()
    .and()
      .formLogin()
      .loginProcessingUrl("/login")
      .loginPage("/index.html")
      .defaultSuccessUrl("/app/home/home.html")
      .usernameParameter("username")
      .passwordParameter("password")
      .permitAll()
    .and()
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/")
      .permitAll()
    .and()
      .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
      .csrf()
      .csrfTokenRepository(csrfTokenRepository())
      ;
    //@formatter:on
  }

  /**
   * Global configuration for users.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
    .inMemoryAuthentication()
    .withUser("user").password("password").roles("USER");
  }
  
  private CsrfTokenRepository csrfTokenRepository() {
    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
    repository.setHeaderName("x-csrf-token");
    return repository;
  }
}
