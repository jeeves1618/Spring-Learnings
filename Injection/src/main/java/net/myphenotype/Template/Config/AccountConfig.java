package net.myphenotype.Template.Config;

import net.myphenotype.Template.Domain.Account;
import net.myphenotype.Template.Domain.CreditAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    public Account creditAccount(){
        return new CreditAccount();
    }
}
