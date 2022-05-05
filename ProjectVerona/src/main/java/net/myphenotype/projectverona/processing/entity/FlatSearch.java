package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FlatSearch {
    private String apartmentName;
    private String apartmentFormat;
    private String apartmentFloor;
}
