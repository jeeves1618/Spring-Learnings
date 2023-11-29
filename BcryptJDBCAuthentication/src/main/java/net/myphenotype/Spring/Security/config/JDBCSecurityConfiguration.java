package net.myphenotype.Spring.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class JDBCSecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        /**
        Disabling the csrf is not idea for production implementations.sss
         */
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((requests) -> requests.requestMatchers("/myaccount","/mybalance","/myloan","/mycard").authenticated()
                        .requestMatchers("/mynotice","/mycontact","/signup").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    /*
    Below is the default jdbc security configurations.
    It is commented out because we are using custom user detail service.
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //Uses the default version, strength and randomiser (salt value).
        return new BCryptPasswordEncoder();
    }
}
