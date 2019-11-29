package cn.math.browser.security;

import cn.math.browser.security.handler.MathAuthenticationFailureHandler;
import cn.math.browser.security.handler.MathAuthenticationSuccessHandler;
import cn.math.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MathAuthenticationSuccessHandler mathAuthenticationSuccessHandler;
    @Autowired
    private MathAuthenticationFailureHandler mathAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.formLogin()//改变认证方式
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(mathAuthenticationSuccessHandler)
                .failureHandler(mathAuthenticationFailureHandler)
                .and()//并且语法
                .authorizeRequests()//授权
                    .antMatchers("/user","/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()//任何请求必须经过授权
                .authenticated()
                .and()
                .csrf().disable();//关闭默认跨站防护
    }
}
