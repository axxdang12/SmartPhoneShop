package swp391.SPS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import swp391.SPS.services.impls.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfig {

  @Autowired UserDetailServiceImpl userDetailService;

  @Autowired EncoderConfig encoderConfig;

  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService).passwordEncoder(encoderConfig.passwordEncoder());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (request) ->
                request
                    .requestMatchers(
                        "/", "/index", "../static/css", "../static/js", "../static/images")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .logout((logout) -> logout.permitAll());

    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.ignoring()
            .requestMatchers(
                "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
  }
}
