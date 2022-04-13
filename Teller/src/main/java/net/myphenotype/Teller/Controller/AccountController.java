package net.myphenotype.Teller.Controller;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Teller.Entity.Account;
import net.myphenotype.Teller.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{accountKey}")
    public Optional<Account> getAccountsById(@PathVariable Integer accountKey){
        return accountRepository.findById(accountKey);
    }

    @GetMapping("/accounts/type/{accountType}")
    public List<Account> getAccountsByType(@PathVariable String accountType){
        return accountRepository.findByAccountType(accountType);
    }

    @GetMapping("/accounts/firstname/{firstName}")
    public List<Account> getAccountsByFirstName(@PathVariable String firstName){
        return accountRepository.findByFirstNameStartingWith(firstName);
    }

    @GetMapping("/accounts/balanceabove/{accountBalance}")
    public List<Account> getAccountsByBalanceAbove(@PathVariable double accountBalance){
        return accountRepository.findByBalanceAbove(accountBalance);
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestBody Account account){
        accountRepository.save(account);
        return account;
    }

    @PutMapping("/accounts")
    public Account updateAccount(@RequestBody Account account){
        accountRepository.save(account);
        return account;
    }
}
