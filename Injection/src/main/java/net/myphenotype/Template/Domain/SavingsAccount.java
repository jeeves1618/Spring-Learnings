package net.myphenotype.Template.Domain;

import org.springframework.stereotype.Component;

@Component
public class SavingsAccount implements Account{
    @Override
    public String getAccountBalance() {
        return "The balance in your savings account is ";
    }
}
