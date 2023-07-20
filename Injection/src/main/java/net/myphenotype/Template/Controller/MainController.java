package net.myphenotype.Template.Controller;

import net.myphenotype.Template.Domain.Account;
import net.myphenotype.Template.Domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private Account mySavingsAccount;
    private Address myHomeAddress;

    //Constructor Injection
    @Autowired
    public MainController(@Qualifier("savingsAccount") Account mySavingsAccount) {
        this.mySavingsAccount = mySavingsAccount;
    }

    //Setter Ingjection
    @Autowired
    public void setAddress(@Qualifier("homeAddress") Address myHomeAddress){
        this.myHomeAddress = myHomeAddress;
    }
    @GetMapping("/balance")
    public String showAccountBalance(){
        return mySavingsAccount.getAccountBalance();
    }

    @GetMapping("/address")
    public String showAddress(){
        return myHomeAddress.getAddress();
    }
}
