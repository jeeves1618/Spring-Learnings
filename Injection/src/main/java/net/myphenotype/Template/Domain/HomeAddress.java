package net.myphenotype.Template.Domain;

import org.springframework.stereotype.Component;

@Component
public class HomeAddress implements Address{

    @Override
    public String getAddress() {
        return "1 Wall Street, New York City, NY 007031";
    }
}
