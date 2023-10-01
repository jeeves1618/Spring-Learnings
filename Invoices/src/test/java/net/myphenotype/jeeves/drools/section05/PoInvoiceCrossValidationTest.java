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
import net.myphenotype.jeeves.drools.TestUtil;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PoInvoiceCrossValidationTest {

  @Test
  void shouldExecuteAllSteps() {
    PoInvoiceCrossValidation.execute(1);
    PoInvoiceCrossValidation.execute(2);
    PoInvoiceCrossValidation.execute(3);
  }

  @Test
  void testStep1() {
    Common.disposeSession = false;
    int step = 1;

    String kieSessionName = "PurchaseOrderMatchingStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    PoInvoiceCrossValidation.execute(step);

    KieSession ksession = TestUtil.getKieSession("PurchaseOrderMatchingStep", step);

  }

}
