/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package net.myphenotype.jeeves.drools.section06;

import net.myphenotype.jeeves.drools.Common;
import net.myphenotype.jeeves.drools.domain.*;
import net.myphenotype.jeeves.drools.repository.InvoiceRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class MatchResult {
  public static void main(final String[] args) {
    execute(Common.promptForStep(6, args, 1, 6));
  }

  static void execute(int step) {
    System.out.println("Running step " + step);
    KieSession ksession = KieServices.Factory.get().getKieClasspathContainer().newKieSession("MatchResultStep" + step);

    ksession.addEventListener(new AgendaGroupEventListener(System.out));

    List<Invoice> invoices = InvoiceRepository.getInvoices();

    if (step == 5) {
      if (Common.promptForYesNoQuestion("Do you want to extend the due dates of all invoices to next year from now?")) {
        System.out.println("Setting all invoices as expired before Drools session starts");
        invoices.forEach(invoice -> invoice.setDueDate(LocalDate.now().plusYears(1)));
      }
    }

    invoices.forEach(ksession::insert);

    List<PurchaseOrder> purchaseOrders = InvoiceRepository.getPurchaseOrders();
    purchaseOrders.forEach(ksession::insert);

    /*Here we are adding the agenda group to a stack data structure. It
      will enable Drools to validate the first added rule to be validated last.
      Follows FILO.
     */
    if (step == 3) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("issue-payment").setFocus();
      agenda.getAgendaGroup("valid-po").setFocus();
      agenda.getAgendaGroup("invalid-po").setFocus();
      agenda.getAgendaGroup("valid-invoice").setFocus();
      agenda.getAgendaGroup("invalid-invoice-zero").setFocus();
      agenda.getAgendaGroup("invalid-invoice-expired").setFocus();
    }


    if (step == 4 || step == 5) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("issue-payment").setFocus();
      agenda.getAgendaGroup("validate-po").setFocus();
      agenda.getAgendaGroup("validate-invoice").setFocus();
    }


    /** BONUS STEP: set focus to first agenda group only */
    if (step == 6) {
      Agenda agenda = ksession.getAgenda();
      agenda.getAgendaGroup("validate-invoice").setFocus();
    }

    System.out.println("==== DROOLS SESSION START ==== ");
    ksession.fireAllRules();
    if (Common.disposeSession) {
      ksession.dispose();
    }
    System.out.println("==== DROOLS SESSION END ==== ");

    Collection<?> matchOutcomes = ksession.getObjects(o -> o.getClass() == MatchingOutcome.class);
    System.out.println(" ");
    System.out.println("== Payment instruction returned from Drools session == ");
    matchOutcomes.forEach(System.out::println);

    Collection<?> pos = ksession.getObjects(o -> o.getClass() == PurchaseOrder.class);
    System.out.println(" ");
    System.out.println("== Purchase Order Status returned from Drools session == ");
    pos.forEach(System.out::println);

  }

}
