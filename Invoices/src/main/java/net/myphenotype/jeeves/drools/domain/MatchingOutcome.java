package net.myphenotype.jeeves.drools.domain;

public class MatchingOutcome {

    private String invoiceNumber;

    public MatchingOutcome(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @Override
    public String toString() {
        return "MatchingOutcome{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                '}' + " sent for payment";
    }
}
