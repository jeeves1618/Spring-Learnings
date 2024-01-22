package net.myphenotype.Configurations.Entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class BookSearch {
    private String searchString;
}
