package net.myphenotype.Teller.Configuration;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Teller.Entity.Account;
import net.myphenotype.Teller.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class AccountLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    AccountRepository accountRepository;

    @Value("${app.numberOfAccounts}")
    private int numberOfAccounts;

    Faker accountGenerator = new Faker();
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Account> accounts = new ArrayList<>();
        String accountType;
        for(int i = 0; i < numberOfAccounts; i++){
            if(i%2 == 0) accountType = "Checking"; else  accountType = "Savings";
            accounts.add(new Account(accountGenerator.numerify("00########"),
                    accountGenerator.name().firstName(),
                    accountGenerator.name().lastName(),
                    accountType,
                    accountGenerator.number().randomDouble(2, 1000, 10000)));
        }
        accountRepository.saveAll(accounts);
        log.info("Number of accounts loaded : " + numberOfAccounts);
    }
}
