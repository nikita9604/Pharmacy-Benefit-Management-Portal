package com.nikita.springbootfinal.security.configuration;

//import com.nikita.springbootfinal.security.CustomUserDetailService;
import com.nikita.springbootfinal.security.MySimpleUrlAuthenticationSuccessHandler;
import com.nikita.springbootfinal.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MySimpleUrlAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Building our own http authentication and authorization strategies
        //super.configure(http);
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        // http.headers().disable()
        http.authorizeRequests()
                .mvcMatchers("/login","/addP").permitAll()
                .mvcMatchers(HttpMethod.GET,"/getD").hasRole("ADMIN")
                .anyRequest()
                .authenticated().and().httpBasic()
                .and().formLogin().successHandler(myAuthenticationSuccessHandler).and().csrf().disable();
//                .formLogin().loginPage("/login").defaultSuccessUrl("/",true);

        // http.authorizeRequests().anyRequest().permitAll();


    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //creating my own in-memory implementation with test user
//        //spring expects each password
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication().withUser("Victoria").password(encoder.encode("password")).roles("ADMIN")
//                .and().withUser("user1").password(encoder.encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(encoder.encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(encoder.encode("adminPass")).roles("ADMIN");
//        ;
//
//    }
}