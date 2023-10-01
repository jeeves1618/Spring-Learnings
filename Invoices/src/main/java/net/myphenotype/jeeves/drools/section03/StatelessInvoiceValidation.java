package net.myphenotype.jeeves.drools.section03;

import net.myphenotype.jeeves.drools.CommonUtil;
import net.myphenotype.jeeves.drools.domain.Invoice;
import net.myphenotype.jeeves.drools.repository.InvoiceRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.List;

public class StatelessInvoiceValidation {

    public static void main(final String[] args) {
        execute(CommonUtil.promptForStep(3, args, 1, 6));
    }

    static void execute(int step) {
        System.out.println("Running Invoice Validation Step: " + step);

        List<Invoice> invoices = InvoiceRepository.getInvoices();

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession("StatelessInvoiceValidationStep" + step);
        System.out.println("==== DROOLS SESSION START ==== ");
        kieSession.execute(invoices);
        System.out.println("==== DROOLS SESSION END ==== ");

        if (step >= 4) {
            System.out.println("==== PASSPORTS AFTER DROOLS SESSION ==== ");
            invoices.forEach(invoice -> System.out.println("This invoice " + invoice + " validation " + invoice.getValidation()));
        }
    }
}
