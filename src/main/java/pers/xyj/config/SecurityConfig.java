package pers.xyj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pers.xyj.filter.JwtAuthenticationTokenFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                // swagger start
                .antMatchers("/swagger-ui.html")
                .antMatchers( "/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("configuration/ui")
                .antMatchers("configuration/security")
                .antMatchers("/webjars/**");
                // swagger end

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //??????csrf
                .csrf().disable()
                //?????????Session??????SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                // ?????????????????? ??????????????????
                .antMatchers("/login").anonymous()
                .antMatchers("/users/register").anonymous()
                .antMatchers("/goods/**").anonymous()
                .antMatchers("/logout").authenticated()

                .antMatchers("/user/**").authenticated()
                .antMatchers("/users/userinfo").authenticated()
                .antMatchers("/upload/**").authenticated()
                .antMatchers("/users/userInfo").authenticated()
                .antMatchers("/sys/**").access("hasAuthority('system:admin')")




                // ????????????????????????????????????????????????????????????
                .anyRequest().permitAll();

        http.logout().disable();

        //?????????????????????
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);


        //?????????????????????
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //????????????
        http.cors();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
