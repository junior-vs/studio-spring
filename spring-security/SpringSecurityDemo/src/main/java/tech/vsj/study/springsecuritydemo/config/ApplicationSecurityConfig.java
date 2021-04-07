package tech.vsj.study.springsecuritydemo.config;

import static tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole.ADMIN;
import static tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole.STUDENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


  private PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .antMatchers("/", "/css/*", "/js/*").permitAll()
    .antMatchers("/api/**").hasRole(ApplicationsUserRole.STUDENT.name())
    .anyRequest()
    .authenticated()
    .and()
    .httpBasic();
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    UserDetails user = User.builder().username("usuario").password(passwordEncoder.encode("pass"))
        .roles(STUDENT.name()).build();

    UserDetails admin = User.builder().username("administrador")
        .password(passwordEncoder.encode("pass")).roles(ADMIN.name()).build();

    return new InMemoryUserDetailsManager(user, admin);


  }
}
