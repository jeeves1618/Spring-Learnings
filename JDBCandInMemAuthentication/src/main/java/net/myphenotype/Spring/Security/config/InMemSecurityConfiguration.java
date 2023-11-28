package net.myphenotype.Spring.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
/*
Add @Configuration here and remove the @Configuration from JDBCSecurityConfiguration class
if you want to use in memory security configuration.

Having both the configurations in the same project will give Authentication Provider not found error.
 */
public class InMemSecurityConfiguration {

    @Bean
    SecurityFilterChain jdbcSecurityFilterChain(HttpSecurity http) throws Exception {
        /**
         *  Below is the custom security configurations
         */

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myaccount","/mybalance","/myloan","/mycard").authenticated()
                        .requestMatchers("/mynotice","/mycontact").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

        /**
         *  Configuration to deny all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

        /**
         *  Configuration to permit all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

    }

    /*
    Below is one approach to create user credentials. With these approach, we can remove the
    user IDs and Passwords from the application.properties file.
    */

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("main")
                .password("main123")
                .authorities("admin")
                .build();

        UserDetails readOnlyUser = User.withDefaultPasswordEncoder()
                .username("reader")
                .password("reader123")
                .authorities("read")
                .build();

        UserDetails primaryUser = User.withDefaultPasswordEncoder()
                .username("primary")
                .password("primary123")
                .authorities("readwrite")
                .build();

        UserDetails differentUser = User.withUsername("different").password("different123").authorities("read").build();
        /*
        If you just write the above line, we will get a failure saying There is no PasswordEncoder mapped for the id "null".
        So, we will add a passwordEncoder bean that defines the password encoder.
        But, if you return the NoOpPasswordEncoder instance, then the users with default password encoder will not work.
        Because, the passwordEncoder will be contending with withDefaultPasswordEncoder()
        */
        return new InMemoryUserDetailsManager(adminUser, readOnlyUser, primaryUser, differentUser);

        /*
        We are invoking InMemoryUserDetailsManager here.
        Other options are JdbcUserDetailsManager and LdapUserDetailsManager.
        These three classes are the implementation of UserDetailsManager interface.
        UserDetailsManager interface extends the UserDetailsService interface.
        */
    }
    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

     */
}
