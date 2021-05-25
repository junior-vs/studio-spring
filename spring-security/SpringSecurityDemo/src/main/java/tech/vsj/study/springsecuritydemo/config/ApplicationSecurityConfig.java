package tech.vsj.study.springsecuritydemo.config;

import static tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole.ADMIN;
import static tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole.ADMIN_TRAINEE;
import static tech.vsj.study.springsecuritydemo.security.ApplicationsUserRole.STUDENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


  private static final String MANAGEMENT_API = "/management/api/**";
  private PasswordEncoder passwordEncoder;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/", "/css/*", "/js/*").permitAll()
        .antMatchers("/api/**").hasRole(STUDENT.name())
        /*
         * .antMatchers(DELETE, MANAGEMENT_API).hasAuthority(COURSE_WRITE.getPermission())
         * .antMatchers(PUT, MANAGEMENT_API).hasAuthority(COURSE_WRITE.getPermission())
         * .antMatchers(POST, MANAGEMENT_API).hasAuthority(COURSE_WRITE.getPermission())
         * .antMatchers(GET, MANAGEMENT_API).hasAnyRole(ADMIN_TRAINEE.name(), ADMIN_TRAINEE.name())
         */
        .anyRequest().authenticated().and()
        .httpBasic();
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    UserDetails user = User.builder().username("estudante").password(passwordEncoder.encode("pass"))
        .authorities(STUDENT.getAuthorities()).build();

    UserDetails admin = User.builder().username("administrador")
        .password(passwordEncoder.encode("pass")).authorities(ADMIN.getAuthorities()).build();

    UserDetails trainee = User.builder().username("trainee")
        .password(passwordEncoder.encode("pass")).authorities(ADMIN_TRAINEE.getAuthorities()).build();


    return new InMemoryUserDetailsManager(user, admin, trainee);


  }
}
