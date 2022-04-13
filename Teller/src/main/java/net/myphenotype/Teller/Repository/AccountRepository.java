package net.myphenotype.Teller.Repository;

import net.myphenotype.Teller.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findByAccountType(String accountType);

    List<Account> findByFirstNameStartingWith(String firstName);

    @Query ("select a from Account a where a.accountBalance > ?1 ")
    List<Account> findByBalanceAbove(double accountBalance);
}
