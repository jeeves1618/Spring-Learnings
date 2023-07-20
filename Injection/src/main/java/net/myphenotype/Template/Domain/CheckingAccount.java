package net.myphenotype.Template.Domain;

import org.springframework.stereotype.Component;

@Component
public class CheckingAccount  implements Account{

    public String getAccountBalance() {
        return "The balance in your checking account is ";
    }
}
