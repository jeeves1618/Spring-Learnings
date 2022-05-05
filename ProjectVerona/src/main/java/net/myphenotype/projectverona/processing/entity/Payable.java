package net.myphenotype.projectverona.processing.entity;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope(value = "prototype")
public class Payable {

    private String dateOfTransaction;
    private String Description;
    private double creditEntry;
    private double debitEntry;
    private double payableBalance;
    private String creditEntryFmtd;
    private String debitEntryFmtd;
    private String payableBalanceFmtd;
}
