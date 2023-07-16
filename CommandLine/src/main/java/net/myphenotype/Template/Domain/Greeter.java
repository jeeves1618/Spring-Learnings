package net.myphenotype.Template.Domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Greeter {
    private String greeting;
}
