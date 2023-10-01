/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package net.myphenotype.jeeves.drools.section05;

import net.myphenotype.jeeves.drools.Common;
import net.myphenotype.jeeves.drools.domain.Invoice;
import net.myphenotype.jeeves.drools.domain.PurchaseOrder;
import net.myphenotype.jeeves.drools.repository.InvoiceRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.List;

public class PoInvoiceCrossValidation {
  public static void main(final String[] args) {
    execute(Common.promptForStep(5, args, 1, 3));
  }


  static void execute(int step) {
    System.out.println("Running step " + step);
    KieContainer kieClasspathContainer = KieServices.Factory.get().getKieClasspathContainer();
    KieSession ksession = kieClasspathContainer.newKieSession("PurchaseOrderMatchingStep" + step);

    List<Invoice> invoices = InvoiceRepository.getInvoices();
    invoices.forEach(ksession::insert);

    List<PurchaseOrder> purchaseOrders = InvoiceRepository.getPurchaseOrders();
    purchaseOrders.forEach(ksession::insert);

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    if (Common.disposeSession) {
      ksession.dispose();
    }
    System.out.println("==== DROOLS SESSION END ==== ");

    System.out.println("==== INVOICES AFTER DROOLS SESSION === ");
    invoices.forEach(invoice -> System.out.println(invoice + " verdict: " + invoice.getValidation()));

    System.out.println("==== MATCHING STATE AFTER DROOLS SESSION === ");
    purchaseOrders.forEach(purchaseOrder ->
        System.out.println(purchaseOrder + " verdict: " + purchaseOrder.getValidation())
    );
  }

}
